package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.PlanoNaturezaFinanceira;
import dc.servicos.dao.financeiro.PlanoNaturezaFinanceiraDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Extensão do CrudListController, tendo alguns métodos herdados,
 * como o pesquisar, e pegamos também algumas informações da classe FormController, herdando
 * algumas informações.
 * Temos a configuração das colunas.
 *
*/

@Controller
@Scope("prototype")
public class PlanoNaturezaFinanceiraListController extends CRUDListController<PlanoNaturezaFinanceira>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PlanoNaturezaFinanceiraDAO dao;

	@Autowired
	PlanoNaturezaFinanceiraFormController planoFormController;

	@Override
	protected String[] getColunas() {
		return new String[] {"nome", "mascara","niveis"};
	}

	@Override
	protected Class<? super PlanoNaturezaFinanceira> getEntityClass() {
		return PlanoNaturezaFinanceira.class;
	}


	@Override
	protected String getTitulo() {
		return "Plano Natureza Financeira";
	}

	@Override
	protected List<PlanoNaturezaFinanceira> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PlanoNaturezaFinanceira> getFormController() {
		return planoFormController;
	}


	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaPlanoNaturezaFinanceira";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PlanoNaturezaFinanceira> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<PlanoNaturezaFinanceira>) dao.getAll(getEntityClass());
	}

}