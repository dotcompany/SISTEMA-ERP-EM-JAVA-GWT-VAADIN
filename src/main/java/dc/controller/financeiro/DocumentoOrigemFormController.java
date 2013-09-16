package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.DocumentoOrigem;
import dc.servicos.dao.financeiro.DocumentoOrigemDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.DocumentoOrigemFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class DocumentoOrigemFormController extends CRUDFormController<DocumentoOrigem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentoOrigemFormView subView;

	@Autowired
	private DocumentoOrigemDAO documentoorigemDAO;

	private DocumentoOrigem currentBean;

	@Override
	protected String getNome() {
		return "Documento Origem";
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
			documentoorigemDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = documentoorigemDAO.find(id);
		subView.preencheForm(currentBean);
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
		subView = new DocumentoOrigemFormView();

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new DocumentoOrigem();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		documentoorigemDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
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
		}
		
		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "documentoorigemForm";
	}
}
