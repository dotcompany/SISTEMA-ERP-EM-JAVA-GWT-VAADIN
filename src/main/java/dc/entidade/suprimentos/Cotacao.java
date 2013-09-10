package dc.entidade.suprimentos;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import dc.anotacoes.Caption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the compra_cotacao database table.
 * 
 */
@Entity
@Table(name="compra_cotacao")
public class Cotacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Caption("Id")
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="data_cotacao")
	@Caption("Data da cotação")
	private Date dataCotacao;

	@Caption("Descrição")
	private String descricao;

	@Caption("Situação")
	private String situacao;

	@OneToMany(mappedBy="cotacao", cascade=CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<FornecedorCotacao> compraFornecedorCotacaos = new ArrayList<>();

	@OneToMany(mappedBy="cotacao", cascade=CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	private List<RequisicaoCotacaoDetalhe> compraReqCotacaoDetalhes = new ArrayList<>();

	public Integer getId() {
		return this.id;
	}

	public Date getDataCotacao() {
		return this.dataCotacao;
	}

	public void setDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<FornecedorCotacao> getCompraFornecedorCotacaos() {
		return this.compraFornecedorCotacaos;
	}

	public void setCompraFornecedorCotacaos(List<FornecedorCotacao> compraFornecedorCotacaos) {
		this.compraFornecedorCotacaos = compraFornecedorCotacaos;
	}

	public FornecedorCotacao addCompraFornecedorCotacao(FornecedorCotacao compraFornecedorCotacao) {
		getCompraFornecedorCotacaos().add(compraFornecedorCotacao);
		compraFornecedorCotacao.setCotacao(this);

		return compraFornecedorCotacao;
	}

	public FornecedorCotacao removeCompraFornecedorCotacao(FornecedorCotacao compraFornecedorCotacao) {
		getCompraFornecedorCotacaos().remove(compraFornecedorCotacao);
		compraFornecedorCotacao.setCotacao(null);

		return compraFornecedorCotacao;
	}

	public List<RequisicaoCotacaoDetalhe> getCompraReqCotacaoDetalhes() {
		return this.compraReqCotacaoDetalhes;
	}

	public void setCompraReqCotacaoDetalhes(List<RequisicaoCotacaoDetalhe> compraReqCotacaoDetalhes) {
		this.compraReqCotacaoDetalhes = compraReqCotacaoDetalhes;
	}

	public RequisicaoCotacaoDetalhe addCompraReqCotacaoDetalhe(RequisicaoCotacaoDetalhe compraReqCotacaoDetalhe) {
		getCompraReqCotacaoDetalhes().add(compraReqCotacaoDetalhe);
		compraReqCotacaoDetalhe.setCotacao(this);

		return compraReqCotacaoDetalhe;
	}

	public RequisicaoCotacaoDetalhe removeCompraReqCotacaoDetalhe(RequisicaoCotacaoDetalhe compraReqCotacaoDetalhe) {
		getCompraReqCotacaoDetalhes().remove(compraReqCotacaoDetalhe);
		compraReqCotacaoDetalhe.setCotacao(null);

		return compraReqCotacaoDetalhe;
	}

}