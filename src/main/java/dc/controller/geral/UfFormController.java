package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.classes.UfUtils;
import dc.control.validator.DotErpException;
import dc.controller.geral.diverso.PaisListController;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.PaisEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.servicos.dao.geral.diverso.PaisDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.UfFormView;

@Controller
@Scope("prototype")
public class UfFormController extends CRUDFormController<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UfFormView subView;

	/**
	 * ENTITY
	 */

	private UfEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private UfBusiness<UfEntity> business;

	// @Autowired
	// private PaisBusiness<PaisEntity> paisBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private PaisDAO paisDAO;

	/**
	 * CONSTRUTOR
	 */

	public UfFormController() {
		// TODO Auto-generated constructor stub
	}

	public UfBusiness<UfEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "UF";
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
	public UfEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UfFormView(this);

			DefaultManyToOneComboModel<PaisEntity> model = new DefaultManyToOneComboModel<PaisEntity>(
					PaisListController.class, this.paisDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nomePtbr";
				}

			};

			this.subView.getMocPais().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			UfUtils.validateRequiredFields(this.subView);

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
			this.entity.setSigla(this.subView.getTfSigla().getValue());

			String codigoIbge = this.subView.getTfCodigoIbge().getValue();

			if (NumberUtils.isNumber(codigoIbge)) {
				this.entity.setCodigoIbge(NumberUtils.toInt(this.subView
						.getTfCodigoIbge().getValue()));
			}

			PaisEntity pais = this.subView.getMocPais().getValue();

			this.entity.setPais(pais);

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
			this.subView.getTfSigla().setValue(this.entity.getSigla());
			this.subView.getTfCodigoIbge().setValue(
					this.entity.getCodigoIbge().toString());

			PaisEntity pais = this.entity.getPais();

			if (ObjectUtils.isNotBlank(pais)) {
				this.subView.getMocPais().setValue(pais);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new UfEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new UfEntity();
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
		// TODO Auto-generated method stub

	}

}