package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.cadastro.HistoricoEntity;
import dc.servicos.dao.contabilidade.cadastro.HistoricoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class HistoricoListController extends CRUDListController<HistoricoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private HistoricoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private HistoricoFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "descricao", "historico", "pedeComplemento" };
	}

	@Override
	public Class<? super HistoricoEntity> getEntityClass() {
		return HistoricoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Histórico";
	}

	@Override
	protected List<HistoricoEntity> pesquisa(String valor) {
		try {
			List<HistoricoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<HistoricoEntity>();
		}
	}

	@Override
	protected CRUDFormController<HistoricoEntity> getFormController() {
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
	protected List<HistoricoEntity> pesquisaDefault() {
		try {
			List<HistoricoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<HistoricoEntity>();
		}
	}

}