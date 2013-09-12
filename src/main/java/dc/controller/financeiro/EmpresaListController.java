package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.framework.Empresa;
import dc.servicos.dao.framework.geral.EmpresaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe temos a Extensão do CrudListController, tendo alguns métodos herdados,
 * como o pesquisar, e pegamos também algumas informações da classe FormController, herdando
 * algumas informações.
 * Temos a configuração das colunas.
 *
*/

@Controller
@Scope("prototype")
public class EmpresaListController extends CRUDListController<Empresa> {

	@Autowired
	private EmpresaDAO dao;

	@Autowired
	private EmpresaFormController empresaFormController;

	@Override
	protected String[] getColunas() {
		return new String[] {"nomeFantasia", "razaoSocial"};
	}

	@Override
	protected Class<? super Empresa> getEntityClass() {
		return Empresa.class;
	}


	@Override
	protected String getTitulo() {
		return "Empresa";
	}

	@Override
	protected List<Empresa> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Empresa> getFormController() {
		return empresaFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaEmpresa";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Empresa> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Empresa>) dao.getAll(getEntityClass());
	}

}