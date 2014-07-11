package dc.controller.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.Pessoa;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PessoaListController extends CRUDListController<Pessoa> {

	@Autowired
	PessoaDAO dao;

	@Autowired
	PessoaFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "tipo", "email", "site" };
	}

	@Override
	protected String getTitulo() {
		return "Pessoa";
	}

	@Override
	protected List<Pessoa> pesquisa(String valor) {
		//return dao.fullTextSearch(valor);
		return new ArrayList<Pessoa>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaPessoa";
	}

	@Override
	protected CRUDFormController<Pessoa> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Pessoa> getEntityClass() {
		return Pessoa.class;
	}

	@Override
	protected List<Pessoa> pesquisaDefault() {

		return new ArrayList<Pessoa>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
