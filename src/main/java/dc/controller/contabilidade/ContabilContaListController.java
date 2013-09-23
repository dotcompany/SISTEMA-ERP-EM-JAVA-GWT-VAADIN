package dc.controller.contabilidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.ContabilConta;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class ContabilContaListController extends CRUDListController<ContabilConta> {

	@Autowired
	private ContabilContaDAO dao;

	@Autowired
	private ContabilContaFormController contabilContaFormController;

	@Override
	protected CRUDFormController<ContabilConta> getFormController() {
		return contabilContaFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"nome"};
	}

	@Override
	public String getViewIdentifier() {
		return "listContabilConta";
	}

	@Override
	protected Class<? super ContabilConta> getEntityClass() {
		return ContabilConta.class;
	}
	

	@Override
	protected List<ContabilConta> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Contabil Conta";
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
	protected List<ContabilConta> pesquisaDefault() {
		return (List<ContabilConta>) dao.getAll(getEntityClass());
	}

}
