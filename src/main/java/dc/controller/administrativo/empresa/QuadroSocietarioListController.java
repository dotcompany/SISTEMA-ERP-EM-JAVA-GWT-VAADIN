package dc.controller.administrativo.empresa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.administrativo.empresa.QuadroSocietarioEntity;
import dc.servicos.dao.administrativo.empresa.QuadroSocietarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class QuadroSocietarioListController extends CRUDListController<QuadroSocietarioEntity> {

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
	protected List<QuadroSocietarioEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaQuadroSocietario";
	}

	@Override
	protected CRUDFormController<QuadroSocietarioEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super QuadroSocietarioEntity> getEntityClass() {
		return QuadroSocietarioEntity.class;
	}

	@Override
	protected List<QuadroSocietarioEntity> pesquisaDefault() {
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
}
