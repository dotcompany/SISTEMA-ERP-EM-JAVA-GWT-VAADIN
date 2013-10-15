package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoContabilEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoContabilDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoContabilListController extends
		CRUDListController<LancamentoContabilEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoContabilDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoContabilFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super LancamentoContabilEntity> getEntityClass() {
		return LancamentoContabilEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado";
	}

	@Override
	protected List<LancamentoContabilEntity> pesquisa(String valor) {
		try {
			List<LancamentoContabilEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoContabilEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoContabilEntity> getFormController() {
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
	protected List<LancamentoContabilEntity> pesquisaDefault() {
		try {
			List<LancamentoContabilEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoContabilEntity>();
		}
	}

}