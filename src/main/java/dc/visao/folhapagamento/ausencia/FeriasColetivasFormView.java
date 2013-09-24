package dc.visao.folhapagamento.ausencia;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.folhapagamento.ausencia.FeriasColetivasFormController;

public class FeriasColetivasFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private PopupDateField pdfDataPagamento;

	@AutoGenerated
	private TextField tfDiasAbono;

	@AutoGenerated
	private PopupDateField pdfAbonoPecuniarioFim;

	@AutoGenerated
	private PopupDateField pdfAbonoPecuniarioInicio;

	@AutoGenerated
	private TextField tfDiasGozo;

	@AutoGenerated
	private PopupDateField pdfDataFim;

	@AutoGenerated
	private PopupDateField pdfDataInicio;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private FeriasColetivasFormController controller;

	public FeriasColetivasFormView(
			final FeriasColetivasFormController controller) {
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

	public PopupDateField getPdfDataFim() {
		return pdfDataFim;
	}

	public void setPdfDataFim(PopupDateField pdfDataFim) {
		this.pdfDataFim = pdfDataFim;
	}

	public TextField getTfDiasGozo() {
		return tfDiasGozo;
	}

	public void setTfDiasGozo(TextField tfDiasGozo) {
		this.tfDiasGozo = tfDiasGozo;
	}

	public PopupDateField getPdfAbonoPecuniarioInicio() {
		return pdfAbonoPecuniarioInicio;
	}

	public void setPdfAbonoPecuniarioInicio(
			PopupDateField pdfAbonoPecuniarioInicio) {
		this.pdfAbonoPecuniarioInicio = pdfAbonoPecuniarioInicio;
	}

	public PopupDateField getPdfAbonoPecuniarioFim() {
		return pdfAbonoPecuniarioFim;
	}

	public void setPdfAbonoPecuniarioFim(PopupDateField pdfAbonoPecuniarioFim) {
		this.pdfAbonoPecuniarioFim = pdfAbonoPecuniarioFim;
	}

	public TextField getTfDiasAbono() {
		return tfDiasAbono;
	}

	public void setTfDiasAbono(TextField tfDiasAbono) {
		this.tfDiasAbono = tfDiasAbono;
	}

	public PopupDateField getPdfDataPagamento() {
		return pdfDataPagamento;
	}

	public void setPdfDataPagamento(PopupDateField pdfDataPagamento) {
		this.pdfDataPagamento = pdfDataPagamento;
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
		gridLayout_1.setRows(20);
		gridLayout_1.setColumns(3);

		// pdfDataInicio
		pdfDataInicio = new PopupDateField();
		pdfDataInicio.setCaption("Data de inicio:");
		pdfDataInicio.setImmediate(false);
		pdfDataInicio.setWidth("-1px");
		pdfDataInicio.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataInicio, 0, 1);

		// pdfDataFim
		pdfDataFim = new PopupDateField();
		pdfDataFim.setCaption("Data de termino:");
		pdfDataFim.setImmediate(false);
		pdfDataFim.setWidth("-1px");
		pdfDataFim.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataFim, 1, 1);

		// tfDiasGozo
		tfDiasGozo = new TextField();
		tfDiasGozo.setCaption("Dias de gozo:");
		tfDiasGozo.setImmediate(false);
		tfDiasGozo.setWidth("175px");
		tfDiasGozo.setHeight("-1px");
		gridLayout_1.addComponent(tfDiasGozo, 0, 2);

		// pdfAbonoPecuniarioInicio
		pdfAbonoPecuniarioInicio = new PopupDateField();
		pdfAbonoPecuniarioInicio.setCaption("Abono pecuniario inicio:");
		pdfAbonoPecuniarioInicio.setImmediate(false);
		pdfAbonoPecuniarioInicio.setWidth("-1px");
		pdfAbonoPecuniarioInicio.setHeight("-1px");
		gridLayout_1.addComponent(pdfAbonoPecuniarioInicio, 1, 2);

		// pdfAbonoPecuniarioFim
		pdfAbonoPecuniarioFim = new PopupDateField();
		pdfAbonoPecuniarioFim.setCaption("Abono pecuniario termino:");
		pdfAbonoPecuniarioFim.setImmediate(false);
		pdfAbonoPecuniarioFim.setWidth("-1px");
		pdfAbonoPecuniarioFim.setHeight("-1px");
		gridLayout_1.addComponent(pdfAbonoPecuniarioFim, 0, 3);

		// tfDiasAbono
		tfDiasAbono = new TextField();
		tfDiasAbono.setCaption("Dias de abono:");
		tfDiasAbono.setImmediate(false);
		tfDiasAbono.setWidth("175px");
		tfDiasAbono.setHeight("-1px");
		gridLayout_1.addComponent(tfDiasAbono, 1, 3);

		// pdfDataPagamento
		pdfDataPagamento = new PopupDateField();
		pdfDataPagamento.setCaption("Data de pagamento:");
		pdfDataPagamento.setImmediate(false);
		pdfDataPagamento.setWidth("-1px");
		pdfDataPagamento.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataPagamento, 0, 4);

		return gridLayout_1;
	}

}