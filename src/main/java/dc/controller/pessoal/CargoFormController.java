package dc.controller.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.tabelas.CBOListController;
import dc.entidade.pessoal.Cargo;
import dc.entidade.tabelas.CBO;
import dc.servicos.dao.pessoal.CargoDAO;
import dc.servicos.dao.tabelas.CBODAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.CargoFormView;

@Controller
@Scope("prototype")
public class CargoFormController extends CRUDFormController<Cargo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CargoFormView subView;

	@Autowired
	private CargoDAO cargoDAO;

	@Autowired
	private CBODAO cboDAO;

	// @Autowired
	// private MainController mainController;

	private Cargo currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em Branco!");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Cargo();
	}

	@Override
	protected void initSubView() {
		subView = new CargoFormView();
		DefaultManyToOneComboModel<CBO> modelCBO = new DefaultManyToOneComboModel<CBO>(
				CBOListController.class, cboDAO, super.getMainController());
		DefaultManyToOneComboModel<CBO> modelCBO2 = new DefaultManyToOneComboModel<CBO>(
				CBOListController.class, cboDAO, super.getMainController());
		subView.getCmbCBO1994().setModel(modelCBO);
		subView.getCmbCBO2002().setModel(modelCBO2);
		// subView.InitCbs(cboDAO.listaTodos());
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cargoDAO.find(id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricaoo().setValue(currentBean.getDescricao());

		this.subView.getTxtSalario().setValue(
				String.valueOf(this.currentBean.getSalario()));
	}

	@Override
	protected void actionSalvar() {
		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricaoo().getValue());

		Double salario = Double.parseDouble(this.subView.getTxtSalario()
				.getValue().isEmpty() ? "0.0" : this.subView.getTxtSalario()
				.getValue());

		this.currentBean.setSalario(salario);

		try {
			cargoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			this.currentBean = new Cargo();

			this.subView.getTxtSalario().setValue("");
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Cargo";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cargoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "cargoForm";
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