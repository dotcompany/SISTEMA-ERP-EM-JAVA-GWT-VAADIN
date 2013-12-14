package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.CstIcmsB;
import dc.servicos.dao.tabelas.CstIcmsBDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstIcmsBListController extends CRUDListController<CstIcmsB> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CstIcmsBDAO dao;

	@Autowired
	CstIcmsBFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	protected Class<? super CstIcmsB> getEntityClass() {
		return CstIcmsB.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Icms B";
	}

	@Override
	protected List<CstIcmsB> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstIcmsB> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstIcmsB";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstIcmsB> pesquisaDefault() {
		return (List<CstIcmsB>) dao.getAll(getEntityClass());
	}

}