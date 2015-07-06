package dc.visao.geral.produto;

import java.util.List;

import org.vaadin.tepi.imageviewer.ImageViewer;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.controller.geral.produto.ProdutoFormController;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.framework.component.NumberField;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class ProdutoFormView extends CustomComponent {

	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TextField tfAliquotaIcms, tfAliquotaIssqn, tfLoteEconomicoCompra,
			tfPontoPedido, tfTaxaComissao, tfPeso, tfCodigoBalanca,
			tfTotalizadorParcial, tfExtipi, tfLst;

	@AutoGenerated
	private TextField tfEstoqueMaximo, tfEstoqueMinimo, tfEstoqueIdeal,
			tfQuantidadeEstoqueAnterior, tfQuantidadeEstoque, cfMarkup,
			cfPrecoLucroMaximo, cfPrecoLucroMinimo, cfPrecoLucroZero,
			cfCustoMedioLiquido, cfValorSugerido, cfValorVendaMinimo,
			cfValorVenda, cfValorCompra;

	@AutoGenerated
	private TextField tfDescricao, tfDescricaoPdv, tfNome, tfCodigoInterno,
			tfGtin;

	@AutoGenerated
	private ComboBox cbTemIcmsCustomizado, cbClasse, cbInativo, cbTipoItemSped,
			cbIppt, cbIat, cbTipoVenda;

	@AutoGenerated
	private ImageViewer ivProduto;

	@AutoGenerated
	private GridLayout glGeral;

	@AutoGenerated
	private TabSheet tsGeral;

	@AutoGenerated
	private Panel plInformacaoGeral;

	@AutoGenerated
	private VerticalLayout vlInformacaoGeral;

	@AutoGenerated
	private GridLayout glInformacaoGeral;

	@AutoGenerated
	private VerticalLayout vlInformacaoValor;

	@AutoGenerated
	private Panel plInformacaoValor;

	@AutoGenerated
	private GridLayout glInformacaoValor;

	@AutoGenerated
	private VerticalLayout vlInformacaoComplementar;

	@AutoGenerated
	private Panel plInformacaoComplementar;

	@AutoGenerated
	private GridLayout glInformacaoComplementar;

	@AutoGenerated
	private ManyToOneCombo<SubGrupoEntity> mocSubGrupo;

	@AutoGenerated
	private ManyToOneCombo<UnidadeProdutoEntity> mocUnidadeProduto;

	@AutoGenerated
	private ManyToOneCombo<MarcaEntity> mocMarca;

	@AutoGenerated
	private ManyToOneCombo<AlmoxarifadoEntity> mocAlmoxarifado;

	@AutoGenerated
	private ManyToOneCombo<GrupoEntity> mocGrupo;

	@AutoGenerated
	private ManyToOneCombo<NcmEntity> mocNcm;

	@AutoGenerated
	private ManyToOneCombo<GrupoTributarioEntity> mocGrupoTributario;

	@AutoGenerated
	private ManyToOneCombo<IcmsCustomizadoCabecalhoEntity> mocIcmsCustomizado;

	private ProdutoFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ProdutoFormView(ProdutoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.controller = controller;

		// TODO add user code here
	}

	@AutoGenerated
	private void buildMainLayout() {
		// the main layout and components will be created here
		setSizeFull();
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

		tsGeral.addTab(bvlInformacaoGeral(), 0);
		tsGeral.addTab(bvlInformacaoValor(), 1);
		tsGeral.addTab(bvlInformacaoComplementar(), 2);

		mainLayout.addComponent(tsGeral);
		mainLayout.setExpandRatio(tsGeral, 1);
		
		for (SimNaoEn en : SimNaoEn.values()) {
			cbTemIcmsCustomizado.addItem(en);
		}
		
		for (SimNaoEn en : SimNaoEn.values()) {
			cbInativo.addItem(en);
		}
		
		for (ClasseEn en : ClasseEn.values()) {
			cbClasse.addItem(en);
		}
		
		for (VendaTipoVendaEn en : VendaTipoVendaEn.values()) {
			cbTipoVenda.addItem(en);
		}
		
		for (TipoSpedEn en : TipoSpedEn.values()) {
			cbTipoItemSped.addItem(en);
		}
		
		for (IatEn en : IatEn.values()) {
			cbIat.addItem(en);
		}
		
		for (IpptEn en : IpptEn.values()) {
			cbIppt.addItem(en);
		}

	}

	/**
	 * GERAL
	 */

	@AutoGenerated
	private GridLayout bglGeral() {
		// common part: create layout
		glGeral = new GridLayout(6, 3);
		glGeral.setImmediate(false);
		glGeral.setWidth("100.0%");
		glGeral.setHeight("-1px");
		glGeral.setMargin(false);
		glGeral.setSpacing(true);
		glGeral.setRows(3);
		glGeral.setColumns(6);

		mocSubGrupo = new ManyToOneCombo<SubGrupoEntity>();
		mocSubGrupo.setCaption("Subgrupo");
		mocSubGrupo.setRequired(true);
		mocSubGrupo.setImmediate(true);
		mocSubGrupo.setHeight("-1px");
		glGeral.addComponent(mocSubGrupo, 0, 0, 1, 0);

		mocUnidadeProduto = new ManyToOneCombo<UnidadeProdutoEntity>();
		mocUnidadeProduto.setCaption("Unidade do produto");
		mocUnidadeProduto.setRequired(true);
		mocUnidadeProduto.setImmediate(true);
		mocUnidadeProduto.setHeight("-1px");
		glGeral.addComponent(mocUnidadeProduto, 2, 0);

		mocMarca = new ManyToOneCombo<MarcaEntity>();
		mocMarca.setCaption("Marca");
		mocMarca.setRequired(true);
		mocMarca.setImmediate(true);
		mocMarca.setHeight("-1px");
		glGeral.addComponent(mocMarca, 0, 1, 1, 1);

		// mocAlmoxarifado
		mocAlmoxarifado = new ManyToOneCombo<AlmoxarifadoEntity>();
		mocAlmoxarifado.setCaption("Almoxarifado");
		mocAlmoxarifado.setRequired(true);
		mocAlmoxarifado.setImmediate(true);
		mocAlmoxarifado.setHeight("-1px");
		glGeral.addComponent(mocAlmoxarifado, 2, 1);

		// mocGrupoTributario
		mocGrupoTributario = new ManyToOneCombo<GrupoTributarioEntity>();
		mocGrupoTributario.setCaption("Grupo tributário");

		// mocIcmsCustomizado
		mocIcmsCustomizado = new ManyToOneCombo<IcmsCustomizadoCabecalhoEntity>();
		mocIcmsCustomizado.setCaption("ICMS customizado");
		mocIcmsCustomizado.setImmediate(true);

		cbTemIcmsCustomizado = ComponentUtil.buildComboBox("ICMS Customizado ?");
		cbTemIcmsCustomizado.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
 
				SimNaoEn en = (SimNaoEn) event.getProperty().getValue();

				if (en.equals(SimNaoEn.S)) {
					getGlGeral().removeComponent(mocGrupoTributario);
					getGlGeral().addComponent(mocIcmsCustomizado, 4, 2);
				}

				if (en.equals(SimNaoEn.N)) {
					getGlGeral().removeComponent(mocIcmsCustomizado);
					getGlGeral().addComponent(mocGrupoTributario, 4, 2);
				}

			}
		});
		cbTemIcmsCustomizado.setImmediate(true);
		cbTemIcmsCustomizado.setRequired(true);
		cbTemIcmsCustomizado.setHeight("-1px");
		glGeral.addComponent(cbTemIcmsCustomizado, 0, 2);

		mocGrupo = new ManyToOneCombo<GrupoEntity>();
		mocGrupo.setCaption(" Grupo ");
		mocGrupo.setRequired(true);
		mocGrupo.setImmediate(true);
		mocGrupo.setHeight("-1px");
		glGeral.addComponent(mocGrupo, 2, 2,3,2);

		return glGeral;

	}
	
	public void InitCbs(List<String> produto) {

		for (String str : produto) {
			cbTemIcmsCustomizado.addItem(str.toString());
		}
	}


	/**
	 * INFORMAÇÃO GERAL
	 */

	@AutoGenerated
	private VerticalLayout bvlInformacaoGeral() {
		// common part: create layout
		vlInformacaoGeral = new VerticalLayout();
		vlInformacaoGeral.setImmediate(false);
		vlInformacaoGeral.setWidth("100.0%");
		vlInformacaoGeral.setHeight("100.0%");
		vlInformacaoGeral.setMargin(true);
		vlInformacaoGeral.setSpacing(true);
		vlInformacaoGeral.setCaption("Informação Geral");

		//
		vlInformacaoGeral.addComponent(bplInformacaoGeral());

		return vlInformacaoGeral;
	}

	@AutoGenerated
	private Panel bplInformacaoGeral() {
		// common part: create layout
		plInformacaoGeral = new Panel();
		plInformacaoGeral.setImmediate(false);
		plInformacaoGeral.setSizeFull();

		plInformacaoGeral.setContent(bglInformacaoGeral());

		return plInformacaoGeral;
	}

	public GridLayout bglInformacaoGeral() {
		// common part: create layout
		glInformacaoGeral = new GridLayout(7, 7);
		glInformacaoGeral.setImmediate(false);
		glInformacaoGeral.setWidth("100.0%");
		glInformacaoGeral.setHeight("-1px");
		glInformacaoGeral.setMargin(true);
		glInformacaoGeral.setSpacing(true);

		tfGtin = ComponentUtil.buildTextField("GTIN");
		tfGtin.setMaxLength(60);
		// tfGtin.setImmediate(false);
		// tfGtin.setWidth("300px");
		tfGtin.setHeight("-1px");
		glInformacaoGeral.addComponent(tfGtin, 0, 0);

		// txtCodigoInterno
		tfCodigoInterno = ComponentUtil.buildTextField("Código interno");
		tfCodigoInterno.setMaxLength(60);
		// tfCodigoInterno.setImmediate(false);
		// tfCodigoInterno.setWidth("200px");
		tfCodigoInterno.setHeight("-1px");
		glInformacaoGeral.addComponent(tfCodigoInterno, 1, 0);

		mocNcm = new ManyToOneCombo<NcmEntity>();
		mocNcm.setCaption("NCM");
		mocNcm.setRequired(true);
		mocNcm.setImmediate(true);
		// mocNcm.setWidth("250px");
		mocNcm.setHeight("-1px");
		glInformacaoGeral.addComponent(mocNcm, 2, 0,3,0);

		// cbInativo
		cbInativo = ComponentUtil.buildComboBox("Inativo?");
		cbInativo.setImmediate(true);
		// cbInativo.setRequired(true);
		// cbInativo.setWidth("100px");
		cbInativo.setHeight("-1px");
		glInformacaoGeral.addComponent(cbInativo, 4, 0);

		// cbClasse
		cbClasse = ComponentUtil.buildComboBox("Classe");
		cbClasse.setImmediate(true);
		// cbClasse.setRequired(true);
		// cbClasse.setWidth("100px");
		cbClasse.setHeight("-1px");
		glInformacaoGeral.addComponent(cbClasse, 5, 0);

		tfNome = ComponentUtil.buildTextField("Nome");
		// tfNome.setImmediate(true);
		tfNome.setWidth("300px");
		tfNome.setHeight("-1px");
		glInformacaoGeral.addComponent(tfNome, 0, 1);

		// txtDescricaoPdv
		tfDescricaoPdv = ComponentUtil.buildTextField("Descrição do PDV");
		// tfDescricaoPdv.setImmediate(true);
		// tfDescricaoPdv.setWidth("300px");
		tfDescricaoPdv.setHeight("-1px");
		glInformacaoGeral.addComponent(tfDescricaoPdv, 1, 1);

		// // txtDescricao
		tfDescricao = ComponentUtil.buildTextField("Descrição");
		// tfDescricao.setImmediate(true);
		tfDescricao.setWidth("300px");
		tfDescricao.setHeight("-1px");
		glInformacaoGeral.addComponent(tfDescricao, 0, 2);

		return glInformacaoGeral;

	}

	/**
	 * INFORMAÇÃO DE VALORES
	 */

	@AutoGenerated
	private Panel bplInformacaoValor() {
		// common part: create layout
		plInformacaoValor = new Panel();
		plInformacaoValor.setImmediate(false);

		plInformacaoValor.setSizeFull();

		plInformacaoValor.setContent(bglInformacaoValor());

		return plInformacaoValor;
	}

	@AutoGenerated
	private VerticalLayout bvlInformacaoValor() {

		vlInformacaoValor = new VerticalLayout();
		vlInformacaoValor.setImmediate(false);
		vlInformacaoValor.setWidth("100.0%");
		vlInformacaoValor.setHeight("100.0%");
		vlInformacaoValor.setMargin(true);
		vlInformacaoValor.setSpacing(true);
		vlInformacaoValor.setCaption("Informação de Valores");

		//
		vlInformacaoValor.addComponent(bplInformacaoValor());

		return vlInformacaoValor;
	}

	@AutoGenerated
	private GridLayout bglInformacaoValor() {
		// common part: create layout
		glInformacaoValor = new GridLayout(7, 7);
		glInformacaoValor.setImmediate(false);
		glInformacaoValor.setWidth("100.0%");
		glInformacaoValor.setMargin(true);
		glInformacaoValor.setSpacing(true);

		// tfValorCompra
		cfValorCompra = ComponentUtil.buildCurrencyField("Valor de compra");
		// cfValorCompra.setWidth("150px");
		// cfValorCompra.setHeight("-1px");
		glInformacaoValor.addComponent(cfValorCompra, 0, 0);

		// tfValorVenda
		cfValorVenda = ComponentUtil.buildCurrencyField("Valor de venda");
		// cfValorVenda = new NumberField("Valor de venda");
		// cfValorVenda.setWidth("150px");
		// cfValorVenda.setHeight("-1px");
		glInformacaoValor.addComponent(cfValorVenda, 1, 0);

		// tfValorVendaMinimo
		// tfValorVendaMinimo =
		cfValorVendaMinimo = ComponentUtil
				.buildCurrencyField("Valor de venda mínimo");
		// cfValorVendaMinimo = new NumberField("Valor de venda mínimo");
		// cfValorVendaMinimo.setWidth("150px");
		// cfValorVendaMinimo.setHeight("-1px");
		glInformacaoValor.addComponent(cfValorVendaMinimo, 2, 0);

		// tfValorSugerido
		cfValorSugerido = ComponentUtil.buildCurrencyField("Valor sugerido");
		// cfValorSugerido = new NumberField("Valor sugerido");
		// cfValorSugerido.setWidth("150px");
		// cfValorSugerido.setHeight("-1px");
		glInformacaoValor.addComponent(cfValorSugerido, 3, 0);

		// tfCustoMedioLiquido
		// tfCustoMedioLiquido =
		cfCustoMedioLiquido = ComponentUtil
				.buildCurrencyField("Custo médio líquido");
		// cfCustoMedioLiquido = new NumberField("Custo médio líquido");
		// cfCustoMedioLiquido.setWidth("150px");
		// cfCustoMedioLiquido.setHeight("-1px");
		glInformacaoValor.addComponent(cfCustoMedioLiquido, 4, 0);

		// tfPrecoLucroZero
		// tfPrecoLucroZero =
		cfPrecoLucroZero = ComponentUtil
				.buildCurrencyField("Preço de lucro zero");
		// cfPrecoLucroZero = new NumberField("Preço de lucro zero");
		// cfPrecoLucroZero.setWidth("150px");
		// cfPrecoLucroZero.setHeight("-1px");
		glInformacaoValor.addComponent(cfPrecoLucroZero, 0, 1);

		// tfPrecoLucroMinimo
		// tfPrecoLucroMinimo =
		cfPrecoLucroMinimo = ComponentUtil
				.buildCurrencyField("Preço de lucro mínimo");
		// cfPrecoLucroMinimo = new NumberField("Preço de lucro mínimo");
		// cfPrecoLucroMinimo.setWidth("150px");
		// cfPrecoLucroMinimo.setHeight("-1px");
		glInformacaoValor.addComponent(cfPrecoLucroMinimo, 1, 1);

		// tfPrecoLucroMaximo
		// tfPrecoLucroMaximo =
		cfPrecoLucroMaximo = ComponentUtil
				.buildCurrencyField("Preço de lucro máximo");
		// cfPrecoLucroMaximo = new NumberField("Preço de lucro máximo");
		// cfPrecoLucroMaximo.setWidth("150px");
		// cfPrecoLucroMaximo.setHeight("-1px");
		glInformacaoValor.addComponent(cfPrecoLucroMaximo, 2, 1);

		// tfMarkup
		cfMarkup = ComponentUtil.buildCurrencyField("Markup");
		// cfMarkup = new NumberField("Markup");
		// cfMarkup.setWidth("150px");
		// cfMarkup.setHeight("-1px");
		glInformacaoValor.addComponent(cfMarkup, 3, 1);

		// tfQuantidadeEstoque
		// tfQuantidadeEstoque = ComponentUtil
		// .buildNumberField("Quantidade de estoque");
		tfQuantidadeEstoque = new NumberField("Quantidade de estoque");
		// tfQuantidadeEstoque.setWidth("150px");
		// tfQuantidadeEstoque.setHeight("-1px");
		glInformacaoValor.addComponent(tfQuantidadeEstoque, 4, 1);

		// tfQuantidadeEstoqueAnterior
		// tfQuantidadeEstoqueAnterior =
		// ComponentUtil.buildNumberField("Qtde de estoque anterior");
		tfQuantidadeEstoqueAnterior = new NumberField(
				"Qtde de estoque anterior");
		// tfQuantidadeEstoqueAnterior.setWidth("150px");
		// tfQuantidadeEstoqueAnterior.setHeight("-1px");
		// tfQuantidadeEstoqueAnterior.setHeight("-1px");
		glInformacaoValor.addComponent(tfQuantidadeEstoqueAnterior, 0, 2);

		// tfEstoqueIdeal
		// tfEstoqueIdeal = ComponentUtil.buildNumberField("Estoque ideal");
		tfEstoqueIdeal = new NumberField("Estoque ideal");
		// tfEstoqueIdeal.setWidth("150px");
		// tfEstoqueIdeal.setHeight("-1px");
		// tfEstoqueIdeal.setHeight("-1px");
		glInformacaoValor.addComponent(tfEstoqueIdeal, 1, 2);

		// tfEstoqueMinimo
		// tfEstoqueMinimo = ComponentUtil.buildNumberField("Estoque mínimo");
		tfEstoqueMinimo = new NumberField("Estoque mínimo");
		// tfEstoqueMinimo.setWidth("150px");
		// tfEstoqueMinimo.setHeight("-1px");
		glInformacaoValor.addComponent(tfEstoqueMinimo, 2, 2);

		// tfEstoqueMaximo
		// tfEstoqueMaximo = ComponentUtil.buildNumberField("Estoque máximo");
		tfEstoqueMaximo = new NumberField("Estoque máximo");
		// tfEstoqueMaximo.setWidth("150px");
		// tfEstoqueMaximo.setHeight("-1px");
		glInformacaoValor.addComponent(tfEstoqueMaximo, 3, 2);

		return glInformacaoValor;

	}

	/**
	 * INFORMAÇÃO COMPLEMENTAR
	 */

	@AutoGenerated
	private VerticalLayout bvlInformacaoComplementar() {
		// common part: create layout
		vlInformacaoComplementar = new VerticalLayout();
		vlInformacaoComplementar.setImmediate(false);
		vlInformacaoComplementar.setWidth("100.0%");
		vlInformacaoComplementar.setHeight("100.0%");
		vlInformacaoComplementar.setMargin(true);
		vlInformacaoComplementar.setSpacing(true);
		vlInformacaoComplementar.setCaption("Informação complementar");

		//
		vlInformacaoComplementar.addComponent(bplInformacaoComplementar());

		return vlInformacaoComplementar;
	}

	@AutoGenerated
	private Panel bplInformacaoComplementar() {
		// common part: create layout
		plInformacaoComplementar = new Panel();
		plInformacaoComplementar.setImmediate(false);
		plInformacaoComplementar.setSizeFull();

		plInformacaoComplementar.setContent(bglInformacaoComplementar());

		return plInformacaoComplementar;
	}

	@AutoGenerated
	private GridLayout bglInformacaoComplementar() {
		// common part: create layout
		glInformacaoComplementar = new GridLayout(6, 7);
		glInformacaoComplementar.setImmediate(false);
		glInformacaoComplementar.setWidth("100.0%");
		glInformacaoComplementar.setMargin(true);
		glInformacaoComplementar.setSpacing(true);

		tfLst = ComponentUtil.buildTextField("LST");
		tfLst.setRequired(true);
		tfLst.setMaxLength(4);
		glInformacaoComplementar.addComponent(tfLst, 0, 0);

		tfExtipi = ComponentUtil.buildTextField("EXTIPI");
		tfExtipi.setRequired(true);
		tfExtipi.setMaxLength(3);
		glInformacaoComplementar.addComponent(tfExtipi, 1, 0);

		cbTipoVenda = ComponentUtil.buildComboBox("Tipo de venda");
		cbTipoVenda.setRequired(true);
		//cbTipoVenda.setHeight("-1px");
		//cbTipoVenda.setWidth("150px");
		glInformacaoComplementar.addComponent(cbTipoVenda, 2, 0);

		cbIat = ComponentUtil.buildComboBox("IAT");
		cbIat.setRequired(true);
		//cbIat.setHeight("-1px");
		//cbIat.setWidth("150px");
		glInformacaoComplementar.addComponent(cbIat, 3, 0);

		cbIppt = ComponentUtil.buildComboBox("IPPT");
		cbIppt.setRequired(true);
		//cbIppt.setHeight("-1px");
		//cbIppt.setWidth("150px");
		glInformacaoComplementar.addComponent(cbIppt, 4, 0);

		cbTipoItemSped = ComponentUtil.buildComboBox("Tipo do item SPED");
		cbTipoItemSped.setRequired(true);
		//cbTipoItemSped.setHeight("-1px");
		//cbTipoItemSped.setWidth("150px");
		glInformacaoComplementar.addComponent(cbTipoItemSped, 5, 0);

		tfTotalizadorParcial = ComponentUtil.buildCurrencyField("Totalizador parcial");
		//tfTotalizadorParcial.setHeight("-1px");
		//tfTotalizadorParcial.setWidth("150px");
		glInformacaoComplementar.addComponent(tfTotalizadorParcial, 0, 1);

		tfCodigoBalanca = ComponentUtil.buildNumericField("Código da balança");
		tfCodigoBalanca.setRequired(true);
		tfCodigoBalanca.setConverter(new IntegerConverter());
		//tfCodigoBalanca.setHeight("-1px");
		//tfCodigoBalanca.setWidth("150px");
		glInformacaoComplementar.addComponent(tfCodigoBalanca, 1, 1);

		tfPeso = ComponentUtil.buildNumberField("Peso");
		//tfPeso.setHeight("-1px");
		//tfPeso.setWidth("150px");
		glInformacaoComplementar.addComponent(tfPeso, 2, 1);

		tfTaxaComissao = ComponentUtil.buildPercentageField("Taxa da comissão");
		//tfTaxaComissao.setHeight("-1px");
		//tfTaxaComissao.setWidth("150px");
		glInformacaoComplementar.addComponent(tfTaxaComissao, 3, 1);

		tfPontoPedido = ComponentUtil.buildNumberField("Ponto do pedido");
		//tfPontoPedido.setHeight("-1px");
		//tfPontoPedido.setWidth("150px");
		glInformacaoComplementar.addComponent(tfPontoPedido, 0, 2);

		tfLoteEconomicoCompra = ComponentUtil.buildNumberField("Lote econômico de compra");
		//tfLoteEconomicoCompra.setHeight("-1px");
		//tfLoteEconomicoCompra.setWidth("150px");
		glInformacaoComplementar.addComponent(tfLoteEconomicoCompra, 1, 2);

		tfAliquotaIcms = ComponentUtil.buildPercentageField("Alíquota ICMS");
		//tfAliquotaIcms.setHeight("-1px");
		//tfAliquotaIcms.setWidth("150px");
		glInformacaoComplementar.addComponent(tfAliquotaIcms, 2, 2);

		tfAliquotaIssqn = ComponentUtil.buildPercentageField("Alíquota ISSQN");
		//tfAliquotaIssqn.setHeight("-1px");
		//tfAliquotaIssqn.setWidth("150px");
		glInformacaoComplementar.addComponent(tfAliquotaIssqn, 3, 2);

		return glInformacaoComplementar;

	}
	
	/**
	 * 
	 */

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		AbsoluteLayout absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");

		// tabSheet_1
		TabSheet tabSheet_1 = buildTabSheet_1();
		absoluteLayout_2.addComponent(tabSheet_1, "top:-3.0px;left:20.0px;");

		return absoluteLayout_2;
	}

	@AutoGenerated
	private TabSheet buildTabSheet_1() {
		// common part: create layout
		TabSheet tabSheet_1 = new TabSheet();
		tabSheet_1.setImmediate(true);
		tabSheet_1.setWidth("100.0%");
		tabSheet_1.setHeight("100.0%");

		// absoluteLayout_3

		// absoluteLayout_4
		AbsoluteLayout absoluteLayout_4 = buildAbsoluteLayout_4();
		tabSheet_1.addTab(absoluteLayout_4, "Tab", null);

		return tabSheet_1;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_4() {
		// common part: create layout
		AbsoluteLayout absoluteLayout_4 = new AbsoluteLayout();
		absoluteLayout_4.setImmediate(false);
		absoluteLayout_4.setWidth("100.0%");
		absoluteLayout_4.setHeight("100.0%");

		// imgProduto
		ivProduto = new ImageViewer();
		ivProduto.setCaption("Imagem do produto");
		ivProduto.setImmediate(false);
		ivProduto.setWidth("-1px");
		ivProduto.setHeight("130px");
		absoluteLayout_4.addComponent(ivProduto, "top:12.0px;left:9.0px;");

		// txtLst
		tfLst = ComponentUtil.buildTextField("LST");
		//tfLst.setImmediate(false);
		//tfLst.setWidth("40px");
		//tfLst.setHeight("-1px");
		absoluteLayout_4.addComponent(tfLst, "top:12.0px;left:159.0px;");

		// txtExtipi
		tfExtipi = ComponentUtil.buildTextField("EXTIPI");
		//tfExtipi.setImmediate(false);
		//tfExtipi.setWidth("-1px");
		//tfExtipi.setHeight("-1px");
		absoluteLayout_4.addComponent(tfExtipi, "top:12.0px;left:219.0px;");

		// cbTipoVenda
		cbTipoVenda = ComponentUtil.buildComboBox("Tipo de venda");
		//cbTipoVenda.setImmediate(false);
		//cbTipoVenda.setWidth("80px");
		//cbTipoVenda.setHeight("-1px");
		absoluteLayout_4.addComponent(cbTipoVenda, "top:12.0px;left:379.0px;");

		// cbIat
		cbIat = ComponentUtil.buildComboBox("IAT");
		//cbIat.setImmediate(false);
		//cbIat.setWidth("100.0%");
		//cbIat.setHeight("-1px");
		absoluteLayout_4.addComponent(cbIat,"top:12.0px;right:426.0px;left:479.0px;");

		// cbIppt
		cbIppt = ComponentUtil.buildComboBox("IPPT");
		//cbIppt.setImmediate(false);
		//cbIppt.setWidth("106px");
		//cbIppt.setHeight("-1px");
		absoluteLayout_4.addComponent(cbIppt, "top:12.0px;left:599.0px;");

		// cbTipoItemSped
		cbTipoItemSped = ComponentUtil.buildComboBox("Tipo do item SPED");
		//cbTipoItemSped.setImmediate(false);
		//cbTipoItemSped.setWidth("-1px");
		//cbTipoItemSped.setHeight("-1px");
		absoluteLayout_4.addComponent(cbTipoItemSped,"top:12.0px;left:726.0px;");

		// txtTotalizadorParcial
		tfTotalizadorParcial = ComponentUtil.buildTextField("Totalizador parcial");
		//tfTotalizadorParcial.setImmediate(false);
		//tfTotalizadorParcial.setWidth("-1px");
		///tfTotalizadorParcial.setHeight("-1px");
		absoluteLayout_4.addComponent(tfTotalizadorParcial,	"top:54.0px;left:158.0px;");

		// txtCodigoBalanca
		tfCodigoBalanca = ComponentUtil.buildTextField("Código da balança");
		//tfCodigoBalanca.setImmediate(false);
		//tfCodigoBalanca.setWidth("-1px");
		//tfCodigoBalanca.setHeight("-1px");
		absoluteLayout_4.addComponent(tfCodigoBalanca,"top:54.0px;left:319.0px;");

		// txtPeso
		tfPeso = ComponentUtil.buildTextField("Peso");
		//tfPeso.setImmediate(false);
		//tfPeso.setWidth("140px");
		//tfPeso.setHeight("-1px");
		absoluteLayout_4.addComponent(tfPeso, "top:98.0px;left:159.0px;");

		// txtTaxaComissao
		tfTaxaComissao = ComponentUtil.buildTextField("Taxa de comissão");
		//tfTaxaComissao.setImmediate(false);
		//tfTaxaComissao.setWidth("-1px");
		//tfTaxaComissao.setHeight("-1px");
		absoluteLayout_4.addComponent(tfTaxaComissao,"top:98.0px;left:319.0px;");

		// txtPontoPedido
		tfPontoPedido = ComponentUtil.buildTextField("Ponto de pedido");
		//tfPontoPedido.setImmediate(false);
		//tfPontoPedido.setWidth("-1px");
		//tfPontoPedido.setHeight("-1px");
		absoluteLayout_4.addComponent(tfPontoPedido, "top:98.0px;left:479.0px;");

		// txtLoteEconomicoCompra
		tfLoteEconomicoCompra = ComponentUtil.buildTextField("Lote econômico da compra");
		//tfLoteEconomicoCompra.setImmediate(false);
		//tfLoteEconomicoCompra.setWidth("-1px");
		//tfLoteEconomicoCompra.setHeight("-1px");
		absoluteLayout_4.addComponent(tfLoteEconomicoCompra,"top:98.0px;left:639.0px;");

		// txtAliquotaIssqn
		tfAliquotaIssqn = ComponentUtil.buildTextField("Alíquota ISSQN");
		//tfAliquotaIssqn.setImmediate(false);
		//tfAliquotaIssqn.setWidth("-1px");
		//tfAliquotaIssqn.setHeight("-1px");
		absoluteLayout_4.addComponent(tfAliquotaIssqn,"top:139.0px;left:319.0px;");

		// txtAliquotaICms
		tfAliquotaIcms = ComponentUtil.buildTextField("Alíquota ICMS");
		//tfAliquotaIcms.setImmediate(false);
		//tfAliquotaIcms.setWidth("-1px");
		//tfAliquotaIcms.setHeight("-1px");
		absoluteLayout_4.addComponent(tfAliquotaIcms,"top:137.0px;left:158.0px;");

		return absoluteLayout_4;
	}

	public VerticalLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(VerticalLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public TextField getTfAliquotaIcms() {
		return tfAliquotaIcms;
	}

	public void setTfAliquotaIcms(TextField tfAliquotaIcms) {
		this.tfAliquotaIcms = tfAliquotaIcms;
	}

	public TextField getTfAliquotaIssqn() {
		return tfAliquotaIssqn;
	}

	public void setTfAliquotaIssqn(TextField tfAliquotaIssqn) {
		this.tfAliquotaIssqn = tfAliquotaIssqn;
	}

	public TextField getTfLoteEconomicoCompra() {
		return tfLoteEconomicoCompra;
	}

	public void setTfLoteEconomicoCompra(TextField tfLoteEconomicoCompra) {
		this.tfLoteEconomicoCompra = tfLoteEconomicoCompra;
	}

	public TextField getTfPontoPedido() {
		return tfPontoPedido;
	}

	public void setTfPontoPedido(TextField tfPontoPedido) {
		this.tfPontoPedido = tfPontoPedido;
	}

	public TextField getTfTaxaComissao() {
		return tfTaxaComissao;
	}

	public void setTfTaxaComissao(TextField tfTaxaComissao) {
		this.tfTaxaComissao = tfTaxaComissao;
	}

	public TextField getTfPeso() {
		return tfPeso;
	}

	public void setTfPeso(TextField tfPeso) {
		this.tfPeso = tfPeso;
	}

	public TextField getTfCodigoBalanca() {
		return tfCodigoBalanca;
	}

	public void setTfCodigoBalanca(TextField tfCodigoBalanca) {
		this.tfCodigoBalanca = tfCodigoBalanca;
	}

	public TextField getTfTotalizadorParcial() {
		return tfTotalizadorParcial;
	}

	public void setTfTotalizadorParcial(TextField tfTotalizadorParcial) {
		this.tfTotalizadorParcial = tfTotalizadorParcial;
	}

	public TextField getTfExtipi() {
		return tfExtipi;
	}

	public void setTfExtipi(TextField tfExtipi) {
		this.tfExtipi = tfExtipi;
	}

	public TextField getTfLst() {
		return tfLst;
	}

	public void setTfLst(TextField tfLst) {
		this.tfLst = tfLst;
	}

	public TextField getTfEstoqueMaximo() {
		return tfEstoqueMaximo;
	}

	public void setTfEstoqueMaximo(TextField tfEstoqueMaximo) {
		this.tfEstoqueMaximo = tfEstoqueMaximo;
	}

	public TextField getTfEstoqueMinimo() {
		return tfEstoqueMinimo;
	}

	public void setTfEstoqueMinimo(TextField tfEstoqueMinimo) {
		this.tfEstoqueMinimo = tfEstoqueMinimo;
	}

	public TextField getTfEstoqueIdeal() {
		return tfEstoqueIdeal;
	}

	public void setTfEstoqueIdeal(TextField tfEstoqueIdeal) {
		this.tfEstoqueIdeal = tfEstoqueIdeal;
	}

	public TextField getTfQuantidadeEstoqueAnterior() {
		return tfQuantidadeEstoqueAnterior;
	}

	public void setTfQuantidadeEstoqueAnterior(TextField tfQuantidadeEstoqueAnterior) {
		this.tfQuantidadeEstoqueAnterior = tfQuantidadeEstoqueAnterior;
	}

	public TextField getTfQuantidadeEstoque() {
		return tfQuantidadeEstoque;
	}

	public void setTfQuantidadeEstoque(TextField tfQuantidadeEstoque) {
		this.tfQuantidadeEstoque = tfQuantidadeEstoque;
	}

	public TextField getCfMarkup() {
		return cfMarkup;
	}

	public void setCfMarkup(TextField cfMarkup) {
		this.cfMarkup = cfMarkup;
	}

	public TextField getCfPrecoLucroMaximo() {
		return cfPrecoLucroMaximo;
	}

	public void setCfPrecoLucroMaximo(TextField cfPrecoLucroMaximo) {
		this.cfPrecoLucroMaximo = cfPrecoLucroMaximo;
	}

	public TextField getCfPrecoLucroMinimo() {
		return cfPrecoLucroMinimo;
	}

	public void setCfPrecoLucroMinimo(TextField cfPrecoLucroMinimo) {
		this.cfPrecoLucroMinimo = cfPrecoLucroMinimo;
	}

	public TextField getCfPrecoLucroZero() {
		return cfPrecoLucroZero;
	}

	public void setCfPrecoLucroZero(TextField cfPrecoLucroZero) {
		this.cfPrecoLucroZero = cfPrecoLucroZero;
	}

	public TextField getCfCustoMedioLiquido() {
		return cfCustoMedioLiquido;
	}

	public void setCfCustoMedioLiquido(TextField cfCustoMedioLiquido) {
		this.cfCustoMedioLiquido = cfCustoMedioLiquido;
	}

	public TextField getCfValorSugerido() {
		return cfValorSugerido;
	}

	public void setCfValorSugerido(TextField cfValorSugerido) {
		this.cfValorSugerido = cfValorSugerido;
	}

	public TextField getCfValorVendaMinimo() {
		return cfValorVendaMinimo;
	}

	public void setCfValorVendaMinimo(TextField cfValorVendaMinimo) {
		this.cfValorVendaMinimo = cfValorVendaMinimo;
	}

	public TextField getCfValorVenda() {
		return cfValorVenda;
	}

	public void setCfValorVenda(TextField cfValorVenda) {
		this.cfValorVenda = cfValorVenda;
	}

	public TextField getCfValorCompra() {
		return cfValorCompra;
	}

	public void setCfValorCompra(TextField cfValorCompra) {
		this.cfValorCompra = cfValorCompra;
	}

	public TextField getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(TextField tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public TextField getTfDescricaoPdv() {
		return tfDescricaoPdv;
	}

	public void setTfDescricaoPdv(TextField tfDescricaoPdv) {
		this.tfDescricaoPdv = tfDescricaoPdv;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfCodigoInterno() {
		return tfCodigoInterno;
	}

	public void setTfCodigoInterno(TextField tfCodigoInterno) {
		this.tfCodigoInterno = tfCodigoInterno;
	}

	public TextField getTfGtin() {
		return tfGtin;
	}

	public void setTfGtin(TextField tfGtin) {
		this.tfGtin = tfGtin;
	}

	public ComboBox getCbTemIcmsCustomizado() {
		return cbTemIcmsCustomizado;
	}

	public void setCbTemIcmsCustomizado(ComboBox cbTemIcmsCustomizado) {
		this.cbTemIcmsCustomizado = cbTemIcmsCustomizado;
	}

	public ComboBox getCbClasse() {
		return cbClasse;
	}

	public void setCbClasse(ComboBox cbClasse) {
		this.cbClasse = cbClasse;
	}

	public ComboBox getCbInativo() {
		return cbInativo;
	}

	public void setCbInativo(ComboBox cbInativo) {
		this.cbInativo = cbInativo;
	}

	public ComboBox getCbTipoItemSped() {
		return cbTipoItemSped;
	}

	public void setCbTipoItemSped(ComboBox cbTipoItemSped) {
		this.cbTipoItemSped = cbTipoItemSped;
	}

	public ComboBox getCbIppt() {
		return cbIppt;
	}

	public void setCbIppt(ComboBox cbIppt) {
		this.cbIppt = cbIppt;
	}

	public ComboBox getCbIat() {
		return cbIat;
	}

	public void setCbIat(ComboBox cbIat) {
		this.cbIat = cbIat;
	}

	public ComboBox getCbTipoVenda() {
		return cbTipoVenda;
	}

	public void setCbTipoVenda(ComboBox cbTipoVenda) {
		this.cbTipoVenda = cbTipoVenda;
	}

	public ImageViewer getIvProduto() {
		return ivProduto;
	}

	public void setIvProduto(ImageViewer ivProduto) {
		this.ivProduto = ivProduto;
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

	public Panel getPlInformacaoGeral() {
		return plInformacaoGeral;
	}

	public void setPlInformacaoGeral(Panel plInformacaoGeral) {
		this.plInformacaoGeral = plInformacaoGeral;
	}

	public VerticalLayout getVlInformacaoGeral() {
		return vlInformacaoGeral;
	}

	public void setVlInformacaoGeral(VerticalLayout vlInformacaoGeral) {
		this.vlInformacaoGeral = vlInformacaoGeral;
	}

	public GridLayout getGlInformacaoGeral() {
		return glInformacaoGeral;
	}

	public void setGlInformacaoGeral(GridLayout glInformacaoGeral) {
		this.glInformacaoGeral = glInformacaoGeral;
	}

	public VerticalLayout getVlInformacaoValor() {
		return vlInformacaoValor;
	}

	public void setVlInformacaoValor(VerticalLayout vlInformacaoValor) {
		this.vlInformacaoValor = vlInformacaoValor;
	}

	public Panel getPlInformacaoValor() {
		return plInformacaoValor;
	}

	public void setPlInformacaoValor(Panel plInformacaoValor) {
		this.plInformacaoValor = plInformacaoValor;
	}

	public GridLayout getGlInformacaoValor() {
		return glInformacaoValor;
	}

	public void setGlInformacaoValor(GridLayout glInformacaoValor) {
		this.glInformacaoValor = glInformacaoValor;
	}

	public VerticalLayout getVlInformacaoComplementar() {
		return vlInformacaoComplementar;
	}

	public void setVlInformacaoComplementar(VerticalLayout vlInformacaoComplementar) {
		this.vlInformacaoComplementar = vlInformacaoComplementar;
	}

	public Panel getPlInformacaoComplementar() {
		return plInformacaoComplementar;
	}

	public void setPlInformacaoComplementar(Panel plInformacaoComplementar) {
		this.plInformacaoComplementar = plInformacaoComplementar;
	}

	public GridLayout getGlInformacaoComplementar() {
		return glInformacaoComplementar;
	}

	public void setGlInformacaoComplementar(GridLayout glInformacaoComplementar) {
		this.glInformacaoComplementar = glInformacaoComplementar;
	}

	public ManyToOneCombo<SubGrupoEntity> getMocSubGrupo() {
		return mocSubGrupo;
	}

	public void setMocSubGrupo(ManyToOneCombo<SubGrupoEntity> mocSubGrupo) {
		this.mocSubGrupo = mocSubGrupo;
	}

	public ManyToOneCombo<UnidadeProdutoEntity> getMocUnidadeProduto() {
		return mocUnidadeProduto;
	}

	public void setMocUnidadeProduto(
			ManyToOneCombo<UnidadeProdutoEntity> mocUnidadeProduto) {
		this.mocUnidadeProduto = mocUnidadeProduto;
	}

	public ManyToOneCombo<MarcaEntity> getMocMarca() {
		return mocMarca;
	}

	public void setMocMarca(ManyToOneCombo<MarcaEntity> mocMarca) {
		this.mocMarca = mocMarca;
	}

	public ManyToOneCombo<AlmoxarifadoEntity> getMocAlmoxarifado() {
		return mocAlmoxarifado;
	}

	public void setMocAlmoxarifado(
			ManyToOneCombo<AlmoxarifadoEntity> mocAlmoxarifado) {
		this.mocAlmoxarifado = mocAlmoxarifado;
	}

	public ManyToOneCombo<GrupoEntity> getMocGrupo() {
		return mocGrupo;
	}

	public void setMocGrupo(ManyToOneCombo<GrupoEntity> mocGrupo) {
		this.mocGrupo = mocGrupo;
	}

	public ManyToOneCombo<NcmEntity> getMocNcm() {
		return mocNcm;
	}

	public void setMocNcm(ManyToOneCombo<NcmEntity> mocNcm) {
		this.mocNcm = mocNcm;
	}

	public ManyToOneCombo<GrupoTributarioEntity> getMocGrupoTributario() {
		return mocGrupoTributario;
	}

	public void setMocGrupoTributario(
			ManyToOneCombo<GrupoTributarioEntity> mocGrupoTributario) {
		this.mocGrupoTributario = mocGrupoTributario;
	}

	public ManyToOneCombo<IcmsCustomizadoCabecalhoEntity> getMocIcmsCustomizado() {
		return mocIcmsCustomizado;
	}

	public void setMocIcmsCustomizado(
			ManyToOneCombo<IcmsCustomizadoCabecalhoEntity> mocIcmsCustomizado) {
		this.mocIcmsCustomizado = mocIcmsCustomizado;
	}

	public ProdutoFormController getController() {
		return controller;
	}

	public void setController(ProdutoFormController controller) {
		this.controller = controller;
	}
	
}
