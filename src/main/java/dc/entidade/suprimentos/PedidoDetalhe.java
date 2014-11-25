package dc.entidade.suprimentos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.compra.PedidoCompra;

/**
 * The persistent class for the compra_pedido_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_pedido_detalhe")
public class PedidoDetalhe extends AbstractMultiEmpresaModel<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Produto produto;

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
	private PedidoCompra pedido;

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

	public PedidoCompra getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoCompra compraPedido) {
		this.pedido = compraPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}