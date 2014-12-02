package dc.controller.geral.tabela;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.geral.tabela.Csosna;
import dc.servicos.dao.geral.tabela.CsosnaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.tabela.CsosnaFormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class CsosnaFormController extends CRUDFormController<Csosna> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CsosnaFormView subView;

	@Autowired
	CsosnaDAO csosnaDAO;

	private Csosna currentBean;

	@Override
	protected String getNome() {
		return "CSOSN A";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setCodigo(subView.getTxtCodigo().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());

		try {
			csosnaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = csosnaDAO.find(id);
		subView.getTxtCodigo().setValue(currentBean.getCodigo());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
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
		subView = new CsosnaFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Csosna();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		csosnaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtDescricao().getValue() == null
				|| subView.getTxtDescricao().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em Branco!");

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public String getViewIdentifier() {
		return "csosnaForm";
	}

	@Override
	public Csosna getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}