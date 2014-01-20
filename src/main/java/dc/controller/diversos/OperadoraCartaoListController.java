package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.OperadoraCartao;
import dc.servicos.dao.diversos.OperadoraCartaoDAO;
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
@SuppressWarnings("unchecked")
public class OperadoraCartaoListController extends CRUDListController<OperadoraCartao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OperadoraCartaoDAO dao;

	@Autowired
	private OperadoraCartaoFormController operadoraFormController;

	@Override
	protected String[] getColunas() {
		return new String[] { "nome", "bandeira" };
	}

	@Override
	protected Class<? super OperadoraCartao> getEntityClass() {
		return OperadoraCartao.class;
	}

	@Override
	protected String getTitulo() {
		return "Operadora Cartão";
	}

	@Override
	protected List<OperadoraCartao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<OperadoraCartao> getFormController() {
		return operadoraFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaOperadoraCartao";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<OperadoraCartao> pesquisaDefault() {
		return (List<OperadoraCartao>) dao.getAll(getEntityClass());
	}

}