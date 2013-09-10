package dc.entidade.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.geral.UF;

/*
 * 
 * Autor: Wesley Junior
 *
 */

@Entity
@Table(name = "sindicato")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Sindicato extends AbstractModel<Integer> implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	 
	    @Id
		@Column(name = "ID", nullable = false)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sindicato_id_seq")
		@SequenceGenerator(name = "sindicato_id_seq", sequenceName = "sindicato_id_seq", allocationSize = 1, initialValue = 0)
		@Basic(optional = false)
		private Integer id;
	    /*
	    @Basic(optional = false)
	    @Column(name = "CEP_ID", nullable = false)
	    private int cepId;
	    @Basic(optional = false)
	    @Column(name = "BANCO_ID", nullable = false)
	    private int bancoId;
	     */
	    
	    @Column(name = "ID_CONTABIL_CONTA")
	    private Integer idContabilConta;
	    
	    @Column(name = "CODIGO_BANCO")
	    private Integer codigoBanco;
	    
	    @Column(name = "CODIGO_AGENCIA")
	    private Integer codigoAgencia;
	    
	    @Column(name = "CONTA_BANCO")
	    private String contaBanco;
	    
	    @Column(name = "CODIGO_CEDENTE")
	    private String codigoCedente;
	    
	    @Field
	    @Caption("Logradouro")
	    @Column(name = "LOGRADOURO", length = 100)
	    private String logradouro;
	    
	    @Field
		@Caption("Numero")
	    @Column(name = "NUMERO", length = 10)
	    private String numero;
	    
	    @Field
		@Caption("Bairro")
	    @Column(name = "BAIRRO", length = 60)
	    private String bairro;
	    
	    @Field
		@Caption("Municipio")
	    @Column(name = "MUNICIPIO", length = 60)
	    private String municipio;
	    
	    @ManyToOne(optional = false)
	    @JoinColumn(name = "UF",referencedColumnName = "ID")
	    private UF  uf;
	    
	    @Field
		@Caption("Telefone 1")
	    @Column(name = "FONE1", length = 10)
	    private String fone1;
	    
	    @Field
		@Caption("Telefone 2")
	    @Column(name = "FONE2", length = 10)
	    private String fone2;
	    
	    @Field
		@Caption("Email")
	    @Column(name = "EMAIL", length = 100)
	    private String email;
	    
	    @Field
	    @Caption("Tipo Sindicato")
	    @Column(name="TIPO_SINDICATO")
	    private String tipoSindicato;
	    
	    @Field
	    @Caption("Data Base")
	    @Column(name = "DATA_BASE")
	    @Temporal(TemporalType.DATE)
	    private Date dataBase;
	    
	    @Field
		@Caption("Nome")
	    @Column(name = "Nome", length = 100)
	    private String nome;
	    
	    @Column(name = "PISO_SALARIAL", precision = 14, scale = 0)
	    private BigDecimal pisoSalarial;
	    
	    @Field
		@Caption("Cnpj")
	    @Column(name = "CNPJ", length = 30)
	    private String cnpj;
	    

	    public Sindicato() {
	    }

	    public Sindicato(Integer id) {
	        this.id = id;
	    }

	    @Override
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }
	    
		public Integer getIdContabilConta() {
			return idContabilConta;
		}

		public void setIdContabilConta(Integer idContabilConta) {
			this.idContabilConta = idContabilConta;
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

		public String getMunicipio() {
			return municipio;
		}

		public void setMunicipio(String municipio) {
			this.municipio = municipio;
		}

		public UF getUf() {
			return uf;
		}

		public void setUf(UF uf) {
			this.uf = uf;
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

		public String getTipoSindicato() {
			return tipoSindicato;
		}

		public void setTipoSindicato(String tipoSindicato) {
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

		@Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this);
	    }
}
