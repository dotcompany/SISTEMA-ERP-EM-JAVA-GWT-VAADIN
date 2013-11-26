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
import dc.entidade.suprimentos.ContagemEstoqueDetalhe;

@Entity
@Table(name = "venda_cabecalho")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Venda extends AbstractMultiEmpresaModel<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vnd")
	@SequenceGenerator(name = "vnd", sequenceName = "venda_cabecalho_id_seq", allocationSize = 1)
	private Integer id;
	
	@OneToMany(mappedBy = "venda", orphanRemoval = true,cascade=CascadeType.ALL)
	@Caption("Detalhe")
	private List<VendaDetalhe> detalhes;
	
	@ManyToOne
	@JoinColumn(name="id_venda_orcamento_cabecalho")
	Orcamento orcamento;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_nota_fiscal")
	TipoNotaFiscal tipoNotaFiscal;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_vendedor")
	VendedorEntity vendedor;
	
	@ManyToOne
	@JoinColumn(name="id_venda_condicoes_pagamento")
	CondicaoPagamento condicaoPagamento;
	
	@Column(name="data_venda")
	@Temporal(TemporalType.DATE)
	Date dataVenda;
	
	@Column(name="data_saida")
	@Temporal(TemporalType.DATE)
	Date dataSaida;
	
	@Column(name="hora_saida")
	String horaSaida;
	
	@Column(name="numero_fatura")
	Integer numeroFatura;
	
	@Column(name="local_entrega")
	String localEntrega;
	
	@Column(name="local_cobranca")
	String localCobranca;
	
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<VendaDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<VendaDetalhe> detalhes) {
		this.detalhes = detalhes;
	}
	
	public VendaDetalhe adicionarDetalhe(VendaDetalhe detalhe) {
		if(detalhes==null) detalhes = new ArrayList();
		getDetalhes().add(detalhe);
		detalhe.setVenda(this);
		return detalhe;
	}

	public TipoNotaFiscal getTipoNotaFiscal() {
		return tipoNotaFiscal;
	}

	public void setTipoNotaFiscal(TipoNotaFiscal tipoNotaFiscal) {
		this.tipoNotaFiscal = tipoNotaFiscal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public VendedorEntity getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorEntity vendedor) {
		this.vendedor = vendedor;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Integer getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(Integer numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public String getLocalCobranca() {
		return localCobranca;
	}

	public void setLocalCobranca(String localCobranca) {
		this.localCobranca = localCobranca;
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

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
	
	
		
}
