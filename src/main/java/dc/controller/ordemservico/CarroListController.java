package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.Carro;
import dc.servicos.dao.ordemservico.CarroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CarroListController extends CRUDListController<Carro> {

	private static final long serialVersionUID = 1L;

	@Autowired
	CarroDAO dao;
	
	@Autowired
	CarroFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"placa"};
	}

	@Override
	protected String getTitulo() {
		return "Carro";
	}

	@Override
	protected List<Carro> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaCarro";
	}

	@Override
	protected CRUDFormController<Carro> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Carro> getEntityClass() {
		return Carro.class;
	}

	@Override
	protected List<Carro> pesquisaDefault() {
		return dao.getAll(Carro.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
