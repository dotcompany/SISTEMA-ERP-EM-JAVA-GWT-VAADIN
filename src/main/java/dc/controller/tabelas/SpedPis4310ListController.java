package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SpedPis4310;
import dc.servicos.dao.tabelas.SpedPis4310DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SpedPis4310ListController extends CRUDListController<SpedPis4310> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SpedPis4310DAO dao;

	@Autowired
	SpedPis4310FormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao",
				"inicioVigencia", "fimVigencia" };
	}

	@Override
	protected Class<? super SpedPis4310> getEntityClass() {
		return SpedPis4310.class;
	}

	@Override
	protected String getTitulo() {
		return "Sped Pis 4310";
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		super.init();

		super.permissao(this, this.pController);
	}

	@Override
	protected List<SpedPis4310> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SpedPis4310> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSpedPis4310";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SpedPis4310> pesquisaDefault() {
		return (List<SpedPis4310>) dao.getAll(getEntityClass());
	}

}