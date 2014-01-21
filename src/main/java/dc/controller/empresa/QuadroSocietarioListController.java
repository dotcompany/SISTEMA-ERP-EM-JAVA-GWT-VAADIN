package dc.controller.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.empresa.QuadroSocietario;
import dc.servicos.dao.empresa.QuadroSocietarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class QuadroSocietarioListController extends CRUDListController<QuadroSocietario> {

	@Autowired
	QuadroSocietarioDAO dao;

	@Autowired
	QuadroSocietarioFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "dataRegistro", "capitalSocial", "valorQuota", "quantidadeCotas" };
	}

	@Override
	protected String getTitulo() {
		return "Quadro Societário";
	}

	@Override
	protected List<QuadroSocietario> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaQuadroSocietario";
	}

	@Override
	protected CRUDFormController<QuadroSocietario> getFormController() {
		return formController;
	}

	@Override
	public Class<? super QuadroSocietario> getEntityClass() {
		return QuadroSocietario.class;
	}

	@Override
	protected List<QuadroSocietario> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
}
