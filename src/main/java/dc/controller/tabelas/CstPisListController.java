package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.CstPis;
import dc.servicos.dao.tabelas.CstPisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;



/**
*
* @author Wesley Jr
/*
*/

@Controller
@Scope("prototype")
public class CstPisListController extends CRUDListController<CstPis>{

	@Autowired
	CstPisDAO dao;
	
	@Autowired
	CstPisFormController cstPisFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"codigo","descricao"};
	}

	@Override
	protected Class<? super CstPis> getEntityClass() {
		return CstPis.class;
	}


	@Override
	protected String getTitulo() {
		return "Cst Pis";
	}

	@Override
	protected List<CstPis> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<CstPis> getFormController() {
		return cstPisFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCstPis";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CstPis> pesquisaDefault() {
		return (List<CstPis>) dao.getAll(getEntityClass());
	}
}
