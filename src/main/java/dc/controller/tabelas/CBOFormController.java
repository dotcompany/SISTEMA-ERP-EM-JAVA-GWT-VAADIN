package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.CBO;
import dc.servicos.dao.tabelas.CBODAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.CBOFormView;

/**
 * 
 * @author Wesley Jr /*
 * 
 */

@Controller
@Scope("prototype")
public class CBOFormController extends CRUDFormController<CBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CBOFormView subView;

	@Autowired
	CBODAO cboDAO;

	private CBO currentBean;

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
		currentBean = new CBO();
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
	
}