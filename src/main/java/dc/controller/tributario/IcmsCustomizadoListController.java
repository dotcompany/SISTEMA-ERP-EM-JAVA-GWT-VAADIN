package dc.controller.tributario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tributario.IcmsCustomizadoEntity;
import dc.servicos.dao.suprimentos.compra.ContagemEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class IcmsCustomizadoListController extends
		CRUDListController<IcmsCustomizadoEntity> {

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
	protected List<IcmsCustomizadoEntity> pesquisa(String valor) {
		return new ArrayList<IcmsCustomizadoEntity>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaContagemEstoque";
	}

	@Override
	protected CRUDFormController<IcmsCustomizadoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super IcmsCustomizadoEntity> getEntityClass() {
		return IcmsCustomizadoEntity.class;
	}

	@Override
	protected List<IcmsCustomizadoEntity> pesquisaDefault() {
		/*
		 * List<ContagemEstoque> lista = new ArrayList<>(); try{ lista =
		 * dao.getAll(ContagemEstoque.class); }catch(Exception e){
		 * e.printStackTrace(); }
		 */
		//
		return new ArrayList<IcmsCustomizadoEntity>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}