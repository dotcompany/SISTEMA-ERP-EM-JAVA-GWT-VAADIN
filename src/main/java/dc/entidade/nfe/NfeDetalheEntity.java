package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "nfe_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_id_seq", sequenceName = "nfe_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "numero_item")
	@Caption(value = "Número do item")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numeroItem = new Integer(0);

	@Field
	@Column(name = "codigo_produto")
	@Caption(value = "Código do produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoProduto = "";

	@Field
	@Column(name = "gtin")
	@Caption(value = "GTIN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtin = "";

	@Field
	@Column(name = "nome_produto")
	@Caption(value = "Nome do produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeProduto = "";

	@Field
	@Column(name = "ncm")
	@Caption(value = "NCM")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ncm = "";

	@Field
	@Column(name = "ex_tipi")
	@Caption(value = "EX TIPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer exTipi = new Integer(0);

	@Field
	@Column(name = "cfop")
	@Caption(value = "CFOP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer cfop = new Integer(0);

	@Field
	@Column(name = "unidade_comercial")
	@Caption(value = "Unidade comercial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String unidadeComercial = "";

	@Field
	@Column(name = "quantidade_comercial")
	@Caption(value = "Quantidade comercial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeComercial = new BigDecimal(0);

	@Field
	@Column(name = "valor_unitario_comercial")
	@Caption(value = "Valor unitário comercial")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnitarioComercial = new BigDecimal(0);

	@Field
	@Column(name = "valor_bruto_produto")
	@Caption(value = "Valor bruto do produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorBrutoProduto = new BigDecimal(0);

	@Field
	@Column(name = "gtin_unidade_tributavel")
	@Caption(value = "GTIN unidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtinUnidadeTributavel = "";

	@Field
	@Column(name = "unidade_tributavel")
	@Caption(value = "Unidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String unidadeTributavel = "";

	@Field
	@Column(name = "quantidade_tributavel")
	@Caption(value = "Quantidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeTributavel = new BigDecimal(0);

	@Field
	@Column(name = "valor_unitario_tributavel")
	@Caption(value = "Valor unitário tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorUnitarioTributavel = new BigDecimal(0);

	@Field
	@Column(name = "valor_frete")
	@Caption(value = "Valor do frete")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorFrete = new BigDecimal(0);

	@Field
	@Column(name = "valor_seguro")
	@Caption(value = "Valor do seguro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSeguro = new BigDecimal(0);

	@Field
	@Column(name = "valor_desconto")
	@Caption(value = "Valor do desconto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorDesconto = new BigDecimal(0);

	@Field
	@Column(name = "valor_outras_despesas")
	@Caption(value = "Valor de outras despesas")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorOutrasDespesas = new BigDecimal(0);

	@Field
	@Column(name = "entra_total")
	@Caption(value = "Entra total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String entraTotal = "";

	@Field
	@Column(name = "valor_subtotal")
	@Caption(value = "Valor subtotal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorSubtotal = new BigDecimal(0);

	@Field
	@Column(name = "valor_total")
	@Caption(value = "Valor total")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorTotal = new BigDecimal(0);

	@Field
	@Column(name = "numero_pedido_compra")
	@Caption(value = "Número do pedido de compra")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroPedidoCompra = "";

	@Field
	@Column(name = "item_pedido_compra")
	@Caption(value = "Item do pedido de compra")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer itemPedidoCompra = new Integer(0);

	@Field
	@Column(name = "informacoes_adicionais")
	@Caption(value = "Informações adicionais")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String informacoesAdicionais = "";

	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Column(name = "id_produto")
	@Caption(value = "Produto")
	private Integer produto;

	@ManyToOne
	@JoinColumn(name = "id_nfe_cabecalho", nullable = false)
	@Caption(value = "NFE cabeçalho")
	private NfeCabecalhoEntity nfeCabecalho;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoCofinsEntity nfeDetalheImpostoCofins;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoIcmsEntity nfeDetalheImpostoIcms;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoIiEntity nfeDetalheImpostoIi;

	// @OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	// private NfeDetalheImpostoIpiEntity nfeDetalheImpostoIpi;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoIssqnEntity nfeDetalheImpostoIssqn;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetalheImpostoPisEntity nfeDetalheImpostoPis;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetEspecificoCombustivelEntity nfeDetEspecificoCombustivel;

	@OneToOne(mappedBy = "nfeDetalhe", cascade = CascadeType.ALL)
	private NfeDetEspecificoVeiculoEntity nfeDetEspecificoVeiculo;

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "nfeDetalhe", fetch = FetchType.LAZY)
	// private List<NfeDetalheImpostoCofinsEntity> nfeDetalheImpostoCofinsList;

	/**
	 * CONSTRUTOR
	 */

	public NfeDetalheEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = (numeroItem == null ? new Integer(0) : numeroItem);
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = (codigoProduto == null ? "" : codigoProduto
				.toUpperCase());
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = (gtin == null ? "" : gtin.toUpperCase());
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = (nomeProduto == null ? "" : nomeProduto
				.toUpperCase());
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = (ncm == null ? "" : ncm.toUpperCase());
	}

	public Integer getExTipi() {
		return exTipi;
	}

	public void setExTipi(Integer exTipi) {
		this.exTipi = (exTipi == null ? new Integer(0) : exTipi);
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = (cfop == null ? new Integer(0) : cfop);
	}

	public String getUnidadeComercial() {
		return unidadeComercial;
	}

	public void setUnidadeComercial(String unidadeComercial) {
		this.unidadeComercial = (unidadeComercial == null ? ""
				: unidadeComercial.toUpperCase());
	}

	public BigDecimal getQuantidadeComercial() {
		return quantidadeComercial;
	}

	public void setQuantidadeComercial(BigDecimal quantidadeComercial) {
		this.quantidadeComercial = (quantidadeComercial == null ? new BigDecimal(
				0) : quantidadeComercial);
	}

	public BigDecimal getValorUnitarioComercial() {
		return valorUnitarioComercial;
	}

	public void setValorUnitarioComercial(BigDecimal valorUnitarioComercial) {
		this.valorUnitarioComercial = (valorUnitarioComercial == null ? new BigDecimal(
				0) : valorUnitarioComercial);
	}

	public BigDecimal getValorBrutoProduto() {
		return valorBrutoProduto;
	}

	public void setValorBrutoProduto(BigDecimal valorBrutoProduto) {
		this.valorBrutoProduto = (valorBrutoProduto == null ? new BigDecimal(0)
				: valorBrutoProduto);
	}

	public String getGtinUnidadeTributavel() {
		return gtinUnidadeTributavel;
	}

	public void setGtinUnidadeTributavel(String gtinUnidadeTributavel) {
		this.gtinUnidadeTributavel = (gtinUnidadeTributavel == null ? ""
				: gtinUnidadeTributavel.toUpperCase());
	}

	public String getUnidadeTributavel() {
		return unidadeTributavel;
	}

	public void setUnidadeTributavel(String unidadeTributavel) {
		this.unidadeTributavel = (unidadeTributavel == null ? ""
				: unidadeTributavel.toUpperCase());
	}

	public BigDecimal getQuantidadeTributavel() {
		return quantidadeTributavel;
	}

	public void setQuantidadeTributavel(BigDecimal quantidadeTributavel) {
		this.quantidadeTributavel = (quantidadeTributavel == null ? new BigDecimal(
				0) : quantidadeTributavel);
	}

	public BigDecimal getValorUnitarioTributavel() {
		return valorUnitarioTributavel;
	}

	public void setValorUnitarioTributavel(BigDecimal valorUnitarioTributavel) {
		this.valorUnitarioTributavel = (valorUnitarioTributavel == null ? new BigDecimal(
				0) : valorUnitarioTributavel);
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = (valorFrete == null ? new BigDecimal(0) : valorFrete);
	}

	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = (valorSeguro == null ? new BigDecimal(0)
				: valorSeguro);
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = (valorDesconto == null ? new BigDecimal(0)
				: valorDesconto);
	}

	public BigDecimal getValorOutrasDespesas() {
		return valorOutrasDespesas;
	}

	public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
		this.valorOutrasDespesas = (valorOutrasDespesas == null ? new BigDecimal(
				0) : valorOutrasDespesas);
	}

	public String getEntraTotal() {
		return entraTotal;
	}

	public void setEntraTotal(String entraTotal) {
		this.entraTotal = (entraTotal == null ? "" : entraTotal.toUpperCase());
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = (valorSubtotal == null ? new BigDecimal(0)
				: valorSubtotal);
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = (valorTotal == null ? new BigDecimal(0) : valorTotal);
	}

	public String getNumeroPedidoCompra() {
		return numeroPedidoCompra;
	}

	public void setNumeroPedidoCompra(String numeroPedidoCompra) {
		this.numeroPedidoCompra = (numeroPedidoCompra == null ? ""
				: numeroPedidoCompra.toUpperCase());
	}

	public Integer getItemPedidoCompra() {
		return itemPedidoCompra;
	}

	public void setItemPedidoCompra(Integer itemPedidoCompra) {
		this.itemPedidoCompra = (itemPedidoCompra == null ? new Integer(0)
				: itemPedidoCompra);
	}

	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = (informacoesAdicionais == null ? ""
				: informacoesAdicionais.toUpperCase());
	}

	public Integer getProduto() {
		return produto;
	}

	public void setProduto(Integer produto) {
		this.produto = (produto == null ? new Integer(0) : produto);
	}

	public NfeCabecalhoEntity getNfeCabecalho() {
		return nfeCabecalho;
	}

	public void setNfeCabecalho(NfeCabecalhoEntity nfeCabecalho) {
		this.nfeCabecalho = nfeCabecalho;
	}

	public NfeDetalheImpostoCofinsEntity getNfeDetalheImpostoCofins() {
		return nfeDetalheImpostoCofins;
	}

	public void setNfeDetalheImpostoCofins(
			NfeDetalheImpostoCofinsEntity nfeDetalheImpostoCofins) {
		this.nfeDetalheImpostoCofins = nfeDetalheImpostoCofins;
	}

	public NfeDetalheImpostoIcmsEntity getNfeDetalheImpostoIcms() {
		return nfeDetalheImpostoIcms;
	}

	public void setNfeDetalheImpostoIcms(
			NfeDetalheImpostoIcmsEntity nfeDetalheImpostoIcms) {
		this.nfeDetalheImpostoIcms = nfeDetalheImpostoIcms;
	}

	public NfeDetalheImpostoIiEntity getNfeDetalheImpostoIi() {
		return nfeDetalheImpostoIi;
	}

	public void setNfeDetalheImpostoIi(
			NfeDetalheImpostoIiEntity nfeDetalheImpostoIi) {
		this.nfeDetalheImpostoIi = nfeDetalheImpostoIi;
	}

	/*
	 * public NfeDetalheImpostoIpiEntity getNfeDetalheImpostoIpi() { return
	 * nfeDetalheImpostoIpi; }
	 * 
	 * public void setNfeDetalheImpostoIpi( NfeDetalheImpostoIpiEntity
	 * nfeDetalheImpostoIpi) { this.nfeDetalheImpostoIpi = nfeDetalheImpostoIpi;
	 * }
	 */

	public NfeDetalheImpostoIssqnEntity getNfeDetalheImpostoIssqn() {
		return nfeDetalheImpostoIssqn;
	}

	public void setNfeDetalheImpostoIssqn(
			NfeDetalheImpostoIssqnEntity nfeDetalheImpostoIssqn) {
		this.nfeDetalheImpostoIssqn = nfeDetalheImpostoIssqn;
	}

	public NfeDetalheImpostoPisEntity getNfeDetalheImpostoPis() {
		return nfeDetalheImpostoPis;
	}

	public void setNfeDetalheImpostoPis(
			NfeDetalheImpostoPisEntity nfeDetalheImpostoPis) {
		this.nfeDetalheImpostoPis = nfeDetalheImpostoPis;
	}

	public NfeDetEspecificoCombustivelEntity getNfeDetEspecificoCombustivel() {
		return nfeDetEspecificoCombustivel;
	}

	public void setNfeDetEspecificoCombustivel(
			NfeDetEspecificoCombustivelEntity nfeDetEspecificoCombustivel) {
		this.nfeDetEspecificoCombustivel = nfeDetEspecificoCombustivel;
	}

	public NfeDetEspecificoVeiculoEntity getNfeDetEspecificoVeiculo() {
		return nfeDetEspecificoVeiculo;
	}

	public void setNfeDetEspecificoVeiculo(
			NfeDetEspecificoVeiculoEntity nfeDetEspecificoVeiculo) {
		this.nfeDetEspecificoVeiculo = nfeDetEspecificoVeiculo;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}