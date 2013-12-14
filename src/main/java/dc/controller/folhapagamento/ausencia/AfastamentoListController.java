package dc.controller.folhapagamento.ausencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.ausencia.AfastamentoEntity;
import dc.servicos.dao.folhapagamento.ausencia.AfastamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class AfastamentoListController extends
		CRUDListController<AfastamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private AfastamentoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private AfastamentoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "colaborador.matricula", "diasAfastado",
				"dataInicio", "dataFim" };
	}

	@Override
	protected Class<? super AfastamentoEntity> getEntityClass() {
		return AfastamentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Afastamento";
	}

	@Override
	protected List<AfastamentoEntity> pesquisa(String valor) {
		List<AfastamentoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<AfastamentoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_ausencia_afastamento_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<AfastamentoEntity> pesquisaDefault() {
		List<AfastamentoEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}