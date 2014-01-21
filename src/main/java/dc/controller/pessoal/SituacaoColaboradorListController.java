package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.SituacaoColaborador;
import dc.servicos.dao.pessoal.SituacaoColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SituacaoColaboradorListController extends CRUDListController<SituacaoColaborador> {

	@Autowired
	SituacaoColaboradorDAO dao;

	@Autowired
	SituacaoColaboradorFormController situacaoColaboradorFormController;

	@Override
	protected CRUDFormController<SituacaoColaborador> getFormController() {
		return situacaoColaboradorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaSituacaoColaborador";
	}

	@Override
	public Class<? super SituacaoColaborador> getEntityClass() {
		return SituacaoColaborador.class;
	}

	@Override
	protected List<SituacaoColaborador> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Situação Colaborador";
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
	protected List<SituacaoColaborador> pesquisaDefault() {
		return (List<SituacaoColaborador>) dao.getAll(getEntityClass());
	}

}
