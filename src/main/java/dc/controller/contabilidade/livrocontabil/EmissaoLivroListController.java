package dc.controller.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.livrocontabil.EmissaoLivroEntity;
import dc.servicos.dao.contabilidade.livrocontabil.EmissaoLivroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EmissaoLivroListController extends
		CRUDListController<EmissaoLivroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EmissaoLivroDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EmissaoLivroFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super EmissaoLivroEntity> getEntityClass() {
		return EmissaoLivroEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Emissão de livro";
	}

	@Override
	protected List<EmissaoLivroEntity> pesquisa(String valor) {
		try {
			List<EmissaoLivroEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EmissaoLivroEntity>();
		}
	}

	@Override
	protected CRUDFormController<EmissaoLivroEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "folhapagamento_movimento_alteracao_salarial_lc";
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<EmissaoLivroEntity> pesquisaDefault() {
		try {
			List<EmissaoLivroEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EmissaoLivroEntity>();
		}
	}

}