package dc.controller.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.SubGrupoProduto;
import dc.servicos.dao.produto.SubGrupoProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SubGrupoProdutoListController extends CRUDListController<SubGrupoProduto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SubGrupoProdutoDAO dao;

	@Autowired
	private SubGrupoProdutoFormController subGrupoProdutoFormController;

	@Override
	protected CRUDFormController<SubGrupoProduto> getFormController() {
		return subGrupoProdutoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaSubGrupoProduto";
	}

	@Override
	protected Class<? super SubGrupoProduto> getEntityClass() {
		return SubGrupoProduto.class;
	}

	@Override
	protected List<SubGrupoProduto> pesquisa(String valor) {
		try {
			return (List<SubGrupoProduto>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SubGrupoProduto>();
		}
	}

	@Override
	protected String getTitulo() {
		return "Sub Grupo Produto";
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
	protected List<SubGrupoProduto> pesquisaDefault() {
		try {
			return (List<SubGrupoProduto>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SubGrupoProduto>();
		}
	}

}