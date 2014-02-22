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
@Table(name = "nfe_detalhe_imposto_icms")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetalheImpostoIcmsEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_detalhe_imposto_icms_id_seq")
	@SequenceGenerator(name = "nfe_detalhe_imposto_icms_id_seq", sequenceName = "nfe_detalhe_imposto_icms_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "origem_mercadoria")
	@Caption(value = "Origem da mercadoria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String origemMercadoria = "";

	@Field
	@Column(name = "cst_icms")
	@Caption(value = "CST ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cstIcms = "";

	@Field
	@Column(name = "csosn")
	@Caption(value = "CSOSN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String csosn = "";

	@Field
	@Column(name = "modalidade_bc_icms")
	@Caption(value = "Modalidade BC ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String modalidadeBcIcms = "";

	@Field
	@Column(name = "taxa_reducao_bc_icms")
	@Caption(value = "Taxa de redução BC ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double taxaReducaoBcIcms = new Double(0.0);

	@Field
	@Column(name = "base_calculo_icms")
	@Caption(value = "Base de cálculo do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double baseCalculoIcms = new Double(0.0);

	@Field
	@Column(name = "aliquota_icms")
	@Caption(value = "Alíquota do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaIcms = new Double(0.0);

	@Field
	@Column(name = "valor_icms")
	@Caption(value = "Valor do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIcms = new Double(0.0);

	@Field
	@Column(name = "motivo_desoneracao_icms")
	@Caption(value = "Motivo da desoneração do ICMS")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String motivoDesoneracaoIcms = "";

	@Field
	@Column(name = "modalidade_bc_icms_st")
	@Caption(value = "Modalidade BC ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String modalidadeBcIcmsSt = "";

	@Field
	@Column(name = "percentual_mva_icms_st")
	@Caption(value = "Percentual MVA ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double percentualMvaIcmsSt = new Double(0.0);

	@Field
	@Column(name = "percentual_reducao_bc_icms_st")
	@Caption(value = "Percentual de redução BC ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double percentualReducaoBcIcmsSt = new Double(0.0);

	@Field
	@Column(name = "valor_base_calculo_icms_st")
	@Caption(value = "Valor da base de cálculo ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorBaseCalculoIcmsSt = new Double(0.0);

	@Field
	@Column(name = "aliquota_icms_st")
	@Caption(value = "Alíquota ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaIcmsSt = new Double(0.0);

	@Field
	@Column(name = "valor_icms_st")
	@Caption(value = "Valor ICMS ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIcmsSt = new Double(0.0);

	@Field
	@Column(name = "valor_bc_icms_st_retido")
	@Caption(value = "Valor BC ICMS ST retido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorBcIcmsStRetido = new Double(0.0);

	@Field
	@Column(name = "valor_icms_st_retido")
	@Caption(value = "Valor ICMS ST retido")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIcmsStRetido = new Double(0.0);

	@Field
	@Column(name = "valor_bc_icms_st_destino")
	@Caption(value = "Valor BC ICMS ST destino")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorBcIcmsStDestino = new Double(0.0);

	@Field
	@Column(name = "valor_icms_st_destino")
	@Caption(value = "Valor ICMS ST destino")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorIcmsStDestino = new Double(0.0);

	@Field
	@Column(name = "aliquota_credito_icms_sn")
	@Caption(value = "Alíquota do crédito ICMS SN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double aliquotaCreditoIcmsSn = new Double(0.0);

	@Field
	@Column(name = "valor_credito_icms_sn")
	@Caption(value = "Valor do crédito ICMS SN")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double valorCreditoIcmsSn = new Double(0.0);

	@Field
	@Column(name = "percentual_bc_operacao_propria")
	@Caption(value = "Percentual BC operação própria")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Double percentualBcOperacaoPropria = new Double(0.0);

	@Field
	@Column(name = "uf_st")
	@Caption(value = "UF ST")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ufSt = "";

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

	public NfeDetalheImpostoIcmsEntity() {
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

	public String getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(String origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public String getCstIcms() {
		return cstIcms;
	}

	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}

	public String getCsosn() {
		return csosn;
	}

	public void setCsosn(String csosn) {
		this.csosn = csosn;
	}

	public String getModalidadeBcIcms() {
		return modalidadeBcIcms;
	}

	public void setModalidadeBcIcms(String modalidadeBcIcms) {
		this.modalidadeBcIcms = modalidadeBcIcms;
	}

	public Double getTaxaReducaoBcIcms() {
		return taxaReducaoBcIcms;
	}

	public void setTaxaReducaoBcIcms(Double taxaReducaoBcIcms) {
		this.taxaReducaoBcIcms = taxaReducaoBcIcms;
	}

	public Double getBaseCalculoIcms() {
		return baseCalculoIcms;
	}

	public void setBaseCalculoIcms(Double baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}

	public Double getAliquotaIcms() {
		return aliquotaIcms;
	}

	public void setAliquotaIcms(Double aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public Double getValorIcms() {
		return valorIcms;
	}

	public void setValorIcms(Double valorIcms) {
		this.valorIcms = valorIcms;
	}

	public String getMotivoDesoneracaoIcms() {
		return motivoDesoneracaoIcms;
	}

	public void setMotivoDesoneracaoIcms(String motivoDesoneracaoIcms) {
		this.motivoDesoneracaoIcms = motivoDesoneracaoIcms;
	}

	public String getModalidadeBcIcmsSt() {
		return modalidadeBcIcmsSt;
	}

	public void setModalidadeBcIcmsSt(String modalidadeBcIcmsSt) {
		this.modalidadeBcIcmsSt = modalidadeBcIcmsSt;
	}

	public Double getPercentualMvaIcmsSt() {
		return percentualMvaIcmsSt;
	}

	public void setPercentualMvaIcmsSt(Double percentualMvaIcmsSt) {
		this.percentualMvaIcmsSt = percentualMvaIcmsSt;
	}

	public Double getPercentualReducaoBcIcmsSt() {
		return percentualReducaoBcIcmsSt;
	}

	public void setPercentualReducaoBcIcmsSt(Double percentualReducaoBcIcmsSt) {
		this.percentualReducaoBcIcmsSt = percentualReducaoBcIcmsSt;
	}

	public Double getValorBaseCalculoIcmsSt() {
		return valorBaseCalculoIcmsSt;
	}

	public void setValorBaseCalculoIcmsSt(Double valorBaseCalculoIcmsSt) {
		this.valorBaseCalculoIcmsSt = valorBaseCalculoIcmsSt;
	}

	public Double getAliquotaIcmsSt() {
		return aliquotaIcmsSt;
	}

	public void setAliquotaIcmsSt(Double aliquotaIcmsSt) {
		this.aliquotaIcmsSt = aliquotaIcmsSt;
	}

	public Double getValorIcmsSt() {
		return valorIcmsSt;
	}

	public void setValorIcmsSt(Double valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}

	public Double getValorBcIcmsStRetido() {
		return valorBcIcmsStRetido;
	}

	public void setValorBcIcmsStRetido(Double valorBcIcmsStRetido) {
		this.valorBcIcmsStRetido = valorBcIcmsStRetido;
	}

	public Double getValorIcmsStRetido() {
		return valorIcmsStRetido;
	}

	public void setValorIcmsStRetido(Double valorIcmsStRetido) {
		this.valorIcmsStRetido = valorIcmsStRetido;
	}

	public Double getValorBcIcmsStDestino() {
		return valorBcIcmsStDestino;
	}

	public void setValorBcIcmsStDestino(Double valorBcIcmsStDestino) {
		this.valorBcIcmsStDestino = valorBcIcmsStDestino;
	}

	public Double getValorIcmsStDestino() {
		return valorIcmsStDestino;
	}

	public void setValorIcmsStDestino(Double valorIcmsStDestino) {
		this.valorIcmsStDestino = valorIcmsStDestino;
	}

	public Double getAliquotaCreditoIcmsSn() {
		return aliquotaCreditoIcmsSn;
	}

	public void setAliquotaCreditoIcmsSn(Double aliquotaCreditoIcmsSn) {
		this.aliquotaCreditoIcmsSn = aliquotaCreditoIcmsSn;
	}

	public Double getValorCreditoIcmsSn() {
		return valorCreditoIcmsSn;
	}

	public void setValorCreditoIcmsSn(Double valorCreditoIcmsSn) {
		this.valorCreditoIcmsSn = valorCreditoIcmsSn;
	}

	public Double getPercentualBcOperacaoPropria() {
		return percentualBcOperacaoPropria;
	}

	public void setPercentualBcOperacaoPropria(
			Double percentualBcOperacaoPropria) {
		this.percentualBcOperacaoPropria = percentualBcOperacaoPropria;
	}

	public String getUfSt() {
		return ufSt;
	}

	public void setUfSt(String ufSt) {
		this.ufSt = ufSt;
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