package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.TipoColaborador;
import dc.servicos.dao.pessoal.TipoColaboradorDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.TipoColaboradorFormView;

@Controller
@Scope("prototype")
public class TipoColaboradorFormController extends CRUDFormController<TipoColaborador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoColaboradorFormView subView;

	@Autowired
	private TipoColaboradorDAO tipoColaboradorDAO;

	private TipoColaborador currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TipoColaborador();
	}

	@Override
	protected void initSubView() {
		subView = new TipoColaboradorFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoColaboradorDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			tipoColaboradorDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Tipo Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoColaboradorDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "tipoColaboradorForm";
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
	public TipoColaborador getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}