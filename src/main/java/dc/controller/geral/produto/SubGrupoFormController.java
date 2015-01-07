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
import dc.model.business.geral.produto.SubGrupoBusiness;
import dc.servicos.dao.geral.produto.GrupoDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.SubGrupoProdutoFormView;

@Controller
@Scope("prototype")
public class SubGrupoFormController extends CRUDFormController<SubGrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubGrupoProdutoFormView subView;

	/**
	 * ENTITY
	 */

	private SubGrupoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private SubGrupoBusiness<SubGrupoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private GrupoDAO grupoDAO;

	/**
	 * CONSTRUTOR
	 */

	public SubGrupoFormController() {
		// TODO Auto-generated constructor stub
	}

	public SubGrupoBusiness<SubGrupoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Subgrupo";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public SubGrupoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SubGrupoProdutoFormView(this);

			DefaultManyToOneComboModel<GrupoEntity> model = new DefaultManyToOneComboModel<GrupoEntity>(
					GrupoListController.class, this.grupoDAO,
					super.getMainController());

			this.subView.getMocGrupoProduto().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	protected void actionSalvar() {
		try {
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());

			this.entity.setGrupo(this.subView.getMocGrupoProduto().getValue());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfNome().setValue(this.entity.getNome());
			this.subView.getTfDescricao().setValue(this.entity.getDescricao());

			// DefaultManyToOneComboModel<GrupoEntity> model = new
			// DefaultManyToOneComboModel<GrupoEntity>(
			// GrupoListController.class, this.grupoProdutoDAO,
			// super.getMainController());

			// this.subView.getMocGrupoProduto().setModel(model);

			this.subView.getMocGrupoProduto().setValue(this.entity.getGrupo());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new SubGrupoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new SubGrupoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

}