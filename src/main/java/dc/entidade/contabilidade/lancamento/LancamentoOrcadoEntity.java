package dc.entidade.contabilidade.lancamento;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "CONTABIL_LANCAMENTO_ORCADO")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoOrcadoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_lancamento_orcado_id_seq")
	@SequenceGenerator(name = "contabil_lancamento_orcado_id_seq", sequenceName = "contabil_lancamento_orcado_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "ano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Ano")
	private String ano = "";

	@Field
	@Column(name = "janeiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Janeiro")
	private Double janeiro = new Double(0.0);

	@Field
	@Column(name = "fevereiro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Fevereiro")
	private Double fevereiro = new Double(0.0);

	@Field
	@Column(name = "marco")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Março")
	private Double marco = new Double(0.0);

	@Field
	@Column(name = "abril")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Abril")
	private Double abril = new Double(0.0);

	@Field
	@Column(name = "Maio")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Maio")
	private Double maio = new Double(0.0);

	@Field
	@Column(name = "junho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Junho")
	private Double junho = new Double(0.0);

	@Field
	@Column(name = "julho")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Julho")
	private Double julho = new Double(0.0);

	@Field
	@Column(name = "agosto")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Agosto")
	private Double agosto = new Double(0.0);

	@Field
	@Column(name = "setembro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Setembro")
	private Double setembro = new Double(0.0);

	@Field
	@Column(name = "outubro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Outubro")
	private Double outubro = new Double(0.0);

	@Field
	@Column(name = "novembro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Novembro")
	private Double novembro = new Double(0.0);

	@Field
	@Column(name = "dezembro")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Dezembro")
	private Double dezembro = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_contabil_conta integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_conta", nullable = false)
	@Caption("Conta")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ContaEntity conta;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	@Transient
	@Field
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	public String getNome() {
		return getAno();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public LancamentoOrcadoEntity() {
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

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = (ano == null ? "" : ano.toUpperCase());
	}

	public Double getJaneiro() {
		return janeiro;
	}

	public void setJaneiro(Double janeiro) {
		this.janeiro = (janeiro == null ? new Double(0.0) : janeiro);
	}

	public Double getFevereiro() {
		return fevereiro;
	}

	public void setFevereiro(Double fevereiro) {
		this.fevereiro = (fevereiro == null ? new Double(0.0) : fevereiro);
	}

	public Double getMarco() {
		return marco;
	}

	public void setMarco(Double marco) {
		this.marco = (marco == null ? new Double(0.0) : marco);
	}

	public Double getAbril() {
		return abril;
	}

	public void setAbril(Double abril) {
		this.abril = (abril == null ? new Double(0.0) : abril);
	}

	public Double getMaio() {
		return maio;
	}

	public void setMaio(Double maio) {
		this.maio = (maio == null ? new Double(0.0) : maio);
	}

	public Double getJunho() {
		return junho;
	}

	public void setJunho(Double junho) {
		this.junho = (junho == null ? new Double(0.0) : junho);
	}

	public Double getJulho() {
		return julho;
	}

	public void setJulho(Double julho) {
		this.julho = (julho == null ? new Double(0.0) : julho);
	}

	public Double getAgosto() {
		return agosto;
	}

	public void setAgosto(Double agosto) {
		this.agosto = (agosto == null ? new Double(0.0) : agosto);
	}

	public Double getSetembro() {
		return setembro;
	}

	public void setSetembro(Double setembro) {
		this.setembro = (setembro == null ? new Double(0.0) : setembro);
	}

	public Double getOutubro() {
		return outubro;
	}

	public void setOutubro(Double outubro) {
		this.outubro = (outubro == null ? new Double(0.0) : outubro);
	}

	public Double getNovembro() {
		return novembro;
	}

	public void setNovembro(Double novembro) {
		this.novembro = (novembro == null ? new Double(0.0) : novembro);
	}

	public Double getDezembro() {
		return dezembro;
	}

	public void setDezembro(Double dezembro) {
		this.dezembro = (dezembro == null ? new Double(0.0) : dezembro);
	}

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}