package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.GrupoBemEntity;
import dc.servicos.dao.patrimonio.GrupoBemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class GrupoBemListController extends CRUDListController<GrupoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private GrupoBemDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private GrupoBemFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "nome", "empresa.nomeFantasia" };
	}

	@Override
	protected Class<? super GrupoBemEntity> getEntityClass() {
		return GrupoBemEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Grupo do bem";
	}

	@Override
	protected List<GrupoBemEntity> pesquisa(String valor) {
		try {
			List<GrupoBemEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<GrupoBemEntity>();
		}
	}

	@Override
	protected CRUDFormController<GrupoBemEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_grupo_bem_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<GrupoBemEntity> pesquisaDefault() {
		try {
			List<GrupoBemEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<GrupoBemEntity>();
		}
	}

}