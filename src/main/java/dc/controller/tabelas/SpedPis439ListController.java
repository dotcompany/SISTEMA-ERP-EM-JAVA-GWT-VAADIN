package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SpedPis439;
import dc.servicos.dao.tabelas.SpedPis439DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SpedPis439ListController extends CRUDListController<SpedPis439> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpedPis439DAO dao;

	@Autowired
	SpedPis439FormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

	@Override
	protected Class<? super SpedPis439> getEntityClass() {
		return SpedPis439.class;
	}

	@Override
	protected String getTitulo() {
		return "Sped Pis 439";
	}

	@Override
	protected List<SpedPis439> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SpedPis439> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSpedPis439";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SpedPis439> pesquisaDefault() {
		return (List<SpedPis439>) dao.getAll(getEntityClass());
	}

}