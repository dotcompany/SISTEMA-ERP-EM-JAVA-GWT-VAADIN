package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

@Entity
@Table(name = "pessoa_contato")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaContatoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_contato_id_seq")
	@SequenceGenerator(name = "pessoa_contato_id_seq", sequenceName = "pessoa_contato_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	// @Column(name = "id_empresa")
	// private Integer empresaId;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="id_empresa") private Empresa empresa;
	 */

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	private String nome = "";

	@Field
	@Caption("E-mail")
	@Column(name = "EMAIL", length = 250)
	private String email = "";

	@Field
	@Caption("Telefone comercial")
	@Column(name = "FONE_COMERCIAL", length = 14)
	private String foneComercial = "";

	@Field
	@Caption("Telefone residencial")
	@Column(name = "FONE_RESIDENCIAL", length = 14)
	private String foneResidencial = "";

	@Field
	@Caption("Celular")
	@Column(name = "FONE_CELULAR", length = 14)
	private String foneCelular = "";

	/**
	 * @Autor Wesley Junior
	 * 
	 *        MODULO ADMINISTRATIVO
	 */

	// @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	// @ManyToOne(optional = false)
	// private Empresa empresa;

	@Caption("Pessoa")
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private PessoaEntity pessoa;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaContatoEntity() {

	}

	public PessoaContatoEntity(Integer id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = (email == null ? "".trim() : email.toUpperCase().trim());
	}

	public String getFoneComercial() {
		return foneComercial;
	}

	public void setFoneComercial(String foneComercial) {
		this.foneComercial = (foneComercial == null ? "".trim() : foneComercial
				.toUpperCase().trim());
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = (foneResidencial == null ? "".trim()
				: foneResidencial.toUpperCase().trim());
	}

	public String getFoneCelular() {
		return foneCelular;
	}

	public void setFoneCelular(String foneCelular) {
		this.foneCelular = (foneCelular == null ? "".trim() : foneCelular
				.toUpperCase().trim());
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}