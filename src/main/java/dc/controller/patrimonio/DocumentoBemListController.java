package dc.controller.patrimonio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.patrimonio.DocumentoBemEntity;
import dc.servicos.dao.patrimonio.DocumentoBemDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class DocumentoBemListController extends
		CRUDListController<DocumentoBemEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private DocumentoBemDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private DocumentoBemFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "nome", "descricao", "bem" };
	}

	@Override
	protected Class<? super DocumentoBemEntity> getEntityClass() {
		return DocumentoBemEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Documento do bem";
	}

	@Override
	protected List<DocumentoBemEntity> pesquisa(String valor) {
		List<DocumentoBemEntity> auxLista = this.pDAO
				.procuraNomeContendo(valor);

		return auxLista;
	}

	@Override
	protected CRUDFormController<DocumentoBemEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "patrimonio_documento_bem_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<DocumentoBemEntity> pesquisaDefault() {
		List<DocumentoBemEntity> auxLista = this.pDAO.listarTodos();

		return auxLista;
	}

}