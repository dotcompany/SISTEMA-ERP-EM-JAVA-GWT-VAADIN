package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.UF;
import dc.servicos.dao.geral.UFDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.CidadeFormView;
import dc.visao.geral.UFFormView;


/**
*
* @author Wesley Jr
* **/

@Controller
@Scope("prototype")
public class UFFormController extends CRUDFormController<UF> {

	private UFFormView subView;
	
	@Autowired
	private UFDAO ufDAO;

	private UF currentBean;
	
	@Override
	protected String getNome() {
		return "UF";
	}

	@Override
	protected Component getSubView() {
		return null;
	}

	@Override  
	protected void actionSalvar() {
		try{
			ufDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = ufDAO.find(id);
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new UFFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new UF();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		ufDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "ufForm";
	}

}
