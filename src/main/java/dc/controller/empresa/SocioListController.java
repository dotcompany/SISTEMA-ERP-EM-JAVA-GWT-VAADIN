package dc.controller.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.empresa.Socio;
import dc.servicos.dao.empresa.SocioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SocioListController extends CRUDListController<Socio> {

	@Autowired
	SocioDAO dao;

	@Autowired
	SocioFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "pessoa" };
	}

	@Override
	protected String getTitulo() {
		return "Sócio";
	}

	@Override
	protected List<Socio> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaQuadroSocietario";
	}

	@Override
	protected CRUDFormController<Socio> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Socio> getEntityClass() {
		return Socio.class;
	}

	@Override
	protected List<Socio> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
}
