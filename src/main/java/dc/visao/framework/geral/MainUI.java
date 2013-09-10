package dc.visao.framework.geral;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.porotype.iconfont.FontAwesome;
import com.vaadin.annotations.Theme;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import dc.visao.framework.geral.fake.DataProvider;

@Component
@Scope("request")
@SuppressWarnings("serial")
@Theme("dotcompany")
public class MainUI extends UI implements ErrorHandler {


	public DataProvider dataProvider = new DataProvider();
	public static Logger logger = LoggerFactory.getLogger(MainUI.class);
	
	@Autowired(required = true)
	public MainController mainController;
	
	
	@Autowired SessionFactory sessionFactory;
	
	private String contextPath = "";
	private String pathInfo = "";
	
	@Override
	protected void init(VaadinRequest request) {
		logger.info("vaadin init called");
		FontAwesome.load();
		contextPath = request.getContextPath();
		String p =request.getPathInfo();
		populatePathInfo(p);
		initApp();
	}

	private void populatePathInfo(String p) {
		String[] pa = p.split("/");
		if(pa.length >= 2){
			pathInfo = pa[1];	
		}else{
			pathInfo = "";
		}
	}

	private void initApp() {
		 getPage().addUriFragmentChangedListener(
	               new UriFragmentChangedListener() {
	           public void uriFragmentChanged(
	                   UriFragmentChangedEvent source) {
	               logger.info("URI FRAGMENT CHANGED: " + source.getUriFragment());
	               logger.info("PAGE: " + source.getPage().getLocation());
	            }
	        });
		 
		 mainController.initNavigationMenu(this);
		 mainController.navigateTo(pathInfo);
	}

    @Override
    public void error(com.vaadin.server.ErrorEvent event)
    {
        // connector event
        if (event.getThrowable().getCause() instanceof AccessDeniedException)
        {
            AccessDeniedException accessDeniedException = (AccessDeniedException) event.getThrowable().getCause();
            Notification.show(accessDeniedException.getMessage(), Notification.Type.ERROR_MESSAGE);

            setContent(null);
            return;
        }

        // Error on page load. Now it doesn't work. User sees standard error page.
        if (event.getThrowable() instanceof AccessDeniedException)
        {
            AccessDeniedException exception = (AccessDeniedException) event.getThrowable();

            Label label = new Label(exception.getMessage());
            label.setWidth(-1, Unit.PERCENTAGE);

            Link goToMain = new Link("Ir para a home", new ExternalResource("/"));

            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(label);
            layout.addComponent(goToMain);
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            layout.setComponentAlignment(goToMain, Alignment.MIDDLE_CENTER);

            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setSizeFull();
            mainLayout.addComponent(layout);
            mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

            setContent(mainLayout);
            Notification.show(exception.getMessage(), Notification.Type.ERROR_MESSAGE);
            return;
        }

        DefaultErrorHandler.doDefault(event);
    }


	public String getContextPath() {
		return contextPath ;
	}
	
	public String getPathInfo() {
		return pathInfo ;
	}



}
