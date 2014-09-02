package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.Combustivel;
import dc.servicos.dao.ordemservico.CombustivelDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CombustivelListController extends CRUDListController<Combustivel> {

	private static final long serialVersionUID = 1L;

	@Autowired
	CombustivelDAO dao;
	
	@Autowired
	CombustivelFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Combustível";
	}

	@Override
	protected List<Combustivel> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaCombustivel";
	}

	@Override
	protected CRUDFormController<Combustivel> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Combustivel> getEntityClass() {
		return Combustivel.class;
	}

	@Override
	protected List<Combustivel> pesquisaDefault() {
		return dao.getAll(Combustivel.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
