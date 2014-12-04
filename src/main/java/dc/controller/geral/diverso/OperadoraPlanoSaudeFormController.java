package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.OperadoraPlanoSaudeValidator;
import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.diverso.OperadoraPlanoSaudeEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.geral.diverso.OperadoraPlanoSaudeDAO;
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

	private OperadoraPlanoSaudeEntity currentBean;

	@Autowired
	private OperadoraPlanoSaudeDAO operadoraPlanoSaudeDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	// private MainController mainController;

	@Override
	protected boolean validaSalvar() {
		try {
			OperadoraPlanoSaudeValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new OperadoraPlanoSaudeEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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
	protected void carregar(Serializable id) {
		try {
			this.currentBean = operadoraPlanoSaudeDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfRegistroAns().setValue(
					this.currentBean.getRegistroAns());

			this.subView.getMocContabilConta().setValue(
					this.currentBean.getContabilConta());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setRegistroAns(this.subView.getTfRegistroAns()
					.getValue());

			ContabilContaEntity contabilConta = this.subView
					.getMocContabilConta().getValue();

			if (ObjectUtils.isNotBlank(contabilConta)) {
				this.currentBean.setContabilConta(contabilConta);
			} else {
				this.currentBean.setContabilConta(null);
			}

			this.operadoraPlanoSaudeDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
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
		try {
			this.operadoraPlanoSaudeDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	public String getViewIdentifier() {
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
	public OperadoraPlanoSaudeEntity getModelBean() {
		return currentBean;
	}

}