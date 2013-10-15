package dc.controller.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.planoconta.ContaContabilEntity;
import dc.servicos.dao.contabilidade.planoconta.ContaContabilDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ContaContabilListController extends
		CRUDListController<ContaContabilEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ContaContabilDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ContaContabilFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super ContaContabilEntity> getEntityClass() {
		return ContaContabilEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Conta contábil";
	}

	@Override
	protected List<ContaContabilEntity> pesquisa(String valor) {
		try {
			List<ContaContabilEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ContaContabilEntity>();
		}
	}

	@Override
	protected CRUDFormController<ContaContabilEntity> getFormController() {
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
	protected List<ContaContabilEntity> pesquisaDefault() {
		try {
			List<ContaContabilEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ContaContabilEntity>();
		}
	}

}