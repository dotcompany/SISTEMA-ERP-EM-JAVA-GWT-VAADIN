package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.Csosna;
import dc.servicos.dao.geral.tabela.CsosnaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * 
 */

@Controller
@Scope("prototype")
public class CsosnaListController extends CRUDListController<Csosna> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CsosnaDAO dao;

	@Autowired
	CsosnaFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao", "observacao" };
	}

	@Override
	public Class<? super Csosna> getEntityClass() {
		return Csosna.class;
	}

	@Override
	protected String getTitulo() {
		return "CSOSN A";
	}

	@Override
	protected List<Csosna> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Csosna> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCsosna";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Csosna> pesquisaDefault() {
		return (List<Csosna>) dao.getAll(getEntityClass());
	}

}