package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.Convenio;
import dc.servicos.dao.financeiro.ConvenioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Extensão da classe principal que é crudListController
 * Temos alguns métodos que pegamos, temos a configuração do Título da Tela;
 * O Método do Button pesquisar, pegando um valor. e também ele pega algumas informações
 * da classe FormController
 *
*/

@Controller
@Scope("prototype")
public class ConvenioListController extends CRUDListController<Convenio>{

	@Autowired
	ConvenioDAO dao;
	
	@Autowired
	ConvenioFormController convenioFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"logradouro", "bairro"};
	}

	@Override
	protected Class<? super Convenio> getEntityClass() {
		return Convenio.class;
	}


	@Override
	protected String getTitulo() {
		return "Convênio";
	}

	@Override
	protected List<Convenio> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<Convenio> getFormController() {
		return convenioFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaConvenios";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Convenio> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Convenio>) dao.getAll(getEntityClass());
	}

}

