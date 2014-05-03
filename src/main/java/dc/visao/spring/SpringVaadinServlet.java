package dc.visao.spring;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.DefaultUIProvider;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.ui.UI;

/**
 * 
 * @author Wesley Jr /* configuração do Servlet o Vaadin Servlet.
 * 
 */

public class SpringVaadinServlet extends VaadinServlet {

	private WebApplicationContext applicationContext;

	public void init(ServletConfig servletConfig) throws ServletException {
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
		super.init(servletConfig);
	}

	@Override
	protected VaadinServletService createServletService(
			DeploymentConfiguration deploymentConfiguration) {

		VaadinServletService service = null;
		try {
			service = super.createServletService(deploymentConfiguration);
			service.addSessionInitListener(new SessionInitListener() {
				@Override
				public void sessionInit(SessionInitEvent event)
						throws ServiceException {
					System.out.println("session init");
					event.getSession().addUIProvider(new DefaultUIProvider() {
						@Override
						public UI createInstance(UICreateEvent event) {
							return applicationContext.getBean(event
									.getUIClass());
						}
					});
				}
			});
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SystemMessagesProvider systemMessagesProvider = new SystemMessagesProvider() {

			CustomizedSystemMessages customizedSystemMessages;

			@Override
			public SystemMessages getSystemMessages(
					SystemMessagesInfo systemMessagesInfo) {

				if (customizedSystemMessages == null) {
					customizedSystemMessages = new CustomizedSystemMessages();
					String loginPage = systemMessagesInfo.getService()
							.getCurrentRequest().getContextPath()
							+ "/login/";
					customizedSystemMessages.setSessionExpiredURL(loginPage);
					customizedSystemMessages
							.setSessionExpiredNotificationEnabled(false);
				}

				return customizedSystemMessages;
			}
		};

		service.setSystemMessagesProvider(systemMessagesProvider);

		/*
		 * service.addSessionDestroyListener(new SessionDestroyListener() {
		 * 
		 * @Override public void sessionDestroy(SessionDestroyEvent event) { //
		 * TODO Auto-generated method stub
		 * 
		 * } });
		 */
		return service;
	}

}
