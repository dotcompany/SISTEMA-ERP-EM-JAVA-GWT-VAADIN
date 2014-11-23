package dc.controller.suprimentos.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.estoque.EstoqueContagemEntity;
import dc.servicos.dao.suprimentos.estoque.EstoqueContagemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EstoqueContagemListController extends
		CRUDListController<EstoqueContagemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EstoqueContagemDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EstoqueContagemFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "estoqueAtualizado" };
	}

	@Override
	public Class<? super EstoqueContagemEntity> getEntityClass() {
		return EstoqueContagemEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Contagem de estoque";
	}

	@Override
	protected List<EstoqueContagemEntity> pesquisa(String valor) {
		List<EstoqueContagemEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<EstoqueContagemEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EstoqueContagemEntity> pesquisaDefault() {
		List<EstoqueContagemEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}