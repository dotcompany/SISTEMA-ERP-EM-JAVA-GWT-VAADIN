package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.servicos.dao.geral.pessoal.AtividadeForCliDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.AtividadeForCliFormView;

@Controller
@Scope("prototype")
public class AtividadeForCliFormController extends
		CRUDFormController<AtividadeForCliEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AtividadeForCliFormView subView;

	@Autowired
	private AtividadeForCliDAO atividadeForCliDAO;

	private AtividadeForCliEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new AtividadeForCliEntity();
	}

	@Override
	protected void initSubView() {
		subView = new AtividadeForCliFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = atividadeForCliDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			atividadeForCliDAO.saveOrUpdate(currentBean);

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
		String s = "";

		return ":::: ";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		atividadeForCliDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

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
	public AtividadeForCliEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}