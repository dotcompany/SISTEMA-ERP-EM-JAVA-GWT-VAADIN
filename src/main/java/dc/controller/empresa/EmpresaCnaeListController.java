package dc.controller.empresa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.empresa.EmpresaCnae;
import dc.entidade.empresa.QuadroSocietario;
import dc.servicos.dao.empresa.EmpresaCnaeDAO;
import dc.servicos.dao.empresa.QuadroSocietarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class EmpresaCnaeListController extends CRUDListController<EmpresaCnae>
{

	@Autowired
	EmpresaCnaeDAO dao;

	@Autowired
	EmpresaCnaeFormController formController;

	@Override
	protected String[] getColunas() {
		return new String[] {"cnae"};
	}

	@Override
	protected String getTitulo() {
		return "Empresa Cnae";
	}

	@Override
	protected List<EmpresaCnae> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaQuadroSocietario";
	}

	@Override
	protected CRUDFormController<EmpresaCnae> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super EmpresaCnae> getEntityClass() {
		return EmpresaCnae.class;
	}

	@Override
	protected List<EmpresaCnae> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
}
