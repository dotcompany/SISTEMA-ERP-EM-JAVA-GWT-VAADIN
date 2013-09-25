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

import dc.anotacoes.Caption;



@Entity
@Table(name="CENTRO_RESULTADO")
public class CentroResultado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
	
    @Column(name = "CLASSIFICACAO")
    @Caption(value="Classificação")
    private String classificacao;
    @Column(name = "DESCRICAO")
    @Caption(value="Descrição")
    private String descricao;
    @Column(name = "SOFRE_RATEIO")
    @Caption(value="Sofre Rateio")
    private String sofreRateio;
   	@JoinColumn(name = "ID_PLANO_CENTRO_RESULTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
   	@Caption(value="Plano Centro Resultado")
    private PlanoCentroResultado planoCentroResultado;

    public CentroResultado() {
    }

    public CentroResultado(Integer id) {
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
    public String getSofreRateio() {
		return sofreRateio;
	}

	public void setSofreRateio(String sofreRateio) {
		this.sofreRateio = sofreRateio;
	}

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroResultado)) {
            return false;
        }
        CentroResultado other = (CentroResultado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    

    /**
     * @return the planoCentroResultado
     */
    public PlanoCentroResultado getPlanoCentroResultado() {
        return planoCentroResultado;
    }

    /**
     * @param planoCentroResultado the planoCentroResultado to set
     */
    public void setPlanoCentroResultado(PlanoCentroResultado planoCentroResultado) {
        this.planoCentroResultado = planoCentroResultado;
    }

}