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
@Table(name = "nfe_detalhe_imposto_cofins")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoCofinsEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_cofins_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_cofins_id_seq", sequenceName = "nfe_detalhe_imposto_cofins_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "cst_cofins")
	@Caption(value = "CST COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstCofins = "";

	@Field
	@Column(name = "quantidade_vendida")
	@Caption(value = "Quantidade vendida")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double quantidadeVendida = new Double(0.0);

	@Field
	@Column(name = "base_calculo_cofins")
	@Caption(value = "Base de cálculo do COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double baseCalculoCofins = new Double(0.0);

	@Field
	@Column(name = "aliquota_cofins_percentual")
	@Caption(value = "Alíquota COFINS percentual")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaCofinsPercentual = new Double(0.0);

	@Field
	@Column(name = "aliquota_cofins_reais")
	@Caption(value = "Alíquota COFINS reais")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaCofinsReais = new Double(0.0);

	@Field
	@Column(name = "valor_cofins")
	@Caption(value = "Valor do COFINS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorCofins = new Double(0.0);

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

	public NfeDetalheImpostoCofinsEntity() {
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

	public String getCstCofins() {
		return cstCofins;
	}

	public void setCstCofins(String cstCofins) {
		this.cstCofins = cstCofins;
	}

	public Double getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(Double quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public Double getBaseCalculoCofins() {
		return baseCalculoCofins;
	}

	public void setBaseCalculoCofins(Double baseCalculoCofins) {
		this.baseCalculoCofins = baseCalculoCofins;
	}

	public Double getAliquotaCofinsPercentual() {
		return aliquotaCofinsPercentual;
	}

	public void setAliquotaCofinsPercentual(Double aliquotaCofinsPercentual) {
		this.aliquotaCofinsPercentual = aliquotaCofinsPercentual;
	}

	public Double getAliquotaCofinsReais() {
		return aliquotaCofinsReais;
	}

	public void setAliquotaCofinsReais(Double aliquotaCofinsReais) {
		this.aliquotaCofinsReais = aliquotaCofinsReais;
	}

	public Double getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(Double valorCofins) {
		this.valorCofins = valorCofins;
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