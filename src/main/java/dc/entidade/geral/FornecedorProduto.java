package dc.entidade.geral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import dc.entidade.framework.AbstractMultiEmpresaModel;

/**
*
* @author Wesley Jr
/*
*Classe que possui o TO, ou seja, o mapeamento com todos os campos que vamos ter 
*no nosso Banco de Dados 
** Nessa classe temos o equals, hashCode e o ToString, no nosso novo mapeamento, pegamos
* e mudamos, está diferente do mapeamento do T2Ti.
* *
* Colocamos também algumas anotações, na classe e em alguns campos, onde temos as anotações
* que é o Field e Caption, o Caption colocamos o nome do campo que queremos que pesquise
* na Tela, pegando os dados que estão salvos no Banco de Dados.
*/

@Entity
@Table(name = "fornecedor_produto")
public class FornecedorProduto extends AbstractMultiEmpresaModel<Integer> implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "FORNECEDOR_ID", nullable = false)
    private int fornecedorId;
    
    @Basic(optional = false)
    @Column(name = "PRODUTO_ID", nullable = false)
    private int produtoId;
    
    @Column(name = "CODIGO_FORNECEDOR_PRODUTO")
    private String codigoFornecedorProduto;
    
    @Column(name = "DATA_ULTIMA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date dataUltimaCompra;
    
    @Column(name = "PRECO_ULTIMA_COMPRA", precision = 14, scale = 0)
    private BigDecimal precoUltimaCompra;
    
    @Column(name = "LOTE_MINIMO_COMPRA", precision = 14, scale = 0)
    private BigDecimal loteMinimoCompra;
    
    @Column(name = "MENOR_EMBALAGEM_COMPRA", precision = 14, scale = 0)
    private BigDecimal menorEmbalagemCompra;
    
    @Column(name = "CUSTO_ULTIMA_COMPRA", precision = 14, scale = 0)
    private BigDecimal custoUltimaCompra;
    
    public FornecedorProduto() {
    }

    public FornecedorProduto(Integer id) {
        this.id = id;
    }

    public FornecedorProduto(Integer id, int fornecedorId, int produtoId) {
        this.id = id;
        this.fornecedorId = fornecedorId;
        this.produtoId = produtoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }
    
    public String getCodigoFornecedorProduto() {
		return codigoFornecedorProduto;
	}

	public void setCodigoFornecedorProduto(String codigoFornecedorProduto) {
		this.codigoFornecedorProduto = codigoFornecedorProduto;
	}

	public Date getDataUltimaCompra() {
		return dataUltimaCompra;
	}

	public void setDataUltimaCompra(Date dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}

	public BigDecimal getPrecoUltimaCompra() {
		return precoUltimaCompra;
	}

	public void setPrecoUltimaCompra(BigDecimal precoUltimaCompra) {
		this.precoUltimaCompra = precoUltimaCompra;
	}

	public BigDecimal getLoteMinimoCompra() {
		return loteMinimoCompra;
	}

	public void setLoteMinimoCompra(BigDecimal loteMinimoCompra) {
		this.loteMinimoCompra = loteMinimoCompra;
	}

	public BigDecimal getMenorEmbalagemCompra() {
		return menorEmbalagemCompra;
	}

	public void setMenorEmbalagemCompra(BigDecimal menorEmbalagemCompra) {
		this.menorEmbalagemCompra = menorEmbalagemCompra;
	}

	public BigDecimal getCustoUltimaCompra() {
		return custoUltimaCompra;
	}

	public void setCustoUltimaCompra(BigDecimal custoUltimaCompra) {
		this.custoUltimaCompra = custoUltimaCompra;
	}

	@Override
    public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"id"});
    }

    @Override
    public boolean equals(Object object) {
    	if (object instanceof FornecedorProduto == false) return false;
    	if (this == object) return true;
    	final FornecedorProduto other = (FornecedorProduto) object;
    	return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }

}

