package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.cadastro.ParametroEntity;
import dc.servicos.dao.contabilidade.cadastro.ParametroDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller(value = "contabilidadeParametroListController")
@Scope("prototype")
public class ParametroListController extends
		CRUDListController<ParametroEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private ParametroDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private ParametroFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super ParametroEntity> getEntityClass() {
		return ParametroEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Parâmetro";
	}

	@Override
	protected List<ParametroEntity> pesquisa(String valor) {
		try {
			List<ParametroEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametroEntity>();
		}
	}

	@Override
	protected CRUDFormController<ParametroEntity> getFormController() {
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
	protected List<ParametroEntity> pesquisaDefault() {
		try {
			List<ParametroEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<ParametroEntity>();
		}
	}

}