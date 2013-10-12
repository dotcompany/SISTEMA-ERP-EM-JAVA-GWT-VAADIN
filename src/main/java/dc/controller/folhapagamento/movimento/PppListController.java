package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.movimento.PppEntity;
import dc.servicos.dao.folhapagamento.movimento.PppDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class PppListController extends CRUDListController<PppEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "observacao", "colaborador.matricula" };
	}

	@Override
	protected Class<? super PppEntity> getEntityClass() {
		return PppEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "PPP";
	}

	@Override
	protected List<PppEntity> pesquisa(String valor) {
		try {
			List<PppEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppEntity>();
		}
	}

	@Override
	protected CRUDFormController<PppEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_ppp_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PppEntity> pesquisaDefault() {
		try {
			List<PppEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppEntity>();
		}
	}

}