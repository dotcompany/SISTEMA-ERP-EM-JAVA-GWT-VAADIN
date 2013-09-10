package dc.controller.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.UnidadeProduto;
import dc.servicos.dao.produto.UnidadeProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Extensão da classe principal que é crudListController
 * Temos alguns métodos que pegamos, temos a configuração do Título da Tela;
 * O m�todo do Button pesquisar, pegando um valor. e também ele pega algumas informações
 * da classe FormController
 *
*/

@Controller
@Scope("prototype")
public class UnidadeProdutoListController extends CRUDListController<UnidadeProduto>{

	@Autowired
	UnidadeProdutoDAO dao;
	
	@Autowired
	UnidadeProdutoFormController unidadeProdutoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"sigla", "descricao"};
	}

	@Override
	protected Class<? super UnidadeProduto> getEntityClass() {
		return UnidadeProduto.class;
	}


	@Override
	protected String getTitulo() {
		return "Unidade Produto";
	}

	@Override
	protected List<UnidadeProduto> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<UnidadeProduto> getFormController() {
		return unidadeProdutoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaUnidadeProduto";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<UnidadeProduto> pesquisaDefault() {
		return (List<UnidadeProduto>) dao.getAll(getEntityClass());
	}

}

