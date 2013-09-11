package dc.entidade.financeiro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "cheque")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Cheque implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Numero")
    @Column(name = "NUMERO")
    private Integer numero;
    
    @Field
    @Caption("Status Cheque")
    @Column(name = "STATUS_CHEQUE")
    private String statusCheque;
    
    @Field
    @Caption("Data Status")
    @Column(name = "DATA_STATUS")
    @Temporal(TemporalType.DATE)
    private Date dataStatus;
    
    @JoinColumn(name = "ID_TALONARIO_CHEQUE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TalonarioCheque idTalonarioCheque;

    public Cheque() {
    }

    public Cheque(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getStatusCheque() {
        return statusCheque;
    }

    public void setStatusCheque(String statusCheque) {
        this.statusCheque = statusCheque;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof Cheque == false) return false;
    	if (this == object) return true;
    	final Cheque other = (Cheque) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public TalonarioCheque getIdTalonarioCheque() {
		return idTalonarioCheque;
	}

	public void setIdTalonarioCheque(TalonarioCheque idTalonarioCheque) {
		this.idTalonarioCheque = idTalonarioCheque;
	}

}
