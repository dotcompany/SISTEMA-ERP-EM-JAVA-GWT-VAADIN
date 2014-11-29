package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoProdutoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class GrupoProdutoListController extends
		CRUDListController<GrupoProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	GrupoProdutoDAO dao;

	@Autowired
	GrupoProdutoFormController grupoProdutoFormController;

	@Override
	protected CRUDFormController<GrupoProdutoEntity> getFormController() {
		return grupoProdutoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super GrupoProdutoEntity> getEntityClass() {
		return GrupoProdutoEntity.class;
	}

	@Override
	protected List<GrupoProdutoEntity> pesquisa(String valor) {
		try {
			return (List<GrupoProdutoEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GrupoProdutoEntity>();
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
	protected List<GrupoProdutoEntity> pesquisaDefault() {
		try {
			return (List<GrupoProdutoEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GrupoProdutoEntity>();
		}
	}

}