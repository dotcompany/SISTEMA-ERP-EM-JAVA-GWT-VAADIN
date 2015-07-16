package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.ordemservico.TipoServicoOsEntity;
import dc.servicos.dao.ordemservico.TipoServicoOsDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.TipoServicoFormView;

@Controller
@Scope("prototype")
public class TipoServicoFormController extends CRUDFormController<TipoServicoOsEntity> {

	private static final long serialVersionUID = 1L;

	TipoServicoFormView subView;

	@Autowired
	TipoServicoOsDAO tipoServicoDAO;

	private TipoServicoOsEntity currentBean;

	@Override
	protected String getNome() {
		return "Tipo de Serviço";
	}
	
	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDescricao(subView.getTfDescricao().getValue());
			tipoServicoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoServicoDAO.find(id);

		subView.getTfDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void quandoNovo() {
		
		try {
			this.currentBean = new TipoServicoOsEntity();

			this.subView.getTfDescricao().setValue(this.currentBean.getDescricao());

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

	}

	@Override
	protected void initSubView() {
		subView = new TipoServicoFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoServicoOsEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoServicoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTfDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTfDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}
	
	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public TipoServicoOsEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
