package dc.entidade.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.TipoPessoaEn;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.pessoal.ClienteEntity;

@Entity
@Table(name = "pessoa")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaEntity extends AbstractMultiEmpresaModel<Integer> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
	@SequenceGenerator(name = "pessoa_id_seq", sequenceName = "pessoa_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Nome")
	@Column(name = "NOME")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;

	@Field
	@Caption("Site")
	@Column(name = "SITE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String site;

	@Field
	@Caption()
	@Column(name = "CLIENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character cliente;

	@Field
	@Caption()
	@Column(name = "FORNECEDOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character fornecedor;

	@Field
	@Caption()
	@Column(name = "COLABORADOR")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character colaborador;

	/*
	 * @Field
	 * 
	 * @Column(name = "CONVENIO") private Character convenio;
	 */

	@Field
	@Caption()
	@Column(name = "TRANSPORTADORA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Character transportadora;

	@Field
	@Caption("Tipo")
	@Column(name = "TIPO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private TipoPessoaEn tipoPessoa;

	/**
	 * REFERENCIA - FK
	 */

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, optional = true)
	private PessoaFisicaEntity pessoaFisica;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, optional = true)
	private PessoaJuridicaEntity pessoaJuridica;

	/**
	 * REFERENCIA - LIST
	 */

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaContatoEntity> pessoaContatoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaEnderecoEntity> pessoaEnderecoList = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<ClienteEntity> clienteList;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public PessoaEntity() {

	}

	public PessoaEntity(Integer id) {
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
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Character getCliente() {
		return cliente;
	}

	public void setCliente(Character cliente) {
		this.cliente = cliente;
	}

	public Character getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Character fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Character getColaborador() {
		return colaborador;
	}

	public void setColaborador(Character colaborador) {
		this.colaborador = colaborador;
	}

	public Character getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Character transportadora) {
		this.transportadora = transportadora;
	}

	public TipoPessoaEn getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEn tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public PessoaFisicaEntity getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridicaEntity getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridicaEntity pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public List<PessoaContatoEntity> getPessoaContatoList() {
		return pessoaContatoList;
	}

	public void setPessoaContatoList(List<PessoaContatoEntity> pessoaContatoList) {
		this.pessoaContatoList = pessoaContatoList;
	}

	public List<PessoaEnderecoEntity> getPessoaEnderecoList() {
		return pessoaEnderecoList;
	}

	public void setPessoaEnderecoList(
			List<PessoaEnderecoEntity> pessoaEnderecoList) {
		this.pessoaEnderecoList = pessoaEnderecoList;
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