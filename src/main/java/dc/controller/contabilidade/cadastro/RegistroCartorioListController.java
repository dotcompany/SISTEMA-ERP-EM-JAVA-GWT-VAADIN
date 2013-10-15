package dc.controller.contabilidade.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.contabilidade.cadastro.RegistroCartorioEntity;
import dc.servicos.dao.contabilidade.cadastro.RegistroCartorioDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class RegistroCartorioListController extends
		CRUDListController<RegistroCartorioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private RegistroCartorioDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private RegistroCartorioFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super RegistroCartorioEntity> getEntityClass() {
		return RegistroCartorioEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Registro de cartório";
	}

	@Override
	protected List<RegistroCartorioEntity> pesquisa(String valor) {
		try {
			List<RegistroCartorioEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<RegistroCartorioEntity>();
		}
	}

	@Override
	protected CRUDFormController<RegistroCartorioEntity> getFormController() {
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
	protected List<RegistroCartorioEntity> pesquisaDefault() {
		try {
			List<RegistroCartorioEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<RegistroCartorioEntity>();
		}
	}

}