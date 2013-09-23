package dc.controller.pessoal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.Pessoa;
import dc.entidade.suprimentos.ContagemEstoque;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.suprimentos.ContagemEstoqueDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PessoaListController extends CRUDListController<Pessoa>
{

	@Autowired
	PessoaDAO dao;

	@Autowired
	PessoaFormController formController;

	@Override
	protected String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Pessoa";
	}

	@Override
	protected List<Pessoa> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaContagemEstoque";
	}

	@Override
	protected CRUDFormController<Pessoa> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Pessoa> getEntityClass() {
		return Pessoa.class;
	}


	@Override
	protected List<Pessoa> pesquisaDefault() {

		/*List<ContagemEstoque> lista = new ArrayList<>();
		try{
			 lista =  dao.getAll(ContagemEstoque.class);	 
		 }catch(Exception e){
			 e.printStackTrace();
		 }*/
		//		
		return new ArrayList<>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
