package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.classes.OperadoraPlanoSaudeUtils;
import dc.control.validator.DotErpException;
import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.model.business.geral.diverso.OperadoraPlanoSaudeBusiness;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.OperadoraPlanoSaudeFormView;

@Controller
@Scope("prototype")
public class OperadoraPlanoSaudeFormController extends
		CRUDFormController<OperadoraPlanoSaudeEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperadoraPlanoSaudeFormView subView;

	/**
	 * ENTITY
	 */

	private OperadoraPlanoSaudeEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private OperadoraPlanoSaudeBusiness<OperadoraPlanoSaudeEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	/**
	 * CONSTRUTOR
	 */

	public OperadoraPlanoSaudeFormController() {
		// TODO Auto-generated constructor stub
	}

	public OperadoraPlanoSaudeBusiness<OperadoraPlanoSaudeEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Operadora de plano de saúde";
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
	public OperadoraPlanoSaudeEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new OperadoraPlanoSaudeFormView(this);

			DefaultManyToOneComboModel<ContabilContaEntity> model = new DefaultManyToOneComboModel<ContabilContaEntity>(
					ContabilContaListController.class, this.contabilContaDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getMocContabilConta().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			OperadoraPlanoSaudeUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setRegistroAns(this.subView.getTfRegistroAns()
					.getValue());

			ContabilContaEntity contabilConta = this.subView
					.getMocContabilConta().getValue();

			if (ObjectUtils.isNotBlank(contabilConta)) {
				this.entity.setContabilConta(contabilConta);
			} else {
				this.entity.setContabilConta(null);
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfRegistroAns().setValue(
					this.entity.getRegistroAns());

			this.subView.getMocContabilConta().setValue(
					this.entity.getContabilConta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new OperadoraPlanoSaudeEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new OperadoraPlanoSaudeEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

}