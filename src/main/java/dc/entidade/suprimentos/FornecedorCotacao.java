package dc.entidade.suprimentos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import dc.entidade.geral.Fornecedor;

/**
 * The persistent class for the compra_fornecedor_cotacao database table.
 * 
 */
@Entity
@Table(name = "compra_fornecedor_cotacao")
public class FornecedorCotacao extends AbstractModel<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;

	@Column(name = "prazo_entrega")
	private String prazoEntrega;

	@Column(name = "taxa_desconto")
	private BigDecimal taxaDesconto;

	private BigDecimal total;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "venda_condicoes_pagamento")
	private String vendaCondicoesPagamento;

	@OneToMany(mappedBy = "compraFornecedorCotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<CotacaoDetalhe> cotacaoDetalhes = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_compra_cotacao")
	private Cotacao cotacao;

	public FornecedorCotacao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getPrazoEntrega() {
		return this.prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public BigDecimal getTaxaDesconto() {
		return this.taxaDesconto;
	}

	public void setTaxaDesconto(BigDecimal taxaDesconto) {
		this.taxaDesconto = taxaDesconto;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public String getVendaCondicoesPagamento() {
		return this.vendaCondicoesPagamento;
	}

	public void setVendaCondicoesPagamento(String vendaCondicoesPagamento) {
		this.vendaCondicoesPagamento = vendaCondicoesPagamento;
	}

	public List<CotacaoDetalhe> getCotacaoDetalhes() {
		return this.cotacaoDetalhes;
	}

	public void setCotacaoDetalhes(List<CotacaoDetalhe> cotacaoDetalhes) {
		this.cotacaoDetalhes = cotacaoDetalhes;
	}

	public CotacaoDetalhe addCotacaoDetalhe(CotacaoDetalhe cotacaoDetalhe) {
		getCotacaoDetalhes().add(cotacaoDetalhe);
		cotacaoDetalhe.setCompraFornecedorCotacao(this);

		return cotacaoDetalhe;
	}

	public CotacaoDetalhe removeCotacaoDetalhe(CotacaoDetalhe cotacaoDetalhe) {
		getCotacaoDetalhes().remove(cotacaoDetalhe);
		cotacaoDetalhe.setCompraFornecedorCotacao(null);

		return cotacaoDetalhe;
	}

	public Cotacao getCotacao() {
		return this.cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}

}