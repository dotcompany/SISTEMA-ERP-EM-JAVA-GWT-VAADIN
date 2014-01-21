package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.Contato;
import dc.servicos.dao.geral.ContatoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * **/

@Controller
@Scope("prototype")
public class ContatoListController extends CRUDListController<Contato> {

	@Autowired
	ContatoDAO dao;

	@Autowired
	ContatoFormController contatoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "email" };
	}

	@Override
	public Class<? super Contato> getEntityClass() {
		return Contato.class;
	}

	@Override
	protected String getTitulo() {
		return "Contato";
	}

	@Override
	protected List<Contato> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Contato> getFormController() {
		return contatoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaContatos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Contato> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Contato>) dao.getAll(getEntityClass());
	}

}
