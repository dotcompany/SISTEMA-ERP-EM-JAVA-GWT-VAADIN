package dc.entidade.tributario;

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

import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;


/**
* <p>Description:  VO relacionado a tabela [TRIBUT_GRUPO_TRIBUTARIO]</p>
*
* @author Wesley Junior
*/
@Entity
@Table(name = "TRIBUT_GRUPO_TRIBUTARIO")
public class GrupoTributario extends AbstractModel<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ORIGEM_MERCADORIA")
    private String origemMercadoria;
    
    @Column(name = "OBSERVACAO")
    private String observacao;
    
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Empresa empresa;

    public GrupoTributario() {
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

    public String getOrigemMercadoria() {
        return origemMercadoria;
    }

    public void setOrigemMercadoria(String origemMercadoria) {
        this.origemMercadoria = origemMercadoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    @Override
    public String toString() {
        return "com.t2tierp.tributacao.java.TributGrupoTributarioVO[id=" + id + "]";
    }

}
