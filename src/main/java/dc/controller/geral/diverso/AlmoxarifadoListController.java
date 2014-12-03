package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.servicos.dao.geral.diverso.AlmoxarifadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class AlmoxarifadoListController extends CRUDListController<AlmoxarifadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AlmoxarifadoDAO dao;

	@Autowired
	private AlmoxarifadoFormController almoxarifadoFormController;

	@Override
	protected CRUDFormController<AlmoxarifadoEntity> getFormController() {
		return almoxarifadoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaAlmoxarifado";
	}

	@Override
	public Class<? super AlmoxarifadoEntity> getEntityClass() {
		return AlmoxarifadoEntity.class;
	}

	@Override
	protected List<AlmoxarifadoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Almoxarifado";
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
	protected List<AlmoxarifadoEntity> pesquisaDefault() {
		return (List<AlmoxarifadoEntity>) dao.getAll(getEntityClass());
	}

}