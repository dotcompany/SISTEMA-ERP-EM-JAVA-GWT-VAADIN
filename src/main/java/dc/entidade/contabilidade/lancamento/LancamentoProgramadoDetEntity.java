package dc.entidade.contabilidade.lancamento;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "CONTABIL_LANCA_PROGRAMADO_DET")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoProgramadoDetEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "HISTORICO")
	private String descricaoHistorico = "";

	@Column(name = "VALOR")
	private BigDecimal valor = new BigDecimal(0);

	@Column(name = "TIPO")
	private String tipo = "";

	/**
	 * REFERENCIA - FK
	 */

	@ManyToOne
	@JoinColumn(name = "id_lanca_programado_cab", nullable = false)
	@Caption("Lançamento programado cab")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private LancamentoProgramadoCabEntity lancamentoProgramadoCab;

	@ManyToOne
	@JoinColumn(name = "id_contabil_conta", nullable = false)
	@Caption("Conta")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private ContaEntity conta;

	@ManyToOne
	@JoinColumn(name = "id_contabil_historico", nullable = false)
	@Caption("Histórico")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private HistoricoEntity historico;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public LancamentoProgramadoDetEntity() {
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

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = (descricaoHistorico == null ? ""
				: descricaoHistorico.toUpperCase());
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = (valor == null ? new BigDecimal(0) : valor);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public LancamentoProgramadoCabEntity getLancamentoProgramadoCab() {
		return lancamentoProgramadoCab;
	}

	public void setLancamentoProgramadoCab(
			LancamentoProgramadoCabEntity lancamentoProgramadoCab) {
		this.lancamentoProgramadoCab = lancamentoProgramadoCab;
	}

	public ContaEntity getConta() {
		return conta;
	}

	public void setConta(ContaEntity conta) {
		this.conta = conta;
	}

	public HistoricoEntity getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoEntity historico) {
		this.historico = historico;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}