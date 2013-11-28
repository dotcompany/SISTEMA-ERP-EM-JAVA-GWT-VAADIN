package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "uf")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class UF extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "ID_PAIS", nullable = false)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer idPais;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Sigla")
	@Column(name = "SIGLA", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla;

	@Column(name = "CODIGO_IBGE", nullable = false)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbge;

	public UF() {

	}

	public UF(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof UF == false)
			return false;
		if (this == object)
			return true;
		final UF other = (UF) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return nome;
	}

}