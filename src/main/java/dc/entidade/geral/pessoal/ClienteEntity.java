package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.PessoaEntity;
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
	@Column(name = "DESDE")
	@Caption("Desde")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date desde;

	@Field
	@Column(name = "DATA_CADASTRO")
	@Caption("Data de cadastro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Lob
	@Type(type = "text")
	@Field
	@Column(name = "OBSERVACAO", length = 65535)
	@Caption("Observação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@Field
	@Column(name = "CONTA_TOMADOR")
	@Caption("Conta do tomador")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaTomador;

	@Field
	@Column(name = "GERA_FINANCEIRO")
	@Caption("Gera financeiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private SimNaoEn geraFinanceiro;

	@Field
	@Column(name = "INDICADOR_PRECO")
	@Caption("Indicador de preço")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private IndicadorPrecoEn indicadorPreco;

	@Field
	@Column(name = "PORCENTO_DESCONTO", precision = 11, scale = 2)
	@Caption("Porcento de desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal porcentoDesconto;

	@Field
	@Column(name = "FORMA_DESCONTO")
	@Caption("Forma de desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private FormaDescontoEn formaDesconto;

	@Field
	@Column(name = "LIMITE_CREDITO", precision = 11, scale = 2)
	@Caption("Limite de crédito")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal limiteCredito;

	@Field
	@Column(name = "TIPO_FRETE")
	@Caption("Tipo de frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private TipoFreteEn tipoFrete;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa", nullable = true)
	@Caption("Pessoa")
	private PessoaEntity pessoa;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_situacao_for_cli", nullable = true)
	@Caption("Situação fornecedor / cliente")
	private SituacaoForCliEntity situacaoForCli;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_atividade_for_cli", nullable = true)
	@Caption("Atividade fornecedor / cliente")
	private AtividadeForCliEntity atividadeForCli;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contabil_conta", nullable = true)
	@Caption("Conta contábil")
	private ContabilContaEntity contabilConta;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_operacao_fiscal", nullable = true)
	@Caption("Operação fiscal")
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
		this.observacao = observacao;
	}

	public String getContaTomador() {
		return contaTomador;
	}

	public void setContaTomador(String contaTomador) {
		this.contaTomador = contaTomador;
	}

	public SimNaoEn getGeraFinanceiro() {
		return geraFinanceiro;
	}

	public void setGeraFinanceiro(SimNaoEn geraFinanceiro) {
		this.geraFinanceiro = geraFinanceiro;
	}

	public IndicadorPrecoEn getIndicadorPreco() {
		return indicadorPreco;
	}

	public void setIndicadorPreco(IndicadorPrecoEn indicadorPreco) {
		this.indicadorPreco = indicadorPreco;
	}

	public BigDecimal getPorcentoDesconto() {
		return porcentoDesconto;
	}

	public void setPorcentoDesconto(BigDecimal porcentoDesconto) {
		this.porcentoDesconto = porcentoDesconto;
	}

	public FormaDescontoEn getFormaDesconto() {
		return formaDesconto;
	}

	public void setFormaDesconto(FormaDescontoEn formaDesconto) {
		this.formaDesconto = formaDesconto;
	}

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
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

	public ContabilContaEntity getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilContaEntity contabilConta) {
		this.contabilConta = contabilConta;
	}

	public OperacaoFiscalEntity getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscalEntity operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
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