package dc.entidade.suprimentos.compra;

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
import dc.entidade.suprimentos.compra.RequisicaoEntity;

/**
 * The persistent class for the compra_requisicao_detalhe database table.
 * 
 */
@Entity
@Table(name = "compra_requisicao_detalhe")
@SuppressWarnings("serial")
public class RequisicaoDetalheEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Column(name = "item_cotado")
	private String itemCotado;

	private BigDecimal quantidade;

	@Column(name = "quantidade_cotada")
	private BigDecimal quantidadeCotada;

	@ManyToOne
	@JoinColumn(name = "id_compra_requisicao")
	private RequisicaoEntity requisicao;

	public RequisicaoDetalheEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getItemCotado() {
		return this.itemCotado;
	}

	public void setItemCotado(String itemCotado) {
		this.itemCotado = itemCotado;
	}

	public RequisicaoEntity getRequisicao() {
		return this.requisicao;
	}

	public void setRequisicao(RequisicaoEntity compraRequisicao) {
		this.requisicao = compraRequisicao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQuantidadeCotada() {
		return quantidadeCotada;
	}

	public void setQuantidadeCotada(BigDecimal quantidadeCotada) {
		this.quantidadeCotada = quantidadeCotada;
	}

}