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
import dc.model.business.geral.produto.GrupoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.GrupoProdutoFormView;

@Controller
@Scope("prototype")
public class GrupoFormController extends CRUDFormController<GrupoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GrupoProdutoFormView subView;

	/**
	 * ENTITY
	 */

	private GrupoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private GrupoBusiness<GrupoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public GrupoFormController() {
		// TODO Auto-generated constructor stub
	}

	public GrupoBusiness<GrupoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Grupo";
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
	public GrupoEntity getModelBean() {
		return entity;
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
	protected void actionSalvar() {
		try {
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());

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
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new GrupoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new GrupoEntity();
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