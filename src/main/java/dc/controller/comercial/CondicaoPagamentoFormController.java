package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.CondicaoPagamento;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.CondicaoPagamentoDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.CondicaoPagamentoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class CondicaoPagamentoFormController extends CRUDFormController<CondicaoPagamento> {

	CondicaoPagamento currentBean;

	CondicaoPagamentoFormView subView;

	@Autowired
	CondicaoPagamentoDAO dao;

	@Override
	public String getViewIdentifier() {
		return "tipoNotaForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new CondicaoPagamento();

	}

	@Override
	protected void initSubView() {
		subView = new CondicaoPagamentoFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void actionSalvar() {

		

	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Condição de Pagamento";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}



}
