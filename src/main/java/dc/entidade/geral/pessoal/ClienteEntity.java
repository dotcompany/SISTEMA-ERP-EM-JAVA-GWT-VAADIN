package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.FormaDescontoEn;
import dc.control.enums.IndicadorPrecoEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoFreteEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.tributario.OperacaoFiscalEntity;

@Entity
@Table(name = "cliente")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ClienteEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq")
	@SequenceGenerator(name = "cliente_id_seq", sequenceName = "cliente_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Desde")
	@Column(name = "DESDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date desde;

	@Field
	@Caption("Data de cadastro")
	@Column(name = "DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Observação")
	@Column(name = "OBSERVACAO", length = 65535)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	@Field
	@Caption("Conta do tomador")
	@Column(name = "CONTA_TOMADOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaTomador = "";

	@Field
	@Caption("Porcento de desconto")
	@Column(name = "PORCENTO_DESCONTO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal porcentoDesconto = new BigDecimal(0);

	@Field
	@Caption("Limite de crédito")
	@Column(name = "LIMITE_CREDITO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal limiteCredito = new BigDecimal(0);

	@Enumerated(EnumType.STRING)
	@Field
	@Column(name = "GERA_FINANCEIRO")
	@Caption("Gera financeiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn gerarFinanceiro;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Indicador de preço")
	@Column(name = "INDICADOR_PRECO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private IndicadorPrecoEn indicadorPreco;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Forma de desconto")
	@Column(name = "FORMA_DESCONTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private FormaDescontoEn formaDesconto;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo de frete")
	@Column(name = "TIPO_FRETE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoFreteEn tipoFrete;

	@Field
	@Caption(value = "Classificação da conta contábil")
	@Column(name = "CLASSIFICACAO_CONTABIL_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String classificacaoContabilConta = "";

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Pessoa")
	@OneToOne
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	private PessoaEntity pessoa;

	@Caption("Situação fornecedor / cliente")
	@ManyToOne
	@JoinColumn(name = "id_situacao_for_cli", nullable = true)
	private SituacaoForCliEntity situacaoForCli;

	@Caption("Atividade fornecedor / cliente")
	@ManyToOne
	@JoinColumn(name = "id_atividade_for_cli", nullable = true)
	private AtividadeForCliEntity atividadeForCli;

	@Caption("Operação fiscal")
	@ManyToOne
	@JoinColumn(name = "id_operacao_fiscal", nullable = true)
	private OperacaoFiscalEntity operacaoFiscal;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * Módulo: NFE
	 */

	// @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	// @Fetch(FetchMode.SUBSELECT)
	// private List<NfeCabecalhoEntity> nfeCabecalhoList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return pessoa.getNome().toUpperCase().trim();
	}

	/**
	 * CONSTRUTOR
	 */

	public ClienteEntity() {

	}

	public ClienteEntity(Integer id) {
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = (observacao == null ? "".trim() : observacao
				.toUpperCase().trim());
	}

	public String getContaTomador() {
		return contaTomador;
	}

	public void setContaTomador(String contaTomador) {
		this.contaTomador = (contaTomador == null ? "".trim() : contaTomador
				.toUpperCase().trim());
	}

	public BigDecimal getPorcentoDesconto() {
		return porcentoDesconto;
	}

	public void setPorcentoDesconto(BigDecimal porcentoDesconto) {
		this.porcentoDesconto = (porcentoDesconto == null ? new BigDecimal(0)
				: porcentoDesconto);
	}

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = (limiteCredito == null ? new BigDecimal(0)
				: limiteCredito);
	}

	public SimNaoEn getGerarFinanceiro() {
		return gerarFinanceiro;
	}

	public void setGerarFinanceiro(SimNaoEn gerarFinanceiro) {
		this.gerarFinanceiro = gerarFinanceiro;
	}

	public IndicadorPrecoEn getIndicadorPreco() {
		return indicadorPreco;
	}

	public void setIndicadorPreco(IndicadorPrecoEn indicadorPreco) {
		this.indicadorPreco = indicadorPreco;
	}

	public FormaDescontoEn getFormaDesconto() {
		return formaDesconto;
	}

	public void setFormaDesconto(FormaDescontoEn formaDesconto) {
		this.formaDesconto = formaDesconto;
	}

	public TipoFreteEn getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(TipoFreteEn tipoFrete) {
		this.tipoFrete = tipoFrete;
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

	public OperacaoFiscalEntity getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscalEntity operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
	}

	public String getClassificacaoContabilConta() {
		return classificacaoContabilConta;
	}

	public void setClassificacaoContabilConta(String classificacaoContabilConta) {
		this.classificacaoContabilConta = (classificacaoContabilConta == null ? ""
				.trim() : classificacaoContabilConta.toUpperCase().trim());
	}

	/**
	 * TO STRING
	 */

	// @Override
	// public String toString() {
	// return ToStringBuilder.reflectionToString(this);
	// }

	@Override
	public String toString() {
		return pessoa.getNome();
	}

}