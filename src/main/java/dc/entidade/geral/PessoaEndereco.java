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
@Table(name = "pessoa_endereco")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class PessoaEndereco extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_endereco_id_seq")
	@SequenceGenerator(name = "pessoa_endereco_id_seq", sequenceName = "pessoa_endereco_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	/*
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "TIPO_ENDERECO_ID", nullable = false) private int
	 * tipoEnderecoId;
	 * 
	 * @Basic(optional = false)
	 * 
	 * @Column(name = "CEP_ID", nullable = false) private int cepId;
	 */
	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	private String logradouro;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO")
	private Integer numero;

	@Field
	@Caption("Complemento")
	@Column(name = "COMPLEMENTO", length = 100)
	private String complemento;

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 50)
	private String bairro;

	@Field
	@Caption("Cidade")
	@Column(name = "CIDADE", length = 50)
	private String cidade;

	@Field
	@Caption("Cep")
	@Column(name = "CEP", length = 8)
	private String cep;

	@Field
	@Caption("Municipio Ibge")
	@Column(name = "MUNICIPIO_IBGE")
	private Integer municipioIbge;

	// @JoinColumn(name = "ID_UF", referencedColumnName = "ID")
	// @ManyToOne(optional = false)
	@Column(name = "UF")
	private String uf;

	@Field
	@Caption("Fone")
	@Column(name = "FONE", length = 14)
	private String fone;

	@Column(name = "FAX", length = 14)
	private String fax;

	@Column(name = "PRINCIPAL")
	private Boolean principal;

	@Column(name = "ENTREGA")
	private Boolean entrega;

	@Column(name = "COBRANCA")
	private Boolean cobranca;

	@Column(name = "CORRESPONDENCIA")
	private Boolean correspondencia;

	/**
	 * @Autor Wesley Júnior
	 * 
	 *        Módulo Administrativo
	 */

	/*
	 * @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
	 * 
	 * @ManyToOne(optional = false) private Empresa empresa;
	 */

	@JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")
	@ManyToOne
	private Pessoa pessoa;

	public PessoaEndereco() {

	}

	public PessoaEndereco(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// public Integer getEmpresaId() {
	// return empresaId;
	// }
	//
	// public void setEmpresaId(Integer empresaId) {
	// this.empresaId = empresaId;
	// }

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	/*
	 * public Empresa getEmpresa() { return empresa; }
	 * 
	 * public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
	 */

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getMunicipioIbge() {
		return municipioIbge;
	}

	public void setMunicipioIbge(Integer municipioIbge) {
		this.municipioIbge = municipioIbge;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public void setEntrega(Boolean entrega) {
		this.entrega = entrega;
	}

	public Boolean getCobranca() {
		return cobranca;
	}

	public void setCobranca(Boolean cobranca) {
		this.cobranca = cobranca;
	}

	public Boolean getCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(Boolean correspondencia) {
		this.correspondencia = correspondencia;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
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