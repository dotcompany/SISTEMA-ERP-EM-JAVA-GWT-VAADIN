package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.ordemservico.GrupoUtils;
import dc.control.validator.DotErpException;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.model.business.ordemservico.GrupoOsBusiness;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.GrupoOsFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class GrupoOsFormController extends CRUDFormController<GrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	private GrupoOsFormView subView;

	private GrupoOsEntity currentBean;

	/**
	 * BUSINESS
	 */
	@Autowired
	private GrupoOsBusiness<GrupoOsEntity> business;

	/**
	 * CONSTRUTOR
	 */
	public GrupoOsFormController() {
	}

	public GrupoOsBusiness<GrupoOsEntity> getBusiness() {
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
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public GrupoOsEntity getModelBean() {
		return currentBean;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new GrupoOsFormView(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			GrupoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		this.currentBean.setNome(subView.getTxtNome().getValue());
		try {
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.business.find(id);

			subView.getTxtNome().setValue(currentBean.getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new GrupoOsEntity();
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