package dc.entidade.suprimentos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.produto.Produto;

@Entity
@Table(name = "estoque_reajuste_detalhe")
@SuppressWarnings("serial")
public class ReajusteEstoqueDetalhe extends AbstractMultiEmpresaModel<Integer> {

	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Caption("Id")
//	private Integer id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rjd")
	@SequenceGenerator(name = "rjd", sequenceName = "estoque_reajuste_detalhe_id_seq", allocationSize = 1)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_estoque_reajuste_cabecalho")
    private ReajusteEstoque reajuste;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@Column(name="valor_original")
	@Caption("Valor Original")
	private BigDecimal valorOriginal;
	
	@Column(name="valor_reajuste")
	@Caption("Valor Reajuste")
	private BigDecimal valorReajuste;

	
	public ReajusteEstoque getReajuste() {
		return reajuste;
	}

	public void setReajuste(ReajusteEstoque reajuste) {
		this.reajuste = reajuste;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	

	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(BigDecimal valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public BigDecimal getValorReajuste() {
		return valorReajuste;
	}

	public void setValorReajuste(BigDecimal valorReajuste) {
		this.valorReajuste = valorReajuste;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	
}
