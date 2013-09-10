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



/**
*
* @author Wesley Jr
/*
*/

@Entity
@Table(name = "almoxarifado")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class Almoxarifado implements Serializable {

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
   
   public Almoxarifado() {
   }

   public Almoxarifado(Integer id) {
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
   
   @Override
   public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
   }

   @Override
   public boolean equals(Object object) {
   	if (object instanceof Almoxarifado == false) return false;
   	if (this == object) return true;
   	final Almoxarifado other = (Almoxarifado) object;
   	return EqualsBuilder.reflectionEquals(this, other);
   }

   @Override
   public String toString() {
   	return ToStringBuilder.reflectionToString(this);
   }

}
