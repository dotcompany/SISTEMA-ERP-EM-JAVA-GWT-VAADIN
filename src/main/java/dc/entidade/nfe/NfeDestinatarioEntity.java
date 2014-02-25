package dc.entidade.nfe;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "nfe_destinatario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDestinatarioEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_destinatario_id_seq")
	@SequenceGenerator(name = "nfe_destinatario_id_seq", sequenceName = "nfe_destinatario_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "cpf_cnpj")
	@Caption(value = "CPF / CNPJ")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cpfCnpj = "";

	@Field
	@Column(name = "razao_social")
	@Caption(value = "Razão social")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String razaoSocial = "";

	@Field
	@Column(name = "logradouro")
	@Caption(value = "Logradouro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro = "";

	@Field
	@Column(name = "numero")
	@Caption(value = "Número")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero = "";

	@Field
	@Column(name = "complemento")
	@Caption(value = "Complemento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento = "";

	@Field
	@Column(name = "bairro")
	@Caption(value = "Bairro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro = "";

	@Field
	@Column(name = "codigo_municipio")
	@Caption(value = "Código do município")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoMunicipio;

	@Field
	@Column(name = "nome_municipio")
	@Caption(value = "Nome do município")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeMunicipio = "";

	@Field
	@Column(name = "uf")
	@Caption(value = "UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String uf = "";

	@Field
	@Column(name = "cep")
	@Caption(value = "CEP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Column(name = "codigo_pais")
	@Caption(value = "Código do país")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoPais = new Integer(0);

	@Field
	@Column(name = "nome_pais")
	@Caption(value = "Nome do país")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomePais = "";

	@Field
	@Column(name = "telefone")
	@Caption(value = "Telefone")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String telefone = "";

	@Field
	@Column(name = "inscricao_estadual")
	@Caption(value = "Inscrição estadual")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoEstadual = "";

	@Field
	@Column(name = "suframa")
	@Caption(value = "SUFRAMA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String suframa = "";

	@Field
	@Column(name = "email")
	@Caption(value = "E-mail")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_nfe_cabecalho integer NOT NULL,

	@Field
	@Column(name = "id_nfe_cabecalho")
	@Caption(value = "NFE cabeçalho")
	private Integer nfeCabecalho;

	// id_empresa integer,

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDestinatarioEntity() {
		// TODO Auto-generated constructor stub
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
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

	public Integer getNfeCabecalho() {
		return nfeCabecalho;
	}

	public void setNfeCabecalho(Integer nfeCabecalho) {
		this.nfeCabecalho = nfeCabecalho;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}