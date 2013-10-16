package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoLoteEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoLoteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoLoteListController extends
		CRUDListController<LancamentoLoteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoLoteDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoLoteFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super LancamentoLoteEntity> getEntityClass() {
		return LancamentoLoteEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado";
	}

	@Override
	protected List<LancamentoLoteEntity> pesquisa(String valor) {
		try {
			List<LancamentoLoteEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoLoteEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoLoteEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<LancamentoLoteEntity> pesquisaDefault() {
		try {
			List<LancamentoLoteEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoLoteEntity>();
		}
	}

}