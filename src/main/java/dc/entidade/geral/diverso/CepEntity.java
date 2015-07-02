package dc.entidade.geral.diverso;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cep")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CepEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cep_id_seq")
	@SequenceGenerator(name = "cep_id_seq", sequenceName = "cep_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 8)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep;

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro;

	@Field
	@Caption("Complemento")
	@Column(name = "COMPLEMENTO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String complemento;

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro;

	@Field
	@Caption("Municipio")
	@Column(name = "MUNICIPIO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String municipio;

	//@Field
	//@Caption()
	//@Column(name = "UF")
	//@ComboValue
	//@Analyzer(definition = "dc_combo_analyzer")
	//private String uf;
	
	@Transient
	private UfEntity uf;

	@Field
	@Caption()
	@Column(name = "CODIGO_IBGE_MUNICIPIO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbgeMunicipio;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public CepEntity() {

	}

	public CepEntity(Integer id) {
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public Integer getCodigoIbgeMunicipio() {
		return codigoIbgeMunicipio;
	}

	public void setCodigoIbgeMunicipio(Integer codigoIbgeMunicipio) {
		this.codigoIbgeMunicipio = codigoIbgeMunicipio;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}