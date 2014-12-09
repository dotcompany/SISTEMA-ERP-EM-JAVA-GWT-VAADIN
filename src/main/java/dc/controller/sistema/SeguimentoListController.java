package dc.controller.sistema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.framework.SeguimentoEntity;
import dc.servicos.dao.framework.geral.SeguimentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SeguimentoListController extends CRUDListController<SeguimentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SeguimentoDAO dao;

	@Autowired
	private SeguimentoFormController seguimentoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super SeguimentoEntity> getEntityClass() {
		return SeguimentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Seguimentos";
	}

	@Override
	protected List<SeguimentoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<SeguimentoEntity> getFormController() {
		return seguimentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaSeguimentos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<SeguimentoEntity> pesquisaDefault() {
		return (List<SeguimentoEntity>) dao.getAll(getEntityClass());
	}

}
