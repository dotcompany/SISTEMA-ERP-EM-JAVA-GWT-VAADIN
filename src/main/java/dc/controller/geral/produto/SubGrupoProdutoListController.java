package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.SubGrupoProdutoEntity;
import dc.servicos.dao.geral.produto.SubGrupoProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SubGrupoProdutoListController extends
		CRUDListController<SubGrupoProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SubGrupoProdutoDAO dao;

	@Autowired
	private SubGrupoProdutoFormController subGrupoProdutoFormController;

	@Override
	protected CRUDFormController<SubGrupoProdutoEntity> getFormController() {
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
	public Class<? super SubGrupoProdutoEntity> getEntityClass() {
		return SubGrupoProdutoEntity.class;
	}

	@Override
	protected List<SubGrupoProdutoEntity> pesquisa(String valor) {
		try {
			return (List<SubGrupoProdutoEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SubGrupoProdutoEntity>();
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
	protected List<SubGrupoProdutoEntity> pesquisaDefault() {
		try {
			return (List<SubGrupoProdutoEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<SubGrupoProdutoEntity>();
		}
	}

}