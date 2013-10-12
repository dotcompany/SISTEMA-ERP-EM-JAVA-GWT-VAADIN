package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.movimento.PppFatorRiscoEntity;
import dc.servicos.dao.folhapagamento.movimento.PppFatorRiscoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class PppFatorRiscoListController extends
		CRUDListController<PppFatorRiscoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppFatorRiscoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppFatorRiscoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "dataInicio", "dataTermino", "tipo" };
	}

	@Override
	protected Class<? super PppFatorRiscoEntity> getEntityClass() {
		return PppFatorRiscoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "PPP fator de risco";
	}

	@Override
	protected List<PppFatorRiscoEntity> pesquisa(String valor) {
		try {
			List<PppFatorRiscoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppFatorRiscoEntity>();
		}
	}

	@Override
	protected CRUDFormController<PppFatorRiscoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_ppp_fator_risco_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PppFatorRiscoEntity> pesquisaDefault() {
		try {
			List<PppFatorRiscoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PppFatorRiscoEntity>();
		}
	}

}