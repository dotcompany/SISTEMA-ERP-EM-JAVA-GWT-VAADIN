package dc.controller.folhapagamento.movimento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.movimento.PppCatEntity;
import dc.servicos.dao.folhapagamento.movimento.PppCatDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class PppCatListController extends CRUDListController<PppCatEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private PppCatDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private PppCatFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "numeroCat", "dataAfastamento", "dataRegistro",
				"ppp.observacao" };
	}

	@Override
	protected Class<? super PppCatEntity> getEntityClass() {
		return PppCatEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "PPP cat";
	}

	@Override
	protected List<PppCatEntity> pesquisa(String valor) {
		List<PppCatEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<PppCatEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_ppp_cat_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<PppCatEntity> pesquisaDefault() {
		List<PppCatEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}