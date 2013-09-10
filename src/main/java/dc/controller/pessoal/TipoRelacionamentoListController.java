package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TipoRelacionamento;
import dc.servicos.dao.pessoal.TipoRelacionamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class TipoRelacionamentoListController extends CRUDListController<TipoRelacionamento> {

	@Autowired
	TipoRelacionamentoDAO dao;

	@Autowired
	TipoRelacionamentoFormController tipoRelacionamentoFormController;

	@Override
	protected CRUDFormController<TipoRelacionamento> getFormController() {
		return tipoRelacionamentoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"codigo", "nome", "descricao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoRelacionamento";
	}

	@Override
	protected Class<? super TipoRelacionamento> getEntityClass() {
		return TipoRelacionamento.class;
	}
	

	@Override
	protected List<TipoRelacionamento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Tipo Relacionamento";
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
	protected List<TipoRelacionamento> pesquisaDefault() {
		return (List<TipoRelacionamento>) dao.getAll(getEntityClass());
	}

}
