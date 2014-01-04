package dc.entidade.produto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/**
 * 
 * @author Wesley Jr /* Classe que possui o TO, ou seja, o mapeamento com todos
 *         os campos que vamos ter no nosso Banco de Dados Nessa classe temos o
 *         equals, hashCode e o ToString, no nosso novo mapeamento, pegamos e
 *         mudamos, está diferente do mapeamento do T2Ti. * Colocamos também
 *         algumas anotações, na classe e em alguns campos, onde temos as
 *         anotações que é o Field e Caption, o Caption colocamos o nome do
 *         campo que queremos que pesquise na Tela, pegando os dados que estão
 *         salvos no Banco de Dados.
 */

@Entity
@Table(name = "produto_sub_grupo")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SubGrupoProduto extends AbstractMultiEmpresaModel<Integer>  implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "ID_GRUPO", nullable = false)
	private int idGrupo;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 20)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Lob
	@Type(type = "text")
	@Column(name = "DESCRICAO", length = 65535)
	private String descricao;

	/**
	 * REFERENCIA - LIST
	 */

//	@OneToMany(mappedBy = "subgrupoProduto", fetch = FetchType.LAZY)
//	private List<Produto> produtoList;

	/**
	 * CONSTRUTOR
	 */

	public SubGrupoProduto() {

	}

	public SubGrupoProduto(Integer id) {
		this.id = id;
	}

	public SubGrupoProduto(Integer id, int idGrupo) {
		this.id = id;
		this.idGrupo = idGrupo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

////	public List<Produto> getProdutoList() {
////		return produtoList;
////	}
////
////	public void setProdutoList(List<Produto> produtoList) {
////		this.produtoList = produtoList;
//	}

	@Override
	public String toString() {
		return nome;
	}

}