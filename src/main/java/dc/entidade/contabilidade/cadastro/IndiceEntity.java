package dc.entidade.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;

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
import dc.entidade.financeiro.IndiceEconomico;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "contabil_indice")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class IndiceEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_indice_id_seq")
	@SequenceGenerator(name = "contabil_indice_id_seq", sequenceName = "contabil_indice_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "periodicidade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Periodicidade")
	private String periodicidade = "";

	@Field
	@Column(name = "diario_a_partir_de")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Diário a partir de")
	private Date diarioPartirDe;

	@Field
	@Column(name = "mensal_mes_ano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Mensal mês ano")
	private String mensalMesAno = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_indice_economico integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_indice_economico", nullable = false)
	@Caption("Índice econômico")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private IndiceEconomico indiceEconomico;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public IndiceEntity() {
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

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = (periodicidade == null ? "" : periodicidade
				.toUpperCase());
	}

	public Date getDiarioPartirDe() {
		return diarioPartirDe;
	}

	public void setDiarioPartirDe(Date diarioPartirDe) {
		this.diarioPartirDe = diarioPartirDe;
	}

	public String getMensalMesAno() {
		return mensalMesAno;
	}

	public void setMensalMesAno(String mensalMesAno) {
		this.mensalMesAno = (mensalMesAno == null ? "" : mensalMesAno
				.toUpperCase());
	}

	public IndiceEconomico getIndiceEconomico() {
		return indiceEconomico;
	}

	public void setIndiceEconomico(IndiceEconomico indiceEconomico) {
		this.indiceEconomico = indiceEconomico;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}