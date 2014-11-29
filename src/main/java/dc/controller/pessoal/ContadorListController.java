package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.ContadorEntity;
import dc.servicos.dao.pessoal.ContadorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContadorListController extends CRUDListController<ContadorEntity> {
	

	@Autowired
	ContadorDAO dao;

	@Autowired
	ContadorFormController contadorFormController;

	@Override
	protected CRUDFormController<ContadorEntity> getFormController() {
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
	public Class<? super ContadorEntity> getEntityClass() {
		return ContadorEntity.class;
	}

	@Override
	protected List<ContadorEntity> pesquisa(String valor) {
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
	protected List<ContadorEntity> pesquisaDefault() {
		return (List<ContadorEntity>) dao.getAll(getEntityClass());
	}
	
}
