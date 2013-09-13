package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.LancamentoPagar;
import dc.servicos.dao.financeiro.LancamentoPagarDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class LancamentoPagarListController extends CRUDListController<LancamentoPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private LancamentoPagarDAO dao;

	@Autowired
	private LancamentoPagarFormController lancamentoPagarFormController;

	@Override
	protected String[] getColunas() {
		return new String[] { "pagamentoCompartilhado", "valorTotal", "valorAPagar", "dataLancamento", "imagemDocumento", "documentoOrigem",
				"fornecedor" };
	}

	@Override
	protected Class<? super LancamentoPagar> getEntityClass() {
		return LancamentoPagar.class;
	}

	@Override
	protected String getTitulo() {
		return "Lançamento à Pagar";
	}

	@Override
	protected List<LancamentoPagar> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<LancamentoPagar> getFormController() {
		return lancamentoPagarFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaLancamentoPagars";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<LancamentoPagar> pesquisaDefault() {
		return (List<LancamentoPagar>) dao.getAll(getEntityClass());
	}

}