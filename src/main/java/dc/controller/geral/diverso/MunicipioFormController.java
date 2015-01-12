package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.classes.MunicipioUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.diverso.MunicipioEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.model.business.geral.diverso.MunicipioBusiness;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.MunicipioFormView;

@Controller
@Scope("prototype")
public class MunicipioFormController extends
		CRUDFormController<MunicipioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MunicipioFormView subView;

	/**
	 * ENTITY
	 */

	private MunicipioEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private MunicipioBusiness<MunicipioEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private UfDAO ufDAO;

	/**
	 * CONSTRUTOR
	 */

	public MunicipioFormController() {
		// TODO Auto-generated constructor stub
	}

	public MunicipioBusiness<MunicipioEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Município";
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
	public MunicipioEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new MunicipioFormView(this);

			DefaultManyToOneComboModel<UfEntity> model = new DefaultManyToOneComboModel<UfEntity>(
					UfListController.class, this.ufDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nome";
				}

			};

			this.subView.getMocUf().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			MunicipioUtils.validateRequiredFields(this.subView);

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

			String codigoEstadual = this.subView.getTfCodigoEstadual()
					.getValue();

			if (NumberUtils.isNumber(codigoEstadual)) {
				this.entity.setCodigoEstadual(NumberUtils.toInt(this.subView
						.getTfCodigoEstadual().getValue()));
			}

			String codigoIbge = this.subView.getTfCodigoIbge().getValue();

			if (NumberUtils.isNumber(codigoIbge)) {
				this.entity.setCodigoIbge(NumberUtils.toInt(this.subView
						.getTfCodigoIbge().getValue()));
			}

			String codigoReceitaFederal = this.subView
					.getTfCodigoReceitaFederal().getValue();

			if (NumberUtils.isNumber(codigoReceitaFederal)) {
				this.entity.setCodigoReceitaFederal(NumberUtils
						.toInt(this.subView.getTfCodigoReceitaFederal()
								.getValue()));
			}

			UfEntity uf = this.subView.getMocUf().getValue();

			if (ObjectUtils.isNotBlank(uf)) {
				this.entity.setUf(uf);
			} else {
				this.entity.setUf(null);
			}

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
			this.subView.getTfCodigoEstadual().setValue(
					this.entity.getCodigoEstadual().toString());
			this.subView.getTfCodigoIbge().setValue(
					this.entity.getCodigoIbge().toString());
			this.subView.getTfCodigoReceitaFederal().setValue(
					this.entity.getCodigoReceitaFederal().toString());

			this.subView.getMocUf().setValue(this.entity.getUf());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new MunicipioEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new MunicipioEntity();
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