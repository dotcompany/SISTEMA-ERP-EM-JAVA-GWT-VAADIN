package dc.entidade.financeiro;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "fluxo_caixa_periodo")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class FluxoCaixaPeriodoEntity extends AbstractMultiEmpresaModel<Integer> {
	
	    private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "ID")
	    private Integer id;
	    
	    @Column(name = "PERIODO")
	    private String periodo;
	    
	    @Column(name = "NOME")
	    private String nome;
	    
	    @JoinColumn(name = "ID_CONTA_CAIXA", referencedColumnName = "ID")
	    @ManyToOne(optional = false)
	    private ContaCaixa contaCaixa;

	    public FluxoCaixaPeriodoEntity() {
	    }

	    public FluxoCaixaPeriodoEntity(Integer id) {
	        this.id = id;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getPeriodo() {
	        return periodo;
	    }

	    public void setPeriodo(String periodo) {
	        this.periodo = periodo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }

	        if (!(obj instanceof FluxoCaixaPeriodoEntity)) {
	            return false;
	        }

	        FluxoCaixaPeriodoEntity that = (FluxoCaixaPeriodoEntity) obj;
	        EqualsBuilder eb = new EqualsBuilder();
	        eb.append(getId(), that.getId());
	        return eb.isEquals();
	    }

	    @Override
	    public int hashCode() {
	        if (getId() == null) {
	            return super.hashCode();
	        } else {
	            return new HashCodeBuilder()
	                    .append(id)
	                    .toHashCode();
	        }
	    }

	    @Override
	    public String toString() {
	        return nome;
	    }

	    /**
	     * @return the contaCaixa
	     */
	    public ContaCaixa getContaCaixa() {
	        return contaCaixa;
	    }

	    /**
	     * @param contaCaixa the contaCaixa to set
	     */
	    public void setContaCaixa(ContaCaixa contaCaixa) {
	        this.contaCaixa = contaCaixa;
	    }


}
