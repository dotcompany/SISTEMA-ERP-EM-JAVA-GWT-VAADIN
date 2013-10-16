package dc.controller.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.planoconta.PlanoContaEntity;
import dc.servicos.dao.contabilidade.planoconta.PlanoContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller(value = "contabilidadePlanoContaListController")
@Scope("prototype")
public class PlanoContaListController extends
		CRUDListController<PlanoContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PlanoContaDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PlanoContaFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super PlanoContaEntity> getEntityClass() {
		return PlanoContaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano conta";
	}

	@Override
	protected List<PlanoContaEntity> pesquisa(String valor) {
		try {
			List<PlanoContaEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaEntity>();
		}
	}

	@Override
	protected CRUDFormController<PlanoContaEntity> getFormController() {
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
	protected List<PlanoContaEntity> pesquisaDefault() {
		try {
			List<PlanoContaEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoContaEntity>();
		}
	}

}