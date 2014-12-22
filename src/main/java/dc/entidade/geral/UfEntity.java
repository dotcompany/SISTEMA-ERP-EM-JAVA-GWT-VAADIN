package dc.entidade.geral;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.diverso.PaisEntity;

@Entity
@Table(name = "uf")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class UfEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uf_id_seq")
	@SequenceGenerator(name = "uf_id_seq", sequenceName = "uf_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 50)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Sigla")
	@Column(name = "SIGLA", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String sigla;

	@Field
	@Caption()
	@Column(name = "CODIGO_IBGE", nullable = false)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoIbge;

	/**
	 * REFERENCIA - FK
	 */

	@Caption("País")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pais", nullable = false)
	private PaisEntity pais;

	/**
	 * REFERENCIA - LIST
	 */

	// @OneToMany(mappedBy = "uf", fetch = FetchType.LAZY)
	// private List<PessoaEnderecoEntity> pessoaEnderecoList;

	@OneToMany(mappedBy = "uf", fetch = FetchType.LAZY)
	private List<AgenciaBancoEntity> agenciaBancoList;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public UfEntity() {

	}

	public UfEntity(Integer id) {
		this.id = id;
	}

	public UfEntity(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public UfEntity(Integer id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = (sigla == null ? "".trim() : sigla.toUpperCase().trim());
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public PaisEntity getPais() {
		return pais;
	}

	public void setPais(PaisEntity pais) {
		this.pais = pais;
	}

	// public List<PessoaEnderecoEntity> getPessoaEnderecoList() {
	// return pessoaEnderecoList;
	// }

	// public void setPessoaEnderecoList(
	// List<PessoaEnderecoEntity> pessoaEnderecoList) {
	// this.pessoaEnderecoList = pessoaEnderecoList;
	// }

	public List<AgenciaBancoEntity> getAgenciaBancoList() {
		return agenciaBancoList;
	}

	public void setAgenciaBancoList(List<AgenciaBancoEntity> agenciaBancoList) {
		this.agenciaBancoList = agenciaBancoList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}