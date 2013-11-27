package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.SituacaoServicoOsFormController;
import dc.entidade.ordemservico.SituacaoServicoOs;
import dc.servicos.dao.ordemservico.SituacaoServicoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class SituacaoServicoOsListController extends CRUDListController<SituacaoServicoOs> {

	private static final long serialVersionUID = 1L;

	@Autowired
	SituacaoServicoOsDAO dao;
	
	@Autowired
	SituacaoServicoOsFormController formController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "SituacaoServicoOs";
	}

	@Override
	protected List<SituacaoServicoOs> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaSituacaoServicoOs";
	}

	@Override
	protected CRUDFormController<SituacaoServicoOs> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super SituacaoServicoOs> getEntityClass() {
		return SituacaoServicoOs.class;
	}

	@Override
	protected List<SituacaoServicoOs> pesquisaDefault() {
		return dao.getAll(SituacaoServicoOs.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
