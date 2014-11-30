package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class GrupoProdutoListController extends CRUDListController<GrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	GrupoProdutoDAO dao;

	@Autowired
	GrupoProdutoFormController grupoProdutoFormController;

	@Override
	protected CRUDFormController<GrupoEntity> getFormController() {
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
	public Class<? super GrupoEntity> getEntityClass() {
		return GrupoEntity.class;
	}

	@Override
	protected List<GrupoEntity> pesquisa(String valor) {
		try {
			return (List<GrupoEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GrupoEntity>();
		}
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
	protected List<GrupoEntity> pesquisaDefault() {
		try {
			return (List<GrupoEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<GrupoEntity>();
		}
	}

}