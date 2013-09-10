package dc.controller.folhapagamento.ausencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.ausencia.FeriasColetivasEntity;
import dc.servicos.dao.folhapagamento.ausencia.FeriasColetivasDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class FeriasColetivasListController extends
		CRUDListController<FeriasColetivasEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private FeriasColetivasDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private FeriasColetivasFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "dataInicio", "dataFim", "dataPagamento" };
	}

	@Override
	protected Class<? super FeriasColetivasEntity> getEntityClass() {
		return FeriasColetivasEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Férias coletivas";
	}

	@Override
	protected List<FeriasColetivasEntity> pesquisa(String valor) {
		List<FeriasColetivasEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<FeriasColetivasEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_ausencia_ferias_coletivas_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<FeriasColetivasEntity> pesquisaDefault() {
		List<FeriasColetivasEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}