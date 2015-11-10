package dc.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.comercial.CondicaoPagamento;
import dc.servicos.dao.comercial.ICondicaoPagamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CondicaoPagamentoListController extends CRUDListController<CondicaoPagamento> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ICondicaoPagamentoDAO dao;

	@Autowired
	private CondicaoPagamentoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	protected String getTitulo() {
		return "Condições Pagamento";
	}

	@Override
	protected CRUDFormController<CondicaoPagamento> getFormController() {
		return formController;
	}

	@Override
	public Class<? super CondicaoPagamento> getEntityClass() {
		return CondicaoPagamento.class;
	}

	@Override
	public String getViewIdentifier() {
		return "Condições Pagamento";
	}

	@Override
	protected List<CondicaoPagamento> pesquisa(String valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<CondicaoPagamento> pesquisaDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}
