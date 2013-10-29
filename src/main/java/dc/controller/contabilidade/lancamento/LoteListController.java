package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LoteEntity;
import dc.servicos.dao.contabilidade.lancamento.LoteDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LoteListController extends CRUDListController<LoteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LoteDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LoteFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "descricao", "dataInclusao", "dataLiberacao" };
	}

	@Override
	protected Class<? super LoteEntity> getEntityClass() {
		return LoteEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento programado";
	}

	@Override
	protected List<LoteEntity> pesquisa(String valor) {
		try {
			List<LoteEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LoteEntity>();
		}
	}

	@Override
	protected CRUDFormController<LoteEntity> getFormController() {
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
	protected List<LoteEntity> pesquisaDefault() {
		try {
			List<LoteEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LoteEntity>();
		}
	}

}