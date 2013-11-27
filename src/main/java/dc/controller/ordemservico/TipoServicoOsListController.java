package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.TipoServicoOsFormController;
import dc.entidade.ordemservico.TipoServicoOs;
import dc.servicos.dao.ordemservico.TipoServicoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoServicoOsListController extends CRUDListController<TipoServicoOs> {

	private static final long serialVersionUID = 1L;

	@Autowired
	TipoServicoOsDAO dao;
	
	@Autowired
	TipoServicoOsFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "TipoServicoOs";
	}

	@Override
	protected List<TipoServicoOs> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaTipoServicoOs";
	}

	@Override
	protected CRUDFormController<TipoServicoOs> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super TipoServicoOs> getEntityClass() {
		return TipoServicoOs.class;
	}

	@Override
	protected List<TipoServicoOs> pesquisaDefault() {
		return dao.getAll(TipoServicoOs.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
