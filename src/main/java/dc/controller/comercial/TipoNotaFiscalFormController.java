package dc.controller.comercial;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.comercial.TipoNotaFiscal;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class TipoNotaFiscalFormController extends CRUDFormController<TipoNotaFiscal> {

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void criarNovoBean() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initSubView() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getNome() {
		// TODO Auto-generated method stub
		return null;
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
