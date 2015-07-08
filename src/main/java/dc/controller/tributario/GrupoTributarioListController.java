package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.GrupoTributarioEntity;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class GrupoTributarioListController extends CRUDListController<GrupoTributarioEntity> {

	@Autowired
	GrupoTributarioDAO dao;

	@Autowired
	GrupoTributarioFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "observacao", "origemString" };
	}

	@Override
	protected String getTitulo() {
		return "Grupo Tributário";
	}

	@Override
	protected List<GrupoTributarioEntity> pesquisa(String valor) {
		return new ArrayList<GrupoTributarioEntity>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaGrupoTributario";
	}

	@Override
	protected CRUDFormController<GrupoTributarioEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super GrupoTributarioEntity> getEntityClass() {
		return GrupoTributarioEntity.class;
	}

	@Override
	protected List<GrupoTributarioEntity> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<GrupoTributarioEntity>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}