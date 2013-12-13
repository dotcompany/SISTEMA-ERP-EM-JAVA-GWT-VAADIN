package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.CBO;
import dc.servicos.dao.tabelas.CBODAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CBOListController extends CRUDListController<CBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CBODAO dao;

	@Autowired
	CBOFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "nome", "observacao" };
	}

	@Override
	protected Class<? super CBO> getEntityClass() {
		return CBO.class;
	}

	@Override
	protected String getTitulo() {
		return "CBO";
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();

		this.setEnabled(false);
		this.pController.setEnabled(false);
	}

	@Override
	protected List<CBO> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CBO> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCBO";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CBO> pesquisaDefault() {
		return (List<CBO>) dao.getAll(getEntityClass());
	}

}