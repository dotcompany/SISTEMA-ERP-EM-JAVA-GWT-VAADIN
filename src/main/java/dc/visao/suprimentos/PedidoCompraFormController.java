package dc.visao.suprimentos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.Fornecedor;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.PedidoCompra;
import dc.entidade.suprimentos.PedidoDetalhe;
import dc.entidade.suprimentos.TipoPedido;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.PedidoCompraDAO;
import dc.servicos.dao.suprimentos.TipoPedidoDAO;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class PedidoCompraFormController extends CRUDFormController<PedidoCompra> {

	PedidoCompraFormView subView;

	@Autowired
	PedidoCompraDAO pedidoCompraDAO;

	@Autowired
	ProdutoDAO produtoDAO;

	@Autowired
	FornecedorDAO fornecedorDAO;

	@Autowired
	TipoPedidoDAO tipoPedidoDAO;

	private PedidoCompra currentBean;

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
			currentBean.setTipoPedido((TipoPedido) subView.getCmbTipoPedido().getValue());
			currentBean.setFornecedor((Fornecedor) subView.getCmbFornecedor().getValue());
			currentBean.setDataPedido(subView.getCalDataPedido().getValue());
			currentBean.setDataPrevistaEntrega(subView.getCalDataEntrega().getValue());
			currentBean.setDataPrevisaoPagamento(subView.getCalDataPagamento().getValue());
			currentBean.setContato(subView.getTxtContato().getValue());
			currentBean.setLocalEntrega(subView.getTxtLocalEntrega().getValue());
			currentBean.setLocalCobranca(subView.getTxtLocalCobranca().getValue());
			currentBean.setValorSubtotal((BigDecimal) subView.getTxtValorSubTotal().getConvertedValue());
			currentBean.setTaxaDesconto((BigDecimal) subView.getTxtTaxaDesconto().getConvertedValue());
			currentBean.setValorDesconto((BigDecimal) subView.getTxtValorDesconto().getConvertedValue());
			currentBean.setValorTotalPedido((BigDecimal) subView.getTxtTotalPedido().getConvertedValue());
			currentBean.setTipoFrete((String) subView.getOptTipoFrete().getValue());
			currentBean.setFormaPagamento((String) subView.getOptFormaPagto().getValue());
			pedidoCompraDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
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
		subView.getCalDataEntrega().setValue(currentBean.getDataPrevistaEntrega());
		subView.getCalDataPagamento().setValue(currentBean.getDataPrevisaoPagamento());
		subView.getTxtContato().setValue(currentBean.getContato());
		subView.getTxtLocalEntrega().setValue(currentBean.getLocalEntrega());
		subView.getTxtLocalCobranca().setValue(currentBean.getLocalCobranca());
		subView.getTxtValorSubTotal().setConvertedValue(currentBean.getValorSubtotal());
		subView.getTxtTaxaDesconto().setConvertedValue(currentBean.getTaxaDesconto());
		subView.getTxtValorDesconto().setConvertedValue(currentBean.getValorDesconto());
		subView.getTxtTotalPedido().setConvertedValue(currentBean.getValorTotalPedido());
		subView.getOptTipoFrete().setValue(currentBean.getTipoFrete());
		subView.getOptFormaPagto().setValue(currentBean.getFormaPagamento());

		subView.fillPedidoDetalhesSubForm(currentBean.getPedidoDetalhes());
	}

	@Override
	protected void initSubView() {
		subView = new PedidoCompraFormView(this);
		subView.fillCmbFornecedor(fornecedorDAO.getAll(Fornecedor.class));
		subView.fillCmbTipoPedido(tipoPedidoDAO.getAll(TipoPedido.class));
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PedidoCompra();
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
		subView.fillPedidoDetalhesSubForm(currentBean.getPedidoDetalhes());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public List<Produto> buscarProdutos() {
		return produtoDAO.getAll(Produto.class);
	}

	public PedidoDetalhe novoPedidoDetalhe() {
		PedidoDetalhe pedidoDetalhe = new PedidoDetalhe();
		currentBean.addPedidoDetalhe(pedidoDetalhe);
		return pedidoDetalhe;
	}

	public void removerPedidoDetalhe(List<PedidoDetalhe> pedidoDetalhes) {
		for (PedidoDetalhe pedidoDetalhe : pedidoDetalhes) {
			currentBean.removePedidoDetalhe(pedidoDetalhe);
		}
	}

	@Override
	public String getViewIdentifier() {
		return "pedidoCompraForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

}
