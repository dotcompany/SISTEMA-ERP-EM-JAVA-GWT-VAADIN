package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.geral.UFListController;
import dc.entidade.geral.Pessoa;
import dc.entidade.geral.UF;
import dc.entidade.pessoal.Contador;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.pessoal.ContadorDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.ContadorFormView;

@Controller
@Scope("prototype")
public class ContadorFormController extends CRUDFormController<Contador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContadorFormView subView;

	@Autowired
	private ContadorDAO contadorDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private UFDAO ufDAO;

	private Contador currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtLogradouro().getValue())) {
			adicionarErroDeValidacao(subView.getTxtLogradouro(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtComplemento().getValue())) {
			adicionarErroDeValidacao(subView.getTxtComplemento(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Contador();
	}

	@Override
	protected void initSubView() {
		subView = new ContadorFormView();

		subView.InitCbs(pessoaDAO.listaTodos(), ufDAO.listaTodos());

		DefaultManyToOneComboModel<Pessoa> model = new DefaultManyToOneComboModel<Pessoa>(
				PessoaListController.class, this.pessoaDAO,
				super.getMainController());

		subView.getCmbPessoa().setModel(model);

		DefaultManyToOneComboModel<UF> model1 = new DefaultManyToOneComboModel<UF>(
				UFListController.class, this.ufDAO, super.getMainController());

		subView.getCmbUf().setModel(model1);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contadorDAO.find(id);

		subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
		subView.getTxtComplemento().setValue(currentBean.getComplemento());
		subView.getTxtCep().setValue(currentBean.getCep());
		subView.getTxtBairro().setValue(currentBean.getBairro());
		subView.getTxtEmail().setValue(currentBean.getEmail());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setLogradouro(subView.getTxtLogradouro().getValue());
		currentBean.setComplemento(subView.getTxtComplemento().getValue());
		currentBean.setCep(subView.getTxtCep().getValue());
		currentBean.setFone(subView.getTxtTelefone().getValue());
		currentBean.setFax(subView.getTxtFax().getValue());
		currentBean.setIdPessoa((Pessoa) subView.getCmbPessoa().getValue());
		currentBean.setUf((UF) subView.getCmbUf().getValue());

		try {
			contadorDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Contador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contadorDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "contadorForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

}