package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.servicos.dao.geral.produto.SubGrupoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SubGrupoProdutoListController extends
		CRUDListController<SubGrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SubGrupoDAO dao;

	@Autowired
	private SubGrupoProdutoFormController subGrupoProdutoFormController;

	@Override
	protected CRUDFormController<SubGrupoEntity> getFormController() {
		return subGrupoProdutoFormController;
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
	public Class<? super SubGrupoEntity> getEntityClass() {
		return SubGrupoEntity.class;
	}

	@Override
	protected List<SubGrupoEntity> pesquisa(String valor) {
		try {
			return (List<SubGrupoEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SubGrupoEntity>();
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
	protected List<SubGrupoEntity> pesquisaDefault() {
		try {
			return (List<SubGrupoEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SubGrupoEntity>();
		}
	}

}