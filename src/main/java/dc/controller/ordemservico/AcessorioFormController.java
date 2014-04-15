package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.AcessorioFormView;
import dc.entidade.ordemservico.Acessorio;
import dc.servicos.dao.ordemservico.AcessorioDAO;


@Controller
@Scope("prototype")
public class AcessorioFormController extends CRUDFormController<Acessorio> {

	private static final long serialVersionUID = 1L;

	AcessorioFormView subView;
	
	@Autowired
	AcessorioDAO acessorioDAO;
	
	private Acessorio currentBean;
	
	@Override
	protected String getNome() {
		return "Acessorio";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		try{
			acessorioDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = acessorioDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
	}
	
	@Override
	protected void initSubView() {
		subView = new AcessorioFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Acessorio();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		acessorioDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "acessorioForm";
	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub
		
	}
} 
