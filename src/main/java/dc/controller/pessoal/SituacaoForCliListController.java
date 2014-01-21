package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.SituacaoForCli;
import dc.servicos.dao.pessoal.SituacaoForCliDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SituacaoForCliListController extends CRUDListController<SituacaoForCli> {

	@Autowired
	SituacaoForCliDAO dao;

	@Autowired
	SituacaoForCliFormController situacaoForCliFormController;

	@Override
	protected CRUDFormController<SituacaoForCli> getFormController() {
		return situacaoForCliFormController;
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
	public Class<? super SituacaoForCli> getEntityClass() {
		return SituacaoForCli.class;
	}

	@Override
	protected List<SituacaoForCli> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Situacao Cliente/Fornecedor";
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
	protected List<SituacaoForCli> pesquisaDefault() {
		return (List<SituacaoForCli>) dao.getAll(getEntityClass());
	}

}
