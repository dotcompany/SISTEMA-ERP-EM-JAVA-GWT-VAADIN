package dc.controller.patrimonio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.EstadoConservacaoEntity;
import dc.servicos.dao.patrimonio.EstadoConservacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EstadoConservacaoListController extends
		CRUDListController<EstadoConservacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EstadoConservacaoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EstadoConservacaoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "codigo", "nome", "descricao",
				"empresa.nomeFantasia" };
	}

	@Override
	protected Class<? super EstadoConservacaoEntity> getEntityClass() {
		return EstadoConservacaoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Estado de conservação";
	}

	@Override
	protected List<EstadoConservacaoEntity> pesquisa(String valor) {
		List<EstadoConservacaoEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<EstadoConservacaoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_estado_conservacao_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EstadoConservacaoEntity> pesquisaDefault() {
		List<EstadoConservacaoEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}