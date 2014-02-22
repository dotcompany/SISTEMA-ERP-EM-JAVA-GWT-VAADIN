package dc.entidade.nfe;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "nfe_detalhe_imposto_ii")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoIiEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_ii_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_ii_id_seq", sequenceName = "nfe_detalhe_imposto_ii_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "valor_bc_ii")
	@Caption(value = "Valor (BC)")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorBcIi = new Double(0.0);

	@Field
	@Column(name = "valor_despesas_aduaneiras")
	@Caption(value = "Valor das despesas aduaneiras")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorDespesasAduaneiras = new Double(0.0);

	@Field
	@Column(name = "valor_imposto_importacao")
	@Caption(value = "Valor do imposto de importação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorImpostoImportacao = new Double(0.0);

	@Field
	@Column(name = "valor_iof")
	@Caption(value = "Valor IOF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIof = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Column(name = "id_nfe_detalhe")
	@Caption(value = "NFE detalhe")
	private Integer nfeDetalhe;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public NfeDetalheImpostoIiEntity() {
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

	public Double getValorBcIi() {
		return valorBcIi;
	}

	public void setValorBcIi(Double valorBcIi) {
		this.valorBcIi = valorBcIi;
	}

	public Double getValorDespesasAduaneiras() {
		return valorDespesasAduaneiras;
	}

	public void setValorDespesasAduaneiras(Double valorDespesasAduaneiras) {
		this.valorDespesasAduaneiras = valorDespesasAduaneiras;
	}

	public Double getValorImpostoImportacao() {
		return valorImpostoImportacao;
	}

	public void setValorImpostoImportacao(Double valorImpostoImportacao) {
		this.valorImpostoImportacao = valorImpostoImportacao;
	}

	public Double getValorIof() {
		return valorIof;
	}

	public void setValorIof(Double valorIof) {
		this.valorIof = valorIof;
	}

	public Integer getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(Integer nfeDetalhe) {
		this.nfeDetalhe = nfeDetalhe;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}