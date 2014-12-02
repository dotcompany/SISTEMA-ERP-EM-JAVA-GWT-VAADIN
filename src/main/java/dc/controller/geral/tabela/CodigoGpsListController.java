package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.CodigoGps;
import dc.servicos.dao.geral.tabela.CodigoGpsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CodigoGpsListController extends CRUDListController<CodigoGps> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CodigoGpsDAO dao;

	@Autowired
	private CodigoGpsFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super CodigoGps> getEntityClass() {
		return CodigoGps.class;
	}

	@Override
	protected String getTitulo() {
		return "Código GPS";
	}

	@Override
	protected List<CodigoGps> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CodigoGps> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCodigoGps";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CodigoGps> pesquisaDefault() {
		return (List<CodigoGps>) dao.getAll(getEntityClass());
	}

}