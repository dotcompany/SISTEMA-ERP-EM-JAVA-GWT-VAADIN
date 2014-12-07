package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.SetorEntity;
import dc.servicos.dao.geral.diverso.SetorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SetorListController extends CRUDListController<SetorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SetorDAO dao;

	@Autowired
	private SetorFormController setorFormController;

	@Override
	protected CRUDFormController<SetorEntity> getFormController() {
		return setorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super SetorEntity> getEntityClass() {
		return SetorEntity.class;
	}

	@Override
	protected List<SetorEntity> pesquisa(String valor) {
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
	protected List<SetorEntity> pesquisaDefault() {
		return (List<SetorEntity>) dao.getAll(getEntityClass());
	}

}