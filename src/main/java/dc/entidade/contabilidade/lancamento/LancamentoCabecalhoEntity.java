package dc.entidade.contabilidade.lancamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

@Entity(name = "contabilidadeLancamentoCabecalhoEntity")
@Table(name = "contabil_lancamento_cabecalho")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class LancamentoCabecalhoEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contabil_lancamento_cabecalho_id_seq")
	@SequenceGenerator(name = "contabil_lancamento_cabecalho_id_seq", sequenceName = "contabil_lancamento_cabecalho_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "data_lancamento")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Data do lançamento")
	private Date dataLancamento;

	@Field
	@Column(name = "data_inclusao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Data da inclusão")
	private Date dataInclusao;

	@Field
	@Column(name = "tipo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Tipo")
	private String tipo = "";

	@Field
	@Column(name = "liberado")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Liberado")
	private String liberado = "";

	@Field
	@Column(name = "valor")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Caption(value = "Valor")
	private Double valor = new Double(0.0);

	/**
	 * REFERENCIA - FK
	 */

	// id_empresa integer NOT NULL,

	// id_contabil_lote integer,

	@ManyToOne
	@JoinColumn(name = "id_contabil_lote", nullable = false)
	@Caption("Lote")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private LoteEntity lote;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "conta", fetch = FetchType.LAZY)
	private List<LancamentoDetalheEntity> lancamentoDetalheList;

	/**
	 * CONSTRUTOR
	 */

	public LancamentoCabecalhoEntity() {
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

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = (tipo == null ? "" : tipo.toUpperCase());
	}

	public String getLiberado() {
		return liberado;
	}

	public void setLiberado(String liberado) {
		this.liberado = (liberado == null ? "" : liberado.toUpperCase());
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = (valor == null ? new Double(0.0) : valor);
	}

	public LoteEntity getLote() {
		return lote;
	}

	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}

	public List<LancamentoDetalheEntity> getLancamentoDetalheList() {
		return lancamentoDetalheList;
	}

	public void setLancamentoDetalheList(
			List<LancamentoDetalheEntity> lancamentoDetalheList) {
		this.lancamentoDetalheList = lancamentoDetalheList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}