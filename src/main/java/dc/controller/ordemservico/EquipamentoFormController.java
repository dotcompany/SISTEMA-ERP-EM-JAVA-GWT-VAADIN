package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.Equipamento;
import dc.servicos.dao.ordemservico.EquipamentoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.EquipamentoFormView;

/**
*
* @author Paulo Sérgio
*/ 

@Controller
@Scope("prototype")
public class EquipamentoFormController extends CRUDFormController<Equipamento> {

	private static final long serialVersionUID = 1L;

	EquipamentoFormView subView;
	
	@Autowired
	EquipamentoDAO equipamentoDAO;
	
	private Equipamento currentBean;
	 
	@Override
	protected String getNome() {
		return "Equipamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
		currentBean.setFilial(subView.getTfFilial().getValue());
		currentBean.setEquipamento(subView.getTfEquipamento().getValue());
		currentBean.setDescricao(subView.getTfDescricao().getValue());
		currentBean.setObservacao(subView.getTaObservacao().getValue());
		try{
			equipamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = equipamentoDAO.find(id);
		subView.getTfFilial().setValue(currentBean.getFilial());
		subView.getTfEquipamento().setValue(currentBean.getEquipamento());
		subView.getTfDescricao().setValue(currentBean.getEquipamento());
		subView.getTaObservacao().setValue(currentBean.getObservacao());
	}
	
	/* Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento padrÃ£o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new EquipamentoFormView();
		
	//	preencheCombos();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Equipamento();
	}
	
	private void preencheCombos() {

//		DefaultManyToOneComboModel<Marca> marca = new DefaultManyToOneComboModel<Marca>(MarcaListController.class,
//				this.marcaDAO, super.getMainController());
//
//		this.subView.getCbMarca().setModel(marca);
	}
	
	@Override
	protected void remover(List<Serializable> ids) {
		 equipamentoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTfEquipamento().getValue())) {
			adicionarErroDeValidacao(subView.getTfEquipamento(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "equipamentoForm";
	}
} 
