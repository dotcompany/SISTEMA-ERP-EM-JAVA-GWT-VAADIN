package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SpedPis4314;
import dc.servicos.dao.tabelas.SpedPis4314DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SpedPis4314ListController extends CRUDListController<SpedPis4314> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpedPis4314DAO dao;

	@Autowired
	SpedPis4314FormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao", "inicioVigencia", "fimVigencia" };
	}

	@Override
	public Class<? super SpedPis4314> getEntityClass() {
		return SpedPis4314.class;
	}

	@Override
	protected String getTitulo() {
		return "Sped Pis 4314";
	}

	@Override
	protected List<SpedPis4314> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SpedPis4314> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSpedPis4314";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SpedPis4314> pesquisaDefault() {
		return (List<SpedPis4314>) dao.getAll(getEntityClass());
	}

}