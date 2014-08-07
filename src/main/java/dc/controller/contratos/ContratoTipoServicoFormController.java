package dc.controller.contratos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.contratos.ContratoTipoServico;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Usuario;
import dc.servicos.dao.contratos.ContratoTipoServicoDAO;
import dc.servicos.util.Validator;
import dc.visao.contratos.ContratoTipoServicoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class ContratoTipoServicoFormController extends CRUDFormController<ContratoTipoServico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ContratoTipoServicoFormView subView;

	@Autowired
	ContratoTipoServicoDAO tipoServicoDAO;

	private ContratoTipoServico currentBean;

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
		currentBean = new ContratoTipoServico();
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Empresa empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);

	}

	@Override
	protected void initSubView() {
		subView = new ContratoTipoServicoFormView();

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = tipoServicoDAO.find(id);
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
			tipoServicoDAO.saveOrUpdate(currentBean);
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
		return "Tipo Serviço";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		tipoServicoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {

		return "contratoTipoServicoForm";
	}

	@Override
	public ContratoTipoServico getModelBean() {
		return currentBean;
	}

}
