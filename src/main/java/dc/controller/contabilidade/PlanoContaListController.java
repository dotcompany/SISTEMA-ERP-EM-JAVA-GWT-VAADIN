package dc.controller.contabilidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.PlanoConta;
import dc.servicos.dao.contabilidade.PlanoContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PlanoContaListController extends CRUDListController<PlanoConta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PlanoContaDAO dao;

	@Autowired
	private PlanoContaFormController planoContaFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "dataInclusao", "mascara", "niveis" };
	}

	@Override
	public Class<? super PlanoConta> getEntityClass() {
		return PlanoConta.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano Conta";
	}

	@Override
	protected List<PlanoConta> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PlanoConta> getFormController() {
		return planoContaFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaPlanoContas";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<PlanoConta> pesquisaDefault() {
		return (List<PlanoConta>) dao.getAll(getEntityClass());
	}

}
