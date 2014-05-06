package dc.controller.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.Produto;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ProdutosListController extends CRUDListController<Produto> {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProdutoDAO dao;

	@Autowired
	ProdutosFormController produtoFormController;

	@Override
	protected CRUDFormController<Produto> getFormController() {
		return produtoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "gtin", "codigoInterno", "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaProduto";
	}

	@Override
	public Class<? super Produto> getEntityClass() {
		return Produto.class;
	}

	@Override
	protected List<Produto> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Produto";
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
	protected List<Produto> pesquisaDefault() {
		return (List<Produto>) dao.getAll(getEntityClass());
	}

}
