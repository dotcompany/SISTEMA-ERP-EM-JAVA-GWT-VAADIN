package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Municipio;
import dc.servicos.dao.diversos.MunicipioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class MunicipioListController extends CRUDListController<Municipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MunicipioDAO dao;

	@Autowired
	private MunicipioFormController municipioFormController;

	@Override
	protected CRUDFormController<Municipio> getFormController() {
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
	public Class<? super Municipio> getEntityClass() {
		return Municipio.class;
	}

	@Override
	protected List<Municipio> pesquisa(String valor) {
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
	protected List<Municipio> pesquisaDefault() {
		return (List<Municipio>) dao.getAll(getEntityClass());
	}

}