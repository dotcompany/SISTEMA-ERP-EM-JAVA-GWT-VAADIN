package dc.controller.diversos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.diversos.Estado;
import dc.servicos.dao.diversos.EstadoDAO;
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
public class EstadoListController extends CRUDListController<Estado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EstadoDAO dao;

	@Autowired
	private EstadoFormController estadoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "sigla" };
	}

	@Override
	public Class<? super Estado> getEntityClass() {
		return Estado.class;
	}

	@Override
	protected String getTitulo() {
		return "Estado";
	}

	@Override
	protected List<Estado> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Estado> getFormController() {
		return estadoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaEstado";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Estado> pesquisaDefault() {
		return (List<Estado>) dao.getAll(getEntityClass());
	}

}