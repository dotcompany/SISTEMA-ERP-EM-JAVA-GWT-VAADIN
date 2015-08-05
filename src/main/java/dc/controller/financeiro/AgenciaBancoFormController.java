package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.BancoDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.financeiro.AgenciaBancoFormView;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class AgenciaBancoFormController extends
		CRUDFormController<AgenciaBancoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AgenciaBancoFormView subView;

	private AgenciaBancoEntity currentBean;

	@Autowired
	private AgenciaBancoDAO agenciaBancoDAO;

	@Autowired
	private BancoDAO bancoDAO;

	@Autowired
	private UfDAO ufDAO;

	@Override
	protected String getNome() {
		return "Agência bancária";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public AgenciaBancoEntity getModelBean() {
		return currentBean;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new AgenciaBancoFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(AgenciaBancoEntity.class);
			
			// Mapeia os campos
			fieldGroup.bind(this.subView.getTfCodigo(),"codigo");
			fieldGroup.bind(this.subView.getTfNome(),"nome");
			fieldGroup.bind(this.subView.getTfLogradouro(),"logradouro");
			fieldGroup.bind(this.subView.getTfCep(),"cep");
			fieldGroup.bind(this.subView.getTfTelefone(),"telefone");
			fieldGroup.bind(this.subView.getTfContato(),"contato");
			fieldGroup.bind(this.subView.getTfGerente(),"gerente");
			
			this.subView.getMocBanco().configuraCombo(
					"nome", BancoListController.class, this.bancoDAO, this.getMainController());

			carregarUf();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {

			 fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.agenciaBancoDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.agenciaBancoDAO.find(id);
			
			 fieldGroup.setItemDataSource(this.currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new AgenciaBancoEntity();
			
			 fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.agenciaBancoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 */

	public void carregarUf() {
		try {
			List<UfEntity> auxLista = this.ufDAO.listaTodos();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			this.subView.getCbUf().setContainerDataSource(bic);
			this.subView.getCbUf().setItemCaptionPropertyId("nome");
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}