package dc.entidade.patrimonio;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.diversos.Setor;
import dc.entidade.framework.AbstractModel;
import dc.entidade.geral.Fornecedor;
import dc.entidade.pessoal.Colaborador;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "patrim_bem")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class BemEntity extends AbstractModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patrim_bem_id_seq")
	@SequenceGenerator(name = "patrim_bem_id_seq", sequenceName = "patrim_bem_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "numero_nb")
	@Field
	@Caption("Número NB")
	@Size(max = 20, message = "Tamanho inválido.")
	private String numeroNb = "";

	@Column(name = "nome")
	@Field
	@Caption("Nome")
	@Size(max = 100, message = "Tamanho inválido.")
	private String nome = "";

	@Column(name = "descricao")
	@Field
	@Caption("Descrição")
	private String descricao = "";

	@Column(name = "numero_serie")
	@Field
	@Caption("Número de serie")
	@Size(max = 50, message = "Tamanho inválido.")
	private String numeroSerie = "";

	@Column(name = "data_aquisicao")
	@Field
	@Caption("Data da aquisição")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataAquisicao;

	@Column(name = "data_aceite")
	@Field
	@Caption("Data do aceite")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataAceite;

	@Column(name = "data_cadastro")
	@Field
	@Caption("Data do cadastro")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "data_contabilizado")
	@Field
	@Caption("Data contabilizado")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataContabilizado;

	@Column(name = "data_vistoria")
	@Field
	@Caption("Data da vistoria")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataVistoria;

	@Column(name = "data_marcacao")
	@Field
	@Caption("Data da marcação")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataMarcacao;

	@Column(name = "data_baixa")
	@Field
	@Caption("Data da baixa")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataBaixa;

	@Column(name = "vencimento_garantia")
	@Field
	@Caption("Vencimento da garantia")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date vencimentoGarantia;

	@Column(name = "numero_nota_fiscal")
	@Field
	@Caption("Número da nota fiscal")
	@Size(max = 50, message = "Tamanho inválido.")
	private String numeroNotaFiscal = "";

	@Column(name = "chave_nfe")
	@Field
	@Caption("Chave NFE")
	@Size(max = 44, message = "Tamanho inválido.")
	private String chaveNfe = "";

	@Column(name = "valor_original")
	@Field
	@Caption("Valor original")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double valorOriginal = new Double(0.0);

	@Column(name = "valor_compra")
	@Field
	@Caption("Valor da compra")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double valorCompra = new Double(0.0);

	@Column(name = "valor_atualizado")
	@Field
	@Caption("Valor atualizado")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double valorAtualizado = new Double(0.0);

	@Column(name = "valor_baixa")
	@Field
	@Caption("Valor da baixa")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double valorBaixa = new Double(0.0);

	@Column(name = "deprecia")
	@Field
	@Caption("Deprecia")
	@Size(max = 1, message = "Tamanho inválido.")
	private String deprecia = "";

	@Column(name = "metodo_depreciacao")
	@Field
	@Caption("Método de depreciação")
	@Size(max = 1, message = "Tamanho inválido.")
	private String metodoDepreciacao = "";

	@Column(name = "inicio_depreciacao")
	@Field
	@Caption("Início da depreciação")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date inicioDepreciacao;

	@Column(name = "ultima_depreciacao")
	@Field
	@Caption("Última depreciação")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date ultimaDepreciacao;

	@Column(name = "tipo_depreciacao")
	@Field
	@Caption("Tipo de depreciação")
	@Size(max = 1, message = "Tamanho inválido.")
	private String tipoDepreciacao = "";

	@Column(name = "taxa_anual_depreciacao")
	@Field
	@Caption("Taxa anual de depreciação")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double taxaAnualDepreciacao = new Double(0.0);

	@Column(name = "taxa_mensal_depreciacao")
	@Field
	@Caption("Taxa mensal de depreciação")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double taxaMensalDepreciacao = new Double(0.0);

	@Column(name = "taxa_depreciacao_acelerada")
	@Field
	@Caption("Taxa de depreciação acelerada")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double taxaDepreciacaoAcelerada = new Double(0.0);

	@Column(name = "taxa_depreciacao_incentivada")
	@Field
	@Caption("Taxa de depreciação incentivada")
	@Digits(integer = 18, fraction = 6, message = "Tamanho inválido.")
	private Double taxaDepreciacaoIncentivada = new Double(0.0);

	@Column(name = "funcao")
	@Field
	@Caption("Função")
	private String funcao = "";

	@ManyToOne
	@JoinColumn(name = "id_patrim_tipo_aquisicao_bem", nullable = false)
	@Caption("Tipo de aquisição")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private TipoAquisicaoEntity tipoAquisicao;

	@ManyToOne
	@JoinColumn(name = "id_patrim_estado_conservacao", nullable = false)
	@Caption("Estado de conservação")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private EstadoConservacaoEntity estadoConservacao;

	@ManyToOne
	@JoinColumn(name = "id_patrim_grupo_bem", nullable = false)
	@Caption("Grupo do bem")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private GrupoBemEntity grupoBem;

	@ManyToOne
	@JoinColumn(name = "id_setor", nullable = false)
	@Caption("Setor")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Setor setor;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor", nullable = false)
	@Caption("Fornecedor")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Colaborador colaborador;

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY)
	private List<ApoliceSeguroEntity> apoliceSeguroList;

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY)
	private List<MovimentacaoBemEntity> movimentacaoBemList;

	@OneToMany(mappedBy = "bem", fetch = FetchType.LAZY)
	private List<DocumentoBemEntity> documentoBemList;

	/**
	 * CONSTRUTOR
	 */

	public BemEntity() {

	}

	public BemEntity(Integer id) {
		this.id = id;
	}

	public BemEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	/**
	 * GETS E SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroNb() {
		return numeroNb;
	}

	public void setNumeroNb(String numeroNb) {
		this.numeroNb = (numeroNb == null ? "" : numeroNb.toUpperCase());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = (numeroSerie == null ? "" : numeroSerie
				.toUpperCase());
	}

	public Date getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataContabilizado() {
		return dataContabilizado;
	}

	public void setDataContabilizado(Date dataContabilizado) {
		this.dataContabilizado = dataContabilizado;
	}

	public Date getDataVistoria() {
		return dataVistoria;
	}

	public void setDataVistoria(Date dataVistoria) {
		this.dataVistoria = dataVistoria;
	}

	public Date getDataMarcacao() {
		return dataMarcacao;
	}

	public void setDataMarcacao(Date dataMarcacao) {
		this.dataMarcacao = dataMarcacao;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public Date getVencimentoGarantia() {
		return vencimentoGarantia;
	}

	public void setVencimentoGarantia(Date vencimentoGarantia) {
		this.vencimentoGarantia = vencimentoGarantia;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = (numeroNotaFiscal == null ? ""
				: numeroNotaFiscal.toUpperCase());
	}

	public String getChaveNfe() {
		return chaveNfe;
	}

	public void setChaveNfe(String chaveNfe) {
		this.chaveNfe = (chaveNfe == null ? "" : chaveNfe.toUpperCase());
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorAtualizado() {
		return valorAtualizado;
	}

	public void setValorAtualizado(Double valorAtualizado) {
		this.valorAtualizado = valorAtualizado;
	}

	public Double getValorBaixa() {
		return valorBaixa;
	}

	public void setValorBaixa(Double valorBaixa) {
		this.valorBaixa = valorBaixa;
	}

	public String getDeprecia() {
		return deprecia;
	}

	public void setDeprecia(String deprecia) {
		this.deprecia = (deprecia == null ? "" : deprecia.toUpperCase());
	}

	public String getMetodoDepreciacao() {
		return metodoDepreciacao;
	}

	public void setMetodoDepreciacao(String metodoDepreciacao) {
		this.metodoDepreciacao = (metodoDepreciacao == null ? ""
				: metodoDepreciacao.toUpperCase());
	}

	public Date getInicioDepreciacao() {
		return inicioDepreciacao;
	}

	public void setInicioDepreciacao(Date inicioDepreciacao) {
		this.inicioDepreciacao = inicioDepreciacao;
	}

	public Date getUltimaDepreciacao() {
		return ultimaDepreciacao;
	}

	public void setUltimaDepreciacao(Date ultimaDepreciacao) {
		this.ultimaDepreciacao = ultimaDepreciacao;
	}

	public String getTipoDepreciacao() {
		return tipoDepreciacao;
	}

	public void setTipoDepreciacao(String tipoDepreciacao) {
		this.tipoDepreciacao = (tipoDepreciacao == null ? "" : tipoDepreciacao
				.toUpperCase());
	}

	public Double getTaxaAnualDepreciacao() {
		return taxaAnualDepreciacao;
	}

	public void setTaxaAnualDepreciacao(Double taxaAnualDepreciacao) {
		this.taxaAnualDepreciacao = taxaAnualDepreciacao;
	}

	public Double getTaxaMensalDepreciacao() {
		return taxaMensalDepreciacao;
	}

	public void setTaxaMensalDepreciacao(Double taxaMensalDepreciacao) {
		this.taxaMensalDepreciacao = taxaMensalDepreciacao;
	}

	public Double getTaxaDepreciacaoAcelerada() {
		return taxaDepreciacaoAcelerada;
	}

	public void setTaxaDepreciacaoAcelerada(Double taxaDepreciacaoAcelerada) {
		this.taxaDepreciacaoAcelerada = taxaDepreciacaoAcelerada;
	}

	public Double getTaxaDepreciacaoIncentivada() {
		return taxaDepreciacaoIncentivada;
	}

	public void setTaxaDepreciacaoIncentivada(Double taxaDepreciacaoIncentivada) {
		this.taxaDepreciacaoIncentivada = taxaDepreciacaoIncentivada;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = (funcao == null ? "" : funcao.toUpperCase());
	}

	public TipoAquisicaoEntity getTipoAquisicao() {
		return tipoAquisicao;
	}

	public void setTipoAquisicao(TipoAquisicaoEntity tipoAquisicao) {
		this.tipoAquisicao = tipoAquisicao;
	}

	public EstadoConservacaoEntity getEstadoConservacao() {
		return estadoConservacao;
	}

	public void setEstadoConservacao(EstadoConservacaoEntity estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}

	public GrupoBemEntity getGrupoBem() {
		return grupoBem;
	}

	public void setGrupoBem(GrupoBemEntity grupoBem) {
		this.grupoBem = grupoBem;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<ApoliceSeguroEntity> getApoliceSeguroList() {
		return apoliceSeguroList;
	}

	public void setApoliceSeguroList(List<ApoliceSeguroEntity> apoliceSeguroList) {
		this.apoliceSeguroList = apoliceSeguroList;
	}

	public List<MovimentacaoBemEntity> getMovimentacaoBemList() {
		return movimentacaoBemList;
	}

	public void setMovimentacaoBemList(
			List<MovimentacaoBemEntity> movimentacaoBemList) {
		this.movimentacaoBemList = movimentacaoBemList;
	}

	public List<DocumentoBemEntity> getDocumentoBemList() {
		return documentoBemList;
	}

	public void setDocumentoBemList(List<DocumentoBemEntity> documentoBemList) {
		this.documentoBemList = documentoBemList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
