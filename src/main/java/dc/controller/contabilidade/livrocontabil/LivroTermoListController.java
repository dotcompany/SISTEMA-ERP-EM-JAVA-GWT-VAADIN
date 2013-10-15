package dc.controller.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.livrocontabil.LivroTermoEntity;
import dc.servicos.dao.contabilidade.livrocontabil.LivroTermoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LivroTermoListController extends
		CRUDListController<LivroTermoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LivroTermoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LivroTermoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super LivroTermoEntity> getEntityClass() {
		return LivroTermoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Livro de termo";
	}

	@Override
	protected List<LivroTermoEntity> pesquisa(String valor) {
		try {
			List<LivroTermoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LivroTermoEntity>();
		}
	}

	@Override
	protected CRUDFormController<LivroTermoEntity> getFormController() {
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
	protected List<LivroTermoEntity> pesquisaDefault() {
		try {
			List<LivroTermoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LivroTermoEntity>();
		}
	}

}