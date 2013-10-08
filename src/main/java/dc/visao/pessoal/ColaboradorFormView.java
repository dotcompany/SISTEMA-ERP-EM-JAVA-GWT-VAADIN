package dc.visao.pessoal;

import java.util.List;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.diversos.Setor;
import dc.entidade.financeiro.Sindicato;
import dc.entidade.geral.NivelFormacao;
import dc.entidade.geral.Pessoa;
import dc.entidade.geral.UF;
import dc.entidade.pessoal.Cargo;
import dc.entidade.pessoal.SituacaoColaborador;
import dc.entidade.pessoal.TipoColaborador;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;

public class ColaboradorFormView extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_8;
	@AutoGenerated
	private TextField txtObservacao;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_7;
	@AutoGenerated
	private PopupDateField dtExpedida;
	@AutoGenerated
	private TextField txtSerie;
	@AutoGenerated
	private TextField txtNumeroCarteira;
	@AutoGenerated
	private TextField txtDigitoo;
	@AutoGenerated
	private TextField txtAgencia1;
	@AutoGenerated
	private TextField txtBanco1;
	@AutoGenerated
	private TextField txtNumeroPis;
	@AutoGenerated
	private PopupDateField dtCadastroPis;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_6;
	@AutoGenerated
	private TextField txtDigito1;
	@AutoGenerated
	private TextField txtConta;
	@AutoGenerated
	private TextField txtDigito;
	@AutoGenerated
	private TextField txtAgencia;
	@AutoGenerated
	private TextField txtBanco;
	@AutoGenerated
	private ComboBox cmbFormaPagamento;
	@AutoGenerated
	private TextField txtCodigoDemissaoCaged;
	@AutoGenerated
	private TextField txtCodigoAdmissao;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_5;
	@AutoGenerated
	private PopupDateField dtDataOpcao;
	@AutoGenerated
	private ComboBox cmbOptante;
	@AutoGenerated
	private TextField txtCodigoDemissao;
	@AutoGenerated
	private TextField txtOcorrencia;
	@AutoGenerated
	private TextField txtCategoria;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_4;
	@AutoGenerated
	private PopupDateField dtVencimento;
	@AutoGenerated
	private PopupDateField dtUltimoExame;
	@AutoGenerated
	private TextField txtCodigoTurma;
	@AutoGenerated
	private ComboBox cmbSaiRais;
	@AutoGenerated
	private ComboBox cmbDescontoPlanoSaude;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private PopupDateField dtDemissao;
	@AutoGenerated
	private PopupDateField dtTransferencia;
	@AutoGenerated
	private PopupDateField dtVencimentoFerias;
	@AutoGenerated
	private PopupDateField dtAdmissao;
	@AutoGenerated
	private PopupDateField dtCadastro;
	@AutoGenerated
	private TextField txtMatricula;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;

	private ManyToOneCombo<Pessoa> cmbPessoa;
	private ManyToOneCombo<TipoColaborador> cmbTipoColaborador;
	private ManyToOneCombo<SituacaoColaborador> cmbSituacaoColaborador;
	private ManyToOneCombo<Sindicato> cmbSindicato;
	private ManyToOneCombo<NivelFormacao> cmbNivelFormacao;
	private ManyToOneCombo<Cargo> cmbCargo;
	private ManyToOneCombo<ContabilConta> cmbContaContabil;
	private ManyToOneCombo<Setor> cmbSetor;
	private ManyToOneCombo<UF> cmbUf;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ColaboradorFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

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
		mainLayout.setRows(8);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1, 0, 0);

		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		mainLayout.addComponent(horizontalLayout_2, 0, 1);

		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		mainLayout.addComponent(horizontalLayout_3, 0, 2);

		// horizontalLayout_4
		horizontalLayout_4 = buildHorizontalLayout_4();
		mainLayout.addComponent(horizontalLayout_4, 0, 3);

		// horizontalLayout_5
		horizontalLayout_5 = buildHorizontalLayout_5();
		mainLayout.addComponent(horizontalLayout_5, 0, 4);

		// horizontalLayout_6
		horizontalLayout_6 = buildHorizontalLayout_6();
		mainLayout.addComponent(horizontalLayout_6, 0, 5);

		// horizontalLayout_7
		horizontalLayout_7 = buildHorizontalLayout_7();
		mainLayout.addComponent(horizontalLayout_7, 0, 6);

		// horizontalLayout_8
		horizontalLayout_8 = buildHorizontalLayout_8();
		mainLayout.addComponent(horizontalLayout_8, 0, 7);

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

		// cmbPessoa
		cmbPessoa = new ManyToOneCombo<>();
		cmbPessoa.setCaption("Pessoa");
		cmbPessoa.setImmediate(false);
		cmbPessoa.setWidth("-1px");
		cmbPessoa.setHeight("-1px");
		horizontalLayout_1.addComponent(cmbPessoa);

		// cmbTipoColaborador
		cmbTipoColaborador = new ManyToOneCombo<>();
		cmbTipoColaborador.setCaption("Tipo Colaborador");
		cmbTipoColaborador.setImmediate(false);
		cmbTipoColaborador.setWidth("-1px");
		cmbTipoColaborador.setHeight("-1px");
		horizontalLayout_1.addComponent(cmbTipoColaborador);

		// cmbSituacaoColaborador
		cmbSituacaoColaborador = new ManyToOneCombo<>();
		cmbSituacaoColaborador.setCaption("Situação Colaborador");
		cmbSituacaoColaborador.setImmediate(false);
		cmbSituacaoColaborador.setWidth("-1px");
		cmbSituacaoColaborador.setHeight("-1px");
		horizontalLayout_1.addComponent(cmbSituacaoColaborador);

		// cmbSindicato
		cmbSindicato = new ManyToOneCombo<>();
		cmbSindicato.setCaption("Sindicato");
		cmbSindicato.setImmediate(false);
		cmbSindicato.setWidth("176px");
		cmbSindicato.setHeight("-1px");
		horizontalLayout_1.addComponent(cmbSindicato);

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

		// cmbNivelFormacao
		cmbNivelFormacao = new ManyToOneCombo<>();
		cmbNivelFormacao.setCaption("Nível Formação");
		cmbNivelFormacao.setImmediate(false);
		cmbNivelFormacao.setWidth("-1px");
		cmbNivelFormacao.setHeight("-1px");
		horizontalLayout_2.addComponent(cmbNivelFormacao);

		// cmbCargo
		cmbCargo = new ManyToOneCombo<>();
		cmbCargo.setCaption("Cargo");
		cmbCargo.setImmediate(false);
		cmbCargo.setWidth("-1px");
		cmbCargo.setHeight("-1px");
		horizontalLayout_2.addComponent(cmbCargo);

		// cmbContaContabil
		cmbContaContabil = new ManyToOneCombo<>();
		cmbContaContabil.setCaption("Conta Contábil");
		cmbContaContabil.setImmediate(false);
		cmbContaContabil.setWidth("-1px");
		cmbContaContabil.setHeight("-1px");
		horizontalLayout_2.addComponent(cmbContaContabil);

		// cmbSetor
		cmbSetor = new ManyToOneCombo<>();
		cmbSetor.setCaption("Setor");
		cmbSetor.setImmediate(false);
		cmbSetor.setWidth("176px");
		cmbSetor.setHeight("-1px");
		horizontalLayout_2.addComponent(cmbSetor);

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

		// txtMatricula
		txtMatricula = new TextField();
		txtMatricula.setCaption("Matrícula");
		txtMatricula.setImmediate(false);
		txtMatricula.setWidth("100px");
		txtMatricula.setHeight("-1px");
		horizontalLayout_3.addComponent(txtMatricula);

		// dtCadastro
		dtCadastro = new PopupDateField();
		dtCadastro.setCaption("Cadastro");
		dtCadastro.setImmediate(false);
		dtCadastro.setWidth("111px");
		dtCadastro.setHeight("-1px");
		horizontalLayout_3.addComponent(dtCadastro);

		// dtAdmissao
		dtAdmissao = new PopupDateField();
		dtAdmissao.setCaption("Admissão");
		dtAdmissao.setImmediate(false);
		dtAdmissao.setWidth("99px");
		dtAdmissao.setHeight("-1px");
		horizontalLayout_3.addComponent(dtAdmissao);

		// dtVencimentoFerias
		dtVencimentoFerias = new PopupDateField();
		dtVencimentoFerias.setCaption("Vencimento Férias");
		dtVencimentoFerias.setImmediate(false);
		dtVencimentoFerias.setWidth("115px");
		dtVencimentoFerias.setHeight("-1px");
		horizontalLayout_3.addComponent(dtVencimentoFerias);

		// dtTransferencia
		dtTransferencia = new PopupDateField();
		dtTransferencia.setCaption("Transferência");
		dtTransferencia.setImmediate(false);
		dtTransferencia.setWidth("109px");
		dtTransferencia.setHeight("-1px");
		horizontalLayout_3.addComponent(dtTransferencia);

		// dtDemissao
		dtDemissao = new PopupDateField();
		dtDemissao.setCaption("Demissão");
		dtDemissao.setImmediate(false);
		dtDemissao.setWidth("115px");
		dtDemissao.setHeight("-1px");
		horizontalLayout_3.addComponent(dtDemissao);

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

		// cmbDescontoPlanoSaude
		cmbDescontoPlanoSaude = new ComboBox();
		cmbDescontoPlanoSaude.setCaption("Desconto Plano Saúde");
		cmbDescontoPlanoSaude.setImmediate(false);
		cmbDescontoPlanoSaude.setWidth("-1px");
		cmbDescontoPlanoSaude.setHeight("-1px");
		horizontalLayout_4.addComponent(cmbDescontoPlanoSaude);

		// cmbSaiRais
		cmbSaiRais = new ComboBox();
		cmbSaiRais.setCaption("Sai na RAIS");
		cmbSaiRais.setImmediate(false);
		cmbSaiRais.setWidth("132px");
		cmbSaiRais.setHeight("-1px");
		horizontalLayout_4.addComponent(cmbSaiRais);

		// txtCodigoTurma
		txtCodigoTurma = new TextField();
		txtCodigoTurma.setCaption("Código Turma");
		txtCodigoTurma.setImmediate(false);
		txtCodigoTurma.setWidth("-1px");
		txtCodigoTurma.setHeight("-1px");
		horizontalLayout_4.addComponent(txtCodigoTurma);

		// dtUltimoExame
		dtUltimoExame = new PopupDateField();
		dtUltimoExame.setCaption("Último Exame em:");
		dtUltimoExame.setImmediate(false);
		dtUltimoExame.setWidth("120px");
		dtUltimoExame.setHeight("-1px");
		horizontalLayout_4.addComponent(dtUltimoExame);

		// dtVencimento
		dtVencimento = new PopupDateField();
		dtVencimento.setCaption("Vencimento");
		dtVencimento.setImmediate(false);
		dtVencimento.setWidth("100px");
		dtVencimento.setHeight("-1px");
		horizontalLayout_4.addComponent(dtVencimento);

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

		// txtCategoria
		txtCategoria = new TextField();
		txtCategoria.setCaption("Categoria");
		txtCategoria.setImmediate(false);
		txtCategoria.setWidth("120px");
		txtCategoria.setHeight("-1px");
		horizontalLayout_5.addComponent(txtCategoria);

		// txtOcorrencia
		txtOcorrencia = new TextField();
		txtOcorrencia.setCaption("Ocorrência");
		txtOcorrencia.setImmediate(false);
		txtOcorrencia.setWidth("-1px");
		txtOcorrencia.setHeight("-1px");
		horizontalLayout_5.addComponent(txtOcorrencia);

		// txtCodigoDemissao
		txtCodigoDemissao = new TextField();
		txtCodigoDemissao.setCaption("Código Demissão");
		txtCodigoDemissao.setImmediate(false);
		txtCodigoDemissao.setWidth("-1px");
		txtCodigoDemissao.setHeight("-1px");
		horizontalLayout_5.addComponent(txtCodigoDemissao);

		// cmbOptante
		cmbOptante = new ComboBox();
		cmbOptante.setCaption("Optante");
		cmbOptante.setImmediate(false);
		cmbOptante.setWidth("108px");
		cmbOptante.setHeight("-1px");
		horizontalLayout_5.addComponent(cmbOptante);

		// dtDataOpcao
		dtDataOpcao = new PopupDateField();
		dtDataOpcao.setCaption("Data Opção");
		dtDataOpcao.setImmediate(false);
		dtDataOpcao.setWidth("146px");
		dtDataOpcao.setHeight("-1px");
		horizontalLayout_5.addComponent(dtDataOpcao);

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

		// txtCodigoAdmissao
		txtCodigoAdmissao = new TextField();
		txtCodigoAdmissao.setCaption("Código Admissão");
		txtCodigoAdmissao.setImmediate(false);
		txtCodigoAdmissao.setWidth("106px");
		txtCodigoAdmissao.setHeight("-1px");
		horizontalLayout_6.addComponent(txtCodigoAdmissao);

		// txtCodigoDemissaoCaged
		txtCodigoDemissaoCaged = new TextField();
		txtCodigoDemissaoCaged.setCaption("Código Demissão Caged");
		txtCodigoDemissaoCaged.setImmediate(false);
		txtCodigoDemissaoCaged.setWidth("-1px");
		txtCodigoDemissaoCaged.setHeight("-1px");
		horizontalLayout_6.addComponent(txtCodigoDemissaoCaged);

		// cmbFormaPagamento
		cmbFormaPagamento = new ComboBox();
		cmbFormaPagamento.setCaption("Forma Pagamento");
		cmbFormaPagamento.setImmediate(false);
		cmbFormaPagamento.setWidth("141px");
		cmbFormaPagamento.setHeight("-1px");
		horizontalLayout_6.addComponent(cmbFormaPagamento);

		// txtBanco
		txtBanco = new TextField();
		txtBanco.setCaption("Banco");
		txtBanco.setImmediate(false);
		txtBanco.setWidth("-1px");
		txtBanco.setHeight("-1px");
		horizontalLayout_6.addComponent(txtBanco);

		// txtAgencia
		txtAgencia = new TextField();
		txtAgencia.setCaption("Agência");
		txtAgencia.setImmediate(false);
		txtAgencia.setWidth("68px");
		txtAgencia.setHeight("-1px");
		horizontalLayout_6.addComponent(txtAgencia);

		// txtDigito
		txtDigito = new TextField();
		txtDigito.setCaption("Dígito");
		txtDigito.setImmediate(false);
		txtDigito.setWidth("33px");
		txtDigito.setHeight("-1px");
		horizontalLayout_6.addComponent(txtDigito);

		// txtConta
		txtConta = new TextField();
		txtConta.setCaption("Conta");
		txtConta.setImmediate(false);
		txtConta.setWidth("81px");
		txtConta.setHeight("-1px");
		horizontalLayout_6.addComponent(txtConta);

		// txtDigito1
		txtDigito1 = new TextField();
		txtDigito1.setCaption("Dígito");
		txtDigito1.setImmediate(false);
		txtDigito1.setWidth("37px");
		txtDigito1.setHeight("-1px");
		horizontalLayout_6.addComponent(txtDigito1);

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

		// dtCadastroPis
		dtCadastroPis = new PopupDateField();
		dtCadastroPis.setCaption("Cadastro Pis");
		dtCadastroPis.setImmediate(false);
		dtCadastroPis.setWidth("126px");
		dtCadastroPis.setHeight("-1px");
		horizontalLayout_7.addComponent(dtCadastroPis);

		// txtNumeroPis
		txtNumeroPis = new TextField();
		txtNumeroPis.setCaption("Número Pis");
		txtNumeroPis.setImmediate(false);
		txtNumeroPis.setWidth("91px");
		txtNumeroPis.setHeight("-1px");
		horizontalLayout_7.addComponent(txtNumeroPis);

		// txtBanco1
		txtBanco1 = new TextField();
		txtBanco1.setCaption("Banco");
		txtBanco1.setImmediate(false);
		txtBanco1.setWidth("-1px");
		txtBanco1.setHeight("-1px");
		horizontalLayout_7.addComponent(txtBanco1);

		// txtAgencia1
		txtAgencia1 = new TextField();
		txtAgencia1.setCaption("Agência");
		txtAgencia1.setImmediate(false);
		txtAgencia1.setWidth("93px");
		txtAgencia1.setHeight("-1px");
		horizontalLayout_7.addComponent(txtAgencia1);

		// txtDigitoo
		txtDigitoo = new TextField();
		txtDigitoo.setCaption("Dígito");
		txtDigitoo.setImmediate(false);
		txtDigitoo.setWidth("36px");
		txtDigitoo.setHeight("-1px");
		horizontalLayout_7.addComponent(txtDigitoo);

		// txtNumeroCarteira
		txtNumeroCarteira = new TextField();
		txtNumeroCarteira.setCaption("Número Carteira Profissional");
		txtNumeroCarteira.setImmediate(false);
		txtNumeroCarteira.setWidth("172px");
		txtNumeroCarteira.setHeight("-1px");
		horizontalLayout_7.addComponent(txtNumeroCarteira);

		// txtSerie
		txtSerie = new TextField();
		txtSerie.setCaption("Série");
		txtSerie.setImmediate(false);
		txtSerie.setWidth("71px");
		txtSerie.setHeight("-1px");
		horizontalLayout_7.addComponent(txtSerie);

		// dtExpedida
		dtExpedida = new PopupDateField();
		dtExpedida.setCaption("Expedida em");
		dtExpedida.setImmediate(false);
		dtExpedida.setWidth("105px");
		dtExpedida.setHeight("-1px");
		horizontalLayout_7.addComponent(dtExpedida);

		// txtUf
		cmbUf = new ManyToOneCombo<>();
		cmbUf.setCaption("UF");
		cmbUf.setImmediate(false);
		cmbUf.setWidth("60px");
		cmbUf.setHeight("-1px");
		horizontalLayout_7.addComponent(cmbUf);

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

		// txtObservacao
		txtObservacao = new TextField();
		txtObservacao.setCaption("Observação");
		txtObservacao.setImmediate(false);
		txtObservacao.setWidth("920px");
		txtObservacao.setHeight("-1px");
		horizontalLayout_8.addComponent(txtObservacao);

		return horizontalLayout_8;
	}
	
	public void InitCbs(List<String> colaboradorTipoList, List<String> auxLista, List<String> auxColaborador, List<String> colaborador) {
		for (String str : colaboradorTipoList) {
			cmbDescontoPlanoSaude.addItem(str.toString());
		}

		for (String str : auxLista) {
			cmbSaiRais.addItem(str.toString());
		}
		for (String str : auxColaborador) {
			cmbOptante.addItem(str.toString());
		}
		for (String str : colaborador) {
			cmbFormaPagamento.addItem(str.toString());
		}
	}

	/*public void InitCbs() {
		cmbDescontoPlanoSaude.addItem(DescontoPlanoSaudeType.SIM.toString());
		cmbDescontoPlanoSaude.addItem(DescontoPlanoSaudeType.NAO.toString());

		cmbSaiRais.addItem(SaiRaisType.SIM.toString());
		cmbSaiRais.addItem(SaiRaisType.NAO.toString());

		cmbOptante.addItem(OptanteType.SIM.toString());
		cmbOptante.addItem(OptanteType.NAO.toString());

		cmbFormaPagamento.addItem(FormaPagamentoType.DINHEIRO.toString());
		cmbFormaPagamento.addItem(FormaPagamentoType.CHEQUE.toString());
		cmbFormaPagamento.addItem(FormaPagamentoType.CONTA.toString());
	}

	public String getCbDescontoPlanoSaude() {
		String cbvalue = new String();

		if (cmbDescontoPlanoSaude.getValue() == "Sim") {
			cbvalue = "S";
		} else if (cmbDescontoPlanoSaude.getValue() == "Nao") {
			cbvalue = "N";
		}

		return cbvalue;
	}

	public void setCbDescontoPlanoSaude(String cbDescontoPlanoSaude) {
		if (cbDescontoPlanoSaude.equalsIgnoreCase("S")) {
			this.cmbDescontoPlanoSaude.setValue("Sim");
		} else if (cbDescontoPlanoSaude.equalsIgnoreCase("N")) {
			this.cmbDescontoPlanoSaude.setValue("Nao");
		}
	}

	public String getCbSaiRais() {
		String cbvalue = new String();
		if (cmbSaiRais.getValue() == "Sim") {
			cbvalue = "S";
		} else if (cmbSaiRais.getValue() == "Nao") {
			cbvalue = "N";
		}
		return cbvalue;
	}

	public void setCbSaiRais(String cbSaiRais) {
		if (cbSaiRais.equalsIgnoreCase("S")) {
			this.cmbSaiRais.setValue("Sim");
		} else if (cbSaiRais.equalsIgnoreCase("N")) {
			this.cmbSaiRais.setValue("Nao");
		}
	}

	public String getCbOptante() {
		String cbvalue = new String();
		if (cmbOptante.getValue() == "Sim") {
			cbvalue = "S";
		} else if (cmbOptante.getValue() == "Nao") {
			cbvalue = "N";
		}
		return cbvalue;
	}

	public void setCbOptante(String cbOptante) {
		if (cbOptante.equalsIgnoreCase("S")) {
			this.cmbOptante.setValue("Sim");
		} else if (cbOptante.equalsIgnoreCase("N")) {
			this.cmbOptante.setValue("Nao");
		}
	}

	public String getCbFormaPagamento() {
		String cbvalue = new String();
		if (cmbFormaPagamento.getValue() == "Dinheiro") {
			cbvalue = "1";
		} else if (cmbFormaPagamento.getValue() == "Cheque") {
			cbvalue = "2";
		} else if (cmbFormaPagamento.getValue() == "Conta") {

			cbvalue = "3";
		}
		return cbvalue;
	}

	public void setCbFormaPagamento(String cbFormaPagamento) {
		if (cbFormaPagamento.equalsIgnoreCase("1")) {
			this.cmbFormaPagamento.setValue("Dinheiro");
		} else if (cbFormaPagamento.equalsIgnoreCase("2")) {
			this.cmbFormaPagamento.setValue("Cheque");
		} else if (cbFormaPagamento.equalsIgnoreCase("3")) {

			this.cmbFormaPagamento.setValue("Conta");
		}
	}*/

	public GridLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(GridLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public HorizontalLayout getHorizontalLayout_8() {
		return horizontalLayout_8;
	}

	public void setHorizontalLayout_8(HorizontalLayout horizontalLayout_8) {
		this.horizontalLayout_8 = horizontalLayout_8;
	}

	public TextField getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(TextField txtObservacao) {
		this.txtObservacao = txtObservacao;
	}

	public HorizontalLayout getHorizontalLayout_7() {
		return horizontalLayout_7;
	}

	public void setHorizontalLayout_7(HorizontalLayout horizontalLayout_7) {
		this.horizontalLayout_7 = horizontalLayout_7;
	}

	public PopupDateField getDtExpedida() {
		return dtExpedida;
	}

	public void setDtExpedida(PopupDateField dtExpedida) {
		this.dtExpedida = dtExpedida;
	}

	public TextField getTxtSerie() {
		return txtSerie;
	}

	public void setTxtSerie(TextField txtSerie) {
		this.txtSerie = txtSerie;
	}

	public TextField getTxtNumeroCarteira() {
		return txtNumeroCarteira;
	}

	public void setTxtNumeroCarteira(TextField txtNumeroCarteira) {
		this.txtNumeroCarteira = txtNumeroCarteira;
	}

	public TextField getTxtDigitoo() {
		return txtDigitoo;
	}

	public void setTxtDigitoo(TextField txtDigitoo) {
		this.txtDigitoo = txtDigitoo;
	}

	public TextField getTxtAgencia1() {
		return txtAgencia1;
	}

	public void setTxtAgencia1(TextField txtAgencia1) {
		this.txtAgencia1 = txtAgencia1;
	}

	public TextField getTxtBanco1() {
		return txtBanco1;
	}

	public void setTxtBanco1(TextField txtBanco1) {
		this.txtBanco1 = txtBanco1;
	}

	public TextField getTxtNumeroPis() {
		return txtNumeroPis;
	}

	public void setTxtNumeroPis(TextField txtNumeroPis) {
		this.txtNumeroPis = txtNumeroPis;
	}

	public PopupDateField getDtCadastroPis() {
		return dtCadastroPis;
	}

	public void setDtCadastroPis(PopupDateField dtCadastroPis) {
		this.dtCadastroPis = dtCadastroPis;
	}

	public HorizontalLayout getHorizontalLayout_6() {
		return horizontalLayout_6;
	}

	public void setHorizontalLayout_6(HorizontalLayout horizontalLayout_6) {
		this.horizontalLayout_6 = horizontalLayout_6;
	}

	public TextField getTxtDigito1() {
		return txtDigito1;
	}

	public void setTxtDigito1(TextField txtDigito1) {
		this.txtDigito1 = txtDigito1;
	}

	public TextField getTxtConta() {
		return txtConta;
	}

	public void setTxtConta(TextField txtConta) {
		this.txtConta = txtConta;
	}

	public TextField getTxtDigito() {
		return txtDigito;
	}

	public void setTxtDigito(TextField txtDigito) {
		this.txtDigito = txtDigito;
	}

	public TextField getTxtAgencia() {
		return txtAgencia;
	}

	public void setTxtAgencia(TextField txtAgencia) {
		this.txtAgencia = txtAgencia;
	}

	public TextField getTxtBanco() {
		return txtBanco;
	}

	public void setTxtBanco(TextField txtBanco) {
		this.txtBanco = txtBanco;
	}

	public TextField getTxtCodigoDemissaoCaged() {
		return txtCodigoDemissaoCaged;
	}

	public void setTxtCodigoDemissaoCaged(TextField txtCodigoDemissaoCaged) {
		this.txtCodigoDemissaoCaged = txtCodigoDemissaoCaged;
	}

	public TextField getTxtCodigoAdmissao() {
		return txtCodigoAdmissao;
	}

	public void setTxtCodigoAdmissao(TextField txtCodigoAdmissao) {
		this.txtCodigoAdmissao = txtCodigoAdmissao;
	}

	public HorizontalLayout getHorizontalLayout_5() {
		return horizontalLayout_5;
	}

	public void setHorizontalLayout_5(HorizontalLayout horizontalLayout_5) {
		this.horizontalLayout_5 = horizontalLayout_5;
	}

	public PopupDateField getDtDataOpcao() {
		return dtDataOpcao;
	}

	public void setDtDataOpcao(PopupDateField dtDataOpcao) {
		this.dtDataOpcao = dtDataOpcao;
	}

	public TextField getTxtCodigoDemissao() {
		return txtCodigoDemissao;
	}

	public void setTxtCodigoDemissao(TextField txtCodigoDemissao) {
		this.txtCodigoDemissao = txtCodigoDemissao;
	}

	public TextField getTxtOcorrencia() {
		return txtOcorrencia;
	}

	public void setTxtOcorrencia(TextField txtOcorrencia) {
		this.txtOcorrencia = txtOcorrencia;
	}

	public TextField getTxtCategoria() {
		return txtCategoria;
	}

	public void setTxtCategoria(TextField txtCategoria) {
		this.txtCategoria = txtCategoria;
	}

	public HorizontalLayout getHorizontalLayout_4() {
		return horizontalLayout_4;
	}

	public void setHorizontalLayout_4(HorizontalLayout horizontalLayout_4) {
		this.horizontalLayout_4 = horizontalLayout_4;
	}

	public PopupDateField getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(PopupDateField dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public PopupDateField getDtUltimoExame() {
		return dtUltimoExame;
	}

	public void setDtUltimoExame(PopupDateField dtUltimoExame) {
		this.dtUltimoExame = dtUltimoExame;
	}

	public TextField getTxtCodigoTurma() {
		return txtCodigoTurma;
	}

	public void setTxtCodigoTurma(TextField txtCodigoTurma) {
		this.txtCodigoTurma = txtCodigoTurma;
	}

	public HorizontalLayout getHorizontalLayout_3() {
		return horizontalLayout_3;
	}

	public void setHorizontalLayout_3(HorizontalLayout horizontalLayout_3) {
		this.horizontalLayout_3 = horizontalLayout_3;
	}

	public PopupDateField getDtDemissao() {
		return dtDemissao;
	}

	public void setDtDemissao(PopupDateField dtDemissao) {
		this.dtDemissao = dtDemissao;
	}

	public PopupDateField getDtTransferencia() {
		return dtTransferencia;
	}

	public void setDtTransferencia(PopupDateField dtTransferencia) {
		this.dtTransferencia = dtTransferencia;
	}

	public PopupDateField getDtVencimentoFerias() {
		return dtVencimentoFerias;
	}

	public void setDtVencimentoFerias(PopupDateField dtVencimentoFerias) {
		this.dtVencimentoFerias = dtVencimentoFerias;
	}

	public PopupDateField getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(PopupDateField dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public PopupDateField getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(PopupDateField dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public TextField getTxtMatricula() {
		return txtMatricula;
	}

	public void setTxtMatricula(TextField txtMatricula) {
		this.txtMatricula = txtMatricula;
	}

	public HorizontalLayout getHorizontalLayout_2() {
		return horizontalLayout_2;
	}

	public void setHorizontalLayout_2(HorizontalLayout horizontalLayout_2) {
		this.horizontalLayout_2 = horizontalLayout_2;
	}

	public HorizontalLayout getHorizontalLayout_1() {
		return horizontalLayout_1;
	}

	public void setHorizontalLayout_1(HorizontalLayout horizontalLayout_1) {
		this.horizontalLayout_1 = horizontalLayout_1;
	}

	public ManyToOneCombo<Pessoa> getCmbPessoa() {
		return cmbPessoa;
	}

	public void setCmbPessoa(ManyToOneCombo<Pessoa> cmbPessoa) {
		this.cmbPessoa = cmbPessoa;
	}

	public ManyToOneCombo<TipoColaborador> getCmbTipoColaborador() {
		return cmbTipoColaborador;
	}

	public void setCmbTipoColaborador(
			ManyToOneCombo<TipoColaborador> cmbTipoColaborador) {
		this.cmbTipoColaborador = cmbTipoColaborador;
	}

	public ManyToOneCombo<SituacaoColaborador> getCmbSituacaoColaborador() {
		return cmbSituacaoColaborador;
	}

	public void setCmbSituacaoColaborador(
			ManyToOneCombo<SituacaoColaborador> cmbSituacaoColaborador) {
		this.cmbSituacaoColaborador = cmbSituacaoColaborador;
	}

	public ManyToOneCombo<Sindicato> getCmbSindicato() {
		return cmbSindicato;
	}

	public void setCmbSindicato(ManyToOneCombo<Sindicato> cmbSindicato) {
		this.cmbSindicato = cmbSindicato;
	}

	public ManyToOneCombo<NivelFormacao> getCmbNivelFormacao() {
		return cmbNivelFormacao;
	}

	public void setCmbNivelFormacao(
			ManyToOneCombo<NivelFormacao> cmbNivelFormacao) {
		this.cmbNivelFormacao = cmbNivelFormacao;
	}

	public ManyToOneCombo<Cargo> getCmbCargo() {
		return cmbCargo;
	}

	public void setCmbCargo(ManyToOneCombo<Cargo> cmbCargo) {
		this.cmbCargo = cmbCargo;
	}

	public ManyToOneCombo<ContabilConta> getCmbContaContabil() {
		return cmbContaContabil;
	}

	public void setCmbContaContabil(
			ManyToOneCombo<ContabilConta> cmbContaContabil) {
		this.cmbContaContabil = cmbContaContabil;
	}

	public ManyToOneCombo<Setor> getCmbSetor() {
		return cmbSetor;
	}

	public void setCmbSetor(ManyToOneCombo<Setor> cmbSetor) {
		this.cmbSetor = cmbSetor;
	}

	public ManyToOneCombo<UF> getCmbUf() {
		return cmbUf;
	}

	public void setCmbUf(ManyToOneCombo<UF> cmbUf) {
		this.cmbUf = cmbUf;
	}

}