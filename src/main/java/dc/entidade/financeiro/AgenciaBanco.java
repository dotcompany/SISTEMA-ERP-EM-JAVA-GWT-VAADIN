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
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

/*Autor: Wesley Junior*/
/*
 *Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
 *no nosso Banco de Dados 
 * *
 * Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
 * e mudamos, está diferente do mapeamento do T2Ti.
 * 
 * Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
 * que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
 * na Tela, pegando os dados que estão salvos no Banco de Dados.
 */

@Entity
@Table(name = "agencia_banco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class AgenciaBanco extends AbstractModel<Integer> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financeiro_id_seq")
	@SequenceGenerator(name = "financeiro_id_seq", sequenceName = "financeiro_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;
	/*
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "CEP_ID", nullable = false) private int cepId;
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "BANCO_ID", nullable = false) private int bancoId;
	 */

	@Column(name = "ID_BANCO")
	private Integer idBanco;

	@Field
	@Caption("Codigo")
	@Column(name = "CODIGO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigo;

	@Field
	@Caption("Nome")
	@Column(name = "NOME", length = 100)
	private String nome;

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	private String logradouro;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO", length = 10)
	private String numero;

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 10)
	private String cep;

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 60)
	private String bairro;

	@Field
	@Caption("Municipio")
	@Column(name = "MUNICIPIO", length = 60)
	private String municipio;

	@Field
	@Caption("Uf")
	@Column(name = "UF")
	private String uf;

	@Field
	@Caption("Telefone")
	@Column(name = "TELEFONE", length = 10)
	private String telefone;

	@Field
	@Caption("Gerente")
	@Column(name = "GERENTE", length = 30)
	private String gerente;

	@Field
	@Caption("Contato")
	@Column(name = "CONTATO", length = 30)
	private String contato;

	@Field
	@Caption("Observacao")
	@Lob
	@Column(name = "OBSERVACAO")
	@Basic(fetch = javax.persistence.FetchType.LAZY)
	@Type(type = "text")
	private String observacao;

	/*
	 * @Caption("Banco")
	 * 
	 * @ManyToOne(optional = false)
	 * 
	 * @JoinColumn(name = "BANCO_ID",referencedColumnName = "ID") private Banco
	 * banco;
	 * 
	 * /*@Caption("Cep Id")
	 * 
	 * @ManyToOne(optional = false)
	 * 
	 * @JoinColumn(name = "CEP_ID", referencedColumnName = "ID") private Cep
	 * cepId;
	 */

	/**
	 * @autor Gutemberg A. Da Silva
	 * 
	 * @module FINANCEIRO
	 */

	@OneToMany(mappedBy = "agenciaBanco", fetch = FetchType.LAZY)
	private List<ContaCaixa> contaCaixaList;

	/**
	 * CONSTRUTOR
	 */

	public AgenciaBanco() {

	}

	public AgenciaBanco(Integer id) {
		this.id = id;
	}

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

	/*
	 * public Cep getCepId() { return cepId; }
	 * 
	 * public void setCepId(Cep cepId) { this.cepId = cepId; }
	 */

	/*
	 * @Override public int hashCode() { return
	 * HashCodeBuilder.reflectionHashCode(this, new String[] {"id"}); }
	 * 
	 * @Override public boolean equals(Object object) { if (object instanceof
	 * AgenciaBanco == false) return false; if (this == object) return true;
	 * final AgenciaBanco other = (AgenciaBanco) object; return
	 * EqualsBuilder.reflectionEquals(this, other); }
	 */

	/*
	 * public Banco getBanco() { return banco; }
	 * 
	 * public void setBanco(Banco banco) { this.banco = banco; }
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}