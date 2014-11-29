package dc.entidade.folhapagamento;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.pessoal.ColaboradorEntity;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Entity
@Table(name = "vendedor")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class VendedorEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vnd")
	@SequenceGenerator(name = "vnd", sequenceName = "vendedor_id_seq", allocationSize = 1)
	private Integer id;

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FOLHAPAGAMENTO
	 */

	// @OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY)
	// private List<LancamentoComissaoEntity> lancamentoComissaoList;

	@ManyToOne
	@JoinColumn(name = "id_colaborador")
	private ColaboradorEntity colaborador;

	/**
	 * CONSTRUTOR
	 */

	public VendedorEntity() {
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

	// public List<LancamentoComissaoEntity> getLancamentoComissaoList() {
	// return lancamentoComissaoList;
	// }
	//
	// public void setLancamentoComissaoList(
	// List<LancamentoComissaoEntity> lancamentoComissaoList) {
	// this.lancamentoComissaoList = lancamentoComissaoList;
	// }

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return colaborador.getPessoa().getNome();
	}

}