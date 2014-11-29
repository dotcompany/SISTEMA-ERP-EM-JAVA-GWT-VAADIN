package dc.entidade.geral.produto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "produto_sub_grupo")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SubGrupoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sub_grupo_id_seq")
	@SequenceGenerator(name = "produto_sub_grupo_id_seq", sequenceName = "produto_sub_grupo_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
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

	// @OneToMany(mappedBy = "subgrupoProduto", fetch = FetchType.LAZY)
	// private List<Produto> produtoList;

	/**
	 * CONSTRUTOR
	 */

	public SubGrupoEntity() {

	}

	public SubGrupoEntity(Integer id) {
		this.id = id;
	}

	public SubGrupoEntity(Integer id, int idGrupo) {
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

	// // public List<Produto> getProdutoList() {
	// // return produtoList;
	// // }
	// //
	// // public void setProdutoList(List<Produto> produtoList) {
	// // this.produtoList = produtoList;
	// }

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}