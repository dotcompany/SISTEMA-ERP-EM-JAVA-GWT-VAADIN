package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.EfdTabela4315;
import dc.servicos.dao.geral.tabela.EfdTabela4315DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela4315ListController extends CRUDListController<EfdTabela4315> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EfdTabela4315DAO dao;

	@Autowired
	EfdTabela4315FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela4315> getEntityClass() {
		return EfdTabela4315.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 4315";
	}

	@Override
	protected List<EfdTabela4315> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela4315> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela4315";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela4315> pesquisaDefault() {
		return (List<EfdTabela4315>) dao.getAll(getEntityClass());
	}

}