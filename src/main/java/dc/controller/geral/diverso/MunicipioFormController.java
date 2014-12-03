package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.diverso.MunicipioEntity;
import dc.servicos.dao.geral.diverso.MunicipioDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.MunicipioFormView;

@Controller
@Scope("prototype")
public class MunicipioFormController extends CRUDFormController<MunicipioEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MunicipioFormView subView;

	@Autowired
	private MunicipioDAO municipioDAO;

	private MunicipioEntity currentBean;

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
	protected void criarNovoBean() {
		currentBean = new MunicipioEntity();
	}

	@Override
	protected void initSubView() {
		subView = new MunicipioFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		
		currentBean = municipioDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());

		try {
			municipioDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Município";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		
		municipioDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "municipioForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public MunicipioEntity getModelBean() {
		return currentBean;
	}

}