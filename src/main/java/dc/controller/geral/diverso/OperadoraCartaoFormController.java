package dc.controller.geral.diverso;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.classes.OperadoraCartaoUtils;
import dc.control.validator.DotErpException;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.geral.diverso.OperadoraCartaoEntity;
import dc.model.business.geral.diverso.OperadoraCartaoBusiness;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.diverso.OperadoraCartaoFormView;

@Controller
@Scope("prototype")
public class OperadoraCartaoFormController extends CRUDFormController<OperadoraCartaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperadoraCartaoFormView subView;

	/**
	 * ENTITY
	 */

	private OperadoraCartaoEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private OperadoraCartaoBusiness<OperadoraCartaoEntity> business;

	/**
	 * DAO
	 */

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	/**
	 * CONSTRUTOR
	 */

	public OperadoraCartaoFormController() {
		// TODO Auto-generated constructor stub
	}

	public OperadoraCartaoBusiness<OperadoraCartaoEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Operadora de cartão";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public OperadoraCartaoEntity getModelBean() {
		return entity;
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			OperadoraCartaoUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			ContaCaixa contaCaixa = this.subView.getMocContaCaixa().getValue();

			if (ObjectUtils.isNotBlank(contaCaixa)) {
				this.entity.setContaCaixa((ContaCaixa) this.subView
						.getMocContaCaixa().getValue());
			} else {
				this.entity.setContaCaixa(null);
			}

			this.entity.setBandeira(this.subView.getTfBandeira().getValue());
			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setFone1(this.subView.getTfTelefone1().getValue());
			this.entity.setFone2(this.subView.getTfTelefone2().getValue());

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			this.subView.getMocContaCaixa().setValue(
					this.entity.getContaCaixa());

			this.subView.getTfBandeira().setValue(this.entity.getBandeira());
			this.subView.getTfNome().setConvertedValue(this.entity.getNome());
			this.subView.getTfVencimentoAluguel().setConvertedValue(
					this.entity.getVencimentoAluguel());
			this.subView.getTfTaxaAdm().setConvertedValue(
					this.entity.getTaxaAdm());
			this.subView.getTfTaxaAdmDebito().setConvertedValue(
					this.entity.getTaxaAdmDebito());
			this.subView.getTfValorAluguelPosPin().setConvertedValue(
					this.entity.getValorAluguelPosPin());
			this.subView.getTfTelefone1().setValue(this.entity.getFone1());
			this.subView.getTfTelefone2().setValue(this.entity.getFone2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new OperadoraCartaoEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

}