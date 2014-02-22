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
@Table(name = "nfe_detalhe_imposto_ipi")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoIpiEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_ipi_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_ipi_id_seq", sequenceName = "nfe_detalhe_imposto_ipi_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "enquadramento_ipi")
	@Caption(value = "Enquadramento do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String enquadramentoIpi = "";

	@Field
	@Column(name = "cnpj_produtor")
	@Caption(value = "CNPJ do produtor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpjProdutor = "";

	@Field
	@Column(name = "codigo_selo_ipi")
	@Caption(value = "Código do selo do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoSeloIpi = "";

	@Field
	@Column(name = "quantidade_selo_ipi")
	@Caption(value = "Quantidade de selo do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer quantidadeSeloIpi = new Integer(0);

	@Field
	@Column(name = "enquadramento_legal_ipi")
	@Caption(value = "Enquadramento legal do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String enquadramentoLegalIpi = "";

	@Field
	@Column(name = "cst_ipi")
	@Caption(value = "CST IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstIpi = "";

	@Field
	@Column(name = "valor_base_calculo_ipi")
	@Caption(value = "Valor da base de cálculo do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorBaseCalculoIpi = new Double(0.0);

	@Field
	@Column(name = "Alíquota do IPI")
	@Caption(value = "aliquota_ipi")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaIpi = new Double(0.0);

	@Field
	@Column(name = "quantidade_unidade_tributavel")
	@Caption(value = "Quantidade da unidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double quantidadeUnidadeTributavel = new Double(0.0);

	@Field
	@Column(name = "valor_unidade_tributavel")
	@Caption(value = "Valor da unidade tributável")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorUnidadeTributavel = new Double(0.0);

	@Field
	@Column(name = "valor_ipi")
	@Caption(value = "Valor do IPI")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIpi = new Double(0.0);

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

	public NfeDetalheImpostoIpiEntity() {
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

	public String getEnquadramentoIpi() {
		return enquadramentoIpi;
	}

	public void setEnquadramentoIpi(String enquadramentoIpi) {
		this.enquadramentoIpi = enquadramentoIpi;
	}

	public String getCnpjProdutor() {
		return cnpjProdutor;
	}

	public void setCnpjProdutor(String cnpjProdutor) {
		this.cnpjProdutor = cnpjProdutor;
	}

	public String getCodigoSeloIpi() {
		return codigoSeloIpi;
	}

	public void setCodigoSeloIpi(String codigoSeloIpi) {
		this.codigoSeloIpi = codigoSeloIpi;
	}

	public Integer getQuantidadeSeloIpi() {
		return quantidadeSeloIpi;
	}

	public void setQuantidadeSeloIpi(Integer quantidadeSeloIpi) {
		this.quantidadeSeloIpi = quantidadeSeloIpi;
	}

	public String getEnquadramentoLegalIpi() {
		return enquadramentoLegalIpi;
	}

	public void setEnquadramentoLegalIpi(String enquadramentoLegalIpi) {
		this.enquadramentoLegalIpi = enquadramentoLegalIpi;
	}

	public String getCstIpi() {
		return cstIpi;
	}

	public void setCstIpi(String cstIpi) {
		this.cstIpi = cstIpi;
	}

	public Double getValorBaseCalculoIpi() {
		return valorBaseCalculoIpi;
	}

	public void setValorBaseCalculoIpi(Double valorBaseCalculoIpi) {
		this.valorBaseCalculoIpi = valorBaseCalculoIpi;
	}

	public Double getAliquotaIpi() {
		return aliquotaIpi;
	}

	public void setAliquotaIpi(Double aliquotaIpi) {
		this.aliquotaIpi = aliquotaIpi;
	}

	public Double getQuantidadeUnidadeTributavel() {
		return quantidadeUnidadeTributavel;
	}

	public void setQuantidadeUnidadeTributavel(
			Double quantidadeUnidadeTributavel) {
		this.quantidadeUnidadeTributavel = quantidadeUnidadeTributavel;
	}

	public Double getValorUnidadeTributavel() {
		return valorUnidadeTributavel;
	}

	public void setValorUnidadeTributavel(Double valorUnidadeTributavel) {
		this.valorUnidadeTributavel = valorUnidadeTributavel;
	}

	public Double getValorIpi() {
		return valorIpi;
	}

	public void setValorIpi(Double valorIpi) {
		this.valorIpi = valorIpi;
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