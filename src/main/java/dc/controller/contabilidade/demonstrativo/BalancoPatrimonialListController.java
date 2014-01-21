package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.BalancoPatrimonialEntity;
import dc.servicos.dao.contabilidade.demonstrativo.BalancoPatrimonialDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class BalancoPatrimonialListController extends CRUDListController<BalancoPatrimonialEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private BalancoPatrimonialDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private BalancoPatrimonialFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	public Class<? super BalancoPatrimonialEntity> getEntityClass() {
		return BalancoPatrimonialEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Balanço patrimonial";
	}

	@Override
	protected List<BalancoPatrimonialEntity> pesquisa(String valor) {
		try {
			List<BalancoPatrimonialEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<BalancoPatrimonialEntity>();
		}
	}

	@Override
	protected CRUDFormController<BalancoPatrimonialEntity> getFormController() {
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
	protected List<BalancoPatrimonialEntity> pesquisaDefault() {
		try {
			List<BalancoPatrimonialEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<BalancoPatrimonialEntity>();
		}
	}

}