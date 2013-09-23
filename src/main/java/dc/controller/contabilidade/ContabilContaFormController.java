package dc.controller.contabilidade;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contabilidade.ContabilConta;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.visao.contabilidade.ContabilContaFormView;
import dc.visao.framework.geral.CRUDFormController;


@Controller
@Scope("prototype")
public class ContabilContaFormController extends CRUDFormController<ContabilConta> {

	private  ContabilContaFormView subView;

	@Autowired
	private ContabilContaDAO contabilContaDAO;
	
	private ContabilConta currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new ContabilConta();
		
	}

	@Override
	protected void initSubView() {
		subView = new ContabilContaFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contabilContaDAO.find(id);
		
	}

	@Override
	protected void actionSalvar() {

		try {
			contabilContaDAO.saveOrUpdate(currentBean);


			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected String getNome() {
		return "ContabilConta";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		contabilContaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "contabilContaForm";
	}
	
	@Override
	public boolean isFullSized(){
	   return true;
	}

	@Override
	protected Component getSubView() {
		return null;
	}
}