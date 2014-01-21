package dc.controller.contabilidade.demonstrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.demonstrativo.EncerramentoExeDetEntity;
import dc.servicos.dao.contabilidade.demonstrativo.EncerramentoExeDetDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class EncerramentoExeDetListController extends CRUDListController<EncerramentoExeDetEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private EncerramentoExeDetDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private EncerramentoExeDetFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "saldoAnterior", "valorDebito", "valorCredito", "saldo" };
	}

	@Override
	public Class<? super EncerramentoExeDetEntity> getEntityClass() {
		return EncerramentoExeDetEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Encerramento de exercício detalhe";
	}

	@Override
	protected List<EncerramentoExeDetEntity> pesquisa(String valor) {
		try {
			List<EncerramentoExeDetEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EncerramentoExeDetEntity>();
		}
	}

	@Override
	protected CRUDFormController<EncerramentoExeDetEntity> getFormController() {
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
	protected List<EncerramentoExeDetEntity> pesquisaDefault() {
		try {
			List<EncerramentoExeDetEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<EncerramentoExeDetEntity>();
		}
	}

}