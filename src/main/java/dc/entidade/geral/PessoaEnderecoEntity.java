package dc.entidade.geral;

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
import javax.persistence.Transient;
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

@Entity
@Table(name = "pessoa_endereco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaEnderecoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
	@SequenceGenerator(name = "endereco_id_seq", sequenceName = "endereco_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro = "";

	@Field
	@Caption("Número")
	@Column(name = "NUMERO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer numero = new Integer(0);

	@Field
	@Caption("Complemento")
	@Column(name = "COMPLEMENTO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento = "";

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro = "";

	@Field
	@Caption("Cidade")
	@Column(name = "CIDADE", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cidade = "";

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 8)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Caption("Municipio Ibge")
	@Column(name = "MUNICIPIO_IBGE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIbge = new Integer(0);

	@Field
	@Caption("Fone")
	@Column(name = "FONE", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone = "";

	@Field
	@Caption()
	@Column(name = "FAX", length = 14)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fax = "";

	@Field
	@Caption()
	@Column(name = "PRINCIPAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean principal = Boolean.FALSE;

	@Field
	@Caption()
	@Column(name = "ENTREGA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean entrega = Boolean.FALSE;

	@Field
	@Caption()
	@Column(name = "COBRANCA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean cobranca = Boolean.FALSE;

	@Field
	@Caption()
	@Column(name = "CORRESPONDENCIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Boolean correspondencia = Boolean.FALSE;

	@Field
	@Caption()
	@Column(name = "uf", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String siglaUf = "";

	@Field
	@Caption()
	@Column(name = "id_uf")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer idUf;

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Pessoa")
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private PessoaEntity pessoa;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	private UfEntity uf;

	/**
	 * CONSTRUTOR
	 */

	public PessoaEnderecoEntity() {

	}

	public PessoaEnderecoEntity(Integer id) {
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = (logradouro == null ? "".trim() : logradouro
				.toUpperCase().trim());
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = (numero == null ? new Integer(0) : numero);
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = (complemento == null ? "".trim() : complemento
				.toUpperCase().trim());
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = (bairro == null ? "".trim() : bairro.toUpperCase().trim());
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = (cidade == null ? "".trim() : cidade.toUpperCase().trim());
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = (cep == null ? "".trim() : cep.toUpperCase().trim());
	}

	public Integer getMunicipioIbge() {
		return municipioIbge;
	}

	public void setMunicipioIbge(Integer municipioIbge) {
		this.municipioIbge = (municipioIbge == null ? new Integer(0)
				: municipioIbge);
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = (fone == null ? "".trim() : fone.toUpperCase().trim());
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = (fax == null ? "".trim() : fax.toUpperCase().trim());
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = (principal == null ? Boolean.FALSE : principal);
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public void setEntrega(Boolean entrega) {
		this.entrega = (entrega == null ? Boolean.FALSE : entrega);
	}

	public Boolean getCobranca() {
		return cobranca;
	}

	public void setCobranca(Boolean cobranca) {
		this.cobranca = (cobranca == null ? Boolean.FALSE : cobranca);
	}

	public Boolean getCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(Boolean correspondencia) {
		this.correspondencia = (correspondencia == null ? Boolean.FALSE
				: correspondencia);
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = (siglaUf == null ? "".trim() : siglaUf.toUpperCase()
				.trim());
	}

	public Integer getIdUf() {
		return idUf;
	}

	public void setIdUf(Integer idUf) {
		this.idUf = idUf;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}