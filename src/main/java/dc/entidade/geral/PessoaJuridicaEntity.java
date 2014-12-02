package dc.entidade.geral;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.CrtEn;
import dc.control.enums.TipoRegimeEn;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "pessoa_juridica")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaJuridicaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_juridica_id_seq")
	@SequenceGenerator(name = "pessoa_juridica_id_seq", sequenceName = "pessoa_juridica_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Column(name = "CNPJ")
	private String cnpj;

	@Field
	@Caption("Fantasia")
	@Column(name = "FANTASIA", length = 150)
	private String fantasia;

	@Column(name = "INSCRICAO_MUNICIPAL")
	private String inscricaoMunicipal;

	@Column(name = "INSCRICAO_ESTADUAL")
	private String inscricaoEstadual;

	@Column(name = "DATA_CONSTITUICAO")
	@Temporal(TemporalType.DATE)
	private Date dataConstituicao;

	@Column(name = "SUFRAMA", length = 9)
	private String suframa;

	@Field
	@Caption("Tipo de regime")
	@Column(name = "tipo_regime")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private TipoRegimeEn tipoRegime;

	@Field
	@Caption("CRT")
	@Column(name = "crt")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private CrtEn crt;

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne()
	@JoinColumn(name = "id_pessoa", insertable = true, updatable = true)
	private PessoaEntity pessoa;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaJuridicaEntity() {

	}

	public PessoaJuridicaEntity(Integer id) {
		this.id = id;
	}

	/**
	 * GETS AND SETS
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Date getDataConstituicao() {
		return dataConstituicao;
	}

	public void setDataConstituicao(Date dataConstituicao) {
		this.dataConstituicao = dataConstituicao;
	}

	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}

	public TipoRegimeEn getTipoRegime() {
		return tipoRegime;
	}

	public void setTipoRegime(TipoRegimeEn tipoRegime) {
		this.tipoRegime = tipoRegime;
	}

	public CrtEn getCrt() {
		return crt;
	}

	public void setCrt(CrtEn crt) {
		this.crt = crt;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}