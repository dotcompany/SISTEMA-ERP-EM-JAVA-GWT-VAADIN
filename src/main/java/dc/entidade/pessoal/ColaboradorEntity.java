package dc.entidade.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.contabilidade.PlanoConta;
import dc.entidade.diversos.Setor;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.Sindicato;
import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.entidade.folhapagamento.ausencia.FeriasPeriodoAquisitivoEntity;
import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.entidade.folhapagamento.movimento.HistoricoSalarialEntity;
import dc.entidade.folhapagamento.movimento.LancamentoCabecalhoEntity;
import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.entidade.folhapagamento.movimento.RescisaoEntity;
import dc.entidade.folhapagamento.movimento.ValeTransporteEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.NivelFormacao;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.patrimonio.BemEntity;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Entity
@Table(name = "colaborador")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class ColaboradorEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer MASTER_ID = 1;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colaborador_id_seq")
	@SequenceGenerator(name = "colaborador_id_seq", sequenceName = "colaborador_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Matricula")
	@Column(name = "MATRICULA", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String matricula;

	@Lob
	@Type(type = "text")
	@Column(name = "FOTO_34")
	@Analyzer(definition = "dc_combo_analyzer")
	private String foto34;

	@Field
	@Caption("Data Cadastro")
	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Field
	@Caption("Data Admissao")
	@Column(name = "DATA_ADMISSAO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataAdmissao;

	@Field
	@Caption("Vencimento Ferias")
	@Column(name = "VENCIMENTO_FERIAS")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date vencimentoFerias;

	@Field
	@Caption("Data Transferencia")
	@Column(name = "DATA_TRANSFERENCIA")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataTransferencia;

	@Column(name = "FGTS_OPTANTE")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character fgts_optante;

	@Column(name = "FGTS_DATA_OPCAO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date fgtsDataOpcao;

	@Column(name = "FGTS_CONTA")
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer fgtsConta;

	@Column(name = "PAGAMENTO_FORMA")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pagamentoForma;

	@Column(name = "PAGAMENTO_Banco", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoBanco;

	@Column(name = "PAGAMENTO_AGENCIA", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoAgencia;

	@Column(name = "PAGAMENTO_AGENCIA_DIGITO")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pagamentoAgenciaDigito;

	@Column(name = "PAGAMENTO_CONTA", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoConta;

	@Column(name = "PAGAMENTO_CONTA_DIGITO")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pagamentoContaDigito;

	@Column(name = "EXAME_MEDICO_ULTIMO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date exameMedicoUltimo;

	@Column(name = "EXAME_MEDICO_VENCIMENTO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date exameMedicoVencimento;

	@Column(name = "PIS_DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date pisDataCadastro;

	@Column(name = "PIS_NUMERO", length = 12)
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisNumero;

	@Column(name = "PIS_BANCO", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisBanco;

	@Column(name = "PIS_AGENCIA", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisAgencia;

	@Column(name = "PIS_AGENCIA_DIGITO")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pisAgenciaDigito;

	@Column(name = "CTPS_NUMERO", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctps_Numero;

	@Column(name = "CTPS_SERIE", length = 10)
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsSerie;

	@Column(name = "CTPS_DATA_EXPEDICAO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date ctpsDataExpedicao;

	@Column(name = "CTPS_UF")
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsUf;

	@Column(name = "DESCONTO_PLANO_SAUDE")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character descontoPlanoSaude;

	@Column(name = "SAI_NA_RAIS")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character saiNaRais;

	@Column(name = "CATEGORIA_SEFIP")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character categoriaSefip;

	@Column(name = "OCORRENCIA_SEFIP")
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer ocorrenciaSefip;

	@Column(name = "CODIGO_ADMISSAO_CAGED")
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoAdmissaoCaged;

	@Column(name = "CODIGO_DEMISSAO_CAGED")
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoDemissaoCaged;

	@Column(name = "CODIGO_DEMISSAO_SEFIP")
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoDemissaoSefip;

	@Column(name = "DATA_DEMISSAO")
	@Temporal(TemporalType.DATE)
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataDemissao;

	@Field
	@Caption("Codigo Turma Ponto")
	@Column(name = "CODIGO_TURMA_PONTO")
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoTurmaPonto;

	@Lob
	@Type(type = "text")
	@Column(name = "OBSERVACAO")
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@JoinColumn(name = "ID_SETOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Setor idSetor;

	@JoinColumn(name = "id_contabil_conta", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY)
	// Carrega o relacionamento no uso.
	private ContabilConta idContaContabil;

	@JoinColumn(name = "ID_CARGO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private CargoEntity idCargo;

	@JoinColumn(name = "ID_TIPO_COLABORADOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private TipoColaboradorEntity idTipoColaborador;

	@JoinColumn(name = "ID_SITUACAO_COLABORADOR", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private SituacaoColaboradorEntity idSituacaoColaborador;

	@JoinColumn(name = "ID_NIVEL_FORMACAO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private NivelFormacao idNivelFormacao;

	@JoinColumn(name = "ID_SINDICATO", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Sindicato idSindicato;

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Analyzer(definition = "dc_combo_analyzer")
	@IndexedEmbedded
	@ComboValue
	private PessoaEntity pessoa;

	@Column(name = "salario_fixo")
	private BigDecimal salarioFixo;

	@Column(name = "tipo_comissao_servico")
	private String tipoComissaoServico;

	@Column(name = "valor_comissao_servico")
	private BigDecimal valorComissaoServico;

	@Column(name = "tipo_comissao_produto")
	private String tipoComissaoProduto;

	@Column(name = "valor_comissao_produto")
	private BigDecimal valorComissaoProduto;

	@Column(name = "priorizar_comissao")
	private Boolean priorizarComissao;

	@Column(name = "comissao_over")
	private Boolean comissaoOver;

	@Column(name = "pgto_comissao_sera")
	private Integer pgtoComissaoSera;

	@Column(name = "lcto_comissao")
	private Integer lctoComissao;

	@ManyToOne
	@JoinColumn(name = "id_plano_conta", referencedColumnName = "id")
	private PlanoConta planoConta;

	@ManyToOne
	@JoinColumn(name = "id_conta_caixa", referencedColumnName = "id")
	private ContaCaixa contaCaixa;

	/**
	 * ********************************************************
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module PATRIMONIO
	 */

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<BemEntity> bemList;

	/**
	 * ********************************************************
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<AfastamentoEntity> afastamentoList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<PlanoSaudeEntity> planoSaudeList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<PppEntity> pppList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<RescisaoEntity> rescisaoList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<ValeTransporteEntity> valeTransporteList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<HistoricoSalarialEntity> historicoSalarialList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<LancamentoCabecalhoEntity> lancamentoCabecalhoList;

	@OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY)
	private List<FeriasPeriodoAquisitivoEntity> feriasPeriodoAquisitivoEntityList;

	/**
	 * ********************************************************
	 */

	/**
	 * CONSTRUTOR
	 */

	public ColaboradorEntity() {

	}

	/**
	 * ********************************************************
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	public ColaboradorEntity(Integer id, String matricula) {
		this.id = id;
		this.matricula = matricula;
	}

	/**
	 * ********************************************************
	 */

	/**
	 * GETS E SETS
	 * 
	 * @param id
	 */

	public ColaboradorEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoto34() {
		return foto34;
	}

	public void setFoto34(String foto34) {
		this.foto34 = foto34;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Setor getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Setor idSetor) {
		this.idSetor = idSetor;
	}

	public CargoEntity getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(CargoEntity idCargo) {
		this.idCargo = idCargo;
	}

	public TipoColaboradorEntity getIdTipoColaborador() {
		return idTipoColaborador;
	}

	public void setIdTipoColaborador(TipoColaboradorEntity idTipoColaborador) {
		this.idTipoColaborador = idTipoColaborador;
	}

	public NivelFormacao getIdNivelFormacao() {
		return idNivelFormacao;
	}

	public void setIdNivelFormacao(NivelFormacao idNivelFormacao) {
		this.idNivelFormacao = idNivelFormacao;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity idPessoa) {
		this.pessoa = idPessoa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Date getVencimentoFerias() {
		return vencimentoFerias;
	}

	public void setVencimentoFerias(Date vencimentoFerias) {
		this.vencimentoFerias = vencimentoFerias;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public Character getFgts_optante() {
		return fgts_optante;
	}

	public void setFgts_optante(Character fgts_optante) {
		this.fgts_optante = fgts_optante;
	}

	public Date getFgtsDataOpcao() {
		return fgtsDataOpcao;
	}

	public void setFgtsDataOpcao(Date fgtsDataOpcao) {
		this.fgtsDataOpcao = fgtsDataOpcao;
	}

	public Integer getFgtsConta() {
		return fgtsConta;
	}

	public void setFgtsConta(Integer fgtsConta) {
		this.fgtsConta = fgtsConta;
	}

	public Character getPagamentoForma() {
		return pagamentoForma;
	}

	public void setPagamentoForma(Character pagamentoForma) {
		this.pagamentoForma = pagamentoForma;
	}

	public String getPagamentoBanco() {
		return pagamentoBanco;
	}

	public void setPagamentoBanco(String pagamentoBanco) {
		this.pagamentoBanco = pagamentoBanco;
	}

	public String getPagamentoAgencia() {
		return pagamentoAgencia;
	}

	public void setPagamentoAgencia(String pagamentoAgencia) {
		this.pagamentoAgencia = pagamentoAgencia;
	}

	public Character getPagamentoAgenciaDigito() {
		return pagamentoAgenciaDigito;
	}

	public void setPagamentoAgenciaDigito(Character pagamentoAgenciaDigito) {
		this.pagamentoAgenciaDigito = pagamentoAgenciaDigito;
	}

	public String getPagamentoConta() {
		return pagamentoConta;
	}

	public void setPagamentoConta(String pagamentoConta) {
		this.pagamentoConta = pagamentoConta;
	}

	public Character getPagamentoContaDigito() {
		return pagamentoContaDigito;
	}

	public void setPagamentoContaDigito(Character pagamentoContaDigito) {
		this.pagamentoContaDigito = pagamentoContaDigito;
	}

	public Date getExameMedicoUltimo() {
		return exameMedicoUltimo;
	}

	public void setExameMedicoUltimo(Date exameMedicoUltimo) {
		this.exameMedicoUltimo = exameMedicoUltimo;
	}

	public Date getExameMedicoVencimento() {
		return exameMedicoVencimento;
	}

	public void setExameMedicoVencimento(Date exameMedicoVencimento) {
		this.exameMedicoVencimento = exameMedicoVencimento;
	}

	public Date getPisDataCadastro() {
		return pisDataCadastro;
	}

	public void setPisDataCadastro(Date pisDataCadastro) {
		this.pisDataCadastro = pisDataCadastro;
	}

	public String getPisNumero() {
		return pisNumero;
	}

	public void setPisNumero(String pisNumero) {
		this.pisNumero = pisNumero;
	}

	public String getPisBanco() {
		return pisBanco;
	}

	public void setPisBanco(String pisBanco) {
		this.pisBanco = pisBanco;
	}

	public String getPisAgencia() {
		return pisAgencia;
	}

	public void setPisAgencia(String pisAgencia) {
		this.pisAgencia = pisAgencia;
	}

	public Character getPisAgenciaDigito() {
		return pisAgenciaDigito;
	}

	public void setPisAgenciaDigito(Character pisAgenciaDigito) {
		this.pisAgenciaDigito = pisAgenciaDigito;
	}

	public String getCtps_Numero() {
		return ctps_Numero;
	}

	public void setCtps_Numero(String ctps_Numero) {
		this.ctps_Numero = ctps_Numero;
	}

	public String getCtpsSerie() {
		return ctpsSerie;
	}

	public void setCtpsSerie(String ctpsSerie) {
		this.ctpsSerie = ctpsSerie;
	}

	public Date getCtpsDataExpedicao() {
		return ctpsDataExpedicao;
	}

	public void setCtpsDataExpedicao(Date ctpsDataExpedicao) {
		this.ctpsDataExpedicao = ctpsDataExpedicao;
	}

	public String getCtpsUf() {
		return ctpsUf;
	}

	public void setCtpsUf(String ctpsUf) {
		this.ctpsUf = ctpsUf;
	}

	public Character getDescontoPlanoSaude() {
		return descontoPlanoSaude;
	}

	public void setDescontoPlanoSaude(Character descontoPlanoSaude) {
		this.descontoPlanoSaude = descontoPlanoSaude;
	}

	public Character getSaiNaRais() {
		return saiNaRais;
	}

	public void setSaiNaRais(Character saiNaRais) {
		this.saiNaRais = saiNaRais;
	}

	public Character getCategoriaSefip() {
		return categoriaSefip;
	}

	public void setCategoriaSefip(Character categoriaSefip) {
		this.categoriaSefip = categoriaSefip;
	}

	public Integer getOcorrenciaSefip() {
		return ocorrenciaSefip;
	}

	public void setOcorrenciaSefip(Integer ocorrenciaSefip) {
		this.ocorrenciaSefip = ocorrenciaSefip;
	}

	public Integer getCodigoAdmissaoCaged() {
		return codigoAdmissaoCaged;
	}

	public void setCodigoAdmissaoCaged(Integer codigoAdmissaoCaged) {
		this.codigoAdmissaoCaged = codigoAdmissaoCaged;
	}

	public Integer getCodigoDemissaoCaged() {
		return codigoDemissaoCaged;
	}

	public void setCodigoDemissaoCaged(Integer codigoDemissaoCaged) {
		this.codigoDemissaoCaged = codigoDemissaoCaged;
	}

	public Integer getCodigoDemissaoSefip() {
		return codigoDemissaoSefip;
	}

	public void setCodigoDemissaoSefip(Integer codigoDemissaoSefip) {
		this.codigoDemissaoSefip = codigoDemissaoSefip;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public String getCodigoTurmaPonto() {
		return codigoTurmaPonto;
	}

	public void setCodigoTurmaPonto(String codigoTurmaPonto) {
		this.codigoTurmaPonto = codigoTurmaPonto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<BemEntity> getBemList() {
		return bemList;
	}

	public void setBemList(List<BemEntity> bemList) {
		this.bemList = bemList;
	}

	public List<AfastamentoEntity> getAfastamentoList() {
		return afastamentoList;
	}

	public void setAfastamentoList(List<AfastamentoEntity> afastamentoList) {
		this.afastamentoList = afastamentoList;
	}

	public List<PlanoSaudeEntity> getPlanoSaudeList() {
		return planoSaudeList;
	}

	public void setPlanoSaudeList(List<PlanoSaudeEntity> planoSaudeList) {
		this.planoSaudeList = planoSaudeList;
	}

	public List<PppEntity> getPppList() {
		return pppList;
	}

	public void setPppList(List<PppEntity> pppList) {
		this.pppList = pppList;
	}

	public List<RescisaoEntity> getRescisaoList() {
		return rescisaoList;
	}

	public void setRescisaoList(List<RescisaoEntity> rescisaoList) {
		this.rescisaoList = rescisaoList;
	}

	public List<ValeTransporteEntity> getValeTransporteList() {
		return valeTransporteList;
	}

	public void setValeTransporteList(
			List<ValeTransporteEntity> valeTransporteList) {
		this.valeTransporteList = valeTransporteList;
	}

	public List<HistoricoSalarialEntity> getHistoricoSalarialList() {
		return historicoSalarialList;
	}

	public void setHistoricoSalarialList(
			List<HistoricoSalarialEntity> historicoSalarialList) {
		this.historicoSalarialList = historicoSalarialList;
	}

	public List<LancamentoCabecalhoEntity> getLancamentoCabecalhoList() {
		return lancamentoCabecalhoList;
	}

	public void setLancamentoCabecalhoList(
			List<LancamentoCabecalhoEntity> lancamentoCabecalhoList) {
		this.lancamentoCabecalhoList = lancamentoCabecalhoList;
	}

	public List<FeriasPeriodoAquisitivoEntity> getFeriasPeriodoAquisitivoEntityList() {
		return feriasPeriodoAquisitivoEntityList;
	}

	public void setFeriasPeriodoAquisitivoEntityList(
			List<FeriasPeriodoAquisitivoEntity> feriasPeriodoAquisitivoEntityList) {
		this.feriasPeriodoAquisitivoEntityList = feriasPeriodoAquisitivoEntityList;
	}

	public ContabilConta getIdContaContabil() {
		return idContaContabil;
	}

	public void setIdContaContabil(ContabilConta idContaContabil) {
		this.idContaContabil = idContaContabil;
	}

	public SituacaoColaboradorEntity getIdSituacaoColaborador() {
		return idSituacaoColaborador;
	}

	public void setIdSituacaoColaborador(
			SituacaoColaboradorEntity idSituacaoColaborador) {
		this.idSituacaoColaborador = idSituacaoColaborador;
	}

	public Sindicato getIdSindicato() {
		return idSindicato;
	}

	public void setIdSindicato(Sindicato idSindicato) {
		this.idSindicato = idSindicato;
	}

	public BigDecimal getSalarioFixo() {
		return salarioFixo;
	}

	public void setSalarioFixo(BigDecimal salarioFixo) {
		this.salarioFixo = salarioFixo;
	}

	public String getTipoComissaoServico() {
		return tipoComissaoServico;
	}

	public void setTipoComissaoServico(String tipoComissaoServico) {
		this.tipoComissaoServico = tipoComissaoServico;
	}

	public BigDecimal getValorComissaoServico() {
		return valorComissaoServico;
	}

	public void setValorComissaoServico(BigDecimal valorComissaoServico) {
		this.valorComissaoServico = valorComissaoServico;
	}

	public String getTipoComissaoProduto() {
		return tipoComissaoProduto;
	}

	public void setTipoComissaoProduto(String tipoComissaoProduto) {
		this.tipoComissaoProduto = tipoComissaoProduto;
	}

	public BigDecimal getValorComissaoProduto() {
		return valorComissaoProduto;
	}

	public void setValorComissaoProduto(BigDecimal valorComissaoProduto) {
		this.valorComissaoProduto = valorComissaoProduto;
	}

	public Boolean getPriorizarComissao() {
		return priorizarComissao;
	}

	public void setPriorizarComissao(Boolean priorizarComissao) {
		this.priorizarComissao = priorizarComissao;
	}

	public Boolean getComissaoOver() {
		return comissaoOver;
	}

	public void setComissaoOver(Boolean comissaoOver) {
		this.comissaoOver = comissaoOver;
	}

	public Integer getPgtoComissaoSera() {
		return pgtoComissaoSera;
	}

	public void setPgtoComissaoSera(Integer pgtoComissaoSera) {
		this.pgtoComissaoSera = pgtoComissaoSera;
	}

	public Integer getLctoComissao() {
		return lctoComissao;
	}

	public void setLctoComissao(Integer lctoComissao) {
		this.lctoComissao = lctoComissao;
	}

	public PlanoConta getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	public ContaCaixa getContaCaixa() {
		return contaCaixa;
	}

	public void setContaCaixa(ContaCaixa contaCaixa) {
		this.contaCaixa = contaCaixa;
	}

	@Override
	public String toString() {
		return this.pessoa.getNome();
	}

}