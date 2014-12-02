package dc.entidade.geral.tabela;

import java.io.Serializable;

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
 * @author Wesley Jr /*
 */

@Entity
@Table(name = "cst_pis")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CstPisEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cst_pis_id_seq")
	@SequenceGenerator(name = "cst_pis_id_seq", sequenceName = "cst_pis_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Codigo")
	@Column(name = "Codigo")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigo;

	@Field
	@Caption("Descricao")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Column(name = "DESCRICAO", length = 250)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	public CstPisEntity() {

	}

	public CstPisEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}