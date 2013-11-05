package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoCabecalhoEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoCabecalhoListController extends
		CRUDListController<LancamentoCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoCabecalhoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoCabecalhoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "dataLancamento", "dataInclusao", "tipo",
				"liberado", "valor" };
	}

	@Override
	protected Class<? super LancamentoCabecalhoEntity> getEntityClass() {
		return LancamentoCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento cabeçalho";
	}

	@Override
	protected List<LancamentoCabecalhoEntity> pesquisa(String valor) {
		try {
			List<LancamentoCabecalhoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoCabecalhoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoCabecalhoEntity> getFormController() {
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
	protected List<LancamentoCabecalhoEntity> pesquisaDefault() {
		try {
			List<LancamentoCabecalhoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoCabecalhoEntity>();
		}
	}

}