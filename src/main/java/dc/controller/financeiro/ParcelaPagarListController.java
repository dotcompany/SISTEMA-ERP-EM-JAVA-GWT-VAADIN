package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ParcelaPagar;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ParcelaPagarListController extends CRUDListController<ParcelaPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ParcelaPagarDAO dao;

	@Autowired
	private ParcelaPagamentoFormController parcelaPagamentoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "contaCaixa", "dataVencimento", "taxaJuro", "taxaMulta", "taxaDesconto", "valorJuro",
				"valorMulta", "valorDesconto","valor"};
	}

	@Override
	public Class<? super ParcelaPagar> getEntityClass() {
		return ParcelaPagar.class;
	}

	@Override
	protected String getTitulo() {
		return "Pagamento Parcela";
	}

	@Override
	protected List<ParcelaPagar> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ParcelaPagar> getFormController() {
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
	protected List<ParcelaPagar> pesquisaDefault() {
		return (List<ParcelaPagar>) dao.getAll(getEntityClass());
	}

}