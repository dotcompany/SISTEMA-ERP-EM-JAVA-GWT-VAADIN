package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.Adiantamento;
import dc.servicos.dao.financeiro.AdiantamentoDAO;
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
public class AdiantamentoListController extends CRUDListController<Adiantamento> {

	@Autowired
	AdiantamentoDAO dao;

	@Autowired
	AdiantamentoFormController adiantamentoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "valor", "onservacoes" };
	}

	@Override
	public Class<? super Adiantamento> getEntityClass() {
		return Adiantamento.class;
	}

	@Override
	protected String getTitulo() {
		return "Adiantamento";
	}

	@Override
	protected List<Adiantamento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Adiantamento> getFormController() {
		return adiantamentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaAdiantamento";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Adiantamento> pesquisaDefault() {
		return (List<Adiantamento>) dao.getAll(getEntityClass());
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}
