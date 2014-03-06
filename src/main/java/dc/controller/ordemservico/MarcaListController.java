package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.controller.ordemservico.MarcaFormController;
import dc.entidade.ordemservico.Marca;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class MarcaListController extends CRUDListController<Marca> {

	private static final long serialVersionUID = 1L;

	@Autowired
	MarcaDAO dao;
	
	@Autowired
	MarcaFormController formController;
	

	@Override
	public String[] getColunas() {
		return new String[] {"nome"};
	}

	@Override
	protected String getTitulo() {
		return "Marca";
	}

	@Override
	protected List<Marca> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaModelo";
	}

	@Override
	protected CRUDFormController<Marca> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Marca> getEntityClass() {
		return Marca.class;
	}

	@Override
	protected List<Marca> pesquisaDefault() {
		return dao.getAll(Marca.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
