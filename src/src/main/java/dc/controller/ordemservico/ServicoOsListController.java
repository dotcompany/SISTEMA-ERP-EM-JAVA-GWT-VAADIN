package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.ServicoOsEntity;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ServicoOsListController extends CRUDListController<ServicoOsEntity> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ServicoOsDAO dao;
	
	@Autowired
	ServicoOsFormController formController;
	
	@Override
	public String[] getColunas() {
		return new String[] {"nome","grupo","subGrupo"};
	}

	@Override
	protected String getTitulo() {
		return "ServicoOs";
	}

	@Override
	protected List<ServicoOsEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaServico";
	}

	@Override
	protected CRUDFormController<ServicoOsEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ServicoOsEntity> getEntityClass() {
		return ServicoOsEntity.class;
	}

	@Override
	protected List<ServicoOsEntity> pesquisaDefault() {
		return dao.getAll(ServicoOsEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
