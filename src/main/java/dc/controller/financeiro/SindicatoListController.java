package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.Sindicato;
import dc.servicos.dao.financeiro.SindicatoDAO;
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
public class SindicatoListController extends CRUDListController<Sindicato> {

	@Autowired
	SindicatoDAO dao;

	@Autowired
	SindicatoFormController sindicatoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "logradouro" };
	}

	@Override
	public Class<? super Sindicato> getEntityClass() {
		return Sindicato.class;
	}

	@Override
	protected String getTitulo() {
		return "Sindicato";
	}

	@Override
	protected List<Sindicato> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Sindicato> getFormController() {
		return sindicatoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaSindicato";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<Sindicato> pesquisaDefault() {
		// TODO Auto-generated method stub
		return (List<Sindicato>) dao.getAll(getEntityClass());
	}

}
