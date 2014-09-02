package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.EfdTabela439;
import dc.servicos.dao.tabelas.EfdTabela439DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela439ListController extends CRUDListController<EfdTabela439> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EfdTabela439DAO dao;

	@Autowired
	EfdTabela439FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela439> getEntityClass() {
		return EfdTabela439.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 439";
	}

	@Override
	protected List<EfdTabela439> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela439> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela439";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela439> pesquisaDefault() {
		return (List<EfdTabela439>) dao.getAll(getEntityClass());
	}

}