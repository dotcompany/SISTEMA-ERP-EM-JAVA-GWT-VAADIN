package dc.controller.diversos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.diversos.Setor;
import dc.servicos.dao.diversos.SetorDAO;
import dc.servicos.util.Validator;
import dc.visao.diversos.SetorFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class SetorFormController extends CRUDFormController<Setor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SetorFormView subView;

	@Autowired
	private SetorDAO setorDAO;

	private Setor currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Setor();
	}

	@Override
	protected void initSubView() {
		subView = new SetorFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = setorDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			setorDAO.saveOrUpdate(currentBean);

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
		return "Setor";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		setorDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "setorForm";
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
	public Setor getModelBean() {
		return currentBean;
	}

}