package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Almoxarifado;
import dc.servicos.dao.diversos.AlmoxarifadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class AlmoxarifadoListController extends CRUDListController<Almoxarifado> {

	@Autowired
	AlmoxarifadoDAO dao;

	@Autowired
	AlmoxarifadoFormController almoxarifadoFormController;

	@Override
	protected CRUDFormController<Almoxarifado> getFormController() {
		return almoxarifadoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"nome"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaAlmoxarifado";
	}

	@Override
	protected Class<? super Almoxarifado> getEntityClass() {
		return Almoxarifado.class;
	}
	

	@Override
	protected List<Almoxarifado> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Almoxarifado";
	}
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
		
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	
	@Override
	protected List<Almoxarifado> pesquisaDefault() {
		return (List<Almoxarifado>) dao.getAll(getEntityClass());
	}

}
