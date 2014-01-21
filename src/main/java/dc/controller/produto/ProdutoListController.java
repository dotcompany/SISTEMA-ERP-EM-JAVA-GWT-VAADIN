package dc.controller.produto;

import java.util.ArrayList;
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
public class ProdutoListController extends CRUDListController<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoDAO dao;

	@Autowired
	private ProdutoFormController produtoFormController;

	@Override
	protected CRUDFormController<Produto> getFormController() {
		return produtoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "gtin", "codigoInterno", "nome", "descricao", "descricaoPdv" };
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
		try {
			return (List<Produto>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Produto>();
		}
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
		try {
			return (List<Produto>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<Produto>();
		}
	}

}