package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.suprimentos.ContagemEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContagemEstoqueListController extends
		CRUDListController<ContagemEstoque> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ContagemEstoqueDAO dao;

	@Autowired
	ContagemEstoqueFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "data" };
	}

	@Override
	protected String getTitulo() {
		return "Contagem Estoque";
	}

	@Override
	protected List<ContagemEstoque> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaContagemEstoque";
	}

	@Override
	protected CRUDFormController<ContagemEstoque> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ContagemEstoque> getEntityClass() {
		return ContagemEstoque.class;
	}

	@Override
	protected List<ContagemEstoque> pesquisaDefault() {
		return dao.getAll(ContagemEstoque.class);

		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}