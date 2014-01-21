package dc.controller.contabilidade.lancamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.lancamento.LancamentoDetalheEntity;
import dc.servicos.dao.contabilidade.lancamento.LancamentoDetalheDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class LancamentoDetalheListController extends CRUDListController<LancamentoDetalheEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * DAO'S
	 */

	@Autowired
	private LancamentoDetalheDAO pDAO;

	/**
	 * CONTROLLER'S
	 */

	@Autowired
	private LancamentoDetalheFormController pController;

	@Override
	public String[] getColunas() {
		return new String[] { "historico", "valor", "tipo" };
	}

	@Override
	public Class<? super LancamentoDetalheEntity> getEntityClass() {
		return LancamentoDetalheEntity.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento detalhe";
	}

	@Override
	protected List<LancamentoDetalheEntity> pesquisa(String valor) {
		try {
			List<LancamentoDetalheEntity> auxLista = this.pDAO.procuraNomeContendo(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoDetalheEntity>();
		}
	}

	@Override
	protected CRUDFormController<LancamentoDetalheEntity> getFormController() {
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
	protected List<LancamentoDetalheEntity> pesquisaDefault() {
		try {
			List<LancamentoDetalheEntity> auxLista = this.pDAO.listarTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<LancamentoDetalheEntity>();
		}
	}

}