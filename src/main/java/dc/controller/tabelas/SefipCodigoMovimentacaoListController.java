package dc.controller.tabelas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.tabelas.SefipCodigoMovimentacao;
import dc.servicos.dao.tabelas.SefipCodigoMovimentacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;



/**
*
* @author Wesley Jr
/*
*/

@Controller
@Scope("prototype")
public class SefipCodigoMovimentacaoListController extends CRUDListController<SefipCodigoMovimentacao>{

	@Autowired
	SefipCodigoMovimentacaoDAO dao;
	
	@Autowired
	SefipCodigoMovimentacaoFormController sefipCodigoMovimentacaoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"codigo","descricao", "aplicacao"};
	}

	@Override
	protected Class<? super SefipCodigoMovimentacao> getEntityClass() {
		return SefipCodigoMovimentacao.class;
	}


	@Override
	protected String getTitulo() {
		return "Sefip Codigo Movimentação";
	}

	@Override
	protected List<SefipCodigoMovimentacao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<SefipCodigoMovimentacao> getFormController() {
		return sefipCodigoMovimentacaoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaSefipCodigoMovimentacao";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<SefipCodigoMovimentacao> pesquisaDefault() {
		return (List<SefipCodigoMovimentacao>) dao.getAll(getEntityClass());
	}
}
