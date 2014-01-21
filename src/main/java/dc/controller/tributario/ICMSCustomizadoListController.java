package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.ICMSCustomizado;
import dc.servicos.dao.suprimentos.ContagemEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class ICMSCustomizadoListController extends CRUDListController<ICMSCustomizado> {

	@Autowired
	ContagemEstoqueDAO dao;

	@Autowired
	ICMSCustomizadoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "origemStr" };
	}

	@Override
	protected String getTitulo() {
		return "ICMS Customizado";
	}

	@Override
	protected List<ICMSCustomizado> pesquisa(String valor) {
		return new ArrayList<ICMSCustomizado>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaContagemEstoque";
	}

	@Override
	protected CRUDFormController<ICMSCustomizado> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ICMSCustomizado> getEntityClass() {
		return ICMSCustomizado.class;
	}

	@Override
	protected List<ICMSCustomizado> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<ICMSCustomizado>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}