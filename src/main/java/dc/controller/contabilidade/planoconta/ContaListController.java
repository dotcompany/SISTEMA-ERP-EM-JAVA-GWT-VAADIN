package dc.controller.contabilidade.planoconta;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.planoconta.ContaEntity;
import dc.servicos.dao.contabilidade.planoconta.ContaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ContaListController extends CRUDListController<ContaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ContaDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ContaFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "classificacao", "tipo", "descricao" };
	}

	@Override
	public Class<? super ContaEntity> getEntityClass() {
		return ContaEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Conta";
	}

	@Override
	protected List<ContaEntity> pesquisa(String valor) {
		try {
			List<ContaEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ContaEntity>();
		}
	}

	@Override
	protected CRUDFormController<ContaEntity> getFormController() {
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
	protected List<ContaEntity> pesquisaDefault() {
		try {
			List<ContaEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ContaEntity>();
		}
	}

}