package dc.controller.ordemservico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.ordemservico.Cor;
import dc.servicos.dao.ordemservico.CorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CorListController extends CRUDListController<Cor> {

	private static final long serialVersionUID = 1L;

	@Autowired
	CorDAO dao;

	@Autowired
	CorFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome" };
	}

	@Override
	protected String getTitulo() {
		return "Cor";
	}

	@Override
	protected List<Cor> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return "listaCor";
	}

	@Override
	protected CRUDFormController<Cor> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Cor> getEntityClass() {
		return Cor.class;
	}

	@Override
	protected List<Cor> pesquisaDefault() {
		return dao.getAll(Cor.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}
}
