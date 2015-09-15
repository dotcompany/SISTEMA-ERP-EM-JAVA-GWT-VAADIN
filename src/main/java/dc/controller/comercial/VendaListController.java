package dc.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.comercial.Venda;
import dc.servicos.dao.comercial.VendaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class VendaListController extends CRUDListController<Venda> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	VendaDAO dao;

	@Autowired
	VendaFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "cliente", "vendedor" };
	}

	@Override
	protected String getTitulo() {
		return "Venda";
	}

	@Override
	protected CRUDFormController<Venda> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Venda> getEntityClass() {
		return Venda.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Venda";
	}

	@Override
	protected List<Venda> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Venda> pesquisaDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
