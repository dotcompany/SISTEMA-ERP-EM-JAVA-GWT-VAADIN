package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.diverso.UfListController;
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.pessoal.ContadorDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.ContadorFormView;

@Controller
@Scope("prototype")
public class ContadorFormController extends CRUDFormController<ContadorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContadorFormView subView;

	@Autowired
	private ContadorDAO contadorDAO;

	@Autowired
	private UfDAO ufDAO;

	private ContadorEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		try {
			// Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
			fieldGroup.commit();
		    return true;
		} catch (FieldGroup.CommitException ce) {
		    return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new ContadorEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		
		try {
			this.subView = new ContadorFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(ContadorEntity.class);
			
			// Mapeia os campos
			
			fieldGroup.bind(this.subView.getTxtNome(),"nome");
			fieldGroup.bind(this.subView.getTxtCpf(),"cpf");
			fieldGroup.bind(this.subView.getTxtCnpj(),"cnpj");
			fieldGroup.bind(this.subView.getTxtLogradouro(),"logradouro");
			fieldGroup.bind(this.subView.getTxtBairro(),"bairro");
			fieldGroup.bind(this.subView.getTxtEmail(),"email");
			
			this.subView.getMocUf().configuraCombo(
					"nome", UfListController.class, this.ufDAO, this.getMainController());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.contadorDAO.find(id);
			fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void actionSalvar() {
		
		try {
			contadorDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			mensagemErro(ex.getMessage());
		}
	}

	@Override
	protected String getNome() {
		return "Contador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.contadorDAO.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public ContadorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}