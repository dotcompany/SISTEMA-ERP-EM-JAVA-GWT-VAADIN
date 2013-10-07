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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;
import dc.entidade.framework.Empresa;


/**
* <p>Description:  VO relacionado a tabela [TRIBUT_GRUPO_TRIBUTARIO]</p>
*
* @author Wesley Junior
*/
@Entity
@Table(name = "tribut_grupo_tributario")
@SuppressWarnings("serial")
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class GrupoTributario extends AbstractMultiEmpresaModel<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trb")
	@SequenceGenerator(name = "trb", sequenceName = "tribut_grupo_tributario_id_seq", allocationSize = 1)
	@ComboCode
	@Analyzer(definition="dc_combo_analyzer")
	@Field
	private Integer id;
  

	
	@Field
	@Column(name="descricao")
    @Caption("Descrição")
	@ComboValue
	@Analyzer(definition="dc_combo_analyzer")
    private String nome;
    
    @Column(name = "ORIGEM_MERCADORIA")
    private String origemMercadoria;
    
    private String observacao;
    
//    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
//    @ManyToOne(optional = false)
//    private Empresa empresa;
    
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

//    public Empresa getEmpresa() {
//        return empresa;
//    }
//
//    public void setEmpresa(Empresa empresa) {
//        this.empresa = empresa;
//    }


    @Override
    public String toString() {
        return nome;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
    
    

}
