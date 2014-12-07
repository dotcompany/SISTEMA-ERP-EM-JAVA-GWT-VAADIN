package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.servicos.dao.geral.diverso.OperadoraCartaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class OperadoraCartaoListController extends
		CRUDListController<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperadoraCartaoDAO dao;

	@Autowired
	private OperadoraCartaoFormController operadoraFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "bandeira" };
	}

	@Override
	public Class<? super OperadoraCartaoEntity> getEntityClass() {
		return OperadoraCartaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	protected List<OperadoraCartaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<OperadoraCartaoEntity> getFormController() {
		return operadoraFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<OperadoraCartaoEntity> pesquisaDefault() {
		return (List<OperadoraCartaoEntity>) dao.getAll(getEntityClass());
	}

}