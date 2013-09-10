package dc.entidade.geral;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;
import dc.entidade.framework.Empresa;

/**
*
* @author Wesley Jr
* 
*/

@Entity
@Table(name = "tribut_operacao_fiscal")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class OperacaoFiscal extends AbstractModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    /*
    @Basic(optional = false)
    @Column(name = "CIDADE_ID", nullable = false)
    private int cidadeId;
     */
    @Field
    @Caption("Descricao")
    @Column(name = "DESCRICAO", length = 100)
    private String descricao;
    
    @Field
    @Caption("Descricao na Nf")
    @Column(name = "DESCRICAO_NA_NF", length = 100)
    private String descricaoNaNf;
    
    @Column(name="CFOP")
    private Integer cfop;
    
    @Lob
	@Field
	@Type(type = "text")
	@Caption("Observacao")
	@Column(name = "OBSERVACAO")
	private String observacao;

    /*
     * Mapeamento Empresa
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPRESA",insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    private Empresa idEmpresa;
    

    public OperacaoFiscal() {
    }

    public OperacaoFiscal(Integer id) {
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

	public String getDescricaoNaNf() {
		return descricaoNaNf;
	}

	public void setDescricaoNaNf(String descricaoNaNf) {
		this.descricaoNaNf = descricaoNaNf;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
