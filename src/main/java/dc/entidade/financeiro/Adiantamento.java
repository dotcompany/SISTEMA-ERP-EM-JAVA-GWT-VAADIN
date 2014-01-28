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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;

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
@Table(name = "adiantamento")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Adiantamento extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Data_Adiantamento")
    @Column(name = "DATA_ADIANTAMENTO")
    @Temporal(TemporalType.DATE)
    private Date dataAdiantamento;
    
    @Field
    @Caption("Valor")
    @Column(name = "VALOR", precision = 14, scale = 0)
    private BigDecimal valor;
    
    @Lob
    @Field
    @Caption("Observações")
    @Type(type="text")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    @Column(name = "OBSERVACOES")
    private String observacoes;
    
    @JoinColumn(name = "ID_LANCAMENTO_PAGAR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private LancamentoPagar idLancamentoPagar;

    public Adiantamento() {
    }

    public Adiantamento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAdiantamento() {
        return dataAdiantamento;
    }

    public void setDataAdiantamento(Date dataAdiantamento) {
        this.dataAdiantamento = dataAdiantamento;
    }

    public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof Adiantamento == false) return false;
    	if (this == object) return true;
    	final Adiantamento other = (Adiantamento) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

	public LancamentoPagar getIdLancamentoPagar() {
		return idLancamentoPagar;
	}

	public void setIdLancamentoPagar(LancamentoPagar idLancamentoPagar) {
		this.idLancamentoPagar = idLancamentoPagar;
	}

}

