package dc.controller.financeiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.TalonarioCheque;
import dc.entidade.financeiro.type.StatusChequeType;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.TalonarioChequeDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.TalonarioChequeFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
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
			adicionarErroDeValidacao(subView.getTxtTalao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNumero(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getCmbContaCaixa().getValue())) {
			adicionarErroDeValidacao(subView.getCmbContaCaixa(), "Não pode ficar em branco");
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

		this.subView.InitCbs(getTalonarioChequeTipo());

		DefaultManyToOneComboModel<ContaCaixa> model = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class, this.contaCaixaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "nome";

			}
		};

		this.subView.getCmbContaCaixa().setModel(model);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = talonarioChequeDAO.find(id);

		subView.getTxtTalao().setValue(currentBean.getTalao());

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

	/** COMBO */
	public List<String> getTalonarioChequeTipo() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (StatusChequeType en : StatusChequeType.values()) {
				siLista.add(en.ordinal(), en.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public TalonarioCheque getModelBean() {
		return currentBean;
	}

}
