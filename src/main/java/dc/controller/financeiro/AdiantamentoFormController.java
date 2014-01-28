package dc.controller.financeiro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.Adiantamento;
import dc.entidade.financeiro.LancamentoPagar;
import dc.servicos.dao.financeiro.AdiantamentoDAO;
import dc.servicos.dao.financeiro.LancamentoPagarDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.AdiantamentoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela.
 * 
 */

@Controller
@Scope("prototype")
public class AdiantamentoFormController extends	CRUDFormController<Adiantamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AdiantamentoFormView subView;

	@Autowired
	private AdiantamentoDAO adiantamentoDAO;

	@Autowired
	private LancamentoPagarDAO lancamentoPagarDAO;

	private Adiantamento currentBean;

	@Override
	protected String getNome() {
		return "Adiantamento";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		
		subView.preencheAdiantamento(currentBean);

		try {
			adiantamentoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		currentBean = adiantamentoDAO.find(id);
		subView.preencheAdiantamentoForm(currentBean);

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
		this.subView = new AdiantamentoFormView();

		DefaultManyToOneComboModel<LancamentoPagar> modelBanco = new DefaultManyToOneComboModel<LancamentoPagar>(
				LancamentoPagarListController.class, this.lancamentoPagarDAO,super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "valorTotal";
			}
		};
		
		this.subView.getCmbLancamentoPagar().setModel(modelBanco);


	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new Adiantamento();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		adiantamentoDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}
	
	private boolean validaCampos() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtObservacoes().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObservacoes(), "Não pode ficar em branco");
			valido = false;
		}

		LancamentoPagar lancamentoPagar = (LancamentoPagar) subView.getCmbLancamentoPagar().getValue();
		if (!Validator.validateObject(lancamentoPagar)) {
			adicionarErroDeValidacao(subView.getCmbLancamentoPagar(), "Não pode ficar em branco");
			valido = false;
		}

		Date dataAdiantamento= (Date) subView.getDtAdiantamento().getValue();
		if (!Validator.validateObject(dataAdiantamento)) {
			adicionarErroDeValidacao(subView.getDtAdiantamento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtValor().getConvertedValue().toString())) {
			adicionarErroDeValidacao(subView.getTxtValor(), "Número inválido");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "adiantamentoBancoForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

}