package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.EstadoCivil;
import dc.servicos.dao.pessoal.EstadoCivilDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class EstadoCivilListController extends CRUDListController<EstadoCivil> {

	@Autowired
	private EstadoCivilDAO dao;

	@Autowired
	private EstadoCivilFormController estadoCivilFormController;

	@Override
	protected CRUDFormController<EstadoCivil> getFormController() {
		return estadoCivilFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaEstadoCivil";
	}

	@Override
	public Class<? super EstadoCivil> getEntityClass() {
		return EstadoCivil.class;
	}

	@Override
	protected List<EstadoCivil> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Estado Civil";
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
	protected List<EstadoCivil> pesquisaDefault() {
		return (List<EstadoCivil>) dao.getAll(getEntityClass());
	}

}
