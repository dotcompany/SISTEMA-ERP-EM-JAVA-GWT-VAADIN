package dc.entidade.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
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
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "nfe_det_especifico_medicamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class NfeDetEspecificoMedicamentoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfe_det_especifico_medicamento_id_seq")
	@SequenceGenerator(name = "nfe_det_especifico_medicamento_id_seq", sequenceName = "nfe_det_especifico_medicamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "numero_lote")
	@Caption(value = "Número do lote")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numeroLote = "";

	@Field
	@Column(name = "quantidade_lote")
	@Caption(value = "Quantidade do lote")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadeLote = new BigDecimal(0);

	@Field
	@Column(name = "data_fabricacao")
	@Caption(value = "Data da fabricação")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataFabricacao;

	@Field
	@Column(name = "data_validade")
	@Caption(value = "Data da validade")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataValidade;

	@Field
	@Column(name = "preco_maximo_consumidor")
	@Caption(value = "Preço máximo ao consumidor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal precoMaximoConsumidor = new BigDecimal(0);

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

	public NfeDetEspecificoMedicamentoEntity() {
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

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public BigDecimal getQuantidadeLote() {
		return quantidadeLote;
	}

	public void setQuantidadeLote(BigDecimal quantidadeLote) {
		this.quantidadeLote = quantidadeLote;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public BigDecimal getPrecoMaximoConsumidor() {
		return precoMaximoConsumidor;
	}

	public void setPrecoMaximoConsumidor(BigDecimal precoMaximoConsumidor) {
		this.precoMaximoConsumidor = precoMaximoConsumidor;
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