package dc.entidade.contabilidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "PLANO_CONTA")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PlanoConta extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Caption(value = "Nome")
	@Column(name = "NOME")
	@ComboValue
	@Field
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Caption(value = "Data Inclusão")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INCLUSAO")
	@Field
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataInclusao;

	@Caption(value = "Máscara")
	@Column(name = "MASCARA")
	@Field
	@Analyzer(definition = "dc_combo_analyzer")
	private String mascara;

	@Caption(value = "Níveis")
	@Column(name = "NIVEIS")
	@Field
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer niveis;

	public PlanoConta() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public Integer getNiveis() {
		return niveis;
	}

	public void setNiveis(Integer niveis) {
		this.niveis = niveis;
	}

	@Override
	public String toString() {
		return "PlanoConta[id=" + id + "]";
	}

}
