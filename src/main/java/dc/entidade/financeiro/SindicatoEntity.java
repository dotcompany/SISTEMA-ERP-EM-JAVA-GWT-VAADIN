package dc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.control.enums.TipoSindicatoEn;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "sindicato")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class SindicatoEntity extends AbstractMultiEmpresaModel<Integer>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sindicato_id_seq")
	@SequenceGenerator(name = "sindicato_id_seq", sequenceName = "sindicato_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption()
	@Column(name = "CODIGO_BANCO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoBanco;

	@Field
	@Caption()
	@Column(name = "CODIGO_AGENCIA")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer codigoAgencia;

	@Field
	@Caption()
	@Column(name = "CONTA_BANCO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String contaBanco;

	@Field
	@Caption()
	@Column(name = "CODIGO_CEDENTE")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String codigoCedente;

	@Field
	@Caption("Logradouro")
	@Column(name = "LOGRADOURO", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String logradouro;

	@Field
	@Caption("Numero")
	@Column(name = "NUMERO", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String numero;

	@Field
	@Caption("Bairro")
	@Column(name = "BAIRRO", length = 60)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String bairro;

	@Field
	@Caption("Telefone 1")
	@Column(name = "FONE1", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone1;

	@Field
	@Caption("Telefone 2")
	@Column(name = "FONE2", length = 10)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String fone2;

	@Field
	@Caption("Email")
	@Column(name = "EMAIL", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String email;

	@Field
	@Caption("Tipo de sindicato")
	@Column(name = "TIPO_SINDICATO")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	@Enumerated(EnumType.STRING)
	private TipoSindicatoEn tipoSindicato;

	@Field
	@Caption("Data Base")
	@Column(name = "DATA_BASE")
	@Temporal(TemporalType.DATE)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private Date dataBase;

	@Field
	@Caption("Nome")
	@Column(name = "Nome", length = 100)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String nome;

	@Field
	@Caption()
	@Column(name = "PISO_SALARIAL", precision = 14, scale = 0)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal pisoSalarial;

	@Field
	@Caption("CNPJ")
	@Column(name = "CNPJ", length = 30)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String cnpj;

	@Field
	@Caption("UF")
	@Column(name = "uf", length = 2)
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private String uf;

	/**
	 * REFERENCIA - FK
	 */

	/**
	 * REFERENCIA - LIST
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contabil_conta", nullable = true)
	@Caption("Conta contábil")
	private ContabilContaEntity contabilConta;

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public SindicatoEntity() {

	}

	public SindicatoEntity(Integer id) {
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

	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Integer getCodigoAgencia() {
		return codigoAgencia;
	}

	public void setCodigoAgencia(Integer codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}

	public String getContaBanco() {
		return contaBanco;
	}

	public void setContaBanco(String contaBanco) {
		this.contaBanco = contaBanco;
	}

	public String getCodigoCedente() {
		return codigoCedente;
	}

	public void setCodigoCedente(String codigoCedente) {
		this.codigoCedente = codigoCedente;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoSindicatoEn getTipoSindicato() {
		return tipoSindicato;
	}

	public void setTipoSindicato(TipoSindicatoEn tipoSindicato) {
		this.tipoSindicato = tipoSindicato;
	}

	public Date getDataBase() {
		return dataBase;
	}

	public void setDataBase(Date dataBase) {
		this.dataBase = dataBase;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPisoSalarial() {
		return pisoSalarial;
	}

	public void setPisoSalarial(BigDecimal pisoSalarial) {
		this.pisoSalarial = pisoSalarial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public ContabilContaEntity getContabilConta() {
		return contabilConta;
	}

	public void setContabilConta(ContabilContaEntity contabilConta) {
		this.contabilConta = contabilConta;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}