package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.AgenciaBanco;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
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
public class AgenciaBancoListController extends CRUDListController<AgenciaBanco> {

	@Autowired
	AgenciaBancoDAO dao;

	@Autowired
	AgenciaBancoFormController agenciaBancoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "logradouro" };
	}

	@Override
	public Class<? super AgenciaBanco> getEntityClass() {
		return AgenciaBanco.class;
	}

	@Override
	protected String getTitulo() {
		return "Agência Banco";
	}

	@Override
	protected List<AgenciaBanco> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<AgenciaBanco> getFormController() {
		return agenciaBancoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaAgenciaBanco";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<AgenciaBanco> pesquisaDefault() {
		return (List<AgenciaBanco>) dao.getAll(getEntityClass());
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}
