package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.servicos.dao.suprimentos.compra.ContagemEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class IcmsCustomizadoListController extends
		CRUDListController<IcmsCustomizadoCabecalhoEntity> {

	@Autowired
	private ContagemEstoqueDAO dao;

	@Autowired
	private IcmsCustomizadoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "origemStr" };
	}

	@Override
	protected String getTitulo() {
		return "ICMS Customizado";
	}

	@Override
	protected List<IcmsCustomizadoCabecalhoEntity> pesquisa(String valor) {
		return new ArrayList<IcmsCustomizadoCabecalhoEntity>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaContagemEstoque";
	}

	@Override
	protected CRUDFormController<IcmsCustomizadoCabecalhoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super IcmsCustomizadoCabecalhoEntity> getEntityClass() {
		return IcmsCustomizadoCabecalhoEntity.class;
	}

	@Override
	protected List<IcmsCustomizadoCabecalhoEntity> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<IcmsCustomizadoCabecalhoEntity>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}