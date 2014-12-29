package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.PaisValidator;
import dc.entidade.geral.diverso.PaisEntity;
import dc.model.business.geral.diverso.PaisBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.PaisFormView;

@Controller
@Scope("prototype")
public class PaisFormController extends CRUDFormController<PaisEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PaisFormView subView;

	/**
	 * ENTITY
	 */

	private PaisEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private PaisBusiness<PaisEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public PaisFormController() {

	}

	public PaisBusiness<PaisEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Pais";
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
	public PaisEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PaisFormView(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			PaisValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setNomePtbr(this.subView.getTfNome().getValue());
			this.entity
					.setNomeIngles(this.subView.getTfNomeIngles().getValue());
			this.entity.setSigla2(this.subView.getTfSigla2().getValue());
			this.entity.setSigla3(this.subView.getTfSigla3().getValue());

			String codigo = this.subView.getTfCodigo().getValue();

			if (NumberUtils.isNumber(codigo)) {
				this.entity.setCodigo(NumberUtils.toInt(codigo));
			} else {
				this.entity.setCodigo(null);
			}

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			criarNovoBean();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getTfNome().setValue(this.entity.getNomePtbr());
			this.subView.getTfNomeIngles()
					.setValue(this.entity.getNomeIngles());
			this.subView.getTfSigla2().setValue(this.entity.getSigla2());
			this.subView.getTfSigla3().setValue(this.entity.getSigla3());
			this.subView.getTfCodigo().setValue(
					this.entity.getCodigo().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new PaisEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new PaisEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAllByIds(ids);

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