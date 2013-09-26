package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.geral.Pessoa;
import dc.entidade.pessoal.Transportadora;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.TransportadoraDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.TransportadoraFormView;

@Controller
@Scope("prototype")
public class TransportadoraFormController extends
		CRUDFormController<Transportadora> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TransportadoraFormView subView;

	@Autowired
	private TransportadoraDAO transportadoraDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	private Transportadora currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtObservacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObservacao(),
					"Não pode ficar em Branco!");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Transportadora();
	}

	@Override
	protected void initSubView() {
		subView = new TransportadoraFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = transportadoraDAO.find(id);

		subView.getTxtObservacao().setValue(currentBean.getObservacao());

		DefaultManyToOneComboModel<Pessoa> model = new DefaultManyToOneComboModel<Pessoa>(
				PessoaListController.class, this.pessoaDAO,
				super.getMainController());

		subView.getCmbPessoa().setModel(model);
		subView.getCmbPessoa().setValue(currentBean.getPessoa());

		DefaultManyToOneComboModel<ContabilConta> model1 = new DefaultManyToOneComboModel<ContabilConta>(
				ContabilContaListController.class, this.contabilContaDAO,
				super.getMainController());

		subView.getCmbContContabil().setModel(model1);
		subView.getCmbContContabil().setValue(currentBean.getContaContabil());
	}

	@Override
	protected void actionSalvar() {
		currentBean.setPessoa((Pessoa) subView.getCmbPessoa().getValue());
		currentBean.setContaContabil((ContabilConta) subView
				.getCmbContContabil().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());

		try {
			transportadoraDAO.saveOrUpdate(currentBean);

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
		return "Transportadora";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		transportadoraDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "transportadoraForm";
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