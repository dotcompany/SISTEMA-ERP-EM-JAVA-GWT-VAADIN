package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.NaturezaFinanceira;
import dc.servicos.dao.financeiro.NaturezaFinanceiraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class NaturezaFinanceiraListController extends CRUDListController<NaturezaFinanceira> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	NaturezaFinanceiraDAO dao;

	@Autowired
	NaturezaFinanceiraFormController naturezafinanceiraFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "classificacao", "tipo", "aplicacao", "aparecePagar", "apareceReceber" };
	}

	@Override
	public Class<? super NaturezaFinanceira> getEntityClass() {
		return NaturezaFinanceira.class;
	}

	@Override
	protected String getTitulo() {
		return "Natureza Financeira";
	}

	@Override
	protected List<NaturezaFinanceira> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<NaturezaFinanceira> getFormController() {
		return naturezafinanceiraFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaNaturezaFinanceiras";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<NaturezaFinanceira> pesquisaDefault() {
		return (List<NaturezaFinanceira>) dao.getAll(getEntityClass());
	}

}