package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.SubGrupoEntity;
import dc.servicos.dao.geral.produto.GrupoProdutoDAO;
import dc.servicos.dao.geral.produto.SubGrupoProdutoDAO;
import dc.servicos.util.Validator;
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

	@Autowired
	private SubGrupoProdutoDAO subGrupoProdutoDAO;

	@Autowired
	private GrupoProdutoDAO grupoProdutoDAO;

	private SubGrupoEntity currentBean;

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

		if (!Validator.validateObject(this.subView.getMocGrupoProduto()
				.getValue())) {
			adicionarErroDeValidacao(this.subView.getTfDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		this.currentBean = new SubGrupoEntity();
	}

	@Override
	protected void initSubView() {
		this.subView = new SubGrupoProdutoFormView();

		DefaultManyToOneComboModel<GrupoEntity> model = new DefaultManyToOneComboModel<GrupoEntity>(
				GrupoProdutoListController.class, this.grupoProdutoDAO,
				super.getMainController());

		this.subView.getMocGrupoProduto().setModel(model);
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