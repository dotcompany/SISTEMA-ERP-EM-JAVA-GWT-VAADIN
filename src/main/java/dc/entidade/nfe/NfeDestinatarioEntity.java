package dc.entidade.nfe;

import java.io.Serializable;

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
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

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
	private Integer id;

	@Field
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	@Field
	@Column(name = "razao_social")
	private String razaoSocial;

	@Field
	@Column(name = "logradouro")
	private String logradouro;

	@Field
	@Column(name = "numero")
	private String numero;

	@Field
	@Column(name = "complemento")
	private String complemento;

	@Field
	@Column(name = "bairro")
	private String bairro;

	@Field
	@Column(name = "codigo_municipio")
	private Integer codigoMunicipio;

	@Field
	@Column(name = "nome_municipio")
	private String nomeMunicipio;

	@Field
	@Column(name = "uf")
	private String uf;

	@Field
	@Column(name = "cep")
	private String cep;

	@Field
	@Column(name = "codigo_pais")
	private Integer codigoPais;

	@Field
	@Column(name = "nome_pais")
	private String nomePais;

	@Field
	@Column(name = "telefone")
	private String telefone;

	@Field
	@Column(name = "inscricao_estadual")
	private String inscricaoEstadual;

	@Field
	@Column(name = "suframa")
	private String suframa;

	@Field
	@Column(name = "email")
	private String email;

	/**
	 * REFERENCIA - FK
	 */

	// id_nfe_cabecalho integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_nfe_cabecalho", nullable = false)
	@Caption("NF-e - Cabeçalho")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private NfeCabecalhoEntity nfeCabecalho;

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

	public NfeCabecalhoEntity getNfeCabecalho() {
		return nfeCabecalho;
	}

	public void setNfeCabecalho(NfeCabecalhoEntity nfeCabecalho) {
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