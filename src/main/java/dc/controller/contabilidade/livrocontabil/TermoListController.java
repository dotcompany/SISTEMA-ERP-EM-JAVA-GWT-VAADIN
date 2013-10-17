package dc.controller.contabilidade.livrocontabil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.livrocontabil.TermoEntity;
import dc.servicos.dao.contabilidade.livrocontabil.TermoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class TermoListController extends CRUDListController<TermoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private TermoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private TermoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "" };
	}

	@Override
	protected Class<? super TermoEntity> getEntityClass() {
		return TermoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Termo";
	}

	@Override
	protected List<TermoEntity> pesquisa(String valor) {
		try {
			List<TermoEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TermoEntity>();
		}
	}

	@Override
	protected CRUDFormController<TermoEntity> getFormController() {
		return this.pController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<TermoEntity> pesquisaDefault() {
		try {
			List<TermoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<TermoEntity>();
		}
	}

}