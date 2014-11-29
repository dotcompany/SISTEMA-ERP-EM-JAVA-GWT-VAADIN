package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.PessoaContatoEntity;
import dc.servicos.dao.geral.PessoaContatoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * **/

@Controller
@Scope("prototype")
public class PessoaContatoListController extends CRUDListController<PessoaContatoEntity> {

	@Autowired
	PessoaContatoDAO dao;

	@Autowired
	PessoaContatoFormController pessoaContatoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "email" };
	}

	@Override
	public Class<? super PessoaContatoEntity> getEntityClass() {
		return PessoaContatoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Pessoa Contato";
	}

	@Override
	protected List<PessoaContatoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<PessoaContatoEntity> getFormController() {
		return pessoaContatoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaPessoaContatos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<PessoaContatoEntity> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<PessoaContatoEntity>) dao.getAll(getEntityClass());
	}

}
