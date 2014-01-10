package dc.controller.contratos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contratos.Template;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Usuario;
import dc.servicos.dao.contratos.TemplateDAO;
import dc.servicos.util.Validator;
import dc.visao.contratos.TemplateFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class TemplateFormController extends CRUDFormController<Template> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TemplateFormView subView;

	@Autowired
	private TemplateDAO templateDAO;

	private Template currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxaDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Template();
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Empresa empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);

	}

	@Override
	protected void initSubView() {
		subView = new TemplateFormView();

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = templateDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxaDescricao().setValue(currentBean.getDescricao());

	}

	@Override
	protected void actionSalvar() {
		String nome = subView.getTxtNome().getValue();
		String descricao = subView.getTxaDescricao().getValue();
		currentBean.setNome(nome);
		currentBean.setDescricao(descricao);
		currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
		try {
			templateDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Template";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		templateDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "templateFormController";
	}

}
