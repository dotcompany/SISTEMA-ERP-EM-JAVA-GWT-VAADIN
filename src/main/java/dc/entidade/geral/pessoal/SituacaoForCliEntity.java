package dc.entidade.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
@Table(name = "situacao_for_cli")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SituacaoForCliEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "situacao_for_cli_id_seq")
	@SequenceGenerator(name = "situacao_for_cli_id_seq", sequenceName = "situacao_for_cli_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Column(name = "NOME")
	@Caption("Nome")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Lob
	@Type(type = "text")
	// @Basic(fetch = javax.persistence.FetchType.LAZY)
	@Field
	@Column(name = "DESCRICAO")
	@Caption("Descrição")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String descricao;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "situacaoForCli", fetch = FetchType.LAZY)
	private List<ClienteEntity> clienteList;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public SituacaoForCliEntity() {

	}

	public SituacaoForCliEntity(Integer id) {
		this.id = id;
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
		this.nome = (nome == null ? "".trim() : nome.toUpperCase().trim());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = (descricao == null ? "".trim() : descricao
				.toUpperCase().trim());
	}

	public List<ClienteEntity> getClienteList() {
		return clienteList;
	}

	public void setClienteList(List<ClienteEntity> clienteList) {
		this.clienteList = clienteList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}