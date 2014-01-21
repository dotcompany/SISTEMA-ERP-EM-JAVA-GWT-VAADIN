package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.TipoCreditoPis;
import dc.servicos.dao.tabelas.TipoCreditoPisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class TipoCreditoPisListController extends CRUDListController<TipoCreditoPis> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	TipoCreditoPisDAO dao;

	@Autowired
	TipoCreditoPisFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "descricao" };
	}

	@Override
	public Class<? super TipoCreditoPis> getEntityClass() {
		return TipoCreditoPis.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Crédito PIS";
	}

	@Override
	protected List<TipoCreditoPis> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TipoCreditoPis> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaTipoCreditoPis";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TipoCreditoPis> pesquisaDefault() {
		return (List<TipoCreditoPis>) dao.getAll(getEntityClass());
	}

}