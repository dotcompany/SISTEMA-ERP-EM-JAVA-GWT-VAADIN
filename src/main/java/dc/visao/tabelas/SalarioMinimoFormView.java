package dc.visao.tabelas;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class SalarioMinimoFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private GridLayout fields;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private PopupDateField dtDou;
	@AutoGenerated
	private TextField txtNormaLegal;
	@AutoGenerated
	private TextField txtValorHora;
	@AutoGenerated
	private TextField txtValorDiario;
	@AutoGenerated
	private TextField txtValorMensal;
	@AutoGenerated
	private PopupDateField dataVigencia;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public SalarioMinimoFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {

		// common part: create layout
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		// setHeight("100.0%");

		fields = buildFields();

		mainLayout.addComponent(fields);
	
		return mainLayout;
	}

	
	private GridLayout buildFields() {
		fields = new GridLayout();
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);
		fields.setRows(4);
		fields.setColumns(3);
		
		// dataVigencia
		dataVigencia = new PopupDateField();
		dataVigencia.setCaption("Vigência");
		dataVigencia.setImmediate(false);
		dataVigencia.setWidth("156px");
		dataVigencia.setHeight("-1px");
		dataVigencia.setSizeFull();
		fields.addComponent(dataVigencia, 0, 0);
		
		// txtValorMensal
		txtValorMensal = new TextField();
		txtValorMensal.setCaption("Valor Mensal");
		txtValorMensal.setImmediate(false);
		txtValorMensal.setWidth("154px");
		txtValorMensal.setHeight("-1px");
		txtValorMensal.setSizeFull();
		fields.addComponent(txtValorMensal, 1, 0);
		
		// txtValorDiario
		txtValorDiario = new TextField();
		txtValorDiario.setCaption("Valor Diário");
		txtValorDiario.setImmediate(false);
		txtValorDiario.setWidth("174px");
		txtValorDiario.setHeight("-1px");
		txtValorDiario.setSizeFull();
		fields.addComponent(txtValorDiario, 2, 0);
		
		// txtValorHora
		txtValorHora = new TextField();
		txtValorHora.setCaption("Valor Hora");
		txtValorHora.setImmediate(false);
		txtValorHora.setWidth("156px");
		txtValorHora.setHeight("-1px");
		txtValorHora.setSizeFull();
		fields.addComponent(txtValorHora, 0, 1);
		
		// txtNormaLegal
		txtNormaLegal = new TextField();
		txtNormaLegal.setCaption("Norma Legal");
		txtNormaLegal.setImmediate(false);
		txtNormaLegal.setWidth("334px");
		txtNormaLegal.setHeight("-1px");
		txtNormaLegal.setSizeFull();
		fields.addComponent(txtNormaLegal, 1, 1);
		
		// dtDou
		dtDou = new PopupDateField();
		dtDou.setCaption("Dou");
		dtDou.setImmediate(false);
		dtDou.setWidth("156px");
		dtDou.setHeight("-1px");
		dtDou.setSizeFull();
		fields.addComponent(dtDou, 2, 1);
		
		return fields;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public PopupDateField getDtDou() {
		return dtDou;
	}

	public void setDtDou(PopupDateField dtDou) {
		this.dtDou = dtDou;
	}
	public TextField getTxtNormaLegal() {
		return txtNormaLegal;
	}

	public void setTxtNormaLegal(TextField txtNormaLegal) {
		this.txtNormaLegal = txtNormaLegal;
	}

	public TextField getTxtValorHora() {
		return txtValorHora;
	}

	public void setTxtValorHora(TextField txtValorHora) {
		this.txtValorHora = txtValorHora;
	}

	public TextField getTxtValorDiario() {
		return txtValorDiario;
	}

	public void setTxtValorDiario(TextField txtValorDiario) {
		this.txtValorDiario = txtValorDiario;
	}

	public TextField getTxtValorMensal() {
		return txtValorMensal;
	}

	public void setTxtValorMensal(TextField txtValorMensal) {
		this.txtValorMensal = txtValorMensal;
	}

	public PopupDateField getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(PopupDateField dataVigencia) {
		this.dataVigencia = dataVigencia;
	}
	
}
