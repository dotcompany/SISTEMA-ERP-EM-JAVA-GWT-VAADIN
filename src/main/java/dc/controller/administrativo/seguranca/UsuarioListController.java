package dc.controller.administrativo.seguranca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.servicos.dao.sistema.UsuarioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class UsuarioListController extends CRUDListController<UsuarioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UsuarioDAO dao;

	@Autowired
	UsuarioFormController usuarioFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "login" };
	}

	@Override
	public Class<? super UsuarioEntity> getEntityClass() {
		return UsuarioEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Usuário";
	}

	@Override
	protected List<UsuarioEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<UsuarioEntity> getFormController() {
		return usuarioFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
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
	protected List<UsuarioEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<UsuarioEntity>) dao.getAll(UsuarioEntity.class);
	}

}