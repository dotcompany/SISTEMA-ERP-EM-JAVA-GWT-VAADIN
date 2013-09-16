package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.financeiro.Cheque;
import dc.entidade.financeiro.TalonarioCheque;
import dc.servicos.dao.financeiro.ChequeDAO;
import dc.servicos.dao.financeiro.TalonarioChequeDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.ChequeFormView;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
*
* @author Wesley Jr
/*
 * Nessa classe ela pega a classe principal que é o CRUD, que tem todos os controllers
 * da Tela, onde quando extendemos herdamos os métodos que temos na tela principal.
 * Temos o botão Novo que é para Criar uma nova Tela, para adicionar informações
 * novas, e dentro temos o Button Salvar que é para salvar as informações no Banco de Dados
 * Temos o carregar também que é para pegar as informações que desejarmos quando
 * formos pesquisar na Tela.
 *
*/

@Controller
@Scope("prototype")
public class ChequeFormController extends CRUDFormController<Cheque> {

	ChequeFormView subView;
	
	@Autowired
	ChequeDAO chequeDAO;
	
	@Autowired
	private TalonarioChequeDAO talonarioChequeDAO;

	private Cheque currentBean;
	
	@Override
	protected String getNome() {
		return "Cheque";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	
	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNumero(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbTalonarioCheque().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTalonarioCheque(),
					"Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}
	
	@Override  
	protected void actionSalvar() {
		
        currentBean.setDataStatus(subView.getDtStatus().getValue());
		
		try {
			chequeDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = chequeDAO.find(id);
		subView.getDtStatus().setValue(currentBean.getDataStatus());	
        subView.setCbStatusCheque(currentBean.getStatusCheque().toString());
        
        /* Configura combo TALONÁRIO CHEQUE */
		ManyToOneComboModel<TalonarioCheque> model = new ManyToOneComboModel<TalonarioCheque>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<TalonarioCheque> getResultado(String q) {
				return talonarioChequeDAO.query(q);
			}

			@Override
			public Class<TalonarioCheque> getEntityClass() {
				return TalonarioCheque.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(TalonarioCheque value) {
				Notification.show("Selecionado Editar: " + value.getTalao());

			}
		};
		
		subView.getCmbTalonarioCheque().setModel(model);
		subView.getCmbTalonarioCheque().setValue(currentBean.getIdTalonarioCheque());
		
	}
	
	/* Callback para quando novo foi acionado. Colocar Programação customizada para essa ação aqui. Ou então deixar em branco, para comportamento padrão */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new ChequeFormView();
		
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new Cheque();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		 chequeDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "chequeForm";
	}

}
