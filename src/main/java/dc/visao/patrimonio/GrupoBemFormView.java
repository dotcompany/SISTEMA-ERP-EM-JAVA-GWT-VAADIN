package dc.visao.patrimonio;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.patrimonio.GrupoBemFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class GrupoBemFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField tfCodigo;

	@AutoGenerated
	private TextField tfNome;

	@AutoGenerated
	private TextField tfContaAtivoImobilizado;

	@AutoGenerated
	private TextField tfContaDepreciacaoAcumulada;

	@AutoGenerated
	private TextField tfContaDespesaDepreciacao;

	@AutoGenerated
	private TextField tfCodigoHistorico;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private GrupoBemFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public GrupoBemFormView(final GrupoBemFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfCodigo() {
		return tfCodigo;
	}

	public void setTfCodigo(TextField tfCodigo) {
		this.tfCodigo = tfCodigo;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfContaAtivoImobilizado() {
		return tfContaAtivoImobilizado;
	}

	public void setTfContaAtivoImobilizado(TextField tfContaAtivoImobilizado) {
		this.tfContaAtivoImobilizado = tfContaAtivoImobilizado;
	}

	public TextField getTfContaDepreciacaoAcumulada() {
		return tfContaDepreciacaoAcumulada;
	}

	public void setTfContaDepreciacaoAcumulada(
			TextField tfContaDepreciacaoAcumulada) {
		this.tfContaDepreciacaoAcumulada = tfContaDepreciacaoAcumulada;
	}

	public TextField getTfContaDespesaDepreciacao() {
		return tfContaDespesaDepreciacao;
	}

	public void setTfContaDespesaDepreciacao(TextField tfContaDespesaDepreciacao) {
		this.tfContaDespesaDepreciacao = tfContaDespesaDepreciacao;
	}

	public TextField getTfCodigoHistorico() {
		return tfCodigoHistorico;
	}

	public void setTfCodigoHistorico(TextField tfCodigoHistorico) {
		this.tfCodigoHistorico = tfCodigoHistorico;
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

		// tfCodigoHistorico
		tfCodigoHistorico = new TextField();
		tfCodigoHistorico.setCaption("Código do histórico:");
		tfCodigoHistorico.setImmediate(false);
		tfCodigoHistorico.setWidth("160px");
		tfCodigoHistorico.setHeight("-1px");
		// tfCodigoHistorico.setRequired(true);
		tfCodigoHistorico.setSizeFull();
		tfCodigoHistorico.setNullRepresentation("");
		gridLayout_1.addComponent(tfCodigoHistorico, 0, 1);

		// tfContaDespesaDepreciacao
		tfContaDespesaDepreciacao = new TextField();
		tfContaDespesaDepreciacao
				.setCaption("Conta da despesa da depreciação:");
		tfContaDespesaDepreciacao.setImmediate(false);
		tfContaDespesaDepreciacao.setWidth("160px");
		tfContaDespesaDepreciacao.setHeight("-1px");
		// tfContaDespesaDepreciacao.setRequired(true);
		tfContaDespesaDepreciacao.setSizeFull();
		tfContaDespesaDepreciacao.setNullRepresentation("");
		gridLayout_1.addComponent(tfContaDespesaDepreciacao, 1, 1);

		// tfContaDepreciacaoAcumulada
		tfContaDepreciacaoAcumulada = new TextField();
		tfContaDepreciacaoAcumulada
				.setCaption("Conta da depreciação acumulada:");
		tfContaDepreciacaoAcumulada.setImmediate(false);
		tfContaDepreciacaoAcumulada.setWidth("160px");
		tfContaDepreciacaoAcumulada.setHeight("-1px");
		// tfContaDepreciacaoAcumulada.setRequired(true);
		tfContaDepreciacaoAcumulada.setSizeFull();
		tfContaDepreciacaoAcumulada.setNullRepresentation("");
		gridLayout_1.addComponent(tfContaDepreciacaoAcumulada, 0, 2);

		// tfContaAtivoImobilizado
		tfContaAtivoImobilizado = new TextField();
		tfContaAtivoImobilizado.setCaption("Conta ativo imobilizado:");
		tfContaAtivoImobilizado.setImmediate(false);
		tfContaAtivoImobilizado.setWidth("160px");
		tfContaAtivoImobilizado.setHeight("-1px");
		// tfContaAtivoImobilizado.setRequired(true);
		tfContaAtivoImobilizado.setSizeFull();
		tfContaAtivoImobilizado.setNullRepresentation("");
		gridLayout_1.addComponent(tfContaAtivoImobilizado, 1, 2);

		// tfNome
		tfNome = new TextField();
		tfNome.setCaption("Nome:");
		tfNome.setImmediate(false);
		tfNome.setWidth("160px");
		tfNome.setHeight("-1px");
		// tfNome.setRequired(true);
		tfNome.setSizeFull();
		tfNome.setNullRepresentation("");
		gridLayout_1.addComponent(tfNome, 0, 3);

		// tfCodigo
		tfCodigo = new TextField();
		tfCodigo.setCaption("Código:");
		tfCodigo.setImmediate(false);
		tfCodigo.setWidth("160px");
		tfCodigo.setHeight("-1px");
		// tfCodigo.setRequired(true);
		tfCodigo.setSizeFull();
		tfCodigo.setNullRepresentation("");
		gridLayout_1.addComponent(tfCodigo, 1, 3);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

}