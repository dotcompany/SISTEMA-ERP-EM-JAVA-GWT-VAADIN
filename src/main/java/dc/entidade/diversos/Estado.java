package dc.entidade.diversos;

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
@Table(name = "estado")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Estado extends AbstractModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "PAIS_ID",referencedColumnName = "ID")
    private Pais paisId;
    
    @Field
    @Caption("Sigla")
    @Column(name = "SIGLA", length = 2)
    private String sigla;
    
    @Field
    @Caption("Nome")
    @Column(name = "NOME", length = 50)
    private String nome;
    
    @Column(name = "CODIGO_IBGE")
    private Integer codigoIbge;

    public Estado() {
    }

    public Estado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }
    
    public Pais getPaisId() {
		return paisId;
	}

	public void setPaisId(Pais paisId) {
		this.paisId = paisId;
	}

	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

}
