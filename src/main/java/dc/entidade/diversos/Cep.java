package dc.entidade.diversos;

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
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.ComboValue;
import dc.entidade.geral.UF;

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
@Table(name = "cep")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Cep extends AbstractModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    /*
    @Basic(optional = false)
    @Column(name = "CIDADE_ID", nullable = false)
    private int cidadeId;
     */
    @Field
    @Caption("Cep")
    @Column(name = "CEP", length = 8)
    @Analyzer(definition= "dc_combo_analyzer")
    @ComboValue
    private String cep;
    
    @Field
    @Caption("Logradouro")
    @Column(name = "LOGRADOURO", length = 60)
    @Analyzer(definition= "dc_combo_analyzer")
    @ComboValue
    private String logradouro;
    
    @Field
    @Caption("Complemento")    
    @Column(name = "COMPLEMENTO", length = 60)
    @Analyzer(definition= "dc_combo_analyzer")
    @ComboValue
    private String complemento;
    
    @Field
    @Caption("Bairro")
    @Column(name = "BAIRRO", length = 60)
    @Analyzer(definition= "dc_combo_analyzer")
    @ComboValue
    private String bairro;
    
    @Field
    @Caption("Municipio")
    @Column(name = "MUNICIPIO", length = 60)
    @Analyzer(definition= "dc_combo_analyzer")
    @ComboValue
    private String municipio;
    
    @Column(name = "UF")
    private UF uf;
    
    @Column(name="CODIGO_IBGE_MUNICIPIO")
    private Integer codigoIbgeMunicipio;


    public Cep() {
    }

    public Cep(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public Integer getCodigoIbgeMunicipio() {
		return codigoIbgeMunicipio;
	}

	public void setCodigoIbgeMunicipio(Integer codigoIbgeMunicipio) {
		this.codigoIbgeMunicipio = codigoIbgeMunicipio;
	}

	@Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof Cep == false) return false;
    	if (this == object) return true;
    	final Cep other = (Cep) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
