package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.OrcamentoOs;
import dc.servicos.dao.ordemservico.OrcamentoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class OrcamentoOsListController extends CRUDListController<OrcamentoOs> {

	private static final long serialVersionUID = 1L;

	@Autowired
	OrcamentoOsDAO dao; 
	
	@Autowired
	private OrcamentoOsFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"placa", "nome"};
	}

	@Override
	protected String getTitulo() {
		return "O.S Simples";
	}

	@Override
	protected List<OrcamentoOs> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaOrcamentoOs";
	}

	@Override
	protected CRUDFormController<OrcamentoOs> getFormController() {
		return formController;
	}

	@Override
	public Class<? super OrcamentoOs> getEntityClass() {
		return OrcamentoOs.class;
	}

	@Override
	protected List<OrcamentoOs> pesquisaDefault() {
		return dao.getAll(OrcamentoOs.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}
