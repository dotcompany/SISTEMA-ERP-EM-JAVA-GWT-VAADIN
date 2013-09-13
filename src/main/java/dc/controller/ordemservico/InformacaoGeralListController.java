package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class InformacaoGeralListController extends CRUDListController<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private InformacaoGeralFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super Object> getEntityClass() {
		return Object.class;
	}

	@Override
	protected String getTitulo() {
		return "Informação geral";
	}

	@Override
	protected List<Object> pesquisa(String valor) {

		return null;
	}

	@Override
	protected CRUDFormController<Object> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "ordemservico_informacaogeral_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Object> pesquisaDefault() {

		return null;
	}

}