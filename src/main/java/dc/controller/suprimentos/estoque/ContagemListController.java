package dc.controller.suprimentos.estoque;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.servicos.dao.suprimentos.estoque.ContagemCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ContagemListController extends
		CRUDListController<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ContagemCabecalhoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ContagemFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "estoqueAtualizado" };
	}

	@Override
	public Class<? super ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Contagem de estoque";
	}

	@Override
	protected List<ContagemCabecalhoEntity> pesquisa(String valor) {
		List<ContagemCabecalhoEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<ContagemCabecalhoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ContagemCabecalhoEntity> pesquisaDefault() {
		List<ContagemCabecalhoEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}