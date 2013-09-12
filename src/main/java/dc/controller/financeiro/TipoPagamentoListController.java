package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.financeiro.TipoPagamento;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoPagamentoListController extends CRUDListController<TipoPagamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoPagamentoDAO dao;

	@Autowired
	private TipoPagamentoFormController tipoPagamentoFormController;

	@Override
	protected String[] getColunas() {
		return new String[] { "tipo", "descricao" };
	}

	@Override
	protected Class<? super TipoPagamento> getEntityClass() {
		return TipoPagamento.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo Pagamento";
	}

	@Override
	protected List<TipoPagamento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<TipoPagamento> getFormController() {
		return tipoPagamentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "listaTipoPagamentos";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<TipoPagamento> pesquisaDefault() {
		return (List<TipoPagamento>) dao.getAll(getEntityClass());
	}

}
