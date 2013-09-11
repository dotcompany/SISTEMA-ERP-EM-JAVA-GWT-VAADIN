package dc.entidade.suprimentos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dc.entidade.framework.AbstractModel;
import dc.entidade.produto.Produto;

@Entity
@Table(name = "requisicao_interna_detalhe")
@SuppressWarnings("serial")
public class RequisicaoInternaDetalhe extends AbstractModel<Integer> {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req")
	@SequenceGenerator(name = "req", sequenceName = "requisicao_interna_detalhe_id_seq", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name="id_req_interna_cabecalho")
	RequisicaoInterna requisicao;

	Integer quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RequisicaoInterna getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(RequisicaoInterna requisicao) {
		this.requisicao = requisicao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
