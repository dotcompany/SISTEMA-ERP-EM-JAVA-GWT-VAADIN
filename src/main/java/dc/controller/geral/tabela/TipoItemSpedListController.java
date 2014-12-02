package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.TipoItemSped;
import dc.servicos.dao.geral.tabela.TipoItemSpedDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class TipoItemSpedListController extends CRUDListController<TipoItemSped> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	TipoItemSpedDAO dao;

	@Autowired
	TipoItemSpedFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super TipoItemSped> getEntityClass() {
		return TipoItemSped.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Item Sped";
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