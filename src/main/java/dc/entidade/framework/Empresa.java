package dc.entidade.framework;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import dc.anotacoes.Caption;
import dc.entidade.diversos.Pais;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.type.EmpresaType;
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
import dc.entidade.geral.Contato;
import dc.entidade.geral.Endereco;
import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.entidade.patrimonio.GrupoBemEntity;
import dc.entidade.patrimonio.SeguradoraEntity;
import dc.entidade.patrimonio.TipoAquisicaoEntity;
import dc.entidade.patrimonio.TipoMovimentacaoEntity;
import dc.entidade.pessoal.Contador;
import dc.entidade.sistema.ContaEmpresa;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */

@Entity
@Table(name = "empresa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Empresa extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "ID_EMPRESA")
	private Integer idEmpresa;

	@Column(name = "ID_SINDICATO_PATRONAL")
	private Integer idSindicatoPatronal;

	@Column(name = "ID_FPAS")
	private Integer idFpas;

	@Column(name = "ID_CONTADOR")
	private Integer idContador;
	
//	@OneToOne
//	@JoinColumn(name="id_contador")
//	private Contador contador;

	@Field
	@Caption("Razao Social")
	@Column(name = "RAZAO_SOCIAL")
	private String razaoSocial;

	@Field
	@Caption("Nome Fantasia")
	@Column(name = "NOME_FANTASIA")
	private String nomeFantasia;

	@Column(name = "CNPJ", length = 14, unique = true)
	private String cnpj;

	@Column(name = "INSCRICAO_ESTADUAL", length = 30)
	private String inscricaoEstadual;

	@Column(name = "INSCRICAO_ESTADUAL_ST", length = 30)
	private String inscricaoEstadualSt;

	@Column(name = "INSCRICAO_MUNICIPAL", length = 30)
	private String inscricaoMunicipal;

	@Column(name = "INSCRICAO_JUNTA_COMERCIAL", length = 30)
	private String inscricaoJuntaComercial;

	@Column(name = "DATA_INSC_JUNTA_COMERCIAL")
	@Temporal(TemporalType.DATE)
	private Date dataInscJuntaComercial;

	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private EmpresaType empresa;

	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Column(name = "DATA_INICIO_ATIVIDADES")
	@Temporal(TemporalType.DATE)
	private Date dataInicioAtividades;

	@Column(name = "SUFRAMA", length = 9)
	private String suframa;

	@Column(name = "EMAIL", length = 250)
	private String email;

	@Lob
	@Type(type = "text")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "IMAGEM_LOGOTIPO")
	private String imagemLogotipo;

	@Column(name = "CRT")
	private Integer crt;

	@Column(name = "TIPO_REGIME")
	private String tipoRegime;

	@Column(name = "ALIQUOTA_PIS", precision = 18, scale = 6)
	private BigDecimal aliquotaPis;

	@Column(name = "CONTATO", length = 250)
	private String contato;

	@Column(name = "ALIQUOTA_COFINS", precision = 18, scale = 6)
	private BigDecimal aliquotaCofins;

	@Column(name = "ALIQUOTA_SAT", precision = 18, scale = 6)
	private BigDecimal aliquotaSat;

	@Column(name = "CODIGO_IBGE_CIDADE")
	private Integer codigoIbgeCidade;

	@Column(name = "CODIGO_IBGE_UF")
	private Integer codigoIbgeUf;

	@Column(name = "CODIGO_TERCEIROS")
	private Integer codigoTerceiros;

	@Column(name = "CODIGO_GPS")
	private Integer codigoGps;

	@Column(name = "CEI", length = 12)
	private String cei;

	@OneToOne(mappedBy = "empresa", fetch = FetchType.LAZY)
	private ContaEmpresa conta;

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
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
	 * 
	 * @module ADMINISTRATIVO
	 */

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Contato> contatos = new ArrayList<>();

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Endereco> enderecos = new ArrayList<>();

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<Pais> paisList;

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
	 * 
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
	 * 
	 * @module FINANCEIRO
	 */

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<ContaCaixa> contaCaixaList;

	/**
	 * CONSTRUTOR
	 */

	public Empresa() {

	}

	public Empresa(Integer id) {
		this.id = id;
	}

	public Empresa(Integer id, int idEmpresa) {
		this.id = id;
		this.idEmpresa = idEmpresa;
	}

	public Empresa(Integer id, String nomeFantasia) {
		this.id = id;
		this.nomeFantasia = nomeFantasia;
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

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getInscricaoEstadualSt() {
		return inscricaoEstadualSt;
	}

	public void setInscricaoEstadualSt(String inscricaoEstadualSt) {
		this.inscricaoEstadualSt = inscricaoEstadualSt;
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

	public EmpresaType getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaType empresa) {
		this.empresa = empresa;
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

	public Integer getIdSindicatoPatronal() {
		return idSindicatoPatronal;
	}

	public void setIdSindicatoPatronal(Integer idSindicatoPatronal) {
		this.idSindicatoPatronal = idSindicatoPatronal;
	}

	public Integer getIdFpas() {
		return idFpas;
	}

	public void setIdFpas(Integer idFpas) {
		this.idFpas = idFpas;
	}

	

	public Integer getIdContador() {
		return idContador;
	}

	public void setIdContador(Integer idContador) {
		this.idContador = idContador;
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

	public List<ContaCaixa> getContaCaixaList() {
		return contaCaixaList;
	}

	public void setContaCaixaList(List<ContaCaixa> contaCaixaList) {
		this.contaCaixaList = contaCaixaList;
	}

	public Contato addContato(Contato contato) {
		getContatos().add(contato);
		contato.setEmpresa(this);

		return contato;
	}

	public Contato removeContato(Contato contato) {
		getContatos().remove(contato);
		contato.setEmpresa(null);

		return contato;
	}
	
	

	

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Endereco addEndereco(Endereco enderecos) {
		getEnderecos().add(enderecos);
		enderecos.setEmpresa(this);

		return enderecos;
	}

	public Endereco removeEndereco(Endereco enderecos) {
		getEnderecos().remove(enderecos);
		enderecos.setEmpresa(null);

		return enderecos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEndereco(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pais> getPaisList() {
		return paisList;
	}

	public void setPaisList(List<Pais> paisList) {
		this.paisList = paisList;
	}

	// /**
	// * HASH E EQUALS
	// */
	//
	// @Override
	// public int hashCode() {
	// return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	// }
	//
	// @Override
	// public boolean equals(Object object) {
	// if (object instanceof Empresa == false)
	// return false;
	//
	// if (this == object)
	// return true;
	//
	// final Empresa other = (Empresa) object;
	//
	// return EqualsBuilder.reflectionEquals(this, other);
	// }

	/*
	 * public List<Matriz> getMatriz() { return matriz; }
	 * 
	 * public void setMatriz(List<Matriz> matriz) { this.matriz = matriz; }
	 */

	@Override
	public String toString() {
		return nomeFantasia;
	}

	/*
	 * public Contador getContador() { return contador; }
	 * 
	 * public void setContador(Contador contador) { this.contador = contador; }
	 * 
	 * public Sindicato getSindicato() { return sindicato; }
	 * 
	 * public void setSindicato(Sindicato sindicato) { this.sindicato =
	 * sindicato; }
	 * 
	 * /*public Fpas getFpas() { return fpas; }
	 * 
	 * public void setFpas(Fpas fpas) { this.fpas = fpas; }
	 */

}