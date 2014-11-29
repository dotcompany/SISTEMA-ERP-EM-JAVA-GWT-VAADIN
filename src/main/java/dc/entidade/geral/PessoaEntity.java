package dc.entidade.geral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;

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
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption("Tipo")
	@Column(name = "TIPO")
	private String tipo;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL")
	private String email;

	@Field
	@Caption("Site")
	@Column(name = "SITE")
	@Analyzer(definition = "dc_combo_analyzer")
	private String site;

	@Field
	@Column(name = "CLIENTE")
	private Character cliente;

	@Field
	@Column(name = "FORNECEDOR")
	private Character fornecedor;

	@Field
	@Column(name = "COLABORADOR")
	@Analyzer(definition = "dc_combo_analyzer")
	private Character colaborador;

	/*
	 * @Field
	 * 
	 * @Column(name = "CONVENIO") private Character convenio;
	 */

	@Field
	@Column(name = "TRANSPORTADORA")
	private Character transportadora;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, optional = true)
	private PessoaFisicaEntity pessoaFisica;

	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, optional = true)
	private PessoaJuridicaEntity pessoaJuridica;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaContatoEntity> contatos = new ArrayList<>();

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<PessoaEnderecoEntity> enderecos = new ArrayList<>();

	public PessoaEntity() {

	}

	public PessoaEntity(Integer id) {
		this.id = id;
	}

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	/*
	 * public Character getConvenio() { return convenio; }
	 * 
	 * public void setConvenio(Character convenio) { this.convenio = convenio; }
	 */

	/*
	 * public Character getContador() { return contador; }
	 * 
	 * public void setContador(Character contador) { this.contador = contador; }
	 */

	public Character getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Character transportadora) {
		this.transportadora = transportadora;
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

	public List<PessoaContatoEntity> getContatos() {
		return contatos;
	}

	public void setContatos(List<PessoaContatoEntity> contatos) {
		this.contatos = contatos;
	}

	public void adicionarContato(PessoaContatoEntity c) {
		getContatos().add(c);
		// c.setEmpresa(this.getEmpresa());
		c.setPessoa(this);
	}

	public void adicionarEndereco(PessoaEnderecoEntity end) {
		getEnderecos().add(end);
		end.setEmpresa(this.getEmpresa());
		end.setPessoa(this);
	}

	public List<PessoaEnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<PessoaEnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}