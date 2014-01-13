package dc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contratos.Contrato;
import dc.entidade.framework.AbstractMultiEmpresaModel;

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
@Table(name = "parcela_pagar")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParcelaPagar extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DATA_EMISSAO")
	@Temporal(TemporalType.DATE)
	@Caption(value = "Data Emissão")
	private Date dataEmissao;

	@Caption(value = "Data Vencimento")
	@Column(name = "DATA_VENCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@Caption(value = "Desconto Até")
	@Column(name = "DESCONTO_ATE")
	@Temporal(TemporalType.DATE)
	private Date descontoAte;

	@Caption(value = "Sofre Retencao")
	@Column(name = "SOFRE_RETENCAO")
	private String sofreRetencao;

	@Field
	@Caption(value = "Valor")
	@Column(name = "VALOR")
	private BigDecimal valor;

	@Caption(value = "Taxa Juro")
	@Column(name = "TAXA_JURO", precision = 14, scale = 0)
	private BigDecimal taxaJuro;

	@Caption(value = "Taxa Multa")
	@Column(name = "TAXA_MULTA", precision = 14, scale = 0)
	private BigDecimal taxaMulta;

	@Caption(value = "Taxa Desconto")
	@Column(name = "TAXA_DESCONTO", precision = 14, scale = 0)
	private BigDecimal taxaDesconto;

	@Caption(value = "Valor Juro")
	@Column(name = "VALOR_JURO", precision = 14, scale = 0)
	private BigDecimal valorJuro;

	@Caption(value = "Valor Multa")
	@Column(name = "VALOR_MULTA", precision = 14, scale = 0)
	private BigDecimal valorMulta;

	@Caption(value = "Valor Desconto")
	@Column(name = "VALOR_DESCONTO", precision = 14, scale = 0)
	private BigDecimal valorDesconto;

	@Caption(value = "Status Parcela")
	@JoinColumn(name = "ID_STATUS_PARCELA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private StatusParcela statusParcela;

	@Caption(value = "Lançamento à Pagar")
	@JoinColumn(name = "ID_LANCAMENTO_PAGAR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private LancamentoPagar lancamentoPagar;
	
	/*@Caption(value = "Contrato")
	@JoinColumn(name = "ID_CONTRATO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Contrato contrato;*/

	@Caption(value = "Número Parcela")
	@Column(name = "NUMERO_PARCELA")
	private Integer numeroParcela;

	@Caption(value = "Conta Caixa")
	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContaCaixa contaCaixa;

	@OneToMany(mappedBy = "parcelaPagar", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<ParcelaPagamento> parcelapagamentos = new ArrayList<>();

	@Transient
	@Caption(value = "Valor Faltante")
	private BigDecimal valorFaltante;

	public ParcelaPagar() {
	}

	public ParcelaPagar(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDescontoAte() {
		return descontoAte;
	}

	public void setDescontoAte(Date descontoAte) {
		this.descontoAte = descontoAte;
	}

	public String getSofreRetencao() {
		return sofreRetencao;
	}

	public void setSofreRetencao(String sofreRetencao) {
		this.sofreRetencao = sofreRetencao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxaJuro() {
		return taxaJuro;
	}

	public void setTaxaJuro(BigDecimal taxaJuro) {
		this.taxaJuro = taxaJuro;
	}

	public BigDecimal getTaxaMulta() {
		return taxaMulta;
	}

	public void setTaxaMulta(BigDecimal taxaMulta) {
		this.taxaMulta = taxaMulta;
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorJuro() {
		return valorJuro;
	}

	public void setValorJuro(BigDecimal valorJuro) {
		this.valorJuro = valorJuro;
	}

	public BigDecimal getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ParcelaPagar == false)
			return false;
		if (this == object)
			return true;
		final ParcelaPagar other = (ParcelaPagar) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the statusParcela
	 */
	public StatusParcela getStatusParcela() {
		return statusParcela;
	}

	/**
	 * @param statusParcela
	 *            the statusParcela to set
	 */
	public void setStatusParcela(StatusParcela statusParcela) {
		this.statusParcela = statusParcela;
	}

	/**
	 * @return the lancamentoPagar
	 */
	public LancamentoPagar getLancamentoPagar() {
		return lancamentoPagar;
	}

	/**
	 * @param lancamentoPagar
	 *            the lancamentoPagar to set
	 */
	public void setLancamentoPagar(LancamentoPagar lancamentoPagar) {
		this.lancamentoPagar = lancamentoPagar;
	}
	
	/*public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}*/

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	public List<ParcelaPagamento> getParcelapagamentos() {
		return parcelapagamentos;
	}

	public void setParcelapagamentos(List<ParcelaPagamento> parcelapagamentos) {
		this.parcelapagamentos = parcelapagamentos;
	}

	public BigDecimal getValorFaltante() {
		valorFaltante = BigDecimal.ZERO.add(valor);
		for (ParcelaPagamento p : parcelapagamentos) {
			if (p.getValorPago() != null) {
				valorFaltante = valorFaltante.subtract(p.getValorPago());
			}
		}

		return valorFaltante;
	}

}
