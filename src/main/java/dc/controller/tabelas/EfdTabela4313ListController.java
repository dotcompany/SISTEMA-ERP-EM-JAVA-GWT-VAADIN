package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.EfdTabela4313;
import dc.servicos.dao.tabelas.EfdTabela4313DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela4313ListController extends CRUDListController<EfdTabela4313> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EfdTabela4313DAO dao;

	@Autowired
	EfdTabela4313FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela4313> getEntityClass() {
		return EfdTabela4313.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 4313";
	}

	@Override
	protected List<EfdTabela4313> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela4313> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela4313";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela4313> pesquisaDefault() {
		return (List<EfdTabela4313>) dao.getAll(getEntityClass());
	}

}