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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ibm.icu.math.BigDecimal;

import dc.entidade.framework.AbstractMultiEmpresaModel;

@Entity
@Table(name = "fluxo_caixa_detalhe")
@NamedQueries({@NamedQuery(name = "FluxoCaixaDetalhe.findAll", query = "SELECT f FROM FluxoCaixaDetalhe f")})
public class FluxoCaixaDetalheEntity extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
	    private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "ID")
	    private Integer id;
	    
	    @Column(name = "PERIODO")
	    private String periodo;
	    
	    @Column(name = "VALOR_ORCADO")
	    private BigDecimal valorOrcado;
	    
	    @Column(name = "VALOR_REALIZADO")
	    private BigDecimal valorRealizado;
	    
	    @Column(name = "TAXA_VARIACAO")
	    private BigDecimal taxaVariacao;
	    
	    @Column(name = "VALOR_VARIACAO")
	    private BigDecimal valorVariacao;
	    
	    @JoinColumn(name = "ID_FLUXO_CAIXA", referencedColumnName = "ID")
	    @ManyToOne(optional = false)
	    private FluxoCaixaEntity fluxoCaixa;
	    
	    @JoinColumn(name = "ID_NATUREZA_FINANCEIRA", referencedColumnName = "ID")
	    @ManyToOne(optional = false)
	    private NaturezaFinanceira naturezaFinanceira;

	    public FluxoCaixaDetalheEntity() {
	    }

	    public FluxoCaixaDetalheEntity(Integer id) {
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

	    public BigDecimal getValorOrcado() {
	        return valorOrcado;
	    }

	    public void setValorOrcado(BigDecimal valorOrcado) {
	        this.valorOrcado = valorOrcado;
	    }

	    public BigDecimal getValorRealizado() {
	        return valorRealizado;
	    }

	    public void setValorRealizado(BigDecimal valorRealizado) {
	        this.valorRealizado = valorRealizado;
	    }

	    public BigDecimal getTaxaVariacao() {
	        return taxaVariacao;
	    }

	    public void setTaxaVariacao(BigDecimal taxaVariacao) {
	        this.taxaVariacao = taxaVariacao;
	    }

	    public BigDecimal getValorVariacao() {
	        return valorVariacao;
	    }

	    public void setValorVariacao(BigDecimal valorVariacao) {
	        this.valorVariacao = valorVariacao;
	    }

	    @Override
	    public String toString() {
	        return periodo;
	    }

	    /**
	     * @return the fluxoCaixa
	     */
	    public FluxoCaixaEntity getFluxoCaixa() {
	        return fluxoCaixa;
	    }

	    /**
	     * @param fluxoCaixa the fluxoCaixa to set
	     */
	    public void setFluxoCaixa(FluxoCaixaEntity fluxoCaixa) {
	        this.fluxoCaixa = fluxoCaixa;
	    }

	    /**
	     * @return the naturezaFinanceira
	     */
	    public NaturezaFinanceira getNaturezaFinanceira() {
	        return naturezaFinanceira;
	    }

	    /**
	     * @param naturezaFinanceira the naturezaFinanceira to set
	     */
	    public void setNaturezaFinanceira(NaturezaFinanceira naturezaFinanceira) {
	        this.naturezaFinanceira = naturezaFinanceira;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }

	        if (!(obj instanceof FluxoCaixaDetalheEntity)) {
	            return false;
	        }

	        FluxoCaixaDetalheEntity that = (FluxoCaixaDetalheEntity) obj;
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


}
