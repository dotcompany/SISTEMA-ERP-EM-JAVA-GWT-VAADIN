package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.Cfop;
import dc.servicos.dao.tabelas.CfopDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.CfopFormView;

/**
 * 
 * @author Wesley Jr /*
 * 
 */

@Controller
@Scope("prototype")
public class CfopFormController extends CRUDFormController<Cfop> {

	CfopFormView subView;

	@Autowired
	CfopDAO cfopDAO;

	private Cfop currentBean;

	@Override
	protected String getNome() {
		return "Cfop";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setAplicacao(subView.getTxtAplicacao().getValue());
		try {
			cfopDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cfopDAO.find(id);
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtAplicacao().setValue(currentBean.getAplicacao());
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new CfopFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Cfop();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cfopDAO.deleteAllByIds(ids);
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
					"não pode ficar em branco");

			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "cfopForm";
	}

}
