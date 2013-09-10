package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Estado;
import dc.servicos.dao.diversos.EstadoDAO;
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
public class EstadoListController extends CRUDListController<Estado>{

	@Autowired
	EstadoDAO dao;
	
	@Autowired
	EstadoFormController estadoFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"nome", "sigla"};
	}

	@Override
	protected Class<? super Estado> getEntityClass() {
		return Estado.class;
	}


	@Override
	protected String getTitulo() {
		return "Estado";
	}

	@Override
	protected List<Estado> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<Estado> getFormController() {
		return estadoFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaEstados";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Estado> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Estado>) dao.getAll(getEntityClass());
	}

	
}
