package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ParcelaPagamento;
import dc.servicos.dao.financeiro.ParcelaPagamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ParcelaPagarListController extends CRUDListController<ParcelaPagamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ParcelaPagamentoDAO dao;

	@Autowired
	private ParcelaPagamentoFormController parcelaPagamentoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "contaCaixa", "tipoPagamento", "dataPagamento", "taxaJuro", "taxaMulta", "taxaDesconto", "valorJuro",
				"valorMulta", "valorDesconto","valorPago" , "historico"};
	}

	@Override
	public Class<? super ParcelaPagamento> getEntityClass() {
		return ParcelaPagamento.class;
	}

	@Override
	protected String getTitulo() {
		return "Pagamento Parcela";
	}

	@Override
	protected List<ParcelaPagamento> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ParcelaPagamento> getFormController() {
		return parcelaPagamentoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<ParcelaPagamento> pesquisaDefault() {
		return (List<ParcelaPagamento>) dao.getAll(getEntityClass());
	}

}