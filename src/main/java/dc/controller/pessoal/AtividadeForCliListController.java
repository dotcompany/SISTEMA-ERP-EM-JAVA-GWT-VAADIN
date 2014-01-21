package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.AtividadeForCli;
import dc.servicos.dao.pessoal.AtividadeForCliDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class AtividadeForCliListController extends CRUDListController<AtividadeForCli> {

	@Autowired
	AtividadeForCliDAO dao;

	@Autowired
	AtividadeForCliFormController atividadeForCliFormController;

	@Override
	protected CRUDFormController<AtividadeForCli> getFormController() {
		return atividadeForCliFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaAtividadeForCli";
	}

	@Override
	public Class<? super AtividadeForCli> getEntityClass() {
		return AtividadeForCli.class;
	}

	@Override
	protected List<AtividadeForCli> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Atividade Cliente/Fornecedor";
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
	protected List<AtividadeForCli> pesquisaDefault() {
		return (List<AtividadeForCli>) dao.getAll(getEntityClass());
	}

}
