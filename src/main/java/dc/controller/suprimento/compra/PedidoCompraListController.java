package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
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
	private PedidoCompraFormController pedidoCompraFormController;

	@Autowired
	private PedidoCompraDAO dao;

	/**
	 * CONSTRUTOR
	 */

	public PedidoCompraListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<PedidoEntity> getFormController() {
		return pedidoCompraFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "id", "dataPedido", "tipoPedido.descricao",
				"fornecedor.pessoa.nome", "contato" };
	}

	@Override
	public Class<? super PedidoEntity> getEntityClass() {
		return PedidoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PedidoEntity> pesquisa(String valor) {
		try {
			List<PedidoEntity> auxLista = (List<PedidoEntity>) this.dao
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<PedidoEntity> pesquisaDefault() {
		try {
			List<PedidoEntity> auxLista = (List<PedidoEntity>) this.dao
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}