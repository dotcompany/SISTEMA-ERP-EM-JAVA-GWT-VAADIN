package dc.entidade.geral.pessoal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.LocalizacaoEn;
import dc.control.enums.SimNaoEn;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.patrimonio.BemEntity;

@Entity
@Table(name = "fornecedor")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FornecedorEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_id_seq")
	@SequenceGenerator(name = "fornecedor_id_seq", sequenceName = "fornecedor_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption(value = "Desde")
	@Column(name = "DESDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date desde;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption(value = "Data de cadastro")
	@Column(name = "DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Caption(value = "Cheque nominal à")
	@Column(name = "CHEQUE_NOMINAL_A")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String chequeNominalA = "";

	@Field
	@Caption(value = "Observação")
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	@Field
	@Caption(value = "Conta do remetente")
	@Column(name = "CONTA_REMETENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaRemetente = "";

	@Field
	@Caption(value = "Prazo médio de entrega")
	@Column(name = "PRAZO_MEDIO_ENTREGA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal prazoMedioEntrega = new BigDecimal(0);

	@Field
	@Caption(value = "Número de dias - Primeiro vencimento")
	@Column(name = "NUM_DIAS_PRIMEIRO_VENCIMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numDiasPrimeiroVencimento = new Integer(0);

	@Field
	@Caption(value = "Número de dias - Intervalo")
	@Column(name = "NUM_DIAS_INTERVALO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numDiasIntervalo = new Integer(0);

	@Field
	@Caption(value = "Quantidade de parcelas")
	@Column(name = "QUANTIDADE_PARCELAS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeParcelas = new Integer(0);

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Optante do simples nacional")
	@Column(name = "OPTANTE_SIMPLES_NACIONAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn optanteSimplesNacional;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Localização")
	@Column(name = "LOCALIZACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private LocalizacaoEn localizacao;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Sofre retenção")
	@Column(name = "SOFRE_RETENCAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn sofreRetencao;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption(value = "Gera faturamento")
	@Column(name = "GERA_FATURAMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn geraFaturamento;

	/**
	 * REFERENCIA - FK
	 */

	// @IndexedEmbedded
	// @Analyzer(definition = "dc_combo_analyzer")
	// @Caption("Pessoa")
	// @ManyToOne(optional = false, fetch = FetchType.EAGER)
	// @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	// private PessoaEntity pessoa;

	@Caption("Pessoa")
	@OneToOne
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	private PessoaEntity pessoa;

	@Caption("Situação fornecedor / cliente")
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_SITUACAO_FOR_CLI")
	private SituacaoForCliEntity situacaoForCli;

	@Caption("Atividade fornecedor / cliente")
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ATIVIDADE_FOR_CLI")
	private AtividadeForCliEntity atividadeForCli;

	@Caption("Conta contábil")
	@ManyToOne
	@JoinColumn(name = "ID_CONTABIL_CONTA")
	private ContabilContaEntity contabilConta;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<BemEntity> bemList;

	/**
	 * Módulo: NFE
	 */

	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<NfeCabecalhoEntity> nfeCabecalhoList;

	/**
	 * TRANSIENT
	 */

	@Transient
	public String getCaption() {
		Object id = this.id;

		if (id == null) {
			id = "";
		}

		String nome = "";

		if (getPessoa() != null && getPessoa().getNome() != null) {
			nome = getPessoa().getNome();
		}

		return "[" + id + "] " + nome;
	}

	/**
	 * CONSTRUTOR
	 */

	public FornecedorEntity() {

	}

	public FornecedorEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getChequeNominalA() {
		return chequeNominalA;
	}

	public void setChequeNominalA(String chequeNominalA) {
		this.chequeNominalA = (chequeNominalA == null ? "".trim()
				: chequeNominalA.toUpperCase().trim());
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "".trim() : observacao
				.toUpperCase().trim());
	}

	public String getContaRemetente() {
		return contaRemetente;
	}

	public void setContaRemetente(String contaRemetente) {
		this.contaRemetente = (contaRemetente == null ? "".trim()
				: contaRemetente.toUpperCase().trim());
	}

	public BigDecimal getPrazoMedioEntrega() {
		return prazoMedioEntrega;
	}

	public void setPrazoMedioEntrega(BigDecimal prazoMedioEntrega) {
		this.prazoMedioEntrega = (prazoMedioEntrega == null ? new BigDecimal(0)
				: prazoMedioEntrega);
	}

	public Integer getNumDiasPrimeiroVencimento() {
		return numDiasPrimeiroVencimento;
	}

	public void setNumDiasPrimeiroVencimento(Integer numDiasPrimeiroVencimento) {
		this.numDiasPrimeiroVencimento = (numDiasPrimeiroVencimento == null ? new Integer(
				0) : numDiasPrimeiroVencimento);
	}

	public Integer getNumDiasIntervalo() {
		return numDiasIntervalo;
	}

	public void setNumDiasIntervalo(Integer numDiasIntervalo) {
		this.numDiasIntervalo = (numDiasIntervalo == null ? new Integer(0)
				: numDiasIntervalo);
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = (quantidadeParcelas == null ? new Integer(0)
				: quantidadeParcelas);
	}

	public SimNaoEn getOptanteSimplesNacional() {
		return optanteSimplesNacional;
	}

	public void setOptanteSimplesNacional(SimNaoEn optanteSimplesNacional) {
		this.optanteSimplesNacional = optanteSimplesNacional;
	}

	public LocalizacaoEn getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(LocalizacaoEn localizacao) {
		this.localizacao = localizacao;
	}

	public SimNaoEn getSofreRetencao() {
		return sofreRetencao;
	}

	public void setSofreRetencao(SimNaoEn sofreRetencao) {
		this.sofreRetencao = sofreRetencao;
	}

	public SimNaoEn getGeraFaturamento() {
		return geraFaturamento;
	}

	public void setGeraFaturamento(SimNaoEn geraFaturamento) {
		this.geraFaturamento = geraFaturamento;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public SituacaoForCliEntity getSituacaoForCli() {
		return situacaoForCli;
	}

	public void setSituacaoForCli(SituacaoForCliEntity situacaoForCli) {
		this.situacaoForCli = situacaoForCli;
	}

	public AtividadeForCliEntity getAtividadeForCli() {
		return atividadeForCli;
	}

	public void setAtividadeForCli(AtividadeForCliEntity atividadeForCli) {
		this.atividadeForCli = atividadeForCli;
	}

	public ContabilContaEntity getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilContaEntity contabilConta) {
		this.contabilConta = contabilConta;
	}

	public List<BemEntity> getBemList() {
		return bemList;
	}

	public void setBemList(List<BemEntity> bemList) {
		this.bemList = bemList;
	}

	public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
		return nfeCabecalhoList;
	}

	public void setNfeCabecalhoList(List<NfeCabecalhoEntity> nfeCabecalhoList) {
		this.nfeCabecalhoList = nfeCabecalhoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}