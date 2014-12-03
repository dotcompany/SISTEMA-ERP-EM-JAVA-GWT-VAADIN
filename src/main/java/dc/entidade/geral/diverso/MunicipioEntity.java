package dc.entidade.geral.diverso;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.UfEntity;

/**
 * 
 * @author Wesley Jr /*
 */

@Entity
@Table(name = "municipio")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class MunicipioEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;
	
	@Field
	@Caption("Codigo Ibge")
	@Column(name = "CODIGO_IBGE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbge;
	
	@Field
	@Caption("Codigo Receita Federal")
	@Column(name = "CODIGO_RECEITA_FEDERAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoReceitaFederal;
	
	@Field
	@Column(name = "CODIGO_ESTADUAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoEstadual;
	
	@ManyToOne
	@JoinColumn(name = "ID_UF", nullable = false)
	@Caption("Uf")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private UfEntity idUf;
	
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA", nullable = false)
	@Caption("Empresa")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Empresa idEmpresa;

	public MunicipioEntity() {

	}

	public MunicipioEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Integer getCodigoReceitaFederal() {
		return codigoReceitaFederal;
	}

	public void setCodigoReceitaFederal(Integer codigoReceitaFederal) {
		this.codigoReceitaFederal = codigoReceitaFederal;
	}

	public Integer getCodigoEstadual() {
		return codigoEstadual;
	}

	public void setCodigoEstadual(Integer codigoEstadual) {
		this.codigoEstadual = codigoEstadual;
	}

	public UfEntity getIdUf() {
		return idUf;
	}

	public void setIdUf(UfEntity idUf) {
		this.idUf = idUf;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] { "id" });
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof MunicipioEntity == false)
			return false;

		if (this == object)
			return true;

		final MunicipioEntity other = (MunicipioEntity) object;

		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}