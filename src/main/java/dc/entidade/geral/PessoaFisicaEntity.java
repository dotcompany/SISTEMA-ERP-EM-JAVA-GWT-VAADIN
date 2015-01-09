package dc.entidade.geral;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.TipoSanguineoEn;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.EstadoCivilEntity;

@Entity
@Table(name = "pessoa_fisica")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaFisicaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_fisica_id_seq")
	@SequenceGenerator(name = "pessoa_fisica_id_seq", sequenceName = "pessoa_fisica_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("CPF")
	@Column(name = "CPF")
	private String cpf;

	@Field
	@Caption("RG")
	@Column(name = "RG")
	private String rg;

	@Column(name = "ORGAO_RG")
	private String orgaoRg;

	@Column(name = "DATA_EMISSAO_RG")
	@Temporal(TemporalType.DATE)
	private Date dataEmissaoRg;

	@Column(name = "DATA_NASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "NATURALIDADE", length = 100)
	private String naturalidade;

	@Column(name = "NACIONALIDADE", length = 100)
	private String nacionalidade;

	@Column(name = "CNH_NUMERO", length = 20)
	private String cnhNumero;

	@Column(name = "CNH_VENCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date cnhVencimento;

	@Column(name = "TITULO_ELEITORAL_NUMERO", length = 20)
	private String tituloEleitoralNumero;

	@Column(name = "TITULO_ELEITORAL_ZONA")
	private Integer tituloEleitoralZona;

	@Column(name = "TITULO_ELEITORAL_SECAO")
	private Integer tituloEleitoralSecao;

	@Column(name = "RESERVISTA_NUMERO", length = 20)
	private String reservistaNumero;

	@Column(name = "NOME_MAE", length = 100)
	private String nomeMae;

	@Column(name = "NOME_PAI", length = 100)
	private String nomePai;

	@Field
	@Caption("Sexo")
	@Column(name = "sexo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private SexoEn sexo;

	@Field
	@Caption("CNH categoria")
	@Column(name = "cnh_categoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private CnhEn cnh;

	@Field
	@Caption("Raça")
	@Column(name = "raca")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private RacaEn raca;

	@Field
	@Caption("Tipo de sangue")
	@Column(name = "tipo_sangue")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private TipoSanguineoEn tipoSangue;

	@Field
	@Caption("Reservista categoria")
	@Column(name = "reservista_categoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.ORDINAL)
	private CategoriaReservistaEn reservistaCategoria;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado_civil")
	private EstadoCivilEntity estadoCivil;

	@OneToOne()
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	private PessoaEntity pessoa;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaFisicaEntity() {

	}

	public PessoaFisicaEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoRg() {
		return orgaoRg;
	}

	public void setOrgaoRg(String orgaoRg) {
		this.orgaoRg = orgaoRg;
	}

	public Date getDataEmissaoRg() {
		return dataEmissaoRg;
	}

	public void setDataEmissaoRg(Date dataEmissaoRg) {
		this.dataEmissaoRg = dataEmissaoRg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCnhNumero() {
		return cnhNumero;
	}

	public void setCnhNumero(String cnhNumero) {
		this.cnhNumero = cnhNumero;
	}

	public Date getCnhVencimento() {
		return cnhVencimento;
	}

	public void setCnhVencimento(Date cnhVencimento) {
		this.cnhVencimento = cnhVencimento;
	}

	public String getTituloEleitoralNumero() {
		return tituloEleitoralNumero;
	}

	public void setTituloEleitoralNumero(String tituloEleitoralNumero) {
		this.tituloEleitoralNumero = tituloEleitoralNumero;
	}

	public Integer getTituloEleitoralZona() {
		return tituloEleitoralZona;
	}

	public void setTituloEleitoralZona(Integer tituloEleitoralZona) {
		this.tituloEleitoralZona = tituloEleitoralZona;
	}

	public Integer getTituloEleitoralSecao() {
		return tituloEleitoralSecao;
	}

	public void setTituloEleitoralSecao(Integer tituloEleitoralSecao) {
		this.tituloEleitoralSecao = tituloEleitoralSecao;
	}

	public String getReservistaNumero() {
		return reservistaNumero;
	}

	public void setReservistaNumero(String reservistaNumero) {
		this.reservistaNumero = reservistaNumero;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public SexoEn getSexo() {
		return sexo;
	}

	public void setSexo(SexoEn sexo) {
		this.sexo = sexo;
	}

	public CnhEn getCnh() {
		return cnh;
	}

	public void setCnh(CnhEn cnh) {
		this.cnh = cnh;
	}

	public RacaEn getRaca() {
		return raca;
	}

	public void setRaca(RacaEn raca) {
		this.raca = raca;
	}

	public TipoSanguineoEn getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(TipoSanguineoEn tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public CategoriaReservistaEn getReservistaCategoria() {
		return reservistaCategoria;
	}

	public void setReservistaCategoria(CategoriaReservistaEn reservistaCategoria) {
		this.reservistaCategoria = reservistaCategoria;
	}

	public EstadoCivilEntity getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEntity estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}