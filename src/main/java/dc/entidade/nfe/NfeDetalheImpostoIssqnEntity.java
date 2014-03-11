package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	private BigDecimal baseCalculoIssqn = new BigDecimal(0);

	@Field
	@Column(name = "aliquota_issqn")
	@Caption(value = "Alíquota do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal aliquotaIssqn = new BigDecimal(0);

	@Field
	@Column(name = "valor_issqn")
	@Caption(value = "Valor do ISSQN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal valorIssqn = new BigDecimal(0);

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

	@OneToOne
	@JoinColumn(name = "id_nfe_detalhe")
	private NfeDetalheEntity nfeDetalhe;

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

	public BigDecimal getBaseCalculoIssqn() {
		return baseCalculoIssqn;
	}

	public void setBaseCalculoIssqn(BigDecimal baseCalculoIssqn) {
		this.baseCalculoIssqn = (baseCalculoIssqn == null ? new BigDecimal(0)
				: baseCalculoIssqn);
	}

	public BigDecimal getAliquotaIssqn() {
		return aliquotaIssqn;
	}

	public void setAliquotaIssqn(BigDecimal aliquotaIssqn) {
		this.aliquotaIssqn = (aliquotaIssqn == null ? new BigDecimal(0)
				: aliquotaIssqn);
	}

	public BigDecimal getValorIssqn() {
		return valorIssqn;
	}

	public void setValorIssqn(BigDecimal valorIssqn) {
		this.valorIssqn = (valorIssqn == null ? new BigDecimal(0) : valorIssqn);
	}

	public Integer getMunicipioIssqn() {
		return municipioIssqn;
	}

	public void setMunicipioIssqn(Integer municipioIssqn) {
		this.municipioIssqn = (municipioIssqn == null ? new Integer(0)
				: municipioIssqn);
	}

	public Integer getItemListaServicos() {
		return itemListaServicos;
	}

	public void setItemListaServicos(Integer itemListaServicos) {
		this.itemListaServicos = (itemListaServicos == null ? new Integer(0)
				: itemListaServicos);
	}

	public String getTributacaoIssqn() {
		return tributacaoIssqn;
	}

	public void setTributacaoIssqn(String tributacaoIssqn) {
		this.tributacaoIssqn = (tributacaoIssqn == null ? "" : tributacaoIssqn
				.toUpperCase());
	}

	public NfeDetalheEntity getNfeDetalhe() {
		return nfeDetalhe;
	}

	public void setNfeDetalhe(NfeDetalheEntity nfeDetalhe) {
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