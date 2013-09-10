package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TipoDesligamento;
import dc.servicos.dao.pessoal.TipoDesligamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class TipoDesligamentoListController extends CRUDListController<TipoDesligamento> {

	@Autowired
	TipoDesligamentoDAO dao;

	@Autowired
	TipoDesligamentoFormController tipoDesligamentoFormController;

	@Override
	protected CRUDFormController<TipoDesligamento> getFormController() {
		return tipoDesligamentoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"descricao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoDesligamento";
	}

	@Override
	protected Class<? super TipoDesligamento> getEntityClass() {
		return TipoDesligamento.class;
	}
	

	@Override
	protected List<TipoDesligamento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Tipo Desligamento";
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
	protected List<TipoDesligamento> pesquisaDefault() {
		return (List<TipoDesligamento>) dao.getAll(getEntityClass());
	}

}
