package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.Csosnb;
import dc.servicos.dao.tabelas.CsosnbDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Controller
@Scope("prototype")
public class CsosnbListController extends CRUDListController<Csosnb> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CsosnbDAO dao;

	@Autowired
	CsosnbFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	protected Class<? super Csosnb> getEntityClass() {
		return Csosnb.class;
	}

	@Override
	protected String getTitulo() {
		return "Csosnb";
	}

	@Override
	protected List<Csosnb> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Csosnb> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCsosnb";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Csosnb> pesquisaDefault() {
		return (List<Csosnb>) dao.getAll(getEntityClass());
	}

}