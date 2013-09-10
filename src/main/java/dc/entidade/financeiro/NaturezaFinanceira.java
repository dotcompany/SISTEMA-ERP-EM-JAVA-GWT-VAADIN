package dc.entidade.financeiro;

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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.contabilidade.ContabilConta;

/**
*
* @author Wesley Jr
/*
*Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
*no nosso Banco de Dados 
** Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
* e mudamos, est� diferente do mapeamento do T2Ti.
* *
* Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
* que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
* na Tela, pegando os dados que estão salvos no Banco de Dados.
*/
@Entity
@Table(name = "natureza_financeira")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class NaturezaFinanceira implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Classificação")
    @Column(name = "CLASSIFICACAO")
    private String classificacao;
    
    @Field
    @Caption("Descricao")
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Column(name="TIPO")
    @Caption(value="Tipo")
    private String tipo;
    
    @Caption(value="Aplicação")
    @Column(name="APLICACAO", length = 255)
    private String aplicacao;
    
    @Caption(value="Aparece à Pagar")
    @Column(name="APARECE_A_PAGAR")
    private String aparecePagar;
    
    @Caption(value="Aparece à Receber")
    @Column(name="APARECE_A_RECEBER")
    private String apareceReceber;
    
    @Caption(value="Plano Natureza Financeira")
    @JoinColumn(name = "ID_PLANO_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PlanoNaturezaFinanceira planoNaturezaFinanceira;
    
    @Caption(value="Conta Contábil")
    @JoinColumn(name = "id_contabil_conta", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ContabilConta contabilconta;

    public NaturezaFinanceira() {
    }

    public NaturezaFinanceira(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getAparecePagar() {
		return aparecePagar;
	}

	public void setAparecePagar(String aparecePagar) {
		this.aparecePagar = aparecePagar;
	}

	public String getApareceReceber() {
		return apareceReceber;
	}

	public void setApareceReceber(String apareceReceber) {
		this.apareceReceber = apareceReceber;
	}

	@Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof NaturezaFinanceira == false) return false;
    	if (this == object) return true;
    	final NaturezaFinanceira other = (NaturezaFinanceira) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the planoNaturezaFinanceira
     */
    public PlanoNaturezaFinanceira getPlanoNaturezaFinanceira() {
        return planoNaturezaFinanceira;
    }

    /**
     * @param planoNaturezaFinanceira the planoNaturezaFinanceira to set
     */
    public void setPlanoNaturezaFinanceira(PlanoNaturezaFinanceira planoNaturezaFinanceira) {
        this.planoNaturezaFinanceira = planoNaturezaFinanceira;
    }

	public ContabilConta getContabilconta() {
		return contabilconta;
	}

	public void setContabilconta(ContabilConta contabilconta) {
		this.contabilconta = contabilconta;
	}
    

}

