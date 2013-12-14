package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.TipoItemSped;
import dc.servicos.dao.tabelas.TipoItemSpedDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class TipoItemSpedListController extends
		CRUDListController<TipoItemSped> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	TipoItemSpedDAO dao;

	@Autowired
	TipoItemSpedFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	protected Class<? super TipoItemSped> getEntityClass() {
		return TipoItemSped.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Item Seped";
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();

		super.permissao(this, this.pController);
	}

	@Override
	protected List<TipoItemSped> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TipoItemSped> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTipoItemSeped";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TipoItemSped> pesquisaDefault() {
		return (List<TipoItemSped>) dao.getAll(getEntityClass());
	}

}