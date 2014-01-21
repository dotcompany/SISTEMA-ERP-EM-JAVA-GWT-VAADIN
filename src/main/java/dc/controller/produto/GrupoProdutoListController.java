package dc.controller.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.GrupoProduto;
import dc.servicos.dao.produto.GrupoProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class GrupoProdutoListController extends CRUDListController<GrupoProduto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	GrupoProdutoDAO dao;

	@Autowired
	GrupoProdutoFormController grupoProdutoFormController;

	@Override
	protected CRUDFormController<GrupoProduto> getFormController() {
		return grupoProdutoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaGrupoProduto";
	}

	@Override
	public Class<? super GrupoProduto> getEntityClass() {
		return GrupoProduto.class;
	}

	@Override
	protected List<GrupoProduto> pesquisa(String valor) {
		try {
			return (List<GrupoProduto>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GrupoProduto>();
		}
	}

	@Override
	protected String getTitulo() {
		return "Grupo Produto";
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
	protected List<GrupoProduto> pesquisaDefault() {
		try {
			return (List<GrupoProduto>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GrupoProduto>();
		}
	}

}