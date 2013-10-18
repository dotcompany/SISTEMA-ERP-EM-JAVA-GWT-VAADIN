package dc.entidade.contabilidade.planoconta;

import java.io.Serializable;
import java.util.Date;
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
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "plano_conta_ref_sped")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PlanoContaRefSpedEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_conta_ref_sped_id_seq")
	@SequenceGenerator(name = "plano_conta_ref_sped_id_seq", sequenceName = "plano_conta_ref_sped_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "cod_cta_ref")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Cod cta ref")
	private String codCtaRef = "";

	@Field
	@Column(name = "descricao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Descrição")
	private String descricao = "";

	@Field
	@Column(name = "orientacoes")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Orientações")
	private String orientacoes = "";

	@Field
	@Column(name = "inicio_validade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Início da validade")
	private Date inicioValidade;

	@Field
	@Column(name = "fim_validade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Fim da validade")
	private Date fimValidade;

	@Field
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Tipo")
	private String tipo = "";

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "planoContaRefSped", fetch = FetchType.LAZY)
	private List<ContaEntity> contaList;

	/**
	 * CONSTRUTOR
	 */

	public PlanoContaRefSpedEntity() {
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

	public String getCodCtaRef() {
		return codCtaRef;
	}

	public void setCodCtaRef(String codCtaRef) {
		this.codCtaRef = (codCtaRef == null ? "" : codCtaRef.toUpperCase());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "" : descricao.toUpperCase());
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = (orientacoes == null ? "" : orientacoes
				.toUpperCase());
	}

	public Date getInicioValidade() {
		return inicioValidade;
	}

	public void setInicioValidade(Date inicioValidade) {
		this.inicioValidade = inicioValidade;
	}

	public Date getFimValidade() {
		return fimValidade;
	}

	public void setFimValidade(Date fimValidade) {
		this.fimValidade = fimValidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public List<ContaEntity> getContaList() {
		return contaList;
	}

	public void setContaList(List<ContaEntity> contaList) {
		this.contaList = contaList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}