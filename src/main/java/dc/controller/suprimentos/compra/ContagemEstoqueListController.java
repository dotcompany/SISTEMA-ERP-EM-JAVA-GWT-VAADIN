package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.estoque.ContagemCabecalhoEntity;
import dc.servicos.dao.suprimentos.compra.ContagemEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContagemEstoqueListController extends
		CRUDListController<ContagemCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContagemEstoqueDAO dao;

	@Autowired
	private ContagemEstoqueFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "data" };
	}

	@Override
	protected String getTitulo() {
		return "Contagem Estoque";
	}

	@Override
	protected List<ContagemCabecalhoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected CRUDFormController<ContagemCabecalhoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super ContagemCabecalhoEntity> getEntityClass() {
		return ContagemCabecalhoEntity.class;
	}

	@Override
	protected List<ContagemCabecalhoEntity> pesquisaDefault() {
		return dao.getAll(ContagemCabecalhoEntity.class);

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