package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.Validator;
import dc.entidade.financeiro.TipoPagamento;
import dc.servicos.dao.financeiro.TipoPagamentoDAO;
import dc.visao.financeiro.TipoPagamentoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class TipoPagamentoFormController extends CRUDFormController<TipoPagamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TipoPagamentoFormView subView;

	@Autowired
	private TipoPagamentoDAO tipoPagamentoDAO;

	private TipoPagamento currentBean;

	@Override
	protected String getNome() {
		return "Tipo Pagamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			tipoPagamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoPagamentoDAO.find(id);
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new TipoPagamentoFormView();

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new TipoPagamento();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoPagamentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		String tipo = subView.getTxtTipo().getValue();
		if (!Validator.validateString(subView.getTxtTipo().getValue())) {
			adicionarErroDeValidacao(subView.getTxtTipo(), "Não pode ficar em branco");
			valido = false;
		} else if (tipo.equals("01") || tipo.equals("02") || tipo.equals("03")) {
			valido = false;
			adicionarErroDeValidacao(subView.getTxtTipo(), "O código informado para o tipo não pode ser cadastrado.");
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "tipoPagamentoForm";
	}
}
