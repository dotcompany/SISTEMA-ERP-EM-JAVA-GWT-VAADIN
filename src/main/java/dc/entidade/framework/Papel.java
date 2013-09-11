package dc.entidade.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
@Table(name = "papel")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Papel extends AbstractModel<Integer> implements Serializable  {
	
    private static final long serialVersionUID = 1L;

	public static final Integer MASTER_ID = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Field
    @Caption("Nome")
    @Column(name = "NOME")
    private String nome;
    
    @Lob
    @Type(type="text")
    @Column(name = "DESCRICAO")
    private String descricao;
    
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<PapelMenu> papelMenus = new ArrayList<PapelMenu>();

    public Papel() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


	/*@Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }*/

    /*@Override
    public boolean equals(Object object) {
    	if (object instanceof Papel== false) return false;
    	if (this == object) return true;
    	final Papel other = (Papel) object;
    	boolean igual = EqualsBuilder.reflectionEquals(this, other, new String[] {"papelMenus"});
    	return igual;
    }*/

    
  /*  @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }*/

	public List<PapelMenu> getPapelMenus() {
		return papelMenus;
	}

	public void setPapelMenus(List<PapelMenu> papelMenus) {
		this.papelMenus = papelMenus;
	}

}
