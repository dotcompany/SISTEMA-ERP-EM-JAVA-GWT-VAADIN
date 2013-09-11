package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.Banco;
import dc.servicos.dao.financeiro.BancoDAO;
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
public class BancoListController extends CRUDListController<Banco>{

	@Autowired
	BancoDAO dao;
	
	@Autowired
	BancoFormController bancoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"nome", "url"};
	}

	@Override
	protected Class<? super Banco> getEntityClass() {
		return Banco.class;
	}


	@Override
	protected String getTitulo() {
		return "Banco";
	}

	@Override
	protected List<Banco> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<Banco> getFormController() {
		return bancoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaBancos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Banco> pesquisaDefault() {
		return (List<Banco>) dao.getAll(getEntityClass());
	}

}