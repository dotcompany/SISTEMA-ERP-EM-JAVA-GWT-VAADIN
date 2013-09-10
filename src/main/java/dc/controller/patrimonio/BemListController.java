package dc.controller.patrimonio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.BemEntity;
import dc.servicos.dao.patrimonio.BemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class BemListController extends CRUDListController<BemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private BemDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private BemFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "nome", "estadoConservacao", "fornecedor" };
	}

	@Override
	protected Class<? super BemEntity> getEntityClass() {
		return BemEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Bem";
	}

	@Override
	protected List<BemEntity> pesquisa(String valor) {
		List<BemEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<BemEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_bem_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<BemEntity> pesquisaDefault() {
		List<BemEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}