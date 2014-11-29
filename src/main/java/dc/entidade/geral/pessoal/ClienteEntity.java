package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.tributario.OperacaoFiscal;

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

	@Column(name = "DATA_CADASTRO")
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Lob
	@Field
	@Caption("Observacao")
	@Type(type = "text")
	@Column(name = "OBSERVACAO", length = 65535)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@Field
	@Caption("Conta Tomador")
	@Column(name = "CONTA_TOMADOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaTomador;

	@Field
	@Caption("Gera Financeiro")
	@Column(name = "GERA_FINANCEIRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String geraFinanceiro;

	@Field
	@Caption("Indicador Preco")
	@Column(name = "INDICADOR_PRECO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String indicadorPreco;

	@Field
	@Caption("Porcento Desconto")
	@Column(name = "PORCENTO_DESCONTO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal porcentoDesconto;

	@Field
	@Caption("Forma Desconto")
	@Column(name = "FORMA_DESCONTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String formaDesconto;

	@Field
	@Caption("Limite Credito")
	@Column(name = "LIMITE_CREDITO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal limiteCredito;

	@Field
	@Caption("Tipo Frete")
	@Column(name = "TIPO_FRETE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoFrete;

	/**
	 * Mapeamento Situação-Cliente
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SITUACAO_FOR_CLI", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private SituacaoForCliEntity situacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PESSOA", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Analyzer(definition = "dc_combo_analyzer")
	@IndexedEmbedded
	private PessoaEntity pessoa;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ATIVIDADE_FOR_CLI", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private AtividadeForCliEntity atividadeForCli;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CONTABIL_CONTA", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private ContabilConta contabilConta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_OPERACAO_FISCAL", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private OperacaoFiscal operacaoFiscal;

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

	/**
	 * @return the situacao
	 */
	public SituacaoForCliEntity getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao
	 *            the situacao to set
	 */
	public void setSituacao(SituacaoForCliEntity situacao) {
		this.situacao = situacao;
	}

	// @Override
	// public int hashCode() {
	// return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	// }
	//
	// @Override
	// public boolean equals(Object object) {
	// if (object instanceof Cliente == false)
	// return false;
	// if (this == object)
	// return true;
	// final Cliente other = (Cliente) object;
	// return EqualsBuilder.reflectionEquals(this, other);
	// }

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
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

	public String getGeraFinanceiro() {
		return geraFinanceiro;
	}

	public void setGeraFinanceiro(String geraFinanceiro) {
		this.geraFinanceiro = geraFinanceiro;
	}

	public String getIndicadorPreco() {
		return indicadorPreco;
	}

	public void setIndicadorPreco(String indicadorPreco) {
		this.indicadorPreco = indicadorPreco;
	}

	public BigDecimal getPorcentoDesconto() {
		return porcentoDesconto;
	}

	public void setPorcentoDesconto(BigDecimal porcentoDesconto) {
		this.porcentoDesconto = porcentoDesconto;
	}

	public String getFormaDesconto() {
		return formaDesconto;
	}

	public void setFormaDesconto(String formaDesconto) {
		this.formaDesconto = formaDesconto;
	}

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public String getTipoFrete() {
		return tipoFrete;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	public AtividadeForCliEntity getAtividadeForCli() {
		return atividadeForCli;
	}

	public void setAtividadeForCli(AtividadeForCliEntity atividadeForCli) {
		this.atividadeForCli = atividadeForCli;
	}

	public ContabilConta getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilConta contabilConta) {
		this.contabilConta = contabilConta;
	}

	public OperacaoFiscal getOperacaoFiscal() {
		return operacaoFiscal;
	}

	public void setOperacaoFiscal(OperacaoFiscal operacaoFiscal) {
		this.operacaoFiscal = operacaoFiscal;
	}

	/**
	 * Módulo: NFE
	 */

	// public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
	// return nfeCabecalhoList;
	// }

	// public void setNfeCabecalhoList(List<NfeCabecalhoEntity>
	// nfeCabecalhoList) {
	// this.nfeCabecalhoList = nfeCabecalhoList;
	// }

	/**
	 * 
	 */

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