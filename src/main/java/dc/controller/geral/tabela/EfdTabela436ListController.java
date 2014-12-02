package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.EfdTabela436;
import dc.servicos.dao.geral.tabela.EfdTabela436DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela436ListController extends CRUDListController<EfdTabela436> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EfdTabela436DAO dao;

	@Autowired
	EfdTabela436FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super EfdTabela436> getEntityClass() {
		return EfdTabela436.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 436";
	}

	@Override
	protected List<EfdTabela436> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela436> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela436";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela436> pesquisaDefault() {
		return (List<EfdTabela436>) dao.getAll(getEntityClass());
	}

}