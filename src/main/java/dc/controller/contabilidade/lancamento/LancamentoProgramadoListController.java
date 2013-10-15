package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoProgramadoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoProgramadoListController extends
		CRUDListController<LancamentoProgramadoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoProgramadoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoProgramadoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super LancamentoProgramadoEntity> getEntityClass() {
		return LancamentoProgramadoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado";
	}

	@Override
	protected List<LancamentoProgramadoEntity> pesquisa(String valor) {
		try {
			List<LancamentoProgramadoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoProgramadoEntity> getFormController() {
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
	protected List<LancamentoProgramadoEntity> pesquisaDefault() {
		try {
			List<LancamentoProgramadoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoEntity>();
		}
	}

}