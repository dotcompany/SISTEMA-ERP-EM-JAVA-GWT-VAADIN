package dc.entidade.tabelas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractModel;



/**
*
* @author Wesley Jr
/*
*/


@Entity
@Table(name = "sped_pis_4310")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class SpedPis4310 extends AbstractModel<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sped_pis_4310_id_seq")
	@SequenceGenerator(name = "sped_pis_4310_id_seq", sequenceName = "sped_pis_4310_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;
	
	
    @Field
    @Caption("Codigo")
    @Column(name="Codigo")
    private Integer codigo;
    
    @Lob
    @Field
    @Caption("Descricao")
    @Type(type="text")
    @Column(name="Descricao")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    private String descricao;
    
    @Lob
    @Field
    @Caption("Observacao")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    @Column(name = "OBSERVACAO")
    @Type(type="text")
    private String observacao;
    
    @Field
    @Caption("Inicio Vigencia")
    @Column(name = "INICIO_VIGENCIA")
    private Date inicioVigencia;
    
    @Field
    @Caption("Fim Vigencia")
    @Column(name = "FIM_VIGENCIA")
    private Date fimVigencia;
    
    public SpedPis4310() {
    }

    public SpedPis4310(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
