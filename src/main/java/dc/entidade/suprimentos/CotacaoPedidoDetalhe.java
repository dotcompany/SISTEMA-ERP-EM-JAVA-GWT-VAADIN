package dc.entidade.suprimentos;

import java.io.Serializable;
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
import dc.entidade.suprimentos.compra.PedidoCompra;

/**
 * The persistent class for the compra_cotacao_pedido_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_cotacao_pedido_detalhe")
public class CotacaoPedidoDetalhe extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "quantidade_pedida")
	private BigDecimal quantidadePedida;

	// bi-directional many-to-one association to CotacaoDetalhe
	@ManyToOne
	@JoinColumn(name = "id_compra_cotacao_detalhe")
	private CotacaoDetalhe compraCotacaoDetalhe;

	// bi-directional many-to-one association to PedidoCompra
	@ManyToOne
	@JoinColumn(name = "id_compra_pedido")
	private PedidoCompra compraPedido;

	public CotacaoPedidoDetalhe() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidadePedida() {
		return this.quantidadePedida;
	}

	public void setQuantidadePedida(BigDecimal quantidadePedida) {
		this.quantidadePedida = quantidadePedida;
	}

	public CotacaoDetalhe getCompraCotacaoDetalhe() {
		return this.compraCotacaoDetalhe;
	}

	public void setCompraCotacaoDetalhe(CotacaoDetalhe compraCotacaoDetalhe) {
		this.compraCotacaoDetalhe = compraCotacaoDetalhe;
	}

	public PedidoCompra getCompraPedido() {
		return this.compraPedido;
	}

	public void setCompraPedido(PedidoCompra compraPedido) {
		this.compraPedido = compraPedido;
	}

}