package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExercicioEntity;
import dc.servicos.dao.contabilidade.demonstrativo.EncerramentoExercicioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EncerramentoExercicioListController extends
		CRUDListController<EncerramentoExercicioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EncerramentoExercicioDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EncerramentoExercicioFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super EncerramentoExercicioEntity> getEntityClass() {
		return EncerramentoExercicioEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Encerramentop de exercício";
	}

	@Override
	protected List<EncerramentoExercicioEntity> pesquisa(String valor) {
		try {
			List<EncerramentoExercicioEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EncerramentoExercicioEntity>();
		}
	}

	@Override
	protected CRUDFormController<EncerramentoExercicioEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EncerramentoExercicioEntity> pesquisaDefault() {
		try {
			List<EncerramentoExercicioEntity> auxLista = this.pDAO
					.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EncerramentoExercicioEntity>();
		}
	}

}