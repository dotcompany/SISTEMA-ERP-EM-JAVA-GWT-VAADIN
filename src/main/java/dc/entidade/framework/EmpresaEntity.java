package dc.entidade.framework;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.folhapagamento.ausencia.FeriasColetivasEntity;
import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.entidade.folhapagamento.ausencia.TipoAfastamentoEntity;
import dc.entidade.folhapagamento.cadastro.EventoEntity;
import dc.entidade.folhapagamento.cadastro.GuiaAcumuladaEntity;
import dc.entidade.folhapagamento.cadastro.ParametroEntity;
import dc.entidade.folhapagamento.inss.InssEntity;
import dc.entidade.folhapagamento.movimento.LancamentoCabecalhoEntity;
import dc.entidade.folhapagamento.movimento.LancamentoComissaoEntity;
import dc.entidade.folhapagamento.movimento.LancamentoDetalheEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.diverso.PaisEntity;
import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.entidade.patrimonio.GrupoBemEntity;
import dc.entidade.patrimonio.SeguradoraEntity;
import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.entidade.patrimonio.TipoMovimentacaoEntity;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.sistema.ContaEmpresa;

@Entity
@Table(name = "empresa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EmpresaEntity extends AbstractModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_id_seq")
	@SequenceGenerator(name = "empresa_id_seq", sequenceName = "empresa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	private String tipo;// 1-Matriz 2-Filial 3-Depósito

	/**
	 * O campo tipo apenas identifica se é Matriz,Filial ou Depósito,já o campo
	 * idMatriz armazena o id da empresa escolhida como matriz
	 */

	@Field
	@Caption()
	@Column(name = "id_matriz")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer matriz;

	@Field
	@Caption()
	@Column(name = "ID_EMPRESA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer empresa;

	@Field
	@Caption()
	@Column(name = "ID_SINDICATO_PATRONAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer sindicatoPatronal;

	@Field
	@Caption()
	@Column(name = "ID_FPAS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer fpas;

	@Field
	@Caption()
	@Column(name = "ID_CONTADOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer contador;

	// @OneToOne
	// @JoinColumn(name="id_contador")
	// private Contador contador;

	@Field
	@Caption("Razao Social")
	@Column(name = "RAZAO_SOCIAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String razaoSocial;

	@Field
	@Caption("Nome Fantasia")
	@Column(name = "NOME_FANTASIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeFantasia;

	@Field
	@Caption()
	@Column(name = "CNPJ", length = 14, unique = true)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_ESTADUAL", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadual;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_ESTADUAL_ST", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadualSt;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_MUNICIPAL", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoMunicipal;

	@Field
	@Caption()
	@Column(name = "INSCRICAO_JUNTA_COMERCIAL", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoJuntaComercial;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "DATA_INSC_JUNTA_COMERCIAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataInscJuntaComercial;

	// @Column(name = "tipo")
	// @Enumerated(value = EnumType.STRING)
	// private EmpresaType empresa;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "DATA_INICIO_ATIVIDADES")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataInicioAtividades;

	@Field
	@Caption()
	@Column(name = "SUFRAMA", length = 9)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String suframa;

	@Field
	@Caption()
	@Column(name = "EMAIL", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;

	@Lob
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Field
	@Caption()
	@Column(name = "IMAGEM_LOGOTIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String imagemLogotipo;

	@Field
	@Caption()
	@Column(name = "CRT")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer crt;

	@Field
	@Caption()
	@Column(name = "TIPO_REGIME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoRegime;

	@Field
	@Caption()
	@Column(name = "ALIQUOTA_PIS", precision = 18, scale = 6)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaPis;

	@Field
	@Caption()
	@Column(name = "CONTATO", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contato;

	@Field
	@Caption()
	@Column(name = "ALIQUOTA_COFINS", precision = 18, scale = 6)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaCofins;

	@Field
	@Caption()
	@Column(name = "ALIQUOTA_SAT", precision = 18, scale = 6)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaSat;

	@Field
	@Caption()
	@Column(name = "CODIGO_IBGE_CIDADE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbgeCidade;

	@Field
	@Caption()
	@Column(name = "CODIGO_IBGE_UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbgeUf;

	@Field
	@Caption()
	@Column(name = "CODIGO_TERCEIROS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoTerceiros;

	@Field
	@Caption()
	@Column(name = "CODIGO_GPS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoGps;

	@Field
	@Caption()
	@Column(name = "CEI", length = 12)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cei;

	@OneToOne(mappedBy = "empresa", fetch = FetchType.LAZY)
	private ContaEmpresa conta;

	@Column(name = "codigo_cnae_principal")
	private String cnaePrincipal;

	/**
	 * @autor Gutemberg A. Da Silva
	 * @module PATRIMONIO
	 */

	@OneToMany(mappedBy = "empresa")
	private List<TipoAquisicaoEntity> tipoAquisicaoList;

	@OneToMany(mappedBy = "empresa")
	private List<SeguradoraEntity> seguradoraList;

	@OneToMany(mappedBy = "empresa")
	private List<TipoMovimentacaoEntity> tipoMovimentacaoList;

	@OneToMany(mappedBy = "empresa")
	private List<EstadoConservacaoEntity> estadoConservacaoList;

	@OneToMany(mappedBy = "empresa")
	private List<GrupoBemEntity> grupoBemList;

	/**
	 * @autor Wesley Júnior
	 * @module ADMINISTRATIVO
	 */

	@OneToMany(mappedBy = "empresa", orphanRemoval = true)
	private List<PessoaEnderecoEntity> enderecos = new ArrayList<>();

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<PaisEntity> paisList;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "ID_MATRIZ" , referencedColumnName = "ID") private
	 * List<Matriz> matriz;
	 * 
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "ID_CONTADOR",insertable = true, updatable = true)
	 * 
	 * @Fetch(FetchMode.JOIN) private Contador contador;
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "ID_SINDICATO_PATRONAL",insertable = true, updatable =
	 * true)
	 * 
	 * @Fetch(FetchMode.JOIN) private Sindicato sindicato;
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "ID_FPAS",insertable = true, updatable = true)
	 * 
	 * @Fetch(FetchMode.JOIN) private Fpas fpas;
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * @module FOLHAPAGAMENTO
	 */

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<TipoAfastamentoEntity> tipoAfastamentoList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<FeriasColetivasEntity> feriasColetivasList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<EventoEntity> eventoList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<ParametroEntity> parametroList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<InssEntity> inssList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<LancamentoCabecalhoEntity> lancamentoCabecalhoList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<LancamentoComissaoEntity> lancamentoComissaoList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<LancamentoDetalheEntity> lancamentoDetalheList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<FeriasPeriodoAquisitivoEntity> feriasPeriodoAquisitivoList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<GuiaAcumuladaEntity> guiaAcumuladaList;

	/**
	 * @autor Gutemberg A. Da Silva
	 * @module FINANCEIRO
	 */

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<ContaCaixa> contaCaixaList;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpresaSeguimento> empresaSeguimentos;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "empresas")
	private Set<Relatorio> relatorios;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public EmpresaEntity() {

	}

	public EmpresaEntity(Integer id) {
		this.id = id;
	}

	public EmpresaEntity(Integer id, int empresa) {
		this.id = id;
		this.empresa = empresa;
	}

	public EmpresaEntity(Integer id, String nomeFantasia) {
		this.id = id;
		this.nomeFantasia = nomeFantasia;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getMatriz() {
		return matriz;
	}

	public void setMatriz(Integer matriz) {
		this.matriz = matriz;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getSindicatoPatronal() {
		return sindicatoPatronal;
	}

	public void setSindicatoPatronal(Integer sindicatoPatronal) {
		this.sindicatoPatronal = sindicatoPatronal;
	}

	public Integer getFpas() {
		return fpas;
	}

	public void setFpas(Integer fpas) {
		this.fpas = fpas;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoEstadualSt() {
		return inscricaoEstadualSt;
	}

	public void setInscricaoEstadualSt(String inscricaoEstadualSt) {
		this.inscricaoEstadualSt = inscricaoEstadualSt;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoJuntaComercial() {
		return inscricaoJuntaComercial;
	}

	public void setInscricaoJuntaComercial(String inscricaoJuntaComercial) {
		this.inscricaoJuntaComercial = inscricaoJuntaComercial;
	}

	public Date getDataInscJuntaComercial() {
		return dataInscJuntaComercial;
	}

	public void setDataInscJuntaComercial(Date dataInscJuntaComercial) {
		this.dataInscJuntaComercial = dataInscJuntaComercial;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicioAtividades() {
		return dataInicioAtividades;
	}

	public void setDataInicioAtividades(Date dataInicioAtividades) {
		this.dataInicioAtividades = dataInicioAtividades;
	}

	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagemLogotipo() {
		return imagemLogotipo;
	}

	public void setImagemLogotipo(String imagemLogotipo) {
		this.imagemLogotipo = imagemLogotipo;
	}

	public Integer getCrt() {
		return crt;
	}

	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	public String getTipoRegime() {
		return tipoRegime;
	}

	public void setTipoRegime(String tipoRegime) {
		this.tipoRegime = tipoRegime;
	}

	public BigDecimal getAliquotaPis() {
		return aliquotaPis;
	}

	public void setAliquotaPis(BigDecimal aliquotaPis) {
		this.aliquotaPis = aliquotaPis;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public BigDecimal getAliquotaCofins() {
		return aliquotaCofins;
	}

	public void setAliquotaCofins(BigDecimal aliquotaCofins) {
		this.aliquotaCofins = aliquotaCofins;
	}

	public BigDecimal getAliquotaSat() {
		return aliquotaSat;
	}

	public void setAliquotaSat(BigDecimal aliquotaSat) {
		this.aliquotaSat = aliquotaSat;
	}

	public Integer getCodigoIbgeCidade() {
		return codigoIbgeCidade;
	}

	public void setCodigoIbgeCidade(Integer codigoIbgeCidade) {
		this.codigoIbgeCidade = codigoIbgeCidade;
	}

	public Integer getCodigoIbgeUf() {
		return codigoIbgeUf;
	}

	public void setCodigoIbgeUf(Integer codigoIbgeUf) {
		this.codigoIbgeUf = codigoIbgeUf;
	}

	public Integer getCodigoTerceiros() {
		return codigoTerceiros;
	}

	public void setCodigoTerceiros(Integer codigoTerceiros) {
		this.codigoTerceiros = codigoTerceiros;
	}

	public Integer getCodigoGps() {
		return codigoGps;
	}

	public void setCodigoGps(Integer codigoGps) {
		this.codigoGps = codigoGps;
	}

	public String getCei() {
		return cei;
	}

	public void setCei(String cei) {
		this.cei = cei;
	}

	public ContaEmpresa getConta() {
		return conta;
	}

	public void setConta(ContaEmpresa conta) {
		this.conta = conta;
	}

	public String getCnaePrincipal() {
		return cnaePrincipal;
	}

	public void setCnaePrincipal(String cnaePrincipal) {
		this.cnaePrincipal = cnaePrincipal;
	}

	public List<TipoAquisicaoEntity> getTipoAquisicaoList() {
		return tipoAquisicaoList;
	}

	public void setTipoAquisicaoList(List<TipoAquisicaoEntity> tipoAquisicaoList) {
		this.tipoAquisicaoList = tipoAquisicaoList;
	}

	public List<SeguradoraEntity> getSeguradoraList() {
		return seguradoraList;
	}

	public void setSeguradoraList(List<SeguradoraEntity> seguradoraList) {
		this.seguradoraList = seguradoraList;
	}

	public List<TipoMovimentacaoEntity> getTipoMovimentacaoList() {
		return tipoMovimentacaoList;
	}

	public void setTipoMovimentacaoList(
			List<TipoMovimentacaoEntity> tipoMovimentacaoList) {
		this.tipoMovimentacaoList = tipoMovimentacaoList;
	}

	public List<EstadoConservacaoEntity> getEstadoConservacaoList() {
		return estadoConservacaoList;
	}

	public void setEstadoConservacaoList(
			List<EstadoConservacaoEntity> estadoConservacaoList) {
		this.estadoConservacaoList = estadoConservacaoList;
	}

	public List<GrupoBemEntity> getGrupoBemList() {
		return grupoBemList;
	}

	public void setGrupoBemList(List<GrupoBemEntity> grupoBemList) {
		this.grupoBemList = grupoBemList;
	}

	public List<PessoaEnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<PessoaEnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

	public List<PaisEntity> getPaisList() {
		return paisList;
	}

	public void setPaisList(List<PaisEntity> paisList) {
		this.paisList = paisList;
	}

	public List<TipoAfastamentoEntity> getTipoAfastamentoList() {
		return tipoAfastamentoList;
	}

	public void setTipoAfastamentoList(
			List<TipoAfastamentoEntity> tipoAfastamentoList) {
		this.tipoAfastamentoList = tipoAfastamentoList;
	}

	public List<FeriasColetivasEntity> getFeriasColetivasList() {
		return feriasColetivasList;
	}

	public void setFeriasColetivasList(
			List<FeriasColetivasEntity> feriasColetivasList) {
		this.feriasColetivasList = feriasColetivasList;
	}

	public List<EventoEntity> getEventoList() {
		return eventoList;
	}

	public void setEventoList(List<EventoEntity> eventoList) {
		this.eventoList = eventoList;
	}

	public List<ParametroEntity> getParametroList() {
		return parametroList;
	}

	public void setParametroList(List<ParametroEntity> parametroList) {
		this.parametroList = parametroList;
	}

	public List<InssEntity> getInssList() {
		return inssList;
	}

	public void setInssList(List<InssEntity> inssList) {
		this.inssList = inssList;
	}

	public List<LancamentoCabecalhoEntity> getLancamentoCabecalhoList() {
		return lancamentoCabecalhoList;
	}

	public void setLancamentoCabecalhoList(
			List<LancamentoCabecalhoEntity> lancamentoCabecalhoList) {
		this.lancamentoCabecalhoList = lancamentoCabecalhoList;
	}

	public List<LancamentoComissaoEntity> getLancamentoComissaoList() {
		return lancamentoComissaoList;
	}

	public void setLancamentoComissaoList(
			List<LancamentoComissaoEntity> lancamentoComissaoList) {
		this.lancamentoComissaoList = lancamentoComissaoList;
	}

	public List<LancamentoDetalheEntity> getLancamentoDetalheList() {
		return lancamentoDetalheList;
	}

	public void setLancamentoDetalheList(
			List<LancamentoDetalheEntity> lancamentoDetalheList) {
		this.lancamentoDetalheList = lancamentoDetalheList;
	}

	public List<FeriasPeriodoAquisitivoEntity> getFeriasPeriodoAquisitivoList() {
		return feriasPeriodoAquisitivoList;
	}

	public void setFeriasPeriodoAquisitivoList(
			List<FeriasPeriodoAquisitivoEntity> feriasPeriodoAquisitivoList) {
		this.feriasPeriodoAquisitivoList = feriasPeriodoAquisitivoList;
	}

	public List<GuiaAcumuladaEntity> getGuiaAcumuladaList() {
		return guiaAcumuladaList;
	}

	public void setGuiaAcumuladaList(List<GuiaAcumuladaEntity> guiaAcumuladaList) {
		this.guiaAcumuladaList = guiaAcumuladaList;
	}

	public List<ContaCaixa> getContaCaixaList() {
		return contaCaixaList;
	}

	public void setContaCaixaList(List<ContaCaixa> contaCaixaList) {
		this.contaCaixaList = contaCaixaList;
	}

	public List<EmpresaSeguimento> getEmpresaSeguimentos() {
		return empresaSeguimentos;
	}

	public void setEmpresaSeguimentos(List<EmpresaSeguimento> empresaSeguimentos) {
		this.empresaSeguimentos = empresaSeguimentos;
	}

	public Set<Relatorio> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(Set<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public PessoaEnderecoEntity addEndereco(PessoaEnderecoEntity enderecos) {
		getEnderecos().add(enderecos);
		enderecos.setEmpresa(this);

		return enderecos;
	}

	public PessoaEnderecoEntity removeEndereco(PessoaEnderecoEntity enderecos) {
		getEnderecos().remove(enderecos);
		enderecos.setEmpresa(null);

		return enderecos;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}