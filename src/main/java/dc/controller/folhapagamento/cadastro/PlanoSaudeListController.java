package dc.controller.folhapagamento.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.cadastro.PlanoSaudeEntity;
import dc.servicos.dao.folhapagamento.cadastro.PlanoSaudeDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class PlanoSaudeListController extends
		CRUDListController<PlanoSaudeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PlanoSaudeDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PlanoSaudeFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "colaborador.matricula", "beneficiario",
				"dataInicio" };
	}

	@Override
	protected Class<? super PlanoSaudeEntity> getEntityClass() {
		return PlanoSaudeEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Plano de saúde";
	}

	@Override
	protected List<PlanoSaudeEntity> pesquisa(String valor) {
		try {
			List<PlanoSaudeEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoSaudeEntity>();
		}
	}

	@Override
	protected CRUDFormController<PlanoSaudeEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_cadastro_plano_saude_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PlanoSaudeEntity> pesquisaDefault() {
		try {
			List<PlanoSaudeEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PlanoSaudeEntity>();
		}
	}

}