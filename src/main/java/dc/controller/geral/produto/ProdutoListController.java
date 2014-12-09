package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ProdutoListController extends CRUDListController<ProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoDAO dao;

	@Autowired
	private ProdutoFormController produtoFormController;

	@Override
	protected CRUDFormController<ProdutoEntity> getFormController() {
		return produtoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "gtin", "codigoInterno", "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super ProdutoEntity> getEntityClass() {
		return ProdutoEntity.class;
	}

	@Override
	protected List<ProdutoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
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
	protected List<ProdutoEntity> pesquisaDefault() {
		return (List<ProdutoEntity>) dao.getAll(getEntityClass());
	}

}