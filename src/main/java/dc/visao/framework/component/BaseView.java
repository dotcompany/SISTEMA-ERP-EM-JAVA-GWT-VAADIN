package dc.visao.framework.component;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseView<C extends BaseController<?>> extends CustomComponent implements View {

	private static final long serialVersionUID = -5826701409728630329L;
	
	protected final C controller;
	
	private HorizontalLayout headerLayout;
	
	private VerticalLayout mainLayout;

	private Label lblTitle;
	
	private PopupButton btnRelatorios;

	private HorizontalLayout relatoriosLayout;
	
	protected abstract void buildContentLayout();
	
	public BaseView(C controller) {
		this.controller = controller;
		
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		
		buildHeaderLayout();
		buildContentLayout();
		setCompositionRoot(mainLayout);
		setSizeFull();
	}
	
	private void buildHeaderLayout() {
		HorizontalLayout topLayout = new HorizontalLayout();
		topLayout.setMargin(false);
		topLayout.setSpacing(false);
		
		headerLayout = new HorizontalLayout();
		headerLayout.setImmediate(false);
		headerLayout.setWidth("-1px");
		headerLayout.setHeight("-1px");
		headerLayout.setMargin(true);
		headerLayout.setSpacing(true);

		lblTitle = new Label();
		lblTitle.setStyleName("h2");
		lblTitle.setImmediate(false);
		lblTitle.setWidth("-1px");
		lblTitle.setHeight("-1px");

		headerLayout.addComponent(lblTitle);
		headerLayout.setExpandRatio(lblTitle, 5.0f);
		headerLayout.setComponentAlignment(lblTitle, Alignment.MIDDLE_LEFT);
		
		topLayout.addComponent(headerLayout);
		
		// btnRelatorios
		btnRelatorios = new PopupButton();
		btnRelatorios.setCaption("Relatórios");
		relatoriosLayout = new HorizontalLayout();
		btnRelatorios.setContent(relatoriosLayout);
		btnRelatorios.setImmediate(true);
		btnRelatorios.setWidth("-1px");
		btnRelatorios.setHeight("-1px");
		btnRelatorios.setVisible(false);
		topLayout.addComponent(btnRelatorios);
		topLayout.setComponentAlignment(btnRelatorios, Alignment.MIDDLE_RIGHT);
		
		mainLayout.addComponent(topLayout);
	}

	protected void addHeaderComponent(AbstractComponent component) {
		component.setImmediate(true);
		component.setWidth("-1px");
		component.setHeight("-1px");
		headerLayout.addComponent(component);
		headerLayout.setComponentAlignment(component, Alignment.MIDDLE_LEFT);
	}
	
	@SuppressWarnings("serial")
	protected void addReport(final ReportController reportController) {
		Button btnRelatorio = new Button(reportController.getControllerTitle());
		btnRelatorio.setStyleName("link");
		btnRelatorio.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				controller.showReport(reportController);
			}
		});
		relatoriosLayout.addComponent(btnRelatorio);
		btnRelatorios.setVisible(true);
	}
	
	public void setTitle(String title) {
		lblTitle.setCaption(title);
	}

	protected void setContent(Component content) {
		mainLayout.addComponent(content);
		mainLayout.setExpandRatio(content, 4.0f);		
	}
	
}
