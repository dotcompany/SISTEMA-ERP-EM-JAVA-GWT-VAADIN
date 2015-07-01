package dc.visao.patrimonio;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.patrimonio.BemFormController;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.entidade.patrimonio.GrupoBemEntity;
import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.visao.framework.util.ComponentUtil;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

public class BemFormView extends CustomComponent {

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel panel_1;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private GridLayout gridLayout_1;

	@AutoGenerated
	private ComboBox cbColaborador;

	@AutoGenerated
	private ComboBox cbFornecedor;

	@AutoGenerated
	private ComboBox cbSetor;

	@AutoGenerated
	private ComboBox cbGrupoBem;

	@AutoGenerated
	private ComboBox cbEstadoConservacao;

	@AutoGenerated
	private ComboBox cbTipoAquisicao;

	@AutoGenerated
	private TextField tfFuncao;

	@AutoGenerated
	private TextField tfTaxaDepreciacaoIncentivada;

	@AutoGenerated
	private TextField tfTaxaDepreciacaoAcelerada;

	@AutoGenerated
	private TextField tfTaxaMensalDepreciacao;

	@AutoGenerated
	private TextField tfTaxaAnualDepreciacao;

	@AutoGenerated
	private TextField tfTipoDepreciacao;

	@AutoGenerated
	private PopupDateField pdfUltimaDepreciacao;

	@AutoGenerated
	private PopupDateField pdfInicioDepreciacao;

	@AutoGenerated
	private TextField tfMetodoDepreciacao;

	@AutoGenerated
	private TextField tfDeprecia;

	@AutoGenerated
	private TextField tfValorBaixa;

	@AutoGenerated
	private TextField tfValorAtualizado;

	@AutoGenerated
	private TextField tfValorCompra;

	@AutoGenerated
	private TextField tfValorOriginal;

	@AutoGenerated
	private TextField tfChaveNfe;

	@AutoGenerated
	private TextField tfNumeroNotaFiscal;

	@AutoGenerated
	private PopupDateField pdfVencimentoGarantia;

	@AutoGenerated
	private PopupDateField pdfDataBaixa;

	@AutoGenerated
	private PopupDateField pdfDataMarcacao;

	@AutoGenerated
	private PopupDateField pdfDataVistoria;

	@AutoGenerated
	private PopupDateField pdfDataContabilizado;

	@AutoGenerated
	private PopupDateField pdfDataCadastro;

	@AutoGenerated
	private PopupDateField pdfDataAceite;

	@AutoGenerated
	private PopupDateField pdfDataAquisicao;

	@AutoGenerated
	private TextField tfNumeroSerie;

	@AutoGenerated
	private TextField tfNome;

	@AutoGenerated
	private TextField tfDescricao;

	@AutoGenerated
	private TextField tfNumeroNb;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private BemFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public BemFormView(final BemFormController controller) {
		this.controller = controller;

		buildMainLayout();
		setCompositionRoot(this.mainLayout);
	}

	public TextField getTfNumeroNb() {
		return tfNumeroNb;
	}

	public void setTfNumeroNb(TextField tfNumeroNb) {
		this.tfNumeroNb = tfNumeroNb;
	}

	public TextField getTfNome() {
		return tfNome;
	}

	public void setTfNome(TextField tfNome) {
		this.tfNome = tfNome;
	}

	public TextField getTfDescricao() {
		return tfDescricao;
	}

	public void setTfDescricao(TextField tfDescricao) {
		this.tfDescricao = tfDescricao;
	}

	public TextField getTfNumeroSerie() {
		return tfNumeroSerie;
	}

	public void setTfNumeroSerie(TextField tfNumeroSerie) {
		this.tfNumeroSerie = tfNumeroSerie;
	}

	public PopupDateField getPdfDataAquisicao() {
		return pdfDataAquisicao;
	}

	public void setPdfDataAquisicao(PopupDateField pdfDataAquisicao) {
		this.pdfDataAquisicao = pdfDataAquisicao;
	}

	public PopupDateField getPdfDataAceite() {
		return pdfDataAceite;
	}

	public void setPdfDataAceite(PopupDateField pdfDataAceite) {
		this.pdfDataAceite = pdfDataAceite;
	}

	public PopupDateField getPdfDataCadastro() {
		return pdfDataCadastro;
	}

	public void setPdfDataCadastro(PopupDateField pdfDataCadastro) {
		this.pdfDataCadastro = pdfDataCadastro;
	}

	public PopupDateField getPdfDataContabilizado() {
		return pdfDataContabilizado;
	}

	public void setPdfDataContabilizado(PopupDateField pdfDataContabilizado) {
		this.pdfDataContabilizado = pdfDataContabilizado;
	}

	public PopupDateField getPdfDataVistoria() {
		return pdfDataVistoria;
	}

	public void setPdfDataVistoria(PopupDateField pdfDataVistoria) {
		this.pdfDataVistoria = pdfDataVistoria;
	}

	public PopupDateField getPdfDataMarcacao() {
		return pdfDataMarcacao;
	}

	public void setPdfDataMarcacao(PopupDateField pdfDataMarcacao) {
		this.pdfDataMarcacao = pdfDataMarcacao;
	}

	public PopupDateField getPdfDataBaixa() {
		return pdfDataBaixa;
	}

	public void setPdfDataBaixa(PopupDateField pdfDataBaixa) {
		this.pdfDataBaixa = pdfDataBaixa;
	}

	public PopupDateField getPdfVencimentoGarantia() {
		return pdfVencimentoGarantia;
	}

	public void setPdfVencimentoGarantia(PopupDateField pdfVencimentoGarantia) {
		this.pdfVencimentoGarantia = pdfVencimentoGarantia;
	}

	public TextField getTfNumeroNotaFiscal() {
		return tfNumeroNotaFiscal;
	}

	public void setTfNumeroNotaFiscal(TextField tfNumeroNotaFiscal) {
		this.tfNumeroNotaFiscal = tfNumeroNotaFiscal;
	}

	public TextField getTfChaveNfe() {
		return tfChaveNfe;
	}

	public void setTfChaveNfe(TextField tfChaveNfe) {
		this.tfChaveNfe = tfChaveNfe;
	}

	public TextField getTfValorOriginal() {
		return tfValorOriginal;
	}

	public void setTfValorOriginal(TextField tfValorOriginal) {
		this.tfValorOriginal = tfValorOriginal;
	}

	public TextField getTfValorCompra() {
		return tfValorCompra;
	}

	public void setTfValorCompra(TextField tfValorCompra) {
		this.tfValorCompra = tfValorCompra;
	}

	public TextField getTfValorAtualizado() {
		return tfValorAtualizado;
	}

	public void setTfValorAtualizado(TextField tfValorAtualizado) {
		this.tfValorAtualizado = tfValorAtualizado;
	}

	public TextField getTfValorBaixa() {
		return tfValorBaixa;
	}

	public void setTfValorBaixa(TextField tfValorBaixa) {
		this.tfValorBaixa = tfValorBaixa;
	}

	public TextField getTfDeprecia() {
		return tfDeprecia;
	}

	public void setTfDeprecia(TextField tfDeprecia) {
		this.tfDeprecia = tfDeprecia;
	}

	public TextField getTfMetodoDepreciacao() {
		return tfMetodoDepreciacao;
	}

	public void setTfMetodoDepreciacao(TextField tfMetodoDepreciacao) {
		this.tfMetodoDepreciacao = tfMetodoDepreciacao;
	}

	public PopupDateField getPdfInicioDepreciacao() {
		return pdfInicioDepreciacao;
	}

	public void setPdfInicioDepreciacao(PopupDateField pdfInicioDepreciacao) {
		this.pdfInicioDepreciacao = pdfInicioDepreciacao;
	}

	public PopupDateField getPdfUltimaDepreciacao() {
		return pdfUltimaDepreciacao;
	}

	public void setPdfUltimaDepreciacao(PopupDateField pdfUltimaDepreciacao) {
		this.pdfUltimaDepreciacao = pdfUltimaDepreciacao;
	}

	public TextField getTfTipoDepreciacao() {
		return tfTipoDepreciacao;
	}

	public void setTfTipoDepreciacao(TextField tfTipoDepreciacao) {
		this.tfTipoDepreciacao = tfTipoDepreciacao;
	}

	public TextField getTfTaxaAnualDepreciacao() {
		return tfTaxaAnualDepreciacao;
	}

	public void setTfTaxaAnualDepreciacao(TextField tfTaxaAnualDepreciacao) {
		this.tfTaxaAnualDepreciacao = tfTaxaAnualDepreciacao;
	}

	public TextField getTfTaxaMensalDepreciacao() {
		return tfTaxaMensalDepreciacao;
	}

	public void setTfTaxaMensalDepreciacao(TextField tfTaxaMensalDepreciacao) {
		this.tfTaxaMensalDepreciacao = tfTaxaMensalDepreciacao;
	}

	public TextField getTfTaxaDepreciacaoAcelerada() {
		return tfTaxaDepreciacaoAcelerada;
	}

	public void setTfTaxaDepreciacaoAcelerada(
			TextField tfTaxaDepreciacaoAcelerada) {
		this.tfTaxaDepreciacaoAcelerada = tfTaxaDepreciacaoAcelerada;
	}

	public TextField getTfTaxaDepreciacaoIncentivada() {
		return tfTaxaDepreciacaoIncentivada;
	}

	public void setTfTaxaDepreciacaoIncentivada(
			TextField tfTaxaDepreciacaoIncentivada) {
		this.tfTaxaDepreciacaoIncentivada = tfTaxaDepreciacaoIncentivada;
	}

	public TextField getTfFuncao() {
		return tfFuncao;
	}

	public void setTfFuncao(TextField tfFuncao) {
		this.tfFuncao = tfFuncao;
	}

	public ComboBox getCbTipoAquisicao() {
		return cbTipoAquisicao;
	}

	public void setCbTipoAquisicao(ComboBox cbTipoAquisicao) {
		this.cbTipoAquisicao = cbTipoAquisicao;
	}

	public ComboBox getCbEstadoConservacao() {
		return cbEstadoConservacao;
	}

	public void setCbEstadoConservacao(ComboBox cbEstadoConservacao) {
		this.cbEstadoConservacao = cbEstadoConservacao;
	}

	public ComboBox getCbGrupoBem() {
		return cbGrupoBem;
	}

	public void setCbGrupoBem(ComboBox cbGrupoBem) {
		this.cbGrupoBem = cbGrupoBem;
	}

	public ComboBox getCbSetor() {
		return cbSetor;
	}

	public void setCbSetor(ComboBox cbSetor) {
		this.cbSetor = cbSetor;
	}

	public ComboBox getCbFornecedor() {
		return cbFornecedor;
	}

	public void setCbFornecedor(ComboBox cbFornecedor) {
		this.cbFornecedor = cbFornecedor;
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
		gridLayout_1.setColumns(3);

		// tfNumeroNb
		tfNumeroNb = new TextField();
		tfNumeroNb.setCaption("Número:");
		tfNumeroNb.setImmediate(false);
		tfNumeroNb.setWidth("160px");
		tfNumeroNb.setHeight("-1px");
		tfNumeroNb.setSizeFull();
		tfNumeroNb.setNullRepresentation("");
		gridLayout_1.addComponent(tfNumeroNb, 0, 1);

		// tfDescricao
		tfDescricao = new TextField();
		tfDescricao.setCaption("Descrição:");
		tfDescricao.setImmediate(false);
		tfDescricao.setWidth("160px");
		tfDescricao.setHeight("-1px");
		tfDescricao.setSizeFull();
		tfDescricao.setNullRepresentation("");
		gridLayout_1.addComponent(tfDescricao, 1, 1);

		// tfNome
		tfNome = new TextField();
		tfNome.setCaption("Nome:");
		tfNome.setImmediate(false);
		tfNome.setWidth("160px");
		tfNome.setHeight("-1px");
		tfNome.setSizeFull();
		tfNome.setNullRepresentation("");
		gridLayout_1.addComponent(tfNome, 2, 1);

		// tfNumeroSerie
		tfNumeroSerie = new TextField();
		tfNumeroSerie.setCaption("Número da série:");
		tfNumeroSerie.setImmediate(false);
		tfNumeroSerie.setWidth("160px");
		tfNumeroSerie.setHeight("-1px");
		tfNumeroSerie.setSizeFull();
		tfNumeroSerie.setNullRepresentation("");
		gridLayout_1.addComponent(tfNumeroSerie, 0, 2);

		// pdfDataAquisicao
		pdfDataAquisicao = new PopupDateField();
		pdfDataAquisicao.setCaption("Data da aquisição:");
		pdfDataAquisicao.setImmediate(false);
		pdfDataAquisicao.setWidth("160px");
		pdfDataAquisicao.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataAquisicao, 1, 2);

		// pdfDataAceite
		pdfDataAceite = new PopupDateField();
		pdfDataAceite.setCaption("Data do aceite:");
		pdfDataAceite.setImmediate(false);
		pdfDataAceite.setWidth("160px");
		pdfDataAceite.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataAceite, 2, 2);

		// pdfDataCadastro
		pdfDataCadastro = new PopupDateField();
		pdfDataCadastro.setCaption("Data de cadastro:");
		pdfDataCadastro.setImmediate(false);
		pdfDataCadastro.setWidth("160px");
		pdfDataCadastro.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataCadastro, 0, 3);

		// pdfDataContabilizado
		pdfDataContabilizado = new PopupDateField();
		pdfDataContabilizado.setCaption("Data contabilizado:");
		pdfDataContabilizado.setImmediate(false);
		pdfDataContabilizado.setWidth("160px");
		pdfDataContabilizado.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataContabilizado, 1, 3);

		// pdfDataVistoria
		pdfDataVistoria = new PopupDateField();
		pdfDataVistoria.setCaption("Data da vistoria:");
		pdfDataVistoria.setImmediate(false);
		pdfDataVistoria.setWidth("160px");
		pdfDataVistoria.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataVistoria, 2, 3);

		// pdfDataMarcacao
		pdfDataMarcacao = new PopupDateField();
		pdfDataMarcacao.setCaption("Data de marcação:");
		pdfDataMarcacao.setImmediate(false);
		pdfDataMarcacao.setWidth("160px");
		pdfDataMarcacao.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataMarcacao, 0, 4);

		// pdfDataBaixa
		pdfDataBaixa = new PopupDateField();
		pdfDataBaixa.setCaption("Data da baixa:");
		pdfDataBaixa.setImmediate(false);
		pdfDataBaixa.setWidth("160px");
		pdfDataBaixa.setHeight("-1px");
		gridLayout_1.addComponent(pdfDataBaixa, 1, 4);

		// pdfVencimentoGarantia
		pdfVencimentoGarantia = new PopupDateField();
		pdfVencimentoGarantia.setCaption("Data do vencimento da garantia:");
		pdfVencimentoGarantia.setImmediate(false);
		pdfVencimentoGarantia.setWidth("160px");
		pdfVencimentoGarantia.setHeight("-1px");
		gridLayout_1.addComponent(pdfVencimentoGarantia, 2, 4);

		// tfNumeroNotaFiscal
		tfNumeroNotaFiscal = new TextField();
		tfNumeroNotaFiscal.setCaption("Número da nota fiscal:");
		tfNumeroNotaFiscal.setImmediate(false);
		tfNumeroNotaFiscal.setWidth("160px");
		tfNumeroNotaFiscal.setHeight("-1px");
		tfNumeroNotaFiscal.setSizeFull();
		tfNumeroNotaFiscal.setNullRepresentation("");
		gridLayout_1.addComponent(tfNumeroNotaFiscal, 0, 5);

		// tfChaveNfe
		tfChaveNfe = new TextField();
		tfChaveNfe.setCaption("Chave NFE:");
		tfChaveNfe.setImmediate(false);
		tfChaveNfe.setWidth("160px");
		tfChaveNfe.setHeight("-1px");
		tfChaveNfe.setSizeFull();
		tfChaveNfe.setNullRepresentation("");
		gridLayout_1.addComponent(tfChaveNfe, 1, 5);

		// tfValorOriginal
		tfValorOriginal = ComponentUtil.buildCurrencyField("Valor original:");
		tfValorOriginal.setImmediate(false);
		tfValorOriginal.setWidth("160px");
		tfValorOriginal.setHeight("-1px");
		tfValorOriginal.setSizeFull();
		tfValorOriginal.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorOriginal, 2, 5);

		// tfValorCompra
		tfValorCompra = ComponentUtil.buildCurrencyField("Valor da compra:");
		tfValorCompra.setImmediate(false);
		tfValorCompra.setWidth("160px");
		tfValorCompra.setHeight("-1px");
		tfValorCompra.setSizeFull();
		tfValorCompra.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorCompra, 0, 6);

		// tfValorAtualizado
		tfValorAtualizado = ComponentUtil.buildCurrencyField("Valor atualizado:");
		tfValorAtualizado.setImmediate(false);
		tfValorAtualizado.setWidth("160px");
		tfValorAtualizado.setHeight("-1px");
		tfValorAtualizado.setSizeFull();
		tfValorAtualizado.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorAtualizado, 1, 6);

		// tfValorBaixa
		tfValorBaixa = ComponentUtil.buildCurrencyField("Valor da baixa:");
		tfValorBaixa.setImmediate(false);
		tfValorBaixa.setWidth("160px");
		tfValorBaixa.setHeight("-1px");
		tfValorBaixa.setSizeFull();
		tfValorBaixa.setNullRepresentation("");
		gridLayout_1.addComponent(tfValorBaixa, 2, 6);

		// tfDeprecia
		tfDeprecia = new TextField();
		tfDeprecia.setCaption("Depreciação:");
		tfDeprecia.setImmediate(false);
		tfDeprecia.setWidth("160px");
		tfDeprecia.setHeight("-1px");
		tfDeprecia.setSizeFull();
		tfDeprecia.setNullRepresentation("");
		gridLayout_1.addComponent(tfDeprecia, 0, 7);

		// tfMetodoDepreciacao
		tfMetodoDepreciacao = new TextField();
		tfMetodoDepreciacao.setCaption("Método de depreciação:");
		tfMetodoDepreciacao.setImmediate(false);
		tfMetodoDepreciacao.setWidth("160px");
		tfMetodoDepreciacao.setHeight("-1px");
		tfMetodoDepreciacao.setSizeFull();
		tfMetodoDepreciacao.setNullRepresentation("");
		gridLayout_1.addComponent(tfMetodoDepreciacao, 1, 7);

		// pdfInicioDepreciacao
		pdfInicioDepreciacao = new PopupDateField();
		pdfInicioDepreciacao.setCaption("Início de depreciação:");
		pdfInicioDepreciacao.setImmediate(false);
		pdfInicioDepreciacao.setWidth("160px");
		pdfInicioDepreciacao.setHeight("-1px");
		gridLayout_1.addComponent(pdfInicioDepreciacao, 2, 7);

		// pdfUltimaDepreciacao
		pdfUltimaDepreciacao = new PopupDateField();
		pdfUltimaDepreciacao.setCaption("Última depreciação:");
		pdfUltimaDepreciacao.setImmediate(false);
		pdfUltimaDepreciacao.setWidth("160px");
		pdfUltimaDepreciacao.setHeight("-1px");
		gridLayout_1.addComponent(pdfUltimaDepreciacao, 0, 8);

		// tfTipoDepreciacao
		tfTipoDepreciacao = new TextField();
		tfTipoDepreciacao.setCaption("Tipo de depreciação:");
		tfTipoDepreciacao.setImmediate(false);
		tfTipoDepreciacao.setWidth("160px");
		tfTipoDepreciacao.setHeight("-1px");
		tfTipoDepreciacao.setSizeFull();
		tfTipoDepreciacao.setNullRepresentation("");
		gridLayout_1.addComponent(tfTipoDepreciacao, 1, 8);

		// tfTaxaAnualDepreciacao
		tfTaxaAnualDepreciacao = ComponentUtil.buildNumberField("Taxa anual de depreciação:");
		tfTaxaAnualDepreciacao.setImmediate(false);
		tfTaxaAnualDepreciacao.setWidth("160px");
		tfTaxaAnualDepreciacao.setHeight("-1px");
		tfTaxaAnualDepreciacao.setSizeFull();
		tfTaxaAnualDepreciacao.setNullRepresentation("");
		gridLayout_1.addComponent(tfTaxaAnualDepreciacao, 2, 8);

		// tfTaxaMensalDepreciacao
		tfTaxaMensalDepreciacao = ComponentUtil.buildNumberField("Taxa mensal de depreciação:");
		tfTaxaMensalDepreciacao.setImmediate(false);
		tfTaxaMensalDepreciacao.setWidth("160px");
		tfTaxaMensalDepreciacao.setHeight("-1px");
		tfTaxaMensalDepreciacao.setSizeFull();
		tfTaxaMensalDepreciacao.setNullRepresentation("");
		gridLayout_1.addComponent(tfTaxaMensalDepreciacao, 0, 9);

		// tfTaxaDepreciacaoAcelerada
		tfTaxaDepreciacaoAcelerada = ComponentUtil.buildNumberField("Taxa de depreciação acelerada:");
		tfTaxaDepreciacaoAcelerada.setImmediate(false);
		tfTaxaDepreciacaoAcelerada.setWidth("160px");
		tfTaxaDepreciacaoAcelerada.setHeight("-1px");
		tfTaxaDepreciacaoAcelerada.setSizeFull();
		tfTaxaDepreciacaoAcelerada.setNullRepresentation("");
		gridLayout_1.addComponent(tfTaxaDepreciacaoAcelerada, 1, 9);

		// tfTaxaDepreciacaoIncentivada
		tfTaxaDepreciacaoIncentivada = ComponentUtil.buildNumberField("Taxa de depreciação incentivada:");
		tfTaxaDepreciacaoIncentivada.setImmediate(false);
		tfTaxaDepreciacaoIncentivada.setWidth("160px");
		tfTaxaDepreciacaoIncentivada.setHeight("-1px");
		tfTaxaDepreciacaoIncentivada.setSizeFull();
		tfTaxaDepreciacaoIncentivada.setNullRepresentation("");
		gridLayout_1.addComponent(tfTaxaDepreciacaoIncentivada, 2, 9);

		// tfFuncao
		tfFuncao = new TextField();
		tfFuncao.setCaption("Função:");
		tfFuncao.setImmediate(false);
		tfFuncao.setWidth("160px");
		tfFuncao.setHeight("-1px");
		tfFuncao.setSizeFull();
		tfFuncao.setNullRepresentation("");
		gridLayout_1.addComponent(tfFuncao, 0, 10);

		// cbTipoAquisicao
		cbTipoAquisicao = new ComboBox();
		cbTipoAquisicao.setCaption("Tipo de aquisição:");
		cbTipoAquisicao.setImmediate(false);
		cbTipoAquisicao.setWidth("160px");
		cbTipoAquisicao.setHeight("-1px");
		cbTipoAquisicao.setRequired(true);
		gridLayout_1.addComponent(cbTipoAquisicao, 1, 10);

		// cbEstadoConservacao
		cbEstadoConservacao = new ComboBox();
		cbEstadoConservacao.setCaption("Estado de conservação:");
		cbEstadoConservacao.setImmediate(false);
		cbEstadoConservacao.setWidth("160px");
		cbEstadoConservacao.setHeight("-1px");
		cbEstadoConservacao.setRequired(true);
		gridLayout_1.addComponent(cbEstadoConservacao, 2, 10);

		// cbGrupoBem
		cbGrupoBem = new ComboBox();
		cbGrupoBem.setCaption("Grupo do bem:");
		cbGrupoBem.setImmediate(false);
		cbGrupoBem.setWidth("160px");
		cbGrupoBem.setHeight("-1px");
		cbGrupoBem.setRequired(true);
		gridLayout_1.addComponent(cbGrupoBem, 0, 11);

		// cbSetor
		cbSetor = new ComboBox();
		cbSetor.setCaption("Setor:");
		cbSetor.setImmediate(false);
		cbSetor.setWidth("160px");
		cbSetor.setHeight("-1px");
		cbSetor.setRequired(true);
		gridLayout_1.addComponent(cbSetor, 1, 11);

		// cbFornecedor
		cbFornecedor = new ComboBox();
		cbFornecedor.setCaption("Fornecedor:");
		cbFornecedor.setImmediate(false);
		cbFornecedor.setWidth("160px");
		cbFornecedor.setHeight("-1px");
		cbFornecedor.setRequired(true);
		gridLayout_1.addComponent(cbFornecedor, 2, 11);

		// cbColaborador
		cbColaborador = new ComboBox();
		cbColaborador.setCaption("Colaborador:");
		cbColaborador.setImmediate(false);
		cbColaborador.setWidth("160px");
		cbColaborador.setHeight("-1px");
		cbColaborador.setRequired(true);
		gridLayout_1.addComponent(cbColaborador, 0, 12);

		return gridLayout_1;
	}

	/**
	 * COMBOS
	 */

	public void carregarCmbTipoAquisicao(List<TipoAquisicaoEntity> lista) {
		BeanItemContainer<TipoAquisicaoEntity> bic = new BeanItemContainer<TipoAquisicaoEntity>(
				TipoAquisicaoEntity.class, lista);
		this.cbTipoAquisicao.setContainerDataSource(bic);
		this.cbTipoAquisicao.setItemCaptionPropertyId("nome");
	}

	public void carregarCmbEstadoConservacao(List<EstadoConservacaoEntity> lista) {
		BeanItemContainer<EstadoConservacaoEntity> bic = new BeanItemContainer<EstadoConservacaoEntity>(
				EstadoConservacaoEntity.class, lista);
		this.cbEstadoConservacao.setContainerDataSource(bic);
		this.cbEstadoConservacao.setItemCaptionPropertyId("nome");
	}

	public void carregarCmbGrupoBem(List<GrupoBemEntity> lista) {
		BeanItemContainer<GrupoBemEntity> bic = new BeanItemContainer<GrupoBemEntity>(
				GrupoBemEntity.class, lista);
		this.cbGrupoBem.setContainerDataSource(bic);
		this.cbGrupoBem.setItemCaptionPropertyId("nome");
	}

	public void carregarCmbSetor(List<SetorEntity> lista) {
		BeanItemContainer<SetorEntity> bic = new BeanItemContainer<SetorEntity>(
				SetorEntity.class, lista);
		this.cbSetor.setContainerDataSource(bic);
		this.cbSetor.setItemCaptionPropertyId("nome");
	}

	public void carregarCmbFornecedor(List<FornecedorEntity> lista) {
		BeanItemContainer<FornecedorEntity> bic = new BeanItemContainer<FornecedorEntity>(
				FornecedorEntity.class, lista);
		this.cbFornecedor.setContainerDataSource(bic);
		this.cbFornecedor.setItemCaptionPropertyId("nome");
	}

	public void carregarCmbColaborador(List<ColaboradorEntity> lista) {
		BeanItemContainer<ColaboradorEntity> bic = new BeanItemContainer<ColaboradorEntity>(
				ColaboradorEntity.class, lista);
		this.cbColaborador.setContainerDataSource(bic);
		this.cbColaborador.setItemCaptionPropertyId("nome");
	}

}