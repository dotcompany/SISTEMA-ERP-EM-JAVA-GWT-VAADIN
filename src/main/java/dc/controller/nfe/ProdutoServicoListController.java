package dc.controller.nfe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.servicos.dao.nfe.NfeDetalheDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ProdutoServicoListController extends
		CRUDListController<NfeDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeDetalheDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ProdutoServicoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "competencia", "formaEscrituracao" };
	}

	@Override
	public Class<? super NfeDetalheEntity> getEntityClass() {
		return NfeDetalheEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Produto / serviço";
	}

	@Override
	protected List<NfeDetalheEntity> pesquisa(String valor) {
		try {
			List<NfeDetalheEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheEntity>();
		}
	}

	@Override
	protected CRUDFormController<NfeDetalheEntity> getFormController() {
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
	protected List<NfeDetalheEntity> pesquisaDefault() {
		try {
			List<NfeDetalheEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<NfeDetalheEntity>();
		}
	}

}