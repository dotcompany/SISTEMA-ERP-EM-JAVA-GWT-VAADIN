package dc.entidade.suprimentos.compra;

import java.io.Serializable;
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

/**
 * The persistent class for the compra_cotacao_pedido_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_cotacao_pedido_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CotacaoPedidoDetalheEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_cotacao_pedido_detalhe_id_seq")
	@SequenceGenerator(name = "compra_cotacao_pedido_detalhe_id_seq", sequenceName = "compra_cotacao_pedido_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "quantidade_pedida")
	private BigDecimal quantidadePedida;

	// bi-directional many-to-one association to CotacaoDetalhe
	@ManyToOne
	@JoinColumn(name = "id_compra_cotacao_detalhe")
	private CotacaoDetalheEntity compraCotacaoDetalhe;

	// bi-directional many-to-one association to PedidoCompra
	@ManyToOne
	@JoinColumn(name = "id_compra_pedido")
	private PedidoEntity compraPedido;

	public CotacaoPedidoDetalheEntity() {
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

	public CotacaoDetalheEntity getCompraCotacaoDetalhe() {
		return this.compraCotacaoDetalhe;
	}

	public void setCompraCotacaoDetalhe(
			CotacaoDetalheEntity compraCotacaoDetalhe) {
		this.compraCotacaoDetalhe = compraCotacaoDetalhe;
	}

	public PedidoEntity getCompraPedido() {
		return this.compraPedido;
	}

	public void setCompraPedido(PedidoEntity compraPedido) {
		this.compraPedido = compraPedido;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}