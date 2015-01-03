package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.SubGrupoProdutoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.servicos.dao.geral.produto.SubGrupoProdutoDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.SubGrupoProdutoFormView;

@Controller
@Scope("prototype")
public class SubGrupoProdutoFormController extends
		CRUDFormController<SubGrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubGrupoProdutoFormView subView;

	private SubGrupoEntity currentBean;

	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	@Override
	protected boolean validaSalvar() {
		try {
			SubGrupoProdutoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new SubGrupoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SubGrupoProdutoFormView(this);

			DefaultManyToOneComboModel<GrupoEntity> model = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoProdutoListController.class, this.grupoProdutoDAO,
					super.getMainController());

			this.subView.getMocGrupoProduto().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.subGrupoProdutoDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfDescricao().setValue(
					this.currentBean.getDescricao());

			DefaultManyToOneComboModel<GrupoEntity> model = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoProdutoListController.class, this.grupoProdutoDAO,
					super.getMainController());

			this.subView.getMocGrupoProduto().setModel(model);

			this.subView.getMocGrupoProduto().setValue(
					this.currentBean.getGrupo());
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

			this.currentBean.setGrupo(this.subView.getMocGrupoProduto()
					.getValue());

			this.subGrupoProdutoDAO.saveOrUpdate(this.currentBean);

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
		return "Subgrupo de produto";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.subGrupoProdutoDAO.deleteAllByIds(ids);

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
	public SubGrupoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}