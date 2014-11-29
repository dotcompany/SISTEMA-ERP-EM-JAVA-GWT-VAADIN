package dc.entidade.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.tributario.GrupoTributario;

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
	private String gtin;

	@Field
	@Caption("Codigo Interno")
	@Column(name = "CODIGO_INTERNO", length = 60)
	private String codigoInterno;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;
	//
	// @Lob
	@Field
	@Caption("Descricao")
	@Type(type = "text")
	@Column(name = "DESCRICAO", length = 65535)
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;
	//
	@Field
	@Caption("Descricao Pdv")
	@Column(name = "DESCRICAO_PDV", length = 30)
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricaoPdv;
	//
	@Column(name = "VALOR_COMPRA", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorCompra;
	//
	@Column(name = "VALOR_VENDA", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorVenda;
	//
	@Column(name = "PRECO_VENDA_MINIMO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoVendaMinimo;
	//
	@Column(name = "PRECO_SUGERIDO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoSugerido;
	//
	@Column(name = "CUSTO_MEDIO_LIQUIDO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal custoMedioLiquido;
	//
	@Column(name = "PRECO_LUCRO_ZERO", precision = 11, scale = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroZero;

	@Column(name = "PRECO_LUCRO_MINIMO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroMinimo;

	@Column(name = "PRECO_LUCRO_MAXIMO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoLucroMaximo;

	@Column(name = "MARKUP", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal markup;

	@Column(name = "QUANTIDADE_ESTOQUE", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoque;

	@Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeEstoqueAnterior;

	@Column(name = "ESTOQUE_MINIMO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMinimo;

	@Column(name = "ESTOQUE_MAXIMO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueMaximo;

	@Column(name = "ESTOQUE_IDEAL", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal estoqueIdeal;
	//
	// @Column(name = "EXCLUIDO")
	// @ComboValue
	// @Analyzer(definition = "dc_combo_analyzer")
	// private Character excluido;
	//
	@Column(name = "INATIVO")
	@Analyzer(definition = "dc_combo_analyzer")
	private String inativo;
	//
	// @Column(name = "DATA_CADASTRO")
	// @ComboValue
	// @Analyzer(definition = "dc_combo_analyzer")
	// private Date dataCadastro;
	//
	// @Column(name = "FOTO_PRODUTO", length = 65535)
	// @ComboValue
	// @Analyzer(definition = "dc_combo_analyzer")
	// private String fotoProduto;
	//

	@Column(name = "codigo_lst")
	private String codigoLst;

	@Column(name = "EX_TIPI")
	@Analyzer(definition = "dc_combo_analyzer")
	private String exTipi;

	@Column(name = "TIPO")
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipo;

	@Column(name = "IAT")
	@Analyzer(definition = "dc_combo_analyzer")
	private String iat;

	@Column(name = "IPPT")
	@Analyzer(definition = "dc_combo_analyzer")
	private String ippt;

	@Column(name = "TIPO_ITEM_SPED")
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoItemSped;

	@Column(name = "TOTALIZADOR_PARCIAL", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String totalizadorParcial;

	@Column(name = "CODIGO_BALANCA")
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoBalanca;

	@Column(name = "CLASSE_ABC")
	@Analyzer(definition = "dc_combo_analyzer")
	private String classe;

	@Column(name = "PESO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal peso;

	@Column(name = "PORCENTO_COMISSAO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal taxaComissao;

	@Column(name = "PONTO_PEDIDO", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal pontoPedido;

	@Column(name = "LOTE_ECONOMICO_COMPRA", precision = 11, scale = 2)
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal loteEconomicoCompra;

	//
	// @Column(name = "DATA_ALTERACAO")
	// @ComboValue
	// @Analyzer(definition = "dc_combo_analyzer")
	// private Date dataAlteracao;
	//

	//
	/**
	 * Mapeamento SubGrupo-Produto
	 * 
	 * @author wesley Junior
	 **/

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_SUB_GRUPO", referencedColumnName = "ID")
	private SubGrupoProdutoEntity subGrupo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_GRUPO_PRODUTO", referencedColumnName = "ID")
	private GrupoProdutoEntity grupo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_UNIDADE_PRODUTO", referencedColumnName = "ID")
	private UnidadeProdutoEntity unidade;

	@Column(name = "aliquota_icms_paf")
	private BigDecimal aliquotaIcms;

	@Column(name = "aliquota_issqn_paf")
	private BigDecimal aliquotaIssqn;

	@ManyToOne
	@JoinColumn(name = "id_marca_produto", nullable = false)
	@Caption("Marca do produto")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private MarcaProdutoEntity marcaProduto;

	@JoinColumn(name = "ID_ALMOXARIFADO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Almoxarifado almoxarifado;

	@JoinColumn(name = "ID_GRUPO_TRIBUTARIO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private GrupoTributario grupoTributario;

	@JoinColumn(name = "ID_NCM", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private NcmEntity ncm;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<NfeDetalheEntity> nfeDetalheList;

	/**
	 * CONSTRUTOR
	 */

	public ProdutoEntity() {

	}

	public ProdutoEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// public String getGtin() {
	// return gtin;
	// }
	//
	// public void setGtin(String gtin) {
	// this.gtin = gtin;
	// }
	//
	// public String getNome() {
	// return nome;
	// }
	//
	// public void setNome(String nome) {
	// this.nome = nome;
	// }
	//
	// public String getDescricao() {
	// return descricao;
	// }
	//
	// public void setDescricao(String descricao) {
	// this.descricao = descricao;
	// }
	//
	// public String getDescricaoPdv() {
	// return descricaoPdv;
	// }
	//
	// public void setDescricaoPdv(String descricaoPdv) {
	// this.descricaoPdv = descricaoPdv;
	// }
	//
	// public BigDecimal getValorCompra() {
	// return valorCompra;
	// }
	//
	// public void setValorCompra(BigDecimal valorCompra) {
	// this.valorCompra = valorCompra;
	// }
	//
	// public BigDecimal getValorVenda() {
	// return valorVenda;
	// }
	//
	// public void setValorVenda(BigDecimal valorVenda) {
	// this.valorVenda = valorVenda;
	// }
	//
	// public BigDecimal getQtdEstoque() {
	// return qtdEstoque;
	// }
	//
	// public void setQtdEstoque(BigDecimal qtdEstoque) {
	// this.qtdEstoque = qtdEstoque;
	// }
	//
	// public BigDecimal getEstoqueMin() {
	// return estoqueMin;
	// }
	//
	// public void setEstoqueMin(BigDecimal estoqueMin) {
	// this.estoqueMin = estoqueMin;
	// }
	//
	// public BigDecimal getEstoqueMax() {
	// return estoqueMax;
	// }
	//
	// public void setEstoqueMax(BigDecimal estoqueMax) {
	// this.estoqueMax = estoqueMax;
	// }
	//
	// public Character getExcluido() {
	// return excluido;
	// }
	//
	// public void setExcluido(Character excluido) {
	// this.excluido = excluido;
	// }
	//
	// public Date getDataCadastro() {
	// return dataCadastro;
	// }
	//
	// public void setDataCadastro(Date dataCadastro) {
	// this.dataCadastro = dataCadastro;
	// }
	//
	// // /**
	// // * @return the subGrupo
	// // */
	// // public SubGrupoProduto getSubGrupo() {
	// // return subGrupo;
	// // }
	// //
	// // /**
	// // * @param subGrupo the subGrupo to set
	// // */
	// // public void setSubGrupo(SubGrupoProduto subGrupo) {
	// // this.subGrupo = subGrupo;
	// // }
	//
	// // /**
	// // * @return the unidade
	// // */
	// // public UnidadeProduto getUnidade() {
	// // return unidade;
	// // }
	// //
	// // /**
	// // * @param unidade the unidade to set
	// // */
	// // public void setUnidade(UnidadeProduto unidade) {
	// // this.unidade = unidade;
	// // }
	//
	// public String getCodigoInterno() {
	// return codigoInterno;
	// }
	//
	// public void setCodigoInterno(String codigoInterno) {
	// this.codigoInterno = codigoInterno;
	// }
	//
	// public String getNcm() {
	// return ncm;
	// }
	//
	// public void setNcm(String ncm) {
	// this.ncm = ncm;
	// }
	//
	// public BigDecimal getPrecoVendaMinimo() {
	// return precoVendaMinimo;
	// }
	//
	// public void setPrecoVendaMinimo(BigDecimal precoVendaMinimo) {
	// this.precoVendaMinimo = precoVendaMinimo;
	// }
	//
	// public BigDecimal getPrecoSugerido() {
	// return precoSugerido;
	// }
	//
	// public void setPrecoSugerido(BigDecimal precoSugerido) {
	// this.precoSugerido = precoSugerido;
	// }
	//
	// public BigDecimal getCustoMedioLiquido() {
	// return custoMedioLiquido;
	// }
	//
	// public void setCustoMedioLiquido(BigDecimal custoMedioLiquido) {
	// this.custoMedioLiquido = custoMedioLiquido;
	// }
	//
	// public BigDecimal getPrecoLucroZero() {
	// return precoLucroZero;
	// }
	//
	// public void setPrecoLucroZero(BigDecimal precoLucroZero) {
	// this.precoLucroZero = precoLucroZero;
	// }
	//
	// public BigDecimal getPrecoLucroMinimo() {
	// return precoLucroMinimo;
	// }
	//
	// public void setPrecoLucroMinimo(BigDecimal precoLucroMinimo) {
	// this.precoLucroMinimo = precoLucroMinimo;
	// }
	//
	// public BigDecimal getPrecoLucroMaximo() {
	// return precoLucroMaximo;
	// }
	//
	// public void setPrecoLucroMaximo(BigDecimal precoLucroMaximo) {
	// this.precoLucroMaximo = precoLucroMaximo;
	// }
	//
	// public BigDecimal getMarkup() {
	// return markup;
	// }
	//
	// public void setMarkup(BigDecimal markup) {
	// this.markup = markup;
	// }
	//
	// public BigDecimal getQtdEstoqueAnterior() {
	// return qtdEstoqueAnterior;
	// }
	//
	// public void setQtdEstoqueAnterior(BigDecimal qtdEstoqueAnterior) {
	// this.qtdEstoqueAnterior = qtdEstoqueAnterior;
	// }
	//
	// public BigDecimal getEstoqueIdeal() {
	// return estoqueIdeal;
	// }
	//
	// public void setEstoqueIdeal(BigDecimal estoqueIdeal) {
	// this.estoqueIdeal = estoqueIdeal;
	// }
	//
	// public String getInativo() {
	// return inativo;
	// }
	//
	// public void setInativo(String inativo) {
	// this.inativo = inativo;
	// }
	//
	// public String getFotoProduto() {
	// return fotoProduto;
	// }
	//
	// public void setFotoProduto(String fotoProduto) {
	// this.fotoProduto = fotoProduto;
	// }
	//
	// public Character getExTipi() {
	// return exTipi;
	// }
	//
	// public void setExTipi(Character exTipi) {
	// this.exTipi = exTipi;
	// }
	//
	// /*
	// * public String getCodigoIst() { return codigoIst; }
	// *
	// * public void setCodigoIst(String codigoIst) { this.codigoIst =
	// codigoIst;
	// * }
	// */
	//
	// public String getClasseAbc() {
	// return classeAbc;
	// }
	//
	// public void setClasseAbc(String classeAbc) {
	// this.classeAbc = classeAbc;
	// }
	//
	// public String getIat() {
	// return iat;
	// }
	//
	// public void setIat(String iat) {
	// this.iat = iat;
	// }
	//
	// public String getIppt() {
	// return ippt;
	// }
	//
	// public void setIppt(String ippt) {
	// this.ippt = ippt;
	// }
	//
	// public String getTipoItemSped() {
	// return tipoItemSped;
	// }
	//
	// public void setTipoItemSped(String tipoItemSped) {
	// this.tipoItemSped = tipoItemSped;
	// }
	//
	// public BigDecimal getPeso() {
	// return peso;
	// }
	//
	// public void setPeso(BigDecimal peso) {
	// this.peso = peso;
	// }
	//
	// public BigDecimal getPorcentoComissao() {
	// return porcentoComissao;
	// }
	//
	// public void setPorcentoComissao(BigDecimal porcentoComissao) {
	// this.porcentoComissao = porcentoComissao;
	// }
	//
	// public BigDecimal getPontoPedido() {
	// return pontoPedido;
	// }
	//
	// public void setPontoPedido(BigDecimal pontoPedido) {
	// this.pontoPedido = pontoPedido;
	// }
	//
	// public BigDecimal getLoteEconomicoCompra() {
	// return loteEconomicoCompra;
	// }
	//
	// public void setLoteEconomicoCompra(BigDecimal loteEconomicoCompra) {
	// this.loteEconomicoCompra = loteEconomicoCompra;
	// }
	//
	// public String getTotalizadorParcial() {
	// return totalizadorParcial;
	// }
	//
	// public void setTotalizadorParcial(String totalizadorParcial) {
	// this.totalizadorParcial = totalizadorParcial;
	// }
	//
	// public Integer getCodigoBalanca() {
	// return codigoBalanca;
	// }
	//
	// public void setCodigoBalanca(Integer codigoBalanca) {
	// this.codigoBalanca = codigoBalanca;
	// }
	//
	// public Date getDataAlteracao() {
	// return dataAlteracao;
	// }
	//
	// public void setDataAlteracao(Date dataAlteracao) {
	// this.dataAlteracao = dataAlteracao;
	// }
	//
	// public String getTipo() {
	// return tipo;
	// }
	//
	// public void setTipo(String tipo) {
	// this.tipo = tipo;
	// }
	//
	// /*
	// * public SubGrupoProduto getIdSubGrupo() { return idSubGrupo; }
	// *
	// * public void setIdSubGrupo(SubGrupoProduto idSubGrupo) { this.idSubGrupo
	// =
	// * idSubGrupo; }
	// */
	//
	// /*
	// * public UnidadeProduto getUnidade() { return unidade; }
	// */
	//
	// public SubGrupoProduto getSubgrupoProduto() {
	// return subgrupoProduto;
	// }
	//
	// public void setSubgrupoProduto(SubGrupoProduto subgrupoProduto) {
	// this.subgrupoProduto = subgrupoProduto;
	// }
	//
	// public UnidadeProduto getUnidadeProduto() {
	// return unidadeProduto;
	// }
	//
	// public void setUnidadeProduto(UnidadeProduto unidadeProduto) {
	// this.unidadeProduto = unidadeProduto;
	// }
	//
	// public MarcaProduto getMarcaProduto() {
	// return marcaProduto;
	// }
	//
	// public void setMarcaProduto(MarcaProduto marcaProduto) {
	// this.marcaProduto = marcaProduto;
	// }
	//
	// /*
	// * public void setUnidade(UnidadeProduto unidade) { this.unidade =
	// unidade;
	// * } /*public MarcaProduto getIdMarcaProduto() { return idMarcaProduto; }
	// *
	// * public void setIdMarcaProduto(MarcaProduto idMarcaProduto) {
	// * this.idMarcaProduto = idMarcaProduto; }
	// */
	//
	// public Almoxarifado getIdAlmoxarifado() {
	// return idAlmoxarifado;
	// }
	//
	// public void setIdAlmoxarifado(Almoxarifado idAlmoxarifado) {
	// this.idAlmoxarifado = idAlmoxarifado;
	// }
	//
	// public GrupoTributario getIdGrupoTributario() {
	// return idGrupoTributario;
	// }
	//
	// public void setIdGrupoTributario(GrupoTributario idGrupoTributario) {
	// this.idGrupoTributario = idGrupoTributario;
	// }

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

	public SubGrupoProdutoEntity getSubGrupo() {
		return subGrupo;
	}

	public void setSubGrupo(SubGrupoProdutoEntity subGrupo) {
		this.subGrupo = subGrupo;
	}

	public UnidadeProdutoEntity getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeProdutoEntity unidade) {
		this.unidade = unidade;
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

	public String getCodigoLst() {
		return codigoLst;
	}

	public void setCodigoLst(String codigoLst) {
		this.codigoLst = codigoLst;
	}

	public String getExTipi() {
		return exTipi;
	}

	public void setExTipi(String exTipi) {
		this.exTipi = exTipi;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getTipoItemSped() {
		return tipoItemSped;
	}

	public void setTipoItemSped(String tipoItemSped) {
		this.tipoItemSped = tipoItemSped;
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

	public BigDecimal getTaxaComissao() {
		return taxaComissao;
	}

	public void setTaxaComissao(BigDecimal taxaComissao) {
		this.taxaComissao = taxaComissao;
	}

	public String getIppt() {
		return ippt;
	}

	public void setIppt(String ippt) {
		this.ippt = ippt;
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

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public MarcaProdutoEntity getMarcaProduto() {
		return marcaProduto;
	}

	public void setMarcaProduto(MarcaProdutoEntity marcaProduto) {
		this.marcaProduto = marcaProduto;
	}

	public Almoxarifado getAlmoxarifado() {
		return almoxarifado;
	}

	public void setAlmoxarifado(Almoxarifado almoxarifado) {
		this.almoxarifado = almoxarifado;
	}

	public GrupoProdutoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoProdutoEntity grupo) {
		this.grupo = grupo;
	}

	public GrupoTributario getGrupoTributario() {
		return grupoTributario;
	}

	public void setGrupoTributario(GrupoTributario grupoTributario) {
		this.grupoTributario = grupoTributario;
	}

	public NcmEntity getNcm() {
		return ncm;
	}

	public void setNcm(NcmEntity ncm) {
		this.ncm = ncm;
	}

	public List<NfeDetalheEntity> getNfeDetalheList() {
		return nfeDetalheList;
	}

	public void setNfeDetalheList(List<NfeDetalheEntity> nfeDetalheList) {
		this.nfeDetalheList = nfeDetalheList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}