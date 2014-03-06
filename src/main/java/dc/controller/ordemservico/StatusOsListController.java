package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.StatusOs;
import dc.servicos.dao.ordemservico.StatusOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class StatusOsListController extends CRUDListController<StatusOs> {

	private static final long serialVersionUID = 1L;

	@Autowired
	StatusOsDAO dao;
	
	@Autowired
	StatusOsFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "StatusOs";
	}

	@Override
	protected List<StatusOs> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaStatusOs";
	}

	@Override
	protected CRUDFormController<StatusOs> getFormController() {
		return formController;
	}

	@Override
	public Class<? super StatusOs> getEntityClass() {
		return StatusOs.class;
	}

	@Override
	protected List<StatusOs> pesquisaDefault() {
		return dao.getAll(StatusOs.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
