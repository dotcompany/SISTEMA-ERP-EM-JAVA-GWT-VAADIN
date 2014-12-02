package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.tabela.CBOListController;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.geral.pessoal.CargoDAO;
import dc.servicos.dao.geral.tabela.CBODAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.CargoFormView;

@Controller
@Scope("prototype")
public class CargoFormController extends CRUDFormController<CargoEntity> {

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

	private CargoEntity currentBean;

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
		currentBean = new CargoEntity();
	}

	@Override
	protected void initSubView() {
		subView = new CargoFormView();
		DefaultManyToOneComboModel<CboEntity> modelCBO = new DefaultManyToOneComboModel<CboEntity>(
				CBOListController.class, cboDAO, super.getMainController());
		DefaultManyToOneComboModel<CboEntity> modelCBO2 = new DefaultManyToOneComboModel<CboEntity>(
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

		DefaultManyToOneComboModel<CboEntity> modelCBO = new DefaultManyToOneComboModel<CboEntity>(
				CBOListController.class, cboDAO, super.getMainController());
		DefaultManyToOneComboModel<CboEntity> modelCBO2 = new DefaultManyToOneComboModel<CboEntity>(
				CBOListController.class, cboDAO, super.getMainController());
		subView.getCmbCBO1994().setModel(modelCBO);
		subView.getCmbCBO2002().setModel(modelCBO2);

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
			this.currentBean = new CargoEntity();

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
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public CargoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}