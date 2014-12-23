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
import dc.control.validator.DotErpException;
import dc.control.validator.classe.UfValidator;
import dc.controller.geral.diverso.PaisListController;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.dao.geral.UfDAO;
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

	private UfEntity currentBean;

	@Autowired
	private UfDAO ufDAO;

	@Autowired
	private PaisDAO paisDAO;

	public UfFormController() {
		// TODO Auto-generated constructor stub
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
		return currentBean;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UfFormView(this);

			DefaultManyToOneComboModel<PaisEntity> paisModel = new DefaultManyToOneComboModel<PaisEntity>(
					PaisListController.class, this.paisDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nomePtbr";
				}

			};

			this.subView.getMocPais().setModel(paisModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			UfValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setSigla(this.subView.getTfSigla().getValue());

			String codigoIbge = this.subView.getTfCodigoIbge().getValue();

			if (NumberUtils.isNumber(codigoIbge)) {
				this.currentBean.setCodigoIbge(NumberUtils.toInt(this.subView
						.getTfCodigoIbge().getValue()));
			}

			PaisEntity pais = this.subView.getMocPais().getValue();

			this.currentBean.setPais(pais);

			this.ufDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			criarNovoBean();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.ufDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfSigla().setValue(this.currentBean.getSigla());
			this.subView.getTfCodigoIbge().setValue(
					this.currentBean.getCodigoIbge().toString());

			PaisEntity pais = this.currentBean.getPais();

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
			this.currentBean = new UfEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.currentBean = new UfEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.ufDAO.deleteAllByIds(ids);

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