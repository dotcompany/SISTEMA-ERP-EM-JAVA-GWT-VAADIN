package dc.entidade.geral.tabela;

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
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "efd_tabela_435")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class EfdTabela435 extends AbstractMultiEmpresaModel<Integer> {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "efd")
	@SequenceGenerator(name = "efd", sequenceName = "efd_tabela_435_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;
	
	String codigo;
	
	String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
