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
@Table(name = "nfe_detalhe_imposto_issqn")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoIssqnEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_issqn_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_issqn_id_seq", sequenceName = "nfe_detalhe_imposto_issqn_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "base_calculo_issqn")
	@Caption(value = "Base do cálculo do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double baseCalculoIssqn = new Double(0.0);

	@Field
	@Column(name = "aliquota_issqn")
	@Caption(value = "Alíquota do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaIssqn = new Double(0.0);

	@Field
	@Column(name = "valor_issqn")
	@Caption(value = "Valor do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIssqn = new Double(0.0);

	@Field
	@Column(name = "municipio_issqn")
	@Caption(value = "Municipio do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIssqn = new Integer(0);

	@Field
	@Column(name = "item_lista_servicos")
	@Caption(value = "Item da lista de serviços")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer itemListaServicos = new Integer(0);

	@Field
	@Column(name = "tributacao_issqn")
	@Caption(value = "Tributação do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String tributacaoIssqn = "";

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

	public NfeDetalheImpostoIssqnEntity() {
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

	public Double getBaseCalculoIssqn() {
		return baseCalculoIssqn;
	}

	public void setBaseCalculoIssqn(Double baseCalculoIssqn) {
		this.baseCalculoIssqn = baseCalculoIssqn;
	}

	public Double getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(Double aliquotaIssqn) {
		this.aliquotaIssqn = aliquotaIssqn;
	}

	public Double getValorIssqn() {
		return valorIssqn;
	}

	public void setValorIssqn(Double valorIssqn) {
		this.valorIssqn = valorIssqn;
	}

	public Integer getMunicipioIssqn() {
		return municipioIssqn;
	}

	public void setMunicipioIssqn(Integer municipioIssqn) {
		this.municipioIssqn = municipioIssqn;
	}

	public Integer getItemListaServicos() {
		return itemListaServicos;
	}

	public void setItemListaServicos(Integer itemListaServicos) {
		this.itemListaServicos = itemListaServicos;
	}

	public String getTributacaoIssqn() {
		return tributacaoIssqn;
	}

	public void setTributacaoIssqn(String tributacaoIssqn) {
		this.tributacaoIssqn = tributacaoIssqn;
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