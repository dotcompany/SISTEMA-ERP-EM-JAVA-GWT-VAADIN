package dc.entidade.framework;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
@Table(name = "seguimento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SeguimentoEntity extends AbstractModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seguimento_id_seq")
	@SequenceGenerator(name = "seguimento_id_seq", sequenceName = "seguimento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption(value = "Nome")
	@Column()
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption(value = "Descrição")
	@Column()
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@OneToMany(mappedBy = "seguimento", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpresaSeguimento> empresaSeguimentos;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public SeguimentoEntity() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<EmpresaSeguimento> getEmpresaSeguimentos() {
		return empresaSeguimentos;
	}

	public void setEmpresaSeguimentos(List<EmpresaSeguimento> empresaSeguimentos) {
		this.empresaSeguimentos = empresaSeguimentos;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}