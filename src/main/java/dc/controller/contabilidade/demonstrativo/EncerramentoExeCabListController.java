package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeCabEntity;
import dc.servicos.dao.contabilidade.demonstrativo.EncerramentoExeCabDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EncerramentoExeCabListController extends
		CRUDListController<EncerramentoExeCabEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EncerramentoExeCabDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EncerramentoExeCabFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "data_inicio", "data_fim", "motivo" };
	}

	@Override
	protected Class<? super EncerramentoExeCabEntity> getEntityClass() {
		return EncerramentoExeCabEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Encerramento de exercício cabeçalho";
	}

	@Override
	protected List<EncerramentoExeCabEntity> pesquisa(String valor) {
		try {
			List<EncerramentoExeCabEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EncerramentoExeCabEntity>();
		}
	}

	@Override
	protected CRUDFormController<EncerramentoExeCabEntity> getFormController() {
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
	protected List<EncerramentoExeCabEntity> pesquisaDefault() {
		try {
			List<EncerramentoExeCabEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EncerramentoExeCabEntity>();
		}
	}

}