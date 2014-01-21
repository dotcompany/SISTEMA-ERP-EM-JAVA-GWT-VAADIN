package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.DfcEntity;
import dc.servicos.dao.contabilidade.demonstrativo.DfcDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class DfcListController extends CRUDListController<DfcEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private DfcDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DfcFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	public Class<? super DfcEntity> getEntityClass() {
		return DfcEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "DFC";
	}

	@Override
	protected List<DfcEntity> pesquisa(String valor) {
		try {
			List<DfcEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DfcEntity>();
		}
	}

	@Override
	protected CRUDFormController<DfcEntity> getFormController() {
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
	protected List<DfcEntity> pesquisaDefault() {
		try {
			List<DfcEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<DfcEntity>();
		}
	}

}