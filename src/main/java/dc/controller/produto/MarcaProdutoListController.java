package dc.controller.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.MarcaProduto;
import dc.servicos.dao.produto.MarcaProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class MarcaProdutoListController extends CRUDListController<MarcaProduto> {

	@Autowired
	MarcaProdutoDAO dao;

	@Autowired
	MarcaProdutoFormController marcaProdutoFormController;

	@Override
	protected CRUDFormController<MarcaProduto> getFormController() {
		return marcaProdutoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"nome", "descricao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaMarcaProduto";
	}

	@Override
	protected Class<? super MarcaProduto> getEntityClass() {
		return MarcaProduto.class;
	}
	

	@Override
	protected List<MarcaProduto> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Marca Produto";
	}
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
		
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	
	@Override
	protected List<MarcaProduto> pesquisaDefault() {
		return (List<MarcaProduto>) dao.getAll(getEntityClass());
	}

}
