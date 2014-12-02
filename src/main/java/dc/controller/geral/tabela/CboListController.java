package dc.controller.geral.tabela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.geral.tabela.CBODAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /*
 */

@Controller
@Scope("prototype")
public class CboListController extends CRUDListController<CboEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CBODAO dao;

	@Autowired
	CboFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "codigo", "nome", "observacao" };
	}

	@Override
	public Class<? super CboEntity> getEntityClass() {
		return CboEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "CBO";
	}

	@Override
	protected List<CboEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<CboEntity> getFormController() {
		return pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaCBO";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<CboEntity> pesquisaDefault() {
		return (List<CboEntity>) dao.getAll(getEntityClass());
	}

}