package dc.entidade.tabelas;

import java.io.Serializable;

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



/**
*
* @author Wesley Jr
/*
*/


@Entity
@Table(name = "sefip_codigo_movimentacao")
@XmlRootElement
@Indexed
@Analyzer(impl=BrazilianAnalyzer.class)
public class SefipCodigoMovimentacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sefip_codigo_movimentacao_id_seq")
	@SequenceGenerator(name = "sefip_codigo_movimentacao_id_seq", sequenceName = "sefip_codigo_movimentacao_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	private Integer id;
    
    @Field
    @Caption("Codigo")
    @Column(name="Codigo", length = 50)
    private String codigo;
    
    @Lob
    @Field
    @Type(type="text")
    @Caption("Descricao")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    @Column(name = "DESCRICAO")
    private String descricao;
    
    @Lob
    @Field
    @Caption("Aplicacao")
    @Basic(fetch=javax.persistence.FetchType.LAZY)
    @Column(name = "APLICACAO")
    @Type(type="text")
    private String aplicacao;
    
    public SefipCodigoMovimentacao() {
    }

    public SefipCodigoMovimentacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
