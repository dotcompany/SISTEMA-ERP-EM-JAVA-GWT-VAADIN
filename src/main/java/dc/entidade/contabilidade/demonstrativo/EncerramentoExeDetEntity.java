package dc.entidade.contabilidade.demonstrativo;

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
@Table(name = "CONTABIL_ENCERRAMENTO_EXE_DET")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class EncerramentoExeDetEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_encerramento_exe_det_id_seq")
	@SequenceGenerator(name = "contabil_encerramento_exe_det_id_seq", sequenceName = "contabil_encerramento_exe_det_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "saldo_anterior")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Saldo anterior")
	private Double saldoAnterior = new Double(0.0);

	@Field
	@Column(name = "valor_debito")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Valor do débito")
	private Double valorDebito = new Double(0.0);

	@Field
	@Column(name = "valor_credito")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Valor do crédito")
	private Double valorCredito = new Double(0.0);

	@Field
	@Column(name = "saldo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Saldo")
	private Double saldo = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer,

	// id_contabil_encerramento_exe integer NOT NULL,

	@ManyToOne
	@JoinColumn(name = "id_contabil_encerramento_exe", nullable = false)
	@Caption("Encerramento exe cab")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private EncerramentoExeCabEntity encerramentoExeCab;

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
		return getSaldo().toString();
	}

	// public void setNome(String nome) {
	// setAberturaEncerramento(nome);
	// }

	/**
	 * CONSTRUTOR
	 */

	public EncerramentoExeDetEntity() {
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

	public Double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = (saldoAnterior == null ? new Double(0.0)
				: saldoAnterior);
	}

	public Double getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(Double valorDebito) {
		this.valorDebito = (valorDebito == null ? new Double(0.0) : valorDebito);
	}

	public Double getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(Double valorCredito) {
		this.valorCredito = (valorCredito == null ? new Double(0.0)
				: valorCredito);
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = (saldo == null ? new Double(0.0) : saldo);
	}

	public EncerramentoExeCabEntity getEncerramentoExeCab() {
		return encerramentoExeCab;
	}

	public void setEncerramentoExeCab(
			EncerramentoExeCabEntity encerramentoExeCab) {
		this.encerramentoExeCab = encerramentoExeCab;
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