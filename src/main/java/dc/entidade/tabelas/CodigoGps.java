package dc.entidade.tabelas;

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

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
 * 
 * @author Wesley Jr
 *
 */

@Entity
@Table(name = "codigo_gps")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class CodigoGps extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigo_gps_id_seq")
	@SequenceGenerator(name = "codigo_gps_id_seq", sequenceName = "codigo_gps_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;
	
	@Field
	@Caption("Codigo")
	@Column(name="CODIGO")
	private Integer codigo;
	
	@Field
	@Caption("Descricao")
	@Column(name="DESCRICAO")
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
