package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.ordemservico.GrupoOsEntity;
import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.servicos.dao.ordemservico.GrupoDAO;
import dc.servicos.dao.ordemservico.SubGrupoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.SubGrupoFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class SubGrupoFormController extends CRUDFormController<SubGrupoOsEntity> {

	private static final long serialVersionUID = 1L;

	SubGrupoFormView subView;

	@Autowired
	SubGrupoDAO subGrupoDAO;

	@Autowired
	GrupoDAO grupoDAO;

	private SubGrupoOsEntity currentBean;

	@Override
	protected String getNome() {
		return "SubGrupo";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setGrupo(subView.getCbGrupo().getValue());
		try {
			subGrupoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = subGrupoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getCbGrupo().setValue(currentBean.getGrupo());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new SubGrupoFormView();

		preencheCombos();
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

		DefaultManyToOneComboModel<GrupoOsEntity> grupo = new DefaultManyToOneComboModel<GrupoOsEntity>(GrupoListController.class, this.grupoDAO,
				super.getMainController());

		this.subView.getCbGrupo().setModel(grupo);
	}

	@Override
	protected void remover(List<Serializable> ids) {
		subGrupoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "subGrupoForm";
	}

	@Override
	public SubGrupoOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
