package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.AcessorioFormController;
import dc.entidade.ordemservico.Acessorio;
import dc.servicos.dao.ordemservico.AcessorioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class AcessorioListController extends CRUDListController<Acessorio> {

	private static final long serialVersionUID = 1L;

	@Autowired
	AcessorioDAO dao;
	
	@Autowired
	AcessorioFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Acessório";
	}

	@Override
	protected List<Acessorio> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaAcessorio";
	}

	@Override
	protected CRUDFormController<Acessorio> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Acessorio> getEntityClass() {
		return Acessorio.class;
	}

	@Override
	protected List<Acessorio> pesquisaDefault() {
		return dao.getAll(Acessorio.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
