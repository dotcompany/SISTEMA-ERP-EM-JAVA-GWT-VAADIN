package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.geral.Fornecedor;
import dc.entidade.suprimentos.compra.Cotacao;
import dc.entidade.suprimentos.compra.CotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.suprimentos.RequisicaoDetalheDAO;
import dc.servicos.dao.suprimentos.compra.CotacaoDAO;
import dc.servicos.dao.suprimentos.compra.PedidoCompraDAO;
import dc.servicos.dao.suprimentos.compra.TipoPedidoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.MapaComparativoFormView;

@Controller
@Scope("prototype")
public class MapaComparativoFormController extends CRUDFormController<Cotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MapaComparativoFormView subView;

	@Autowired
	private CotacaoDAO cotacaoDao;

	@Autowired
	private PedidoCompraDAO pedidoCompraDAO;

	@Autowired
	private FornecedorDAO fornecedorDao;

	@Autowired
	private RequisicaoDetalheDAO requisicaoDetalheDao;

	@Autowired
	private TipoPedidoDAO tipoPedidoDAO;

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

			List<FornecedorCotacaoEntity> fornecedores = currentBean
					.getCompraFornecedorCotacaos();

			for (FornecedorCotacaoEntity cotacao : fornecedores) {
				List<CotacaoDetalheEntity> cotacaoDetalhes = cotacao
						.getCotacaoDetalhes();

				PedidoEntity pedidoCompra = null;

				for (CotacaoDetalheEntity detalhe : cotacaoDetalhes) {
					boolean existePedido = pedidoCompraDAO
							.existsPedidoDetalheByCotacao(detalhe.getId());

					if (!existePedido) {
						if (pedidoCompra == null) {
							pedidoCompra = new PedidoEntity();
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

						PedidoDetalheEntity pedidoDetalhe = new PedidoDetalheEntity();
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

	public List<RequisicaoDetalheEntity> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoDetalheEntity.class);
	}

	public ReqCotacaoDetalheEntity novoRequisicaoCotacaoDetalhe() {
		ReqCotacaoDetalheEntity cotacaoDetalhe = new ReqCotacaoDetalheEntity();
		currentBean.getCompraReqCotacaoDetalhes().add(cotacaoDetalhe);

		return cotacaoDetalhe;
	}

	public void removerRequisicaoCotacaoDetalhes(
			List<ReqCotacaoDetalheEntity> values) {
		for (ReqCotacaoDetalheEntity requisicaoCotacaoDetalhe : values) {
			currentBean.getCompraReqCotacaoDetalhes().remove(
					requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	public List<Fornecedor> buscarFornecedores() {
		return fornecedorDao.getAll(Fornecedor.class);
	}

	public FornecedorCotacaoEntity novoFornecedorCotacao() {
		FornecedorCotacaoEntity fornecedorCotacao = new FornecedorCotacaoEntity();
		currentBean.getCompraFornecedorCotacaos().add(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacaoEntity> values) {
		for (FornecedorCotacaoEntity fornecedorCotacao : values) {
			currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
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