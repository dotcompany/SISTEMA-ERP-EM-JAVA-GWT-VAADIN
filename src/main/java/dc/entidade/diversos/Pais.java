package dc.entidade.diversos;

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
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/***
 * 
 * @author Wesley Jr
 * 
 ***/

@Entity
@Table(name = "pais")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Pais extends AbstractModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "CODIGO")
	private Integer codigo;

	@Field
	@Caption("Nome En")
	@Column(name = "NOME_EN", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeEn;

	@Field
	@Caption("Nome PTBR")
	@Column(name = "NOME_PTBR", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomePtbr;

	@Field
	@Caption("Sigla 2")
	@Column(name = "SIGLA2", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla2;

	@Field
	@Caption("Sigla 3")
	@Column(name = "SIGLA3", length = 3)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla3;

	public Pais() {

	}

	public Pais(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeEn() {
		return nomeEn;
	}

	public void setNomeEn(String nomeEn) {
		this.nomeEn = nomeEn;
	}

	public String getNomePtbr() {
		return nomePtbr;
	}

	public void setNomePtbr(String nomePtbr) {
		this.nomePtbr = nomePtbr;
	}

	public String getSigla2() {
		return sigla2;
	}

	public void setSigla2(String sigla2) {
		this.sigla2 = sigla2;
	}

	public String getSigla3() {
		return sigla3;
	}

	public void setSigla3(String sigla3) {
		this.sigla3 = sigla3;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Pais == false)
			return false;
		if (this == object)
			return true;
		final Pais other = (Pais) object;
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
