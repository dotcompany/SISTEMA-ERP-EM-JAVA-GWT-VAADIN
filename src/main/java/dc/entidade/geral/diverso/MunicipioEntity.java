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

@Entity
@Table(name = "municipio")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class MunicipioEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipio_id_seq")
	@SequenceGenerator(name = "municipio_id_seq", sequenceName = "municipio_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	@Field
	@Caption("Codigo IBGE")
	@Column(name = "CODIGO_IBGE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbge = new Integer(0);

	@Field
	@Caption("Código da receita federal")
	@Column(name = "CODIGO_RECEITA_FEDERAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoReceitaFederal = new Integer(0);

	@Field
	@Caption("Código estadual")
	@Column(name = "CODIGO_ESTADUAL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoEstadual = new Integer(0);
	
	@Field
	@Caption("UF sigla")
	@Column(name = "uf_sigla", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufSigla = "";
	

	/**
	 * REFERENCIA - FK
	 */

	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	@Caption("UF")
	@ManyToOne
	@JoinColumn(name = "ID_UF", nullable = false)
	private UfEntity uf;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public MunicipioEntity() {

	}

	public MunicipioEntity(Integer id) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = (codigoIbge == null ? new Integer(0) : codigoIbge);
	}

	public Integer getCodigoReceitaFederal() {
		return codigoReceitaFederal;
	}

	public void setCodigoReceitaFederal(Integer codigoReceitaFederal) {
		this.codigoReceitaFederal = (codigoReceitaFederal == null ? new Integer(
				0) : codigoReceitaFederal);
	}

	public Integer getCodigoEstadual() {
		return codigoEstadual;
	}

	public void setCodigoEstadual(Integer codigoEstadual) {
		this.codigoEstadual = (codigoEstadual == null ? new Integer(0)
				: codigoEstadual);
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}
	
	public String getUfSigla() {
		return ufSigla;
	}

	public void setUfSigla(String ufSigla) {
		this.ufSigla = (ufSigla == null ? "".trim() : ufSigla.toUpperCase()
				.trim());
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}