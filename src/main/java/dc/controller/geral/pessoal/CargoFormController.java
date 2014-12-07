package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.controller.geral.tabela.CboListController;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.geral.pessoal.CargoDAO;
import dc.servicos.dao.geral.tabela.CboDAO;
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

	private CargoEntity currentBean;

	@Autowired
	private CargoDAO cargoDAO;

	@Autowired
	private CboDAO cboDAO;

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
		try {
			this.currentBean = new CargoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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

			this.subView.getTfSalario().setValue(
					String.valueOf(this.currentBean.getSalario()));

			String sCbo1994 = this.currentBean.getCbo1994();

			if (StringUtils.isNotBlank(sCbo1994)) {
				CboEntity cbo1994 = this.cboDAO.find(sCbo1994);

				this.subView.getMocCbo1994().setValue(cbo1994);
			}

			String sCbo2002 = this.currentBean.getCbo2002();

			if (StringUtils.isNotBlank(sCbo2002)) {
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

			String salario = this.subView.getTfSalario().getValue();

			if (NumberUtils.isNumber(salario)) {
				this.currentBean.setSalario(NumberUtils.createDouble(salario));
			}

			CboEntity cbo1994 = this.subView.getMocCbo1994().getValue();

			if (ObjectUtils.isNotBlank(cbo1994)) {
				this.currentBean.setCbo1994(cbo1994.getCodigo());
			} else {
				this.currentBean.setCbo1994(null);
			}

			CboEntity cbo2002 = this.subView.getMocCbo2002().getValue();

			if (ObjectUtils.isNotBlank(cbo2002)) {
				this.currentBean.setCbo2002(cbo2002.getCodigo());
			} else {
				this.currentBean.setCbo2002(null);
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
		try {
			this.cargoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
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