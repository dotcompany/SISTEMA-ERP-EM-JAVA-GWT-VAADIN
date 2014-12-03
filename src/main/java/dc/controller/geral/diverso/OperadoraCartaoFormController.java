package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.geral.diverso.OperadoraCartaoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.OperadoraCartaoFormView;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class OperadoraCartaoFormController extends CRUDFormController<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperadoraCartaoFormView subView;

	@Autowired
	private OperadoraCartaoDAO operadoraDAO;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	private OperadoraCartaoEntity currentBean;

	@Override
	protected String getNome() {
		return "Operadora Cartão";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {

		subView.preencheOperadoraCartao(currentBean);

		try {
			operadoraDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = operadoraDAO.find(id);
		subView.preencheOperadoraCartaoForm(currentBean);

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
		subView = new OperadoraCartaoFormView();

		DefaultManyToOneComboModel<ContaCaixa> model = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class, this.contaCaixaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "nome";

			}
		};

		this.subView.getCmbContaCaixa().setModel(model);

		DefaultManyToOneComboModel<ContabilContaEntity> model1 = new DefaultManyToOneComboModel<ContabilContaEntity>(ContabilContaListController.class,
				this.contabilContaDAO, super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "descricao";

			}
		};

		this.subView.getCmbContabilConta().setModel(model1);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new OperadoraCartaoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		operadoraDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {

		boolean valido = true;

		ContabilContaEntity contabilConta = (ContabilContaEntity) subView.getCmbContabilConta().getValue();
		if (!Validator.validateObject(contabilConta)) {
			adicionarErroDeValidacao(subView.getCmbContabilConta(), "Não pode ficar em branco");
			valido = false;
		}

		ContaCaixa contaCaixa = (ContaCaixa) subView.getCmbContaCaixa().getValue();
		if (!Validator.validateObject(contaCaixa)) {
			adicionarErroDeValidacao(subView.getCmbContaCaixa(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtBandeira().getValue())) {
			adicionarErroDeValidacao(subView.getTxtBandeira(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtVencimentoAluguel().getValue())) {
			adicionarErroDeValidacao(subView.getTxtVencimentoAluguel(), "Número inválido");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "operadoraCartaoForm";
	}

	@Override
	public OperadoraCartaoEntity getModelBean() {
		return currentBean;
	}

}