package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.NcmUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.NcmEntity;
import dc.model.business.geral.produto.NcmBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.NcmFormView;

@Controller
@Scope("prototype")
public class NcmFormController extends CRUDFormController<NcmEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NcmFormView subView;

	/**
	 * ENTITY
	 */

	private NcmEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private NcmBusiness<NcmEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public NcmFormController() {
		// TODO Auto-generated constructor stub
	}

	public NcmBusiness<NcmEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "NCM";
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
	public NcmEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new NcmFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			NcmUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setCodigo(this.subView.getTfCodigo().getValue());
			this.entity.setNome(this.subView.getTfDescricao().getValue());
			this.entity
					.setObservacao(this.subView.getTfObservacao().getValue());

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

			this.subView.getTfCodigo().setValue(this.entity.getCodigo());
			this.subView.getTfDescricao().setValue(this.entity.getNome());
			this.subView.getTfObservacao()
					.setValue(this.entity.getObservacao());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new NcmEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new NcmEntity();
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