package dc.visao.contabilidade.cadastro;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.contabilidade.cadastro.IndiceValorFormController;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class IndiceValorFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private PopupDateField pdfDataIndice;

	@AutoGenerated
	private TextField tfValor;

	@AutoGenerated
	private ManyToOneCombo<IndiceEntity> cbIndice;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private IndiceValorFormController controller;

	public IndiceValorFormView(final IndiceValorFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public PopupDateField getPdfDataIndice() {
		return pdfDataIndice;
	}

	public void setPdfDataIndice(PopupDateField pdfDataIndice) {
		this.pdfDataIndice = pdfDataIndice;
	}

	public TextField getTfValor() {
		return tfValor;
	}

	public void setTfValor(TextField tfValor) {
		this.tfValor = tfValor;
	}

	public ManyToOneCombo<IndiceEntity> getCbIndice() {
		return cbIndice;
	}

	public void setCbIndice(ManyToOneCombo<IndiceEntity> cbIndice) {
		this.cbIndice = cbIndice;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

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
		gridLayout_1.setSpacing(true);
		gridLayout_1.setRows(20);
		gridLayout_1.setColumns(2);

		// pdfDataIndice
		pdfDataIndice = new PopupDateField();
		pdfDataIndice.setCaption("Data do índice:");
		pdfDataIndice.setImmediate(false);
		pdfDataIndice.setWidth("175px");
		pdfDataIndice.setHeight("-1px");
		pdfDataIndice.setSizeFull();
		gridLayout_1.addComponent(pdfDataIndice, 0, 1);

		// tfValor
		tfValor = new TextField();
		tfValor.setCaption("Valor:");
		tfValor.setImmediate(false);
		tfValor.setWidth("175px");
		tfValor.setHeight("-1px");
		tfValor.setSizeFull();
		tfValor.setNullRepresentation("");
		gridLayout_1.addComponent(tfValor, 1, 1);

		// cbIndice
		cbIndice = new ManyToOneCombo<>();
		cbIndice.setCaption("Índice:");
		cbIndice.setImmediate(false);
		cbIndice.setWidth("175px");
		cbIndice.setHeight("-1px");
		cbIndice.setSizeFull();
		gridLayout_1.addComponent(cbIndice, 0, 2);

		return gridLayout_1;
	}

}