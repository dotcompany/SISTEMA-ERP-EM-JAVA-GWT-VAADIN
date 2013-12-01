package dc.entidade.comercial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.folhapagamento.VendedorEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.Cliente;
import dc.entidade.pessoal.Transportadora;


@Entity
@Table(name = "venda_orcamento_cabecalho")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Orcamento extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tnf")
	@SequenceGenerator(name = "tnf", sequenceName = "venda_orcamento_cabecalho_id_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_vendedor")
	@Caption("Vendedor")
	VendedorEntity vendedor;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	@Caption("Cliente")
	Cliente cliente;

	@ManyToOne
	@JoinColumn(name="id_venda_condicoes_pagamento")
	CondicaoPagamento condicaoPagamento;
		
	@ManyToOne
	@JoinColumn(name="id_transportadora")
	Transportadora transportadora;

	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro")
	Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name="data_entrega")
	Date dataEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="validade")
	Date dataValidade;

	@Column(name="valor_subtotal")
	BigDecimal valorSubTotal;

	@Column(name="valor_frete")
	BigDecimal valorFrete;

	@Column(name="taxa_comissao")
	BigDecimal taxaComissao;

	@Column(name="valor_comissao")
	BigDecimal valorComissao;

	@Column(name="taxa_desconto")
	BigDecimal taxaDesconto;

	@Column(name="valor_desconto")
	BigDecimal valorDesconto;

	@Column(name="valor_total")
	BigDecimal valorTotal;

	String observacao;

	@OneToMany(mappedBy="orcamento",orphanRemoval = true,cascade=CascadeType.ALL)
	List<ItemOrcamento> itens = new ArrayList<ItemOrcamento>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VendedorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorEntity vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public void adicionarItem(ItemOrcamento item){
		getItens().add(item);
		item.setOrcamento(this);
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(BigDecimal valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Vendedor:" + vendedor.toString() + ", Cliente:" + cliente.toString() + "]";
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	






}
