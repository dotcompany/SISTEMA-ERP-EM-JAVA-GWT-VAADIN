package dc.controller.geral.diverso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.geral.diverso.MunicipioEntity;
import dc.servicos.dao.geral.diverso.MunicipioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class MunicipioListController extends CRUDListController<MunicipioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	MunicipioDAO dao;

	@Autowired
	MunicipioFormController municipioFormController;

	@Override
	protected CRUDFormController<MunicipioEntity> getFormController() {
		return municipioFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "codigoIbge", "codigoReceitaFederal" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaMunicipio";
	}

	@Override
	public Class<? super MunicipioEntity> getEntityClass() {
		return MunicipioEntity.class;
	}

	@Override
	protected List<MunicipioEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Município";
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
	protected List<MunicipioEntity> pesquisaDefault() {
		return (List<MunicipioEntity>) dao.getAll(getEntityClass());
	}

}