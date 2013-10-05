package dc.entidade.folhapagamento.ausencia;

import java.io.Serializable;
import java.util.Date;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.Colaborador;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "folha_afastamento")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AfastamentoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "folha_afastamento_id_seq")
	@SequenceGenerator(name = "folha_afastamento_id_seq", sequenceName = "folha_afastamento_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;

	@Column(name = "data_inicio")
	@Field
	@Caption("Data início")
	private Date dataInicio;

	@Column(name = "data_fim")
	@Field
	@Caption("Data término")
	private Date dataFim;

	@Column(name = "dias_afastado")
	@Field
	@Caption("Dias afastado")
	private Integer diasAfastado = new Integer(0);

	/**
	 * REFERENCIA - FK
	 */

	/*
	 * id_colaborador integer NOT NULL, id_folha_tipo_afastamento integer NOT
	 * NULL,
	 */

	@ManyToOne
	@JoinColumn(name = "id_colaborador", nullable = false)
	@Caption("Colaborador")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private Colaborador colaborador;

	@ManyToOne
	@JoinColumn(name = "id_folha_tipo_afastamento", nullable = false)
	@Caption("Tipo de afastamento")
	@javax.validation.constraints.NotNull(message = "Não pode estar vazio.")
	private TipoAfastamentoEntity tipoAfastamento;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * CONSTRUTOR
	 */

	public AfastamentoEntity() {
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getDiasAfastado() {
		return diasAfastado;
	}

	public void setDiasAfastado(Integer diasAfastado) {
		this.diasAfastado = diasAfastado;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public TipoAfastamentoEntity getTipoAfastamento() {
		return tipoAfastamento;
	}

	public void setTipoAfastamento(TipoAfastamentoEntity tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}