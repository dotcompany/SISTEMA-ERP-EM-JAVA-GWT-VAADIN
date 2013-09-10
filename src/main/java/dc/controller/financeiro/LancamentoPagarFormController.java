package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.LancamentoPagar;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.LancamentoPagarDAO;
import dc.visao.financeiro.LancamentoPagarFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class LancamentoPagarFormController extends CRUDFormController<LancamentoPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoPagarFormView subView;

	@Autowired
	private LancamentoPagarDAO lancamentoPagarDAO;

	private LancamentoPagar currentBean;

	@Autowired
	private ContabilContaDAO contabilcontaDAO;

	@Override
	protected String getNome() {
		return "Lançamento à Pagar";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);

		try {
			lancamentoPagarDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = lancamentoPagarDAO.find(id);

		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar programa��o customizada
	 * para essa a��o aqui. Ou ent�o deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new LancamentoPagarFormView();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new LancamentoPagar();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		lancamentoPagarDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {

		return "lancamentoPagarForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}
}
