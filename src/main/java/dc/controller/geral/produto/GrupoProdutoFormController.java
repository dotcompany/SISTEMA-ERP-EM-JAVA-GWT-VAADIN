package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.GrupoProdutoFormView;

@Controller
@Scope("prototype")
public class GrupoProdutoFormController extends CRUDFormController<GrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GrupoProdutoFormView subView;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	private GrupoEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(this.subView.getTfNome().getValue())) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(this.subView.getTfDescricao().getValue())) {
			adicionarErroDeValidacao(this.subView.getTfDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		this.currentBean = new GrupoEntity();
	}

	@Override
	protected void initSubView() {
		this.subView = new GrupoProdutoFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.grupoProdutoDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfDescricao().setValue(
					this.currentBean.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());

			this.grupoProdutoDAO.saveOrUpdate(this.currentBean);

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
		return "Grupo de produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.grupoProdutoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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
	public GrupoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}