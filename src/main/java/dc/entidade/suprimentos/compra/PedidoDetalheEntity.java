package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.produto.ProdutoEntity;

/**
 * The persistent class for the compra_pedido_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_pedido_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PedidoDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_pedido_detalhe_id_seq")
	@SequenceGenerator(name = "compra_pedido_detalhe_id_seq", sequenceName = "compra_pedido_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "aliquota_icms")
	private BigDecimal aliquotaIcms;

	@Column(name = "aliquota_ipi")
	private BigDecimal aliquotaIpi;

	@Column(name = "base_calculo_icms")
	private BigDecimal baseCalculoIcms;

	private Integer cfop;

	@Column(name = "cst_csosn")
	private String cstCsosn;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private ProdutoEntity produto;

	private BigDecimal quantidade;

	@Column(name = "taxa_desconto")
	private BigDecimal taxaDesconto;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_icms")
	private BigDecimal valorIcms;

	@Column(name = "valor_ipi")
	private BigDecimal valorIpi;

	@Column(name = "valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	@ManyToOne
	@JoinColumn(name = "id_compra_pedido")
	private PedidoEntity pedido;

	public Integer getId() {
		return this.id;
	}

	public BigDecimal getAliquotaIcms() {
		return this.aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public BigDecimal getAliquotaIpi() {
		return this.aliquotaIpi;
	}

	public void setAliquotaIpi(BigDecimal aliquotaIpi) {
		this.aliquotaIpi = aliquotaIpi;
	}

	public BigDecimal getBaseCalculoIcms() {
		return this.baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public Integer getCfop() {
		return this.cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public String getCstCsosn() {
		return this.cstCsosn;
	}

	public void setCstCsosn(String cstCsosn) {
		this.cstCsosn = cstCsosn;
	}

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTaxaDesconto() {
		return this.taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorDesconto() {
		return this.valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorIcms() {
		return this.valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public BigDecimal getValorIpi() {
		return this.valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public BigDecimal getValorSubtotal() {
		return this.valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public PedidoEntity getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoEntity compraPedido) {
		this.pedido = compraPedido;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}