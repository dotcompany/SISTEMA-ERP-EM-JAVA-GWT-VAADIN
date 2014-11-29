package dc.entidade.geral;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.pessoal.AtividadeForCliEntity;
import dc.entidade.pessoal.SituacaoForCliEntity;

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

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DESDE")
	@Caption(value = "Desde")
	private Date desde;

	@Field
	@Column(name = "OPTANTE_SIMPLES_NACIONAL")
	@Caption(value = "Optante Simples Nacional")
	private String optanteSimplesNacional;

	@Field
	@Column(name = "LOCALIZACAO")
	@Caption(value = "Localização")
	private String localizacao;

	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO")
	@Caption(value = "Data Cadastro")
	private Date dataCadastro;

	@Field
	@Column(name = "SOFRE_RETENCAO")
	@Caption(value = "Sofre Retenção")
	private String sofreRetencao;

	@Field
	@Column(name = "CHEQUE_NOMINAL_A")
	@Caption(value = "Cheque Nominla à")
	private String chequeNominalA;

	@Field
	@Column(name = "OBSERVACAO")
	@Caption(value = "Observação")
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@Field
	@Column(name = "CONTA_REMETENTE")
	@Caption(value = "Conta Remetente")
	private String contaRemetente;

	@Field
	@Column(name = "PRAZO_MEDIO_ENTREGA")
	@Caption(value = "Prazo médio Entrega")
	private BigDecimal prazoMedioEntrega;

	@Field
	@Column(name = "GERA_FATURAMENTO")
	@Caption(value = "Gera Faturamento")
	private String geraFaturamento;

	@Field
	@Column(name = "NUM_DIAS_PRIMEIRO_VENCIMENTO")
	@Caption(value = "Núm dias Primeiro Vencimento")
	private Integer numDiasPrimeiroVencimento;

	@Field
	@Column(name = "NUM_DIAS_INTERVALO")
	@Caption(value = "Num Dias Intervalo")
	private Integer numDiasIntervalo;

	@Field
	@Column(name = "QUANTIDADE_PARCELAS")
	@Caption(value = "Quantidade Parcelas")
	private Integer quantidadeParcelas;

	@JoinColumn(name = "ID_SITUACAO_FOR_CLI", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private SituacaoForCliEntity situacaoForCli;

	@JoinColumn(name = "ID_ATIVIDADE_FOR_CLI", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private AtividadeForCliEntity atividadeForCli;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Analyzer(definition = "dc_combo_analyzer")
	@IndexedEmbedded
	private PessoaEntity pessoa;

	@JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
	@ManyToOne
	private ContabilConta contabilConta;

	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<BemEntity> bemList;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * Módulo: NFE
	 */

	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<NfeCabecalhoEntity> nfeCabecalhoList;

	/**
	 * 
	 */

	/**
	 * CONSTRUTOR
	 */

	public FornecedorEntity() {

	}

	public FornecedorEntity(Integer id) {
		this.id = id;
	}

	/**
	 * Metodo transient para exibir nas views. Composto do id + nome da pessoa
	 * 
	 * @return
	 * @author cjalmeida
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

	public String getOptanteSimplesNacional() {
		return optanteSimplesNacional;
	}

	public void setOptanteSimplesNacional(String optanteSimplesNacional) {
		this.optanteSimplesNacional = optanteSimplesNacional;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSofreRetencao() {
		return sofreRetencao;
	}

	public void setSofreRetencao(String sofreRetencao) {
		this.sofreRetencao = sofreRetencao;
	}

	public String getChequeNominalA() {
		return chequeNominalA;
	}

	public void setChequeNominalA(String chequeNominalA) {
		this.chequeNominalA = chequeNominalA;
	}

	/**
	 * @return the situacaoForCli
	 */
	public SituacaoForCliEntity getSituacaoForCli() {
		return situacaoForCli;
	}

	/**
	 * @param situacaoForCli
	 *            the situacaoForCli to set
	 */
	public void setSituacaoForCli(SituacaoForCliEntity situacaoForCli) {
		this.situacaoForCli = situacaoForCli;
	}

	/**
	 * @return the pessoa
	 */
	public PessoaEntity getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the atividadeForCli
	 */
	public AtividadeForCliEntity getAtividadeForCli() {
		return atividadeForCli;
	}

	/**
	 * @param atividadeForCli
	 *            the atividadeForCli to set
	 */
	public void setAtividadeForCli(AtividadeForCliEntity atividadeForCli) {
		this.atividadeForCli = atividadeForCli;
	}

	/**
	 * @return the observacoes
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacoes
	 *            the observacoes to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getContaRemetente() {
		return contaRemetente;
	}

	public void setContaRemetente(String contaRemetente) {
		this.contaRemetente = contaRemetente;
	}

	public BigDecimal getPrazoMedioEntrega() {
		return prazoMedioEntrega;
	}

	public void setPrazoMedioEntrega(BigDecimal prazoMedioEntrega) {
		this.prazoMedioEntrega = prazoMedioEntrega;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public List<BemEntity> getBemList() {
		return bemList;
	}

	public void setBemList(List<BemEntity> bemList) {
		this.bemList = bemList;
	}

	/*
	 * @Override public int hashCode() { return
	 * HashCodeBuilder.reflectionHashCode(this, new String[] {"id"}); }
	 * 
	 * @Override public boolean equals(Object object) { if (object instanceof
	 * Fornecedor == false) return false; if (this == object) return true; final
	 * Fornecedor other = (Fornecedor) object; return
	 * EqualsBuilder.reflectionEquals(this, other); }
	 */

	public String getGeraFaturamento() {
		return geraFaturamento;
	}

	public void setGeraFaturamento(String geraFaturamento) {
		this.geraFaturamento = geraFaturamento;
	}

	public Integer getNumDiasPrimeiroVencimento() {
		return numDiasPrimeiroVencimento;
	}

	public void setNumDiasPrimeiroVencimento(Integer numDiasPrimeiroVencimento) {
		this.numDiasPrimeiroVencimento = numDiasPrimeiroVencimento;
	}

	public Integer getNumDiasIntervalo() {
		return numDiasIntervalo;
	}

	public void setNumDiasIntervalo(Integer numDiasIntervalo) {
		this.numDiasIntervalo = numDiasIntervalo;
	}

	public ContabilConta getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilConta contabilConta) {
		this.contabilConta = contabilConta;
	}

	/**
	 * Módulo: NFE
	 */

	public List<NfeCabecalhoEntity> getNfeCabecalhoList() {
		return nfeCabecalhoList;
	}

	public void setNfeCabecalhoList(List<NfeCabecalhoEntity> nfeCabecalhoList) {
		this.nfeCabecalhoList = nfeCabecalhoList;
	}

	/**
	 * 
	 */

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}