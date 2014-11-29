package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.servicos.dao.geral.produto.UnidadeProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class UnidadeProdutoListController extends
		CRUDListController<UnidadeProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UnidadeProdutoDAO dao;

	@Autowired
	UnidadeProdutoFormController unidadeProdutoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "sigla", "nome" };
	}

	@Override
	public Class<? super UnidadeProdutoEntity> getEntityClass() {
		return UnidadeProdutoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Unidade Produto";
	}

	@Override
	protected List<UnidadeProdutoEntity> pesquisa(String valor) {
		try {
			return (List<UnidadeProdutoEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<UnidadeProdutoEntity>();
		}
	}

	@Override
	protected CRUDFormController<UnidadeProdutoEntity> getFormController() {
		return unidadeProdutoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaUnidadeProduto";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<UnidadeProdutoEntity> pesquisaDefault() {
		try {
			return (List<UnidadeProdutoEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<UnidadeProdutoEntity>();
		}
	}

}