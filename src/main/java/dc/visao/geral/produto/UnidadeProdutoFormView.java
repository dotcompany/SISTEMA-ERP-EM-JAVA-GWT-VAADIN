package dc.visao.geral.produto;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import dc.controller.geral.produto.UnidadeProdutoFormController;

public class UnidadeProdutoFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	@AutoGenerated
	private TextField tfDescricao;

	@AutoGenerated
	private ComboBox cbPodeFracionar;

	@AutoGenerated
	private TextField tfSigla;

	private UnidadeProdutoFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public UnidadeProdutoFormView(UnidadeProdutoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
	}

	public TextField getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(TextField tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public ComboBox getCbPodeFracionar() {
		return cbPodeFracionar;
	}

	public void setCbPodeFracionar(ComboBox cbPodeFracionar) {
		this.cbPodeFracionar = cbPodeFracionar;
	}

	public TextField getTfSigla() {
		return tfSigla;
	}

	public void setTfSigla(TextField tfSigla) {
		this.tfSigla = tfSigla;
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setRows(2);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 0);

		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2, 0, 1);

		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);

		// tfSigla
		tfSigla = new TextField();
		tfSigla.setCaption("Sigla");
		tfSigla.setImmediate(false);
		tfSigla.setWidth("-1px");
		tfSigla.setHeight("-1px");
		horizontalLayout_1.addComponent(tfSigla);

		// cbPodeFracionar
		cbPodeFracionar = new ComboBox();
		cbPodeFracionar.setCaption("Pode Fracionar?");
		cbPodeFracionar.setImmediate(false);
		cbPodeFracionar.setWidth("193px");
		cbPodeFracionar.setHeight("-1px");
		horizontalLayout_1.addComponent(cbPodeFracionar);

		return horizontalLayout_1;
	}

	/*
	 * public void InitCbs() {
	 * 
	 * cmbPodeFracionar.addItem(PodeFracionarType.SIM.toString());
	 * cmbPodeFracionar.addItem(PodeFracionarType.NAO.toString());
	 * 
	 * }
	 * 
	 * public String getCbPodeFracionar() { String cbvalue = new String();
	 * if(cmbPodeFracionar.getValue() == "Sim") { cbvalue = "S"; } else
	 * if(cmbPodeFracionar.getValue() == "Não") { cbvalue = "N"; } return
	 * cbvalue; }
	 * 
	 * public void setCbPodeFracionar(String cbPodeFracionar) {
	 * if(cbPodeFracionar.equalsIgnoreCase("S")) {
	 * this.cmbPodeFracionar.setValue("Sim"); } else
	 * if(cbPodeFracionar.equalsIgnoreCase("N")) {
	 * this.cmbPodeFracionar.setValue("Não"); } }
	 */

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);

		// tfDescricao
		tfDescricao = new TextField();
		tfDescricao.setCaption("Descrição");
		tfDescricao.setImmediate(false);
		tfDescricao.setWidth("340px");
		tfDescricao.setHeight("-1px");
		horizontalLayout_2.addComponent(tfDescricao);

		return horizontalLayout_2;
	}

}