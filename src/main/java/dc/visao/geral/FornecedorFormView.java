package dc.visao.geral;

import java.math.BigDecimal;
import java.util.Date;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.geral.Fornecedor;
import dc.entidade.geral.Pessoa;
import dc.entidade.pessoal.AtividadeForCli;
import dc.entidade.pessoal.SituacaoForCli;
import dc.visao.framework.component.BigDecimalConverter;
import dc.visao.framework.component.IntegerConverter;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo;
import dc.visao.framework.util.ComponentUtil;

public class FornecedorFormView extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AutoGenerated
	private GridLayout mainLayout;

	private ManyToOneCombo<Pessoa> cbPessoa;
	private ManyToOneCombo<SituacaoForCli> cbSituacao;
	private ManyToOneCombo<AtividadeForCli> cbAtividade;
	private ManyToOneCombo<ContabilConta> cbContabilConta;
	private PopupDateField dtDesde;
	private TextField txContaRemetente;
	private ComboBox cbGerarFaturamento;
	private ComboBox cbOptanteSimples;
	private ComboBox cbLocalizacao;
	private ComboBox cbSofreRentencao;
	private TextField txPrazoMedioEntrega;
	private TextField txNumDiasPrimeiroVenc;
	private TextField txNumDiasIntervalo;
	private TextField txQuantidadesParcelas;
	private TextField txChequeNominalA;
	private TextArea txaObservacao;

	public FornecedorFormView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private GridLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new GridLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setRows(7);
		mainLayout.setColumns(6);

		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");

		cbPessoa = new ManyToOneCombo<>();
		mainLayout.addComponent(cbPessoa, 0, 0, 5, 0);
		cbPessoa.setCaption("Pessoa");

		cbSituacao = new ManyToOneCombo<>();
		mainLayout.addComponent(cbSituacao, 0, 1, 2, 1);
		cbSituacao.setCaption("Situação");

		cbAtividade = new ManyToOneCombo<>();
		mainLayout.addComponent(cbAtividade, 3, 1, 5, 1);
		cbAtividade.setCaption("Atividade");

		cbContabilConta = new ManyToOneCombo<>();
		mainLayout.addComponent(cbContabilConta, 0, 2, 5, 2);
		cbContabilConta.setCaption("Conta Contábil");

		dtDesde = ComponentUtil.buildPopupDateField("Desde");
		mainLayout.addComponent(dtDesde, 0, 3);

		txContaRemetente = ComponentUtil.buildTextField("Conta Remetente");
		mainLayout.addComponent(txContaRemetente, 1, 3);

		cbGerarFaturamento = ComponentUtil.buildComboBox("Gerar Faturamento");
		mainLayout.addComponent(cbGerarFaturamento, 2, 3);

		cbOptanteSimples = ComponentUtil.buildComboBox("Optante Simples");
		mainLayout.addComponent(cbOptanteSimples, 3, 3);

		cbLocalizacao = ComponentUtil.buildComboBox("Localização");
		mainLayout.addComponent(cbLocalizacao, 4, 3);

		cbSofreRentencao = ComponentUtil.buildComboBox("Sofre Retenção");
		mainLayout.addComponent(cbSofreRentencao, 5, 3);
		//
		txPrazoMedioEntrega = ComponentUtil.buildNumericField("Prazo Médio Entrega");
		txPrazoMedioEntrega.setConverter(new BigDecimalConverter());
		mainLayout.addComponent(txPrazoMedioEntrega, 0, 4);

		txNumDiasPrimeiroVenc = ComponentUtil.buildNumericField("Núm. Dias 1º Vencimento");
		txNumDiasPrimeiroVenc.setConverter(new IntegerConverter());
		mainLayout.addComponent(txNumDiasPrimeiroVenc, 1, 4);

		txNumDiasIntervalo = ComponentUtil.buildNumericField("Núm. Dias Intervalo");
		txNumDiasIntervalo.setConverter(new IntegerConverter());
		mainLayout.addComponent(txNumDiasIntervalo, 2, 4);

		txQuantidadesParcelas = ComponentUtil.buildNumericField("Quantidade Parcelas");
		txQuantidadesParcelas.setConverter(new IntegerConverter());
		mainLayout.addComponent(txQuantidadesParcelas, 3, 4);

		txChequeNominalA = ComponentUtil.buildTextField("Cheque Nominal à");
		mainLayout.addComponent(txChequeNominalA, 4, 4, 5, 4);

		txaObservacao = ComponentUtil.buildTextArea("Observação");
		mainLayout.addComponent(txaObservacao, 0, 5, 5, 6);

		return mainLayout;
	}

	public void preencheBean(Fornecedor currentBean) {
		currentBean.setAtividadeForCli(cbAtividade.getValue());
		currentBean.setChequeNominalA(txChequeNominalA.getValue());
		currentBean.setContabilConta(cbContabilConta.getValue());
		currentBean.setContaRemetente(txContaRemetente.getValue());
		currentBean.setDesde(dtDesde.getValue());
		currentBean.setNumDiasIntervalo((Integer) txNumDiasIntervalo.getConvertedValue());
		currentBean.setNumDiasPrimeiroVencimento((Integer) txNumDiasPrimeiroVenc.getConvertedValue());
		currentBean.setObservacao(txaObservacao.getValue());
		currentBean.setOptanteSimplesNacional(((SimNao) cbOptanteSimples.getValue()).getCodigo());
		currentBean.setPessoa(cbPessoa.getValue());
		currentBean.setPrazoMedioEntrega((BigDecimal) txPrazoMedioEntrega.getConvertedValue());
		currentBean.setQuantidadeParcelas((Integer) txQuantidadesParcelas.getConvertedValue());
		currentBean.setSituacaoForCli(cbSituacao.getValue());
		currentBean.setSofreRetencao(((SimNao) cbOptanteSimples.getValue()).getCodigo());
		currentBean.setLocalizacao(((Localizacao) cbLocalizacao.getValue()).getCodigo());

		if (currentBean.getDataCadastro() == null) {
			currentBean.setDataCadastro(new Date());
		}
	}

	public void preencheForm(Fornecedor currentBean) {
		cbAtividade.setValue(currentBean.getAtividadeForCli());
		txChequeNominalA.setValue(currentBean.getChequeNominalA());
		cbContabilConta.setValue(currentBean.getContabilConta());
		txContaRemetente.setValue(currentBean.getContaRemetente());
		dtDesde.setValue(currentBean.getDesde());
		txNumDiasIntervalo.setConvertedValue(currentBean.getNumDiasIntervalo());
		txNumDiasPrimeiroVenc.setConvertedValue(currentBean.getNumDiasPrimeiroVencimento());
		txaObservacao.setValue(currentBean.getObservacao());
		cbOptanteSimples.setValue(SimNao.getSimNao(currentBean.getOptanteSimplesNacional()));
		cbPessoa.setValue(currentBean.getPessoa());
		txPrazoMedioEntrega.setConvertedValue(currentBean.getPrazoMedioEntrega());
		txQuantidadesParcelas.setConvertedValue(currentBean.getQuantidadeParcelas());
		cbSituacao.setValue(currentBean.getSituacaoForCli());
		cbOptanteSimples.setValue(SimNao.getSimNao(currentBean.getSofreRetencao()));
		cbLocalizacao.setValue(Localizacao.getLocalizacao(currentBean.getLocalizacao()));
	}

	public ManyToOneCombo<Pessoa> getCbPessoa() {
		return cbPessoa;
	}

	public void setCbPessoa(ManyToOneCombo<Pessoa> cbPessoa) {
		this.cbPessoa = cbPessoa;
	}

	public ManyToOneCombo<SituacaoForCli> getCbSituacao() {
		return cbSituacao;
	}

	public void setCbSituacao(ManyToOneCombo<SituacaoForCli> cbSituacao) {
		this.cbSituacao = cbSituacao;
	}

	public ManyToOneCombo<AtividadeForCli> getCbAtividade() {
		return cbAtividade;
	}

	public void setCbAtividade(ManyToOneCombo<AtividadeForCli> cbAtividade) {
		this.cbAtividade = cbAtividade;
	}

	public ManyToOneCombo<ContabilConta> getCbContabilConta() {
		return cbContabilConta;
	}

	public void setCbContabilConta(ManyToOneCombo<ContabilConta> cbContabilConta) {
		this.cbContabilConta = cbContabilConta;
	}

	public PopupDateField getDtDesde() {
		return dtDesde;
	}

	public void setDtDesde(PopupDateField dtDesde) {
		this.dtDesde = dtDesde;
	}

	public TextField getTxContaRemetente() {
		return txContaRemetente;
	}

	public void setTxContaRemetente(TextField txContaRemetente) {
		this.txContaRemetente = txContaRemetente;
	}

	public ComboBox getCbGerarFaturamento() {
		return cbGerarFaturamento;
	}

	public void setCbGerarFaturamento(ComboBox cbGerarFaturamento) {
		this.cbGerarFaturamento = cbGerarFaturamento;
	}

	public ComboBox getCbOptanteSimples() {
		return cbOptanteSimples;
	}

	public void setCbOptanteSimples(ComboBox cbOptanteSimples) {
		this.cbOptanteSimples = cbOptanteSimples;
	}

	public ComboBox getCbLocalizacao() {
		return cbLocalizacao;
	}

	public void setCbLocalizacao(ComboBox cbLocalizacao) {
		this.cbLocalizacao = cbLocalizacao;
	}

	public ComboBox getCbSofreRentencao() {
		return cbSofreRentencao;
	}

	public void setCbSofreRentencao(ComboBox cbSofreRentencao) {
		this.cbSofreRentencao = cbSofreRentencao;
	}

	public TextField getTxPrazoMedioEntrega() {
		return txPrazoMedioEntrega;
	}

	public void setTxPrazoMedioEntrega(TextField txPrazoMedioEntrega) {
		this.txPrazoMedioEntrega = txPrazoMedioEntrega;
	}

	public TextField getTxNumDiasPrimeiroVenc() {
		return txNumDiasPrimeiroVenc;
	}

	public void setTxNumDiasPrimeiroVenc(TextField txNumDiasPrimeiroVenc) {
		this.txNumDiasPrimeiroVenc = txNumDiasPrimeiroVenc;
	}

	public TextField getTxNumDiasIntervalo() {
		return txNumDiasIntervalo;
	}

	public void setTxNumDiasIntervalo(TextField txNumDiasIntervalo) {
		this.txNumDiasIntervalo = txNumDiasIntervalo;
	}

	public TextField getTxQuantidadesParcelas() {
		return txQuantidadesParcelas;
	}

	public void setTxQuantidadesParcelas(TextField txQuantidadesParcelas) {
		this.txQuantidadesParcelas = txQuantidadesParcelas;
	}

	public TextField getTxChequeNominalA() {
		return txChequeNominalA;
	}

	public void setTxChequeNominalA(TextField txChequeNominalA) {
		this.txChequeNominalA = txChequeNominalA;
	}

	public TextArea getTxaObservacao() {
		return txaObservacao;
	}

	public void setTxaObservacao(TextArea txaObservacao) {
		this.txaObservacao = txaObservacao;
	}

	public enum SimNao {

		SIM("Sim", "S"), NAO("Não", "N");

		private SimNao(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static SimNao getSimNao(String codigo) {
			for (SimNao e : SimNao.values()) {
				if (e.getCodigo().equalsIgnoreCase(codigo)) {
					return e;
				}
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

	public enum Localizacao {

		Nacional("Nacional", "N"), Internacional("Internacional", "I");

		private Localizacao(String label, String codigo) {
			this.label = label;
			this.codigo = codigo;
		}

		private String label;
		private String codigo;

		public static Localizacao getLocalizacao(String codigo) {
			for (Localizacao e : Localizacao.values()) {
				if (e.getCodigo().equalsIgnoreCase(codigo)) {
					return e;
				}
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

}