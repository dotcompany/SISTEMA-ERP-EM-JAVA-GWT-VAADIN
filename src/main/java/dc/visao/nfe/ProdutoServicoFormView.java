package dc.visao.nfe;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.nfe.ProdutoServicoFormController;
import dc.entidade.contabilidade.livrocontabil.LivroEntity;
import dc.visao.framework.component.SubFormComponent;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class ProdutoServicoFormView extends CustomComponent {

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

	/**
	 * DECLARAÇÃO IMPORTAÇÃO
	 */

	@AutoGenerated
	private GridLayout glDeclaracaoImportacao;

	/**
	 * ARMAMENTO
	 */

	@AutoGenerated
	private GridLayout glArmamento;

	/**
	 * MEDICAMENTO
	 */

	@AutoGenerated
	private GridLayout glMedicamento;

	/**
	 * VEÍCULO
	 */

	@AutoGenerated
	private GridLayout glVeiculo;

	@AutoGenerated
	private TextField textField_10;

	/**
	 * ISSQN
	 */

	@AutoGenerated
	private GridLayout glIssqn;

	@AutoGenerated
	private TextField tfBaseCalculoBcIssqn;

	@AutoGenerated
	private TextField tfAliquotaIssqn;

	@AutoGenerated
	private TextField tfValorIssqn;

	@AutoGenerated
	private TextField tfMunicipioIssqn;

	@AutoGenerated
	private TextField tfItemListaServicosIssqn;

	@AutoGenerated
	private TextField tfTributacaoIssqn;

	/**
	 * COMBUSTÍVEL
	 */

	@AutoGenerated
	private GridLayout glCombustivel;

	@AutoGenerated
	private TextField tfCodigoAnpCombustivel;

	@AutoGenerated
	private TextField tfCodifCombustivel;

	@AutoGenerated
	private TextField tfQtdeTempAmbienteCombustivel;

	@AutoGenerated
	private TextField tfUfConsumoCombustivel;

	@AutoGenerated
	private TextField tfBcCideCombustivel;

	@AutoGenerated
	private TextField tfAliquotaCideCombustivel;

	@AutoGenerated
	private TextField tfValorCideCombustivel;

	/**
	 * IMPOSTO IMPORTAÇÃO
	 */

	@AutoGenerated
	private GridLayout glImpostoImportacao;

	@AutoGenerated
	private TextField tfBaseCalculoBcImpostoImportacao;

	@AutoGenerated
	private TextField tfDespesasAduaneirasImpostoImportacao;

	@AutoGenerated
	private TextField tfValorImpostoImportacao;

	@AutoGenerated
	private TextField tfIofImpostoImportacao;

	/**
	 * IPI
	 */

	@AutoGenerated
	private GridLayout glIpi;

	@AutoGenerated
	private TextField tfCstIpi;

	@AutoGenerated
	private TextField tfBaseCalculoBcIpi;

	@AutoGenerated
	private TextField tfAliquotaIpi;

	@AutoGenerated
	private TextField tfQtdUndTributavelIpi;

	@AutoGenerated
	private TextField tfValorUndTributavelIpi;

	@AutoGenerated
	private TextField tfValorIpi;

	@AutoGenerated
	private TextField tfEnquadramentoIpi;

	@AutoGenerated
	private TextField tfEnquadramentoLegalIpi;

	@AutoGenerated
	private TextField tfCnpjProdutorIpi;

	@AutoGenerated
	private TextField tfQtdSeloIpi;

	@AutoGenerated
	private TextField tfCodigoSeloIpi;

	/**
	 * COFINS
	 */

	@AutoGenerated
	private GridLayout glCofins;

	@AutoGenerated
	private TextField tfCstCofins;

	@AutoGenerated
	private TextField tfQtdVendidaCofins;

	@AutoGenerated
	private TextField tfBaseCalculoBcCofins;

	@AutoGenerated
	private TextField tfAliquotaPercentualCofins;

	@AutoGenerated
	private TextField tfAliquotaReaisCofins;

	@AutoGenerated
	private TextField tfValorCofins;

	/**
	 * PIS
	 */

	@AutoGenerated
	private GridLayout glPis;

	@AutoGenerated
	private TextField tfCstPis;

	@AutoGenerated
	private TextField tfQtdVendidaPis;

	@AutoGenerated
	private TextField tfBaseCalculoBcPis;

	@AutoGenerated
	private TextField tfAliquotaPercentualPis;

	@AutoGenerated
	private TextField tfAliquotaReaisPis;

	@AutoGenerated
	private TextField tfValorPis;

	/**
	 * *****************
	 */

	@AutoGenerated
	private GridLayout glIcms;

	@AutoGenerated
	private TextField tfOrigemMercadoria;

	@AutoGenerated
	private TextField tfCst;

	@AutoGenerated
	private TextField tfCsosn;

	@AutoGenerated
	private TextField tfModalidadeBc;

	@AutoGenerated
	private TextField tfTaxaReducaoBc;

	@AutoGenerated
	private TextField tfBaseCalculoBc;

	@AutoGenerated
	private TextField tfAliquota;

	@AutoGenerated
	private TextField tfValor;

	@AutoGenerated
	private TextField tfMotivoDesoneracao;

	@AutoGenerated
	private Panel panel_2;

	@AutoGenerated
	private VerticalLayout verticalLayout_3;

	@AutoGenerated
	private TabSheet tabSheet_13;

	@AutoGenerated
	private GridLayout gridLayout_23;

	@AutoGenerated
	private TextField textField_3;

	@AutoGenerated
	private GridLayout gridLayout_22;

	@AutoGenerated
	private TextField textField_2;

	private SubFormComponent<LivroEntity, Integer> sfPrincipal;

	private SubFormComponent<LivroEntity, Integer> sfMedicamento;

	private SubFormComponent<LivroEntity, Integer> sfArmamento;

	private SubFormComponent<LivroEntity, Integer> sfDeclaracaoImportacao;

	private SubFormComponent<LivroEntity, Integer> sfDeclaracaoImportacaoAdicoes;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private ProdutoServicoFormController controller;

	public ProdutoServicoFormView(ProdutoServicoFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
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

		verticalLayout_3.addComponent(buildSubForm());

		return verticalLayout_3;
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
		glIcms = bglIcms();
		tabSheet_1.addTab(glIcms, "ICMS", null);

		// gridLayout_15
		glPis = bglPis();
		tabSheet_1.addTab(glPis, "PIS", null);

		// gridLayout_16
		glCofins = bglCofins();
		tabSheet_1.addTab(glCofins, "COFINS", null);

		// gridLayout_17
		glIpi = bglIpi();
		tabSheet_1.addTab(glIpi, "IPI", null);

		// gridLayout_18
		glImpostoImportacao = bglImpostoImportacao();
		tabSheet_1.addTab(glImpostoImportacao, "Imposto importação", null);

		// gridLayout_19
		glIssqn = bglIssqn();
		tabSheet_1.addTab(glIssqn, "ISSQN", null);

		// gridLayout_20
		glCombustivel = bglCombustivel();
		tabSheet_1.addTab(glCombustivel, "Combustível", null);

		// gridLayout_21
		glVeiculo = bglVeiculo();
		tabSheet_1.addTab(glVeiculo, "Veículo", null);

		// gridLayout_1
		glMedicamento = bglMedicamento();
		tabSheet_1.addTab(glMedicamento, "Medicamento", null);

		// gridLayout_2
		glArmamento = bglArmamento();
		tabSheet_1.addTab(glArmamento, "Armamento", null);

		// gridLayout_3
		glDeclaracaoImportacao = bglDeclaracaoImportacao();
		tabSheet_1
				.addTab(glDeclaracaoImportacao, "Declaração importação", null);

		return tabSheet_1;
	}

	@AutoGenerated
	private GridLayout bglIcms() {
		// common part: create layout
		glIcms = new GridLayout();
		glIcms.setImmediate(false);
		glIcms.setWidth("100.0%");
		glIcms.setHeight("100.0%");
		glIcms.setMargin(false);
		glIcms.setSpacing(true);
		glIcms.setRows(4);
		glIcms.setColumns(4);

		// tfOrigemMercadoria
		tfOrigemMercadoria = new TextField("Origem da mercadoria: ");
		tfOrigemMercadoria.setImmediate(false);
		tfOrigemMercadoria.setWidth("-1px");
		tfOrigemMercadoria.setHeight("-1px");
		tfOrigemMercadoria.setSizeFull();
		tfOrigemMercadoria.setNullRepresentation("");
		glIcms.addComponent(tfOrigemMercadoria, 0, 0);

		// tfCst
		tfCst = new TextField("CST: ");
		tfCst.setImmediate(false);
		tfCst.setWidth("-1px");
		tfCst.setHeight("-1px");
		tfCst.setSizeFull();
		tfCst.setNullRepresentation("");
		glIcms.addComponent(tfCst, 1, 0);

		// tfCsosn
		tfCsosn = new TextField("CSOSN: ");
		tfCsosn.setImmediate(false);
		tfCsosn.setWidth("-1px");
		tfCsosn.setHeight("-1px");
		tfCsosn.setSizeFull();
		tfCsosn.setNullRepresentation("");
		glIcms.addComponent(tfCsosn, 2, 0);

		// tfModalidadeBc
		tfModalidadeBc = new TextField("Modalidade (BC): ");
		tfModalidadeBc.setImmediate(false);
		tfModalidadeBc.setWidth("-1px");
		tfModalidadeBc.setHeight("-1px");
		tfModalidadeBc.setSizeFull();
		tfModalidadeBc.setNullRepresentation("");
		glIcms.addComponent(tfModalidadeBc, 3, 0);

		// tfTaxaReducaoBc
		tfTaxaReducaoBc = new TextField("Taxa de redução (BC): ");
		tfTaxaReducaoBc.setImmediate(false);
		tfTaxaReducaoBc.setWidth("-1px");
		tfTaxaReducaoBc.setHeight("-1px");
		tfTaxaReducaoBc.setSizeFull();
		tfTaxaReducaoBc.setNullRepresentation("");
		glIcms.addComponent(tfTaxaReducaoBc, 0, 1);

		// tfBaseCalculoBc
		tfBaseCalculoBc = new TextField("Base de cálculo (BC): ");
		tfBaseCalculoBc.setImmediate(false);
		tfBaseCalculoBc.setWidth("-1px");
		tfBaseCalculoBc.setHeight("-1px");
		tfBaseCalculoBc.setSizeFull();
		tfBaseCalculoBc.setNullRepresentation("");
		glIcms.addComponent(tfBaseCalculoBc, 1, 1);

		// tfAliquota
		tfAliquota = new TextField("Alíquota: ");
		tfAliquota.setImmediate(false);
		tfAliquota.setWidth("-1px");
		tfAliquota.setHeight("-1px");
		tfAliquota.setSizeFull();
		tfAliquota.setNullRepresentation("");
		glIcms.addComponent(tfAliquota, 2, 1);

		// tfValor
		tfValor = new TextField("Valor: ");
		tfValor.setImmediate(false);
		tfValor.setWidth("-1px");
		tfValor.setHeight("-1px");
		tfValor.setSizeFull();
		tfValor.setNullRepresentation("");
		glIcms.addComponent(tfValor, 3, 1);

		// tfMotivoDesoneracao
		tfMotivoDesoneracao = new TextField("Motivo desoneração: ");
		tfMotivoDesoneracao.setImmediate(false);
		tfMotivoDesoneracao.setWidth("-1px");
		tfMotivoDesoneracao.setHeight("-1px");
		tfMotivoDesoneracao.setSizeFull();
		tfMotivoDesoneracao.setNullRepresentation("");
		glIcms.addComponent(tfMotivoDesoneracao, 0, 2);

		return glIcms;
	}

	@AutoGenerated
	private GridLayout bglPis() {
		// common part: create layout
		glPis = new GridLayout();
		glPis.setImmediate(false);
		glPis.setWidth("100.0%");
		glPis.setHeight("100.0%");
		glPis.setMargin(false);
		glPis.setSpacing(true);
		glPis.setRows(4);
		glPis.setColumns(4);

		// tfCstPis
		tfCstPis = new TextField("CST: ");
		tfCstPis.setImmediate(false);
		tfCstPis.setWidth("-1px");
		tfCstPis.setHeight("-1px");
		tfCstPis.setSizeFull();
		tfCstPis.setNullRepresentation("");
		glPis.addComponent(tfCstPis, 0, 0);

		// tfQtdVendidaPis
		tfQtdVendidaPis = new TextField("Quantidade vendida: ");
		tfQtdVendidaPis.setImmediate(false);
		tfQtdVendidaPis.setWidth("-1px");
		tfQtdVendidaPis.setHeight("-1px");
		tfQtdVendidaPis.setSizeFull();
		tfQtdVendidaPis.setNullRepresentation("");
		glPis.addComponent(tfQtdVendidaPis, 1, 0);

		// tfBaseCalculoBcPis
		tfBaseCalculoBcPis = new TextField("Base de cáculo (BC): ");
		tfBaseCalculoBcPis.setImmediate(false);
		tfBaseCalculoBcPis.setWidth("-1px");
		tfBaseCalculoBcPis.setHeight("-1px");
		tfBaseCalculoBcPis.setSizeFull();
		tfBaseCalculoBcPis.setNullRepresentation("");
		glPis.addComponent(tfBaseCalculoBcPis, 2, 0);

		// tfAliquotaPercentualPis
		tfAliquotaPercentualPis = new TextField("Alíquota percentual: ");
		tfAliquotaPercentualPis.setImmediate(false);
		tfAliquotaPercentualPis.setWidth("-1px");
		tfAliquotaPercentualPis.setHeight("-1px");
		tfAliquotaPercentualPis.setSizeFull();
		tfAliquotaPercentualPis.setNullRepresentation("");
		glPis.addComponent(tfAliquotaPercentualPis, 3, 0);

		// tfAliquotaReaisPis
		tfAliquotaReaisPis = new TextField("Alíquota reais: ");
		tfAliquotaReaisPis.setImmediate(false);
		tfAliquotaReaisPis.setWidth("-1px");
		tfAliquotaReaisPis.setHeight("-1px");
		tfAliquotaReaisPis.setSizeFull();
		tfAliquotaReaisPis.setNullRepresentation("");
		glPis.addComponent(tfAliquotaReaisPis, 0, 1);

		// tfValorPis
		tfValorPis = new TextField("Valor: ");
		tfValorPis.setImmediate(false);
		tfValorPis.setWidth("-1px");
		tfValorPis.setHeight("-1px");
		tfValorPis.setSizeFull();
		tfValorPis.setNullRepresentation("");
		glPis.addComponent(tfValorPis, 1, 1);

		return glPis;
	}

	@AutoGenerated
	private GridLayout bglCofins() {
		// common part: create layout
		glCofins = new GridLayout();
		glCofins.setImmediate(false);
		glCofins.setWidth("100.0%");
		glCofins.setHeight("100.0%");
		glCofins.setMargin(false);
		glCofins.setSpacing(true);
		glCofins.setRows(4);
		glCofins.setColumns(4);

		// tfCstCofins
		tfCstCofins = new TextField("CST: ");
		tfCstCofins.setImmediate(false);
		tfCstCofins.setWidth("-1px");
		tfCstCofins.setHeight("-1px");
		tfCstCofins.setSizeFull();
		tfCstCofins.setNullRepresentation("");
		glCofins.addComponent(tfCstCofins, 0, 0);

		// tfQtdVendidaCofins
		tfQtdVendidaCofins = new TextField("Quantidade vendida: ");
		tfQtdVendidaCofins.setImmediate(false);
		tfQtdVendidaCofins.setWidth("-1px");
		tfQtdVendidaCofins.setHeight("-1px");
		tfQtdVendidaCofins.setSizeFull();
		tfQtdVendidaCofins.setNullRepresentation("");
		glCofins.addComponent(tfQtdVendidaCofins, 1, 0);

		// tfBaseCalculoBcCofins
		tfBaseCalculoBcCofins = new TextField("Base de cáculo (BC): ");
		tfBaseCalculoBcCofins.setImmediate(false);
		tfBaseCalculoBcCofins.setWidth("-1px");
		tfBaseCalculoBcCofins.setHeight("-1px");
		tfBaseCalculoBcCofins.setSizeFull();
		tfBaseCalculoBcCofins.setNullRepresentation("");
		glCofins.addComponent(tfBaseCalculoBcCofins, 2, 0);

		// tfAliquotaPercentualCofins
		tfAliquotaPercentualCofins = new TextField("Alíquota percentual: ");
		tfAliquotaPercentualCofins.setImmediate(false);
		tfAliquotaPercentualCofins.setWidth("-1px");
		tfAliquotaPercentualCofins.setHeight("-1px");
		tfAliquotaPercentualCofins.setSizeFull();
		tfAliquotaPercentualCofins.setNullRepresentation("");
		glCofins.addComponent(tfAliquotaPercentualCofins, 3, 0);

		// tfAliquotaReaisCofins
		tfAliquotaReaisCofins = new TextField("Alíquota reais: ");
		tfAliquotaReaisCofins.setImmediate(false);
		tfAliquotaReaisCofins.setWidth("-1px");
		tfAliquotaReaisCofins.setHeight("-1px");
		tfAliquotaReaisCofins.setSizeFull();
		tfAliquotaReaisCofins.setNullRepresentation("");
		glCofins.addComponent(tfAliquotaReaisCofins, 0, 1);

		// tfValorCofins
		tfValorCofins = new TextField("Valor: ");
		tfValorCofins.setImmediate(false);
		tfValorCofins.setWidth("-1px");
		tfValorCofins.setHeight("-1px");
		tfValorCofins.setSizeFull();
		tfValorCofins.setNullRepresentation("");
		glCofins.addComponent(tfValorCofins, 1, 1);

		return glCofins;
	}

	@AutoGenerated
	private GridLayout bglIpi() {
		// common part: create layout
		glIpi = new GridLayout();
		glIpi.setImmediate(false);
		glIpi.setWidth("100.0%");
		glIpi.setHeight("100.0%");
		glIpi.setMargin(false);
		glIpi.setSpacing(true);
		glIpi.setRows(4);
		glIpi.setColumns(4);

		// tfCstIpi
		tfCstIpi = new TextField("CST: ");
		tfCstIpi.setImmediate(false);
		tfCstIpi.setWidth("-1px");
		tfCstIpi.setHeight("-1px");
		tfCstIpi.setSizeFull();
		tfCstIpi.setNullRepresentation("");
		glIpi.addComponent(tfCstIpi, 0, 0);

		// tfBaseCalculoBcIpi
		tfBaseCalculoBcIpi = new TextField("Base de cálculo (BC): ");
		tfBaseCalculoBcIpi.setImmediate(false);
		tfBaseCalculoBcIpi.setWidth("-1px");
		tfBaseCalculoBcIpi.setHeight("-1px");
		tfBaseCalculoBcIpi.setSizeFull();
		tfBaseCalculoBcIpi.setNullRepresentation("");
		glIpi.addComponent(tfBaseCalculoBcIpi, 1, 0);

		// tfAliquotaIpi
		tfAliquotaIpi = new TextField("Alíquota: ");
		tfAliquotaIpi.setImmediate(false);
		tfAliquotaIpi.setWidth("-1px");
		tfAliquotaIpi.setHeight("-1px");
		tfAliquotaIpi.setSizeFull();
		tfAliquotaIpi.setNullRepresentation("");
		glIpi.addComponent(tfAliquotaIpi, 2, 0);

		// tfQtdUndTributavelIpi
		tfQtdUndTributavelIpi = new TextField("Quantidade und tributável: ");
		tfQtdUndTributavelIpi.setImmediate(false);
		tfQtdUndTributavelIpi.setWidth("-1px");
		tfQtdUndTributavelIpi.setHeight("-1px");
		tfQtdUndTributavelIpi.setSizeFull();
		tfQtdUndTributavelIpi.setNullRepresentation("");
		glIpi.addComponent(tfQtdUndTributavelIpi, 3, 0);

		// tfValorUndTributavelIpi
		tfValorUndTributavelIpi = new TextField("Valor und tributável: ");
		tfValorUndTributavelIpi.setImmediate(false);
		tfValorUndTributavelIpi.setWidth("-1px");
		tfValorUndTributavelIpi.setHeight("-1px");
		tfValorUndTributavelIpi.setSizeFull();
		tfValorUndTributavelIpi.setNullRepresentation("");
		glIpi.addComponent(tfValorUndTributavelIpi, 0, 1);

		// tfValorIpi
		tfValorIpi = new TextField("Valor: ");
		tfValorIpi.setImmediate(false);
		tfValorIpi.setWidth("-1px");
		tfValorIpi.setHeight("-1px");
		tfValorIpi.setSizeFull();
		tfValorIpi.setNullRepresentation("");
		glIpi.addComponent(tfValorIpi, 1, 1);

		// tfEnquadramentoIpi
		tfEnquadramentoIpi = new TextField("Enquadramento: ");
		tfEnquadramentoIpi.setImmediate(false);
		tfEnquadramentoIpi.setWidth("-1px");
		tfEnquadramentoIpi.setHeight("-1px");
		tfEnquadramentoIpi.setSizeFull();
		tfEnquadramentoIpi.setNullRepresentation("");
		glIpi.addComponent(tfEnquadramentoIpi, 2, 1);

		// tfEnquadramentoLegalIpi
		tfEnquadramentoLegalIpi = new TextField("Enquadramento legal: ");
		tfEnquadramentoLegalIpi.setImmediate(false);
		tfEnquadramentoLegalIpi.setWidth("-1px");
		tfEnquadramentoLegalIpi.setHeight("-1px");
		tfEnquadramentoLegalIpi.setSizeFull();
		tfEnquadramentoLegalIpi.setNullRepresentation("");
		glIpi.addComponent(tfEnquadramentoLegalIpi, 3, 1);

		// tfCnpjProdutorIpi
		tfCnpjProdutorIpi = new TextField("CNPJ do produtor: ");
		tfCnpjProdutorIpi.setImmediate(false);
		tfCnpjProdutorIpi.setWidth("-1px");
		tfCnpjProdutorIpi.setHeight("-1px");
		tfCnpjProdutorIpi.setSizeFull();
		tfCnpjProdutorIpi.setNullRepresentation("");
		glIpi.addComponent(tfCnpjProdutorIpi, 0, 2);

		// tfQtdSeloIpi
		tfQtdSeloIpi = new TextField("Quantidade de selo: ");
		tfQtdSeloIpi.setImmediate(false);
		tfQtdSeloIpi.setWidth("-1px");
		tfQtdSeloIpi.setHeight("-1px");
		tfQtdSeloIpi.setSizeFull();
		tfQtdSeloIpi.setNullRepresentation("");
		glIpi.addComponent(tfQtdSeloIpi, 1, 2);

		// tfCodigoSeloIpi
		tfCodigoSeloIpi = new TextField("Código do selo: ");
		tfCodigoSeloIpi.setImmediate(false);
		tfCodigoSeloIpi.setWidth("-1px");
		tfCodigoSeloIpi.setHeight("-1px");
		tfCodigoSeloIpi.setSizeFull();
		tfCodigoSeloIpi.setNullRepresentation("");
		glIpi.addComponent(tfCodigoSeloIpi, 2, 2);

		return glIpi;
	}

	@AutoGenerated
	private GridLayout bglImpostoImportacao() {
		// common part: create layout
		glImpostoImportacao = new GridLayout();
		glImpostoImportacao.setImmediate(false);
		glImpostoImportacao.setWidth("100.0%");
		glImpostoImportacao.setHeight("100.0%");
		glImpostoImportacao.setMargin(false);
		glImpostoImportacao.setSpacing(true);
		glImpostoImportacao.setRows(4);
		glImpostoImportacao.setColumns(4);

		// tfBaseCalculoBcImpostoImportacao
		tfBaseCalculoBcImpostoImportacao = new TextField(
				"Base de cálculo (BC): ");
		tfBaseCalculoBcImpostoImportacao.setImmediate(false);
		tfBaseCalculoBcImpostoImportacao.setWidth("-1px");
		tfBaseCalculoBcImpostoImportacao.setHeight("-1px");
		tfBaseCalculoBcImpostoImportacao.setSizeFull();
		tfBaseCalculoBcImpostoImportacao.setNullRepresentation("");
		glImpostoImportacao
				.addComponent(tfBaseCalculoBcImpostoImportacao, 0, 0);

		// tfDespesasAduaneirasImpostoImportacao
		tfDespesasAduaneirasImpostoImportacao = new TextField(
				"Despesas aduaneiras: ");
		tfDespesasAduaneirasImpostoImportacao.setImmediate(false);
		tfDespesasAduaneirasImpostoImportacao.setWidth("-1px");
		tfDespesasAduaneirasImpostoImportacao.setHeight("-1px");
		tfDespesasAduaneirasImpostoImportacao.setSizeFull();
		tfDespesasAduaneirasImpostoImportacao.setNullRepresentation("");
		glImpostoImportacao.addComponent(tfDespesasAduaneirasImpostoImportacao,
				1, 0);

		// tfValorImpostoImportacao
		tfValorImpostoImportacao = new TextField("Valor: ");
		tfValorImpostoImportacao.setImmediate(false);
		tfValorImpostoImportacao.setWidth("-1px");
		tfValorImpostoImportacao.setHeight("-1px");
		tfValorImpostoImportacao.setSizeFull();
		tfValorImpostoImportacao.setNullRepresentation("");
		glImpostoImportacao.addComponent(tfValorImpostoImportacao, 2, 0);

		// tfIofImpostoImportacao
		tfIofImpostoImportacao = new TextField("IOF: ");
		tfIofImpostoImportacao.setImmediate(false);
		tfIofImpostoImportacao.setWidth("-1px");
		tfIofImpostoImportacao.setHeight("-1px");
		tfIofImpostoImportacao.setSizeFull();
		tfIofImpostoImportacao.setNullRepresentation("");
		glImpostoImportacao.addComponent(tfIofImpostoImportacao, 3, 0);

		return glImpostoImportacao;
	}

	@AutoGenerated
	private GridLayout bglIssqn() {
		// common part: create layout
		glIssqn = new GridLayout();
		glIssqn.setImmediate(false);
		glIssqn.setWidth("100.0%");
		glIssqn.setHeight("100.0%");
		glIssqn.setMargin(false);
		glIssqn.setSpacing(true);
		glIssqn.setRows(4);
		glIssqn.setColumns(4);

		// tfBaseCalculoBcIssqn
		tfBaseCalculoBcIssqn = new TextField("Base de cálculo (BC): ");
		tfBaseCalculoBcIssqn.setImmediate(false);
		tfBaseCalculoBcIssqn.setWidth("-1px");
		tfBaseCalculoBcIssqn.setHeight("-1px");
		tfBaseCalculoBcIssqn.setSizeFull();
		tfBaseCalculoBcIssqn.setNullRepresentation("");
		glIssqn.addComponent(tfBaseCalculoBcIssqn, 0, 0);

		// tfAliquotaIssqn
		tfAliquotaIssqn = new TextField("Alíquota: ");
		tfAliquotaIssqn.setImmediate(false);
		tfAliquotaIssqn.setWidth("-1px");
		tfAliquotaIssqn.setHeight("-1px");
		tfAliquotaIssqn.setSizeFull();
		tfAliquotaIssqn.setNullRepresentation("");
		glIssqn.addComponent(tfAliquotaIssqn, 1, 0);

		// tfValorIssqn
		tfValorIssqn = new TextField("Valor: ");
		tfValorIssqn.setImmediate(false);
		tfValorIssqn.setWidth("-1px");
		tfValorIssqn.setHeight("-1px");
		tfValorIssqn.setSizeFull();
		tfValorIssqn.setNullRepresentation("");
		glIssqn.addComponent(tfValorIssqn, 2, 0);

		// tfMunicipioIssqn
		tfMunicipioIssqn = new TextField("Município: ");
		tfMunicipioIssqn.setImmediate(false);
		tfMunicipioIssqn.setWidth("-1px");
		tfMunicipioIssqn.setHeight("-1px");
		tfMunicipioIssqn.setSizeFull();
		tfMunicipioIssqn.setNullRepresentation("");
		glIssqn.addComponent(tfMunicipioIssqn, 3, 0);

		// tfItemListaServicosIssqn
		tfItemListaServicosIssqn = new TextField("Item da lista de serviços: ");
		tfItemListaServicosIssqn.setImmediate(false);
		tfItemListaServicosIssqn.setWidth("-1px");
		tfItemListaServicosIssqn.setHeight("-1px");
		tfItemListaServicosIssqn.setSizeFull();
		tfItemListaServicosIssqn.setNullRepresentation("");
		glIssqn.addComponent(tfItemListaServicosIssqn, 0, 1);

		// tfTributacaoIssqn
		tfTributacaoIssqn = new TextField("Tributação: ");
		tfTributacaoIssqn.setImmediate(false);
		tfTributacaoIssqn.setWidth("-1px");
		tfTributacaoIssqn.setHeight("-1px");
		tfTributacaoIssqn.setSizeFull();
		tfTributacaoIssqn.setNullRepresentation("");
		glIssqn.addComponent(tfTributacaoIssqn, 1, 1);

		return glIssqn;
	}

	@AutoGenerated
	private GridLayout bglCombustivel() {
		// common part: create layout
		glCombustivel = new GridLayout();
		glCombustivel.setImmediate(false);
		glCombustivel.setWidth("100.0%");
		glCombustivel.setHeight("100.0%");
		glCombustivel.setMargin(false);
		glCombustivel.setSpacing(true);
		glCombustivel.setRows(4);
		glCombustivel.setColumns(4);

		// tfCodigoAnpCombustivel
		tfCodigoAnpCombustivel = new TextField("Código ANP: ");
		tfCodigoAnpCombustivel.setImmediate(false);
		tfCodigoAnpCombustivel.setWidth("-1px");
		tfCodigoAnpCombustivel.setHeight("-1px");
		tfCodigoAnpCombustivel.setSizeFull();
		tfCodigoAnpCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfCodigoAnpCombustivel, 0, 0);

		// tfCodifCombustivel
		tfCodifCombustivel = new TextField("CODIF: ");
		tfCodifCombustivel.setImmediate(false);
		tfCodifCombustivel.setWidth("-1px");
		tfCodifCombustivel.setHeight("-1px");
		tfCodifCombustivel.setSizeFull();
		tfCodifCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfCodifCombustivel, 1, 0);

		// tfQtdeTempAmbienteCombustivel
		tfQtdeTempAmbienteCombustivel = new TextField("Qtde temp ambiente: ");
		tfQtdeTempAmbienteCombustivel.setImmediate(false);
		tfQtdeTempAmbienteCombustivel.setWidth("-1px");
		tfQtdeTempAmbienteCombustivel.setHeight("-1px");
		tfQtdeTempAmbienteCombustivel.setSizeFull();
		tfQtdeTempAmbienteCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfQtdeTempAmbienteCombustivel, 2, 0);

		// tfUfConsumoCombustivel
		tfUfConsumoCombustivel = new TextField("UF consumo: ");
		tfUfConsumoCombustivel.setImmediate(false);
		tfUfConsumoCombustivel.setWidth("-1px");
		tfUfConsumoCombustivel.setHeight("-1px");
		tfUfConsumoCombustivel.setSizeFull();
		tfUfConsumoCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfUfConsumoCombustivel, 3, 0);

		// tfBcCideCombustivel
		tfBcCideCombustivel = new TextField("BC CIDE: ");
		tfBcCideCombustivel.setImmediate(false);
		tfBcCideCombustivel.setWidth("-1px");
		tfBcCideCombustivel.setHeight("-1px");
		tfBcCideCombustivel.setSizeFull();
		tfBcCideCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfBcCideCombustivel, 0, 1);

		// tfAliquotaCideCombustivel
		tfAliquotaCideCombustivel = new TextField("Alíquota CIDE: ");
		tfAliquotaCideCombustivel.setImmediate(false);
		tfAliquotaCideCombustivel.setWidth("-1px");
		tfAliquotaCideCombustivel.setHeight("-1px");
		tfAliquotaCideCombustivel.setSizeFull();
		tfAliquotaCideCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfAliquotaCideCombustivel, 1, 1);

		// tfValorCideCombustivel
		tfValorCideCombustivel = new TextField("Valor CIDE: ");
		tfValorCideCombustivel.setImmediate(false);
		tfValorCideCombustivel.setWidth("-1px");
		tfValorCideCombustivel.setHeight("-1px");
		tfValorCideCombustivel.setSizeFull();
		tfValorCideCombustivel.setNullRepresentation("");
		glCombustivel.addComponent(tfValorCideCombustivel, 2, 1);

		return glCombustivel;
	}

	@AutoGenerated
	private GridLayout bglVeiculo() {
		// common part: create layout
		glVeiculo = new GridLayout();
		glVeiculo.setImmediate(false);
		glVeiculo.setWidth("100.0%");
		glVeiculo.setHeight("100.0%");
		glVeiculo.setMargin(false);
		glVeiculo.setSpacing(true);
		glVeiculo.setRows(4);
		glVeiculo.setColumns(4);

		// textField_10
		textField_10 = new TextField();
		textField_10.setImmediate(false);
		textField_10.setWidth("-1px");
		textField_10.setHeight("-1px");
		glVeiculo.addComponent(textField_10, 0, 0);

		return glVeiculo;
	}

	@AutoGenerated
	private GridLayout bglMedicamento() {
		// common part: create layout
		glMedicamento = new GridLayout();
		glMedicamento.setImmediate(false);
		glMedicamento.setWidth("100.0%");
		glMedicamento.setHeight("100.0%");
		glMedicamento.setMargin(false);

		glMedicamento.addComponent(buildMedicamentoSubForm());

		return glMedicamento;
	}

	@AutoGenerated
	private GridLayout bglArmamento() {
		// common part: create layout
		glArmamento = new GridLayout();
		glArmamento.setImmediate(false);
		glArmamento.setWidth("100.0%");
		glArmamento.setHeight("100.0%");
		glArmamento.setMargin(false);

		glArmamento.addComponent(buildArmamentoSubForm());

		return glArmamento;
	}

	@AutoGenerated
	private GridLayout bglDeclaracaoImportacao() {
		// common part: create layout
		glDeclaracaoImportacao = new GridLayout();
		glDeclaracaoImportacao.setImmediate(false);
		glDeclaracaoImportacao.setWidth("100.0%");
		glDeclaracaoImportacao.setHeight("100.0%");
		glDeclaracaoImportacao.setMargin(false);
		glDeclaracaoImportacao.setSpacing(true);
		glDeclaracaoImportacao.setRows(2);
		glDeclaracaoImportacao.setColumns(1);

		glDeclaracaoImportacao.addComponent(buildDeclaracaoImportacaoSubForm(),
				0, 0);
		glDeclaracaoImportacao.addComponent(
				buildDeclaracaoImportacaoAdicoesSubForm(), 0, 1);

		return glDeclaracaoImportacao;
	}

	/**
	 * SUBFORM
	 * 
	 * @return
	 */

	private SubFormComponent<LivroEntity, Integer> buildSubForm() {
		// common part: create layout
		sfPrincipal = new SubFormComponent<LivroEntity, Integer>(
				LivroEntity.class, new String[] { "descricao", "competencia",
						"formaEscrituracao" }, new String[] { "Descrição",
						"Competência", "Forma de escrituração" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<LivroEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		return sfPrincipal;
	}

	private SubFormComponent<LivroEntity, Integer> buildMedicamentoSubForm() {
		// common part: create layout
		sfMedicamento = new SubFormComponent<LivroEntity, Integer>(
				LivroEntity.class, new String[] { "descricao", "competencia",
						"formaEscrituracao" }, new String[] { "Descrição",
						"Competência", "Forma de escrituração" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<LivroEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		return sfMedicamento;
	}

	private SubFormComponent<LivroEntity, Integer> buildArmamentoSubForm() {
		// common part: create layout
		sfArmamento = new SubFormComponent<LivroEntity, Integer>(
				LivroEntity.class, new String[] { "descricao", "competencia",
						"formaEscrituracao" }, new String[] { "Descrição",
						"Competência", "Forma de escrituração" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<LivroEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		return sfArmamento;
	}

	private SubFormComponent<LivroEntity, Integer> buildDeclaracaoImportacaoSubForm() {
		// common part: create layout
		sfDeclaracaoImportacao = new SubFormComponent<LivroEntity, Integer>(
				LivroEntity.class, new String[] { "descricao", "competencia",
						"formaEscrituracao" }, new String[] { "Descrição",
						"Competência", "Forma de escrituração" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<LivroEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		return sfDeclaracaoImportacao;
	}

	private SubFormComponent<LivroEntity, Integer> buildDeclaracaoImportacaoAdicoesSubForm() {
		// common part: create layout
		sfDeclaracaoImportacaoAdicoes = new SubFormComponent<LivroEntity, Integer>(
				LivroEntity.class, new String[] { "descricao", "competencia",
						"formaEscrituracao" }, new String[] { "Descrição",
						"Competência", "Forma de escrituração" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected TableFieldFactory getFieldFactory() {
				return new TableFieldFactory() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Field<?> createField(Container container,
							Object itemId, Object propertyId,
							Component uiContext) {
						return null;
					}

				};
			}

			@Override
			public boolean validateItems(List<LivroEntity> items) {
				// TODO Auto-generated method stub
				return true;
			}

		};

		return sfDeclaracaoImportacaoAdicoes;
	}

	public void preencherSubForm(List<LivroEntity> auxLista) {
		try {
			sfPrincipal.fillWith(auxLista);
			sfMedicamento.fillWith(auxLista);
			sfArmamento.fillWith(auxLista);
			sfDeclaracaoImportacao.fillWith(auxLista);
			sfDeclaracaoImportacaoAdicoes.fillWith(auxLista);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}