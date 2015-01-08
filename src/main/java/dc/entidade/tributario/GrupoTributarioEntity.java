package dc.entidade.tributario;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.produto.ProdutoEntity;

@Entity
@Table(name = "tribut_grupo_tributario")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class GrupoTributarioEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tribut_grupo_tributario_id_seq")
	@SequenceGenerator(name = "tribut_grupo_tributario_id_seq", sequenceName = "tribut_grupo_tributario_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "descricao")
	@Caption("Descrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Column(name = "ORIGEM_MERCADORIA")
	private String origemMercadoria;

	private String observacao;

	// @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	// @ManyToOne(optional = false)
	// private Empresa empresa;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "grupoTributario", fetch = FetchType.LAZY)
	private List<ProdutoEntity> produtoList;

	/**
	 * TRANSIENT
	 */

	@Transient
	@Caption("Origem da Mercadoria")
	private String origemString;

	/**
	 * CONSTRUTOR
	 */

	public GrupoTributarioEntity() {

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
		this.nome = nome;
	}

	public String getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(String origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ProdutoEntity> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<ProdutoEntity> produtoList) {
		this.produtoList = produtoList;
	}

	public String getOrigemString() {
		switch (new Integer(this.origemMercadoria)) {
		case 0:
			this.origemString = "NACIONAL";

			break;
		case 1:
			this.origemString = "ESTRANGEIRA";

			break;
		default:
			this.origemString = " ";

			break;
		}

		return this.origemString;
	}

	public void setOrigemString(String origemString) {
		this.origemString = origemString;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}