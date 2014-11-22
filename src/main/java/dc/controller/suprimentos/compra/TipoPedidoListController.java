package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.suprimentos.TipoPedido;
import dc.servicos.dao.suprimentos.TipoPedidoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoPedidoListController extends CRUDListController<TipoPedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	TipoPedidoDAO dao;

	@Autowired
	TipoPedidoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "id", "codigo", "nome", "descricao" };
	}

	@Override
	protected String getTitulo() {
		return "Tipo Pedido Compra";
	}

	@Override
	protected List<TipoPedido> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoPedido";
	}

	@Override
	protected CRUDFormController<TipoPedido> getFormController() {
		return formController;
	}

	@Override
	public Class<? super TipoPedido> getEntityClass() {
		return TipoPedido.class;
	}

	@Override
	protected List<TipoPedido> pesquisaDefault() {
		return dao.getAll(TipoPedido.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}