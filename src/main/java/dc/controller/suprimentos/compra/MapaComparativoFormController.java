package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.Fornecedor;
import dc.entidade.suprimentos.Cotacao;
import dc.entidade.suprimentos.CotacaoDetalhe;
import dc.entidade.suprimentos.FornecedorCotacao;
import dc.entidade.suprimentos.PedidoCompra;
import dc.entidade.suprimentos.PedidoDetalhe;
import dc.entidade.suprimentos.RequisicaoCotacaoDetalhe;
import dc.entidade.suprimentos.RequisicaoDetalhe;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.suprimentos.CotacaoDAO;
import dc.servicos.dao.suprimentos.PedidoCompraDAO;
import dc.servicos.dao.suprimentos.RequisicaoDetalheDAO;
import dc.servicos.dao.suprimentos.TipoPedidoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.MapaComparativoFormView;

@Controller
@Scope("prototype")
public class MapaComparativoFormController extends CRUDFormController<Cotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MapaComparativoFormView subView;

	@Autowired
	CotacaoDAO cotacaoDao;

	@Autowired
	PedidoCompraDAO pedidoCompraDAO;

	@Autowired
	FornecedorDAO fornecedorDao;

	@Autowired
	RequisicaoDetalheDAO requisicaoDetalheDao;

	@Autowired
	TipoPedidoDAO tipoPedidoDAO;

	private Cotacao currentBean;

	@Override
	protected String getNome() {
		return "Mapa Comparativo";
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setDataCotacao(subView.getCalDataCotacao().getValue());

			List<FornecedorCotacao> fornecedores = currentBean
					.getCompraFornecedorCotacaos();

			for (FornecedorCotacao cotacao : fornecedores) {
				List<CotacaoDetalhe> cotacaoDetalhes = cotacao
						.getCotacaoDetalhes();

				PedidoCompra pedidoCompra = null;

				for (CotacaoDetalhe detalhe : cotacaoDetalhes) {
					boolean existePedido = pedidoCompraDAO
							.existsPedidoDetalheByCotacao(detalhe.getId());

					if (!existePedido) {
						if (pedidoCompra == null) {
							pedidoCompra = new PedidoCompra();
							pedidoCompra.setTipoPedido(tipoPedidoDAO.find(4));
							pedidoCompra.setFornecedor(cotacao.getFornecedor());
							pedidoCompra.setTaxaDesconto(cotacao
									.getTaxaDesconto());
							pedidoCompra
									.setValorTotalPedido(cotacao.getTotal());
							pedidoCompra.setValorDesconto(cotacao
									.getValorDesconto());
							pedidoCompra.setValorSubtotal(cotacao
									.getValorSubtotal());
							pedidoCompra.setFormaPagamento(cotacao
									.getVendaCondicoesPagamento());
						}

						PedidoDetalhe pedidoDetalhe = new PedidoDetalhe();
						pedidoDetalhe.setProduto(detalhe.getProduto());
						pedidoDetalhe.setQuantidade(detalhe
								.getQuantidadePedida());
						pedidoDetalhe.setValorUnitario(detalhe
								.getValorUnitario());
						pedidoDetalhe
								.setTaxaDesconto(detalhe.getTaxaDesconto());
						pedidoDetalhe.setValorDesconto(detalhe
								.getValorDesconto());
						pedidoDetalhe.setValorSubtotal(detalhe
								.getValorUnitario().multiply(
										detalhe.getQuantidadePedida()));
						pedidoDetalhe.setValorTotal(pedidoDetalhe
								.getValorSubtotal().subtract(
										pedidoDetalhe.getValorDesconto()));

						pedidoCompra.addPedidoDetalhe(pedidoDetalhe);
					}
				}

				pedidoCompraDAO.save(pedidoCompra);
			}

			cotacaoDao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cotacaoDao.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getCalDataCotacao().setValue(currentBean.getDataCotacao());

		subView.fillCompraFornecedorCotacoesSubForm(currentBean
				.getCompraFornecedorCotacaos());
	}

	@Override
	protected void initSubView() {
		subView = new MapaComparativoFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Cotacao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cotacaoDao.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
		subView.fillCompraFornecedorCotacoesSubForm(currentBean
				.getCompraFornecedorCotacaos());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public List<RequisicaoDetalhe> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoDetalhe.class);
	}

	public RequisicaoCotacaoDetalhe novoRequisicaoCotacaoDetalhe() {
		RequisicaoCotacaoDetalhe cotacaoDetalhe = new RequisicaoCotacaoDetalhe();
		currentBean.addCompraReqCotacaoDetalhe(cotacaoDetalhe);

		return cotacaoDetalhe;
	}

	public void removerRequisicaoCotacaoDetalhes(
			List<RequisicaoCotacaoDetalhe> values) {
		for (RequisicaoCotacaoDetalhe requisicaoCotacaoDetalhe : values) {
			currentBean.removeCompraReqCotacaoDetalhe(requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	public List<Fornecedor> buscarFornecedores() {
		return fornecedorDao.getAll(Fornecedor.class);
	}

	public FornecedorCotacao novoFornecedorCotacao() {
		FornecedorCotacao fornecedorCotacao = new FornecedorCotacao();
		currentBean.addCompraFornecedorCotacao(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacao> values) {
		for (FornecedorCotacao fornecedorCotacao : values) {
			currentBean.removeCompraFornecedorCotacao(fornecedorCotacao);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "mapaComparativoForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public Cotacao getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}