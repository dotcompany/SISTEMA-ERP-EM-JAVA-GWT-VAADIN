package dc.controller.sistema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.Usuario;
import dc.servicos.dao.sistema.UsuarioDAO;
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
public class UsuarioListController extends CRUDListController<Usuario>{

	@Autowired
	UsuarioDAO dao;
	
	@Autowired
	UsuarioFormController usuarioFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"login"};
	}

	@Override
	protected Class<? super Usuario> getEntityClass() {
		return Usuario.class;
	}


	@Override
	protected String getTitulo() {
		return "Usuário";
	}

	@Override
	protected List<Usuario> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}	
	

	@Override
	protected CRUDFormController<Usuario> getFormController() {
		return usuarioFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaUsuarios";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return true;
	}
	

	@Override
	protected List<Usuario> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Usuario>) dao.getAll(Usuario.class);
	}

	
}