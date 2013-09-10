package dc.controller.folhapagamento.movimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.movimento.ValeTransporteEntity;
import dc.servicos.dao.folhapagamento.movimento.ValeTransporteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ValeTransporteListController extends
		CRUDListController<ValeTransporteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ValeTransporteDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ValeTransporteFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "numero", "bem.nome", "seguradora.nome" };
	}

	@Override
	protected Class<? super ValeTransporteEntity> getEntityClass() {
		return ValeTransporteEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Vale transporte";
	}

	@Override
	protected List<ValeTransporteEntity> pesquisa(String valor) {
		List<ValeTransporteEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<ValeTransporteEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_vale_transporte_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ValeTransporteEntity> pesquisaDefault() {
		List<ValeTransporteEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}