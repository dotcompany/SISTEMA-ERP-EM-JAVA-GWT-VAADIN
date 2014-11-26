package dc.entidade.suprimentos.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.produto.Produto;

/**
 * The persistent class for the compra_cotacao_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_cotacao_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CotacaoDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ccompra_cotacao_detalhe_id_seq")
	@SequenceGenerator(name = "ccompra_cotacao_detalhe_id_seq", sequenceName = "ccompra_cotacao_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "quantidade")
	@Caption("Quantidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidade;

	@Field
	@Column(name = "quantidade_pedida")
	@Caption("Quantidade pedida")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadePedida;

	@Field
	@Column(name = "taxa_desconto")
	@Caption("Taxa de desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaDesconto;

	@Field
	@Column(name = "valor_desconto")
	@Caption("Valor de desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto;

	@Field
	@Column(name = "valor_subtotal")
	@Caption("Valor subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubtotal;

	@Field
	@Column(name = "valor_total")
	@Caption("Valor total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal;

	@Field
	@Column(name = "valor_unitario")
	@Caption("Valor unitário")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnitario;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_produto")
	@Caption(value = "Produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_compra_fornecedor_cotacao")
	@Caption(value = "Fornecedor - Cotação")
	private FornecedorCotacaoEntity compraFornecedorCotacao;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "compraCotacaoDetalhe")
	@Fetch(FetchMode.SUBSELECT)
	private List<CotacaoPedidoDetalheEntity> compraCotacaoPedidoDetalhes;

	/**
	 * CONSTRUTOR
	 */

	public CotacaoDetalheEntity() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidadePedida() {
		return quantidadePedida;
	}

	public void setQuantidadePedida(BigDecimal quantidadePedida) {
		this.quantidadePedida = quantidadePedida;
	}

	public BigDecimal getTaxaDesconto() {
		return taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public FornecedorCotacaoEntity getCompraFornecedorCotacao() {
		return compraFornecedorCotacao;
	}

	public void setCompraFornecedorCotacao(
			FornecedorCotacaoEntity compraFornecedorCotacao) {
		this.compraFornecedorCotacao = compraFornecedorCotacao;
	}

	public List<CotacaoPedidoDetalheEntity> getCompraCotacaoPedidoDetalhes() {
		return compraCotacaoPedidoDetalhes;
	}

	public void setCompraCotacaoPedidoDetalhes(
			List<CotacaoPedidoDetalheEntity> compraCotacaoPedidoDetalhes) {
		this.compraCotacaoPedidoDetalhes = compraCotacaoPedidoDetalhes;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}