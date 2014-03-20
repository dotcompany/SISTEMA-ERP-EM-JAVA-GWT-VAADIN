package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ParametroOs;
import dc.servicos.dao.ordemservico.ParametroOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ParametroOsListController extends CRUDListController<ParametroOs> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ParametroOsDAO dao; 
	
	@Autowired
	private ParametroOsFormController formController;
	

	@Override 
	public String[] getColunas() {
		return new String[] {"id","qtdDiasPadrao","obsDefeitoPadrao","obsPadrao"};
	}

	@Override
	protected String getTitulo() {
		return "Parametro de O.S";
	}

	@Override
	protected List<ParametroOs> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaParametros";
	}

	@Override
	protected CRUDFormController<ParametroOs> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ParametroOs> getEntityClass() {
		return ParametroOs.class;
	}

	@Override
	protected List<ParametroOs> pesquisaDefault() {
		return dao.getAll(ParametroOs.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
