package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.contabilidade.PlanoConta;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.SindicatoEntity;
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
import dc.entidade.geral.NivelFormacaoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.diverso.SetorEntity;
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
public class ColaboradorEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

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
	@Caption("Matrícula")
	@Column(name = "MATRICULA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String matricula;

	@Lob
	@Type(type = "text")
	@Field
	@Caption("Foto 3x4")
	@Column(name = "FOTO_34")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String foto34;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de cadastro")
	@Column(name = "DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de admissão")
	@Column(name = "DATA_ADMISSAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataAdmissao;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Vencimento de férias")
	@Column(name = "VENCIMENTO_FERIAS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date vencimentoFerias;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("Data de transferência")
	@Column(name = "DATA_TRANSFERENCIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataTransferencia;

	@Field
	@Caption("FGTS optante")
	@Column(name = "FGTS_OPTANTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character fgts_optante;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption("FGTS data da opção")
	@Column(name = "FGTS_DATA_OPCAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date fgtsDataOpcao;

	@Field
	@Caption("FGTS conta")
	@Column(name = "FGTS_CONTA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer fgtsConta;

	@Field
	@Caption("Forma de pagamento")
	@Column(name = "PAGAMENTO_FORMA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pagamentoForma;

	@Field
	@Caption("Banco do pagamento")
	@Column(name = "PAGAMENTO_BANCO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoBanco;

	@Field
	@Caption("Agência do pagamento")
	@Column(name = "PAGAMENTO_AGENCIA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoAgencia;

	@Field
	@Caption()
	@Column(name = "PAGAMENTO_AGENCIA_DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pagamentoAgenciaDigito;

	@Field
	@Caption()
	@Column(name = "PAGAMENTO_CONTA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pagamentoConta;

	@Field
	@Caption()
	@Column(name = "PAGAMENTO_CONTA_DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pagamentoContaDigito;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "EXAME_MEDICO_ULTIMO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date exameMedicoUltimo;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "EXAME_MEDICO_VENCIMENTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date exameMedicoVencimento;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "PIS_DATA_CADASTRO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date pisDataCadastro;

	@Field
	@Caption()
	@Column(name = "PIS_NUMERO", length = 12)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisNumero;

	@Field
	@Caption()
	@Column(name = "PIS_BANCO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisBanco;

	@Field
	@Caption()
	@Column(name = "PIS_AGENCIA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String pisAgencia;

	@Field
	@Caption()
	@Column(name = "PIS_AGENCIA_DIGITO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character pisAgenciaDigito;

	@Field
	@Caption()
	@Column(name = "CTPS_NUMERO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctps_Numero;

	@Field
	@Caption()
	@Column(name = "CTPS_SERIE", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsSerie;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "CTPS_DATA_EXPEDICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date ctpsDataExpedicao;

	@Field
	@Caption()
	@Column(name = "CTPS_UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ctpsUf;

	@Field
	@Caption()
	@Column(name = "DESCONTO_PLANO_SAUDE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character descontoPlanoSaude;

	@Field
	@Caption()
	@Column(name = "SAI_NA_RAIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character saiNaRais;

	@Field
	@Caption()
	@Column(name = "CATEGORIA_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character categoriaSefip;

	@Field
	@Caption()
	@Column(name = "OCORRENCIA_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer ocorrenciaSefip;

	@Field
	@Caption()
	@Column(name = "CODIGO_ADMISSAO_CAGED")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoAdmissaoCaged;

	@Field
	@Caption()
	@Column(name = "CODIGO_DEMISSAO_CAGED")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoDemissaoCaged;

	@Field
	@Caption()
	@Column(name = "CODIGO_DEMISSAO_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoDemissaoSefip;

	@Temporal(TemporalType.DATE)
	@Field
	@Caption()
	@Column(name = "DATA_DEMISSAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataDemissao;

	@Field
	@Caption("Código da turma do ponto")
	@Column(name = "CODIGO_TURMA_PONTO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoTurmaPonto;

	@Lob
	@Type(type = "text")
	@Field
	@Caption()
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao;

	@Field
	@Caption()
	@Column(name = "salario_fixo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal salarioFixo;

	@Field
	@Caption()
	@Column(name = "tipo_comissao_servico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoComissaoServico;

	@Field
	@Caption()
	@Column(name = "valor_comissao_servico")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorComissaoServico;

	@Field
	@Caption()
	@Column(name = "tipo_comissao_produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipoComissaoProduto;

	@Field
	@Caption()
	@Column(name = "valor_comissao_produto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorComissaoProduto;

	@Field
	@Caption()
	@Column(name = "priorizar_comissao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean priorizarComissao;

	@Field
	@Caption()
	@Column(name = "comissao_over")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean comissaoOver;

	@Field
	@Caption()
	@Column(name = "pgto_comissao_sera")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer pgtoComissaoSera;

	@Field
	@Caption()
	@Column(name = "lcto_comissao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer lctoComissao;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_setor", nullable = false)
	@Caption("Setor")
	private SetorEntity setor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contabil_conta", nullable = true)
	@Caption("Conta contábil")
	private ContabilContaEntity contaContabil;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cargo", nullable = false)
	@Caption("Cargo")
	private CargoEntity cargo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipo_colaborador", nullable = false)
	@Caption("Tipo do colaborador")
	private TipoColaboradorEntity tipoColaborador;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_situacao_colaborador", nullable = false)
	@Caption("Situação do colaborador")
	private SituacaoColaboradorEntity situacaoColaborador;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_nivel_formacao", nullable = false)
	@Caption("Nível de formação")
	private NivelFormacaoEntity nivelFormacao;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_sindicato", nullable = false)
	@Caption("Sindicato")
	private SindicatoEntity sindicato;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_plano_conta", nullable = true)
	@Caption("Plano de conta")
	private PlanoConta planoConta;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_conta_caixa", nullable = true)
	@Caption("Conta da caixa")
	private ContaCaixa contaCaixa;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa", nullable = false)
	@Caption("Pessoa")
	private PessoaEntity pessoa;

	/**
	 * REFERENCIA - LIST
	 */

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
	 * TRANSIENT
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

	public ColaboradorEntity(Integer id) {
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public SetorEntity getSetor() {
		return setor;
	}

	public void setSetor(SetorEntity setor) {
		this.setor = setor;
	}

	public ContabilContaEntity getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContabilContaEntity contaContabil) {
		this.contaContabil = contaContabil;
	}

	public CargoEntity getCargo() {
		return cargo;
	}

	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}

	public TipoColaboradorEntity getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(TipoColaboradorEntity tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	public SituacaoColaboradorEntity getSituacaoColaborador() {
		return situacaoColaborador;
	}

	public void setSituacaoColaborador(
			SituacaoColaboradorEntity situacaoColaborador) {
		this.situacaoColaborador = situacaoColaborador;
	}

	public NivelFormacaoEntity getNivelFormacao() {
		return nivelFormacao;
	}

	public void setNivelFormacao(NivelFormacaoEntity nivelFormacao) {
		this.nivelFormacao = nivelFormacao;
	}

	public SindicatoEntity getSindicato() {
		return sindicato;
	}

	public void setSindicato(SindicatoEntity sindicato) {
		this.sindicato = sindicato;
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

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
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

	@Override
	public String toString() {
		return this.pessoa.getNome();
	}

}