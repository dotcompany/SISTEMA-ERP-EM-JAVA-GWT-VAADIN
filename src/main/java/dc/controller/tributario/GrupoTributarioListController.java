package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.GrupoTributario;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class GrupoTributarioListController extends
		CRUDListController<GrupoTributario> {

	@Autowired
	GrupoTributarioDAO dao;

	@Autowired
	GrupoTributarioFormController formController;

	@Override
	protected String[] getColunas() {
		return new String[] { "descricao", "origemString" };
	}

	@Override
	protected String getTitulo() {
		return "Grupo Tributário";
	}

	@Override
	protected List<GrupoTributario> pesquisa(String valor) {
		return new ArrayList<GrupoTributario>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaGrupoTributario";
	}

	@Override
	protected CRUDFormController<GrupoTributario> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super GrupoTributario> getEntityClass() {
		return GrupoTributario.class;
	}

	@Override
	protected List<GrupoTributario> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<GrupoTributario>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}