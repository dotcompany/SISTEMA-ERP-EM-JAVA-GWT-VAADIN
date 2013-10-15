package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.cadastro.AidfAimdfEntity;
import dc.servicos.dao.contabilidade.cadastro.AidfAimdfDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class AidfAimdfListController extends
		CRUDListController<AidfAimdfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private AidfAimdfDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private AidfAimdfFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super AidfAimdfEntity> getEntityClass() {
		return AidfAimdfEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "AIDF / AIMDF";
	}

	@Override
	protected List<AidfAimdfEntity> pesquisa(String valor) {
		try {
			List<AidfAimdfEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<AidfAimdfEntity>();
		}
	}

	@Override
	protected CRUDFormController<AidfAimdfEntity> getFormController() {
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
	protected List<AidfAimdfEntity> pesquisaDefault() {
		try {
			List<AidfAimdfEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<AidfAimdfEntity>();
		}
	}

}