package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.EfdTabela437;
import dc.servicos.dao.geral.tabela.EfdTabela437DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela437ListController extends CRUDListController<EfdTabela437> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EfdTabela437DAO dao;

	@Autowired
	EfdTabela437FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super EfdTabela437> getEntityClass() {
		return EfdTabela437.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 437";
	}

	@Override
	protected List<EfdTabela437> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela437> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela437";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela437> pesquisaDefault() {
		return (List<EfdTabela437>) dao.getAll(getEntityClass());
	}

}