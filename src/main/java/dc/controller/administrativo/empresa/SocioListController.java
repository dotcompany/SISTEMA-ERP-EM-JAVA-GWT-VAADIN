package dc.controller.administrativo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.administrativo.empresa.SocioEntity;
import dc.servicos.dao.administrativo.empresa.SocioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SocioListController extends CRUDListController<SocioEntity> {

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
	protected List<SocioEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaQuadroSocietario";
	}

	@Override
	protected CRUDFormController<SocioEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super SocioEntity> getEntityClass() {
		return SocioEntity.class;
	}

	@Override
	protected List<SocioEntity> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
}
