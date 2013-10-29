package dc.controller.adm.dotcompany;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.servicos.dao.adm.dotcompany.ParametroClienteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class ParametroClienteListController extends CRUDListController<ParametroCliente>{
	
	@Autowired
	ParametroClienteDAO dao;
	
	@Autowired
	ParametroClienteFormController parametroClienteFormController;
	
	@Override
	protected String[] getColunas() {
		return new String[] {"modelo","serie","nome","descricao"};
	}

	@Override
	protected String getTitulo() {
		return "Parâmetro Cliente";
	}
	
	@Override
	protected CRUDFormController<ParametroCliente> getFormController() {
		return parametroClienteFormController;
	}

	@Override
	protected Class<? super ParametroCliente> getEntityClass() {
		return ParametroCliente.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Parâmetro Cliente";
	}

	@Override
	protected List<ParametroCliente> pesquisa(String valor) {
		return null;
	}

	@Override
	protected List<ParametroCliente> pesquisaDefault() {
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}
