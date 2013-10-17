package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.cadastro.ParametrosEntity;
import dc.servicos.dao.contabilidade.cadastro.ParametrosDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller(value = "contabilidadeParametrosListController")
@Scope("prototype")
public class ParametrosListController extends
		CRUDListController<ParametrosEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ParametrosDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ParametrosFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super ParametrosEntity> getEntityClass() {
		return ParametrosEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Parâmetros";
	}

	@Override
	protected List<ParametrosEntity> pesquisa(String valor) {
		try {
			List<ParametrosEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametrosEntity>();
		}
	}

	@Override
	protected CRUDFormController<ParametrosEntity> getFormController() {
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
	protected List<ParametrosEntity> pesquisaDefault() {
		try {
			List<ParametrosEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametrosEntity>();
		}
	}

}