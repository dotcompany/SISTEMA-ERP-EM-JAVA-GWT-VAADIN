package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.ModeloFormController;
import dc.entidade.ordemservico.Modelo;
import dc.servicos.dao.ordemservico.ModeloDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ModeloListController extends CRUDListController<Modelo> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ModeloDAO dao;
	
	@Autowired
	ModeloFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"nome","marca"};
	}

	@Override
	protected String getTitulo() {
		return "Modelo";
	}

	@Override
	protected List<Modelo> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaModelo";
	}

	@Override
	protected CRUDFormController<Modelo> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Modelo> getEntityClass() {
		return Modelo.class;
	}

	@Override
	protected List<Modelo> pesquisaDefault() {
		return dao.getAll(Modelo.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
