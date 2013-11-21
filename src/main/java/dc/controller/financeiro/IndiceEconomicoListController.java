package dc.controller.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.servicos.dao.financeiro.IndiceEconomicoDAO;
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
public class IndiceEconomicoListController extends
		CRUDListController<IndiceEconomicoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IndiceEconomicoDAO pDAO;

	@Autowired
	private IndiceEconomicoFormController indiceFormController;

	@Override
	protected String[] getColunas() {
		return new String[] { "nome", "sigla" };
	}

	@Override
	protected Class<? super IndiceEconomicoEntity> getEntityClass() {
		return IndiceEconomicoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "IndiceEconomico";
	}

	@Override
	protected List<IndiceEconomicoEntity> pesquisa(String valor) {
		try {
			List<IndiceEconomicoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceEconomicoEntity>();
		}
	}

	@Override
	protected CRUDFormController<IndiceEconomicoEntity> getFormController() {
		return indiceFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaIndices";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<IndiceEconomicoEntity> pesquisaDefault() {
		try {
			List<IndiceEconomicoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<IndiceEconomicoEntity>();
		}
	}

}