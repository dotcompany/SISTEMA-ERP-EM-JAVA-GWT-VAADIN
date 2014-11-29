package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.contabilidade.planoconta.PlanoContaListController;
import dc.controller.diversos.SetorListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.controller.financeiro.SindicatoListController;
import dc.controller.geral.NivelFormacaoListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.contabilidade.PlanoConta;
import dc.entidade.diversos.Setor;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.Sindicato;
import dc.entidade.geral.NivelFormacaoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.entidade.type.pessoal.DescontoPlanoSaudeType;
import dc.entidade.type.pessoal.FormaPagamentoType;
import dc.entidade.type.pessoal.OptanteType;
import dc.entidade.type.pessoal.SaiRaisType;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.contabilidade.PlanoContaDAO;
import dc.servicos.dao.diversos.SetorDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.dao.geral.NivelFormacaoDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.geral.pessoal.CargoDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.geral.pessoal.SituacaoColaboradorDAO;
import dc.servicos.dao.geral.pessoal.TipoColaboradorDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.ColaboradorFormView;

@Controller
@Scope("prototype")
public class ColaboradorFormController extends
		CRUDFormController<ColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * @Autowired private MainController mainController;
	 */

	private ColaboradorFormView subView;

	@Autowired
	private ColaboradorDAO colaboradorDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private TipoColaboradorDAO tipoColaboradorDAO;

	@Autowired
	private SituacaoColaboradorDAO situacaoColaboradorDAO;

	@Autowired
	private SindicatoDAO sindicatoDAO;

	@Autowired
	private NivelFormacaoDAO nivelFormacaoDAO;

	@Autowired
	private CargoDAO cargoDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private SetorDAO setorDAO;

	@Autowired
	private UFDAO ufDAO;

	@Autowired
	PlanoContaDAO planoContaDAO;

	@Autowired
	ContaCaixaDAO contaCaixaDAO;

	private ColaboradorEntity currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtMatricula().getValue())) {
			adicionarErroDeValidacao(subView.getTxtMatricula(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtCategoria().getValue())) {
			adicionarErroDeValidacao(subView.getTxtCategoria(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new ColaboradorEntity();
	}

	@Override
	protected void initSubView() {
		subView = new ColaboradorFormView(this);

		this.subView.InitCbs(getColaboradorDescontoPlanoSaudeType(),
				getColaboradorSaiRaisType(), getColaboradorOptanteType(),
				getColaboradorFormaPagamentoType());

		/* Configura combo Pessoa */
		// DefaultManyToOneComboModel<Pessoa> model= new
		// DefaultManyToOneComboModel(PessoaFisicaListController.class,pessoaDAO,mainController,daoPapel);
		// subView.getCmbPessoa().setModel(model);

		DefaultManyToOneComboModel<PessoaEntity> modelPessoa = new DefaultManyToOneComboModel<PessoaEntity>(
				PessoaListController.class, pessoaDAO,
				super.getMainController());
		subView.getCmbPessoa().setModel(modelPessoa);

		DefaultManyToOneComboModel<NivelFormacaoEntity> modelNivelFormacao = new DefaultManyToOneComboModel<NivelFormacaoEntity>(
				NivelFormacaoListController.class, nivelFormacaoDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "nome";
			}
		};

		this.subView.getCmbNivelFormacao().setModel(modelNivelFormacao);

		DefaultManyToOneComboModel<TipoColaboradorEntity> modelTipo = new DefaultManyToOneComboModel<TipoColaboradorEntity>(
				TipoColaboradorListController.class, tipoColaboradorDAO,
				super.getMainController());
		subView.getCmbTipoColaborador().setModel(modelTipo);

		DefaultManyToOneComboModel<CargoEntity> modelCargo = new DefaultManyToOneComboModel<CargoEntity>(
				CargoListController.class, cargoDAO, super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCmbCargo().setModel(modelCargo);

		DefaultManyToOneComboModel<SituacaoColaboradorEntity> modelSituacaoColaborador = new DefaultManyToOneComboModel<SituacaoColaboradorEntity>(
				SituacaoColaboradorListController.class,
				situacaoColaboradorDAO, super.getMainController());
		subView.getCmbSituacaoColaborador().setModel(modelSituacaoColaborador);

		DefaultManyToOneComboModel<ContabilConta> model = new DefaultManyToOneComboModel<ContabilConta>(
				ContabilContaListController.class, this.contabilContaDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCmbContaContabil().setModel(model);

		DefaultManyToOneComboModel<Sindicato> modelSindicato = new DefaultManyToOneComboModel<Sindicato>(
				SindicatoListController.class, sindicatoDAO,
				super.getMainController());
		subView.getCmbSindicato().setModel(modelSindicato);

		DefaultManyToOneComboModel<Setor> modelSetor = new DefaultManyToOneComboModel<Setor>(
				SetorListController.class, setorDAO, super.getMainController());
		subView.getCmbSetor().setModel(modelSetor);

		/*
		 * DefaultManyToOneComboModel<UF> modelUf = new
		 * DefaultManyToOneComboModel<UF>( UFListController.class, this.ufDAO,
		 * super.getMainController()); subView.getCmbUf().setModel(modelUf);
		 */

		DefaultManyToOneComboModel<PlanoConta> planoConta = new DefaultManyToOneComboModel<PlanoConta>(
				PlanoContaListController.class, this.planoContaDAO,
				super.getMainController());

		this.subView.getCbPlanoConta().setModel(planoConta);

		DefaultManyToOneComboModel<ContaCaixa> contaCaixa = new DefaultManyToOneComboModel<ContaCaixa>(
				ContaCaixaListController.class, this.contaCaixaDAO,
				super.getMainController());

		this.subView.getCbContaCaixa().setModel(contaCaixa);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = colaboradorDAO.find(id);

		subView.getDtCadastro().setValue(currentBean.getDataCadastro());
		subView.getDtAdmissao().setValue(currentBean.getDataAdmissao());
		subView.getDtVencimentoFerias().setValue(
				currentBean.getVencimentoFerias());
		subView.getDtTransferencia().setValue(
				currentBean.getDataTransferencia());
		subView.getDtDemissao().setValue(currentBean.getDataDemissao());
		subView.getDtUltimoExame().setValue(currentBean.getExameMedicoUltimo());
		subView.getDtVencimento().setValue(
				currentBean.getExameMedicoVencimento());
		subView.getDtDataOpcao().setValue(currentBean.getFgtsDataOpcao());
		subView.getDtCadastroPis().setValue(currentBean.getPisDataCadastro());
		subView.getTxtBanco1().setValue(currentBean.getPisBanco());
		subView.getCmbUf().setValue(currentBean.getCtpsUf());
		subView.getTxtAgencia1().setValue(currentBean.getPisAgencia());
		subView.getTxtBanco().setValue(currentBean.getPagamentoBanco());
		subView.getTxtAgencia().setValue(currentBean.getPagamentoAgencia());
		subView.getTxtConta().setValue(currentBean.getPagamentoConta());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());

		subView.getCbPriorizarPgto().setValue(
				currentBean.getPriorizarComissao());
		subView.getCbComissaoOver().setValue(currentBean.getComissaoOver());

		BigDecimal salarioFixo = currentBean.getSalarioFixo();
		if (salarioFixo != null) {
			subView.getTxtSalarioFixo().setConvertedValue(salarioFixo);
			;
		}
		subView.getOptTipoComissaoServico().setValue(
				currentBean.getTipoComissaoServico());
		subView.getOptTipoComissaoProduto().setValue(
				currentBean.getTipoComissaoProduto());

		BigDecimal comissaoServico = currentBean.getValorComissaoServico();
		if (comissaoServico != null) {
			subView.getTxtComissaoServico().setConvertedValue(comissaoServico);
			;
		}

		BigDecimal comissaoProduto = currentBean.getValorComissaoProduto();
		if (comissaoProduto != null) {
			subView.getTxtComissaoProduto().setConvertedValue(comissaoProduto);
			;
		}
		subView.getCbPgtoComissao().setValue(currentBean.getPgtoComissaoSera());
		subView.getCbLctoComissao().setValue(currentBean.getLctoComissao());
		subView.getCbContaCaixa().setValue(currentBean.getContaCaixa());
		subView.getCbPlanoConta().setValue(currentBean.getPlanoConta());

		/*
		 * subView.getCmbPessoa().setValue(currentBean.getPessoa());
		 * 
		 * subView.getCmbTipoColaborador().setValue(
		 * currentBean.getIdTipoColaborador());
		 * 
		 * DefaultManyToOneComboModel<SituacaoColaborador> model = new
		 * DefaultManyToOneComboModel<SituacaoColaborador>(
		 * SituacaoColaboradorListController.class, this.situacaoColaboradorDAO,
		 * super.getMainController());
		 * 
		 * subView.getCmbSituacaoColaborador().setModel(model);
		 * subView.getCmbSituacaoColaborador().setValue(
		 * currentBean.getIdSituacaoColaborador());
		 */

		/*
		 * DefaultManyToOneComboModel<Sindicato> modelsindicato = new
		 * DefaultManyToOneComboModel<Sindicato>( sindic.class,
		 * this.colaboradorDAO, super.getMainController());
		 * 
		 * subView.getCmbSindicato().setModel(modelsindicato);
		 */
		// subView.getCmbSindicato().setValue(currentBean.getIdSindicato());

		/*
		 * DefaultManyToOneComboModel<Sindicato> modelsindicato = new
		 * DefaultManyToOneComboModel<Sindicato>( Nivelf.class,
		 * this.colaboradorDAO, super.getMainController());
		 * 
		 * subView.getCmbNivelFormacao().setModel(modelnivel);
		 */
		/*
		 * subView.getCmbNivelFormacao()
		 * .setValue(currentBean.getIdNivelFormacao());
		 * 
		 * DefaultManyToOneComboModel<Cargo> modelcargo = new
		 * DefaultManyToOneComboModel<Cargo>( CargoListController.class,
		 * this.cargoDAO, super.getMainController());
		 * 
		 * subView.getCmbCargo().setModel(modelcargo);
		 * subView.getCmbCargo().setValue(currentBean.getIdCargo());
		 * 
		 * DefaultManyToOneComboModel<ContabilConta> modelconta = new
		 * DefaultManyToOneComboModel<ContabilConta>(
		 * ContabilContaListController.class, this.contabilContaDAO,
		 * super.getMainController());
		 * 
		 * subView.getCmbContaContabil().setModel(modelconta);
		 * subView.getCmbContaContabil()
		 * .setValue(currentBean.getIdContaContabil());
		 * 
		 * DefaultManyToOneComboModel<Setor> modelsetor = new
		 * DefaultManyToOneComboModel<Setor>( SetorListController.class,
		 * this.setorDAO, super.getMainController());
		 * 
		 * subView.getCmbSetor().setModel(modelsetor);
		 * subView.getCmbSetor().setValue(currentBean.getIdSetor());
		 * 
		 * subView.getCmbUf().setValue(currentBean.getCtpsUf());
		 */
	}

	void carregarCombos() {
		carregarUFs();
	}

	public List<UfEntity> listarUfs() {
		return ufDAO.listaTodos();
	}

	public BeanItemContainer<String> carregarUFs() {
		BeanItemContainer<String> container = new BeanItemContainer<>(
				String.class);
		List<UfEntity> ufs = listarUfs();

		for (UfEntity u : ufs) {
			container.addBean(u.getSigla());
		}

		return container;
	}

	@Override
	protected void actionSalvar() {
		currentBean.setPessoa((PessoaEntity) subView.getCmbPessoa().getValue());
		currentBean.setIdTipoColaborador((TipoColaboradorEntity) subView
				.getCmbTipoColaborador().getValue());
		currentBean
				.setIdSituacaoColaborador((SituacaoColaboradorEntity) subView
						.getCmbSituacaoColaborador().getValue());
		currentBean.setIdSindicato((Sindicato) subView.getCmbSindicato()
				.getValue());
		currentBean.setIdNivelFormacao((NivelFormacaoEntity) subView
				.getCmbNivelFormacao().getValue());
		currentBean.setIdCargo((CargoEntity) subView.getCmbCargo().getValue());
		currentBean.setIdContaContabil((ContabilConta) subView
				.getCmbContaContabil().getValue());
		currentBean.setDataCadastro(subView.getDtCadastro().getValue());
		currentBean.setDataAdmissao(subView.getDtAdmissao().getValue());
		currentBean.setVencimentoFerias(subView.getDtVencimentoFerias()
				.getValue());
		currentBean.setDataTransferencia(subView.getDtTransferencia()
				.getValue());
		currentBean.setDataDemissao(subView.getDtDemissao().getValue());
		currentBean.setExameMedicoUltimo(subView.getDtUltimoExame().getValue());
		currentBean.setExameMedicoVencimento(subView.getDtVencimento()
				.getValue());
		currentBean.setFgtsDataOpcao(subView.getDtDataOpcao().getValue());
		currentBean.setPisDataCadastro(subView.getDtCadastroPis().getValue());
		currentBean.setPisBanco(subView.getTxtBanco1().getValue());
		currentBean.setPisAgencia(subView.getTxtAgencia1().getValue());
		currentBean.setPagamentoBanco(subView.getTxtBanco().getValue());
		currentBean.setPagamentoAgencia(subView.getTxtAgencia().getValue());
		currentBean.setObservacao(subView.getTxtObservacao().getValue());

		if (subView.getTxtSalarioFixo() != null) {
			currentBean.setSalarioFixo((BigDecimal) subView.getTxtSalarioFixo()
					.getConvertedValue());
		}

		currentBean.setPriorizarComissao(Boolean.valueOf(subView
				.getCbPriorizarPgto().getValue().toString()));
		currentBean.setComissaoOver(Boolean.valueOf(subView.getCbComissaoOver()
				.getValue().toString()));
		currentBean.setTipoComissaoServico((String) subView
				.getOptTipoComissaoServico().getValue());
		currentBean.setTipoComissaoProduto((String) subView
				.getOptTipoComissaoProduto().getValue());

		if (subView.getTxtComissaoProduto() != null) {
			currentBean.setValorComissaoProduto((BigDecimal) subView
					.getTxtComissaoProduto().getConvertedValue());
		}

		if (subView.getTxtComissaoServico() != null) {
			currentBean.setValorComissaoServico((BigDecimal) subView
					.getTxtComissaoServico().getConvertedValue());
		}

		if (subView.getCbPgtoComissao().getValue() != null) {
			currentBean.setPgtoComissaoSera(Integer.valueOf(subView
					.getCbPgtoComissao().getValue().toString()));
		}

		if (subView.getCbLctoComissao().getValue() != null) {
			currentBean.setLctoComissao(Integer.valueOf(subView
					.getCbLctoComissao().getValue().toString()));
		}

		if (subView.getCbContaCaixa() != null) {
			currentBean.setContaCaixa(subView.getCbContaCaixa().getValue());
		}

		if (subView.getCbPlanoConta() != null) {
			currentBean.setPlanoConta(subView.getCbPlanoConta().getValue());
		}

		try {
			colaboradorDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected String getNome() {
		return "Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		colaboradorDAO.deleteAllByIds(ids);

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

	/** COMBOS */

	public List<String> getColaboradorDescontoPlanoSaudeType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (DescontoPlanoSaudeType en : DescontoPlanoSaudeType.values()) {
				siLista.add(en.ordinal(), en.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getColaboradorSaiRaisType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (SaiRaisType in : SaiRaisType.values()) {
				siLista.add(in.ordinal(), in.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getColaboradorOptanteType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (OptanteType tf : OptanteType.values()) {
				siLista.add(tf.ordinal(), tf.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getColaboradorFormaPagamentoType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (FormaPagamentoType fd : FormaPagamentoType.values()) {
				siLista.add(fd.ordinal(), fd.toString());
			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	public ColaboradorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}