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
	@Caption("Gtin")
	@Column(name = "GTIN", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gtin;

	@Field
	@Caption("Código interno")
	@Column(name = "CODIGO_INTERNO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoInterno;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	// @Lob
	@Field
	@Caption("Descrição")
	@Type(type = "text")
	@Column(name = "DESCRICAO", length = 65535)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@Field
	@Caption("Descrição Pdv")
	@Column(name = "DESCRICAO_PDV", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoPdv;

	@Field
	@Caption()
	@Column(name = "VALOR_COMPRA", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCompra;

	@Field
	@Caption()
	@Column(name = "VALOR_VENDA", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorVenda;

	@Field
	@Caption()
	@Column(name = "PRECO_VENDA_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoVendaMinimo;

	@Field
	@Caption()
	@Column(name = "PRECO_SUGERIDO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoSugerido;

	@Field
	@Caption()
	@Column(name = "CUSTO_MEDIO_LIQUIDO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal custoMedioLiquido;

	@Field
	@Caption()
	@Column(name = "PRECO_LUCRO_ZERO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroZero;

	@Field
	@Caption()
	@Column(name = "PRECO_LUCRO_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroMinimo;

	@Field
	@Caption()
	@Column(name = "PRECO_LUCRO_MAXIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroMaximo;

	@Field
	@Caption()
	@Column(name = "MARKUP", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal markup;

	@Field
	@Caption()
	@Column(name = "QUANTIDADE_ESTOQUE", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoque;

	@Field
	@Caption()
	@Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoqueAnterior;

	@Field
	@Caption()
	@Column(name = "ESTOQUE_MINIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMinimo;

	@Field
	@Caption()
	@Column(name = "ESTOQUE_MAXIMO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMaximo;

	@Field
	@Caption()
	@Column(name = "ESTOQUE_IDEAL", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueIdeal;

	@Field
	@Caption()
	@Column(name = "codigo_lst")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoLst;

	@Field
	@Caption()
	@Column(name = "TOTALIZADOR_PARCIAL", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String totalizadorParcial;

	@Field
	@Caption()
	@Column(name = "CODIGO_BALANCA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoBalanca;

	@Field
	@Caption()
	@Column(name = "PESO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal peso;

	@Field
	@Caption()
	@Column(name = "PORCENTO_COMISSAO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaComissao;

	@Field
	@Caption()
	@Column(name = "PONTO_PEDIDO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal pontoPedido;

	@Field
	@Caption()
	@Column(name = "LOTE_ECONOMICO_COMPRA", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal loteEconomicoCompra;

	@Field
	@Caption()
	@Column(name = "aliquota_icms_paf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIcms;

	@Field
	@Caption()
	@Column(name = "aliquota_issqn_paf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIssqn;

	@Field
	@Caption()
	@Column(name = "EX_TIPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String exTipi;

	@Field
	@Caption()
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private VendaTipoVendaEn tipoVenda;

	@Field
	@Caption()
	@Column(name = "INATIVO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private SimNaoEn inativo;

	@Field
	@Caption()
	@Column(name = "CLASSE_ABC")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private ClasseEn classe;

	@Field
	@Caption("IAT")
	@Column(name = "iat")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	// @Type(type = "dc.control.enums.IatEn", parameters = @Parameter(name =
	// "type", value = "dc.control.enums.IatEn"))
	private IatEn iat;

	@Field
	@Caption("IPPT")
	@Column(name = "ippt")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private IpptEn ippt;

	@Field
	@Caption("Tipo Sped")
	@Column(name = "tipo_item_sped")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private TipoSpedEn tipoSped;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_sub_grupo", nullable = false)
	@Caption("Subgrupo")
	private SubGrupoEntity subGrupo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_grupo_produto", nullable = false)
	@Caption("Grupo")
	private GrupoEntity grupo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_unidade_produto", nullable = false)
	@Caption("Grupo")
	private UnidadeProdutoEntity unidadeProduto;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_marca_produto", nullable = false)
	@Caption("Marca do produto")
	private MarcaEntity marca;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_almoxarifado", nullable = false)
	@Caption("Almoxarifado")
	private AlmoxarifadoEntity almoxarifado;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ncm", nullable = false)
	@Caption("NCM")
	private NcmEntity ncm;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_grupo_tributario", nullable = true)
	@Caption("Grupo tributário")
	private GrupoTributarioEntity grupoTributario;

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
		this.gtin = gtin;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoPdv() {
		return descricaoPdv;
	}

	public void setDescricaoPdv(String descricaoPdv) {
		this.descricaoPdv = descricaoPdv;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getPrecoVendaMinimo() {
		return precoVendaMinimo;
	}

	public void setPrecoVendaMinimo(BigDecimal precoVendaMinimo) {
		this.precoVendaMinimo = precoVendaMinimo;
	}

	public BigDecimal getPrecoSugerido() {
		return precoSugerido;
	}

	public void setPrecoSugerido(BigDecimal precoSugerido) {
		this.precoSugerido = precoSugerido;
	}

	public BigDecimal getCustoMedioLiquido() {
		return custoMedioLiquido;
	}

	public void setCustoMedioLiquido(BigDecimal custoMedioLiquido) {
		this.custoMedioLiquido = custoMedioLiquido;
	}

	public BigDecimal getPrecoLucroZero() {
		return precoLucroZero;
	}

	public void setPrecoLucroZero(BigDecimal precoLucroZero) {
		this.precoLucroZero = precoLucroZero;
	}

	public BigDecimal getPrecoLucroMinimo() {
		return precoLucroMinimo;
	}

	public void setPrecoLucroMinimo(BigDecimal precoLucroMinimo) {
		this.precoLucroMinimo = precoLucroMinimo;
	}

	public BigDecimal getPrecoLucroMaximo() {
		return precoLucroMaximo;
	}

	public void setPrecoLucroMaximo(BigDecimal precoLucroMaximo) {
		this.precoLucroMaximo = precoLucroMaximo;
	}

	public BigDecimal getMarkup() {
		return markup;
	}

	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}

	public BigDecimal getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public BigDecimal getQuantidadeEstoqueAnterior() {
		return quantidadeEstoqueAnterior;
	}

	public void setQuantidadeEstoqueAnterior(
			BigDecimal quantidadeEstoqueAnterior) {
		this.quantidadeEstoqueAnterior = quantidadeEstoqueAnterior;
	}

	public BigDecimal getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public BigDecimal getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public void setEstoqueMaximo(BigDecimal estoqueMaximo) {
		this.estoqueMaximo = estoqueMaximo;
	}

	public BigDecimal getEstoqueIdeal() {
		return estoqueIdeal;
	}

	public void setEstoqueIdeal(BigDecimal estoqueIdeal) {
		this.estoqueIdeal = estoqueIdeal;
	}

	public String getCodigoLst() {
		return codigoLst;
	}

	public void setCodigoLst(String codigoLst) {
		this.codigoLst = codigoLst;
	}

	public String getTotalizadorParcial() {
		return totalizadorParcial;
	}

	public void setTotalizadorParcial(String totalizadorParcial) {
		this.totalizadorParcial = totalizadorParcial;
	}

	public Integer getCodigoBalanca() {
		return codigoBalanca;
	}

	public void setCodigoBalanca(Integer codigoBalanca) {
		this.codigoBalanca = codigoBalanca;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public BigDecimal getPontoPedido() {
		return pontoPedido;
	}

	public void setPontoPedido(BigDecimal pontoPedido) {
		this.pontoPedido = pontoPedido;
	}

	public BigDecimal getLoteEconomicoCompra() {
		return loteEconomicoCompra;
	}

	public void setLoteEconomicoCompra(BigDecimal loteEconomicoCompra) {
		this.loteEconomicoCompra = loteEconomicoCompra;
	}

	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = aliquotaIssqn;
	}

	public String getExTipi() {
		return exTipi;
	}

	public void setExTipi(String exTipi) {
		this.exTipi = exTipi;
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

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}