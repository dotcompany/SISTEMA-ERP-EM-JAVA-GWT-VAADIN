package dc.entidade.suprimentos.compra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.geral.FornecedorEntity;

/**
 * The persistent class for the compra_pedido database table.
 * 
 */
@Entity
@Table(name = "compra_pedido")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PedidoEntity extends AbstractMultiEmpresaModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_pedido_id_seq")
	@SequenceGenerator(name = "compra_pedido_id_seq", sequenceName = "compra_pedido_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "base_calculo_icms")
	private BigDecimal baseCalculoIcms;

	@Column(name = "base_calculo_icms_st")
	private BigDecimal baseCalculoIcmsSt;

	@Caption("Contato")
	private String contato;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_pedido")
	@Caption("Data do pedido")
	private Date dataPedido;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_previsao_pagamento")
	private Date dataPrevisaoPagamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_prevista_entrega")
	private Date dataPrevistaEntrega;

	@Column(name = "dias_intervalo")
	private Integer diasIntervalo;

	@Column(name = "dias_primeiro_vencimento")
	private Integer diasPrimeiroVencimento;

	@Column(name = "forma_pagamento")
	private String formaPagamento;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	@Caption("Fornecedor")
	private FornecedorEntity fornecedor;

	@Column(name = "local_cobranca")
	private String localCobranca;

	@Column(name = "local_entrega")
	private String localEntrega;

	@Column(name = "quantidade_parcelas")
	private Integer quantidadeParcelas;

	@Column(name = "taxa_desconto")
	private BigDecimal taxaDesconto;

	@Column(name = "tipo_frete")
	private String tipoFrete;

	@Column(name = "valor_desconto")
	private BigDecimal valorDesconto;

	@Column(name = "valor_frete")
	private BigDecimal valorFrete;

	@Column(name = "valor_icms")
	private BigDecimal valorIcms;

	@Column(name = "valor_icms_st")
	private BigDecimal valorIcmsSt;

	@Column(name = "valor_ipi")
	private BigDecimal valorIpi;

	@Column(name = "valor_outras_despesas")
	private BigDecimal valorOutrasDespesas;

	@Column(name = "valor_seguro")
	private BigDecimal valorSeguro;

	@Column(name = "valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "valor_total_nf")
	private BigDecimal valorTotalNf;

	@Column(name = "valor_total_pedido")
	private BigDecimal valorTotalPedido;

	@Column(name = "valor_total_produtos")
	private BigDecimal valorTotalProdutos;

	@ManyToOne
	@JoinColumn(name = "id_compra_tipo_pedido")
	@Caption("Tipo do pedido")
	private TipoPedidoEntity tipoPedido;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private List<PedidoDetalheEntity> pedidoDetalhes = new ArrayList<>();

	public Integer getId() {
		return this.id;
	}

	public String getContato() {
		return this.contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Date getDataPedido() {
		return this.dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataPrevisaoPagamento() {
		return this.dataPrevisaoPagamento;
	}

	public void setDataPrevisaoPagamento(Date dataPrevisaoPagamento) {
		this.dataPrevisaoPagamento = dataPrevisaoPagamento;
	}

	public Date getDataPrevistaEntrega() {
		return this.dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public Integer getDiasIntervalo() {
		return this.diasIntervalo;
	}

	public void setDiasIntervalo(Integer diasIntervalo) {
		this.diasIntervalo = diasIntervalo;
	}

	public Integer getDiasPrimeiroVencimento() {
		return this.diasPrimeiroVencimento;
	}

	public void setDiasPrimeiroVencimento(Integer diasPrimeiroVencimento) {
		this.diasPrimeiroVencimento = diasPrimeiroVencimento;
	}

	public String getFormaPagamento() {
		return this.formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getLocalCobranca() {
		return this.localCobranca;
	}

	public void setLocalCobranca(String localCobranca) {
		this.localCobranca = localCobranca;
	}

	public String getLocalEntrega() {
		return this.localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public Integer getQuantidadeParcelas() {
		return this.quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getTipoFrete() {
		return this.tipoFrete;
	}

	public void setTipoFrete(String tipoFrete) {
		this.tipoFrete = tipoFrete;
	}

	public TipoPedidoEntity getTipoPedido() {
		return this.tipoPedido;
	}

	public void setTipoPedido(TipoPedidoEntity tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	public List<PedidoDetalheEntity> getPedidoDetalhes() {
		return this.pedidoDetalhes;
	}

	public void setPedidoDetalhes(List<PedidoDetalheEntity> compraPedidoDetalhes) {
		this.pedidoDetalhes = compraPedidoDetalhes;
	}

	public PedidoDetalheEntity addPedidoDetalhe(
			PedidoDetalheEntity pedidoDetalhe) {
		getPedidoDetalhes().add(pedidoDetalhe);
		pedidoDetalhe.setPedido(this);

		return pedidoDetalhe;
	}

	public PedidoDetalheEntity removePedidoDetalhe(
			PedidoDetalheEntity pedidoDetalhe) {
		getPedidoDetalhes().remove(pedidoDetalhe);
		pedidoDetalhe.setPedido(null);

		return pedidoDetalhe;
	}

	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public BigDecimal getBaseCalculoIcmsSt() {
		return baseCalculoIcmsSt;
	}

	public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
		this.baseCalculoIcmsSt = baseCalculoIcmsSt;
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

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public BigDecimal getValorOutrasDespesas() {
		return valorOutrasDespesas;
	}

	public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
		this.valorOutrasDespesas = valorOutrasDespesas;
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorTotalNf() {
		return valorTotalNf;
	}

	public void setValorTotalNf(BigDecimal valorTotalNf) {
		this.valorTotalNf = valorTotalNf;
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public BigDecimal getValorTotalProdutos() {
		return valorTotalProdutos;
	}

	public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
		this.valorTotalProdutos = valorTotalProdutos;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}