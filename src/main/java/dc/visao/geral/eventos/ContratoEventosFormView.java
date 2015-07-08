package dc.visao.geral.eventos;

import java.util.List;

import org.vaadin.addons.maskedtextfield.NumericField;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.eventos.ContratoEventosFormController;
import dc.entidade.geral.eventos.CerimonialEventosEntity;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class ContratoEventosFormView extends CustomComponent {

	
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TextField txtCurso;

	@AutoGenerated
	private TextField txtUnidade;
	
	@AutoGenerated
	private PopupDateField pdfDataContrato;
	
	@AutoGenerated
	private PopupDateField pdfDataPrimeiroEvento;
	
	@AutoGenerated
	private NumericField txtQuantidadeFormandos;
	
	@AutoGenerated
	private TextField txtAnoFormatura;
	
	@AutoGenerated
	private ComboBox cbTipoSemestre;
	
	@AutoGenerated
	private GridLayout glGeral;

	@AutoGenerated
	private TabSheet tsGeral;
	
	@AutoGenerated
	private ManyToOneCombo<CerimonialEventosEntity> mocNomeCerimonial;
	
	private ContratoEventosFormController controller;


	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ContratoEventosFormView(ContratoEventosFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	@AutoGenerated
	private void buildMainLayout() {
		// the main layout and components will be created here
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		glGeral = bglGeral();
		mainLayout.addComponent(glGeral);

		tsGeral = new TabSheet();
		tsGeral.setImmediate(true);
		tsGeral.setSizeFull();

		//tsGeral.addTab(bvlInformacaoGeral(), 0);

		mainLayout.addComponent(tsGeral);
		mainLayout.setExpandRatio(tsGeral, 1);
	}

	/**
	 * GERAL
	 */

	@AutoGenerated
	private GridLayout bglGeral() {
		// common part: create layout
		glGeral = new GridLayout(6, 6);
		glGeral.setImmediate(false);
		glGeral.setWidth("100.0%");
		glGeral.setHeight("-1px");
		glGeral.setMargin(false);
		glGeral.setSpacing(true);

		txtCurso = new TextField();
		txtCurso.setCaption("Curso");
		txtCurso.setRequired(true);
		txtCurso.setImmediate(true);
		txtCurso.setWidth("400px");
		txtCurso.setHeight("-1px");
		glGeral.addComponent(txtCurso, 0, 0);

		//
		txtUnidade = new TextField();
		txtUnidade.setCaption(" Unidade ");
		txtUnidade.setRequired(true);
		txtUnidade.setImmediate(true);
		txtUnidade.setWidth("400px");
		txtUnidade.setHeight("-1px");
		glGeral.addComponent(txtUnidade, 1, 0);
		
		txtAnoFormatura = new TextField();
		txtAnoFormatura.setCaption(" Ano de Formatura ");
		txtAnoFormatura.setRequired(true);
		txtAnoFormatura.setImmediate(true);
		txtAnoFormatura.setHeight("-1px");
		glGeral.addComponent(txtAnoFormatura, 1, 1);
		
		cbTipoSemestre = new ComboBox();
		cbTipoSemestre.setCaption("Semestre");
		cbTipoSemestre.setImmediate(false);
		cbTipoSemestre.setWidth("180px");
		cbTipoSemestre.setHeight("-1px");
		glGeral.addComponent(cbTipoSemestre, 2, 1);

		//
		mocNomeCerimonial = new ManyToOneCombo<>();
		mocNomeCerimonial.setCaption("Nome Cerimonial");
		mocNomeCerimonial.setImmediate(true);
		mocNomeCerimonial.setHeight("-1px");
		glGeral.addComponent(mocNomeCerimonial, 0, 1);
		
		// pdfDataContrato
		pdfDataContrato = new PopupDateField();
		pdfDataContrato.setCaption("Data do Contrato");
		pdfDataContrato.setImmediate(false);
		pdfDataContrato.setWidth("135px");
		pdfDataContrato.setHeight("-1px");
		//glGeral.addComponent(pdfDataContrato,"top:20.0px;left:25.0px;");
		glGeral.addComponent(pdfDataContrato, 0, 2);

		// pdfDataPrimeiroEvento
		pdfDataPrimeiroEvento = new PopupDateField();
		pdfDataPrimeiroEvento.setCaption("Data do Primeiro Evento");
		pdfDataPrimeiroEvento.setImmediate(false);
		pdfDataPrimeiroEvento.setWidth("160px");
		pdfDataPrimeiroEvento.setHeight("-1px");
		//glGeral.addComponent(pdfDataPrimeiroEvento,"top:20.0px;left:25.0px;");
		glGeral.addComponent(pdfDataPrimeiroEvento, 1, 2);
		
		/*txtQuantidadeFormandos = new TextField();
		txtQuantidadeFormandos.setCaption(" Quantidade de Formandos ");
		txtQuantidadeFormandos.setRequired(true);
		txtQuantidadeFormandos.setImmediate(true);
		txtQuantidadeFormandos.setHeight("-1px");*/
		
		txtQuantidadeFormandos = ComponentUtil.buildNumericField("Quantidade de Formandos");
		txtQuantidadeFormandos.setConverter(new IntegerConverter());
		txtQuantidadeFormandos.setWidth("200px");
		glGeral.addComponent(txtQuantidadeFormandos, 0, 3);

		return glGeral;
	}
	
	public void InitCbs(List<String> contratoEventosTipo) {

		for (String str : contratoEventosTipo) {
			cbTipoSemestre.addItem(str.toString());
		}
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextField getTxtCurso() {
		return txtCurso;
	}

	public void setTxtCurso(TextField txtCurso) {
		this.txtCurso = txtCurso;
	}

	public TextField getTxtUnidade() {
		return txtUnidade;
	}

	public void setTxtUnidade(TextField txtUnidade) {
		this.txtUnidade = txtUnidade;
	}

	public PopupDateField getPdfDataContrato() {
		return pdfDataContrato;
	}

	public void setPdfDataContrato(PopupDateField pdfDataContrato) {
		this.pdfDataContrato = pdfDataContrato;
	}

	public PopupDateField getPdfDataPrimeiroEvento() {
		return pdfDataPrimeiroEvento;
	}

	public void setPdfDataPrimeiroEvento(PopupDateField pdfDataPrimeiroEvento) {
		this.pdfDataPrimeiroEvento = pdfDataPrimeiroEvento;
	}

	public GridLayout getGlGeral() {
		return glGeral;
	}

	public void setGlGeral(GridLayout glGeral) {
		this.glGeral = glGeral;
	}

	public TabSheet getTsGeral() {
		return tsGeral;
	}

	public void setTsGeral(TabSheet tsGeral) {
		this.tsGeral = tsGeral;
	}

	public ManyToOneCombo<CerimonialEventosEntity> getMocNomeCerimonial() {
		return mocNomeCerimonial;
	}

	public void setMocNomeCerimonial(
			ManyToOneCombo<CerimonialEventosEntity> mocNomeCerimonial) {
		this.mocNomeCerimonial = mocNomeCerimonial;
	}

	public ContratoEventosFormController getController() {
		return controller;
	}

	public void setController(ContratoEventosFormController controller) {
		this.controller = controller;
	}

	public NumericField getTxtQuantidadeFormandos() {
		return txtQuantidadeFormandos;
	}

	public void setTxtQuantidadeFormandos(NumericField txtQuantidadeFormandos) {
		this.txtQuantidadeFormandos = txtQuantidadeFormandos;
	}

	public TextField getTxtAnoFormatura() {
		return txtAnoFormatura;
	}

	public void setTxtAnoFormatura(TextField txtAnoFormatura) {
		this.txtAnoFormatura = txtAnoFormatura;
	}

	public ComboBox getCbTipoSemestre() {
		return cbTipoSemestre;
	}

	public void setCbTipoSemestre(ComboBox cbTipoSemestre) {
		this.cbTipoSemestre = cbTipoSemestre;
	}
	
}