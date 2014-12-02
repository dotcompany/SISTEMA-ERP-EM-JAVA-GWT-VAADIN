package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.FormaDescontoEn;
import dc.control.enums.IndicadorPrecoEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoFreteEn;
import dc.control.util.ClassUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.geral.pessoal.AtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.ClienteDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.geral.pessoal.SituacaoForCliDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.ClienteFormView;

@Controller
@Scope("prototype")
public class ClienteFormController extends CRUDFormController<ClienteEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClienteFormView subView;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private SituacaoForCliDAO situacaoDAO;

	@Autowired
	private AtividadeForCliDAO atividadeDAO;

	@Autowired
	private ContabilContaDAO contaDAO;

	@Autowired
	private OperacaoFiscalDAO operacaoDAO;

	private ClienteEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		Date dataDesde = (Date) this.subView.getPdfDesde().getValue();

		if (!Validator.validateObject(dataDesde)) {
			adicionarErroDeValidacao(this.subView.getPdfDesde(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(this.subView.getTfContaTomador()
				.getValue())) {
			adicionarErroDeValidacao(this.subView.getTfContaTomador(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator
				.validateString(this.subView.getTfObservacao().getValue())) {
			adicionarErroDeValidacao(this.subView.getTfObservacao(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateNumber(this.subView.getTfLimiteCredito()
				.getConvertedValue().toString())) {
			adicionarErroDeValidacao(this.subView.getTfLimiteCredito(),
					"Número Inválido");

			valido = false;
		}

		PessoaEntity pessoa = (PessoaEntity) this.subView.getMocPessoa()
				.getValue();

		if (!Validator.validateObject(pessoa)) {
			adicionarErroDeValidacao(this.subView.getMocPessoa(),
					"Não pode ficar em branco");

			valido = false;
		}

		SituacaoForCliEntity situacao = (SituacaoForCliEntity) this.subView
				.getMocSituacao().getValue();

		if (!Validator.validateObject(situacao)) {
			adicionarErroDeValidacao(this.subView.getMocSituacao(),
					"Não pode ficar em branco");

			valido = false;
		}

		AtividadeForCliEntity atividade = (AtividadeForCliEntity) this.subView
				.getMocAtividade().getValue();

		if (!Validator.validateObject(atividade)) {
			adicionarErroDeValidacao(this.subView.getMocAtividade(),
					"Não pode ficar em branco");

			valido = false;
		}

		/*
		 * ContabilContaEntity contabil = (ContabilContaEntity) subView
		 * .getMocContaContabil().getValue();
		 * 
		 * if (!Validator.validateObject(contabil)) {
		 * adicionarErroDeValidacao(subView.getMocContaContabil(),
		 * "Não pode ficar em branco");
		 * 
		 * valido = false; }
		 */

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ClienteEntity();
	}

	@Override
	protected void initSubView() {
		this.subView = new ClienteFormView(this);

		DefaultManyToOneComboModel<PessoaEntity> model = new DefaultManyToOneComboModel<PessoaEntity>(
				PessoaListController.class, this.pessoaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				// TODO Auto-generated method stub
				return "nome";
			}

		};

		this.subView.getMocPessoa().setModel(model);

		DefaultManyToOneComboModel<SituacaoForCliEntity> modelsituacao = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
				SituacaoForCliListController.class, this.situacaoDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				// TODO Auto-generated method stub
				return "nome";
			}

		};

		this.subView.getMocSituacao().setModel(modelsituacao);

		DefaultManyToOneComboModel<AtividadeForCliEntity> modelatividade = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
				AtividadeForCliListController.class, this.atividadeDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				// TODO Auto-generated method stub
				return "nome";
			}

		};

		this.subView.getMocAtividade().setModel(modelatividade);

		/*
		 * DefaultManyToOneComboModel<ContabilContaEntity> modelconta = new
		 * DefaultManyToOneComboModel<ContabilContaEntity>(
		 * ContabilContaListController.class, contaDAO,
		 * super.getMainController()) {
		 * 
		 * @Override public String getCaptionProperty() { return
		 * "codigoReduzido"; }
		 * 
		 * };
		 */

		DefaultManyToOneComboModel<ContabilContaEntity> modelconta = new DefaultManyToOneComboModel<ContabilContaEntity>(
				ContabilContaListController.class, this.contaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "codigoReduzido";
			}

		};

		this.subView.getMocContaContabil().setModel(modelconta);

		DefaultManyToOneComboModel<OperacaoFiscalEntity> modeloperacao = new DefaultManyToOneComboModel<OperacaoFiscalEntity>(
				OperacaoFiscalListController.class, this.operacaoDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				// TODO Auto-generated method stub
				return "descricao";
			}

		};

		this.subView.getMocOperacaoFiscal().setModel(modeloperacao);

		comboGerarFinanceiro();
		comboFormaDesconto();
		comboIndicadorPreco();
		comboTipoFrete();
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.clienteDAO.find(id);

			this.subView.getMocPessoa().setValue(this.currentBean.getPessoa());
			this.subView.getMocContaContabil().setValue(
					this.currentBean.getContabilConta());
			this.subView.getMocSituacao().setValue(
					this.currentBean.getSituacaoForCli());
			this.subView.getMocAtividade().setValue(
					this.currentBean.getAtividadeForCli());
			this.subView.getMocOperacaoFiscal().setValue(
					this.currentBean.getOperacaoFiscal());

			this.subView.getPdfDesde().setValue(this.currentBean.getDesde());
			this.subView.getTfContaTomador().setValue(
					this.currentBean.getContaTomador());
			this.subView.getTfObservacao().setValue(
					this.currentBean.getObservacao());
			this.subView.getCbTipoFrete().setValue(
					this.currentBean.getTipoFrete());
			this.subView.getCbFormaDesconto().setValue(
					this.currentBean.getFormaDesconto());
			this.subView.getCbGerarFinanceiro().setValue(
					this.currentBean.getGerarFinanceiro());
			this.subView.getCbIndicadorPreco().setValue(
					this.currentBean.getIndicadorPreco());
			this.subView.getTfTaxaDesconto().setConvertedValue(
					this.currentBean.getPorcentoDesconto().toString());
			this.subView.getTfLimiteCredito().setConvertedValue(
					this.currentBean.getLimiteCredito().toString());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setPessoa((PessoaEntity) this.subView
					.getMocPessoa().getValue());

			this.currentBean
					.setSituacaoForCli((SituacaoForCliEntity) this.subView
							.getMocSituacao().getValue());
			this.currentBean
					.setAtividadeForCli((AtividadeForCliEntity) this.subView
							.getMocAtividade().getValue());

			if (this.subView.getMocContaContabil().getValue() != null) {
				this.currentBean
						.setContabilConta((ContabilContaEntity) this.subView
								.getMocContaContabil().getValue());
			}

			if (this.subView.getMocOperacaoFiscal().getValue() != null) {
				this.currentBean
						.setOperacaoFiscal((OperacaoFiscalEntity) this.subView
								.getMocOperacaoFiscal().getValue());
			}

			this.currentBean.setDesde(this.subView.getPdfDesde().getValue());
			this.currentBean.setContaTomador(this.subView.getTfContaTomador()
					.getValue());
			this.currentBean.setObservacao(this.subView.getTfObservacao()
					.getValue());

			TipoFreteEn tipoFreteEn = (TipoFreteEn) this.subView
					.getCbTipoFrete().getValue();

			this.currentBean.setTipoFrete(tipoFreteEn);

			FormaDescontoEn formaDescontoEn = (FormaDescontoEn) this.subView
					.getCbFormaDesconto().getValue();

			this.currentBean.setFormaDesconto(formaDescontoEn);

			SimNaoEn geraFinanceiroEn = (SimNaoEn) this.subView
					.getCbGerarFinanceiro().getValue();

			this.currentBean.setGerarFinanceiro(geraFinanceiroEn);

			IndicadorPrecoEn indicadorPrecoEn = (IndicadorPrecoEn) this.subView
					.getCbIndicadorPreco().getValue();

			this.currentBean.setIndicadorPreco(indicadorPrecoEn);

			this.currentBean.setPorcentoDesconto(new BigDecimal(
					(String) this.subView.getTfTaxaDesconto()
							.getConvertedValue()));
			this.currentBean.setLimiteCredito(new BigDecimal(
					(String) this.subView.getTfLimiteCredito()
							.getConvertedValue()));

			this.clienteDAO.saveOrUpdate(this.currentBean);

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
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Cliente";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.clienteDAO.deleteAllByIds(ids);

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

	/**
	 * COMBOS
	 */

	public void comboGerarFinanceiro() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbGerarFinanceiro().addItem(en);
		}
	}

	public void comboIndicadorPreco() {
		for (IndicadorPrecoEn en : IndicadorPrecoEn.values()) {
			this.subView.getCbIndicadorPreco().addItem(en);
		}
	}

	public void comboTipoFrete() {
		for (TipoFreteEn en : TipoFreteEn.values()) {
			this.subView.getCbTipoFrete().addItem(en);
		}
	}

	public void comboFormaDesconto() {
		for (FormaDescontoEn en : FormaDescontoEn.values()) {
			this.subView.getCbFormaDesconto().addItem(en);
		}
	}

	@Override
	public ClienteEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}