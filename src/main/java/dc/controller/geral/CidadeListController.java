package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.Cidade;
import dc.servicos.dao.geral.CidadeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * **/

@Controller
@Scope("prototype")
public class CidadeListController extends CRUDListController<Cidade> {

	@Autowired
	CidadeDAO dao;

	@Autowired
	CidadeFormController cidadeFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	public Class<? super Cidade> getEntityClass() {
		return Cidade.class;
	}

	@Override
	protected String getTitulo() {
		return "Cidade";
	}

	@Override
	protected List<Cidade> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Cidade> getFormController() {
		return cidadeFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaCidades";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Cidade> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Cidade>) dao.getAll(getEntityClass());
	}

}
