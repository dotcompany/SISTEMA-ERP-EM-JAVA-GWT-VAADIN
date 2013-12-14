package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SpedPis4316;
import dc.servicos.dao.tabelas.SpedPis4316DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SpedPis4316ListController extends CRUDListController<SpedPis4316> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpedPis4316DAO dao;

	@Autowired
	SpedPis4316FormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

	@Override
	protected Class<? super SpedPis4316> getEntityClass() {
		return SpedPis4316.class;
	}

	@Override
	protected String getTitulo() {
		return "Sped Pis 4316";
	}

	@Override
	protected List<SpedPis4316> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SpedPis4316> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSpedPis4316";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SpedPis4316> pesquisaDefault() {
		return (List<SpedPis4316>) dao.getAll(getEntityClass());
	}

}