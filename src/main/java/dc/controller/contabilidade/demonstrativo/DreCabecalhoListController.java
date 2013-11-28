package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.DreCabecalhoEntity;
import dc.servicos.dao.contabilidade.demonstrativo.DreCabecalhoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class DreCabecalhoListController extends
		CRUDListController<DreCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private DreCabecalhoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DreCabecalhoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "descricao", "padrao", "periodoInicial",
				"periodoFinal" };
	}

	@Override
	protected Class<? super DreCabecalhoEntity> getEntityClass() {
		return DreCabecalhoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "DRE cabeçalho";
	}

	@Override
	protected List<DreCabecalhoEntity> pesquisa(String valor) {
		try {
			List<DreCabecalhoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreCabecalhoEntity>();
		}
	}

	@Override
	protected CRUDFormController<DreCabecalhoEntity> getFormController() {
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
	protected List<DreCabecalhoEntity> pesquisaDefault() {
		try {
			List<DreCabecalhoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DreCabecalhoEntity>();
		}
	}

}