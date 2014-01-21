package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Cep;
import dc.servicos.dao.diversos.CepDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Wesley Jr /* Nessa classe temos a Extensão da classe principal que é
 *         crudListController Temos alguns métodos que pegamos, temos a
 *         configuração do Título da Tela; O Método do Button pesquisar, pegando
 *         um valor. e também ele pega algumas informações da classe
 *         FormController
 * 
 */

@Controller
@Scope("prototype")
public class CepListController extends CRUDListController<Cep> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	CepDAO dao;

	@Autowired
	CepFormController cepFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "cep", "logradouro" };
	}

	@Override
	public Class<? super Cep> getEntityClass() {
		return Cep.class;
	}

	@Override
	protected String getTitulo() {
		return "Cep";
	}

	@Override
	protected List<Cep> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Cep> getFormController() {
		return cepFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaCeps";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Cep> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Cep>) dao.getAll(getEntityClass());
	}

}