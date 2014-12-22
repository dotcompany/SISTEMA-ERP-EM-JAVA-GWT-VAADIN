package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.BancoEntity;
import dc.servicos.dao.financeiro.BancoDAO;
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
public class BancoListController extends CRUDListController<BancoEntity> {

	@Autowired
	BancoDAO dao;

	@Autowired
	BancoFormController bancoFormController;

	@Override
	public String[] getColunas() {
		return new String[] {"codigo", "nome", "url" };
	}

	@Override
	public Class<? super BancoEntity> getEntityClass() {
		return BancoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Banco";
	}

	@Override
	protected List<BancoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<BancoEntity> getFormController() {
		return bancoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaBancos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<BancoEntity> pesquisaDefault() {
		return (List<BancoEntity>) dao.getAll(getEntityClass());
	}

}