package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.GrupoFormController;
import dc.entidade.ordemservico.Grupo;
import dc.servicos.dao.ordemservico.GrupoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class GrupoListController extends CRUDListController<Grupo> {

	private static final long serialVersionUID = 1L;

	@Autowired
	GrupoDAO dao;
	
	@Autowired
	GrupoFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Grupo";
	}

	@Override
	protected List<Grupo> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaGrupo";
	}

	@Override
	protected CRUDFormController<Grupo> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Grupo> getEntityClass() {
		return Grupo.class;
	}

	@Override
	protected List<Grupo> pesquisaDefault() {
		return dao.getAll(Grupo.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
