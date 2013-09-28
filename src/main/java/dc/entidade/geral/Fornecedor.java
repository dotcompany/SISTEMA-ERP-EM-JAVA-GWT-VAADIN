package dc.entidade.geral;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.patrimonio.BemEntity;
import dc.entidade.pessoal.AtividadeForCli;
import dc.entidade.pessoal.SituacaoForCli;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */

@Entity
@Table(name = "fornecedor")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Fornecedor extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@Field
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DESDE")
	private Date desde;
	
	@Field
	@Column(name = "OPTANTE_SIMPLES_NACIONAL")
	private String optanteSimplesNacional;
	
	@Field
	@Column(name = "LOCALIZACAO")
	private String localizacao;
	
	@Field
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;
	
	@Field
	@Column(name = "SOFRE_RETENCAO")
	private String sofreRetencao;
	
	@Field
	@Column(name = "CHEQUE_NOMINAL_A")
	private String chequeNominalA;
	
	@Field
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;
	
	@Field
	@Column(name = "CONTA_REMETENTE")
	private String contaRemetente;
	
	@Field
	@Column(name = "PRAZO_MEDIO_ENTREGA")
	private BigDecimal prazoMedioEntrega;
	
	@Field
	@Column(name = "GERA_FATURAMENTO")
	private String geraFaturamento;
	
	@Field
	@Column(name = "NUM_DIAS_PRIMEIRO_VENCIMENTO")
	private Integer numDiasPrimeiroVencimento;
	
	@Field
	@Column(name = "NUM_DIAS_INTERVALO")
	private Integer numDiasIntervalo;
	
	@Field
	@Column(name = "QUANTIDADE_PARCELAS")
	private Integer quantidadeParcelas;
	
	@JoinColumn(name = "ID_SITUACAO_FOR_CLI", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private SituacaoForCli situacaoForCli;
	
	@JoinColumn(name = "ID_ATIVIDADE_FOR_CLI", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private AtividadeForCli atividadeForCli;
	
	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	private Pessoa pessoa;
		
	@JoinColumn(name = "ID_CONTABIL_CONTA", referencedColumnName = "ID")
	@ManyToOne
	private ContabilConta contabilConta;

	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<BemEntity> bemList;

	public Fornecedor() {

	}

	public Fornecedor(Integer id) {
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
	public SituacaoForCli getSituacaoForCli() {
		return situacaoForCli;
	}

	/**
	 * @param situacaoForCli
	 *            the situacaoForCli to set
	 */
	public void setSituacaoForCli(SituacaoForCli situacaoForCli) {
		this.situacaoForCli = situacaoForCli;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa
	 *            the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the atividadeForCli
	 */
	public AtividadeForCli getAtividadeForCli() {
		return atividadeForCli;
	}

	/**
	 * @param atividadeForCli
	 *            the atividadeForCli to set
	 */
	public void setAtividadeForCli(AtividadeForCli atividadeForCli) {
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

	@Override
	public String toString() {
		return this.observacao;
	}

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

}