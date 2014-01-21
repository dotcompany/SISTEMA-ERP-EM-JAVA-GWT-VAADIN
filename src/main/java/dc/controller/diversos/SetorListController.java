package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Setor;
import dc.servicos.dao.diversos.SetorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class SetorListController extends CRUDListController<Setor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SetorDAO dao;

	@Autowired
	SetorFormController setorFormController;

	@Override
	protected CRUDFormController<Setor> getFormController() {
		return setorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaSetor";
	}

	@Override
	public Class<? super Setor> getEntityClass() {
		return Setor.class;
	}

	@Override
	protected List<Setor> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Setor";
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
	protected List<Setor> pesquisaDefault() {
		return (List<Setor>) dao.getAll(getEntityClass());
	}

}