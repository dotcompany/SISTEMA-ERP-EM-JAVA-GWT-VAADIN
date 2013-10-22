package dc.entidade.contabilidade.planoconta;

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
import javax.persistence.OneToMany;
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
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "plano_conta")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PlanoContaEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_conta_id_seq")
	@SequenceGenerator(name = "plano_conta_id_seq", sequenceName = "plano_conta_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption(value = "Nome")
	@Column(name = "nome")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	@Field
	@Caption(value = "Data da inclusão")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inclusao")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataInclusao;

	@Field
	@Caption(value = "Máscara")
	@Column(name = "mascara")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String mascara = "";

	@Field
	@Caption(value = "Níveis")
	@Column(name = "niveis")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer niveis = new Integer(0);

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "planoConta", fetch = FetchType.LAZY)
	private List<ContaEntity> contaList;

	/**
	 * CONSTRUTOR
	 */

	public PlanoContaEntity() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome.toUpperCase());
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
		this.mascara = (mascara == null ? "" : mascara.toUpperCase());
	}

	public Integer getNiveis() {
		return niveis;
	}

	public void setNiveis(Integer niveis) {
		this.niveis = (niveis == null ? new Integer(0) : niveis);
	}

	public List<ContaEntity> getContaList() {
		return contaList;
	}

	public void setContaList(List<ContaEntity> contaList) {
		this.contaList = contaList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}