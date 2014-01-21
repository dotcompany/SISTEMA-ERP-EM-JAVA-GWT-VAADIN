package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.Defeito;
import dc.servicos.dao.ordemservico.DefeitoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class DefeitoListController extends CRUDListController<Defeito> {

	private static final long serialVersionUID = 1L;

	@Autowired
	DefeitoDAO dao;

	@Autowired
	DefeitoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	public String getTitulo() {
		return "Defeito";
	}

	@Override
	protected List<Defeito> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaDefeito";
	}

	@Override
	protected CRUDFormController<Defeito> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Defeito> getEntityClass() {
		return Defeito.class;
	}

	@Override
	protected List<Defeito> pesquisaDefault() {
		return dao.getAll(Defeito.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
