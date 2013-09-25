package dc.visao.folhapagamento.movimento;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.folhapagamento.movimento.PppFatorRiscoFormController;
import dc.entidade.folhapagamento.movimento.PppEntity;

public class PppFatorRiscoFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private PopupDateField pdfDataInicio;

	@AutoGenerated
	private PopupDateField pdfDataTermino;

	@AutoGenerated
	private TextField tfTipo;

	@AutoGenerated
	private TextField tfFatorRisco;

	@AutoGenerated
	private TextField tfIntensidade;

	@AutoGenerated
	private TextField tfTecnicaUtilizada;

	@AutoGenerated
	private TextField tfEpcEficaz;

	@AutoGenerated
	private TextField tfEpiEficaz;

	@AutoGenerated
	private TextField tfCaEpi;

	@AutoGenerated
	private TextField tfAtendimentoNr061;

	@AutoGenerated
	private TextField tfAtendimentoNr062;

	@AutoGenerated
	private TextField tfAtendimentoNr063;

	@AutoGenerated
	private TextField tfAtendimentoNr064;

	@AutoGenerated
	private TextField tfAtendimentoNr065;

	@AutoGenerated
	private ComboBox cbPpp;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private PppFatorRiscoFormController controller;

	public PppFatorRiscoFormView(final PppFatorRiscoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public PopupDateField getPdfDataInicio() {
		return pdfDataInicio;
	}

	public void setPdfDataInicio(PopupDateField pdfDataInicio) {
		this.pdfDataInicio = pdfDataInicio;
	}

	public PopupDateField getPdfDataTermino() {
		return pdfDataTermino;
	}

	public void setPdfDataTermino(PopupDateField pdfDataTermino) {
		this.pdfDataTermino = pdfDataTermino;
	}

	public TextField getTfTipo() {
		return tfTipo;
	}

	public void setTfTipo(TextField tfTipo) {
		this.tfTipo = tfTipo;
	}

	public TextField getTfFatorRisco() {
		return tfFatorRisco;
	}

	public void setTfFatorRisco(TextField tfFatorRisco) {
		this.tfFatorRisco = tfFatorRisco;
	}

	public TextField getTfIntensidade() {
		return tfIntensidade;
	}

	public void setTfIntensidade(TextField tfIntensidade) {
		this.tfIntensidade = tfIntensidade;
	}

	public TextField getTfTecnicaUtilizada() {
		return tfTecnicaUtilizada;
	}

	public void setTfTecnicaUtilizada(TextField tfTecnicaUtilizada) {
		this.tfTecnicaUtilizada = tfTecnicaUtilizada;
	}

	public TextField getTfEpcEficaz() {
		return tfEpcEficaz;
	}

	public void setTfEpcEficaz(TextField tfEpcEficaz) {
		this.tfEpcEficaz = tfEpcEficaz;
	}

	public TextField getTfEpiEficaz() {
		return tfEpiEficaz;
	}

	public void setTfEpiEficaz(TextField tfEpiEficaz) {
		this.tfEpiEficaz = tfEpiEficaz;
	}

	public TextField getTfCaEpi() {
		return tfCaEpi;
	}

	public void setTfCaEpi(TextField tfCaEpi) {
		this.tfCaEpi = tfCaEpi;
	}

	public TextField getTfAtendimentoNr061() {
		return tfAtendimentoNr061;
	}

	public void setTfAtendimentoNr061(TextField tfAtendimentoNr061) {
		this.tfAtendimentoNr061 = tfAtendimentoNr061;
	}

	public TextField getTfAtendimentoNr062() {
		return tfAtendimentoNr062;
	}

	public void setTfAtendimentoNr062(TextField tfAtendimentoNr062) {
		this.tfAtendimentoNr062 = tfAtendimentoNr062;
	}

	public TextField getTfAtendimentoNr063() {
		return tfAtendimentoNr063;
	}

	public void setTfAtendimentoNr063(TextField tfAtendimentoNr063) {
		this.tfAtendimentoNr063 = tfAtendimentoNr063;
	}

	public TextField getTfAtendimentoNr064() {
		return tfAtendimentoNr064;
	}

	public void setTfAtendimentoNr064(TextField tfAtendimentoNr064) {
		this.tfAtendimentoNr064 = tfAtendimentoNr064;
	}

	public TextField getTfAtendimentoNr065() {
		return tfAtendimentoNr065;
	}

	public void setTfAtendimentoNr065(TextField tfAtendimentoNr065) {
		this.tfAtendimentoNr065 = tfAtendimentoNr065;
	}

	public ComboBox getCbPpp() {
		return cbPpp;
	}

	public void setCbPpp(ComboBox cbPpp) {
		this.cbPpp = cbPpp;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// panel_1
		panel_1 = buildPanel_1();
		mainLayout.addComponent(panel_1);

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
		panel_1.setHeight("100.0%");

		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		panel_1.setContent(verticalLayout_2);

		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("100.0%");
		verticalLayout_2.setMargin(false);

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		verticalLayout_2.addComponent(gridLayout_1);

		return verticalLayout_2;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		// gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);
		gridLayout_1.setRows(30);
		gridLayout_1.setColumns(3);

		// pdfDataInicio
		pdfDataInicio = new PopupDateField();
		pdfDataInicio.setCaption("Data de início:");
		pdfDataInicio.setImmediate(false);
		pdfDataInicio.setWidth("-1px");
		pdfDataInicio.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataInicio, 0, 1);

		// pdfDataTermino
		pdfDataTermino = new PopupDateField();
		pdfDataTermino.setCaption("Data do término:");
		pdfDataTermino.setImmediate(false);
		pdfDataTermino.setWidth("-1px");
		pdfDataTermino.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 1);

		// tfTipo
		tfTipo = new TextField();
		tfTipo.setCaption("Tipo:");
		tfTipo.setImmediate(false);
		tfTipo.setWidth("175px");
		tfTipo.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 0, 2);

		// tfFatorRisco
		tfFatorRisco = new TextField();
		tfFatorRisco.setCaption("Fator de risco:");
		tfFatorRisco.setImmediate(false);
		tfFatorRisco.setWidth("175px");
		tfFatorRisco.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 2);

		// tfIntensidade
		tfIntensidade = new TextField();
		tfIntensidade.setCaption("Intensidade:");
		tfIntensidade.setImmediate(false);
		tfIntensidade.setWidth("175px");
		tfIntensidade.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 0, 3);

		// tfTecnicaUtilizada
		tfTecnicaUtilizada = new TextField();
		tfTecnicaUtilizada.setCaption("Técnica utilizada:");
		tfTecnicaUtilizada.setImmediate(false);
		tfTecnicaUtilizada.setWidth("175px");
		tfTecnicaUtilizada.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 3);

		// tfEpcEficaz
		tfEpcEficaz = new TextField();
		tfEpcEficaz.setCaption("Epc eficaz:");
		tfEpcEficaz.setImmediate(false);
		tfEpcEficaz.setWidth("175px");
		tfEpcEficaz.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 0, 4);

		// tfEpiEficaz
		tfEpiEficaz = new TextField();
		tfEpiEficaz.setCaption("Epi eficaz:");
		tfEpiEficaz.setImmediate(false);
		tfEpiEficaz.setWidth("175px");
		tfEpiEficaz.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 4);

		// tfCaEpi
		tfCaEpi = new TextField();
		tfCaEpi.setCaption("Ca epi:");
		tfCaEpi.setImmediate(false);
		tfCaEpi.setWidth("175px");
		tfCaEpi.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 0, 5);

		// tfAtendimentoNr061
		tfAtendimentoNr061 = new TextField();
		tfAtendimentoNr061.setCaption("Atendimento nr 06.1:");
		tfAtendimentoNr061.setImmediate(false);
		tfAtendimentoNr061.setWidth("175px");
		tfAtendimentoNr061.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 5);

		// tfAtendimentoNr062
		tfAtendimentoNr062 = new TextField();
		tfAtendimentoNr062.setCaption("Atendimento nr 06.2:");
		tfAtendimentoNr062.setImmediate(false);
		tfAtendimentoNr062.setWidth("175px");
		tfAtendimentoNr062.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 0, 6);

		// tfAtendimentoNr063
		tfAtendimentoNr063 = new TextField();
		tfAtendimentoNr063.setCaption("Atendimento nr 06.3:");
		tfAtendimentoNr063.setImmediate(false);
		tfAtendimentoNr063.setWidth("175px");
		tfAtendimentoNr063.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 6);

		// tfAtendimentoNr064
		tfAtendimentoNr064 = new TextField();
		tfAtendimentoNr064.setCaption("Atendimento nr 06.4:");
		tfAtendimentoNr064.setImmediate(false);
		tfAtendimentoNr064.setWidth("175px");
		tfAtendimentoNr064.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 0, 7);

		// tfAtendimentoNr065
		tfAtendimentoNr065 = new TextField();
		tfAtendimentoNr065.setCaption("Atendimento nr 06.5:");
		tfAtendimentoNr065.setImmediate(false);
		tfAtendimentoNr065.setWidth("175px");
		tfAtendimentoNr065.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataTermino, 1, 7);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbPpp(List<PppEntity> lista) {
		BeanItemContainer<PppEntity> bic = new BeanItemContainer<PppEntity>(
				PppEntity.class, lista);
		this.cbPpp.setContainerDataSource(bic);
		this.cbPpp.setItemCaptionPropertyId("observacao");
	}

}