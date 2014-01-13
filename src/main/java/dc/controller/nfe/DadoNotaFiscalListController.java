package dc.controller.nfe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.servicos.dao.nfe.NfeCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class DadoNotaFiscalListController extends
		CRUDListController<NfeCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeCabecalhoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DadoNotaFiscalFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigoNumerico" };
	}

	@Override
	protected Class<? super Object> getEntityClass() {
		return Object.class;
	}

	@Override
	protected String getTitulo() {
		return "Dados NF-e";
	}

	@Override
	protected List<NfeCabecalhoEntity> pesquisa(String valor) {
		List<NfeCabecalhoEntity> auxLista = this.pDAO.fullTextSearch(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<NfeCabecalhoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<NfeCabecalhoEntity> pesquisaDefault() {
		List<NfeCabecalhoEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}