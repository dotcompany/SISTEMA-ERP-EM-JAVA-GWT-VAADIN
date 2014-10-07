package dc.controller.tabelas;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.tabelas.EfdTabela436;
import dc.servicos.dao.tabelas.EfdTabela436DAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.tabelas.EfdTabela436FormView;

/** @author Wesley Jr /* */

@Controller
@Scope("prototype")
public class EfdTabela436FormController extends CRUDFormController<EfdTabela436> {

	EfdTabela436FormView subView;

	@Autowired
	EfdTabela436DAO efdTabela436DAO;

	private EfdTabela436 currentBean;

	@Override
	protected String getNome() {
		return "EFD Tabela 436";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		try {
			efdTabela436DAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = efdTabela436DAO.find(id);
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
		subView = new EfdTabela436FormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new EfdTabela436();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		efdTabela436DAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		if (subView.getTxtDescricao().getValue() == null || subView.getTxtDescricao().getValue().isEmpty()) {
			// Utilizar adicionarErroDeValidacao() para adicionar mensagem de
			// erro para o campo que esta sendo validado
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em Branco!");
			return false;
		}
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "efdTabela436Form";
	}

	@Override
	public EfdTabela436 getModelBean() {
		return currentBean;
	}

}