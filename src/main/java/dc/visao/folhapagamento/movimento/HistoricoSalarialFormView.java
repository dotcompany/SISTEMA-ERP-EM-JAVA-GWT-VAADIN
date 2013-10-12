package dc.visao.folhapagamento.movimento;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.folhapagamento.movimento.HistoricoSalarialFormController;
import dc.entidade.pessoal.Colaborador;

public class HistoricoSalarialFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private TextField tfCompetencia;

	@AutoGenerated
	private TextField tfSalarioAtual;

	@AutoGenerated
	private TextField tfPercentualAumento;

	@AutoGenerated
	private TextField tfSalarioNovo;

	@AutoGenerated
	private TextField tfValidoAPartir;

	@AutoGenerated
	private TextField tfMotivo;

	@AutoGenerated
	private ComboBox cbColaborador;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private HistoricoSalarialFormController controller;

	public HistoricoSalarialFormView(
			final HistoricoSalarialFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfCompetencia() {
		return tfCompetencia;
	}

	public void setTfCompetencia(TextField tfCompetencia) {
		this.tfCompetencia = tfCompetencia;
	}

	public TextField getTfSalarioAtual() {
		return tfSalarioAtual;
	}

	public void setTfSalarioAtual(TextField tfSalarioAtual) {
		this.tfSalarioAtual = tfSalarioAtual;
	}

	public TextField getTfPercentualAumento() {
		return tfPercentualAumento;
	}

	public void setTfPercentualAumento(TextField tfPercentualAumento) {
		this.tfPercentualAumento = tfPercentualAumento;
	}

	public TextField getTfSalarioNovo() {
		return tfSalarioNovo;
	}

	public void setTfSalarioNovo(TextField tfSalarioNovo) {
		this.tfSalarioNovo = tfSalarioNovo;
	}

	public TextField getTfValidoAPartir() {
		return tfValidoAPartir;
	}

	public void setTfValidoAPartir(TextField tfValidoAPartir) {
		this.tfValidoAPartir = tfValidoAPartir;
	}

	public TextField getTfMotivo() {
		return tfMotivo;
	}

	public void setTfMotivo(TextField tfMotivo) {
		this.tfMotivo = tfMotivo;
	}

	public ComboBox getCbColaborador() {
		return cbColaborador;
	}

	public void setCbColaborador(ComboBox cbColaborador) {
		this.cbColaborador = cbColaborador;
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

		// tfCompetencia
		tfCompetencia = new TextField();
		tfCompetencia.setCaption("Número:");
		tfCompetencia.setImmediate(false);
		tfCompetencia.setWidth("175px");
		tfCompetencia.setHeight("-1px");
		// tfCompetencia.setRequired(true);
		tfCompetencia.setSizeFull();
		tfCompetencia.setNullRepresentation("");
		gridLayout_1.addComponent(tfCompetencia, 0, 1);

		// tfSalarioAtual
		tfSalarioAtual = new TextField();
		tfSalarioAtual.setCaption("Valor do prêmio:");
		tfSalarioAtual.setImmediate(false);
		tfSalarioAtual.setWidth("175px");
		tfSalarioAtual.setHeight("-1px");
		tfSalarioAtual.setSizeFull();
		tfSalarioAtual.setNullRepresentation("");
		gridLayout_1.addComponent(tfSalarioAtual, 1, 1);

		// tfPercentualAumento
		tfPercentualAumento = new TextField();
		tfPercentualAumento.setCaption("Valor segurado:");
		tfPercentualAumento.setImmediate(false);
		tfPercentualAumento.setWidth("175px");
		tfPercentualAumento.setHeight("-1px");
		tfPercentualAumento.setSizeFull();
		tfPercentualAumento.setNullRepresentation("");
		gridLayout_1.addComponent(tfPercentualAumento, 0, 2);

		// tfSalarioNovo
		tfSalarioNovo = new TextField();
		tfSalarioNovo.setCaption("Observação:");
		tfSalarioNovo.setImmediate(false);
		tfSalarioNovo.setWidth("175px");
		tfSalarioNovo.setHeight("-1px");
		tfSalarioNovo.setSizeFull();
		tfSalarioNovo.setNullRepresentation("");
		gridLayout_1.addComponent(tfSalarioNovo, 1, 2);

		// tfValidoAPartir
		tfValidoAPartir = new TextField();
		tfValidoAPartir.setCaption("Imagem:");
		tfValidoAPartir.setImmediate(false);
		tfValidoAPartir.setWidth("-1px");
		tfValidoAPartir.setHeight("-1px");
		tfValidoAPartir.setSizeFull();
		tfValidoAPartir.setNullRepresentation("");
		gridLayout_1.addComponent(tfValidoAPartir, 0, 3);

		// tfMotivo
		tfMotivo = new TextField();
		tfMotivo.setCaption("Motivo:");
		tfMotivo.setImmediate(false);
		tfMotivo.setWidth("-1px");
		tfMotivo.setHeight("-1px");
		tfMotivo.setSizeFull();
		tfMotivo.setNullRepresentation("");
		gridLayout_1.addComponent(tfMotivo, 1, 3);

		// cbColaborador
		cbColaborador = new ComboBox();
		cbColaborador.setCaption("Colaborador:");
		cbColaborador.setImmediate(false);
		cbColaborador.setWidth("160px");
		cbColaborador.setHeight("-1px");
		cbColaborador.setRequired(true);
		gridLayout_1.addComponent(cbColaborador, 0, 4);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbColaborador(List<Colaborador> lista) {
		BeanItemContainer<Colaborador> bic = new BeanItemContainer<Colaborador>(
				Colaborador.class, lista);
		this.cbColaborador.setContainerDataSource(bic);
		this.cbColaborador.setItemCaptionPropertyId("nome");
	}

}