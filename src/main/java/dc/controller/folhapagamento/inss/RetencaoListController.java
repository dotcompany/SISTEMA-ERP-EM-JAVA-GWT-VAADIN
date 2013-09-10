package dc.controller.folhapagamento.inss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.inss.RetencaoEntity;
import dc.servicos.dao.folhapagamento.inss.RetencaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class RetencaoListController extends CRUDListController<RetencaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private RetencaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private RetencaoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "inss.competencia", "servico.nome",
				"valorMensal", "valor13" };
	}

	@Override
	protected Class<? super RetencaoEntity> getEntityClass() {
		return RetencaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Retenção";
	}

	@Override
	protected List<RetencaoEntity> pesquisa(String valor) {
		List<RetencaoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<RetencaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_inss_retencao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<RetencaoEntity> pesquisaDefault() {
		List<RetencaoEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}