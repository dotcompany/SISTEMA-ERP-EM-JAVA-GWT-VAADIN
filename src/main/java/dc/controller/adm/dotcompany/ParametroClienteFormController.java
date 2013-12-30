package dc.controller.adm.dotcompany;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.servicos.dao.adm.dotcompany.ParametroClienteDAO;
import dc.visao.adm.dotcompany.ParametroClienteFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParametroClienteFormController extends CRUDFormController<ParametroCliente> {
	
	
	private ParametroCliente currentBean;
	
	ParametroClienteFormView subView;
	
	@Autowired
	ParametroClienteDAO parametroClienteDAO;

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
		currentBean = parametroClienteDAO.find(id);
		
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
		parametroClienteDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
		
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		parametroClienteDAO.deleteAll(objetos);
		 mensagemRemovidoOK();
	}
	
	@Override
	public String getViewIdentifier() {
		return "parametroClienteForm";
	}
	
	

}
