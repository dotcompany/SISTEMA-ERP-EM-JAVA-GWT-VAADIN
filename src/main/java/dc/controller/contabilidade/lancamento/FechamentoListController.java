package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.FechamentoEntity;
import dc.servicos.dao.contabilidade.lancamento.FechamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller(value = "contabilidadeFechamentoListController")
@Scope("prototype")
public class FechamentoListController extends
		CRUDListController<FechamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private FechamentoDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private FechamentoFormController pController;

	@Override
	protected String[] getColunas() {
		return new String[] { "dataInicio", "dataFim", "criterioLancamento" };
	}

	@Override
	protected Class<? super FechamentoEntity> getEntityClass() {
		return FechamentoEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Fechamento";
	}

	@Override
	protected List<FechamentoEntity> pesquisa(String valor) {
		try {
			List<FechamentoEntity> auxLista = this.pDAO
					.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FechamentoEntity>();
		}
	}

	@Override
	protected CRUDFormController<FechamentoEntity> getFormController() {
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
	protected List<FechamentoEntity> pesquisaDefault() {
		try {
			List<FechamentoEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<FechamentoEntity>();
		}
	}

}