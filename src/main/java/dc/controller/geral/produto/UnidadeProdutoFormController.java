package dc.controller.geral.produto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.control.util.classes.UnidadeProdutoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.model.business.geral.produto.UnidadeProdutoBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.UnidadeProdutoFormView;

@Controller
@Scope("prototype")
public class UnidadeProdutoFormController extends
		CRUDFormController<UnidadeProdutoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UnidadeProdutoFormView subView;

	/**
	 * ENTITY
	 */

	private UnidadeProdutoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private UnidadeProdutoBusiness<UnidadeProdutoEntity> business;

	/**
	 * DAO
	 */

	/**
	 * CONSTRUTOR
	 */

	public UnidadeProdutoFormController() {
		// TODO Auto-generated constructor stub
	}

	public UnidadeProdutoBusiness<UnidadeProdutoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Unidade do produto";
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
	public UnidadeProdutoEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UnidadeProdutoFormView(this);

			comboPodeFracionar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			UnidadeProdutoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.entity.setSigla(this.subView.getTfSigla().getValue());
			this.entity.setDescricao(this.subView.getTfDescricao().getValue());

			SimNaoEn en = SimNaoEn.getEnum(this.subView.getCbPodeFracionar()
					.getValue().toString());

			this.entity.setPodeFracionar(en);

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

			this.subView.getTfSigla().setValue(this.entity.getSigla());
			this.subView.getTfDescricao().setValue(this.entity.getDescricao());
			this.subView.getCbPodeFracionar().setValue(
					(SimNaoEn.valueOf(this.entity.getPodeFracionar().name()))
							.toString());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new UnidadeProdutoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new UnidadeProdutoEntity();
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

	/**
	 * COMBOS
	 */

	public void comboPodeFracionar() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbPodeFracionar().addItem(en);
		}
	}

}