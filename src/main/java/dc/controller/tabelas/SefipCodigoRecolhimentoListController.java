package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SefipCodigoRecolhimento;
import dc.servicos.dao.tabelas.SefipCodigoRecolhimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;



/**
*
* @author Wesley Jr
/*
*/

@Controller
@Scope("prototype")
public class SefipCodigoRecolhimentoListController extends CRUDListController<SefipCodigoRecolhimento>{

	@Autowired
	SefipCodigoRecolhimentoDAO dao;
	
	@Autowired
	SefipCodigoRecolhimentoFormController sefipCodigoRecolhimentoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"codigo","descricao", "aplicacao"};
	}

	@Override
	protected Class<? super SefipCodigoRecolhimento> getEntityClass() {
		return SefipCodigoRecolhimento.class;
	}


	@Override
	protected String getTitulo() {
		return "Sefip Codigo Recolhimento";
	}

	@Override
	protected List<SefipCodigoRecolhimento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<SefipCodigoRecolhimento> getFormController() {
		return sefipCodigoRecolhimentoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSefipCodigoRecolhimento";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SefipCodigoRecolhimento> pesquisaDefault() {
		return (List<SefipCodigoRecolhimento>) dao.getAll(getEntityClass());
	}
}
