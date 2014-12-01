package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.contabilidade.ContabilContaListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.financeiro.Sindicato;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.SindicatoFormView;
import dc.visao.financeiro.SindicatoFormView.TIPO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/** @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela. */

@Controller
@Scope("prototype")
public class SindicatoFormController extends CRUDFormController<Sindicato> {

	private SindicatoFormView subView;

	@Autowired
	private SindicatoDAO sindicatoDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	private Sindicato currentBean;

	@Override
	protected String getNome() {
		return "Sindicato";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {

			ContabilContaEntity contabilConta = subView.getCmbContabilConta().getValue();

			if (!Validator.validateObject(contabilConta)) {
				throw new ErroValidacaoException("Informe a Contábil Conta");
			}

			currentBean.setContabilConta(contabilConta);

			currentBean.setNome(subView.getTxtNome().getValue());

			currentBean.setLogradouro(subView.getTxtLogradouro().getValue());

			currentBean.setEmail(subView.getTxtEmail().getValue());

			currentBean.setDataBase(subView.getDtDataBase().getValue());

			TIPO enumTipo = (TIPO) (subView.getCmbTipo().getValue());
			if (Validator.validateObject(enumTipo)) {
				String tipo = (enumTipo).getCodigo();
				currentBean.setTipoSindicato(tipo);
			}

			sindicatoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = sindicatoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());

		String tipo = currentBean.getTipoSindicato();
		if (Validator.validateString(tipo)) {
			subView.getCmbTipo().setValue(TIPO.getValor(tipo));
		}
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
		subView = new SindicatoFormView();

		DefaultManyToOneComboModel<ContabilContaEntity> model = new DefaultManyToOneComboModel<ContabilContaEntity>(ContabilContaListController.class,
				this.contabilContaDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCmbContabilConta().setModel(model);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Sindicato();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		sindicatoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */

	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {

		boolean valido = true;

		if (!Validator.validateObject(subView.getCmbContabilConta().getValue())) {
			adicionarErroDeValidacao(subView.getCmbContabilConta(), "Não pode ficar em branco");
			valido = false;
		}

		if (subView.getTxtNome().getValue() == null || subView.getTxtNome().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em Branco!");
			valido = false;
		}

		if (subView.getTxtLogradouro().getValue() == null || subView.getTxtLogradouro().getValue().isEmpty()) {
			adicionarErroDeValidacao(subView.getTxtLogradouro(), "Não pode ficar em Branco!");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "sindicatoForm";
	}

	@Override
	public Sindicato getModelBean() {
		return currentBean;
	}

}
