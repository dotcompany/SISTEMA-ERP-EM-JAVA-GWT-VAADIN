package dc.controller.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.planoconta.PlanoContaRefSpedEntity;
import dc.servicos.dao.contabilidade.planoconta.PlanoContaSpedDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class PlanoContaSpedListController extends
		CRUDListController<PlanoContaRefSpedEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PlanoContaSpedDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PlanoContaSpedFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super PlanoContaRefSpedEntity> getEntityClass() {
		return PlanoContaRefSpedEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano conta - SPED";
	}

	@Override
	protected List<PlanoContaRefSpedEntity> pesquisa(String valor) {
		try {
			List<PlanoContaRefSpedEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaRefSpedEntity>();
		}
	}

	@Override
	protected CRUDFormController<PlanoContaRefSpedEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PlanoContaRefSpedEntity> pesquisaDefault() {
		try {
			List<PlanoContaRefSpedEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaRefSpedEntity>();
		}
	}

}