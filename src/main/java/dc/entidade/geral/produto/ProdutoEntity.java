package dc.entidade.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.IpptEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;

@Entity
@Table(name = "produto")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ProdutoEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
	@SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("GTIN")
	@Column(name = "GTIN", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtin = "";

	@Field
	@Caption("Código interno")
	@Column(name = "CODIGO_INTERNO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoInterno = "";

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	// @Lob
	@Type(type = "text")
	@Field
	@Caption("Descrição")
	@Column(name = "DESCRICAO", length = 65535)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao = "";

	@Field
	@Caption("Descrição do PDV")
	@Column(name = "DESCRICAO_PDV", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoPdv = "";

	@Field
	@Caption("Valor da compra")
	@Column(name = "VALOR_COMPRA", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCompra = new BigDecimal(0);

	@Field
	@Caption("Valor da venda")
	@Column(name = "VALOR_VENDA", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorVenda = new BigDecimal(0);

	@Field
	@Caption("Preço de venda mínimo")
	@Column(name = "PRECO_VENDA_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoVendaMinimo = new BigDecimal(0);

	@Field
	@Caption("Preço sugerido")
	@Column(name = "PRECO_SUGERIDO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoSugerido = new BigDecimal(0);

	@Field
	@Caption("Custo médio líquido")
	@Column(name = "CUSTO_MEDIO_LIQUIDO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal custoMedioLiquido = new BigDecimal(0);

	@Field
	@Caption("Preço de lucro zero")
	@Column(name = "PRECO_LUCRO_ZERO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroZero = new BigDecimal(0);

	@Field
	@Caption("Preço de lucro mínimo")
	@Column(name = "PRECO_LUCRO_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroMinimo = new BigDecimal(0);

	@Field
	@Caption("Preço de lucro máximo")
	@Column(name = "PRECO_LUCRO_MAXIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroMaximo = new BigDecimal(0);

	@Field
	@Caption("Markup")
	@Column(name = "MARKUP", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal markup = new BigDecimal(0);

	@Field
	@Caption("Quantidade de estoque")
	@Column(name = "QUANTIDADE_ESTOQUE", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoque = new BigDecimal(0);

	@Field
	@Caption("Quantidade de estoque anterior")
	@Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoqueAnterior = new BigDecimal(0);

	@Field
	@Caption("Estoque mínimo")
	@Column(name = "ESTOQUE_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMinimo = new BigDecimal(0);

	@Field
	@Caption("Estoque máximo")
	@Column(name = "ESTOQUE_MAXIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMaximo = new BigDecimal(0);

	@Field
	@Caption("Estoque ideal")
	@Column(name = "ESTOQUE_IDEAL", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueIdeal = new BigDecimal(0);

	@Field
	@Caption("Código LST")
	@Column(name = "codigo_lst")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoLst = "";

	@Field
	@Caption("Totalizador parcial")
	@Column(name = "TOTALIZADOR_PARCIAL", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String totalizadorParcial = "";

	@Field
	@Caption("Código da balança")
	@Column(name = "CODIGO_BALANCA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoBalanca = new Integer(0);

	@Field
	@Caption("Peso")
	@Column(name = "PESO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal peso = new BigDecimal(0);

	@Field
	@Caption("Porcentagem da comissão")
	@Column(name = "PORCENTO_COMISSAO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaComissao = new BigDecimal(0);

	@Field
	@Caption("Ponto do pedido")
	@Column(name = "PONTO_PEDIDO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal pontoPedido = new BigDecimal(0);

	@Field
	@Caption("Lote econômico de compra")
	@Column(name = "LOTE_ECONOMICO_COMPRA", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal loteEconomicoCompra = new BigDecimal(0);

	@Field
	@Caption("Alíquota ICMS")
	@Column(name = "aliquota_icms_paf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIcms = new BigDecimal(0);

	@Field
	@Caption("Alíquota ISSQN")
	@Column(name = "aliquota_issqn_paf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIssqn = new BigDecimal(0);

	@Field
	@Caption("EXTIPI")
	@Column(name = "EX_TIPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String exTipi = "";

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo de venda")
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private VendaTipoVendaEn tipoVenda;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Inativo")
	@Column(name = "INATIVO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private SimNaoEn inativo;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Classe")
	@Column(name = "CLASSE_ABC")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private ClasseEn classe;

	@Enumerated(EnumType.STRING)
	// @Type(type = "dc.control.enums.IatEn", parameters = @Parameter(name =
	// "type", value = "dc.control.enums.IatEn"))
	@Field
	@Caption("IAT")
	@Column(name = "iat")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private IatEn iat;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("IPPT")
	@Column(name = "ippt")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private IpptEn ippt;

	@Enumerated(EnumType.STRING)
	@Field
	@Caption("Tipo do item SPED")
	@Column(name = "tipo_item_sped")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private TipoSpedEn tipoSped;

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Subgrupo")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_sub_grupo", nullable = false)
	private SubGrupoEntity subGrupo;

	@Caption("Grupo")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_grupo_produto", nullable = false)
	private GrupoEntity grupo;

	@Caption("Unidade do produto")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_unidade_produto", nullable = false)
	private UnidadeProdutoEntity unidadeProduto;

	@Caption("Marca do produto")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_marca_produto", nullable = false)
	private MarcaEntity marca;

	@Caption("Almoxarifado")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_almoxarifado", nullable = false)
	private AlmoxarifadoEntity almoxarifado;

	@Caption("NCM")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ncm", nullable = false)
	private NcmEntity ncm;

	@Caption("Grupo tributário")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_grupo_tributario", nullable = true)
	private GrupoTributarioEntity grupoTributario;

	@Caption("ICMS customizado")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tribut_icms_custom_cab")
	private IcmsCustomizadoCabecalhoEntity icmsCustomizado;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public ProdutoEntity() {

	}

	public ProdutoEntity(Integer id) {
		this.id = id;
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

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = (gtin == null ? "".trim() : gtin);
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = (codigoInterno == null ? "".trim() : codigoInterno
				.toUpperCase().trim());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}

	public String getDescricaoPdv() {
		return descricaoPdv;
	}

	public void setDescricaoPdv(String descricaoPdv) {
		this.descricaoPdv = (descricaoPdv == null ? "".trim() : descricaoPdv
				.toUpperCase().trim());
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = (valorCompra == null ? new BigDecimal(0)
				: valorCompra);
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = (valorVenda == null ? new BigDecimal(0) : valorVenda);
	}

	public BigDecimal getPrecoVendaMinimo() {
		return precoVendaMinimo;
	}

	public void setPrecoVendaMinimo(BigDecimal precoVendaMinimo) {
		this.precoVendaMinimo = (precoVendaMinimo == null ? new BigDecimal(0)
				: precoVendaMinimo);
	}

	public BigDecimal getPrecoSugerido() {
		return precoSugerido;
	}

	public void setPrecoSugerido(BigDecimal precoSugerido) {
		this.precoSugerido = (precoSugerido == null ? new BigDecimal(0)
				: precoSugerido);
	}

	public BigDecimal getCustoMedioLiquido() {
		return custoMedioLiquido;
	}

	public void setCustoMedioLiquido(BigDecimal custoMedioLiquido) {
		this.custoMedioLiquido = (custoMedioLiquido == null ? new BigDecimal(0)
				: custoMedioLiquido);
	}

	public BigDecimal getPrecoLucroZero() {
		return precoLucroZero;
	}

	public void setPrecoLucroZero(BigDecimal precoLucroZero) {
		this.precoLucroZero = (precoLucroZero == null ? new BigDecimal(0)
				: precoLucroZero);
	}

	public BigDecimal getPrecoLucroMinimo() {
		return precoLucroMinimo;
	}

	public void setPrecoLucroMinimo(BigDecimal precoLucroMinimo) {
		this.precoLucroMinimo = (precoLucroMinimo == null ? new BigDecimal(0)
				: precoLucroMinimo);
	}

	public BigDecimal getPrecoLucroMaximo() {
		return precoLucroMaximo;
	}

	public void setPrecoLucroMaximo(BigDecimal precoLucroMaximo) {
		this.precoLucroMaximo = (precoLucroMaximo == null ? new BigDecimal(0)
				: precoLucroMaximo);
	}

	public BigDecimal getMarkup() {
		return markup;
	}

	public void setMarkup(BigDecimal markup) {
		this.markup = (markup == null ? new BigDecimal(0) : markup);
	}

	public BigDecimal getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
		this.quantidadeEstoque = (quantidadeEstoque == null ? new BigDecimal(0)
				: quantidadeEstoque);
	}

	public BigDecimal getQuantidadeEstoqueAnterior() {
		return quantidadeEstoqueAnterior;
	}

	public void setQuantidadeEstoqueAnterior(
			BigDecimal quantidadeEstoqueAnterior) {
		this.quantidadeEstoqueAnterior = (quantidadeEstoqueAnterior == null ? new BigDecimal(
				0) : quantidadeEstoqueAnterior);
	}

	public BigDecimal getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
		this.estoqueMinimo = (estoqueMinimo == null ? new BigDecimal(0)
				: estoqueMinimo);
	}

	public BigDecimal getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public void setEstoqueMaximo(BigDecimal estoqueMaximo) {
		this.estoqueMaximo = (estoqueMaximo == null ? new BigDecimal(0)
				: estoqueMaximo);
	}

	public BigDecimal getEstoqueIdeal() {
		return estoqueIdeal;
	}

	public void setEstoqueIdeal(BigDecimal estoqueIdeal) {
		this.estoqueIdeal = (estoqueIdeal == null ? new BigDecimal(0)
				: estoqueIdeal);
	}

	public String getCodigoLst() {
		return codigoLst;
	}

	public void setCodigoLst(String codigoLst) {
		this.codigoLst = (codigoLst == null ? "".trim() : codigoLst
				.toUpperCase().trim());
	}

	public String getTotalizadorParcial() {
		return totalizadorParcial;
	}

	public void setTotalizadorParcial(String totalizadorParcial) {
		this.totalizadorParcial = (totalizadorParcial == null ? "".trim()
				: totalizadorParcial.toUpperCase().trim());
	}

	public Integer getCodigoBalanca() {
		return codigoBalanca;
	}

	public void setCodigoBalanca(Integer codigoBalanca) {
		this.codigoBalanca = (codigoBalanca == null ? new Integer(0)
				: codigoBalanca);
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = (peso == null ? new BigDecimal(0) : peso);
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = (taxaComissao == null ? new BigDecimal(0)
				: taxaComissao);
	}

	public BigDecimal getPontoPedido() {
		return pontoPedido;
	}

	public void setPontoPedido(BigDecimal pontoPedido) {
		this.pontoPedido = (pontoPedido == null ? new BigDecimal(0)
				: pontoPedido);
	}

	public BigDecimal getLoteEconomicoCompra() {
		return loteEconomicoCompra;
	}

	public void setLoteEconomicoCompra(BigDecimal loteEconomicoCompra) {
		this.loteEconomicoCompra = (loteEconomicoCompra == null ? new BigDecimal(
				0) : loteEconomicoCompra);
	}

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = (aliquotaIcms == null ? new BigDecimal(0)
				: aliquotaIcms);
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = (aliquotaIssqn == null ? new BigDecimal(0)
				: aliquotaIssqn);
	}

	public String getExTipi() {
		return exTipi;
	}

	public void setExTipi(String exTipi) {
		this.exTipi = (exTipi == null ? "".trim() : exTipi.toUpperCase().trim());
	}

	public VendaTipoVendaEn getTipoVenda() {
		return tipoVenda;
	}

	public void setTipoVenda(VendaTipoVendaEn tipoVenda) {
		this.tipoVenda = tipoVenda;
	}

	public SimNaoEn getInativo() {
		return inativo;
	}

	public void setInativo(SimNaoEn inativo) {
		this.inativo = inativo;
	}

	public ClasseEn getClasse() {
		return classe;
	}

	public void setClasse(ClasseEn classe) {
		this.classe = classe;
	}

	public IatEn getIat() {
		return iat;
	}

	public void setIat(IatEn iat) {
		this.iat = iat;
	}

	public IpptEn getIppt() {
		return ippt;
	}

	public void setIppt(IpptEn ippt) {
		this.ippt = ippt;
	}

	public TipoSpedEn getTipoSped() {
		return tipoSped;
	}

	public void setTipoSped(TipoSpedEn tipoSped) {
		this.tipoSped = tipoSped;
	}

	public SubGrupoEntity getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupoEntity subGrupo) {
		this.subGrupo = subGrupo;
	}

	public GrupoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEntity grupo) {
		this.grupo = grupo;
	}

	public UnidadeProdutoEntity getUnidadeProduto() {
		return unidadeProduto;
	}

	public void setUnidadeProduto(UnidadeProdutoEntity unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}

	public AlmoxarifadoEntity getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(AlmoxarifadoEntity almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public NcmEntity getNcm() {
		return ncm;
	}

	public void setNcm(NcmEntity ncm) {
		this.ncm = ncm;
	}

	public GrupoTributarioEntity getGrupoTributario() {
		return grupoTributario;
	}

	public void setGrupoTributario(GrupoTributarioEntity grupoTributario) {
		this.grupoTributario = grupoTributario;
	}

	public IcmsCustomizadoCabecalhoEntity getIcmsCustomizado() {
		return icmsCustomizado;
	}

	public void setIcmsCustomizado(
			IcmsCustomizadoCabecalhoEntity icmsCustomizado) {
		this.icmsCustomizado = icmsCustomizado;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}