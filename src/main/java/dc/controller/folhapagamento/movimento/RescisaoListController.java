package dc.controller.folhapagamento.movimento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.folhapagamento.movimento.RescisaoEntity;
import dc.servicos.dao.folhapagamento.movimento.RescisaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class RescisaoListController extends CRUDListController<RescisaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private RescisaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private RescisaoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "colaborador.matricula", "dataDemissao",
				"dataPagamento", "motivo" };
	}

	@Override
	protected Class<? super RescisaoEntity> getEntityClass() {
		return RescisaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Rescisão";
	}

	@Override
	protected List<RescisaoEntity> pesquisa(String valor) {
		try {
			List<RescisaoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<RescisaoEntity>();
		}
	}

	@Override
	protected CRUDFormController<RescisaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_rescisao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<RescisaoEntity> pesquisaDefault() {
		try {
			List<RescisaoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<RescisaoEntity>();
		}
	}

}