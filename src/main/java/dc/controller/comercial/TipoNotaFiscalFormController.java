package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.visao.comercial.TipoNotaFiscalFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class TipoNotaFiscalFormController extends CRUDFormController<TipoNotaFiscal> {
	
	TipoNotaFiscal currentBean;
	
	TipoNotaFiscalFormView subView;

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
		currentBean = new TipoNotaFiscal();
		
	}

	@Override
	protected void initSubView() {
		subView = new TipoNotaFiscalFormView(this);
		
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void actionSalvar() {
		// TODO Auto-generated method stub
		
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
		return "Tipo de Nota Fiscal";
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
