package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "nivel_formacao")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NivelFormacaoEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nivel_formacao_id_seq")
	@SequenceGenerator(name = "nivel_formacao_id_seq", sequenceName = "nivel_formacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Lob
	@Type(type = "text")
	@Column(name = "DESCRICAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	@Column(name = "GRAU_INSTRUCAO_CAGED")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer grauInstrucaoCaged;

	@Column(name = "GRAU_INSTRUCAO_SEFIP")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer grauInstrucaoSefip;

	@Column(name = "GRAU_INSTRUCAO_RAIS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer grauInstrucaoRais;

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelFormacao")
	// private List<ColaboradorVO> colaboradorVOList;

	public NivelFormacaoEntity() {

	}

	public NivelFormacaoEntity(Integer id) {
		this.id = id;
	}

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

	public Integer getGrauInstrucaoCaged() {
		return grauInstrucaoCaged;
	}

	public void setGrauInstrucaoCaged(Integer grauInstrucaoCaged) {
		this.grauInstrucaoCaged = grauInstrucaoCaged;
	}

	public Integer getGrauInstrucaoSefip() {
		return grauInstrucaoSefip;
	}

	public void setGrauInstrucaoSefip(Integer grauInstrucaoSefip) {
		this.grauInstrucaoSefip = grauInstrucaoSefip;
	}

	public Integer getGrauInstrucaoRais() {
		return grauInstrucaoRais;
	}

	public void setGrauInstrucaoRais(Integer grauInstrucaoRais) {
		this.grauInstrucaoRais = grauInstrucaoRais;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}