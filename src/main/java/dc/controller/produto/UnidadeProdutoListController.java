package dc.controller.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.produto.UnidadeProduto;
import dc.servicos.dao.produto.UnidadeProdutoDAO;
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
public class UnidadeProdutoListController extends CRUDListController<UnidadeProduto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UnidadeProdutoDAO dao;

	@Autowired
	UnidadeProdutoFormController unidadeProdutoFormController;

	@Override
	protected String[] getColunas() {
		return new String[] { "sigla", "nome" };
	}

	@Override
	protected Class<? super UnidadeProduto> getEntityClass() {
		return UnidadeProduto.class;
	}

	@Override
	protected String getTitulo() {
		return "Unidade Produto";
	}

	@Override
	protected List<UnidadeProduto> pesquisa(String valor) {
		try {
			return (List<UnidadeProduto>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<UnidadeProduto>();
		}
	}

	@Override
	protected CRUDFormController<UnidadeProduto> getFormController() {
		return unidadeProdutoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaUnidadeProduto";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<UnidadeProduto> pesquisaDefault() {
		try {
			return (List<UnidadeProduto>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<UnidadeProduto>();
		}
	}

}