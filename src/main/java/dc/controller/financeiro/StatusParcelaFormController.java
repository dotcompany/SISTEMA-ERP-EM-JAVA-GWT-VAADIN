package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.Validator;
import dc.entidade.financeiro.StatusParcela;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.visao.financeiro.StatusParcelaFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class StatusParcelaFormController extends CRUDFormController<StatusParcela> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StatusParcelaFormView subView;

	@Autowired
	private StatusParcelaDAO statusParcelaDAO;

	private StatusParcela currentBean;

	@Override
	protected String getNome() {
		return "Status Parcela";
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
			statusParcelaDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = statusParcelaDAO.find(id);
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
		subView = new StatusParcelaFormView();

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new StatusParcela();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		statusParcelaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtProcedimento().getValue())) {
			adicionarErroDeValidacao(subView.getTxtProcedimento(), "Não pode ficar em branco");
			valido = false;
		}
		String situacao = subView.getTxtSituacao().getValue();
		if (!Validator.validateString(subView.getTxtSituacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSituacao(), "Não pode ficar em branco");
			valido = false;
		} else if (situacao.equals("01") || situacao.equals("02") || situacao.equals("03") || situacao.equals("04")) {
			valido = false;
			adicionarErroDeValidacao(subView.getTxtSituacao(), "O código informado para a situação não pode ser cadastrado.");
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "statusParcelaForm";
	}
}
