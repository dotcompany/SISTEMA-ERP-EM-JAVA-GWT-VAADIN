package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TipoColaborador;
import dc.servicos.dao.pessoal.TipoColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class TipoColaboradorListController extends CRUDListController<TipoColaborador> {

	@Autowired
	TipoColaboradorDAO dao;

	@Autowired
	TipoColaboradorFormController tipoColaboradorFormController;

	@Override
	protected CRUDFormController<TipoColaborador> getFormController() {
		return tipoColaboradorFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"nome", "descricao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoColaborador";
	}

	@Override
	protected Class<? super TipoColaborador> getEntityClass() {
		return TipoColaborador.class;
	}
	

	@Override
	protected List<TipoColaborador> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Tipo Colaborador";
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
	protected List<TipoColaborador> pesquisaDefault() {
		return (List<TipoColaborador>) dao.getAll(getEntityClass());
	}

}
