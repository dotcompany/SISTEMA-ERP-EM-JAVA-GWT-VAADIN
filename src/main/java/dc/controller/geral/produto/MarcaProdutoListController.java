package dc.controller.geral.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.MarcaEntity;
import dc.servicos.dao.geral.produto.MarcaProdutoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class MarcaProdutoListController extends
		CRUDListController<MarcaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	MarcaProdutoDAO dao;

	@Autowired
	MarcaProdutoFormController marcaProdutoFormController;

	@Override
	protected CRUDFormController<MarcaEntity> getFormController() {
		return marcaProdutoFormController;
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
	public Class<? super MarcaEntity> getEntityClass() {
		return MarcaEntity.class;
	}

	@Override
	protected List<MarcaEntity> pesquisa(String valor) {
		try {
			return (List<MarcaEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<MarcaEntity>();
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
	protected List<MarcaEntity> pesquisaDefault() {
		try {
			return (List<MarcaEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<MarcaEntity>();
		}
	}

}