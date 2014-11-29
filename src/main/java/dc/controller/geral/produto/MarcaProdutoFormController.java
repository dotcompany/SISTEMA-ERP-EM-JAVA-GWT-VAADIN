package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.MarcaProdutoEntity;
import dc.servicos.dao.geral.produto.MarcaProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.MarcaProdutoFormView;

@Controller
@Scope("prototype")
public class MarcaProdutoFormController extends
		CRUDFormController<MarcaProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MarcaProdutoFormView subView;

	@Autowired
	private MarcaProdutoDAO marcaProdutoDAO;

	private MarcaProdutoEntity currentBean;

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
		currentBean = new MarcaProdutoEntity();
	}

	@Override
	protected void initSubView() {
		subView = new MarcaProdutoFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = marcaProdutoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			marcaProdutoDAO.saveOrUpdate(currentBean);

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
		return "Marca Produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		marcaProdutoDAO.deleteAllByIds(ids);

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
	public MarcaProdutoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}