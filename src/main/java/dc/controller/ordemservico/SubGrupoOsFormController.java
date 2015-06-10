package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.ordemservico.SubGrupoOsUtils;
import dc.control.validator.DotErpException;
import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.entidade.ordemservico.GrupoOsEntity;
import dc.model.business.ordemservico.GrupoOsBusiness;
import dc.model.business.ordemservico.SubGrupoOsBusiness;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.SubGrupoOsFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class SubGrupoOsFormController extends
		CRUDFormController<SubGrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	private SubGrupoOsFormView subView;

	private SubGrupoOsEntity currentBean;

	/**
	 * BUSINESS
	 */
	@Autowired
	private SubGrupoOsBusiness<SubGrupoOsEntity> business;

	@Autowired
	private GrupoOsBusiness<GrupoOsEntity> businessGrupoOs;

	/**
	 * CONSTRUTOR
	 */
	public SubGrupoOsFormController() {
	}

	public SubGrupoOsBusiness<SubGrupoOsEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "SubGrupo";
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
	public SubGrupoOsEntity getModelBean() {
		return currentBean;
	}
	
	@Override
	protected void initSubView() {
		try {
			this.subView = new SubGrupoOsFormView(this);

			preencheCombos();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			SubGrupoOsUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}
	
	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setGrupo(subView.getCbGrupo().getValue());
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
			subView.getCbGrupo().setValue(currentBean.getGrupo());
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
		currentBean = new SubGrupoOsEntity();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<GrupoOsEntity> grupo = new DefaultManyToOneComboModel<GrupoOsEntity>(
				GrupoOsListController.class, super.getMainController(), false, this.businessGrupoOs);

		this.subView.getCbGrupo().setModel(grupo);
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