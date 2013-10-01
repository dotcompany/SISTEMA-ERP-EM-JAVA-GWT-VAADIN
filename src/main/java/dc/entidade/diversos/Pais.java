package dc.entidade.diversos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Empresa;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_id_seq")
	@SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Caption("Código")
	@Column(name = "codigo")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigo;

	@Field
	@Caption("Nome En")
	@Column(name = "nome_en", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomeEn;

	@Field
	@Caption("Nome PTBR")
	@Column(name = "nome_ptbr", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nomePtbr;

	@Field
	@Caption("Sigla 2")
	@Column(name = "sigla2", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla2;

	@Field
	@Caption("Sigla 3")
	@Column(name = "sigla3", length = 3)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla3;

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA", nullable = false)
	@Caption("Empresa")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Empresa empresa;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "paisId", fetch = FetchType.LAZY)
	private List<Estado> estadoList;

	/**
	 * CONSTRUTOR
	 */

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Estado> getEstadoList() {
		return estadoList;
	}

	public void setEstadoList(List<Estado> estadoList) {
		this.estadoList = estadoList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}