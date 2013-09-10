package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;


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
@Table(name = "pessoa")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Nome")
    @Column(name = "NOME")
    private String nome;
    
    @Field
    @Caption("Tipo")
    @Column(name = "TIPO")
    private String tipo;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "SITE")
    private String site;
    
    @Column(name="CLIENTE")
    private Character cliente;
    
    @Column(name="FORNECEDOR")
    private Character fornecedor;
    
    @Column(name="COLABORADOR")
    private Character colaborador;
    
    @Column(name="CONVENIO")
    private Character convenio;
    
    @Column(name="CONTADOR")
    private Character contador;
    
    @Column(name="TRANSPORTADORA")
    private Character transportadora;
    
    public Pessoa() {
    }

    public Pessoa(Integer id) {
        this.id = id;
    }

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

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof Pessoa == false) return false;
    	if (this == object) return true;
    	final Pessoa other = (Pessoa) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
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

	public Character getConvenio() {
		return convenio;
	}

	public void setConvenio(Character convenio) {
		this.convenio = convenio;
	}

	public Character getContador() {
		return contador;
	}

	public void setContador(Character contador) {
		this.contador = contador;
	}

	public Character getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Character transportadora) {
		this.transportadora = transportadora;
	}

}