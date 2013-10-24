package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.Orcamento;
import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.OrcamentoDAO;
import dc.servicos.dao.comercial.TipoNotaFiscalDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.OrcamentoFormView;
import dc.visao.comercial.TipoNotaFiscalFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class OrcamentoFormController extends CRUDFormController<Orcamento> {

	Orcamento currentBean;

	OrcamentoFormView subView;

	@Autowired
	OrcamentoDAO dao;

	@Override
	public String getViewIdentifier() {
		return "orcamentoForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Orcamento();

	}

	@Override
	protected void initSubView() {
		subView = new OrcamentoFormView(this);

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
		return "Orçamento de Venda";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean isFullSized(){
		return true;
	}



}
