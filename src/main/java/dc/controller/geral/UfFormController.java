package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.UfEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.UFFormView;

@Controller
@Scope("prototype")
public class UfFormController extends CRUDFormController<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UFFormView subView;

	@Autowired
	private UfDAO ufDAO;

	private UfEntity currentBean;

	@Override
	protected String getNome() {
		return "UF";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setNome(subView.getTxtNome().getValue());
			currentBean.setSigla(subView.getTxtSigla().getValue());

			ufDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = ufDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtSigla().setValue(currentBean.getSigla());
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new UFFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new UfEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		ufDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	protected boolean validaSalvar() {
		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {
		boolean valido = true;

		if (subView.getTxtNome().getValue() == null
				|| subView.getTxtNome().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em Branco!");

			return false;
		}

		if (subView.getTxtSigla().getValue() == null
				|| subView.getTxtSigla().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtSigla(),
					"Não pode ficar em Branco!");

			return false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public UfEntity getModelBean() {
		return currentBean;
	}

}