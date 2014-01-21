package dc.controller.sistema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.framework.Papel;
import dc.servicos.dao.sistema.PapelDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PapelListController extends CRUDListController<Papel> {

	@Autowired
	PapelDAO dao;

	@Autowired
	PapelFormController papelFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super Papel> getEntityClass() {
		return Papel.class;
	}

	@Override
	protected String getTitulo() {
		return "Papel";
	}

	@Override
	protected List<Papel> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Papel> getFormController() {
		return papelFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaPapeis";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected List<Papel> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Papel>) dao.getAll(this.getEntityClass());
	}

}