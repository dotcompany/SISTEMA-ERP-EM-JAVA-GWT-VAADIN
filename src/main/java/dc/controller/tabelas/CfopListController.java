package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.Cfop;
import dc.servicos.dao.tabelas.CfopDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;



/**
*
* @author Wesley Jr
/*
*/

@Controller
@Scope("prototype")
public class CfopListController extends CRUDListController<Cfop>{

	@Autowired
	CfopDAO dao;
	
	@Autowired
	CfopFormController cfopFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"descricao","aplicacao"};
	}

	@Override
	protected Class<? super Cfop> getEntityClass() {
		return Cfop.class;
	}


	@Override
	protected String getTitulo() {
		return "Cfop";
	}

	@Override
	protected List<Cfop> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<Cfop> getFormController() {
		return cfopFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCfop";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Cfop> pesquisaDefault() {
		return (List<Cfop>) dao.getAll(getEntityClass());
	}
}
