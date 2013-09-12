package dc.controller.patrimonio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.DepreciacaoBemEntity;
import dc.servicos.dao.patrimonio.DepreciacaoBemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class DepreciacaoBemListController extends
		CRUDListController<DepreciacaoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private DepreciacaoBemDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DepreciacaoBemFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "numero", "bem.nome", "seguradora.nome" };
	}

	@Override
	protected Class<? super DepreciacaoBemEntity> getEntityClass() {
		return DepreciacaoBemEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Depreciação do bem";
	}

	@Override
	protected List<DepreciacaoBemEntity> pesquisa(String valor) {
		try {
			List<DepreciacaoBemEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DepreciacaoBemEntity>();
		}
	}

	@Override
	protected CRUDFormController<DepreciacaoBemEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_depreciacao_bem_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<DepreciacaoBemEntity> pesquisaDefault() {
		try {
			List<DepreciacaoBemEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<DepreciacaoBemEntity>();
		}
	}

}