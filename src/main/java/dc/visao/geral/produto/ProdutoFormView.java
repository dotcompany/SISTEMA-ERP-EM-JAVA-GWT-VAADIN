package dc.visao.geral.produto;

import org.vaadin.tepi.imageviewer.ImageViewer;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.control.enums.SimNaoEn;
import dc.controller.geral.produto.ProdutoFormController;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoEntity;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class ProdutoFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_3;

	@AutoGenerated
	private AbsoluteLayout absoluteLayout_4;

	@AutoGenerated
	private TextField tfAliquotaIcms;

	@AutoGenerated
	private TextField tfAliquotaIssqn;

	@AutoGenerated
	private TextField tfLoteEconomicoCompra;

	@AutoGenerated
	private TextField tfPontoPedido;

	@AutoGenerated
	private TextField tfTaxaComissao;

	@AutoGenerated
	private TextField tfPeso;

	@AutoGenerated
	private TextField tfCodigoBalanca;

	@AutoGenerated
	private TextField tfTotalizadorParcial;

	@AutoGenerated
	private TextField tfExtipi;

	@AutoGenerated
	private TextField tfLst;

	@AutoGenerated
	private TextField tfEstoqueMaximo;

	@AutoGenerated
	private TextField tfEstoqueMinimo;

	@AutoGenerated
	private TextField tfEstoqueIdeal;

	@AutoGenerated
	private TextField tfQuantidadeEstoqueAnterior;

	@AutoGenerated
	private TextField tfQuantidadeEstoque;

	@AutoGenerated
	private TextField tfMarkup;

	@AutoGenerated
	private TextField tfPrecoLucroMaximo;

	@AutoGenerated
	private TextField tfPrecoLucroMinimo;

	@AutoGenerated
	private TextField tfPrecoLucroZero;

	@AutoGenerated
	private TextField tfCustoMedioLiquido;

	@AutoGenerated
	private TextField tfValorSugerido;

	@AutoGenerated
	private TextField tfValorVendaMinimo;

	@AutoGenerated
	private TextField tfValorVenda;

	@AutoGenerated
	private TextField tfValorCompra;

	@AutoGenerated
	private TextField tfDescricao;

	@AutoGenerated
	private TextField tfDescricaoPdv;

	@AutoGenerated
	private TextField tfNome;

	@AutoGenerated
	private TextField tfCodigoInterno;

	@AutoGenerated
	private TextField tfGtin;

	@AutoGenerated
	private ComboBox cbTemIcmsCustomizado;

	@AutoGenerated
	private ComboBox cbClasse;

	@AutoGenerated
	private ComboBox cbInativo;

	@AutoGenerated
	private ComboBox cbTipoItemSped;

	@AutoGenerated
	private ComboBox cbIppt;

	@AutoGenerated
	private ComboBox cbIat;

	@AutoGenerated
	private ComboBox cbTipoVenda;

	@AutoGenerated
	private ImageViewer ivProduto;

	@AutoGenerated
	private TabSheet tabSheet_1;

	@AutoGenerated
	private TabSheet subForms;

	@AutoGenerated
	private GridLayout fields;

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
	private ManyToOneCombo<IcmsCustomizadoEntity> mocIcmsCustomizado;

	private ProdutoFormController controller;

	public ProdutoFormView(ProdutoFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.controller = controller;
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

	public void setTfQuantidadeEstoqueAnterior(
			TextField tfQuantidadeEstoqueAnterior) {
		this.tfQuantidadeEstoqueAnterior = tfQuantidadeEstoqueAnterior;
	}

	public TextField getTfQuantidadeEstoque() {
		return tfQuantidadeEstoque;
	}

	public void setTfQuantidadeEstoque(TextField tfQuantidadeEstoque) {
		this.tfQuantidadeEstoque = tfQuantidadeEstoque;
	}

	public TextField getTfMarkup() {
		return tfMarkup;
	}

	public void setTfMarkup(TextField tfMarkup) {
		this.tfMarkup = tfMarkup;
	}

	public TextField getTfPrecoLucroMaximo() {
		return tfPrecoLucroMaximo;
	}

	public void setTfPrecoLucroMaximo(TextField tfPrecoLucroMaximo) {
		this.tfPrecoLucroMaximo = tfPrecoLucroMaximo;
	}

	public TextField getTfPrecoLucroMinimo() {
		return tfPrecoLucroMinimo;
	}

	public void setTfPrecoLucroMinimo(TextField tfPrecoLucroMinimo) {
		this.tfPrecoLucroMinimo = tfPrecoLucroMinimo;
	}

	public TextField getTfPrecoLucroZero() {
		return tfPrecoLucroZero;
	}

	public void setTfPrecoLucroZero(TextField tfPrecoLucroZero) {
		this.tfPrecoLucroZero = tfPrecoLucroZero;
	}

	public TextField getTfCustoMedioLiquido() {
		return tfCustoMedioLiquido;
	}

	public void setTfCustoMedioLiquido(TextField tfCustoMedioLiquido) {
		this.tfCustoMedioLiquido = tfCustoMedioLiquido;
	}

	public TextField getTfValorSugerido() {
		return tfValorSugerido;
	}

	public void setTfValorSugerido(TextField tfValorSugerido) {
		this.tfValorSugerido = tfValorSugerido;
	}

	public TextField getTfValorVendaMinimo() {
		return tfValorVendaMinimo;
	}

	public void setTfValorVendaMinimo(TextField tfValorVendaMinimo) {
		this.tfValorVendaMinimo = tfValorVendaMinimo;
	}

	public TextField getTfValorVenda() {
		return tfValorVenda;
	}

	public void setTfValorVenda(TextField tfValorVenda) {
		this.tfValorVenda = tfValorVenda;
	}

	public TextField getTfValorCompra() {
		return tfValorCompra;
	}

	public void setTfValorCompra(TextField tfValorCompra) {
		this.tfValorCompra = tfValorCompra;
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

	public ManyToOneCombo<IcmsCustomizadoEntity> getMocIcmsCustomizado() {
		return mocIcmsCustomizado;
	}

	public void setMocIcmsCustomizado(
			ManyToOneCombo<IcmsCustomizadoEntity> mocIcmsCustomizado) {
		this.mocIcmsCustomizado = mocIcmsCustomizado;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		// mainLayout.setWidth("100%");
		// mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setHeight("100%");
		// setWidth("100%");

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
		// absoluteLayout_2 = buildAbsoluteLayout_2();
		// mainLayout.addComponent(absoluteLayout_2, 0, 2);

		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildFields() {
		fields = new GridLayout(7, 7);
		fields.setImmediate(false);
		fields.setWidth("100.0%");
		fields.setHeight("-1px");
		fields.setMargin(false);
		fields.setSpacing(true);

		mocSubGrupo = new ManyToOneCombo<>();
		mocSubGrupo.setCaption("Subgrupo do produto");
		// mocSubGrupoProduto.setWidth("780px");
		mocSubGrupo.setHeight("-1px");

		// //
		mocUnidadeProduto = new ManyToOneCombo<>();
		mocUnidadeProduto.setCaption("Unidade do produto");
		mocUnidadeProduto.setImmediate(false);
		// mocUnidadeProduto.setWidth("780px");
		mocUnidadeProduto.setHeight("-1px");

		// //
		fields.addComponent(mocSubGrupo, 0, 0);
		fields.addComponent(mocUnidadeProduto, 1, 0);

		// //
		mocMarca = new ManyToOneCombo<>();
		mocMarca.setCaption("Marca do produto");
		// mocMarcaProduto.setImmediate(false);
		// mocMarcaProduto.setWidth("780px");
		mocMarca.setHeight("-1px");

		// //
		// // // mocAlmoxarifado
		mocAlmoxarifado = new ManyToOneCombo<>();
		mocAlmoxarifado.setCaption("Almoxarifado");
		// mocAlmoxarifado.setImmediate(false);
		// mocAlmoxarifado.setWidth("780px");
		mocAlmoxarifado.setHeight("-1px");

		fields.addComponent(mocMarca, 0, 1);
		fields.addComponent(mocAlmoxarifado, 1, 1);

		// //
		// cbIcmsCustomizado
		cbTemIcmsCustomizado = new ComboBox();
		cbTemIcmsCustomizado.setCaption("ICMS Customizado?");
		// cbTemIcmsCustomizado.setImmediate(false);
		// cbTemIcmsCustomizado.setWidth("100px");
		cbTemIcmsCustomizado.setHeight("-1px");

		// carregarIcms();
		cbTemIcmsCustomizado.setValue(SimNaoEn.N);
		cbTemIcmsCustomizado
				.addValueChangeListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						SimNaoEn obj = (SimNaoEn) event.getProperty()
								.getValue();

						if (obj.equals(SimNaoEn.S)) {
							fields.removeComponent(mocGrupoTributario);
							fields.addComponent(mocIcmsCustomizado, 1, 2);
						}

						if (obj.equals(SimNaoEn.N)) {
							fields.removeComponent(mocIcmsCustomizado);
							fields.addComponent(mocGrupoTributario, 1, 2);
						}
					}
				});

		// mocGrupoTributario
		mocGrupoTributario = new ManyToOneCombo<>();
		mocGrupoTributario.setCaption("Grupo tributário");
		// mocGrupoTributario.setImmediate(false);
		// mocGrupoTributario.setWidth("456px");
		// mocGrupoTributario.setHeight("-1px");

		mocGrupo = new ManyToOneCombo<>();
		mocGrupo.setCaption("Grupo do produto");
		// mocGrupoProduto.setWidth("456px");
		mocGrupo.setHeight("-1px");

		fields.addComponent(cbTemIcmsCustomizado, 0, 2);
		fields.addComponent(mocGrupo, 1, 2);
		// ////

		mocIcmsCustomizado = new ManyToOneCombo<>();
		mocIcmsCustomizado.setCaption("ICMS Customizado");
		// mocIcmsCustomizado.setImmediate(false);
		// mocIcmsCustomizado.setWidth("456px");
		// mocIcmsCustomizado.setHeight("-1px");

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

		tfGtin = ComponentUtil.buildTextField("GTIN");
		tfGtin.setMaxLength(60);
		tfGtin.setImmediate(false);
		tfGtin.setWidth("300px");
		tfGtin.setHeight("-1px");

		// txtCodigoInterno
		tfCodigoInterno = ComponentUtil.buildTextField("Código Interno");
		tfCodigoInterno.setMaxLength(60);
		tfCodigoInterno.setImmediate(false);
		tfCodigoInterno.setWidth("200px");
		tfCodigoInterno.setHeight("-1px");

		mocNcm = new ManyToOneCombo<>();
		mocNcm.setCaption("NCM");
		mocNcm.setImmediate(false);
		mocNcm.setWidth("250px");
		mocNcm.setHeight("-1px");

		// cbInativo
		cbInativo = new ComboBox();
		cbInativo.setCaption("Inativo?");
		cbInativo.setImmediate(false);
		cbInativo.setRequired(true);
		cbInativo.setWidth("100px");
		cbInativo.setHeight("-1px");

		// cbClasse
		cbClasse = new ComboBox();
		cbClasse.setCaption("Classe");
		cbClasse.setImmediate(false);
		cbClasse.setRequired(true);
		cbClasse.setWidth("100px");
		cbClasse.setHeight("-1px");

		layout.addComponent(tfGtin, 0, 0);
		layout.addComponent(tfCodigoInterno, 1, 0);
		layout.addComponent(mocNcm, 2, 0);
		layout.addComponent(cbInativo, 3, 0);
		layout.addComponent(cbClasse, 4, 0);

		tfNome = ComponentUtil.buildTextField("Nome");
		tfNome.setImmediate(false);
		tfNome.setWidth("300px");
		tfNome.setHeight("-1px");

		// txtDescricaoPdv
		tfDescricaoPdv = ComponentUtil.buildTextField("Descrição PDV");
		tfDescricaoPdv.setImmediate(false);
		tfDescricaoPdv.setWidth("300px");
		tfDescricaoPdv.setHeight("-1px");

		// // txtDescricao
		tfDescricao = ComponentUtil.buildTextField("Descrição");
		tfDescricao.setImmediate(false);
		tfDescricao.setWidth("300px");
		tfDescricao.setHeight("-1px");
		//
		layout.addComponent(tfNome, 0, 1);
		layout.addComponent(tfDescricaoPdv, 1, 1);
		layout.addComponent(tfDescricao, 0, 2);

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

		tfValorCompra = ComponentUtil.buildCurrencyField("Valor de compra");
		tfValorCompra.setHeight("-1px");
		tfValorCompra.setWidth("150px");

		tfValorVenda = ComponentUtil.buildCurrencyField("Valor de venda");
		tfValorVenda.setHeight("-1px");
		tfValorVenda.setWidth("150px");

		tfValorVendaMinimo = ComponentUtil
				.buildCurrencyField("Valor de venda mínimo");
		tfValorVendaMinimo.setHeight("-1px");
		tfValorVendaMinimo.setWidth("150px");

		tfValorSugerido = ComponentUtil.buildCurrencyField("Valor sugerido");
		tfValorSugerido.setHeight("-1px");
		tfValorSugerido.setWidth("150px");

		tfCustoMedioLiquido = ComponentUtil
				.buildCurrencyField("Custo médio líquido");
		tfCustoMedioLiquido.setHeight("-1px");
		tfCustoMedioLiquido.setWidth("150px");

		layout.addComponent(tfValorCompra, 0, 0);
		layout.addComponent(tfValorVenda, 1, 0);
		layout.addComponent(tfValorVendaMinimo, 2, 0);
		layout.addComponent(tfValorSugerido, 3, 0);
		layout.addComponent(tfCustoMedioLiquido, 4, 0);

		tfPrecoLucroZero = ComponentUtil
				.buildCurrencyField("Preço de lucro zero");
		tfPrecoLucroZero.setHeight("-1px");
		tfPrecoLucroZero.setWidth("150px");

		tfPrecoLucroMinimo = ComponentUtil
				.buildCurrencyField("Preço de lucro mínimo");
		tfPrecoLucroMinimo.setHeight("-1px");
		tfPrecoLucroMinimo.setWidth("150px");

		tfPrecoLucroMaximo = ComponentUtil
				.buildCurrencyField("Preço de lucro máximo");
		tfPrecoLucroMaximo.setHeight("-1px");
		tfPrecoLucroMaximo.setWidth("150px");

		tfMarkup = ComponentUtil.buildCurrencyField("Markup");
		tfMarkup.setHeight("-1px");
		tfMarkup.setWidth("150px");

		tfQuantidadeEstoque = ComponentUtil
				.buildNumberField("Quantidade de estoque");
		tfQuantidadeEstoque.setHeight("-1px");
		tfQuantidadeEstoque.setWidth("150px");

		layout.addComponent(tfPrecoLucroZero, 0, 1);
		layout.addComponent(tfPrecoLucroMinimo, 1, 1);
		layout.addComponent(tfPrecoLucroMaximo, 2, 1);
		layout.addComponent(tfMarkup, 3, 1);
		layout.addComponent(tfQuantidadeEstoque, 4, 1);

		tfQuantidadeEstoqueAnterior = ComponentUtil
				.buildNumberField("Qtde de estoque anterior");
		tfQuantidadeEstoqueAnterior.setHeight("-1px");
		tfQuantidadeEstoqueAnterior.setHeight("-1px");
		tfQuantidadeEstoqueAnterior.setWidth("150px");

		tfEstoqueIdeal = ComponentUtil.buildNumberField("Estoque ideal");
		tfEstoqueIdeal.setHeight("-1px");
		tfEstoqueIdeal.setHeight("-1px");
		tfEstoqueIdeal.setWidth("150px");

		tfEstoqueMinimo = ComponentUtil.buildNumberField("Estoque mínimo");
		tfEstoqueMinimo.setHeight("-1px");
		tfEstoqueMinimo.setHeight("-1px");
		tfEstoqueMinimo.setWidth("150px");

		tfEstoqueMaximo = ComponentUtil.buildNumberField("Estoque máximo");
		tfEstoqueMaximo.setHeight("-1px");
		tfEstoqueMaximo.setHeight("-1px");
		tfEstoqueMaximo.setWidth("150px");

		layout.addComponent(tfQuantidadeEstoqueAnterior, 0, 2);
		layout.addComponent(tfEstoqueIdeal, 1, 2);
		layout.addComponent(tfEstoqueMinimo, 2, 2);
		layout.addComponent(tfEstoqueMaximo, 3, 2);

		subForms.addTab(layout, "Valores principais", null);
	}

	public void buildComplementarSubForm() {
		GridLayout layout = new GridLayout(6, 6);
		layout.setImmediate(false);
		layout.setWidth("100.0%");
		layout.setHeight("100.0%");
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();

		tfLst = new TextField("LST:");
		tfLst.setRequired(true);
		tfLst.setMaxLength(4);

		tfExtipi = new TextField("EXTIPI");
		tfExtipi.setRequired(true);
		tfExtipi.setMaxLength(3);

		cbTipoVenda = ComponentUtil.buildComboBox("Tipo de venda");
		cbTipoVenda.setRequired(true);
		cbTipoVenda.setHeight("-1px");
		cbTipoVenda.setWidth("150px");

		cbIat = ComponentUtil.buildComboBox("IAT");
		cbIat.setRequired(true);
		cbIat.setHeight("-1px");
		cbIat.setWidth("150px");

		layout.addComponent(tfLst, 0, 0);
		layout.addComponent(tfExtipi, 1, 0);

		cbIppt = ComponentUtil.buildComboBox("IPPT");
		cbIppt.setRequired(true);
		cbIppt.setHeight("-1px");
		cbIppt.setWidth("150px");

		cbTipoItemSped = ComponentUtil.buildComboBox("Tipo do item SPED");
		cbTipoItemSped.setRequired(true);
		cbTipoItemSped.setHeight("-1px");
		cbTipoItemSped.setWidth("150px");

		layout.addComponent(cbTipoVenda, 2, 0);
		layout.addComponent(cbIat, 3, 0);
		layout.addComponent(cbIppt, 4, 0);
		layout.addComponent(cbTipoItemSped, 5, 0);

		tfTotalizadorParcial = ComponentUtil
				.buildCurrencyField("Totalizador parcial");
		tfTotalizadorParcial.setHeight("-1px");
		tfTotalizadorParcial.setWidth("150px");

		tfCodigoBalanca = ComponentUtil.buildNumericField("Código da balança");
		tfCodigoBalanca.setRequired(true);
		tfCodigoBalanca.setConverter(new IntegerConverter());
		tfCodigoBalanca.setHeight("-1px");
		tfCodigoBalanca.setWidth("150px");

		tfPeso = ComponentUtil.buildNumberField("Peso");
		tfPeso.setHeight("-1px");
		tfPeso.setWidth("150px");

		tfTaxaComissao = ComponentUtil.buildPercentageField("Taxa da comissão");
		tfTaxaComissao.setHeight("-1px");
		tfTaxaComissao.setWidth("150px");

		layout.addComponent(tfTotalizadorParcial, 0, 1);
		layout.addComponent(tfCodigoBalanca, 1, 1);
		layout.addComponent(tfPeso, 2, 1);
		layout.addComponent(tfTaxaComissao, 3, 1);

		tfPontoPedido = ComponentUtil.buildNumberField("Ponto do pedido");
		tfPontoPedido.setHeight("-1px");
		tfPontoPedido.setWidth("150px");

		tfLoteEconomicoCompra = ComponentUtil
				.buildNumberField("Lote econômico de compra");
		tfLoteEconomicoCompra.setHeight("-1px");
		tfLoteEconomicoCompra.setWidth("150px");

		tfAliquotaIcms = ComponentUtil.buildPercentageField("Alíquota ICMS");
		tfAliquotaIcms.setHeight("-1px");
		tfAliquotaIcms.setWidth("150px");

		tfAliquotaIssqn = ComponentUtil.buildPercentageField("Alíquota ISSQN");
		tfAliquotaIssqn.setHeight("-1px");
		tfAliquotaIssqn.setWidth("150px");

		layout.addComponent(tfPontoPedido, 0, 2);
		layout.addComponent(tfLoteEconomicoCompra, 1, 2);
		layout.addComponent(tfAliquotaIcms, 2, 2);
		layout.addComponent(tfAliquotaIssqn, 3, 2);

		subForms.addTab(layout, "Dados complementares", null);
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_4() {
		// common part: create layout
		absoluteLayout_4 = new AbsoluteLayout();
		absoluteLayout_4.setImmediate(false);
		absoluteLayout_4.setWidth("100.0%");
		absoluteLayout_4.setHeight("100.0%");

		// imgProduto
		ivProduto = new ImageViewer();
		ivProduto.setCaption("Imagem do Produto");
		ivProduto.setImmediate(false);
		ivProduto.setWidth("-1px");
		ivProduto.setHeight("130px");
		absoluteLayout_4.addComponent(ivProduto, "top:12.0px;left:9.0px;");

		// txtLst
		tfLst = new TextField();
		tfLst.setCaption("LST");
		tfLst.setImmediate(false);
		tfLst.setWidth("40px");
		tfLst.setHeight("-1px");
		absoluteLayout_4.addComponent(tfLst, "top:12.0px;left:159.0px;");

		// txtExtipi
		tfExtipi = new TextField();
		tfExtipi.setCaption("EXTIPI");
		tfExtipi.setImmediate(false);
		tfExtipi.setWidth("-1px");
		tfExtipi.setHeight("-1px");
		absoluteLayout_4.addComponent(tfExtipi, "top:12.0px;left:219.0px;");

		// cbTipoVenda
		cbTipoVenda = new ComboBox();
		cbTipoVenda.setCaption("Tipo venda");
		cbTipoVenda.setImmediate(false);
		cbTipoVenda.setWidth("80px");
		cbTipoVenda.setHeight("-1px");
		absoluteLayout_4.addComponent(cbTipoVenda, "top:12.0px;left:379.0px;");

		// cbIat
		cbIat = new ComboBox();
		cbIat.setCaption("IAT");
		cbIat.setImmediate(false);
		cbIat.setWidth("100.0%");
		cbIat.setHeight("-1px");
		absoluteLayout_4.addComponent(cbIat,
				"top:12.0px;right:426.0px;left:479.0px;");

		// cbIppt
		cbIppt = new ComboBox();
		cbIppt.setCaption("IPPT");
		cbIppt.setImmediate(false);
		cbIppt.setWidth("106px");
		cbIppt.setHeight("-1px");
		absoluteLayout_4.addComponent(cbIppt, "top:12.0px;left:599.0px;");

		// cbTipoItemSped
		cbTipoItemSped = new ComboBox();
		cbTipoItemSped.setCaption("Tipo Item Sped");
		cbTipoItemSped.setImmediate(false);
		cbTipoItemSped.setWidth("-1px");
		cbTipoItemSped.setHeight("-1px");
		absoluteLayout_4.addComponent(cbTipoItemSped,
				"top:12.0px;left:726.0px;");

		// txtTotalizadorParcial
		tfTotalizadorParcial = new TextField();
		tfTotalizadorParcial.setCaption("Totalizador Parcial");
		tfTotalizadorParcial.setImmediate(false);
		tfTotalizadorParcial.setWidth("-1px");
		tfTotalizadorParcial.setHeight("-1px");
		absoluteLayout_4.addComponent(tfTotalizadorParcial,
				"top:54.0px;left:158.0px;");

		// txtCodigoBalanca
		tfCodigoBalanca = new TextField();
		tfCodigoBalanca.setCaption("Código Balança");
		tfCodigoBalanca.setImmediate(false);
		tfCodigoBalanca.setWidth("-1px");
		tfCodigoBalanca.setHeight("-1px");
		absoluteLayout_4.addComponent(tfCodigoBalanca,
				"top:54.0px;left:319.0px;");

		// txtPeso
		tfPeso = new TextField();
		tfPeso.setCaption("Peso");
		tfPeso.setImmediate(false);
		tfPeso.setWidth("140px");
		tfPeso.setHeight("-1px");
		absoluteLayout_4.addComponent(tfPeso, "top:98.0px;left:159.0px;");

		// txtTaxaComissao
		tfTaxaComissao = new TextField();
		tfTaxaComissao.setCaption("Taxa Comissão");
		tfTaxaComissao.setImmediate(false);
		tfTaxaComissao.setWidth("-1px");
		tfTaxaComissao.setHeight("-1px");
		absoluteLayout_4.addComponent(tfTaxaComissao,
				"top:98.0px;left:319.0px;");

		// txtPontoPedido
		tfPontoPedido = new TextField();
		tfPontoPedido.setCaption("Ponto Pedido");
		tfPontoPedido.setImmediate(false);
		tfPontoPedido.setWidth("-1px");
		tfPontoPedido.setHeight("-1px");
		absoluteLayout_4
				.addComponent(tfPontoPedido, "top:98.0px;left:479.0px;");

		// txtLoteEconomicoCompra
		tfLoteEconomicoCompra = new TextField();
		tfLoteEconomicoCompra.setCaption("Lote Econômico Compra");
		tfLoteEconomicoCompra.setImmediate(false);
		tfLoteEconomicoCompra.setWidth("-1px");
		tfLoteEconomicoCompra.setHeight("-1px");
		absoluteLayout_4.addComponent(tfLoteEconomicoCompra,
				"top:98.0px;left:639.0px;");

		// txtAliquotaIssqn
		tfAliquotaIssqn = new TextField();
		tfAliquotaIssqn.setCaption("Alíquota ISSQN");
		tfAliquotaIssqn.setImmediate(false);
		tfAliquotaIssqn.setWidth("-1px");
		tfAliquotaIssqn.setHeight("-1px");
		absoluteLayout_4.addComponent(tfAliquotaIssqn,
				"top:139.0px;left:319.0px;");

		// txtAliquotaICms
		tfAliquotaIcms = new TextField();
		tfAliquotaIcms.setCaption("Alíquota ICMS");
		tfAliquotaIcms.setImmediate(false);
		tfAliquotaIcms.setWidth("-1px");
		tfAliquotaIcms.setHeight("-1px");
		absoluteLayout_4.addComponent(tfAliquotaIcms,
				"top:137.0px;left:158.0px;");

		return absoluteLayout_4;
	}

}