package dc.visao.nfe;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class DadoNotaFiscalFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private Panel panel_3;

	@AutoGenerated
	private VerticalLayout verticalLayout_4;

	@AutoGenerated
	private TabSheet tabSheet_1;

	@AutoGenerated
	private GridLayout gridLayout_3;

	@AutoGenerated
	private TextField textField_13;

	@AutoGenerated
	private GridLayout gridLayout_2;

	@AutoGenerated
	private TextField textField_12;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField textField_11;

	@AutoGenerated
	private GridLayout gridLayout_21;

	@AutoGenerated
	private TextField textField_10;

	@AutoGenerated
	private GridLayout gridLayout_19;

	@AutoGenerated
	private TextField textField_8;

	@AutoGenerated
	private GridLayout gridLayout_20;

	@AutoGenerated
	private TextField textField_9;

	@AutoGenerated
	private GridLayout gridLayout_18;

	@AutoGenerated
	private TextField textField_7;

	@AutoGenerated
	private GridLayout gridLayout_17;

	@AutoGenerated
	private TextField textField_6;

	@AutoGenerated
	private GridLayout gridLayout_16;

	@AutoGenerated
	private TextField textField_5;

	@AutoGenerated
	private GridLayout gridLayout_15;

	@AutoGenerated
	private TextField textField_4;

	@AutoGenerated
	private GridLayout gridLayout_14;

	@AutoGenerated
	private TextField textField_1;

	@AutoGenerated
	private Panel panel_2;

	@AutoGenerated
	private VerticalLayout verticalLayout_3;

	@AutoGenerated
	private TabSheet tabSheet_2;

	@AutoGenerated
	private VerticalLayout verticalLayout_5;

	@AutoGenerated
	private Panel panel_5;

	@AutoGenerated
	private VerticalLayout verticalLayout_7;

	@AutoGenerated
	private GridLayout gridLayout_5;

	@AutoGenerated
	private TextField tfTcl;

	@AutoGenerated
	private Panel panel_4;

	@AutoGenerated
	private VerticalLayout verticalLayout_6;

	@AutoGenerated
	private GridLayout gridLayout_4;

	@AutoGenerated
	private TextField tfOperacaoFiscal;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	// private DepreciacaoBemFormController controller;

	public DadoNotaFiscalFormView() {
		// this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfTcl() {
		return tfTcl;
	}

	public void setTfTcl(TextField tfTcl) {
		this.tfTcl = tfTcl;
	}

	public TextField getTfOperacaoFiscal() {
		return tfOperacaoFiscal;
	}

	public void setTfOperacaoFiscal(TextField tfOperacaoFiscal) {
		this.tfOperacaoFiscal = tfOperacaoFiscal;
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

		// panel_2
		panel_2 = buildPanel_2();
		verticalLayout_2.addComponent(panel_2);

		// panel_3
		panel_3 = buildPanel_3();
		verticalLayout_2.addComponent(panel_3);

		return verticalLayout_2;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setImmediate(false);
		panel_2.setWidth("100.0%");
		panel_2.setHeight("100.0%");

		// verticalLayout_3
		verticalLayout_3 = buildVerticalLayout_3();
		panel_2.setContent(verticalLayout_3);

		return panel_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_3() {
		// common part: create layout
		verticalLayout_3 = new VerticalLayout();
		verticalLayout_3.setImmediate(false);
		verticalLayout_3.setWidth("100.0%");
		verticalLayout_3.setHeight("100.0%");
		verticalLayout_3.setMargin(false);

		// tabSheet_2
		tabSheet_2 = buildTabSheet_2();
		verticalLayout_3.addComponent(tabSheet_2);

		return verticalLayout_3;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_2() {
		// common part: create layout
		tabSheet_2 = new TabSheet();
		tabSheet_2.setImmediate(true);
		tabSheet_2.setWidth("100.0%");
		tabSheet_2.setHeight("100.0%");

		// verticalLayout_5
		verticalLayout_5 = buildVerticalLayout_5();
		tabSheet_2.addTab(verticalLayout_5, "Dados NF-e", null);

		return tabSheet_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_5() {
		// common part: create layout
		verticalLayout_5 = new VerticalLayout();
		verticalLayout_5.setImmediate(false);
		verticalLayout_5.setWidth("100.0%");
		verticalLayout_5.setHeight("100.0%");
		verticalLayout_5.setMargin(false);

		// panel_4
		panel_4 = buildPanel_4();
		verticalLayout_5.addComponent(panel_4);

		// panel_5
		panel_5 = buildPanel_5();
		verticalLayout_5.addComponent(panel_5);

		return verticalLayout_5;
	}

	@AutoGenerated
	private Panel buildPanel_4() {
		// common part: create layout
		panel_4 = new Panel();
		panel_4.setImmediate(false);
		panel_4.setWidth("100.0%");
		panel_4.setHeight("100.0%");

		// verticalLayout_6
		verticalLayout_6 = buildVerticalLayout_6();
		panel_4.setContent(verticalLayout_6);

		return panel_4;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_6() {
		// common part: create layout
		verticalLayout_6 = new VerticalLayout();
		verticalLayout_6.setImmediate(false);
		verticalLayout_6.setWidth("100.0%");
		verticalLayout_6.setHeight("100.0%");
		verticalLayout_6.setMargin(false);

		// gridLayout_4
		gridLayout_4 = buildGridLayout_4();
		verticalLayout_6.addComponent(gridLayout_4);

		return verticalLayout_6;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_4() {
		// common part: create layout
		gridLayout_4 = new GridLayout();
		gridLayout_4.setImmediate(false);
		gridLayout_4.setWidth("100.0%");
		gridLayout_4.setHeight("100.0%");
		gridLayout_4.setMargin(false);

		// tfOperacaoFiscal
		tfOperacaoFiscal = new TextField();
		tfOperacaoFiscal.setCaption("Operação fiscal");
		tfOperacaoFiscal.setImmediate(false);
		tfOperacaoFiscal.setWidth("-1px");
		tfOperacaoFiscal.setHeight("-1px");
		gridLayout_4.addComponent(tfOperacaoFiscal, 0, 0);

		return gridLayout_4;
	}

	@AutoGenerated
	private Panel buildPanel_5() {
		// common part: create layout
		panel_5 = new Panel();
		panel_5.setImmediate(false);
		panel_5.setWidth("100.0%");
		panel_5.setHeight("100.0%");

		// verticalLayout_7
		verticalLayout_7 = buildVerticalLayout_7();
		panel_5.setContent(verticalLayout_7);

		return panel_5;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_7() {
		// common part: create layout
		verticalLayout_7 = new VerticalLayout();
		verticalLayout_7.setImmediate(false);
		verticalLayout_7.setWidth("100.0%");
		verticalLayout_7.setHeight("100.0%");
		verticalLayout_7.setMargin(false);

		// gridLayout_5
		gridLayout_5 = buildGridLayout_5();
		verticalLayout_7.addComponent(gridLayout_5);

		return verticalLayout_7;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_5() {
		// common part: create layout
		gridLayout_5 = new GridLayout();
		gridLayout_5.setImmediate(false);
		gridLayout_5.setWidth("100.0%");
		gridLayout_5.setHeight("100.0%");
		gridLayout_5.setMargin(false);

		// tfTcl
		tfTcl = new TextField();
		tfTcl.setCaption("Tcl");
		tfTcl.setImmediate(false);
		tfTcl.setWidth("-1px");
		tfTcl.setHeight("-1px");
		gridLayout_5.addComponent(tfTcl, 0, 0);

		return gridLayout_5;
	}

	@AutoGenerated
	private Panel buildPanel_3() {
		// common part: create layout
		panel_3 = new Panel();
		panel_3.setImmediate(false);
		panel_3.setWidth("100.0%");
		panel_3.setHeight("100.0%");

		// verticalLayout_4
		verticalLayout_4 = buildVerticalLayout_4();
		panel_3.setContent(verticalLayout_4);

		return panel_3;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_4() {
		// common part: create layout
		verticalLayout_4 = new VerticalLayout();
		verticalLayout_4.setImmediate(false);
		verticalLayout_4.setWidth("100.0%");
		verticalLayout_4.setHeight("100.0%");
		verticalLayout_4.setMargin(false);

		// tabSheet_1
		tabSheet_1 = buildTabSheet_1();
		verticalLayout_4.addComponent(tabSheet_1);

		return verticalLayout_4;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_1() {
		// common part: create layout
		tabSheet_1 = new TabSheet();
		tabSheet_1.setImmediate(true);
		tabSheet_1.setWidth("100.0%");
		tabSheet_1.setHeight("100.0%");

		// gridLayout_14
		gridLayout_14 = buildGridLayout_14();
		tabSheet_1.addTab(gridLayout_14, "ICMS", null);

		// gridLayout_15
		gridLayout_15 = buildGridLayout_15();
		tabSheet_1.addTab(gridLayout_15, "PIS", null);

		// gridLayout_16
		gridLayout_16 = buildGridLayout_16();
		tabSheet_1.addTab(gridLayout_16, "COFINS", null);

		// gridLayout_17
		gridLayout_17 = buildGridLayout_17();
		tabSheet_1.addTab(gridLayout_17, "IPI", null);

		// gridLayout_18
		gridLayout_18 = buildGridLayout_18();
		tabSheet_1.addTab(gridLayout_18, "Imposto importação", null);

		// gridLayout_20
		gridLayout_20 = buildGridLayout_20();
		tabSheet_1.addTab(gridLayout_20, "Combustível", null);

		// gridLayout_19
		gridLayout_19 = buildGridLayout_19();
		tabSheet_1.addTab(gridLayout_19, "ISSQN", null);

		// gridLayout_21
		gridLayout_21 = buildGridLayout_21();
		tabSheet_1.addTab(gridLayout_21, "Veículo", null);

		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		tabSheet_1.addTab(gridLayout_1, "Medicamentos", null);

		// gridLayout_2
		gridLayout_2 = buildGridLayout_2();
		tabSheet_1.addTab(gridLayout_2, "Armamento", null);

		// gridLayout_3
		gridLayout_3 = buildGridLayout_3();
		tabSheet_1.addTab(gridLayout_3, "Declaração importação", null);

		return tabSheet_1;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_14() {
		// common part: create layout
		gridLayout_14 = new GridLayout();
		gridLayout_14.setImmediate(false);
		gridLayout_14.setWidth("100.0%");
		gridLayout_14.setHeight("100.0%");
		gridLayout_14.setMargin(false);

		// textField_1
		textField_1 = new TextField();
		textField_1.setImmediate(false);
		textField_1.setWidth("-1px");
		textField_1.setHeight("-1px");
		gridLayout_14.addComponent(textField_1, 0, 0);

		return gridLayout_14;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_15() {
		// common part: create layout
		gridLayout_15 = new GridLayout();
		gridLayout_15.setImmediate(false);
		gridLayout_15.setWidth("100.0%");
		gridLayout_15.setHeight("100.0%");
		gridLayout_15.setMargin(false);

		// textField_4
		textField_4 = new TextField();
		textField_4.setImmediate(false);
		textField_4.setWidth("-1px");
		textField_4.setHeight("-1px");
		gridLayout_15.addComponent(textField_4, 0, 0);

		return gridLayout_15;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_16() {
		// common part: create layout
		gridLayout_16 = new GridLayout();
		gridLayout_16.setImmediate(false);
		gridLayout_16.setWidth("100.0%");
		gridLayout_16.setHeight("100.0%");
		gridLayout_16.setMargin(false);

		// textField_5
		textField_5 = new TextField();
		textField_5.setImmediate(false);
		textField_5.setWidth("-1px");
		textField_5.setHeight("-1px");
		gridLayout_16.addComponent(textField_5, 0, 0);

		return gridLayout_16;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_17() {
		// common part: create layout
		gridLayout_17 = new GridLayout();
		gridLayout_17.setImmediate(false);
		gridLayout_17.setWidth("100.0%");
		gridLayout_17.setHeight("100.0%");
		gridLayout_17.setMargin(false);

		// textField_6
		textField_6 = new TextField();
		textField_6.setImmediate(false);
		textField_6.setWidth("-1px");
		textField_6.setHeight("-1px");
		gridLayout_17.addComponent(textField_6, 0, 0);

		return gridLayout_17;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_18() {
		// common part: create layout
		gridLayout_18 = new GridLayout();
		gridLayout_18.setImmediate(false);
		gridLayout_18.setWidth("100.0%");
		gridLayout_18.setHeight("100.0%");
		gridLayout_18.setMargin(false);

		// textField_7
		textField_7 = new TextField();
		textField_7.setImmediate(false);
		textField_7.setWidth("-1px");
		textField_7.setHeight("-1px");
		gridLayout_18.addComponent(textField_7, 0, 0);

		return gridLayout_18;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_20() {
		// common part: create layout
		gridLayout_20 = new GridLayout();
		gridLayout_20.setImmediate(false);
		gridLayout_20.setWidth("100.0%");
		gridLayout_20.setHeight("100.0%");
		gridLayout_20.setMargin(false);

		// textField_9
		textField_9 = new TextField();
		textField_9.setImmediate(false);
		textField_9.setWidth("-1px");
		textField_9.setHeight("-1px");
		gridLayout_20.addComponent(textField_9, 0, 0);

		return gridLayout_20;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_19() {
		// common part: create layout
		gridLayout_19 = new GridLayout();
		gridLayout_19.setImmediate(false);
		gridLayout_19.setWidth("100.0%");
		gridLayout_19.setHeight("100.0%");
		gridLayout_19.setMargin(false);

		// textField_8
		textField_8 = new TextField();
		textField_8.setImmediate(false);
		textField_8.setWidth("-1px");
		textField_8.setHeight("-1px");
		gridLayout_19.addComponent(textField_8, 0, 0);

		return gridLayout_19;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_21() {
		// common part: create layout
		gridLayout_21 = new GridLayout();
		gridLayout_21.setImmediate(false);
		gridLayout_21.setWidth("100.0%");
		gridLayout_21.setHeight("100.0%");
		gridLayout_21.setMargin(false);

		// textField_10
		textField_10 = new TextField();
		textField_10.setImmediate(false);
		textField_10.setWidth("-1px");
		textField_10.setHeight("-1px");
		gridLayout_21.addComponent(textField_10, 0, 0);

		return gridLayout_21;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);

		// textField_11
		textField_11 = new TextField();
		textField_11.setImmediate(false);
		textField_11.setWidth("-1px");
		textField_11.setHeight("-1px");
		gridLayout_1.addComponent(textField_11, 0, 0);

		return gridLayout_1;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_2() {
		// common part: create layout
		gridLayout_2 = new GridLayout();
		gridLayout_2.setImmediate(false);
		gridLayout_2.setWidth("100.0%");
		gridLayout_2.setHeight("100.0%");
		gridLayout_2.setMargin(false);

		// textField_12
		textField_12 = new TextField();
		textField_12.setImmediate(false);
		textField_12.setWidth("-1px");
		textField_12.setHeight("-1px");
		gridLayout_2.addComponent(textField_12, 0, 0);

		return gridLayout_2;
	}

	@AutoGenerated
	private GridLayout buildGridLayout_3() {
		// common part: create layout
		gridLayout_3 = new GridLayout();
		gridLayout_3.setImmediate(false);
		gridLayout_3.setWidth("100.0%");
		gridLayout_3.setHeight("100.0%");
		gridLayout_3.setMargin(false);

		// textField_13
		textField_13 = new TextField();
		textField_13.setImmediate(false);
		textField_13.setWidth("-1px");
		textField_13.setHeight("-1px");
		gridLayout_3.addComponent(textField_13, 0, 0);

		return gridLayout_3;
	}

}