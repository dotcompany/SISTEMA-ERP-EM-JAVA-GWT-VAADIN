package dc.entidade.geral.produto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import dc.control.enums.SimNaoEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "unidade_produto")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class UnidadeProdutoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_produto_id_seq")
	@SequenceGenerator(name = "unidade_produto_id_seq", sequenceName = "unidade_produto_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Sigla")
	@Column(name = "SIGLA", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla;

	@Lob
	@Field
	@Caption("Nome")
	@Type(type = "text")
	@Column(name = "DESCRICAO", length = 65535)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Pode fracionar?")
	@Column(name = "PODE_FRACIONAR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private SimNaoEn podeFracionar;

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "unidadeProduto", fetch = FetchType.EAGER)
	// private List<Produto> produtoList;

	/**
	 * CONSTRUTOR
	 */

	public UnidadeProdutoEntity() {

	}

	public UnidadeProdutoEntity(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public SimNaoEn getPodeFracionar() {
		return podeFracionar;
	}

	public void setPodeFracionar(SimNaoEn podeFracionar) {
		this.podeFracionar = podeFracionar;
	}

	// public List<Produto> getProdutoList() {
	// return produtoList;
	// }
	//
	// public void setProdutoList(List<Produto> produtoList) {
	// this.produtoList = produtoList;
	// }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}