package dc.visao.produto;

import org.vaadin.tepi.imageviewer.ImageViewer;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;

import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.util.ComponentUtil;


import dc.controller.produto.ProdutosFormController;
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.produto.NCM;
import dc.entidade.produto.SubGrupoProduto;
import dc.entidade.produto.GrupoProduto;
import dc.entidade.produto.MarcaProduto;
import dc.entidade.produto.UnidadeProduto;
import dc.entidade.tributario.GrupoTributario;
import dc.entidade.tributario.ICMSCustomizado;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class ProdutosFormView extends CustomComponent {

	//@AutoGenerated
	//private GridLayout mainLayout;
	
	@AutoGenerated
	private VerticalLayout mainLayout;
	
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_3;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_4;
	
	@AutoGenerated
	private TextField txtAliquotaICms;
	@AutoGenerated
	private TextField txtAliquotaIssqn;
	@AutoGenerated
	private TextField txtLoteEconomicoCompra;
	@AutoGenerated
	private TextField txtPontoPedido;
	@AutoGenerated
	private TextField txtTaxaComissao;
	@AutoGenerated
	private TextField txtPeso;
	@AutoGenerated
	private TextField txtCodigoBalanca;
	@AutoGenerated
	private TextField txtTotalizadorParcial;
	@AutoGenerated
	private TextField txtExtipi;
	@AutoGenerated
	private TextField txtLst;
	@AutoGenerated
	private TextField txtEstoqueMaximo;
	@AutoGenerated
	private TextField txtEstoqueMinimo;
	@AutoGenerated
	private TextField txtEstoqueIdeal;
	@AutoGenerated
	private TextField txtQuantidadeEstoqueAnterior;
	@AutoGenerated
	private TextField txtQuantidadeEstoque;
	@AutoGenerated
	private TextField txtMarkup;
	@AutoGenerated
	private TextField txtPrecoLucroMaximo;
	@AutoGenerated
	private TextField txtPrecoLucroMinimo;
	@AutoGenerated
	private TextField txtPrecoLucroZero;
	@AutoGenerated
	private TextField txtCustoMedioLiquido;
	@AutoGenerated
	private TextField txtValorSugerido;
	@AutoGenerated
	private TextField txtValorVendaMinimo;
	@AutoGenerated
	private TextField txtValorVenda;
	@AutoGenerated
	private TextField txtValorCompra;
	@AutoGenerated
	private TextField txtDescricao;
	@AutoGenerated
	private TextField txtDescricaoPdv;
	@AutoGenerated
	private TextField txtNome;
	@AutoGenerated
	private TextField txtCodigoInterno;
	@AutoGenerated
	private TextField txtGtin;
	
	@AutoGenerated
	private ComboBox cmbTemIcmsCustomizado;
	@AutoGenerated
	private ComboBox cmbClasse;
	@AutoGenerated
	private ComboBox cmbInativo;
	@AutoGenerated
	private ComboBox cmbTipoItemSped;
	@AutoGenerated
	private ComboBox cmbIppt;
	@AutoGenerated
	private ComboBox cmbIat;
	@AutoGenerated
	private ComboBox cmbTipo;
	
	@AutoGenerated
	private ImageViewer imgProduto;
	
	@AutoGenerated
	private TabSheet tabSheet_1;
	@AutoGenerated
	private TabSheet subForms;
	GridLayout fields;

	private ManyToOneCombo<SubGrupoProduto> cmbSubGrupoProduto;
	private ManyToOneCombo<UnidadeProduto> cmbUnidadeProduto;
	private ManyToOneCombo<MarcaProduto> cmbMarcaProduto;
	private ManyToOneCombo<Almoxarifado> cmbAlmoxarifado;
	private ManyToOneCombo<GrupoProduto> cmbGrupoProduto;
	private ManyToOneCombo<NCM> cmbNcm;
	
	private ManyToOneCombo<GrupoTributario> cmbGrupoTributario;
	private ManyToOneCombo<ICMSCustomizado> cmbIcmsCustomizado;
	
	private ProdutosFormController controller;
	
	public ProdutosFormView(ProdutosFormController controller) {
		
		this.controller = controller;
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		//mainLayout.setWidth("100%");
		//mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setHeight("100%");
		//setWidth("100%");

		fields = buildFields();

		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
		mainLayout.addComponent(fields);


		buildPrincipalSubForm();
		buildValorPrincipalSubForm();
		buildComplementarSubForm();
		mainLayout.addComponent(subForms);

		//
		// // absoluteLayout_2
		//absoluteLayout_2 = buildAbsoluteLayout_2();
		//mainLayout.addComponent(absoluteLayout_2, 0, 2);

		return mainLayout;
	}
	
	@AutoGenerated
	private GridLayout buildFields() {

		fields = new GridLayout(7, 7);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setMargin(true);
		fields.setSpacing(true);
		fields.setRows(7);
		fields.setColumns(7);

		cmbSubGrupoProduto = new ManyToOneCombo<>();
		cmbSubGrupoProduto.setCaption(" SubGrupo Produto ");
		//cmbSubGrupoProduto.setWidth("780px");
		cmbSubGrupoProduto.setHeight("-1px");
		
		// //
		cmbUnidadeProduto = new ManyToOneCombo<>();
		cmbUnidadeProduto.setCaption("Unidade Produto");
		cmbUnidadeProduto.setImmediate(false);
		//cmbUnidadeProduto.setWidth("780px");
		cmbUnidadeProduto.setHeight("-1px");
		
		// //
		fields.addComponent(cmbSubGrupoProduto, 0, 0);
		fields.addComponent(cmbUnidadeProduto, 1, 0);
		
		// //
		cmbMarcaProduto = new ManyToOneCombo<>();
		cmbMarcaProduto.setCaption("Marca Produto");
		//cmbMarcaProduto.setImmediate(false);
		//cmbMarcaProduto.setWidth("780px");
		cmbMarcaProduto.setHeight("-1px");
		
		// //
		// // // cmbAlmoxarifado
		cmbAlmoxarifado = new ManyToOneCombo<>();
		cmbAlmoxarifado.setCaption("Almoxarifado");
		//cmbAlmoxarifado.setImmediate(false);
		//cmbAlmoxarifado.setWidth("780px");
		cmbAlmoxarifado.setHeight("-1px");

		fields.addComponent(cmbMarcaProduto, 0, 1);
		fields.addComponent(cmbAlmoxarifado, 1, 1);
		
		// //
		// cmbIcmsCustomizado
		cmbTemIcmsCustomizado = new ComboBox();
		cmbTemIcmsCustomizado.setCaption("ICMS Customizado?");
		//cmbTemIcmsCustomizado.setImmediate(false);
		//cmbTemIcmsCustomizado.setWidth("100px");
		cmbTemIcmsCustomizado.setHeight("-1px");
		
		carregarIcms();
		cmbTemIcmsCustomizado.setValue(SIM_NAO.NAO);

		cmbTemIcmsCustomizado.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				SIM_NAO obj = (SIM_NAO) event.getProperty().getValue();

				if (obj.equals(SIM_NAO.SIM)) {
					fields.removeComponent(cmbGrupoTributario);
					fields.addComponent(cmbIcmsCustomizado, 1, 2);
				}

				if (obj.equals(SIM_NAO.NAO)) {
					fields.removeComponent(cmbIcmsCustomizado);
					fields.addComponent(cmbGrupoTributario, 1, 2);
				}
			}
		});

		// cmbGrupoTributario
		cmbGrupoTributario = new ManyToOneCombo<>();
		cmbGrupoTributario.setCaption("Grupo Tributário");
		//cmbGrupoTributario.setImmediate(false);
		//cmbGrupoTributario.setWidth("456px");
		//cmbGrupoTributario.setHeight("-1px");

		cmbGrupoProduto = new ManyToOneCombo<>();
		cmbGrupoProduto.setCaption("Grupo Produto");
		//cmbGrupoProduto.setWidth("456px");
		cmbGrupoProduto.setHeight("-1px");

		fields.addComponent(cmbTemIcmsCustomizado, 0, 2);
		fields.addComponent(cmbGrupoProduto, 1, 2);
		// ////
		
	    cmbIcmsCustomizado = new ManyToOneCombo<>();
		cmbIcmsCustomizado.setCaption("ICMS Customizado");
		//cmbIcmsCustomizado.setImmediate(false);
		//cmbIcmsCustomizado.setWidth("456px");
		//cmbIcmsCustomizado.setHeight("-1px");
		
		
		return fields;

	}
	
	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");

		// tabSheet_1
		tabSheet_1 = buildTabSheet_1();
		absoluteLayout_2.addComponent(tabSheet_1, "top:-3.0px;left:20.0px;");

		return absoluteLayout_2;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_1() {
		// common part: create layout
		tabSheet_1 = new TabSheet();
		tabSheet_1.setImmediate(true);
		tabSheet_1.setWidth("100.0%");
		tabSheet_1.setHeight("100.0%");

		// absoluteLayout_3

		// absoluteLayout_4
		absoluteLayout_4 = buildAbsoluteLayout_4();
		tabSheet_1.addTab(absoluteLayout_4, "Tab", null);

		return tabSheet_1;
	}

	public void buildPrincipalSubForm() {
		GridLayout layout = new GridLayout(5, 5);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(false);
		layout.setSizeFull();

		txtGtin = ComponentUtil.buildTextField("GTIN");
		txtGtin.setMaxLength(60);
		txtGtin.setImmediate(false);
		txtGtin.setWidth("300px");
		txtGtin.setHeight("-1px");

		// txtCodigoInterno
		txtCodigoInterno = ComponentUtil.buildTextField("Código Interno");
		txtCodigoInterno.setMaxLength(60);
		txtCodigoInterno.setImmediate(false);
		txtCodigoInterno.setWidth("200px");
		txtCodigoInterno.setHeight("-1px");

		cmbNcm = new ManyToOneCombo<>();
		cmbNcm.setCaption("NCM");
		cmbNcm.setImmediate(false);
		cmbNcm.setWidth("250px");
		cmbNcm.setHeight("-1px");

		// cmbInativo
		cmbInativo = new ComboBox();
		cmbInativo.setCaption("Inativo?");
		cmbInativo.setImmediate(false);
		cmbInativo.setWidth("100px");
		cmbInativo.setHeight("-1px");
		carregarInativo();

		// cmbClasse
		cmbClasse = new ComboBox();
		cmbClasse.setCaption("Classe");
		cmbClasse.setImmediate(false);
		cmbClasse.setWidth("100px");
		cmbClasse.setHeight("-1px");
		carregarClasse();

		layout.addComponent(txtGtin, 0, 0);
		layout.addComponent(txtCodigoInterno, 1, 0);
		layout.addComponent(cmbNcm, 2, 0);
		layout.addComponent(cmbInativo, 3, 0);
		layout.addComponent(cmbClasse, 4, 0);

		txtNome = ComponentUtil.buildTextField("Nome");
		txtNome.setImmediate(false);
		txtNome.setWidth("300px");
		txtNome.setHeight("-1px");

		// txtDescricaoPdv
		txtDescricaoPdv = ComponentUtil.buildTextField("Descrição PDV");
		txtDescricaoPdv.setImmediate(false);
		txtDescricaoPdv.setWidth("300px");
		txtDescricaoPdv.setHeight("-1px");

		// // txtDescricao
		txtDescricao = ComponentUtil.buildTextField("Descrição");
		txtDescricao.setImmediate(false);
		txtDescricao.setWidth("300px");
		txtDescricao.setHeight("-1px");
		//
		layout.addComponent(txtNome, 0, 1);
		layout.addComponent(txtDescricaoPdv, 1, 1);
		layout.addComponent(txtDescricao, 0, 2);

		subForms.addTab(layout, "Principal", null);
	}

	public void buildValorPrincipalSubForm() {
		GridLayout layout = new GridLayout(5, 5);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();

		txtValorCompra = ComponentUtil.buildCurrencyField("Valor Compra");
		txtValorCompra.setHeight("-1px");
		txtValorCompra.setWidth("150px");

		txtValorVenda = ComponentUtil.buildCurrencyField("Valor Venda");
		txtValorVenda.setHeight("-1px");
		txtValorVenda.setWidth("150px");

		txtValorVendaMinimo = ComponentUtil.buildCurrencyField("Valor Venda Minimo");
		txtValorVendaMinimo.setHeight("-1px");
		txtValorVendaMinimo.setWidth("150px");

		txtValorSugerido = ComponentUtil.buildCurrencyField("Valor Sugerido");
		txtValorSugerido.setHeight("-1px");
		txtValorSugerido.setWidth("150px");

		txtCustoMedioLiquido = ComponentUtil.buildCurrencyField("Custo Médio Liquido");
		txtCustoMedioLiquido.setHeight("-1px");
		txtCustoMedioLiquido.setWidth("150px");

		layout.addComponent(txtValorCompra, 0, 0);
		layout.addComponent(txtValorVenda, 1, 0);
		layout.addComponent(txtValorVendaMinimo, 2, 0);
		layout.addComponent(txtValorSugerido, 3, 0);
		layout.addComponent(txtCustoMedioLiquido, 4, 0);

		txtPrecoLucroZero = ComponentUtil.buildCurrencyField("Preço Lucro Zero");
		txtPrecoLucroZero.setHeight("-1px");
		txtPrecoLucroZero.setWidth("150px");

		txtPrecoLucroMinimo = ComponentUtil.buildCurrencyField("Preço Lucro Minimo");
		txtPrecoLucroMinimo.setHeight("-1px");
		txtPrecoLucroMinimo.setWidth("150px");

		txtPrecoLucroMaximo = ComponentUtil.buildCurrencyField("Preço Lucro Máximo");
		txtPrecoLucroMaximo.setHeight("-1px");
		txtPrecoLucroMaximo.setWidth("150px");

		txtMarkup = ComponentUtil.buildCurrencyField("Markup");
		txtMarkup.setHeight("-1px");
		txtMarkup.setWidth("150px");

		txtQuantidadeEstoque = ComponentUtil.buildNumberField("Quantidade Estoque");
		txtQuantidadeEstoque.setHeight("-1px");
		txtQuantidadeEstoque.setWidth("150px");

		layout.addComponent(txtPrecoLucroZero, 0, 1);
		layout.addComponent(txtPrecoLucroMinimo, 1, 1);
		layout.addComponent(txtPrecoLucroMaximo, 2, 1);
		layout.addComponent(txtMarkup, 3, 1);
		layout.addComponent(txtQuantidadeEstoque, 4, 1);

		txtQuantidadeEstoqueAnterior = ComponentUtil.buildNumberField("Qtde Estoque Anterior");
		txtQuantidadeEstoqueAnterior.setHeight("-1px");
		txtQuantidadeEstoqueAnterior.setHeight("-1px");
		txtQuantidadeEstoqueAnterior.setWidth("150px");

		txtEstoqueIdeal = ComponentUtil.buildNumberField("Estoque Ideal");
		txtEstoqueIdeal.setHeight("-1px");
		txtEstoqueIdeal.setHeight("-1px");
		txtEstoqueIdeal.setWidth("150px");

		txtEstoqueMinimo = ComponentUtil.buildNumberField("Estoque Minimo");
		txtEstoqueMinimo.setHeight("-1px");
		txtEstoqueMinimo.setHeight("-1px");
		txtEstoqueMinimo.setWidth("150px");

		txtEstoqueMaximo = ComponentUtil.buildNumberField("Estoque Máximo");
		txtEstoqueMaximo.setHeight("-1px");
		txtEstoqueMaximo.setHeight("-1px");
		txtEstoqueMaximo.setWidth("150px");

		layout.addComponent(txtQuantidadeEstoqueAnterior, 0, 2);
		layout.addComponent(txtEstoqueIdeal, 1, 2);
		layout.addComponent(txtEstoqueMinimo, 2, 2);
		layout.addComponent(txtEstoqueMaximo, 3, 2);

		subForms.addTab(layout, "Valores Principais", null);
	}

	public void buildComplementarSubForm() {
		GridLayout layout = new GridLayout(6, 6);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();

		txtLst = new TextField("LST:");
		txtLst.setMaxLength(4);

		txtExtipi = new TextField("EXTIPI");
		txtExtipi.setMaxLength(3);

		cmbTipo = ComponentUtil.buildComboBox("Tipo");
		cmbTipo.setHeight("-1px");
		cmbTipo.setWidth("150px");
		carregarTipoVenda();

		cmbIat = ComponentUtil.buildComboBox("IAT");
		cmbIat.setHeight("-1px");
		cmbIat.setWidth("150px");
		carregarIAT();

		layout.addComponent(txtLst, 0, 0);
		layout.addComponent(txtExtipi, 1, 0);

		cmbIppt = ComponentUtil.buildComboBox("IPPT");
		cmbIppt.setHeight("-1px");
		cmbIppt.setWidth("150px");
		carregarIPPT();

		cmbTipoItemSped = ComponentUtil.buildComboBox("Tipo Item SPED");
		cmbTipoItemSped.setHeight("-1px");
		cmbTipoItemSped.setWidth("150px");
		carregarSped();

		layout.addComponent(cmbTipo, 2, 0);
		layout.addComponent(cmbIat, 3, 0);
		layout.addComponent(cmbIppt, 4, 0);
		layout.addComponent(cmbTipoItemSped, 5, 0);

		txtTotalizadorParcial = ComponentUtil.buildCurrencyField("Totalizador Parcial");
		txtTotalizadorParcial.setHeight("-1px");
		txtTotalizadorParcial.setWidth("150px");

		txtCodigoBalanca = ComponentUtil.buildNumericField("Código Balança");
		txtCodigoBalanca.setConverter(new IntegerConverter());
		txtCodigoBalanca.setHeight("-1px");
		txtCodigoBalanca.setWidth("150px");

		txtPeso = ComponentUtil.buildNumberField("Peso");
		txtPeso.setHeight("-1px");
		txtPeso.setWidth("150px");

		txtTaxaComissao = ComponentUtil.buildPercentageField("Taxa Comissão");
		txtTaxaComissao.setHeight("-1px");
		txtTaxaComissao.setWidth("150px");

		layout.addComponent(txtTotalizadorParcial, 0, 1);
		layout.addComponent(txtCodigoBalanca, 1, 1);
		layout.addComponent(txtPeso, 2, 1);
		layout.addComponent(txtTaxaComissao, 3, 1);

		txtPontoPedido = ComponentUtil.buildNumberField("Ponto Pedido");
		txtPontoPedido.setHeight("-1px");
		txtPontoPedido.setWidth("150px");

		txtLoteEconomicoCompra = ComponentUtil.buildNumberField("Lote Econômico Compra");
		txtLoteEconomicoCompra.setHeight("-1px");
		txtLoteEconomicoCompra.setWidth("150px");

		txtAliquotaICms = ComponentUtil.buildPercentageField("Aliquota ICMS");
		txtAliquotaICms.setHeight("-1px");
		txtAliquotaICms.setWidth("150px");

		txtAliquotaIssqn = ComponentUtil.buildPercentageField("Aliquota ISSQN");
		txtAliquotaIssqn.setHeight("-1px");
		txtAliquotaIssqn.setWidth("150px");

		layout.addComponent(txtPontoPedido, 0, 2);
		layout.addComponent(txtLoteEconomicoCompra, 1, 2);
		layout.addComponent(txtAliquotaICms, 2, 2);
		layout.addComponent(txtAliquotaIssqn, 3, 2);

		subForms.addTab(layout, "Dados Complementares", null);
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_4() {
		// common part: create layout
		absoluteLayout_4 = new AbsoluteLayout();
		absoluteLayout_4.setImmediate(false);
		absoluteLayout_4.setWidth("100.0%");
		absoluteLayout_4.setHeight("100.0%");

		// imgProduto
		imgProduto = new ImageViewer();
		imgProduto.setCaption("Imagem do Produto");
		imgProduto.setImmediate(false);
		imgProduto.setWidth("-1px");
		imgProduto.setHeight("130px");
		absoluteLayout_4.addComponent(imgProduto, "top:12.0px;left:9.0px;");

		// txtLst
		txtLst = new TextField();
		txtLst.setCaption("LST");
		txtLst.setImmediate(false);
		txtLst.setWidth("40px");
		txtLst.setHeight("-1px");
		absoluteLayout_4.addComponent(txtLst, "top:12.0px;left:159.0px;");

		// txtExtipi
		txtExtipi = new TextField();
		txtExtipi.setCaption("EXTIPI");
		txtExtipi.setImmediate(false);
		txtExtipi.setWidth("-1px");
		txtExtipi.setHeight("-1px");
		absoluteLayout_4.addComponent(txtExtipi, "top:12.0px;left:219.0px;");

		// cmbTipo
		cmbTipo = new ComboBox();
		cmbTipo.setCaption("Tipo");
		cmbTipo.setImmediate(false);
		cmbTipo.setWidth("80px");
		cmbTipo.setHeight("-1px");
		absoluteLayout_4.addComponent(cmbTipo, "top:12.0px;left:379.0px;");

		// cmbIat
		cmbIat = new ComboBox();
		cmbIat.setCaption("IAT");
		cmbIat.setImmediate(false);
		cmbIat.setWidth("100.0%");
		cmbIat.setHeight("-1px");
		absoluteLayout_4.addComponent(cmbIat, "top:12.0px;right:426.0px;left:479.0px;");

		// cmbIppt
		cmbIppt = new ComboBox();
		cmbIppt.setCaption("IPPT");
		cmbIppt.setImmediate(false);
		cmbIppt.setWidth("106px");
		cmbIppt.setHeight("-1px");
		absoluteLayout_4.addComponent(cmbIppt, "top:12.0px;left:599.0px;");

		// cmbTipoItemSped
		cmbTipoItemSped = new ComboBox();
		cmbTipoItemSped.setCaption("Tipo Item Sped");
		cmbTipoItemSped.setImmediate(false);
		cmbTipoItemSped.setWidth("-1px");
		cmbTipoItemSped.setHeight("-1px");
		absoluteLayout_4.addComponent(cmbTipoItemSped, "top:12.0px;left:726.0px;");

		// txtTotalizadorParcial
		txtTotalizadorParcial = new TextField();
		txtTotalizadorParcial.setCaption("Totalizador Parcial");
		txtTotalizadorParcial.setImmediate(false);
		txtTotalizadorParcial.setWidth("-1px");
		txtTotalizadorParcial.setHeight("-1px");
		absoluteLayout_4.addComponent(txtTotalizadorParcial, "top:54.0px;left:158.0px;");

		// txtCodigoBalanca
		txtCodigoBalanca = new TextField();
		txtCodigoBalanca.setCaption("Código Balança");
		txtCodigoBalanca.setImmediate(false);
		txtCodigoBalanca.setWidth("-1px");
		txtCodigoBalanca.setHeight("-1px");
		absoluteLayout_4.addComponent(txtCodigoBalanca, "top:54.0px;left:319.0px;");

		// txtPeso
		txtPeso = new TextField();
		txtPeso.setCaption("Peso");
		txtPeso.setImmediate(false);
		txtPeso.setWidth("140px");
		txtPeso.setHeight("-1px");
		absoluteLayout_4.addComponent(txtPeso, "top:98.0px;left:159.0px;");

		// txtTaxaComissao
		txtTaxaComissao = new TextField();
		txtTaxaComissao.setCaption("Taxa Comissão");
		txtTaxaComissao.setImmediate(false);
		txtTaxaComissao.setWidth("-1px");
		txtTaxaComissao.setHeight("-1px");
		absoluteLayout_4.addComponent(txtTaxaComissao, "top:98.0px;left:319.0px;");

		// txtPontoPedido
		txtPontoPedido = new TextField();
		txtPontoPedido.setCaption("Ponto Pedido");
		txtPontoPedido.setImmediate(false);
		txtPontoPedido.setWidth("-1px");
		txtPontoPedido.setHeight("-1px");
		absoluteLayout_4.addComponent(txtPontoPedido, "top:98.0px;left:479.0px;");

		// txtLoteEconomicoCompra
		txtLoteEconomicoCompra = new TextField();
		txtLoteEconomicoCompra.setCaption("Lote Econômico Compra");
		txtLoteEconomicoCompra.setImmediate(false);
		txtLoteEconomicoCompra.setWidth("-1px");
		txtLoteEconomicoCompra.setHeight("-1px");
		absoluteLayout_4.addComponent(txtLoteEconomicoCompra, "top:98.0px;left:639.0px;");

		// txtAliquotaIssqn
		txtAliquotaIssqn = new TextField();
		txtAliquotaIssqn.setCaption("Alíquota ISSQN");
		txtAliquotaIssqn.setImmediate(false);
		txtAliquotaIssqn.setWidth("-1px");
		txtAliquotaIssqn.setHeight("-1px");
		absoluteLayout_4.addComponent(txtAliquotaIssqn, "top:139.0px;left:319.0px;");

		// txtAliquotaICms
		txtAliquotaICms = new TextField();
		txtAliquotaICms.setCaption("Alíquota ICMS");
		txtAliquotaICms.setImmediate(false);
		txtAliquotaICms.setWidth("-1px");
		txtAliquotaICms.setHeight("-1px");
		absoluteLayout_4.addComponent(txtAliquotaICms, "top:137.0px;left:158.0px;");

		return absoluteLayout_4;
	}
	
	
	public enum TIPO_VENDA {

		VENDA("Venda", "1"), COMPOSICAO("Composição", "2"), PRODUCAO("Produção", "3"), INSUMO("Insumo", "4"), USO_PROPRIO("Uso Próprio", "5");

		private TIPO_VENDA(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TIPO_VENDA getTipoVenda(String codigo) {
			if (codigo.equals("1")) {
				return VENDA;
			}
			if (codigo.equals("2")) {
				return COMPOSICAO;
			}

			if (codigo.equals("3")) {
				return PRODUCAO;
			}

			if (codigo.equals("4")) {
				return INSUMO;
			}

			if (codigo.equals("5")) {
				return USO_PROPRIO;
			}
			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public enum IAT {

		ARREDONDAMENTO("Arredondamento", "1"), TRUNCAMENTO("Truncamento", "2");

		private IAT(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static IAT getIat(String codigo) {
			if (codigo.equals("1")) {
				return ARREDONDAMENTO;
			}
			if (codigo.equals("2")) {
				return TRUNCAMENTO;
			}

			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public enum IPPT {

		PROPRIO("Próprio", "1"), TERCEIRO("Terceiro", "2");

		private IPPT(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static IPPT getiPPT(String codigo) {
			if (codigo.equals("1")) {
				return PROPRIO;
			}
			if (codigo.equals("2")) {
				return TERCEIRO;
			}

			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public void carregarSped() {
		cmbTipoItemSped.removeAllItems();
		cmbTipoItemSped.addItem(TIPO_SPED.MERCADORIA_PRA_REVENDA);
		cmbTipoItemSped.addItem(TIPO_SPED.MATERIA_PRIMA);
		cmbTipoItemSped.addItem(TIPO_SPED.EMBALAGEM);
		cmbTipoItemSped.addItem(TIPO_SPED.PRODUTO_EM_PROCESSO);
		cmbTipoItemSped.addItem(TIPO_SPED.PRODUTO_ACABADO);
		cmbTipoItemSped.addItem(TIPO_SPED.SUBPRODUTO);
		cmbTipoItemSped.addItem(TIPO_SPED.PRODUTO_INTERMEDIARIO);
		cmbTipoItemSped.addItem(TIPO_SPED.MATERIAL_USO_CONSUMO);
	}

	public enum TIPO_SPED {

		MERCADORIA_PRA_REVENDA("Mercadoria P/Revenda", "00"), MATERIA_PRIMA("Matéria Prima", "01"), EMBALAGEM("Embalagem", "02"), PRODUTO_EM_PROCESSO(
				"Produto em Processo", "03"), PRODUTO_ACABADO("Produto Acabado", "04"), SUBPRODUTO("SubProduto", "05"), PRODUTO_INTERMEDIARIO(
				"Produto Intermediário", "06"), MATERIAL_USO_CONSUMO("Material de Uso e Consumo", "07");

		private TIPO_SPED(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static TIPO_SPED getTipoSped(String codigo) {
			if (codigo.equals("00")) {
				return MERCADORIA_PRA_REVENDA;
			}
			if (codigo.equals("01")) {
				return MATERIA_PRIMA;
			}

			if (codigo.equals("02")) {
				return EMBALAGEM;
			}

			if (codigo.equals("03")) {
				return PRODUTO_EM_PROCESSO;
			}

			if (codigo.equals("04")) {
				return PRODUTO_ACABADO;
			}

			if (codigo.equals("05")) {
				return SUBPRODUTO;
			}

			if (codigo.equals("06")) {
				return PRODUTO_INTERMEDIARIO;
			}

			if (codigo.equals("07")) {
				return MATERIAL_USO_CONSUMO;
			}

			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public void carregarTipoVenda() {
		this.cmbTipo.removeAllItems();
		this.cmbTipo.addItem(TIPO_VENDA.VENDA);
		this.cmbTipo.addItem(TIPO_VENDA.COMPOSICAO);
		this.cmbTipo.addItem(TIPO_VENDA.PRODUCAO);
		this.cmbTipo.addItem(TIPO_VENDA.INSUMO);
		this.cmbTipo.addItem(TIPO_VENDA.USO_PROPRIO);

	}

	public void carregarIAT() {
		this.cmbIat.removeAllItems();
		this.cmbIat.addItem(IAT.ARREDONDAMENTO);
		this.cmbIat.addItem(IAT.TRUNCAMENTO);
	}

	public void carregarIPPT() {
		this.cmbIppt.removeAllItems();
		this.cmbIppt.addItem(IPPT.PROPRIO);
		this.cmbIppt.addItem(IPPT.TERCEIRO);
	}

	public enum SIM_NAO {

		NAO("Não", "0"), SIM("Sim", "1");

		private SIM_NAO(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static SIM_NAO getValor(String codigo) {
			if (codigo.equals("0")) {
				return NAO;
			}
			if (codigo.equals("1")) {
				return SIM;
			}
			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	public void carregarIcms() {
		cmbTemIcmsCustomizado.removeAllItems();
		cmbTemIcmsCustomizado.addItem(SIM_NAO.NAO);
		cmbTemIcmsCustomizado.addItem(SIM_NAO.SIM);
	}

	public void carregarInativo() {
		cmbInativo.removeAllItems();
		cmbInativo.addItem(SIM_NAO.NAO);
		cmbInativo.addItem(SIM_NAO.SIM);
	}

	public ComboBox getCmbTemIcmsCustomizado() {
		return cmbTemIcmsCustomizado;
	}

	public void setCmbTemIcmsCustomizado(ComboBox cmbTemIcmsCustomizado) {
		this.cmbTemIcmsCustomizado = cmbTemIcmsCustomizado;
	}

	public ComboBox getCmbTipoItemSped() {
		return cmbTipoItemSped;
	}

	public void setCmbTipoItemSped(ComboBox cmbTipoItemSped) {
		this.cmbTipoItemSped = cmbTipoItemSped;
	}

	public ComboBox getCmbIppt() {
		return cmbIppt;
	}

	public void setCmbIppt(ComboBox cmbIppt) {
		this.cmbIppt = cmbIppt;
	}

	public ComboBox getCmbIat() {
		return cmbIat;
	}

	public void setCmbIat(ComboBox cmbIat) {
		this.cmbIat = cmbIat;
	}

	public ComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(ComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public ComboBox getCmbClasse() {
		return cmbClasse;
	}

	public void setCmbClasse(ComboBox cmbClasse) {
		this.cmbClasse = cmbClasse;
	}

	public ComboBox getCmbInativo() {
		return cmbInativo;
	}

	public void setCmbInativo(ComboBox cmbInativo) {
		this.cmbInativo = cmbInativo;
	}

	public enum CLASSE {

		A("A", "1"), B("B", "2"), C("C", "3");

		private CLASSE(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static CLASSE getClasse(String codigo) {
			if (codigo.equals("1")) {
				return A;
			}
			if (codigo.equals("2")) {
				return B;
			}
			if (codigo.equals("3")) {
				return C;
			}
			return null;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getLabel() {
			return label;
		}

		@Override
		public String toString() {
			return label;
		}
	}

	void carregarClasse() {
		cmbClasse.removeAllItems();
		cmbClasse.addItem(CLASSE.A);
		cmbClasse.addItem(CLASSE.B);
		cmbClasse.addItem(CLASSE.C);

	}
	
	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public AbsoluteLayout getAbsoluteLayout_2() {
		return absoluteLayout_2;
	}

	public void setAbsoluteLayout_2(AbsoluteLayout absoluteLayout_2) {
		this.absoluteLayout_2 = absoluteLayout_2;
	}

	public AbsoluteLayout getAbsoluteLayout_3() {
		return absoluteLayout_3;
	}

	public void setAbsoluteLayout_3(AbsoluteLayout absoluteLayout_3) {
		this.absoluteLayout_3 = absoluteLayout_3;
	}

	public AbsoluteLayout getAbsoluteLayout_4() {
		return absoluteLayout_4;
	}

	public void setAbsoluteLayout_4(AbsoluteLayout absoluteLayout_4) {
		this.absoluteLayout_4 = absoluteLayout_4;
	}

	public ImageViewer getImgProduto() {
		return imgProduto;
	}

	public void setImgProduto(ImageViewer imgProduto) {
		this.imgProduto = imgProduto;
	}

	public TabSheet getTabSheet_1() {
		return tabSheet_1;
	}

	public void setTabSheet_1(TabSheet tabSheet_1) {
		this.tabSheet_1 = tabSheet_1;
	}

	public TabSheet getSubForms() {
		return subForms;
	}

	public void setSubForms(TabSheet subForms) {
		this.subForms = subForms;
	}

	public TextField getTxtAliquotaICms() {
		return txtAliquotaICms;
	}

	public void setTxtAliquotaICms(TextField txtAliquotaICms) {
		this.txtAliquotaICms = txtAliquotaICms;
	}

	public TextField getTxtAliquotaIssqn() {
		return txtAliquotaIssqn;
	}

	public void setTxtAliquotaIssqn(TextField txtAliquotaIssqn) {
		this.txtAliquotaIssqn = txtAliquotaIssqn;
	}

	public TextField getTxtLoteEconomicoCompra() {
		return txtLoteEconomicoCompra;
	}

	public void setTxtLoteEconomicoCompra(TextField txtLoteEconomicoCompra) {
		this.txtLoteEconomicoCompra = txtLoteEconomicoCompra;
	}

	public TextField getTxtPontoPedido() {
		return txtPontoPedido;
	}

	public void setTxtPontoPedido(TextField txtPontoPedido) {
		this.txtPontoPedido = txtPontoPedido;
	}

	public TextField getTxtTaxaComissao() {
		return txtTaxaComissao;
	}

	public void setTxtTaxaComissao(TextField txtTaxaComissao) {
		this.txtTaxaComissao = txtTaxaComissao;
	}

	public TextField getTxtPeso() {
		return txtPeso;
	}

	public void setTxtPeso(TextField txtPeso) {
		this.txtPeso = txtPeso;
	}

	public TextField getTxtCodigoBalanca() {
		return txtCodigoBalanca;
	}

	public void setTxtCodigoBalanca(TextField txtCodigoBalanca) {
		this.txtCodigoBalanca = txtCodigoBalanca;
	}

	public TextField getTxtTotalizadorParcial() {
		return txtTotalizadorParcial;
	}

	public void setTxtTotalizadorParcial(TextField txtTotalizadorParcial) {
		this.txtTotalizadorParcial = txtTotalizadorParcial;
	}

	public TextField getTxtExtipi() {
		return txtExtipi;
	}

	public void setTxtExtipi(TextField txtExtipi) {
		this.txtExtipi = txtExtipi;
	}

	public TextField getTxtLst() {
		return txtLst;
	}

	public void setTxtLst(TextField txtLst) {
		this.txtLst = txtLst;
	}

	public TextField getTxtEstoqueMaximo() {
		return txtEstoqueMaximo;
	}

	public void setTxtEstoqueMaximo(TextField txtEstoqueMaximo) {
		this.txtEstoqueMaximo = txtEstoqueMaximo;
	}

	public TextField getTxtEstoqueMinimo() {
		return txtEstoqueMinimo;
	}

	public void setTxtEstoqueMinimo(TextField txtEstoqueMinimo) {
		this.txtEstoqueMinimo = txtEstoqueMinimo;
	}

	public TextField getTxtEstoqueIdeal() {
		return txtEstoqueIdeal;
	}

	public void setTxtEstoqueIdeal(TextField txtEstoqueIdeal) {
		this.txtEstoqueIdeal = txtEstoqueIdeal;
	}

	public TextField getTxtQuantidadeEstoqueAnterior() {
		return txtQuantidadeEstoqueAnterior;
	}

	public void setTxtQuantidadeEstoqueAnterior(
			TextField txtQuantidadeEstoqueAnterior) {
		this.txtQuantidadeEstoqueAnterior = txtQuantidadeEstoqueAnterior;
	}

	public TextField getTxtQuantidadeEstoque() {
		return txtQuantidadeEstoque;
	}

	public void setTxtQuantidadeEstoque(TextField txtQuantidadeEstoque) {
		this.txtQuantidadeEstoque = txtQuantidadeEstoque;
	}

	public TextField getTxtMarkup() {
		return txtMarkup;
	}

	public void setTxtMarkup(TextField txtMarkup) {
		this.txtMarkup = txtMarkup;
	}

	public TextField getTxtPrecoLucroMaximo() {
		return txtPrecoLucroMaximo;
	}

	public void setTxtPrecoLucroMaximo(TextField txtPrecoLucroMaximo) {
		this.txtPrecoLucroMaximo = txtPrecoLucroMaximo;
	}

	public TextField getTxtPrecoLucroMinimo() {
		return txtPrecoLucroMinimo;
	}

	public void setTxtPrecoLucroMinimo(TextField txtPrecoLucroMinimo) {
		this.txtPrecoLucroMinimo = txtPrecoLucroMinimo;
	}

	public TextField getTxtPrecoLucroZero() {
		return txtPrecoLucroZero;
	}

	public void setTxtPrecoLucroZero(TextField txtPrecoLucroZero) {
		this.txtPrecoLucroZero = txtPrecoLucroZero;
	}

	public TextField getTxtCustoMedioLiquido() {
		return txtCustoMedioLiquido;
	}

	public void setTxtCustoMedioLiquido(TextField txtCustoMedioLiquido) {
		this.txtCustoMedioLiquido = txtCustoMedioLiquido;
	}

	public TextField getTxtValorSugerido() {
		return txtValorSugerido;
	}

	public void setTxtValorSugerido(TextField txtValorSugerido) {
		this.txtValorSugerido = txtValorSugerido;
	}

	public TextField getTxtValorVendaMinimo() {
		return txtValorVendaMinimo;
	}

	public void setTxtValorVendaMinimo(TextField txtValorVendaMinimo) {
		this.txtValorVendaMinimo = txtValorVendaMinimo;
	}

	public TextField getTxtValorVenda() {
		return txtValorVenda;
	}

	public void setTxtValorVenda(TextField txtValorVenda) {
		this.txtValorVenda = txtValorVenda;
	}

	public TextField getTxtValorCompra() {
		return txtValorCompra;
	}

	public void setTxtValorCompra(TextField txtValorCompra) {
		this.txtValorCompra = txtValorCompra;
	}

	public TextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(TextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public TextField getTxtDescricaoPdv() {
		return txtDescricaoPdv;
	}

	public void setTxtDescricaoPdv(TextField txtDescricaoPdv) {
		this.txtDescricaoPdv = txtDescricaoPdv;
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtCodigoInterno() {
		return txtCodigoInterno;
	}

	public void setTxtCodigoInterno(TextField txtCodigoInterno) {
		this.txtCodigoInterno = txtCodigoInterno;
	}

	public TextField getTxtGtin() {
		return txtGtin;
	}

	public void setTxtGtin(TextField txtGtin) {
		this.txtGtin = txtGtin;
	}

	public ManyToOneCombo<UnidadeProduto> getCmbUnidadeProduto() {
		return cmbUnidadeProduto;
	}

	public void setCmbUnidadeProduto(
			ManyToOneCombo<UnidadeProduto> cmbUnidadeProduto) {
		this.cmbUnidadeProduto = cmbUnidadeProduto;
	}

	public ManyToOneCombo<MarcaProduto> getCmbMarcaProduto() {
		return cmbMarcaProduto;
	}

	public void setCmbMarcaProduto(ManyToOneCombo<MarcaProduto> cmbMarcaProduto) {
		this.cmbMarcaProduto = cmbMarcaProduto;
	}

	public ManyToOneCombo<Almoxarifado> getCmbAlmoxarifado() {
		return cmbAlmoxarifado;
	}

	public void setCmbAlmoxarifado(ManyToOneCombo<Almoxarifado> cmbAlmoxarifado) {
		this.cmbAlmoxarifado = cmbAlmoxarifado;
	}

	public ManyToOneCombo<GrupoProduto> getCmbGrupoProduto() {
		return cmbGrupoProduto;
	}

	public void setCmbGrupoProduto(ManyToOneCombo<GrupoProduto> cmbGrupoProduto) {
		this.cmbGrupoProduto = cmbGrupoProduto;
	}

	public ManyToOneCombo<NCM> getCmbNcm() {
		return cmbNcm;
	}

	public void setCmbNcm(ManyToOneCombo<NCM> cmbNcm) {
		this.cmbNcm = cmbNcm;
	}

	public ManyToOneCombo<GrupoTributario> getCmbGrupoTributario() {
		return cmbGrupoTributario;
	}

	public void setCmbGrupoTributario(
			ManyToOneCombo<GrupoTributario> cmbGrupoTributario) {
		this.cmbGrupoTributario = cmbGrupoTributario;
	}

	public ManyToOneCombo<SubGrupoProduto> getCmbSubGrupoProduto() {
		return cmbSubGrupoProduto;
	}

	public void setCmbSubGrupoProduto(
			ManyToOneCombo<SubGrupoProduto> cmbSubGrupoProduto) {
		this.cmbSubGrupoProduto = cmbSubGrupoProduto;
	}

	public GridLayout getFields() {
		return fields;
	}

	public void setFields(GridLayout fields) {
		this.fields = fields;
	}

	public ManyToOneCombo<ICMSCustomizado> getCmbIcmsCustomizado() {
		return cmbIcmsCustomizado;
	}

	public void setCmbIcmsCustomizado(
			ManyToOneCombo<ICMSCustomizado> cmbIcmsCustomizado) {
		this.cmbIcmsCustomizado = cmbIcmsCustomizado;
	}
	
}
