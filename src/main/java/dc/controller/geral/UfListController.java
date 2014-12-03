package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.UfEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class UfListController extends CRUDListController<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UfDAO dao;

	@Autowired
	private UfFormController ufFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "sigla" };
	}

	@Override
	public Class<? super UfEntity> getEntityClass() {
		return UfEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<UfEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<UfEntity> getFormController() {
		return ufFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
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
	protected List<UfEntity> pesquisaDefault() {
		return (List<UfEntity>) dao.getAll(getEntityClass());
	}

}