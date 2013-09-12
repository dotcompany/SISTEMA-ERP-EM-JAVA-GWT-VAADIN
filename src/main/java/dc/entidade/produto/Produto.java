package dc.entidade.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import dc.entidade.diversos.Almoxarifado;
import dc.entidade.framework.AbstractModel;
import dc.entidade.tributario.GrupoTributario;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, est� diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */

@Entity
@Table(name = "produto")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Produto extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
	@SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "ID_SUB_GRUPO", nullable = false) 
	private int idSubGrupo;
	 
	@Basic(optional = false) 
	@Column(name = "ID_UNIDADE_PRODUTO", nullable = false) 
	private int idUnidade;

	@Field
	@Caption("Gtin")
	@Column(name = "GTIN", length = 14)
	private String gtin;

	@Field
	@Caption("Codigo Interno")
	@Column(name = "CODIGO_INTERNO", length = 60)
	private String codigoInterno;

	@Column(name = "NCM")
	private String ncm;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	private String nome;

	// @Lob
	@Field
	@Caption("Descricao")
	@Type(type = "text")
	@Column(name = "DESCRICAO", length = 65535)
	private String descricao;

	@Field
	@Caption("Descricao Pdv")
	@Column(name = "DESCRICAO_PDV", length = 30)
	private String descricaoPdv;

	@Column(name = "VALOR_COMPRA", precision = 11, scale = 2)
	private BigDecimal valorCompra;

	@Column(name = "VALOR_VENDA", precision = 11, scale = 2)
	private BigDecimal valorVenda;

	@Column(name = "PRECO_VENDA_MINIMO", precision = 11, scale = 2)
	private BigDecimal precoVendaMinimo;

	@Column(name = "PRECO_SUGERIDO", precision = 11, scale = 2)
	private BigDecimal precoSugerido;

	@Column(name = "CUSTO_MEDIO_LIQUIDO", precision = 11, scale = 2)
	private BigDecimal custoMedioLiquido;

	@Column(name = "PRECO_LUCRO_ZERO", precision = 11, scale = 2)
	private BigDecimal precoLucroZero;

	@Column(name = "PRECO_LUCRO_MINIMO", precision = 11, scale = 2)
	private BigDecimal precoLucroMinimo;

	@Column(name = "PRECO_LUCRO_MAXIMO", precision = 11, scale = 2)
	private BigDecimal precoLucroMaximo;

	@Column(name = "MARKUP", precision = 11, scale = 2)
	private BigDecimal markup;

	@Column(name = "QUANTIDADE_ESTOQUE", precision = 11, scale = 2)
	private BigDecimal qtdEstoque;

	@Column(name = "QUANTIDADE_ESTOQUE_ANTERIOR", precision = 11, scale = 2)
	private BigDecimal qtdEstoqueAnterior;

	@Column(name = "ESTOQUE_MINIMO", precision = 11, scale = 2)
	private BigDecimal estoqueMin;

	@Column(name = "ESTOQUE_MAXIMO", precision = 11, scale = 2)
	private BigDecimal estoqueMax;

	@Column(name = "ESTOQUE_IDEAL", precision = 11, scale = 2)
	private BigDecimal estoqueIdeal;

	@Column(name = "EXCLUIDO")
	private Character excluido;

	@Column(name = "INATIVO")
	private String inativo;

	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name = "FOTO_PRODUTO", length = 65535)
	private String fotoProduto;

	@Column(name = "EX_TIPI")
	private Character exTipi;
	
	/*
	 * @Column(name = "CODIGO_iST") 
	 * private String codigoIst;
	 */

	@Column(name = "CLASSE_ABC")
	private String classeAbc;

	@Column(name = "IAT")
	private String iat;

	@Column(name = "IPPT")
	private String ippt;

	@Column(name = "TIPO_ITEM_SPED")
	private String tipoItemSped;

	@Column(name = "PESO", precision = 11, scale = 2)
	private BigDecimal peso;

	@Column(name = "PORCENTO_COMISSAO", precision = 11, scale = 2)
	private BigDecimal porcentoComissao;

	@Column(name = "PONTO_PEDIDO", precision = 11, scale = 2)
	private BigDecimal pontoPedido;

	@Column(name = "LOTE_ECONOMICO_COMPRA", precision = 11, scale = 2)
	private BigDecimal loteEconomicoCompra;

	@Column(name = "TOTALIZADOR_PARCIAL", length = 10)
	private String totalizadorParcial;

	@Column(name = "CODIGO_BALANCA")
	private Integer codigoBalanca;

	@Column(name = "DATA_ALTERACAO")
	private Date dataAlteracao;

	@Column(name = "TIPO")
	private String tipo;

	/**
	 * Mapeamento SubGrupo-Produto
	 * @author wesley Junior
	 **/
	/*@JoinColumn(name = "ID_SUB_GRUPO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private SubGrupoProduto idSubGrupo;*/

	/** 
	*Mapeamento Unidade-Produto
	*@author wesley Junior
	**/
	/*@JoinColumn(name = "ID_UNIDADE_PRODUTO",insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	private UnidadeProduto unidade;*/
	
	/*@JoinColumn(name = "ID_MARCA_PRODUTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private MarcaProduto idMarcaProduto;*/
	
	@JoinColumn(name = "ID_ALMOXARIFADO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Almoxarifado idAlmoxarifado;
	
	@JoinColumn(name = "ID_GRUPO_TRIBUTARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private GrupoTributario idGrupoTributario;

	public Produto() {
	}

	public Produto(Integer id) {
		this.id = id;
	}

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

	public BigDecimal getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(BigDecimal qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public BigDecimal getEstoqueMin() {
		return estoqueMin;
	}

	public void setEstoqueMin(BigDecimal estoqueMin) {
		this.estoqueMin = estoqueMin;
	}

	public BigDecimal getEstoqueMax() {
		return estoqueMax;
	}

	public void setEstoqueMax(BigDecimal estoqueMax) {
		this.estoqueMax = estoqueMax;
	}

	public Character getExcluido() {
		return excluido;
	}

	public void setExcluido(Character excluido) {
		this.excluido = excluido;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	// /**
	// * @return the subGrupo
	// */
	// public SubGrupoProduto getSubGrupo() {
	// return subGrupo;
	// }
	//
	// /**
	// * @param subGrupo the subGrupo to set
	// */
	// public void setSubGrupo(SubGrupoProduto subGrupo) {
	// this.subGrupo = subGrupo;
	// }

	// /**
	// * @return the unidade
	// */
	// public UnidadeProduto getUnidade() {
	// return unidade;
	// }
	//
	// /**
	// * @param unidade the unidade to set
	// */
	// public void setUnidade(UnidadeProduto unidade) {
	// this.unidade = unidade;
	// }

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
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

	public BigDecimal getQtdEstoqueAnterior() {
		return qtdEstoqueAnterior;
	}

	public void setQtdEstoqueAnterior(BigDecimal qtdEstoqueAnterior) {
		this.qtdEstoqueAnterior = qtdEstoqueAnterior;
	}

	public BigDecimal getEstoqueIdeal() {
		return estoqueIdeal;
	}

	public void setEstoqueIdeal(BigDecimal estoqueIdeal) {
		this.estoqueIdeal = estoqueIdeal;
	}

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	public String getFotoProduto() {
		return fotoProduto;
	}

	public void setFotoProduto(String fotoProduto) {
		this.fotoProduto = fotoProduto;
	}

	public Character getExTipi() {
		return exTipi;
	}

	public void setExTipi(Character exTipi) {
		this.exTipi = exTipi;
	}

	/*
	 * public String getCodigoIst() { return codigoIst; }
	 * 
	 * public void setCodigoIst(String codigoIst) { this.codigoIst = codigoIst;
	 * }
	 */

	public String getClasseAbc() {
		return classeAbc;
	}

	public void setClasseAbc(String classeAbc) {
		this.classeAbc = classeAbc;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getIppt() {
		return ippt;
	}

	public void setIppt(String ippt) {
		this.ippt = ippt;
	}

	public String getTipoItemSped() {
		return tipoItemSped;
	}

	public void setTipoItemSped(String tipoItemSped) {
		this.tipoItemSped = tipoItemSped;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPorcentoComissao() {
		return porcentoComissao;
	}

	public void setPorcentoComissao(BigDecimal porcentoComissao) {
		this.porcentoComissao = porcentoComissao;
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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/*public SubGrupoProduto getIdSubGrupo() {
		return idSubGrupo;
	}

	public void setIdSubGrupo(SubGrupoProduto idSubGrupo) {
		this.idSubGrupo = idSubGrupo;
	}*/
	
	/*public UnidadeProduto getUnidade() {
		return unidade;
	}*/

	public int getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(int idUnidade) {
		this.idUnidade = idUnidade;
	}

	public int getIdSubGrupo() {
		return idSubGrupo;
	}

	public void setIdSubGrupo(int idSubGrupo) {
		this.idSubGrupo = idSubGrupo;
	}

	/*public void setUnidade(UnidadeProduto unidade) {
		this.unidade = unidade;
	}
	/*public MarcaProduto getIdMarcaProduto() {
		return idMarcaProduto;
	}

	public void setIdMarcaProduto(MarcaProduto idMarcaProduto) {
		this.idMarcaProduto = idMarcaProduto;
	}*/

	public Almoxarifado getIdAlmoxarifado() {
		return idAlmoxarifado;
	}

	public void setIdAlmoxarifado(Almoxarifado idAlmoxarifado) {
		this.idAlmoxarifado = idAlmoxarifado;
	}
	
	public GrupoTributario getIdGrupoTributario() {
		return idGrupoTributario;
	}

	public void setIdGrupoTributario(GrupoTributario idGrupoTributario) {
		this.idGrupoTributario = idGrupoTributario;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
