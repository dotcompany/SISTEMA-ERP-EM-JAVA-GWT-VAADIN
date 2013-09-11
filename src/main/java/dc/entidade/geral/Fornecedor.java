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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
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
	private Integer id;

	@Column(name = "DESDE")
	@Temporal(TemporalType.DATE)
	private Date desde;

	@Column(name = "OPTANTE_SIMPLES_NACIONAL")
	private String optanteSimplesNacional;

	@Column(name = "LOCALIZACAO")
	private String localizacao;

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(name = "SOFRE_RETENCAO")
	private String sofreRetencao;

	@Column(name = "CHEQUE_NOMINAL_A")
	private String chequeNominalA;

	@Lob
	@Field
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Caption("Observacao")
	@Type(type = "text")
	@Column(name = "OBSERVACAO")
	private String observacao;

	@Lob
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Type(type = "text")
	@Column(name = "OBSERVACOES")
	private String Observacoes;

	@Column(name = "CONTA_REMETENTE")
	private String contaRemetente;

	@Column(name = "PRAZO_MEDIO_ENTREGA", precision = 14, scale = 0)
	private BigDecimal prazoMedioEntrega;

	@Column(name = "GERA_FATURAMENTO")
	private Character geraFaturamento;

	@Column(name = "NUMERO_DIAS_PRIMEIRO_VENCIMENTO")
	private Integer numeroDiasPrimeiroVencimento;

	@Column(name = "NUMERO_DIAS_INTERVALO")
	private Integer numeroDiasIntervalo;

	@Column(name = "QUANTIDADE_PARCELAS")
	private Integer quantidadeParcelas;

	@JoinColumn(name = "ID_SITUACAO_FOR_CLI", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private SituacaoForCli situacaoForCli;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Pessoa pessoa;

	@JoinColumn(name = "ID_ATIVIDADE_FOR_CLI", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private AtividadeForCli atividadeForCli;

	@OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
	private List<BemEntity> bemList;

	public Fornecedor() {

	}

	public Fornecedor(Integer id) {
		this.id = id;
	}
	
	/**
	 * Metodo transient para exibir nas views. Composto do id + nome da pessoa
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

	public String getObservacoes() {
		return Observacoes;
	}

	public void setObservacoes(String observacoes) {
		Observacoes = observacoes;
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

	public Character getGeraFaturamento() {
		return geraFaturamento;
	}

	public void setGeraFaturamento(Character geraFaturamento) {
		this.geraFaturamento = geraFaturamento;
	}

	public Integer getNumeroDiasPrimeiroVencimento() {
		return numeroDiasPrimeiroVencimento;
	}

	public void setNumeroDiasPrimeiroVencimento(
			Integer numeroDiasPrimeiroVencimento) {
		this.numeroDiasPrimeiroVencimento = numeroDiasPrimeiroVencimento;
	}

	public Integer getNumeroDiasIntervalo() {
		return numeroDiasIntervalo;
	}

	public void setNumeroDiasIntervalo(Integer numeroDiasIntervalo) {
		this.numeroDiasIntervalo = numeroDiasIntervalo;
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
		return this.pessoa.getNome();
	}

}