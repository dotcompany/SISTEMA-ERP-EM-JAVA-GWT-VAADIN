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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;


/**
*
* @author Wesley Jr
/*
*Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
*no nosso Banco de Dados 
** Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
* e mudamos, está diferente do mapeamento do T2Ti.
* *
* Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
* que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
* na Tela, pegando os dados que estão salvos no Banco de Dados.
*/

@Entity
@Table(name = "endereco")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Endereco extends AbstractModel<Integer> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
   
    
//    @Column(name = "EMPRESA_ID")
//    private Integer empresaId;
    
    /*
    @Basic(optional = false)
    @Column(name = "TIPO_ENDERECO_ID", nullable = false)
    private int tipoEnderecoId;
    
    @Basic(optional = false)
    @Column(name = "CEP_ID", nullable = false)
    private int cepId;
     * 
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
    
    @Column(name="UF")
    private UF uf;
    
   
    
    @Field
    @Caption("Fone")
    @Column(name="FONE", length = 14)
    private String fone;
    
    @Column(name = "FAX", length = 14)
    private String fax;
    
    @Column(name="PRINCIPAL")
    private String principal;
    
    @Column(name="ENTREGA")
    private String entrega;
    
    @Column(name="COBRANCA")
    private String cobranca;
    
    @Column(name="CORRESPONDENCIA")
    private String correspondencia;
    
    /**
     * @Autor Wesley Júnior
     * 
     * Módulo Administrativo
     */
    
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Empresa empresa;

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getEmpresaId() {
//        return empresaId;
//    }
//
//    public void setEmpresaId(Integer empresaId) {
//        this.empresaId = empresaId;
//    }

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
	
    public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

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

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}



	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public String getCobranca() {
		return cobranca;
	}

	public void setCobranca(String cobranca) {
		this.cobranca = cobranca;
	}

	public String getCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(String correspondencia) {
		this.correspondencia = correspondencia;
	}

}

