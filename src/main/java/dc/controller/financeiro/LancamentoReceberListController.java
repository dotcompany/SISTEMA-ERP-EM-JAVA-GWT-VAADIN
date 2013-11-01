package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.LancamentoReceber;
import dc.servicos.dao.financeiro.LancamentoReceberDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class LancamentoReceberListController extends CRUDListController<LancamentoReceber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LancamentoReceberDAO dao;

	@Autowired
	private LancamentoReceberFormController lancamentoReceberFormController;

	@Override
	protected String[] getColunas() {
		return new String[] { "valorTotal", "valorAReceber", "dataLancamento", "documentoOrigem", "cliente" };
	}

	@Override
	protected Class<? super LancamentoReceber> getEntityClass() {
		return LancamentoReceber.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento à Pagar";
	}

	@Override
	protected List<LancamentoReceber> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<LancamentoReceber> getFormController() {
		return lancamentoReceberFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaLancamentoRecebers";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<LancamentoReceber> pesquisaDefault() {
		return (List<LancamentoReceber>) dao.getAll(getEntityClass());
	}

}