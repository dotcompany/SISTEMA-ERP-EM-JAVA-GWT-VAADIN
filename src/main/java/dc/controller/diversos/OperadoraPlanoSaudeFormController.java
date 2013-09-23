package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.diversos.OperadoraPlanoSaude;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.diversos.OperadoraPlanoSaudeDAO;
import dc.servicos.util.Validator;
import dc.visao.diversos.OperadoraPlanoSaudeFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;


@Controller
@Scope("prototype")
public class OperadoraPlanoSaudeFormController extends CRUDFormController<OperadoraPlanoSaude> {

	private  OperadoraPlanoSaudeFormView subView;

	@Autowired
	private OperadoraPlanoSaudeDAO operadoraPlanoSaudeDAO;
	
	@Autowired
	private ContabilContaDAO contabilContaDAO;
	
	private OperadoraPlanoSaude currentBean;
	
	private MainController mainController;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtRegistroANS().getValue())) {
			adicionarErroDeValidacao(subView.getTxtRegistroANS(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}
	
	@Override
	protected void criarNovoBean() {
		currentBean = new OperadoraPlanoSaude();
		
	}

	@Override
	protected void initSubView() {
		subView = new OperadoraPlanoSaudeFormView();
		DefaultManyToOneComboModel<ContabilConta> model= new DefaultManyToOneComboModel(ContabilContaListController . class , contabilContaDAO , mainController);
		
	}
	
	@Override
	protected void carregar(Serializable id) {
		currentBean = operadoraPlanoSaudeDAO.find(id);
		
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtRegistroANS().setValue(currentBean.getRegistroAns());
		
		// Configura combo
				ManyToOneComboModel<ContabilConta> model = new ManyToOneComboModel<ContabilConta>() {
					
					@Override
					public void onCriarNovo(String filter) {
						Notification.show("Selecionado Criar Novo: " + filter);
					}
					
					@Override
					public List<ContabilConta> getResultado(String q) {
						return contabilContaDAO.query(q);
					}
					
					@Override
					public Class<ContabilConta> getEntityClass() {
						return ContabilConta.class;
					}
					
					@Override
					public String getCaptionProperty() {
						return "classificacao";
					}

					@Override
					public void onEditar(ContabilConta value) {
						Notification.show("Selecionado Editar: " + value.getClassificacao());
						
					}
				};
				subView.getCmbContabilConta().setModel(model);
				subView.getCmbContabilConta().setValue(currentBean.getContabilConta());
	} 

	@Override
	protected void actionSalvar() {

		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setRegistroAns(subView.getTxtRegistroANS().getValue());
		
		try {
			operadoraPlanoSaudeDAO.saveOrUpdate(currentBean);


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
		return "Operadora Plano Saúde";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		operadoraPlanoSaudeDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}
	
	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}
	
	@Override
	public String getViewIdentifier() {
		return "operadoraPlanoSaudeForm";
	}
	
	@Override
	public boolean isFullSized(){
	   return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}
}