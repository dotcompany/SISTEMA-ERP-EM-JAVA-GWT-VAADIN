package dc.entidade.produto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
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
@Table(name = "ncm")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class NCM implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Codigo")
    @Column(name = "CODIGO")
    private String codigo;
    
    @Lob
    @Field
    @Caption("Descricao")
    @Type(type="text")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Lob
    @Field
    @Caption("Observacao")
    @Type(type="text")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    @Column(name = "OBSERVACAO")
    private String observacao;

    public NCM() {
    }

    public NCM(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof NCM == false) return false;
    	if (this == object) return true;
    	final NCM other = (NCM) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}

