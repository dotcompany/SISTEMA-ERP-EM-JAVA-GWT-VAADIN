package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.TipoServicoFormView;
import dc.entidade.ordemservico.TipoServico;
import dc.servicos.dao.ordemservico.TipoServicoDAO;

@Controller
@Scope("prototype")
public class TipoServicoFormController extends CRUDFormController<TipoServico> {

	private static final long serialVersionUID = 1L;

	TipoServicoFormView subView;
	
	@Autowired
	TipoServicoDAO tipoServicoDAO;
	
	private TipoServico currentBean;
	
	@Override
	protected String getNome() {
		return "Tipo de Serviço"; 
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		try{
		    currentBean.setDescricao(subView.getTfDescricao().getValue());
			tipoServicoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoServicoDAO.find(id);
		
		subView.getTfDescricao().setValue(currentBean.getDescricao());
	}
	
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new TipoServicoFormView();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoServico();
	}
	
	@Override
	protected void remover(List<Serializable> ids) {
		 tipoServicoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTfDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTfDescricao(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}
 
	@Override 
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "tipoServicoForm";
	}
} 