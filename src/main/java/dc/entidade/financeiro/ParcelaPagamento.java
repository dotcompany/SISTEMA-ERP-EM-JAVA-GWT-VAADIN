package dc.entidade.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

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
@Table(name = "parcela_pagamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ParcelaPagamento extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PAGAMENTO")
	@Field
	private Date dataPagamento;

	@Column(name = "TAXA_JURO")
	@Field
	private BigDecimal taxaJuro;

	@Column(name = "TAXA_MULTA")
	@Field
	private BigDecimal taxaMulta;

	@Column(name = "TAXA_DESCONTO")
	@Field
	private BigDecimal taxaDesconto;

	@Column(name = "VALOR_JURO")
	@Field
	private BigDecimal valorJuro;

	@Column(name = "VALOR_MULTA")
	@Field
	private BigDecimal valorMulta;

	@Column(name = "VALOR_DESCONTO")
	@Field
	private BigDecimal valorDesconto;

	@Column(name = "VALOR_PAGO")
	@Field
	private BigDecimal valorPago;

	@Column(name = "HISTORICO")
	@Field
	private String historico;

	@JoinColumn(name = "ID_FIN_PARCELA_PAGAR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ParcelaPagar parcelaPagar;

	@JoinColumn(name = "ID_FIN_CHEQUE_EMITIDO", referencedColumnName = "ID")
	@ManyToOne
	private ChequeEmitido chequeEmitido;

	@JoinColumn(name = "ID_FIN_TIPO_PAGAMENTO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoPagamento tipoPagamento;

	@JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private ContaCaixa contaCaixa;

	public ParcelaPagamento() {
	}

	public ParcelaPagamento(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
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

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ParcelaPagamento == false)
			return false;
		if (this == object)
			return true;
		final ParcelaPagamento other = (ParcelaPagamento) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the chequeEmitido
	 */
	public ChequeEmitido getChequeEmitido() {
		return chequeEmitido;
	}

	/**
	 * @param chequeEmitido
	 *            the chequeEmitido to set
	 */
	public void setChequeEmitido(ChequeEmitido chequeEmitido) {
		this.chequeEmitido = chequeEmitido;
	}

	/**
	 * @return the tipoPagamento
	 */
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	/**
	 * @param tipoPagamento
	 *            the tipoPagamento to set
	 */
	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	/**
	 * @return the parcelaPagar
	 */
	public ParcelaPagar getParcelaPagar() {
		return parcelaPagar;
	}

	/**
	 * @param parcelaPagar
	 *            the parcelaPagar to set
	 */
	public void setParcelaPagar(ParcelaPagar parcelaPagar) {
		this.parcelaPagar = parcelaPagar;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

}
