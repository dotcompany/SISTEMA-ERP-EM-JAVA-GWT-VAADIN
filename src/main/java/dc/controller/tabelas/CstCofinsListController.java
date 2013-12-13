package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.CstCofins;
import dc.servicos.dao.tabelas.CstCofinsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstCofinsListController extends CRUDListController<CstCofins> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CstCofinsDAO dao;

	@Autowired
	CstCofinsFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	protected Class<? super CstCofins> getEntityClass() {
		return CstCofins.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Cofins";
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();

		this.setEnabled(false);
		this.pController.setEnabled(false);
	}

	@Override
	protected List<CstCofins> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstCofins> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstCofins";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstCofins> pesquisaDefault() {
		return (List<CstCofins>) dao.getAll(getEntityClass());
	}

}