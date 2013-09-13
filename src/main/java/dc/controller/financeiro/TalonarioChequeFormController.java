package dc.controller.financeiro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.TalonarioCheque;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.TalonarioChequeDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.TalonarioChequeFormView;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;



@Controller
@Scope("prototype")
public class TalonarioChequeFormController extends CRUDFormController<TalonarioCheque> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TalonarioChequeFormView subView;

	@Autowired
	private TalonarioChequeDAO talonarioChequeDAO;
	
	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	private TalonarioCheque currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtTalao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtTalao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNumero(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbContaCaixa().getValue())) {
			adicionarErroDeValidacao(subView.getCmbContaCaixa(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new TalonarioCheque();
	}

	@Override
	protected void initSubView() {
		subView = new TalonarioChequeFormView();
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = talonarioChequeDAO.find(id);
		
		subView.getTxtTalao().setValue(currentBean.getTalao());
		subView.setCbStatus(currentBean.getStatusTalao().toString());
		
		/* Configura combo Conta CAIXA */
		ManyToOneComboModel<ContaCaixa> model = new ManyToOneComboModel<ContaCaixa>() {

			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}

			@Override
			public List<ContaCaixa> getResultado(String q) {
				return contaCaixaDAO.query(q);
			}

			@Override
			public Class<ContaCaixa> getEntityClass() {
				return ContaCaixa.class;
			}

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(ContaCaixa value) {
				Notification.show("Selecionado Editar: " + value.getNome());

			}
		};
		subView.getCmbContaCaixa().setModel(model);
		subView.getCmbContaCaixa().setValue(currentBean.getContaCaixa());
	}

	@Override
	protected void actionSalvar() {
		
		currentBean.setTalao(subView.getTxtTalao().getValue());
		
		try {
			talonarioChequeDAO.saveOrUpdate(currentBean);
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
		return "Talonário Cheque";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		talonarioChequeDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}
	
	@Override
	public String getViewIdentifier() {
		return "talonarioChequeFormController";
	}


}
