package dc.entidade.administrativo.empresa;

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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.CnaeEntity;

@Entity
@Table(name = "empresa_cnae")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EmpresaCnaeEntity extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "epc")
	@SequenceGenerator(name = "epc", sequenceName = "empresa_cnae_id_seq", allocationSize = 1)
	private Integer id;

	@Field
	@Caption("Principal")
	@Column(name = "principal")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String principal; // 0-Não 1-Sim

	@Field
	@Caption("Ramo de Atividade")
	@Column(name = "ramo_atividade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ramoAtividade;

	@Field
	@Caption("Objeto Social")
	@Column(name = "objeto_social")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String objetoSocial;

	@Caption("CNAE")
	@ManyToOne
	@JoinColumn(name = "ID_CNAE", nullable = false)
	private CnaeEntity cnae;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	@Caption("Principal")
	private String principalStr;

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getCnae().getNome();
	}

	/**
	 * CONSTRUTOR
	 */

	public EmpresaCnaeEntity() {
		// TODO Auto-generated constructor stub
	}

	public EmpresaCnaeEntity(Integer id) {
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

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getObjetoSocial() {
		return objetoSocial;
	}

	public void setObjetoSocial(String objetoSocial) {
		this.objetoSocial = objetoSocial;
	}

	public CnaeEntity getCnae() {
		return cnae;
	}

	public void setCnae(CnaeEntity cnae) {
		this.cnae = cnae;
	}

	public String getPrincipalStr() {
		principalStr = principal.equals("0") ? "Não" : "Sim";
		return principalStr;
	}

	public void setPrincipalStr(String principalStr) {
		this.principalStr = principalStr;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}