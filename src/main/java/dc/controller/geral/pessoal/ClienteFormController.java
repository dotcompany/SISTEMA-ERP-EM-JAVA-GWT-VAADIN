package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.entidade.type.pessoal.FormaDescontoType;
import dc.entidade.type.pessoal.GerarFinanceiroType;
import dc.entidade.type.pessoal.IndicadorPrecoType;
import dc.entidade.type.pessoal.TipoFreteType;
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

		Date dataDesde = (Date) subView.getDtDesde().getValue();

		if (!Validator.validateObject(dataDesde)) {
			adicionarErroDeValidacao(subView.getDtDesde(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtContaTomador().getValue())) {
			adicionarErroDeValidacao(subView.getTxtContaTomador(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateString(subView.getTxtObservacao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtObservacao(),
					"Não pode ficar em branco");

			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtLimiteCredito()
				.getConvertedValue().toString())) {
			adicionarErroDeValidacao(subView.getTxtLimiteCredito(),
					"Número Inválido");

			valido = false;
		}

		PessoaEntity pessoa = (PessoaEntity) subView.getMocPessoa().getValue();

		if (!Validator.validateObject(pessoa)) {
			adicionarErroDeValidacao(subView.getMocPessoa(),
					"Não pode ficar em branco");

			valido = false;
		}

		SituacaoForCliEntity situacao = (SituacaoForCliEntity) subView
				.getMocSituacao().getValue();

		if (!Validator.validateObject(situacao)) {
			adicionarErroDeValidacao(subView.getMocSituacao(),
					"Não pode ficar em branco");

			valido = false;
		}

		AtividadeForCliEntity atividade = (AtividadeForCliEntity) subView
				.getMocAtividade().getValue();

		if (!Validator.validateObject(atividade)) {
			adicionarErroDeValidacao(subView.getMocAtividade(),
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
				super.getMainController());

		this.subView.getMocPessoa().setModel(model);

		DefaultManyToOneComboModel<SituacaoForCliEntity> modelsituacao = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
				SituacaoForCliListController.class, this.situacaoDAO,
				super.getMainController());

		this.subView.getMocSituacao().setModel(modelsituacao);

		DefaultManyToOneComboModel<AtividadeForCliEntity> modelatividade = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
				AtividadeForCliListController.class, this.atividadeDAO,
				super.getMainController());

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
				super.getMainController());

		this.subView.getMocContaContabil().setModel(modelconta);

		DefaultManyToOneComboModel<OperacaoFiscalEntity> modeloperacao = new DefaultManyToOneComboModel<OperacaoFiscalEntity>(
				OperacaoFiscalListController.class, this.operacaoDAO,
				super.getMainController());

		this.subView.getMocOperacaoFiscal().setModel(modeloperacao);

		/* subView.getCmbGerarFinanceiro.setValue(GerarFinanceiroType); */

		/*
		 * this.subView.InitCbs(getClienteTipo());
		 * this.subView.InitCbs(getClienteIndicadorPrecoType());
		 * this.subView.InitCbs(getClienteTipoFreteType());
		 * this.subView.InitCbs(getClienteFormaDescontoType());
		 */

		/*
		 * this.subView.getCmbGerarFinanceiro().setData(
		 * getClienteGerarFinanceiroType());
		 * this.subView.getCmbIndicadorPreco().
		 * setData(getClienteIndicadorPrecoType());
		 * this.subView.getCmbTipoFrete().setData(getClienteTipoFreteType());
		 * this.subView.getCmbFormaDesconto().setData(
		 * getClienteFormaDescontoType());
		 */

		this.subView.InitCbs(getClienteGerarFinanceiroType(),
				getClienteIndicadorPrecoType(), getClienteTipoFreteType(),
				getClienteFormaDescontoType());
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

			this.subView.getDtDesde().setValue(this.currentBean.getDesde());
			this.subView.getTxtContaTomador().setValue(
					this.currentBean.getContaTomador());
			this.subView.getTxtObservacao().setValue(
					this.currentBean.getObservacao());
			this.subView.getCmbTipoFrete().setValue(
					this.currentBean.getTipoFrete());
			this.subView.getCmbFormaDesconto().setValue(
					this.currentBean.getFormaDesconto());
			this.subView.getCmbGerarFinanceiro().setValue(
					this.currentBean.getGeraFinanceiro());
			this.subView.getCmbIndicadorPreco().setValue(
					this.currentBean.getIndicadorPreco());
			this.subView.getTxtTaxaDesconto().setConvertedValue(
					this.currentBean.getPorcentoDesconto());
			this.subView.getTxtLimiteCredito().setConvertedValue(
					this.currentBean.getLimiteCredito());
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

			this.currentBean.setDesde(this.subView.getDtDesde().getValue());
			this.currentBean.setContaTomador(this.subView.getTxtContaTomador()
					.getValue());
			this.currentBean.setObservacao(this.subView.getTxtObservacao()
					.getValue());
			this.currentBean.setTipoFrete((String) this.subView
					.getCmbTipoFrete().getValue());
			this.currentBean.setFormaDesconto((String) this.subView
					.getCmbFormaDesconto().getValue());
			this.currentBean.setGeraFinanceiro((String) this.subView
					.getCmbGerarFinanceiro().getValue());
			this.currentBean.setIndicadorPreco((String) this.subView
					.getCmbIndicadorPreco().getValue());
			this.currentBean.setPorcentoDesconto(new BigDecimal(
					(String) this.subView.getTxtTaxaDesconto()
							.getConvertedValue()));
			this.currentBean.setLimiteCredito(new BigDecimal(
					(String) this.subView.getTxtLimiteCredito()
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
		clienteDAO.deleteAllByIds(ids);

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

	/** COMBO */

	public List<String> getClienteGerarFinanceiroType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (GerarFinanceiroType en : GerarFinanceiroType.values()) {
				siLista.add(en.ordinal(), en.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getClienteIndicadorPrecoType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (IndicadorPrecoType in : IndicadorPrecoType.values()) {
				siLista.add(in.ordinal(), in.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getClienteTipoFreteType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (TipoFreteType tf : TipoFreteType.values()) {
				siLista.add(tf.ordinal(), tf.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getClienteFormaDescontoType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (FormaDescontoType fd : FormaDescontoType.values()) {
				siLista.add(fd.ordinal(), fd.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ClienteEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}