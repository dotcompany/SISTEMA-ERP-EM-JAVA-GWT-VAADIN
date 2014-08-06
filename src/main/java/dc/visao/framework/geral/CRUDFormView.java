package dc.visao.framework.geral;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/** @author Wesley Jr /* Essa classe ï¿½ a classe principal da Tela (DESIGN), onde
 *         nela temos o button, o GRID, onde vai listar algumas informaï¿½ï¿½es
 *         de outra Tela e tem tudo correto, uma nova Tela. */

public class CRUDFormView extends CustomComponent implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private CssLayout contentLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private Button btnCancelar;
	@AutoGenerated
	private Button btnSalvar;
	@AutoGenerated
	private Button btnNovo;
	@AutoGenerated
	private Label lblNome;
	private VerticalLayout popupButtonReportContent;
	private PopupButton pbReport;

	/** The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization. The constructor will not be
	 * automatically regenerated by the visual editor. */
	public CRUDFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.setHeight("100%");

	}

	public Label getLblNome() {
		return lblNome;
	}

	public void setLblNome(Label lblNome) {
		this.lblNome = lblNome;
	}

	public CssLayout getPnlContent() {
		return contentLayout;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public void setBtnSalvar(Button btnSalvar) {
		this.btnSalvar = btnSalvar;
	}

	public Button getBtnNovo() {
		// TODO Auto-generated method stub
		return btnNovo;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}

	public Button getBtnCancelar() {
		// TODO Auto-generated method stub
		return btnCancelar;
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout(1, 2);
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.addStyleName("crud-form-grid");

		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2);

		// contentLayout
		contentLayout = new CssLayout();
		contentLayout.setImmediate(false);
		contentLayout.addStyleName("crud-form-content");
		contentLayout.setWidth("100.0%");
		contentLayout.setWidth("100.0%");
		// contentLayout.setHeight("-1px");

		mainLayout.addComponent(contentLayout);
		mainLayout.setRowExpandRatio(1, 8.0f);
		mainLayout.setRowExpandRatio(2, 0.0f);

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);

		// lblNome
		lblNome = new Label();
		lblNome.setStyleName("h2");
		lblNome.setImmediate(false);
		lblNome.setWidth("-1px");
		lblNome.setHeight("-1px");
		lblNome.setValue("Label");
		horizontalLayout_2.addComponent(lblNome);
		horizontalLayout_2.setComponentAlignment(lblNome, new Alignment(33));

		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		horizontalLayout_2.addComponent(horizontalLayout_3);
		horizontalLayout_2.setComponentAlignment(horizontalLayout_3, new Alignment(34));

		return horizontalLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("-1px");
		horizontalLayout_3.setHeight("-1px");
		horizontalLayout_3.setMargin(false);
		horizontalLayout_3.setSpacing(true);

		popupButtonReportContent = buildPopupButtonReportLayout();

		pbReport = new PopupButton("Relatórios");
		pbReport.setContent(popupButtonReportContent);

		horizontalLayout_3.addComponent(pbReport);

		// btnNovo
		btnNovo = new Button();
		btnNovo.setCaption("Novo");
		btnNovo.setImmediate(true);
		btnNovo.setWidth("-1px");
		btnNovo.setHeight("-1px");
		horizontalLayout_3.addComponent(btnNovo);
		horizontalLayout_3.setComponentAlignment(btnNovo, new Alignment(34));

		// btnSalvar
		btnSalvar = new Button();
		btnSalvar.setCaption("Salvar");
		btnSalvar.setImmediate(true);
		btnSalvar.setWidth("-1px");
		btnSalvar.setHeight("-1px");
		horizontalLayout_3.addComponent(btnSalvar);
		horizontalLayout_3.setComponentAlignment(btnSalvar, new Alignment(34));

		// btnCancelar
		btnCancelar = new Button();
		btnCancelar.setCaption("Voltar");
		btnCancelar.setImmediate(true);
		btnCancelar.setWidth("-1px");
		btnCancelar.setHeight("-1px");
		horizontalLayout_3.addComponent(btnCancelar);
		horizontalLayout_3.setComponentAlignment(btnCancelar, new Alignment(34));

		return horizontalLayout_3;
	}

	private VerticalLayout buildPopupButtonReportLayout() {
		popupButtonReportContent = new VerticalLayout();
		popupButtonReportContent.setImmediate(false);
		popupButtonReportContent.setWidth("-1px");
		popupButtonReportContent.setHeight("-1px");
		popupButtonReportContent.setMargin(true);
		popupButtonReportContent.setSpacing(true);
		return popupButtonReportContent;
	}

	public VerticalLayout getPopupButtonReportContent() {
		return popupButtonReportContent;
	}

	public void setPopupButtonReportContent(VerticalLayout popupButtonReportContent) {
		this.popupButtonReportContent = popupButtonReportContent;
	}

	public PopupButton getPbReport() {
		return pbReport;
	}

	public void setPbReport(PopupButton pbReport) {
		this.pbReport = pbReport;
	}

}
