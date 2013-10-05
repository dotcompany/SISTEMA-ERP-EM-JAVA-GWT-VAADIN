package dc.entidade.folhapagamento.cadastro;

import java.io.Serializable;
import java.util.Date;

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

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "guias_acumuladas")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class GuiaAcumuladaEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guias_acumuladas_id_seq")
	@SequenceGenerator(name = "guias_acumuladas_id_seq", sequenceName = "guias_acumuladas_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Field
	@Caption("Gps tipo")
	@Column(name = "gps_tipo", insertable = false, updatable = false)
	private String gpsTipo = "";

	@Field
	@Caption("Gps competência")
	@Column(name = "gps_competencia")
	private String gpsCompetencia = "";

	@Field
	@Caption("Gps valor INSS")
	@Column(name = "gps_valor_inss")
	private Double gpsValorInss = new Double(0.0);

	@Field
	@Caption("Gps valor outras ent")
	@Column(name = "gps_valor_outras_ent")
	private Double gpsValorOutrasEnt = new Double(0.0);

	@Field
	@Caption("Gps data pagamento")
	@Column(name = "gps_data_pagamento")
	private Date gpsDataPagamento;

	@Field
	@Caption("IRRF competência")
	@Column(name = "irrf_competencia")
	private String irrfCompetencia = "";

	@Field
	@Caption("IRRF código recolhimento")
	@Column(name = "irrf_codigo_recolhimento")
	private Integer irrfCodigoRecolhimento = new Integer(0);

	@Field
	@Caption("IRRF valor acumulado")
	@Column(name = "irrf_valor_acumulado")
	private Double irrfValorAcumulado = new Double(0.0);

	@Field
	@Caption("IRRF data pagamento")
	@Column(name = "irrf_data_pagamento")
	private Date irrfDataPagamento;

	@Field
	@Caption("PIS competência")
	@Column(name = "pis_competencia")
	private String pisCompetencia = "";

	@Field
	@Caption("PIS valor acumulado")
	@Column(name = "pis_valor_acumulado")
	private Double pisValorAcumulado = new Double(0.0);

	@Field
	@Caption("Gps tipo")
	@Column(name = "gps_tipo")
	private Date pisDataPagamento;

	/**
	 * REFERENCIA - FK
	 */

	/* id_empresa integer NOT NULL, */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id_empresa", nullable = false)
	 * 
	 * @Caption("Empresa")
	 * 
	 * @javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	 * private Empresa empresa;
	 */

	/**
	 * LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public GuiaAcumuladaEntity() {
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

	public String getGpsTipo() {
		return gpsTipo;
	}

	public void setGpsTipo(String gpsTipo) {
		this.gpsTipo = (gpsTipo == null ? "" : gpsTipo.toUpperCase());
	}

	public String getGpsCompetencia() {
		return gpsCompetencia;
	}

	public void setGpsCompetencia(String gpsCompetencia) {
		this.gpsCompetencia = (gpsCompetencia == null ? "" : gpsCompetencia
				.toUpperCase());
	}

	public Double getGpsValorInss() {
		return gpsValorInss;
	}

	public void setGpsValorInss(Double gpsValorInss) {
		this.gpsValorInss = (gpsValorInss == null ? new Double(0.0)
				: gpsValorInss);
	}

	public Double getGpsValorOutrasEnt() {
		return gpsValorOutrasEnt;
	}

	public void setGpsValorOutrasEnt(Double gpsValorOutrasEnt) {
		this.gpsValorOutrasEnt = (gpsValorOutrasEnt == null ? new Double(0.0)
				: gpsValorOutrasEnt);
	}

	public Date getGpsDataPagamento() {
		return gpsDataPagamento;
	}

	public void setGpsDataPagamento(Date gpsDataPagamento) {
		this.gpsDataPagamento = gpsDataPagamento;
	}

	public String getIrrfCompetencia() {
		return irrfCompetencia;
	}

	public void setIrrfCompetencia(String irrfCompetencia) {
		this.irrfCompetencia = (irrfCompetencia == null ? "" : irrfCompetencia
				.toUpperCase());
	}

	public Integer getIrrfCodigoRecolhimento() {
		return irrfCodigoRecolhimento;
	}

	public void setIrrfCodigoRecolhimento(Integer irrfCodigoRecolhimento) {
		this.irrfCodigoRecolhimento = (irrfCodigoRecolhimento == null ? new Integer(
				0) : irrfCodigoRecolhimento);
	}

	public Double getIrrfValorAcumulado() {
		return irrfValorAcumulado;
	}

	public void setIrrfValorAcumulado(Double irrfValorAcumulado) {
		this.irrfValorAcumulado = (irrfValorAcumulado == null ? new Double(0.0)
				: irrfValorAcumulado);
	}

	public Date getIrrfDataPagamento() {
		return irrfDataPagamento;
	}

	public void setIrrfDataPagamento(Date irrfDataPagamento) {
		this.irrfDataPagamento = irrfDataPagamento;
	}

	public String getPisCompetencia() {
		return pisCompetencia;
	}

	public void setPisCompetencia(String pisCompetencia) {
		this.pisCompetencia = (pisCompetencia == null ? "" : pisCompetencia
				.toUpperCase());
	}

	public Double getPisValorAcumulado() {
		return pisValorAcumulado;
	}

	public void setPisValorAcumulado(Double pisValorAcumulado) {
		this.pisValorAcumulado = (pisValorAcumulado == null ? new Double(0.0)
				: pisValorAcumulado);
	}

	public Date getPisDataPagamento() {
		return pisDataPagamento;
	}

	public void setPisDataPagamento(Date pisDataPagamento) {
		this.pisDataPagamento = pisDataPagamento;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}