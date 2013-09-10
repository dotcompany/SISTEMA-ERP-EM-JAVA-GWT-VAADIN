package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.Csosna;
import dc.servicos.dao.tabelas.CsosnaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;



/**
*
* @author Wesley Jr
*
*/

@Controller
@Scope("prototype")
public class CsosnaListController extends CRUDListController<Csosna>{

	@Autowired
	CsosnaDAO dao;
	
	@Autowired
	CsosnaFormController csosnaFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"codigo","descricao", "observacao"};
	}

	@Override
	protected Class<? super Csosna> getEntityClass() {
		return Csosna.class;
	}


	@Override
	protected String getTitulo() {
		return "Csosna";
	}

	@Override
	protected List<Csosna> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<Csosna> getFormController() {
		return csosnaFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCsosna";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Csosna> pesquisaDefault() {
		return (List<Csosna>) dao.getAll(getEntityClass());
	}
}
