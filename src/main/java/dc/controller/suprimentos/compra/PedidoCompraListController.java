package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.PedidoCompra;
import dc.servicos.dao.suprimentos.PedidoCompraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class PedidoCompraListController extends
		CRUDListController<PedidoCompra> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PedidoCompraDAO dao;

	@Autowired
	PedidoCompraFormController formController;

	/*
	 * @Override protected void initReports() { addReport(new
	 * ReportController()); }
	 */

	@Override
	public String[] getColunas() {
		return new String[] { "id", "dataPedido", "tipoPedido.descricao",
				"fornecedor.pessoa.nome", "contato" };
	}

	@Override
	protected String getTitulo() {
		return "Pedido de Compra";
	}

	@Override
	protected List<PedidoCompra> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected CRUDFormController<PedidoCompra> getFormController() {
		return formController;
	}

	@Override
	public Class<? super PedidoCompra> getEntityClass() {
		return PedidoCompra.class;
	}

	@Override
	protected List<PedidoCompra> pesquisaDefault() {
		return dao.getAll(PedidoCompra.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}