package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.StatusOsEntity;
import dc.servicos.dao.ordemservico.StatusOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.StatusOsFormView;

@Controller
@Scope("prototype")
public class StatusOsFormController extends CRUDFormController<StatusOsEntity> {

	private static final long serialVersionUID = 1L;

	StatusOsFormView subView;

	@Autowired
	StatusOsDAO statusOsDAO;

	private StatusOsEntity currentBean;

	@Override
	protected String getNome() {
		return "Status O.S";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDescricao(subView.getTfDescricao().getValue());
			statusOsDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = statusOsDAO.find(id);

		subView.getTfDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new StatusOsFormView();
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new StatusOsEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		statusOsDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
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
		return "statusOsForm";
	}

	@Override
	public StatusOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
