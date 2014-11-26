package dc.controller.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.PessoaEntity;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PessoaListController extends CRUDListController<PessoaEntity> {

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
	protected List<PessoaEntity> pesquisa(String valor) {
		//return dao.fullTextSearch(valor);
		return new ArrayList<PessoaEntity>();
	}

	@Override
	public String getViewIdentifier() {
		return "listaPessoa";
	}

	@Override
	protected CRUDFormController<PessoaEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Override
	protected List<PessoaEntity> pesquisaDefault() {

		return new ArrayList<PessoaEntity>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
