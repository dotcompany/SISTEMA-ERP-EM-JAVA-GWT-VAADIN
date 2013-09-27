package dc.controller.produto;

import java.util.ArrayList;
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
public class MarcaProdutoListController extends
		CRUDListController<MarcaProduto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return new String[] { "nome", "descricao" };
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
		try {
			return (List<MarcaProduto>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<MarcaProduto>();
		}
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
		try {
			return (List<MarcaProduto>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<MarcaProduto>();
		}
	}

}