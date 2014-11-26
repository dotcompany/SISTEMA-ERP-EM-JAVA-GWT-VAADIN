package dc.entidade.pessoal;

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
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.PessoaEntity;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Entity
@Table(name = "contador")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Contador extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contador_id_seq")
	@SequenceGenerator(name = "contador_id_seq", sequenceName = "contador_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Inscricao CRC")
	@Column(name = "INSCRICAO_CRC", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String inscricaoCrc;

	@Field
	@Caption("Uf CRC")
	@Column(name = "UF_CRC")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufCrc;

	@Field
	@Caption("Telefone")
	@Column(name = "FONE", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone;

	@Field
	@Caption("Fax")
	@Column(name = "FAX")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fax;

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero;

	@Field
	@Caption("Complemento")
	@Column(name = "complemento", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento;

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro;

	@Field
	@Caption("Cidade")
	@Column(name = "CIDADE", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cidade;

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep;

	@Field
	@Caption("Municipio IBGE")
	@Column(name = "MUNICIPIO_IBGE", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIBGE;

	@Field
	@Caption("UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String uf;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;
	
	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 150)
	private String nome;
	
	@Field
	@Caption("Cpf")
	@Column(name = "CPF", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cpf;
	
	@Field
	@Caption("Cnpj")
	@Column(name = "CNPJ", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj;
	
	@Field
	@Caption("Site")
	@Column(name = "SITE", length = 150)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String site;

	public Contador() {

	}

	public Contador(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInscricaoCrc() {
		return inscricaoCrc;
	}

	public void setInscricaoCrc(String inscricaoCrc) {
		this.inscricaoCrc = inscricaoCrc;
	}

	public String getUfCrc() {
		return ufCrc;
	}

	public void setUfCrc(String ufCrc) {
		this.ufCrc = ufCrc;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getMunicipioIBGE() {
		return municipioIBGE;
	}

	public void setMunicipioIBGE(Integer municipioIBGE) {
		this.municipioIBGE = municipioIBGE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}