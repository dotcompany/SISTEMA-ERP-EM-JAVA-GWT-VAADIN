package dc.entidade.suprimentos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dc.entidade.framework.AbstractModel;


/**
 * The persistent class for the compra_req_cotacao_detalhe database table.
 * 
 */
@Entity
@Table(name="compra_req_cotacao_detalhe")
public class RequisicaoCotacaoDetalhe extends AbstractModel<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="quantidade_cotada")
	private BigDecimal quantidadeCotada;

	@ManyToOne
	@JoinColumn(name="id_compra_cotacao")
	private Cotacao cotacao;

	@ManyToOne
	@JoinColumn(name="id_compra_requisicao_detalhe")
	private RequisicaoDetalhe requisicaoDetalhe;

	public RequisicaoCotacaoDetalhe() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidadeCotada() {
		return this.quantidadeCotada;
	}

	public void setQuantidadeCotada(BigDecimal quantidadeCotada) {
		this.quantidadeCotada = quantidadeCotada;
	}

	public Cotacao getCotacao() {
		return this.cotacao;
	}

	public void setCotacao(Cotacao compraCotacao) {
		this.cotacao = compraCotacao;
	}

	public RequisicaoDetalhe getRequisicaoDetalhe() {
		return this.requisicaoDetalhe;
	}

	public void setRequisicaoDetalhe(RequisicaoDetalhe compraRequisicaoDetalhe) {
		this.requisicaoDetalhe = compraRequisicaoDetalhe;
	}

}