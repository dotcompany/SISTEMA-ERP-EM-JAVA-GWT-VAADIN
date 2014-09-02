package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.EfdTabela4314;
import dc.servicos.dao.tabelas.EfdTabela4314DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class EfdTabela4314ListController extends CRUDListController<EfdTabela4314> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	EfdTabela4314DAO dao;

	@Autowired
	EfdTabela4314FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super EfdTabela4314> getEntityClass() {
		return EfdTabela4314.class;
	}

	@Override
	protected String getTitulo() {
		return "EFD Tabela 4314";
	}

	@Override
	protected List<EfdTabela4314> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<EfdTabela4314> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEfdTabela4314";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EfdTabela4314> pesquisaDefault() {
		return (List<EfdTabela4314>) dao.getAll(getEntityClass());
	}

}