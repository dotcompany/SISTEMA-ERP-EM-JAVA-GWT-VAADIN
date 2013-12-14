package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SefipCategoriaTrabalho;
import dc.servicos.dao.tabelas.SefipCategoriaTrabalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class SefipCategoriaTrabalhoListController extends
		CRUDListController<SefipCategoriaTrabalho> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SefipCategoriaTrabalhoDAO dao;

	@Autowired
	SefipCategoriaTrabalhoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "nome" };
	}

	@Override
	protected Class<? super SefipCategoriaTrabalho> getEntityClass() {
		return SefipCategoriaTrabalho.class;
	}

	@Override
	protected String getTitulo() {
		return "Sefip Categoria de Trabalho";
	}

	@Override
	protected List<SefipCategoriaTrabalho> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SefipCategoriaTrabalho> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSefipCategoriaTrabalho";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SefipCategoriaTrabalho> pesquisaDefault() {
		return (List<SefipCategoriaTrabalho>) dao.getAll(getEntityClass());
	}

}