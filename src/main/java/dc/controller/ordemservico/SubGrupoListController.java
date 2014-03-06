package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.SubGrupoFormController;
import dc.entidade.ordemservico.SubGrupo;
import dc.servicos.dao.ordemservico.SubGrupoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class SubGrupoListController extends CRUDListController<SubGrupo> {

	private static final long serialVersionUID = 1L;

	@Autowired
	SubGrupoDAO dao;
	
	@Autowired
	SubGrupoFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome","grupo"};
	}

	@Override
	protected String getTitulo() {
		return "SubGrupo";
	}

	@Override
	protected List<SubGrupo> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaSubGrupo";
	}

	@Override
	protected CRUDFormController<SubGrupo> getFormController() {
		return formController;
	}

	@Override
	public Class<? super SubGrupo> getEntityClass() {
		return SubGrupo.class;
	}
 
	@Override
	protected List<SubGrupo> pesquisaDefault() {
		return dao.getAll(SubGrupo.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
