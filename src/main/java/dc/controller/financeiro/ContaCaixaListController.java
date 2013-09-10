package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.ContaCaixa;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class ContaCaixaListController extends CRUDListController<ContaCaixa> {

	@Autowired
	ContaCaixaDAO dao;

	@Autowired
	ContaCaixaFormController contaCaixaFormController;

	@Override
	protected CRUDFormController<ContaCaixa> getFormController() {
		return contaCaixaFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"nome", "digito","descricao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaContaCaixa";
	}

	@Override
	protected Class<? super ContaCaixa> getEntityClass() {
		return ContaCaixa.class;
	}
	

	@Override
	protected List<ContaCaixa> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Conta Caixa";
	}
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
		
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	
	@Override
	protected List<ContaCaixa> pesquisaDefault() {
		return (List<ContaCaixa>) dao.getAll(getEntityClass());
	}

}
