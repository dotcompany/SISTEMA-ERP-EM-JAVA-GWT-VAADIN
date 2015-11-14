package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.compra.PedidoDetalheEntity;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.entidade.suprimentos.compra.TipoPedidoEntity;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.suprimentos.compra.IPedidoCompraDAO;
import dc.servicos.dao.suprimentos.compra.ITipoPedidoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.PedidoCompraFormView;

@Controller
@Scope("prototype")
public class PedidoCompraFormController extends
		CRUDFormController<PedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PedidoCompraFormView subView;

	@Autowired
	private IPedidoCompraDAO pedidoCompraDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	@Autowired
	private IFornecedorDAO fornecedorDAO;

	@Autowired
	private ITipoPedidoDAO tipoPedidoDAO;

	private PedidoEntity currentBean;

	@Override
	protected String getNome() {
		return "Pedido de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setTipoPedido((TipoPedidoEntity) subView
					.getCmbTipoPedido().getValue());
			currentBean.setFornecedor((FornecedorEntity) subView
					.getCmbFornecedor().getValue());
			currentBean.setDataPedido(subView.getCalDataPedido().getValue());
			currentBean.setDataPrevistaEntrega(subView.getCalDataEntrega()
					.getValue());
			currentBean.setDataPrevisaoPagamento(subView.getCalDataPagamento()
					.getValue());
			currentBean.setContato(subView.getTxtContato().getValue());
			currentBean
					.setLocalEntrega(subView.getTxtLocalEntrega().getValue());
			currentBean.setLocalCobranca(subView.getTxtLocalCobranca()
					.getValue());
			currentBean.setValorSubtotal((BigDecimal) subView
					.getTxtValorSubTotal().getConvertedValue());
			currentBean.setTaxaDesconto((BigDecimal) subView
					.getTxtTaxaDesconto().getConvertedValue());
			currentBean.setValorDesconto((BigDecimal) subView
					.getTxtValorDesconto().getConvertedValue());
			currentBean.setValorTotalPedido((BigDecimal) subView
					.getTxtTotalPedido().getConvertedValue());
			currentBean.setTipoFrete((String) subView.getOptTipoFrete()
					.getValue());
			currentBean.setFormaPagamento((String) subView.getOptFormaPagto()
					.getValue());
			pedidoCompraDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = pedidoCompraDAO.find(id);
		subView.getLblId().setValue(String.valueOf(currentBean.getId()));
		subView.getCmbTipoPedido().setValue(currentBean.getTipoPedido());
		subView.getCmbFornecedor().setValue(currentBean.getFornecedor());
		subView.getCalDataPedido().setValue(currentBean.getDataPedido());
		subView.getCalDataEntrega().setValue(
				currentBean.getDataPrevistaEntrega());
		subView.getCalDataPagamento().setValue(
				currentBean.getDataPrevisaoPagamento());
		subView.getTxtContato().setValue(currentBean.getContato());
		subView.getTxtLocalEntrega().setValue(currentBean.getLocalEntrega());
		subView.getTxtLocalCobranca().setValue(currentBean.getLocalCobranca());
		subView.getTxtValorSubTotal().setConvertedValue(
				currentBean.getValorSubtotal());
		subView.getTxtTaxaDesconto().setConvertedValue(
				currentBean.getTaxaDesconto());
		subView.getTxtValorDesconto().setConvertedValue(
				currentBean.getValorDesconto());
		subView.getTxtTotalPedido().setConvertedValue(
				currentBean.getValorTotalPedido());
		subView.getOptTipoFrete().setValue(currentBean.getTipoFrete());
		subView.getOptFormaPagto().setValue(currentBean.getFormaPagamento());

		subView.fillPedidoDetalhesSubForm(currentBean.getPedidoDetalhe());
	}

	@Override
	protected void initSubView() {
		subView = new PedidoCompraFormView(this);
		subView.fillCmbFornecedor(fornecedorDAO.getAll(FornecedorEntity.class));
		subView.fillCmbTipoPedido(tipoPedidoDAO.getAll(TipoPedidoEntity.class));
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PedidoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pedidoCompraDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
		subView.fillPedidoDetalhesSubForm(currentBean.getPedidoDetalhe());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public PedidoDetalheEntity novoPedidoDetalhe() {
		PedidoDetalheEntity pedidoDetalhe = new PedidoDetalheEntity();
		// currentBean.addPedidoDetalhe(pedidoDetalhe);

		return pedidoDetalhe;
	}

	public void removerPedidoDetalhe(List<PedidoDetalheEntity> pedidoDetalhe) {
		for (PedidoDetalheEntity ent : pedidoDetalhe) {
			// currentBean.removePedidoDetalhe(ent);
		}
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public PedidoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}