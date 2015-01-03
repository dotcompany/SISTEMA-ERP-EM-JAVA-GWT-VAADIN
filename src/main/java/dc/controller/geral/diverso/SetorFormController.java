package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.SetorUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.SetorEntity;
import dc.model.business.geral.diverso.SetorBusiness;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.SetorFormView;

@Controller
@Scope("prototype")
public class SetorFormController extends CRUDFormController<SetorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SetorFormView subView;

	/**
	 * ENTITY
	 */

	private SetorEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private SetorBusiness<SetorEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private UfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public SetorFormController() {
		// TODO Auto-generated constructor stub
	}

	public SetorBusiness<SetorEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Setor";
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
	public SetorEntity getModelBean() {
		return entity;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			SetorUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new SetorFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
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
			this.entity = new SetorEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new SetorEntity();
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

	}

}