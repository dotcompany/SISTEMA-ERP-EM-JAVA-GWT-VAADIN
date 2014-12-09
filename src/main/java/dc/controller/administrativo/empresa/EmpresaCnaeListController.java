package dc.controller.administrativo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.administrativo.empresa.EmpresaCnaeEntity;
import dc.servicos.dao.administrativo.empresa.EmpresaCnaeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class EmpresaCnaeListController extends CRUDListController<EmpresaCnaeEntity> {

	@Autowired
	EmpresaCnaeDAO dao;

	@Autowired
	EmpresaCnaeFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "cnae", "principalStr", "ramoAtividade", "objetoSocial" };
	}

	@Override
	protected String getTitulo() {
		return "Empresa Cnae";
	}

	@Override
	protected List<EmpresaCnaeEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaQuadroSocietario";
	}

	@Override
	protected CRUDFormController<EmpresaCnaeEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super EmpresaCnaeEntity> getEntityClass() {
		return EmpresaCnaeEntity.class;
	}

	@Override
	protected List<EmpresaCnaeEntity> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
}
