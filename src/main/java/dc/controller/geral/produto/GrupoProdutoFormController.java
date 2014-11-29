package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.produto.GrupoProdutoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.GrupoProdutoFormView;

@Controller
@Scope("prototype")
public class GrupoProdutoFormController extends
		CRUDFormController<GrupoProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GrupoProdutoFormView subView;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	private GrupoProdutoEntity currentBean;

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
		currentBean = new GrupoProdutoEntity();
	}

	@Override
	protected void initSubView() {
		subView = new GrupoProdutoFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = grupoProdutoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			grupoProdutoDAO.saveOrUpdate(currentBean);

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
		return "Grupo Produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		grupoProdutoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "grupoProdutoForm";
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
	public GrupoProdutoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}