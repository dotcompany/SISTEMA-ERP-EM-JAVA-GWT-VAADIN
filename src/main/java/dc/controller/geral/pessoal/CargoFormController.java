package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.geral.tabela.CboListController;
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

	private CargoEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		String nome = this.subView.getTfNome().getValue();

		if (!Validator.validateString(nome)) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em Branco!");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		this.currentBean = new CargoEntity();
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new CargoFormView();

			DefaultManyToOneComboModel<CboEntity> modelCbo = new DefaultManyToOneComboModel<CboEntity>(
					CboListController.class, this.cboDAO,
					super.getMainController());

			this.subView.getMocCbo1994().setModel(modelCbo);

			DefaultManyToOneComboModel<CboEntity> modelCbo2 = new DefaultManyToOneComboModel<CboEntity>(
					CboListController.class, this.cboDAO,
					super.getMainController());

			this.subView.getMocCbo2002().setModel(modelCbo2);
			// subView.InitCbs(cboDAO.listaTodos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.cargoDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfDescricao().setValue(
					this.currentBean.getDescricao());

			DefaultManyToOneComboModel<CboEntity> modelCbo = new DefaultManyToOneComboModel<CboEntity>(
					CboListController.class, this.cboDAO,
					super.getMainController());

			this.subView.getMocCbo1994().setModel(modelCbo);

			DefaultManyToOneComboModel<CboEntity> modelCbo2 = new DefaultManyToOneComboModel<CboEntity>(
					CboListController.class, this.cboDAO,
					super.getMainController());

			this.subView.getMocCbo2002().setModel(modelCbo2);

			this.subView.getTfSalario().setValue(
					String.valueOf(this.currentBean.getSalario()));

			String sCbo1994 = this.currentBean.getCbo1994();

			if (sCbo1994 != null && !sCbo1994.equals("")) {
				CboEntity cbo1994 = this.cboDAO.find(sCbo1994);

				this.subView.getMocCbo1994().setValue(cbo1994);
			}

			String sCbo2002 = this.currentBean.getCbo2002();

			if (sCbo2002 != null && !sCbo2002.equals("")) {
				CboEntity cbo2002 = this.cboDAO.find(sCbo2002);

				this.subView.getMocCbo2002().setValue(cbo2002);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setDescricao(this.subView.getTfDescricao()
					.getValue());

			Double salario = Double.parseDouble(this.subView.getTfSalario()
					.getValue().isEmpty() ? "0.0" : this.subView.getTfSalario()
					.getValue());

			this.currentBean.setSalario(salario);

			CboEntity cbo1994 = this.subView.getMocCbo1994().getValue();

			if (cbo1994 != null && !cbo1994.equals("")) {
				this.currentBean.setCbo1994(cbo1994.getCodigo());
			}

			CboEntity cbo2002 = this.subView.getMocCbo2002().getValue();

			if (cbo2002 != null && !cbo2002.equals("")) {
				this.currentBean.setCbo2002(cbo2002.getCodigo());
			}

			this.cargoDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
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