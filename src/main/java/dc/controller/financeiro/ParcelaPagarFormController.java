package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.ParcelaPagar;
import dc.servicos.dao.financeiro.ParcelaPagarDAO;
import dc.visao.financeiro.ParcelaPagarFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ParcelaPagarFormController extends CRUDFormController<ParcelaPagar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ParcelaPagarFormView subView;

	@Autowired
	private ParcelaPagarDAO parcelaPagarDAO;

	private ParcelaPagar currentBean;

	@Override
	protected String getNome() {
		return "Parcela Pagar";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			//currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
			parcelaPagarDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = parcelaPagarDAO.find(id);
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
		subView = new ParcelaPagarFormView();

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new ParcelaPagar();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		parcelaPagarDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		/*if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCodigo(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateString(subView.getTxtSiglaDocumento().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSiglaDocumento(), "Não pode ficar em branco");
			valido = false;
		}*/
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "parcelaPagamento";
	}
}
