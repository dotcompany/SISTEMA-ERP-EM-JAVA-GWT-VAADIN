package dc.entidade.financeiro;

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
@Table(name = "agencia_banco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AgenciaBancoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencia_banco_id_seq")
	@SequenceGenerator(name = "agencia_banco_id_seq", sequenceName = "agencia_banco_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Código")
	@Column(name = "CODIGO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigo;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome = "";

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro = "";

	@Field
	@Caption("Número")
	@Column(name = "NUMERO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero = "";

	@Field
	@Caption("CEP")
	@Column(name = "CEP", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cep = "";

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro = "";

	@Field
	@Caption("Município")
	@Column(name = "MUNICIPIO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String municipio = "";

	@Field
	@Caption("UF")
	@Column(name = "UF")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String uf = "";

	@Field
	@Caption("Telefone")
	@Column(name = "TELEFONE", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String telefone = "";

	@Field
	@Caption("Gerente")
	@Column(name = "GERENTE", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String gerente = "";

	@Field
	@Caption("Contato")
	@Column(name = "CONTATO", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contato = "";

	@Lob
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Type(type = "text")
	@Field
	@Caption("Observação")
	@Column(name = "OBSERVACAO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String observacao = "";

	@Field
	@Caption("Dígito")
	@Column(name = "DIGITO", length = 1)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String digito = "";

	/**
	 * REFERENCIA - FK
	 */

	@Field
	@Caption("Banco")
	@Column(name = "id_banco")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer banco;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FINANCEIRO
	 */

	@OneToMany(mappedBy = "agenciaBanco", fetch = FetchType.LAZY)
	private List<ContaCaixa> contaCaixaList;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public AgenciaBancoEntity() {

	}

	public AgenciaBancoEntity(Integer id) {
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = digito;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public List<ContaCaixa> getContaCaixaList() {
		return contaCaixaList;
	}

	public void setContaCaixaList(List<ContaCaixa> contaCaixaList) {
		this.contaCaixaList = contaCaixaList;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}