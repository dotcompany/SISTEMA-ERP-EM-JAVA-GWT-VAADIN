package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.CstIcmsA;
import dc.servicos.dao.geral.tabela.CstIcmsADAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstIcmsAListController extends CRUDListController<CstIcmsA> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CstIcmsADAO dao;

	@Autowired
	CstIcmsAFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super CstIcmsA> getEntityClass() {
		return CstIcmsA.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Icms A";
	}

	@Override
	protected List<CstIcmsA> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstIcmsA> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstIcmsA";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstIcmsA> pesquisaDefault() {
		return (List<CstIcmsA>) dao.getAll(getEntityClass());
	}

}