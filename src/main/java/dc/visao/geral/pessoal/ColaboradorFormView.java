package dc.visao.geral.pessoal;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dc.controller.geral.pessoal.ColaboradorFormController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.contabilidade.PlanoConta;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.outro.SindicatoEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.pessoal.NivelFormacaoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class ColaboradorFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_8;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_7;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_6;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_5;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_4;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;

	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	@AutoGenerated
	private TextField tfObservacao;

	@AutoGenerated
	private PopupDateField pdfDataExpedida;

	@AutoGenerated
	private TextField tfSerie;

	@AutoGenerated
	private TextField tfNumeroCarteira;

	@AutoGenerated
	private TextField tfDigitoAgencia1;

	@AutoGenerated
	private TextField tfAgencia1;

	@AutoGenerated
	private TextField tfBanco1;

	@AutoGenerated
	private TextField tfNumeroPis;

	@AutoGenerated
	private PopupDateField pdfDataCadastroPis;

	@AutoGenerated
	private TextField tfDigitoConta;

	@AutoGenerated
	private TextField tfConta;

	@AutoGenerated
	private TextField tfDigitoAgencia;

	@AutoGenerated
	private TextField tfAgencia;

	@AutoGenerated
	private TextField tfBanco;

	@AutoGenerated
	private ComboBox cbFormaPagamento;

	@AutoGenerated
	private TextField tfCodigoDemissaoCaged;

	@AutoGenerated
	private TextField tfCodigoAdmissao;

	@AutoGenerated
	private PopupDateField pdfDataOpcao;

	@AutoGenerated
	private ComboBox cbOptante;

	@AutoGenerated
	private TextField tfCodigoDemissao;

	@AutoGenerated
	private TextField tfOcorrencia;

	@AutoGenerated
	private TextField tfCategoria;

	@AutoGenerated
	private TextField tfSalarioFixo;

	@AutoGenerated
	private TextField tfComissaoProduto;

	@AutoGenerated
	private TextField tfComissaoServico;

	@AutoGenerated
	private PopupDateField pdfDataVencimento;

	@AutoGenerated
	private PopupDateField pdfDataUltimoExame;

	@AutoGenerated
	private TextField tfCodigoTurma;

	@AutoGenerated
	private ComboBox cbSaiRais;

	@AutoGenerated
	private ComboBox cbDescontoPlanoSaude;

	@AutoGenerated
	private PopupDateField pdfDataDemissao;

	@AutoGenerated
	private PopupDateField pdfDataTransferencia;

	@AutoGenerated
	private PopupDateField pdfDataVencimentoFerias;

	@AutoGenerated
	private PopupDateField pdfDataAdmissao;

	@AutoGenerated
	private PopupDateField pdfDataCadastro;

	@AutoGenerated
	private TextField tfMatricula;

	@AutoGenerated
	private ComboBox cbPriorizarPgto;

	@AutoGenerated
	private ComboBox cbComissaoOver;

	@AutoGenerated
	private ComboBox cbPgtoComissao;

	@AutoGenerated
	private ComboBox cbLctoComissao;

	@AutoGenerated
	private OptionGroup ogTipoComissaoServico;

	@AutoGenerated
	private OptionGroup ogTipoComissaoProduto;

	@AutoGenerated
	private TabSheet subForms;

	@AutoGenerated
	private ManyToOneCombo<PessoaEntity> mocPessoa;

	@AutoGenerated
	private ManyToOneCombo<TipoColaboradorEntity> mocTipoColaborador;

	@AutoGenerated
	private ManyToOneCombo<SituacaoColaboradorEntity> mocSituacaoColaborador;

	@AutoGenerated
	private ManyToOneCombo<SindicatoEntity> mocSindicato;

	@AutoGenerated
	private ManyToOneCombo<NivelFormacaoEntity> mocNivelFormacao;

	@AutoGenerated
	private ManyToOneCombo<CargoEntity> mocCargo;

	@AutoGenerated
	private ManyToOneCombo<SetorEntity> mocSetor;

	@AutoGenerated
	private ManyToOneCombo<PlanoConta> mocPlanoConta;

	@AutoGenerated
	private ManyToOneCombo<ContaCaixa> mocContaCaixa;

	@AutoGenerated
	private ManyToOneCombo<UfEntity> mocUf;

	private ColaboradorFormController controller;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ColaboradorFormView(ColaboradorFormController controller) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		this.controller = controller;
	}

	public TextField getTfObservacao() {
		return tfObservacao;
	}

	public void setTfObservacao(TextField tfObservacao) {
		this.tfObservacao = tfObservacao;
	}

	public PopupDateField getPdfDataExpedida() {
		return pdfDataExpedida;
	}

	public void setPdfDataExpedida(PopupDateField pdfDataExpedida) {
		this.pdfDataExpedida = pdfDataExpedida;
	}

	public TextField getTfSerie() {
		return tfSerie;
	}

	public void setTfSerie(TextField tfSerie) {
		this.tfSerie = tfSerie;
	}

	public TextField getTfNumeroCarteira() {
		return tfNumeroCarteira;
	}

	public void setTfNumeroCarteira(TextField tfNumeroCarteira) {
		this.tfNumeroCarteira = tfNumeroCarteira;
	}

	public TextField getTfDigitoAgencia() {
		return tfDigitoAgencia;
	}

	public void setTfDigitoAgencia(TextField tfDigitoAgencia) {
		this.tfDigitoAgencia = tfDigitoAgencia;
	}

	public TextField getTfAgencia1() {
		return tfAgencia1;
	}

	public void setTfAgencia1(TextField tfAgencia1) {
		this.tfAgencia1 = tfAgencia1;
	}

	public TextField getTfBanco1() {
		return tfBanco1;
	}

	public void setTfBanco1(TextField tfBanco1) {
		this.tfBanco1 = tfBanco1;
	}

	public TextField getTfNumeroPis() {
		return tfNumeroPis;
	}

	public void setTfNumeroPis(TextField tfNumeroPis) {
		this.tfNumeroPis = tfNumeroPis;
	}

	public PopupDateField getPdfDataCadastroPis() {
		return pdfDataCadastroPis;
	}

	public void setPdfDataCadastroPis(PopupDateField pdfDataCadastroPis) {
		this.pdfDataCadastroPis = pdfDataCadastroPis;
	}

	public TextField getTfDigitoAgencia1() {
		return tfDigitoAgencia1;
	}

	public void setTfDigitoAgencia1(TextField tfDigitoAgencia1) {
		this.tfDigitoAgencia1 = tfDigitoAgencia1;
	}

	public TextField getTfDigitoConta() {
		return tfDigitoConta;
	}

	public void setTfDigitoConta(TextField tfDigitoConta) {
		this.tfDigitoConta = tfDigitoConta;
	}

	public TextField getTfConta() {
		return tfConta;
	}

	public void setTfConta(TextField tfConta) {
		this.tfConta = tfConta;
	}

	public TextField getTfAgencia() {
		return tfAgencia;
	}

	public void setTfAgencia(TextField tfAgencia) {
		this.tfAgencia = tfAgencia;
	}

	public TextField getTfBanco() {
		return tfBanco;
	}

	public void setTfBanco(TextField tfBanco) {
		this.tfBanco = tfBanco;
	}

	public ComboBox getCbFormaPagamento() {
		return cbFormaPagamento;
	}

	public void setCbFormaPagamento(ComboBox cbFormaPagamento) {
		this.cbFormaPagamento = cbFormaPagamento;
	}

	public TextField getTfCodigoDemissaoCaged() {
		return tfCodigoDemissaoCaged;
	}

	public void setTfCodigoDemissaoCaged(TextField tfCodigoDemissaoCaged) {
		this.tfCodigoDemissaoCaged = tfCodigoDemissaoCaged;
	}

	public TextField getTfCodigoAdmissao() {
		return tfCodigoAdmissao;
	}

	public void setTfCodigoAdmissao(TextField tfCodigoAdmissao) {
		this.tfCodigoAdmissao = tfCodigoAdmissao;
	}

	public PopupDateField getPdfDataOpcao() {
		return pdfDataOpcao;
	}

	public void setPdfDataOpcao(PopupDateField pdfDataOpcao) {
		this.pdfDataOpcao = pdfDataOpcao;
	}

	public ComboBox getCbOptante() {
		return cbOptante;
	}

	public void setCbOptante(ComboBox cbOptante) {
		this.cbOptante = cbOptante;
	}

	public TextField getTfCodigoDemissao() {
		return tfCodigoDemissao;
	}

	public void setTfCodigoDemissao(TextField tfCodigoDemissao) {
		this.tfCodigoDemissao = tfCodigoDemissao;
	}

	public TextField getTfOcorrencia() {
		return tfOcorrencia;
	}

	public void setTfOcorrencia(TextField tfOcorrencia) {
		this.tfOcorrencia = tfOcorrencia;
	}

	public TextField getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(TextField tfCategoria) {
		this.tfCategoria = tfCategoria;
	}

	public TextField getTfSalarioFixo() {
		return tfSalarioFixo;
	}

	public void setTfSalarioFixo(TextField tfSalarioFixo) {
		this.tfSalarioFixo = tfSalarioFixo;
	}

	public TextField getTfComissaoProduto() {
		return tfComissaoProduto;
	}

	public void setTfComissaoProduto(TextField tfComissaoProduto) {
		this.tfComissaoProduto = tfComissaoProduto;
	}

	public TextField getTfComissaoServico() {
		return tfComissaoServico;
	}

	public void setTfComissaoServico(TextField tfComissaoServico) {
		this.tfComissaoServico = tfComissaoServico;
	}

	public PopupDateField getPdfDataVencimento() {
		return pdfDataVencimento;
	}

	public void setPdfDataVencimento(PopupDateField pdfDataVencimento) {
		this.pdfDataVencimento = pdfDataVencimento;
	}

	public PopupDateField getPdfDataUltimoExame() {
		return pdfDataUltimoExame;
	}

	public void setPdfDataUltimoExame(PopupDateField pdfDataUltimoExame) {
		this.pdfDataUltimoExame = pdfDataUltimoExame;
	}

	public TextField getTfCodigoTurma() {
		return tfCodigoTurma;
	}

	public void setTfCodigoTurma(TextField tfCodigoTurma) {
		this.tfCodigoTurma = tfCodigoTurma;
	}

	public ComboBox getCbSaiRais() {
		return cbSaiRais;
	}

	public void setCbSaiRais(ComboBox cbSaiRais) {
		this.cbSaiRais = cbSaiRais;
	}

	public ComboBox getCbDescontoPlanoSaude() {
		return cbDescontoPlanoSaude;
	}

	public void setCbDescontoPlanoSaude(ComboBox cbDescontoPlanoSaude) {
		this.cbDescontoPlanoSaude = cbDescontoPlanoSaude;
	}

	public PopupDateField getPdfDataDemissao() {
		return pdfDataDemissao;
	}

	public void setPdfDataDemissao(PopupDateField pdfDataDemissao) {
		this.pdfDataDemissao = pdfDataDemissao;
	}

	public PopupDateField getPdfDataTransferencia() {
		return pdfDataTransferencia;
	}

	public void setPdfDataTransferencia(PopupDateField pdfDataTransferencia) {
		this.pdfDataTransferencia = pdfDataTransferencia;
	}

	public PopupDateField getPdfDataVencimentoFerias() {
		return pdfDataVencimentoFerias;
	}

	public void setPdfDataVencimentoFerias(
			PopupDateField pdfDataVencimentoFerias) {
		this.pdfDataVencimentoFerias = pdfDataVencimentoFerias;
	}

	public PopupDateField getPdfDataAdmissao() {
		return pdfDataAdmissao;
	}

	public void setPdfDataAdmissao(PopupDateField pdfDataAdmissao) {
		this.pdfDataAdmissao = pdfDataAdmissao;
	}

	public PopupDateField getPdfDataCadastro() {
		return pdfDataCadastro;
	}

	public void setPdfDataCadastro(PopupDateField pdfDataCadastro) {
		this.pdfDataCadastro = pdfDataCadastro;
	}

	public TextField getTfMatricula() {
		return tfMatricula;
	}

	public void setTfMatricula(TextField tfMatricula) {
		this.tfMatricula = tfMatricula;
	}

	public ComboBox getCbComissaoOver() {
		return cbComissaoOver;
	}

	public void setCbComissaoOver(ComboBox cbComissaoOver) {
		this.cbComissaoOver = cbComissaoOver;
	}

	public ComboBox getCbLctoComissao() {
		return cbLctoComissao;
	}

	public void setCbLctoComissao(ComboBox cbLctoComissao) {
		this.cbLctoComissao = cbLctoComissao;
	}

	public ComboBox getCbPgtoComissao() {
		return cbPgtoComissao;
	}

	public void setCbPgtoComissao(ComboBox cbPgtoComissao) {
		this.cbPgtoComissao = cbPgtoComissao;
	}

	public ComboBox getCbPriorizarPgto() {
		return cbPriorizarPgto;
	}

	public void setCbPriorizarPgto(ComboBox cbPriorizarPgto) {
		this.cbPriorizarPgto = cbPriorizarPgto;
	}

	public ManyToOneCombo<PessoaEntity> getMocPessoa() {
		return mocPessoa;
	}

	public void setMocPessoa(ManyToOneCombo<PessoaEntity> mocPessoa) {
		this.mocPessoa = mocPessoa;
	}

	public ManyToOneCombo<TipoColaboradorEntity> getMocTipoColaborador() {
		return mocTipoColaborador;
	}

	public void setMocTipoColaborador(
			ManyToOneCombo<TipoColaboradorEntity> mocTipoColaborador) {
		this.mocTipoColaborador = mocTipoColaborador;
	}

	public ManyToOneCombo<SituacaoColaboradorEntity> getMocSituacaoColaborador() {
		return mocSituacaoColaborador;
	}

	public void setMocSituacaoColaborador(
			ManyToOneCombo<SituacaoColaboradorEntity> mocSituacaoColaborador) {
		this.mocSituacaoColaborador = mocSituacaoColaborador;
	}

	public ManyToOneCombo<SindicatoEntity> getMocSindicato() {
		return mocSindicato;
	}

	public void setMocSindicato(ManyToOneCombo<SindicatoEntity> mocSindicato) {
		this.mocSindicato = mocSindicato;
	}

	public ManyToOneCombo<NivelFormacaoEntity> getMocNivelFormacao() {
		return mocNivelFormacao;
	}

	public void setMocNivelFormacao(
			ManyToOneCombo<NivelFormacaoEntity> mocNivelFormacao) {
		this.mocNivelFormacao = mocNivelFormacao;
	}

	public ManyToOneCombo<CargoEntity> getMocCargo() {
		return mocCargo;
	}

	public void setMocCargo(ManyToOneCombo<CargoEntity> mocCargo) {
		this.mocCargo = mocCargo;
	}

	public ManyToOneCombo<SetorEntity> getMocSetor() {
		return mocSetor;
	}

	public void setMocSetor(ManyToOneCombo<SetorEntity> mocSetor) {
		this.mocSetor = mocSetor;
	}

	public ManyToOneCombo<ContaCaixa> getMocContaCaixa() {
		return mocContaCaixa;
	}

	public void setMocContaCaixa(ManyToOneCombo<ContaCaixa> mocContaCaixa) {
		this.mocContaCaixa = mocContaCaixa;
	}

	public ManyToOneCombo<PlanoConta> getMocPlanoConta() {
		return mocPlanoConta;
	}

	public void setMocPlanoConta(ManyToOneCombo<PlanoConta> mocPlanoConta) {
		this.mocPlanoConta = mocPlanoConta;
	}

	public OptionGroup getOgTipoComissaoProduto() {
		return ogTipoComissaoProduto;
	}

	public void setOgTipoComissaoProduto(OptionGroup ogTipoComissaoProduto) {
		this.ogTipoComissaoProduto = ogTipoComissaoProduto;
	}

	public OptionGroup getOgTipoComissaoServico() {
		return ogTipoComissaoServico;
	}

	public void setOgTipoComissaoServico(OptionGroup ogTipoComissaoServico) {
		this.ogTipoComissaoServico = ogTipoComissaoServico;
	}

	public ManyToOneCombo<UfEntity> getMocUf() {
		return mocUf;
	}

	public void setMocUf(ManyToOneCombo<UfEntity> mocUf) {
		this.mocUf = mocUf;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		setSizeFull();
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);
		setWidth("100.0%");

		// common part: create layout
		subForms = new TabSheet();
		subForms.setWidth("100.0%");
		subForms.setHeight("100.0%");
		subForms.setSizeFull();
		subForms.setImmediate(true);
		mainLayout.addComponent(subForms);

		buildAbaDadosGerais();
		buildAbaComissao();

		return mainLayout;
	}

	public void buildAbaDadosGerais() {
		GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		gridLayout_1.setMargin(true);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setRows(8);
		gridLayout_1.setColumns(7);

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		gridLayout_1.addComponent(horizontalLayout_1, 0, 0);

		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		gridLayout_1.addComponent(horizontalLayout_2, 0, 1);

		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		gridLayout_1.addComponent(horizontalLayout_3, 0, 2);

		// horizontalLayout_4
		horizontalLayout_4 = buildHorizontalLayout_4();
		gridLayout_1.addComponent(horizontalLayout_4, 0, 3);

		// horizontalLayout_5
		horizontalLayout_5 = buildHorizontalLayout_5();
		gridLayout_1.addComponent(horizontalLayout_5, 0, 4);

		// horizontalLayout_6
		horizontalLayout_6 = buildHorizontalLayout_6();
		gridLayout_1.addComponent(horizontalLayout_6, 0, 5);

		// horizontalLayout_7
		horizontalLayout_7 = buildHorizontalLayout_7();
		gridLayout_1.addComponent(horizontalLayout_7, 0, 6);

		// horizontalLayout_8
		horizontalLayout_8 = buildHorizontalLayout_8();
		gridLayout_1.addComponent(horizontalLayout_8, 0, 7);

		subForms.addTab(gridLayout_1, "Dados do colaborador", null);
	}

	public void buildAbaComissao() {
		GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		// gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(true);
		gridLayout_1.setSpacing(true);
		gridLayout_1.setRows(5);
		gridLayout_1.setColumns(5);

		tfSalarioFixo = ComponentUtil.buildCurrencyField("Salário fixo");
		gridLayout_1.addComponent(tfSalarioFixo, 0, 0, 0, 0);

		cbPriorizarPgto = ComponentUtil
				.buildComboBox("Priorizar comissão cadastro");
		gridLayout_1.addComponent(cbPriorizarPgto, 1, 0, 1, 0);

		cbComissaoOver = ComponentUtil.buildComboBox("Pagamento comissão over");
		gridLayout_1.addComponent(cbComissaoOver, 2, 0, 2, 0);

		ogTipoComissaoServico = new OptionGroup();
		ogTipoComissaoServico.setStyleName("horizontal");
		ogTipoComissaoServico.setCaption("Tipo comissão serviço");
		ogTipoComissaoServico.setNullSelectionAllowed(false);
		ogTipoComissaoServico.setImmediate(false);
		ogTipoComissaoServico.addItem("R");
		ogTipoComissaoServico.setItemCaption("R", "Comissão / Serviço R$");
		ogTipoComissaoServico.addItem("P");
		ogTipoComissaoServico.setItemCaption("P", "Comissão / Serviço %");
		gridLayout_1.addComponent(ogTipoComissaoServico, 0, 1, 1, 1);

		tfComissaoServico = ComponentUtil
				.buildCurrencyField("Valor da comissão do serviço");
		gridLayout_1.addComponent(tfComissaoServico, 2, 1, 2, 1);

		ogTipoComissaoProduto = new OptionGroup();
		ogTipoComissaoProduto.setStyleName("horizontal");
		ogTipoComissaoProduto.setCaption("Tipo da comissão do produto");
		ogTipoComissaoProduto.setNullSelectionAllowed(false);
		ogTipoComissaoProduto.setImmediate(false);
		ogTipoComissaoProduto.addItem("R");
		ogTipoComissaoProduto.setItemCaption("R", "Comissão / Produto R$");
		ogTipoComissaoProduto.addItem("P");
		ogTipoComissaoProduto.setItemCaption("P", "Comissão / Produto %");
		gridLayout_1.addComponent(ogTipoComissaoProduto, 0, 2, 1, 2);

		tfComissaoProduto = ComponentUtil
				.buildCurrencyField("Valor da comissão do produto");
		gridLayout_1.addComponent(tfComissaoProduto, 2, 2, 2, 2);

		cbPgtoComissao = ComponentUtil
				.buildComboBox("Vendedor / Atendente pgto comissão será");
		gridLayout_1.addComponent(cbPgtoComissao, 0, 3, 0, 3);

		cbLctoComissao = ComponentUtil.buildComboBox("Lançamento de comissão");
		gridLayout_1.addComponent(cbLctoComissao, 2, 3, 2, 3);

		mocContaCaixa = new ManyToOneCombo<ContaCaixa>();
		mocContaCaixa.setCaption("Nr. conta");
		gridLayout_1.addComponent(mocContaCaixa, 0, 4, 1, 4);

		mocPlanoConta = new ManyToOneCombo<PlanoConta>();
		mocPlanoConta.setCaption("Plano de conta");
		gridLayout_1.addComponent(mocPlanoConta, 2, 4, 3, 4);

		subForms.addTab(gridLayout_1, "Dados de comissão", null);
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100%");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);

		// mocPessoa
		mocPessoa = new ManyToOneCombo<>();
		mocPessoa.setCaption("Pessoa");
		mocPessoa.setImmediate(false);
		mocPessoa.setWidth("90%");
		mocPessoa.setHeight("-1px");
		horizontalLayout_1.addComponent(mocPessoa);

		// mocTipoColaborador
		mocTipoColaborador = new ManyToOneCombo<>();
		mocTipoColaborador.setCaption("Tipo do colaborador");
		mocTipoColaborador.setImmediate(false);
		mocTipoColaborador.setWidth("90%");
		mocTipoColaborador.setHeight("-1px");
		horizontalLayout_1.addComponent(mocTipoColaborador);

		// mocSituacaoColaborador
		mocSituacaoColaborador = new ManyToOneCombo<>();
		mocSituacaoColaborador.setCaption("Situação do colaborador");
		mocSituacaoColaborador.setImmediate(false);
		mocSituacaoColaborador.setWidth("90%");
		mocSituacaoColaborador.setHeight("-1px");
		horizontalLayout_1.addComponent(mocSituacaoColaborador);

		// mocSindicato
		mocSindicato = new ManyToOneCombo<>();
		mocSindicato.setCaption("Sindicato");
		mocSindicato.setImmediate(false);
		mocSindicato.setWidth("90%");
		mocSindicato.setHeight("-1px");
		horizontalLayout_1.addComponent(mocSindicato);

		return horizontalLayout_1;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);

		// mocNivelFormacao
		mocNivelFormacao = new ManyToOneCombo<>();
		mocNivelFormacao.setCaption("Nível de formação");
		mocNivelFormacao.setImmediate(false);
		mocNivelFormacao.setWidth("-1px");
		mocNivelFormacao.setHeight("-1px");
		horizontalLayout_2.addComponent(mocNivelFormacao);

		// mocCargo
		mocCargo = new ManyToOneCombo<>();
		mocCargo.setCaption("Cargo");
		mocCargo.setImmediate(false);
		mocCargo.setWidth("-1px");
		mocCargo.setHeight("-1px");
		horizontalLayout_2.addComponent(mocCargo);

		// mocSetor
		mocSetor = new ManyToOneCombo<>();
		mocSetor.setCaption("Setor");
		mocSetor.setImmediate(false);
		mocSetor.setWidth("176px");
		mocSetor.setHeight("-1px");
		horizontalLayout_2.addComponent(mocSetor);

		return horizontalLayout_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("-1px");
		horizontalLayout_3.setHeight("-1px");
		horizontalLayout_3.setMargin(false);
		horizontalLayout_3.setSpacing(true);

		// tfMatricula
		tfMatricula = new TextField();
		tfMatricula.setCaption("Matrícula");
		tfMatricula.setNullRepresentation("");
		tfMatricula.setImmediate(false);
		tfMatricula.setWidth("100px");
		tfMatricula.setHeight("-1px");
		horizontalLayout_3.addComponent(tfMatricula);

		// pdfDataCadastro
		pdfDataCadastro = new PopupDateField();
		pdfDataCadastro.setCaption("Data de cadastro");
		pdfDataCadastro.setImmediate(false);
		pdfDataCadastro.setWidth("111px");
		pdfDataCadastro.setHeight("-1px");
		horizontalLayout_3.addComponent(pdfDataCadastro);

		// pdfDataAdmissao
		pdfDataAdmissao = new PopupDateField();
		pdfDataAdmissao.setCaption("Data da admissão");
		pdfDataAdmissao.setImmediate(false);
		pdfDataAdmissao.setWidth("99px");
		pdfDataAdmissao.setHeight("-1px");
		horizontalLayout_3.addComponent(pdfDataAdmissao);

		// pdfDataVencimentoFerias
		pdfDataVencimentoFerias = new PopupDateField();
		pdfDataVencimentoFerias.setCaption("Vencimento das férias");
		pdfDataVencimentoFerias.setImmediate(false);
		pdfDataVencimentoFerias.setWidth("115px");
		pdfDataVencimentoFerias.setHeight("-1px");
		horizontalLayout_3.addComponent(pdfDataVencimentoFerias);

		// pdfDataTransferencia
		pdfDataTransferencia = new PopupDateField();
		pdfDataTransferencia.setCaption("Transferência");
		pdfDataTransferencia.setImmediate(false);
		pdfDataTransferencia.setWidth("109px");
		pdfDataTransferencia.setHeight("-1px");
		horizontalLayout_3.addComponent(pdfDataTransferencia);

		// pdfDataDemissao
		pdfDataDemissao = new PopupDateField();
		pdfDataDemissao.setCaption("Demissão");
		pdfDataDemissao.setImmediate(false);
		pdfDataDemissao.setWidth("115px");
		pdfDataDemissao.setHeight("-1px");
		horizontalLayout_3.addComponent(pdfDataDemissao);

		return horizontalLayout_3;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_4() {
		// common part: create layout
		horizontalLayout_4 = new HorizontalLayout();
		horizontalLayout_4.setImmediate(false);
		horizontalLayout_4.setWidth("-1px");
		horizontalLayout_4.setHeight("-1px");
		horizontalLayout_4.setMargin(false);
		horizontalLayout_4.setSpacing(true);

		// cbDescontoPlanoSaude
		cbDescontoPlanoSaude = new ComboBox();
		cbDescontoPlanoSaude.setCaption("Desconto do plano de saúde");
		cbDescontoPlanoSaude.setImmediate(false);
		cbDescontoPlanoSaude.setWidth("-1px");
		cbDescontoPlanoSaude.setHeight("-1px");
		horizontalLayout_4.addComponent(cbDescontoPlanoSaude);

		// cbSaiRais
		cbSaiRais = new ComboBox();
		cbSaiRais.setCaption("Sai na RAIS");
		cbSaiRais.setImmediate(false);
		cbSaiRais.setWidth("132px");
		cbSaiRais.setHeight("-1px");
		horizontalLayout_4.addComponent(cbSaiRais);

		// tfCodigoTurma
		tfCodigoTurma = new TextField();
		tfCodigoTurma.setCaption("Código da turma");
		tfCodigoTurma.setNullRepresentation("");
		tfCodigoTurma.setImmediate(false);
		tfCodigoTurma.setWidth("-1px");
		tfCodigoTurma.setHeight("-1px");
		horizontalLayout_4.addComponent(tfCodigoTurma);

		// pdfDataUltimoExame
		pdfDataUltimoExame = new PopupDateField();
		pdfDataUltimoExame.setCaption("Último exame em:");
		pdfDataUltimoExame.setImmediate(false);
		pdfDataUltimoExame.setWidth("120px");
		pdfDataUltimoExame.setHeight("-1px");
		horizontalLayout_4.addComponent(pdfDataUltimoExame);

		// pdfDataVencimento
		pdfDataVencimento = new PopupDateField();
		pdfDataVencimento.setCaption("Vencimento");
		pdfDataVencimento.setImmediate(false);
		pdfDataVencimento.setWidth("100px");
		pdfDataVencimento.setHeight("-1px");
		horizontalLayout_4.addComponent(pdfDataVencimento);

		return horizontalLayout_4;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_5() {
		// common part: create layout
		horizontalLayout_5 = new HorizontalLayout();
		horizontalLayout_5.setImmediate(false);
		horizontalLayout_5.setWidth("-1px");
		horizontalLayout_5.setHeight("-1px");
		horizontalLayout_5.setMargin(false);
		horizontalLayout_5.setSpacing(true);

		// tfCategoria
		tfCategoria = new TextField();
		tfCategoria.setCaption("Categoria");
		tfCategoria.setNullRepresentation("");
		tfCategoria.setImmediate(false);
		tfCategoria.setWidth("120px");
		tfCategoria.setHeight("-1px");
		horizontalLayout_5.addComponent(tfCategoria);

		// tfOcorrencia
		tfOcorrencia = new TextField();
		tfOcorrencia.setCaption("Ocorrência");
		tfOcorrencia.setNullRepresentation("");
		tfOcorrencia.setImmediate(false);
		tfOcorrencia.setWidth("-1px");
		tfOcorrencia.setHeight("-1px");
		horizontalLayout_5.addComponent(tfOcorrencia);

		// tfCodigoDemissao
		tfCodigoDemissao = new TextField();
		tfCodigoDemissao.setCaption("Código de demissão");
		tfCodigoDemissao.setNullRepresentation("");
		tfCodigoDemissao.setImmediate(false);
		tfCodigoDemissao.setWidth("-1px");
		tfCodigoDemissao.setHeight("-1px");
		horizontalLayout_5.addComponent(tfCodigoDemissao);

		// cbOptante
		cbOptante = new ComboBox();
		cbOptante.setCaption("Optante");
		cbOptante.setImmediate(false);
		cbOptante.setWidth("108px");
		cbOptante.setHeight("-1px");
		horizontalLayout_5.addComponent(cbOptante);

		// pdfDataOpcao
		pdfDataOpcao = new PopupDateField();
		pdfDataOpcao.setCaption("Data opção");
		pdfDataOpcao.setImmediate(false);
		pdfDataOpcao.setWidth("146px");
		pdfDataOpcao.setHeight("-1px");
		horizontalLayout_5.addComponent(pdfDataOpcao);

		return horizontalLayout_5;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_6() {
		// common part: create layout
		horizontalLayout_6 = new HorizontalLayout();
		horizontalLayout_6.setImmediate(false);
		horizontalLayout_6.setWidth("-1px");
		horizontalLayout_6.setHeight("-1px");
		horizontalLayout_6.setMargin(false);
		horizontalLayout_6.setSpacing(true);

		// tfCodigoAdmissao
		tfCodigoAdmissao = new TextField();
		tfCodigoAdmissao.setCaption("Código de admissão");
		tfCodigoAdmissao.setNullRepresentation("");
		tfCodigoAdmissao.setImmediate(false);
		tfCodigoAdmissao.setWidth("106px");
		tfCodigoAdmissao.setHeight("-1px");
		horizontalLayout_6.addComponent(tfCodigoAdmissao);

		// tfCodigoDemissaoCaged
		tfCodigoDemissaoCaged = new TextField();
		tfCodigoDemissaoCaged.setCaption("Código de demissão CAGED");
		tfCodigoDemissaoCaged.setNullRepresentation("");
		tfCodigoDemissaoCaged.setImmediate(false);
		tfCodigoDemissaoCaged.setWidth("-1px");
		tfCodigoDemissaoCaged.setHeight("-1px");
		horizontalLayout_6.addComponent(tfCodigoDemissaoCaged);

		// cbFormaPagamento
		cbFormaPagamento = new ComboBox();
		cbFormaPagamento.setCaption("Forma de pagamento");
		cbFormaPagamento.setImmediate(false);
		cbFormaPagamento.setWidth("141px");
		cbFormaPagamento.setHeight("-1px");
		horizontalLayout_6.addComponent(cbFormaPagamento);

		// tfBanco
		tfBanco = new TextField();
		tfBanco.setCaption("Banco");
		tfBanco.setNullRepresentation("");
		tfBanco.setImmediate(false);
		tfBanco.setWidth("-1px");
		tfBanco.setHeight("-1px");
		horizontalLayout_6.addComponent(tfBanco);

		// tfAgencia
		tfAgencia = new TextField();
		tfAgencia.setCaption("Agência");
		tfAgencia.setNullRepresentation("");
		tfAgencia.setImmediate(false);
		tfAgencia.setWidth("68px");
		tfAgencia.setHeight("-1px");
		horizontalLayout_6.addComponent(tfAgencia);

		// tfDigitoAgencia
		tfDigitoAgencia = new TextField();
		tfDigitoAgencia.setCaption("Dígito");
		tfDigitoAgencia.setNullRepresentation("");
		tfDigitoAgencia.setImmediate(false);
		tfDigitoAgencia.setWidth("33px");
		tfDigitoAgencia.setHeight("-1px");
		horizontalLayout_6.addComponent(tfDigitoAgencia);

		// tfConta
		tfConta = new TextField();
		tfConta.setCaption("Conta");
		tfConta.setNullRepresentation("");
		tfConta.setImmediate(false);
		tfConta.setWidth("81px");
		tfConta.setHeight("-1px");
		horizontalLayout_6.addComponent(tfConta);

		// tfDigitoConta
		tfDigitoConta = new TextField();
		tfDigitoConta.setCaption("Dígito");
		tfDigitoConta.setNullRepresentation("");
		tfDigitoConta.setImmediate(false);
		tfDigitoConta.setWidth("37px");
		tfDigitoConta.setHeight("-1px");
		horizontalLayout_6.addComponent(tfDigitoConta);

		return horizontalLayout_6;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_7() {
		// common part: create layout
		horizontalLayout_7 = new HorizontalLayout();
		horizontalLayout_7.setImmediate(false);
		horizontalLayout_7.setWidth("-1px");
		horizontalLayout_7.setHeight("-1px");
		horizontalLayout_7.setMargin(false);
		horizontalLayout_7.setSpacing(true);

		// pdfDataCadastroPis
		pdfDataCadastroPis = new PopupDateField();
		pdfDataCadastroPis.setCaption("Data de cadastro do PIS");
		pdfDataCadastroPis.setImmediate(false);
		pdfDataCadastroPis.setWidth("126px");
		pdfDataCadastroPis.setHeight("-1px");
		horizontalLayout_7.addComponent(pdfDataCadastroPis);

		// tfNumeroPis
		tfNumeroPis = new TextField();
		tfNumeroPis.setCaption("Número do PIS");
		tfNumeroPis.setNullRepresentation("");
		tfNumeroPis.setImmediate(false);
		tfNumeroPis.setWidth("91px");
		tfNumeroPis.setHeight("-1px");
		horizontalLayout_7.addComponent(tfNumeroPis);

		// tfBanco1
		tfBanco1 = new TextField();
		tfBanco1.setCaption("Banco");
		tfBanco1.setNullRepresentation("");
		tfBanco1.setImmediate(false);
		tfBanco1.setWidth("-1px");
		tfBanco1.setHeight("-1px");
		horizontalLayout_7.addComponent(tfBanco1);

		// tfAgencia1
		tfAgencia1 = new TextField();
		tfAgencia1.setCaption("Agência");
		tfAgencia1.setNullRepresentation("");
		tfAgencia1.setImmediate(false);
		tfAgencia1.setWidth("93px");
		tfAgencia1.setHeight("-1px");
		horizontalLayout_7.addComponent(tfAgencia1);

		// tfDigitoAgencia1
		tfDigitoAgencia1 = new TextField();
		tfDigitoAgencia1.setCaption("Dígito");
		tfDigitoAgencia1.setNullRepresentation("");
		tfDigitoAgencia1.setImmediate(false);
		tfDigitoAgencia1.setWidth("36px");
		tfDigitoAgencia1.setHeight("-1px");
		horizontalLayout_7.addComponent(tfDigitoAgencia1);

		// tfNumeroCarteira
		tfNumeroCarteira = new TextField();
		tfNumeroCarteira.setCaption("Número da carteira profissional");
		tfNumeroCarteira.setNullRepresentation("");
		tfNumeroCarteira.setImmediate(false);
		tfNumeroCarteira.setWidth("172px");
		tfNumeroCarteira.setHeight("-1px");
		horizontalLayout_7.addComponent(tfNumeroCarteira);

		// tfSerie
		tfSerie = new TextField();
		tfSerie.setCaption("Série");
		tfSerie.setNullRepresentation("");
		tfSerie.setImmediate(false);
		tfSerie.setWidth("71px");
		tfSerie.setHeight("-1px");
		horizontalLayout_7.addComponent(tfSerie);

		// pdfDataExpedida
		pdfDataExpedida = new PopupDateField();
		pdfDataExpedida.setCaption("Expedida em");
		pdfDataExpedida.setImmediate(false);
		pdfDataExpedida.setWidth("105px");
		pdfDataExpedida.setHeight("-1px");
		horizontalLayout_7.addComponent(pdfDataExpedida);

		// mocUf
		mocUf = new ManyToOneCombo<>();
		mocUf.setCaption("UF");
		mocUf.setImmediate(false);
		mocUf.setWidth("60px");
		mocUf.setHeight("-1px");
		horizontalLayout_7.addComponent(mocUf);

		return horizontalLayout_7;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_8() {
		// common part: create layout
		horizontalLayout_8 = new HorizontalLayout();
		horizontalLayout_8.setImmediate(false);
		horizontalLayout_8.setWidth("-1px");
		horizontalLayout_8.setHeight("-1px");
		horizontalLayout_8.setMargin(false);

		// tfObservacao
		tfObservacao = new TextField();
		tfObservacao.setCaption("Observação");
		tfObservacao.setNullRepresentation("");
		tfObservacao.setImmediate(false);
		tfObservacao.setWidth("920px");
		tfObservacao.setHeight("-1px");
		horizontalLayout_8.addComponent(tfObservacao);

		return horizontalLayout_8;
	}

	/*
	 * public void InitCbs() {
	 * cmbDescontoPlanoSaude.addItem(DescontoPlanoSaudeType.SIM.toString());
	 * cmbDescontoPlanoSaude.addItem(DescontoPlanoSaudeType.NAO.toString());
	 * 
	 * cmbSaiRais.addItem(SaiRaisType.SIM.toString());
	 * cmbSaiRais.addItem(SaiRaisType.NAO.toString());
	 * 
	 * cmbOptante.addItem(OptanteType.SIM.toString());
	 * cmbOptante.addItem(OptanteType.NAO.toString());
	 * 
	 * cmbFormaPagamento.addItem(FormaPagamentoType.DINHEIRO.toString());
	 * cmbFormaPagamento.addItem(FormaPagamentoType.CHEQUE.toString());
	 * cmbFormaPagamento.addItem(FormaPagamentoType.CONTA.toString()); }
	 * 
	 * public String getCbDescontoPlanoSaude() { String cbvalue = new String();
	 * 
	 * if (cmbDescontoPlanoSaude.getValue() == "Sim") { cbvalue = "S"; } else if
	 * (cmbDescontoPlanoSaude.getValue() == "Nao") { cbvalue = "N"; }
	 * 
	 * return cbvalue; }
	 * 
	 * public void setCbDescontoPlanoSaude(String cbDescontoPlanoSaude) { if
	 * (cbDescontoPlanoSaude.equalsIgnoreCase("S")) {
	 * this.cmbDescontoPlanoSaude.setValue("Sim"); } else if
	 * (cbDescontoPlanoSaude.equalsIgnoreCase("N")) {
	 * this.cmbDescontoPlanoSaude.setValue("Nao"); } }
	 * 
	 * public String getCbSaiRais() { String cbvalue = new String(); if
	 * (cmbSaiRais.getValue() == "Sim") { cbvalue = "S"; } else if
	 * (cmbSaiRais.getValue() == "Nao") { cbvalue = "N"; } return cbvalue; }
	 * 
	 * public void setCbSaiRais(String cbSaiRais) { if
	 * (cbSaiRais.equalsIgnoreCase("S")) { this.cmbSaiRais.setValue("Sim"); }
	 * else if (cbSaiRais.equalsIgnoreCase("N")) {
	 * this.cmbSaiRais.setValue("Nao"); } }
	 * 
	 * public String getCbOptante() { String cbvalue = new String(); if
	 * (cmbOptante.getValue() == "Sim") { cbvalue = "S"; } else if
	 * (cmbOptante.getValue() == "Nao") { cbvalue = "N"; } return cbvalue; }
	 * 
	 * public void setCbOptante(String cbOptante) { if
	 * (cbOptante.equalsIgnoreCase("S")) { this.cmbOptante.setValue("Sim"); }
	 * else if (cbOptante.equalsIgnoreCase("N")) {
	 * this.cmbOptante.setValue("Nao"); } }
	 * 
	 * public String getCbFormaPagamento() { String cbvalue = new String(); if
	 * (cmbFormaPagamento.getValue() == "Dinheiro") { cbvalue = "1"; } else if
	 * (cmbFormaPagamento.getValue() == "Cheque") { cbvalue = "2"; } else if
	 * (cmbFormaPagamento.getValue() == "Conta") {
	 * 
	 * cbvalue = "3"; } return cbvalue; }
	 * 
	 * public void setCbFormaPagamento(String cbFormaPagamento) { if
	 * (cbFormaPagamento.equalsIgnoreCase("1")) {
	 * this.cmbFormaPagamento.setValue("Dinheiro"); } else if
	 * (cbFormaPagamento.equalsIgnoreCase("2")) {
	 * this.cmbFormaPagamento.setValue("Cheque"); } else if
	 * (cbFormaPagamento.equalsIgnoreCase("3")) {
	 * 
	 * this.cmbFormaPagamento.setValue("Conta"); } }
	 */

}