package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.Fornecedor;

/**
 * The persistent class for the compra_fornecedor_cotacao database table.
 * 
 */
@Entity
@Table(name = "compra_fornecedor_cotacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FornecedorCotacaoEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_fornecedor_cotacao_id_seq")
	@SequenceGenerator(name = "compra_fornecedor_cotacao_id_seq", sequenceName = "compra_fornecedor_cotacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
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
	private List<CotacaoDetalheEntity> cotacaoDetalhes = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_compra_cotacao")
	private Cotacao cotacao;

	public FornecedorCotacaoEntity() {
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

	public List<CotacaoDetalheEntity> getCotacaoDetalhes() {
		return this.cotacaoDetalhes;
	}

	public void setCotacaoDetalhes(List<CotacaoDetalheEntity> cotacaoDetalhes) {
		this.cotacaoDetalhes = cotacaoDetalhes;
	}

	public CotacaoDetalheEntity addCotacaoDetalhe(
			CotacaoDetalheEntity cotacaoDetalhe) {
		getCotacaoDetalhes().add(cotacaoDetalhe);
		cotacaoDetalhe.setCompraFornecedorCotacao(this);

		return cotacaoDetalhe;
	}

	public CotacaoDetalheEntity removeCotacaoDetalhe(
			CotacaoDetalheEntity cotacaoDetalhe) {
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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}