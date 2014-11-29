package dc.entidade.tabelas;

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
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.UfEntity;


/**
 * 
 * @author Wesley Jr /*
 */

@Entity
@Table(name = "feriados")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Feriados extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feriados_id_seq")
	@SequenceGenerator(name = "feriados_id_seq", sequenceName = "feriados_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	
	@Field
	@Caption("Ano")
	@Column(name = "Ano")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String ano;
	
	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;
	
	@Column(name = "ABRANGENCIA")
	@Analyzer(definition = "dc_combo_analyzer")
	private String abrangencia;
	
	//@ManyToOne(optional = false)
	//@JoinColumn(name = "UF", referencedColumnName = "ID")
	@Column(name = "UF")
	private UfEntity uf;
	
	@Field
	@Caption("Municipio Ibge")
	@Column(name = "MUNICIPIO_IBGE", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer municipioIbge;

	
	@Column(name = "TIPO")
	@Analyzer(definition = "dc_combo_analyzer")
	private String tipo;
	
	@Column(name = "DATA_FERIADO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date data;
	
	public Feriados() {

	}

	public Feriados(Integer id) {
		this.id = id;
	}

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
		this.ano = ano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
	}

	public UfEntity getUf() {
		return uf;
	}

	public void setUf(UfEntity uf) {
		this.uf = uf;
	}

	public Integer getMunicipioIbge() {
		return municipioIbge;
	}

	public void setMunicipioIbge(Integer municipioIbge) {
		this.municipioIbge = municipioIbge;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
