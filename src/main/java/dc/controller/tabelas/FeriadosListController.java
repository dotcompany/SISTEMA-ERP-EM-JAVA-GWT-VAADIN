package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.Feriados;
import dc.servicos.dao.tabelas.FeriadosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class FeriadosListController extends CRUDListController<Feriados> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FeriadosDAO feriadosDAO;

	@Autowired
	private FeriadosFormController feriadosFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "ano", "nome" };
	}

	@Override
	public Class<? super Feriados> getEntityClass() {
		return Feriados.class;
	}

	@Override
	protected String getTitulo() {
		return "Feriados";
	}

	@Override
	protected List<Feriados> pesquisa(String valor) {
		return feriadosDAO.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Feriados> getFormController() {
		return feriadosFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaFeriados";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Feriados> pesquisaDefault() {
		return (List<Feriados>) feriadosDAO.getAll(getEntityClass());
	}

}