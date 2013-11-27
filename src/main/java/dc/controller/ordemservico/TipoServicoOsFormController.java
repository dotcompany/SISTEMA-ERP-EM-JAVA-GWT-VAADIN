package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.TipoServicoOsFormView;
import dc.entidade.ordemservico.TipoServicoOs;
import dc.servicos.dao.ordemservico.TipoServicoOsDAO;

/**
*
* @author Paulo Sérgio
*/ 

@Controller
@Scope("prototype")
public class TipoServicoOsFormController extends CRUDFormController<TipoServicoOs> {

	private static final long serialVersionUID = 1L;

	TipoServicoOsFormView subView;
	
	@Autowired
	TipoServicoOsDAO tipoServicoOsDAO;
	
	private TipoServicoOs currentBean;
	
	@Override
	protected String getNome() {
		return "TipoServicoOs";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		try{
			tipoServicoOsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoServicoOsDAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}
	
	/* Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento padrÃ£o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new TipoServicoOsFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoServicoOs();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoServicoOsDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "tipoServicoOsForm";
	}
} 
