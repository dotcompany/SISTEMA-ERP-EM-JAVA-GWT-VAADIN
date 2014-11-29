package dc.controller.geral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.UfEntity;
import dc.servicos.dao.geral.UFDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr
 * **/

@Controller
@Scope("prototype")
public class UFListController extends CRUDListController<UfEntity> {

	@Autowired
	private UFDAO dao;

	@Autowired
	private UFFormController ufFormController;

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
		return "UF";
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
		return "listaUF";
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
