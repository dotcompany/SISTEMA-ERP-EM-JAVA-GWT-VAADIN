package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.PlanoNaturezaFinanceira;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.NaturezaFinanceiraDAO;
import dc.servicos.dao.financeiro.PlanoNaturezaFinanceiraDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.NaturezaFinanceiraFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;

@Controller
@Scope("prototype")
public class NaturezaFinanceiraFormController extends CRUDFormController<NaturezaFinanceira> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NaturezaFinanceiraFormView subView;

	@Autowired
	private NaturezaFinanceiraDAO naturezafinanceiraDAO;

	private NaturezaFinanceira currentBean;

	@Autowired
	private PlanoNaturezaFinanceiraDAO planonaturezafinanceiraDAO;

	@Autowired
	private ContabilContaDAO contabilcontaDAO;

	@Autowired
	private MainController mainController;

	@Override
	protected String getNome() {
		return "Natureza Financeira";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);

		try {
			naturezafinanceiraDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = naturezafinanceiraDAO.find(id);

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
		subView = new NaturezaFinanceiraFormView();

		DefaultManyToOneComboModel<PlanoNaturezaFinanceira> model1 = new DefaultManyToOneComboModel<PlanoNaturezaFinanceira>(
				PlanoNaturezaFinanceiraListController.class, planonaturezafinanceiraDAO, mainController);
		subView.getCbPlanoNaturezaFinanceira().setModel(model1);

		DefaultManyToOneComboModel<ContabilConta> model2 = new DefaultManyToOneComboModel<ContabilConta>(ContabilContaListController.class,
				contabilcontaDAO, mainController) {
			@Override
			public String getCaptionProperty() {
				return "codigoReduzido";
			}
		};
		subView.getCbContabilConta().setModel(model2);

		subView.InitCbs();

	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new NaturezaFinanceira();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		naturezafinanceiraDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtAplicacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtAplicacao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtClassficacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtClassficacao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbContabilConta())) {
			adicionarErroDeValidacao(subView.getCbContabilConta(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCbPlanoNaturezaFinanceira())) {
			adicionarErroDeValidacao(subView.getCbPlanoNaturezaFinanceira(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getCbTipoAsString())) {
			adicionarErroDeValidacao(subView.getCbTipo(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "naturezafinanceiraForm";
	}
}
