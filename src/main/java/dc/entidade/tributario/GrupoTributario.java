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
import javax.persistence.Transient;

import dc.anotacoes.Caption;
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
  
    @Caption("Descrição")
    private String descricao;
    
    @Column(name = "ORIGEM_MERCADORIA")
    private String origemMercadoria;
    
    private String observacao;
    
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Empresa empresa;
    
    @Transient
    @Caption("Origem da Mercadoria")
    private String origemString;

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
        return descricao;
    }

	public String getOrigemString() {
		switch (new Integer(origemMercadoria)) {
		case 0:
			origemString = "NACIONAL";
			break;
		case 1:
			origemString = "ESTRANGEIRA";
			break;

		default:
			origemString = " ";
			break;
		}
		return origemString;
	}

	public void setOrigemString(String origemString) {
		this.origemString = origemString;
	}
    
    

}
