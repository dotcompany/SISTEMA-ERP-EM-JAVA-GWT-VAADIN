package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.servicos.dao.suprimentos.compra.PedidoCompraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class PedidoCompraListController extends
		CRUDListController<PedidoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PedidoCompraDAO dao;

	@Autowired
	private PedidoCompraFormController formController;

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
	protected List<PedidoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected CRUDFormController<PedidoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super PedidoEntity> getEntityClass() {
		return PedidoEntity.class;
	}

	@Override
	protected List<PedidoEntity> pesquisaDefault() {
		return dao.getAll(PedidoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}