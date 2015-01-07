package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.GrupoProdutoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.GrupoEntity;
import dc.servicos.dao.geral.produto.GrupoDAO;
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

	private GrupoEntity currentBean;

	@Autowired
	private GrupoDAO grupoProdutoDAO;

	@Override
	protected boolean validaSalvar() {
		try {
			GrupoProdutoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new GrupoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new GrupoProdutoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
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