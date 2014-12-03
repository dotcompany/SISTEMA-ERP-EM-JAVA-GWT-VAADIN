package dc.entidade.geral.diverso;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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
import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "operadora_plano_saude")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class OperadoraPlanoSaudeEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operadora_plano_saude_id_seq")
	@SequenceGenerator(name = "operadora_plano_saude_id_seq", sequenceName = "operadora_plano_saude_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Registro ANS")
	@Column(name = "REGISTRO_ANS", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String registroAns;

	/*
	 * @ManyToOne(optional = false)
	 * 
	 * @JoinColumn(name = "CONTABIL_CONTA", referencedColumnName = "ID") private
	 * ContabilConta contabilConta;
	 */

	/**
	 * ********************************************************
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	@OneToMany(mappedBy = "operadoraPlanoSaude", fetch = FetchType.LAZY)
	private List<PlanoSaudeEntity> planoSaudeList;

	/**
	 * ********************************************************
	 */

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

	public OperadoraPlanoSaudeEntity() {

	}

	public OperadoraPlanoSaudeEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public OperadoraPlanoSaudeEntity(Integer id) {
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
		this.nome = nome;
	}

	public String getRegistroAns() {
		return registroAns;
	}

	public void setRegistroAns(String registroAns) {
		this.registroAns = registroAns;
	}

	/*
	 * public ContabilConta getContabilConta() { return contabilConta; }
	 * 
	 * public void setContabilConta(ContabilConta contabilConta) {
	 * this.contabilConta = contabilConta; }
	 */

	public List<PlanoSaudeEntity> getPlanoSaudeList() {
		return planoSaudeList;
	}

	public void setPlanoSaudeList(List<PlanoSaudeEntity> planoSaudeList) {
		this.planoSaudeList = planoSaudeList;
	}

	/*
	 * @Override public int hashCode() { return
	 * HashCodeBuilder.reflectionHashCode(this, new String[] {"id"}); }
	 * 
	 * @Override public boolean equals(Object object) { if (object instanceof
	 * OperadoraPlanoSaude == false) return false; if (this == object) return
	 * true; final OperadoraPlanoSaude other = (OperadoraPlanoSaude) object;
	 * return EqualsBuilder.reflectionEquals(this, other); }
	 */

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}