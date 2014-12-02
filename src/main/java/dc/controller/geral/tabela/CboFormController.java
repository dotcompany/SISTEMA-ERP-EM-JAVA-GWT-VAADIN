package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.geral.tabela.CboDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CBOFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CboFormController extends CRUDFormController<CboEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CBOFormView subView;

	@Autowired
	CboDAO cboDAO;

	private CboEntity currentBean;

	@Override
	protected String getNome() {
		return "CBO";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());

		try {
			cboDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cboDAO.find(id);
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
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
		subView = new CBOFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new CboEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cboDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtNome().getValue() == null
				|| subView.getTxtNome().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em Branco!");

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "cboForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	@Override
	public CboEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}