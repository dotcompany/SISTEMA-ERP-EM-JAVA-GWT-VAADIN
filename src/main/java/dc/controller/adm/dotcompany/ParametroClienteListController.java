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
public class ParametroClienteListController extends CRUDListController<ParametroCliente> {

	@Autowired
	ParametroClienteDAO dao;

	@Autowired
	ParametroClienteFormController parametroClienteFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "empresa" };
	}

	@Override
	public Class<? super ParametroCliente> getEntityClass() {
		return ParametroCliente.class;
	}

	@Override
	protected String getTitulo() {
		return "Parametro Cliente";
	}

	@Override
	protected List<ParametroCliente> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ParametroCliente> getFormController() {
		return parametroClienteFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaParametroCliente";
	}

	@Override
	protected boolean deletaEmCascata() {
		return true;
	}

	@Override
	protected List<ParametroCliente> pesquisaDefault() {
		return (List<ParametroCliente>) dao.getAll(getEntityClass());
	}

}
