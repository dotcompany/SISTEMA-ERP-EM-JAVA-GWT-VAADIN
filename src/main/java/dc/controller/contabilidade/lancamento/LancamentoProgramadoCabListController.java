package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoProgramadoCabEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoProgramadoCabDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoProgramadoCabListController extends
		CRUDListController<LancamentoProgramadoCabEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoProgramadoCabDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoProgramadoCabFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "dataInclusão", "tipo", "liberado" };
	}

	@Override
	protected Class<? super LancamentoProgramadoCabEntity> getEntityClass() {
		return LancamentoProgramadoCabEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado cab";
	}

	@Override
	protected List<LancamentoProgramadoCabEntity> pesquisa(String valor) {
		try {
			List<LancamentoProgramadoCabEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoCabEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoProgramadoCabEntity> getFormController() {
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
	protected List<LancamentoProgramadoCabEntity> pesquisaDefault() {
		try {
			List<LancamentoProgramadoCabEntity> auxLista = this.pDAO
					.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoProgramadoCabEntity>();
		}
	}

}