package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.MarcaProdutoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.MarcaEntity;
import dc.model.business.geral.produto.MarcaBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.MarcaProdutoFormView;

@Controller
@Scope("prototype")
public class MarcaFormController extends CRUDFormController<MarcaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MarcaProdutoFormView subView;

	/**
	 * ENTITY
	 */

	private MarcaEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private MarcaBusiness<MarcaEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public MarcaFormController() {
		// TODO Auto-generated constructor stub
	}

	public MarcaBusiness<MarcaEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Marca";
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
	public MarcaEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new MarcaProdutoFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			MarcaProdutoUtils.validateRequiredFields(this.subView);

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
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new MarcaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new MarcaEntity();
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