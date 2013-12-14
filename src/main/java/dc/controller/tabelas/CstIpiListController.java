package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.CstIpi;
import dc.servicos.dao.tabelas.CstIpiDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CstIpiListController extends CRUDListController<CstIpi> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CstIpiDAO dao;

	@Autowired
	CstIpiFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	protected Class<? super CstIpi> getEntityClass() {
		return CstIpi.class;
	}

	@Override
	protected String getTitulo() {
		return "Cst Ipi";
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();

		super.permissao(this, this.pController);
	}

	@Override
	protected List<CstIpi> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CstIpi> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstIpi";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstIpi> pesquisaDefault() {
		return (List<CstIpi>) dao.getAll(getEntityClass());
	}

}