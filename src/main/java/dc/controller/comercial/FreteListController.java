package dc.controller.comercial;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import dc.entidade.comercial.Frete;
import dc.servicos.dao.comercial.FreteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class FreteListController extends CRUDListController<Frete>{
	
	@Autowired
	FreteDAO dao;
	
	@Autowired
	FreteFormController formController;
	
	@Override
	protected String[] getColunas() {
		return new String[] {"id","venda","transportadora"};
	}

	@Override
	protected String getTitulo() {
		return "Frete";
	}
	
	@Override
	protected CRUDFormController<Frete> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Frete> getEntityClass() {
		return Frete.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Frete";
	}

	@Override
	protected List<Frete> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Frete> pesquisaDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}

