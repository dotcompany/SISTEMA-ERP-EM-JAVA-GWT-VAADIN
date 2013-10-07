package dc.controller.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.ContaCaixaTipoEnum;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.financeiro.AgenciaBanco;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.framework.Empresa;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.ContaCaixaFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;


/**
 * 
 *  DotCompany
 * @author Wesley Jr
 *
 */
@Controller
@Scope("prototype")
public class ContaCaixaFormController extends CRUDFormController<ContaCaixa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContaCaixaFormView subView;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Autowired
	private ContabilContaDAO contabilDAO;

	@Autowired
	private AgenciaBancoDAO agenciaDAO;

	private ContaCaixa currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCodigo(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDigito().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDigito(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new ContaCaixa();

			this.subView.getTxtNome().setValue(currentBean.getNome());
			this.subView.getTxtDescricao().setValue(currentBean.getDescricao());
			this.subView.getTxtCodigo().setValue(currentBean.getCodigo());
			this.subView.getTxtDigito().setValue(currentBean.getDigito());

			this.subView.getCmbContabilConta().setValue(
					currentBean.getContabilConta());
			this.subView.getCmbAgenciaBanco().setValue(
					currentBean.getAgenciaBanco());

			this.subView.getCmbTipo().setValue(
					ContaCaixaTipoEnum.valueOf(this.currentBean.getTipo())
							.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ContaCaixaFormView();

		this.subView.InitCbs(getContaCaixaTipo());

		/*
		 * DefaultManyToOneComboModel<AgenciaBanco> model = new
		 * DefaultManyToOneComboModel<AgenciaBanco>(
		 * AgenciaBancoListController.class, this.agenciaDAO,
		 * super.getMainController());
		 * 
		 * this.subView.getCmbAgenciaBanco().setModel(model);
		 * 
		 * DefaultManyToOneComboModel<ContabilConta> model1 = new
		 * DefaultManyToOneComboModel<ContabilConta>(
		 * ContabilContaListController.class, this.contabilDAO,
		 * super.getMainController());
		 * 
		 * this.subView.getCmbContabilConta().setModel(model1);
		 */
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = contaCaixaDAO.find(id);

			this.subView.getTxtNome().setValue(this.currentBean.getNome());
			this.subView.getTxtDescricao().setValue(
					this.currentBean.getDescricao());
			this.subView.getTxtCodigo().setValue(this.currentBean.getCodigo());
			this.subView.getTxtDigito().setValue(this.currentBean.getDigito());

			this.subView.getCmbContabilConta().setValue(
					this.currentBean.getContabilConta());
			this.subView.getCmbAgenciaBanco().setValue(
					this.currentBean.getAgenciaBanco());

			this.subView.getCmbTipo().setValue(
					ContaCaixaTipoEnum.valueOf(this.currentBean.getTipo())
							.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTxtNome().getValue());
			this.currentBean.setDescricao(this.subView.getTxtDescricao()
					.getValue());
			this.currentBean.setCodigo(this.subView.getTxtCodigo().getValue());
			this.currentBean.setDigito(this.subView.getTxtDigito().getValue());

			this.currentBean.setAgenciaBanco((AgenciaBanco) this.subView
					.getCmbAgenciaBanco().getValue());
			this.currentBean.setContabilConta((ContabilConta) this.subView
					.getCmbContabilConta().getValue());

			for (ContaCaixaTipoEnum en : ContaCaixaTipoEnum.values()) {
				if (en.toString().equals(
						this.subView.getCmbTipo().getValue().toString())) {
					this.currentBean.setTipo(en.name());

					break;
				}
			}

			/**
			 * Empresa vinda da conta do usuário logado
			 */

			Empresa empresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa();

			this.currentBean.setEmpresa(empresa);

			/**
			 * **************************************
			 */

			this.contaCaixaDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			quandoNovo();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.currentBean = new ContaCaixa();

			this.subView.getTxtNome().setValue(currentBean.getNome());
			this.subView.getTxtDescricao().setValue(currentBean.getDescricao());
			this.subView.getTxtCodigo().setValue(currentBean.getCodigo());
			this.subView.getTxtDigito().setValue(currentBean.getDigito());

			this.subView.getCmbContabilConta().setValue(
					currentBean.getContabilConta());
			this.subView.getCmbAgenciaBanco().setValue(
					currentBean.getAgenciaBanco());

			this.subView.getCmbTipo().setValue(
					ContaCaixaTipoEnum.valueOf(this.currentBean.getTipo())
							.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getNome() {
		return "Conta caixa";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contaCaixaDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "contaCaixaForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	/**
	 * COMBO
	 */
	public List<String> getContaCaixaTipo() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (ContaCaixaTipoEnum en : ContaCaixaTipoEnum.values()) {
				siLista.add(en.ordinal(), en.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}