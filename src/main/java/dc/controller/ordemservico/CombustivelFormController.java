package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.CombustivelFormView;
import dc.entidade.ordemservico.Combustivel;
import dc.servicos.dao.ordemservico.CombustivelDAO;

/**
*
* @author Paulo Sérgio
*/ 

@Controller
@Scope("prototype")
public class CombustivelFormController extends CRUDFormController<Combustivel> {

	private static final long serialVersionUID = 1L;

	CombustivelFormView subView;
	
	@Autowired
	CombustivelDAO combustivelDAO;
	
	private Combustivel currentBean;
	
	@Override
	protected String getNome() {
		return "Combustivel";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		try{
			combustivelDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = combustivelDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
	}
	
	/* Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento padrÃ£o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new CombustivelFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Combustivel();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 combustivelDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
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
		return "combustivelForm";
	}
} 