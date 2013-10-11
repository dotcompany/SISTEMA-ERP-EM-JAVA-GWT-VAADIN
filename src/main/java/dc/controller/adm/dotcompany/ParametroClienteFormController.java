package dc.controller.adm.dotcompany;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.visao.adm.dotcompany.ParametroClienteFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParametroClienteFormController extends CRUDFormController<ParametroCliente> {
	
	ParametroCliente currentBean;
	
	ParametroClienteFormView subView;

	@Override
	public String getViewIdentifier() {
		return "Parâmetro Cliente";
	}

	@Override
	protected boolean validaSalvar() {
		return false;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ParametroCliente();
		
	}

	@Override
	protected void initSubView() {
		subView = new ParametroClienteFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		
	}

	@Override
	protected void actionSalvar() {
		
	}

	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Parâmetro Cliente";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
	}
	
	

}
