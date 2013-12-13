package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SpedPis4313;
import dc.servicos.dao.tabelas.SpedPis4313DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SpedPis4313ListController extends CRUDListController<SpedPis4313> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpedPis4313DAO dao;

	@Autowired
	SpedPis4313FormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

	@Override
	protected Class<? super SpedPis4313> getEntityClass() {
		return SpedPis4313.class;
	}

	@Override
	protected String getTitulo() {
		return "Sped Pis 4313";
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();

		this.setEnabled(false);
		this.pController.setEnabled(false);
	}

	@Override
	protected List<SpedPis4313> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SpedPis4313> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSpedPis4313";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SpedPis4313> pesquisaDefault() {
		return (List<SpedPis4313>) dao.getAll(getEntityClass());
	}

}