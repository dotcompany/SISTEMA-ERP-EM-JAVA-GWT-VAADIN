package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.Contador;
import dc.servicos.dao.pessoal.ContadorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContadorListController extends CRUDListController<Contador> {
	

	@Autowired
	ContadorDAO dao;

	@Autowired
	ContadorFormController contadorFormController;

	@Override
	protected CRUDFormController<Contador> getFormController() {
		return contadorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "logradouro", "bairro" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaContador";
	}

	@Override
	public Class<? super Contador> getEntityClass() {
		return Contador.class;
	}

	@Override
	protected List<Contador> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Contador";
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
	protected List<Contador> pesquisaDefault() {
		return (List<Contador>) dao.getAll(getEntityClass());
	}
	
}
