package dc.entidade.suprimentos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dc.entidade.framework.AbstractModel;
import dc.entidade.produto.Produto;


/**
 * The persistent class for the compra_cotacao_detalhe database table.
 * 
 */
@Entity
@Table(name="compra_cotacao_detalhe")
public class CotacaoDetalhe extends AbstractModel<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	private BigDecimal quantidade;

	@Column(name="quantidade_pedida")
	private BigDecimal quantidadePedida;

	@Column(name="taxa_desconto")
	private BigDecimal taxaDesconto;

	@Column(name="valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name="valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name="valor_total")
	private BigDecimal valorTotal;

	@Column(name="valor_unitario")
	private BigDecimal valorUnitario;

	@ManyToOne
	@JoinColumn(name="id_compra_fornecedor_cotacao")
	private FornecedorCotacao compraFornecedorCotacao;

	@OneToMany(mappedBy="compraCotacaoDetalhe")
	@Fetch(FetchMode.SUBSELECT)
	private List<CotacaoPedidoDetalhe> compraCotacaoPedidoDetalhes;

	public CotacaoDetalhe() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidadePedida() {
		return this.quantidadePedida;
	}

	public void setQuantidadePedida(BigDecimal quantidadePedida) {
		this.quantidadePedida = quantidadePedida;
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

	public FornecedorCotacao getCompraFornecedorCotacao() {
		return this.compraFornecedorCotacao;
	}

	public void setCompraFornecedorCotacao(FornecedorCotacao compraFornecedorCotacao) {
		this.compraFornecedorCotacao = compraFornecedorCotacao;
	}

	public List<CotacaoPedidoDetalhe> getCompraCotacaoPedidoDetalhes() {
		return this.compraCotacaoPedidoDetalhes;
	}

	public void setCompraCotacaoPedidoDetalhes(List<CotacaoPedidoDetalhe> compraCotacaoPedidoDetalhes) {
		this.compraCotacaoPedidoDetalhes = compraCotacaoPedidoDetalhes;
	}

	public CotacaoPedidoDetalhe addCompraCotacaoPedidoDetalhe(CotacaoPedidoDetalhe compraCotacaoPedidoDetalhe) {
		getCompraCotacaoPedidoDetalhes().add(compraCotacaoPedidoDetalhe);
		compraCotacaoPedidoDetalhe.setCompraCotacaoDetalhe(this);

		return compraCotacaoPedidoDetalhe;
	}

	public CotacaoPedidoDetalhe removeCompraCotacaoPedidoDetalhe(CotacaoPedidoDetalhe compraCotacaoPedidoDetalhe) {
		getCompraCotacaoPedidoDetalhes().remove(compraCotacaoPedidoDetalhe);
		compraCotacaoPedidoDetalhe.setCompraCotacaoDetalhe(null);

		return compraCotacaoPedidoDetalhe;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}