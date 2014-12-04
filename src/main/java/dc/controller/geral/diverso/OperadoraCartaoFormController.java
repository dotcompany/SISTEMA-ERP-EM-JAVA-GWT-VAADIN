package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.OperadoraCartaoValidator;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.geral.diverso.OperadoraCartaoDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.OperadoraCartaoFormView;

@Controller
@Scope("prototype")
public class OperadoraCartaoFormController extends
		CRUDFormController<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperadoraCartaoFormView subView;

	private OperadoraCartaoEntity currentBean;

	@Autowired
	private OperadoraCartaoDAO operadoraDAO;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Override
	protected String getNome() {
		return "Operadora de cartão";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			OperadoraCartaoValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setContaCaixa((ContaCaixa) this.subView
					.getMocContaCaixa().getValue());
			this.currentBean
					.setContabilConta((ContabilContaEntity) this.subView
							.getMocContabilConta().getValue());
			this.currentBean.setBandeira(this.subView.getTfBandeira()
					.getValue());
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			// operadoraCartao.setVencimentoAluguel(txtVencimentoAluguel.getConvertedValue()
			// != null ? (Integer) txtVencimentoAluguel.getConvertedValue() :
			// 0);
			// operadoraCartao.setTaxaAdm((BigDecimal)
			// txtTaxaAdm.getConvertedValue());
			// operadoraCartao.setTaxaAdmDebito((BigDecimal)
			// txtTaxaAdmDebito.getConvertedValue());
			// operadoraCartao.setValorAluguelPosPin((BigDecimal)
			// txtValorAluguelPosPin.getConvertedValue());
			this.currentBean.setFone1(this.subView.getTfTelefone1().getValue());
			this.currentBean.setFone2(this.subView.getTfTelefone2().getValue());

			this.operadoraDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.operadoraDAO.find(id);

			this.subView.getMocContaCaixa().setValue(
					this.currentBean.getContaCaixa());
			this.subView.getMocContabilConta().setValue(
					this.currentBean.getContabilConta());

			this.subView.getTfBandeira().setValue(
					this.currentBean.getBandeira());
			this.subView.getTfNome().setConvertedValue(
					this.currentBean.getNome());
			this.subView.getTfVencimentoAluguel().setConvertedValue(
					this.currentBean.getVencimentoAluguel());
			this.subView.getTfTaxaAdm().setConvertedValue(
					this.currentBean.getTaxaAdm());
			this.subView.getTfTaxaAdmDebito().setConvertedValue(
					this.currentBean.getTaxaAdmDebito());
			this.subView.getTfValorAluguelPosPin().setConvertedValue(
					this.currentBean.getValorAluguelPosPin());
			this.subView.getTfTelefone1().setValue(this.currentBean.getFone1());
			this.subView.getTfTelefone2().setValue(this.currentBean.getFone2());
		} catch (Exception e) {
			e.printStackTrace();
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
		try {
			this.subView = new OperadoraCartaoFormView(this);

			DefaultManyToOneComboModel<ContaCaixa> model = new DefaultManyToOneComboModel<ContaCaixa>(
					ContaCaixaListController.class, this.contaCaixaDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nome";
				}

			};

			this.subView.getMocContaCaixa().setModel(model);

			DefaultManyToOneComboModel<ContabilContaEntity> model1 = new DefaultManyToOneComboModel<ContabilContaEntity>(
					ContabilContaListController.class, this.contabilContaDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getMocContabilConta().setModel(model1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new OperadoraCartaoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.operadoraDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public OperadoraCartaoEntity getModelBean() {
		return currentBean;
	}

}