package dc.controller.geral.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.ProdutoEntity;
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
	private ProdutoFormController produtoFormController;

	/**
	 * CONSTRUTOR
	 */

	public ProdutoListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<ProdutoEntity> getFormController() {
		return produtoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] {  "subGrupo","unidadeProduto","marca","almoxarifado","grupo","grupoTributario", "codigoInterno", 
				"nome", "descricao","ncm","gtin" };
	}

	@Override
	public Class<? super ProdutoEntity> getEntityClass() {
		return ProdutoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ProdutoEntity> pesquisa(String valor) {
		try {
			List<ProdutoEntity> auxLista = (List<ProdutoEntity>) this.produtoFormController.getBusiness().fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<ProdutoEntity> pesquisaDefault() {
		try {
			@SuppressWarnings("unchecked")
			List<ProdutoEntity> auxLista = (List<ProdutoEntity>) this.produtoFormController.getBusiness().getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}