package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ServicoOs;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ServicoOsListController extends CRUDListController<ServicoOs> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ServicoOsDAO dao;
	
	@Autowired
	ServicoOsFormController formController;
	
	@Override
	protected String[] getColunas() {
		return new String[] {"descricao"};
	}

	@Override
	protected String getTitulo() {
		return "ServicoOs";
	}

	@Override
	protected List<ServicoOs> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaServico";
	}

	@Override
	protected CRUDFormController<ServicoOs> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super ServicoOs> getEntityClass() {
		return ServicoOs.class;
	}

	@Override
	protected List<ServicoOs> pesquisaDefault() {
		return dao.getAll(ServicoOs.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
