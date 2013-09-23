package dc.controller.folhapagamento.movimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.movimento.HistoricoSalarialEntity;
import dc.servicos.dao.folhapagamento.movimento.HistoricoSalarialDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class HistoricoSalarialListController extends
		CRUDListController<HistoricoSalarialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private HistoricoSalarialDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private HistoricoSalarialFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "competencia", "salarioAtual",
				"percentualAumento", "salarioNovo", "colaborador.matricula" };
	}

	@Override
	protected Class<? super HistoricoSalarialEntity> getEntityClass() {
		return HistoricoSalarialEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Histórico salarial";
	}

	@Override
	protected List<HistoricoSalarialEntity> pesquisa(String valor) {
		List<HistoricoSalarialEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<HistoricoSalarialEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_historico_salarial_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<HistoricoSalarialEntity> pesquisaDefault() {
		List<HistoricoSalarialEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}