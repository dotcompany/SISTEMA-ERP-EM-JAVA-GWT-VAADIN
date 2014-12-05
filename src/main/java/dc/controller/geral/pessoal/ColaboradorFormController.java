package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.FormaPagamentoEn;
import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.ColaboradorValidator;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.contabilidade.planoconta.PlanoContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.controller.financeiro.SindicatoListController;
import dc.controller.geral.UfListController;
import dc.controller.geral.diverso.SetorListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.contabilidade.PlanoConta;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.SindicatoEntity;
import dc.entidade.geral.NivelFormacaoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.SetorEntity;
import dc.entidade.geral.pessoal.CargoEntity;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.pessoal.SituacaoColaboradorEntity;
import dc.entidade.geral.pessoal.TipoColaboradorEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.contabilidade.PlanoContaDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.dao.geral.NivelFormacaoDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.diverso.SetorDAO;
import dc.servicos.dao.geral.pessoal.CargoDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.geral.pessoal.SituacaoColaboradorDAO;
import dc.servicos.dao.geral.pessoal.TipoColaboradorDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.ColaboradorFormView;

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

	private ColaboradorEntity currentBean;

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
	private UfDAO ufDAO;

	@Autowired
	private PlanoContaDAO planoContaDAO;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Override
	protected boolean validaSalvar() {
		try {
			ColaboradorValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new ColaboradorEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ColaboradorFormView(this);

			/* Configura combo Pessoa */
			// DefaultManyToOneComboModel<Pessoa> model= new
			// DefaultManyToOneComboModel(PessoaFisicaListController.class,pessoaDAO,mainController,daoPapel);
			// subView.getCmbPessoa().setModel(model);

			DefaultManyToOneComboModel<PessoaEntity> modelPessoa = new DefaultManyToOneComboModel<PessoaEntity>(
					PessoaListController.class, this.pessoaDAO,
					super.getMainController());
			this.subView.getMocPessoa().setModel(modelPessoa);

			DefaultManyToOneComboModel<NivelFormacaoEntity> modelNivelFormacao = new DefaultManyToOneComboModel<NivelFormacaoEntity>(
					NivelFormacaoListController.class, this.nivelFormacaoDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nome";
				}

			};

			this.subView.getMocNivelFormacao().setModel(modelNivelFormacao);

			DefaultManyToOneComboModel<TipoColaboradorEntity> modelTipoColaborador = new DefaultManyToOneComboModel<TipoColaboradorEntity>(
					TipoColaboradorListController.class,
					this.tipoColaboradorDAO, super.getMainController());

			this.subView.getMocTipoColaborador().setModel(modelTipoColaborador);

			DefaultManyToOneComboModel<CargoEntity> modelCargo = new DefaultManyToOneComboModel<CargoEntity>(
					CargoListController.class, this.cargoDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getMocCargo().setModel(modelCargo);

			DefaultManyToOneComboModel<SituacaoColaboradorEntity> modelSituacaoColaborador = new DefaultManyToOneComboModel<SituacaoColaboradorEntity>(
					SituacaoColaboradorListController.class,
					this.situacaoColaboradorDAO, super.getMainController());

			this.subView.getMocSituacaoColaborador().setModel(
					modelSituacaoColaborador);

			DefaultManyToOneComboModel<ContabilContaEntity> modelContabilConta = new DefaultManyToOneComboModel<ContabilContaEntity>(
					ContabilContaListController.class, this.contabilContaDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "descricao";
				}

			};

			this.subView.getMocContaContabil().setModel(modelContabilConta);

			DefaultManyToOneComboModel<SindicatoEntity> modelSindicato = new DefaultManyToOneComboModel<SindicatoEntity>(
					SindicatoListController.class, this.sindicatoDAO,
					super.getMainController());

			this.subView.getMocSindicato().setModel(modelSindicato);

			DefaultManyToOneComboModel<SetorEntity> modelSetor = new DefaultManyToOneComboModel<SetorEntity>(
					SetorListController.class, this.setorDAO,
					super.getMainController());

			this.subView.getMocSetor().setModel(modelSetor);

			DefaultManyToOneComboModel<PlanoConta> modelPlanoConta = new DefaultManyToOneComboModel<PlanoConta>(
					PlanoContaListController.class, this.planoContaDAO,
					super.getMainController());

			this.subView.getMocPlanoConta().setModel(modelPlanoConta);

			DefaultManyToOneComboModel<ContaCaixa> modelContaCaixa = new DefaultManyToOneComboModel<ContaCaixa>(
					ContaCaixaListController.class, this.contaCaixaDAO,
					super.getMainController());

			this.subView.getMocContaCaixa().setModel(modelContaCaixa);

			DefaultManyToOneComboModel<UfEntity> modelUf = new DefaultManyToOneComboModel<UfEntity>(
					UfListController.class, this.ufDAO,
					super.getMainController());

			this.subView.getMocUf().setModel(modelUf);

			comboDescontoPlanoSaude();
			comboFormaPagamento();
			comboOptante();
			comboSaiRais();
			comboPriorizarPgto();
			comboComissaoOver();
			// comboUf();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.colaboradorDAO.find(id);

			this.subView.getPdfDataCadastro().setValue(
					this.currentBean.getDataCadastro());
			this.subView.getPdfDataAdmissao().setValue(
					this.currentBean.getDataAdmissao());
			this.subView.getPdfDataVencimentoFerias().setValue(
					this.currentBean.getVencimentoFerias());
			this.subView.getPdfDataTransferencia().setValue(
					this.currentBean.getDataTransferencia());
			this.subView.getPdfDataDemissao().setValue(
					this.currentBean.getDataDemissao());
			this.subView.getPdfDataUltimoExame().setValue(
					this.currentBean.getExameMedicoUltimo());
			this.subView.getPdfDataVencimento().setValue(
					this.currentBean.getExameMedicoVencimento());
			this.subView.getPdfDataOpcao().setValue(
					this.currentBean.getFgtsDataOpcao());
			this.subView.getPdfDataCadastroPis().setValue(
					this.currentBean.getPisDataCadastro());
			this.subView.getTfBanco1().setValue(this.currentBean.getPisBanco());
			// this.subView.getMocUf().setValue(this.currentBean.getCtpsUf());
			this.subView.getTfAgencia1().setValue(
					this.currentBean.getPisAgencia());
			this.subView.getTfBanco().setValue(
					this.currentBean.getPagamentoBanco());
			this.subView.getTfAgencia().setValue(
					this.currentBean.getPagamentoAgencia());
			this.subView.getTfConta().setValue(
					this.currentBean.getPagamentoConta());
			this.subView.getTfObservacao().setValue(
					this.currentBean.getObservacao());

			this.subView.getCbPriorizarPgto().setValue(
					this.currentBean.getPriorizarComissao());
			this.subView.getCbComissaoOver().setValue(
					this.currentBean.getComissaoOver());

			boolean priorizarComissao = this.currentBean.getPriorizarComissao();

			if (ObjectUtils.isNotBlank(priorizarComissao)) {
				this.subView.getCbPriorizarPgto().setValue(priorizarComissao);
			}

			boolean comissaoOver = this.currentBean.getComissaoOver();

			if (ObjectUtils.isNotBlank(comissaoOver)) {
				this.subView.getCbComissaoOver().setValue(comissaoOver);
			}

			BigDecimal salarioFixo = this.currentBean.getSalarioFixo();

			if (salarioFixo != null) {
				this.subView.getTfSalarioFixo().setConvertedValue(salarioFixo);
			}

			this.subView.getOgTipoComissaoServico().setValue(
					this.currentBean.getTipoComissaoServico());
			this.subView.getOgTipoComissaoProduto().setValue(
					this.currentBean.getTipoComissaoProduto());

			BigDecimal comissaoServico = this.currentBean
					.getValorComissaoServico();

			if (comissaoServico != null) {
				this.subView.getTfComissaoServico().setConvertedValue(
						comissaoServico);
			}

			BigDecimal comissaoProduto = this.currentBean
					.getValorComissaoProduto();

			if (comissaoProduto != null) {
				this.subView.getTfComissaoProduto().setConvertedValue(
						comissaoProduto);
			}

			this.subView.getCbPgtoComissao().setValue(
					this.currentBean.getPgtoComissaoSera());
			this.subView.getCbLctoComissao().setValue(
					this.currentBean.getLctoComissao());

			PessoaEntity pessoa = this.currentBean.getPessoa();

			if (ObjectUtils.isNotBlank(pessoa)) {
				this.subView.getMocPessoa().setValue(pessoa);
			}

			TipoColaboradorEntity tipoColaborador = this.currentBean
					.getTipoColaborador();

			if (ObjectUtils.isNotBlank(tipoColaborador)) {
				this.subView.getMocTipoColaborador().setValue(tipoColaborador);
			}

			SituacaoColaboradorEntity situacaoColaborador = this.currentBean
					.getSituacaoColaborador();

			if (ObjectUtils.isNotBlank(situacaoColaborador)) {
				this.subView.getMocSituacaoColaborador().setValue(
						situacaoColaborador);
			}

			SindicatoEntity sindicato = this.currentBean.getSindicato();

			if (ObjectUtils.isNotBlank(sindicato)) {
				this.subView.getMocSindicato().setValue(sindicato);
			}

			NivelFormacaoEntity nivelFormacao = this.currentBean
					.getNivelFormacao();

			if (ObjectUtils.isNotBlank(nivelFormacao)) {
				this.subView.getMocNivelFormacao().setValue(nivelFormacao);
			}

			CargoEntity cargo = this.currentBean.getCargo();

			if (ObjectUtils.isNotBlank(cargo)) {
				this.subView.getMocCargo().setValue(cargo);
			}

			ContabilContaEntity contabilConta = this.currentBean
					.getContaContabil();

			if (ObjectUtils.isNotBlank(contabilConta)) {
				this.subView.getMocContaContabil().setValue(contabilConta);
			}

			ContaCaixa contaCaixa = this.currentBean.getContaCaixa();

			if (ObjectUtils.isNotBlank(contaCaixa)) {
				this.subView.getMocContaCaixa().setValue(contaCaixa);
			}

			PlanoConta planoConta = this.currentBean.getPlanoConta();

			if (ObjectUtils.isNotBlank(planoConta)) {
				this.subView.getMocPlanoConta().setValue(planoConta);
			}

			SetorEntity setor = this.currentBean.getSetor();

			if (ObjectUtils.isNotBlank(setor)) {
				this.subView.getMocSetor().setValue(setor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			PessoaEntity pessoa = this.subView.getMocPessoa().getValue();

			if (ObjectUtils.isNotBlank(pessoa)) {
				this.currentBean.setPessoa(pessoa);
			} else {
				this.currentBean.setPessoa(null);
			}

			TipoColaboradorEntity tipoColaborador = this.subView
					.getMocTipoColaborador().getValue();

			if (ObjectUtils.isNotBlank(tipoColaborador)) {
				this.currentBean.setTipoColaborador(tipoColaborador);
			} else {
				this.currentBean.setTipoColaborador(null);
			}

			SituacaoColaboradorEntity situacaoColaborador = this.subView
					.getMocSituacaoColaborador().getValue();

			if (ObjectUtils.isNotBlank(situacaoColaborador)) {
				this.currentBean.setSituacaoColaborador(situacaoColaborador);
			} else {
				this.currentBean.setSituacaoColaborador(null);
			}

			SindicatoEntity sindicato = this.subView.getMocSindicato()
					.getValue();

			if (ObjectUtils.isNotBlank(sindicato)) {
				this.currentBean.setSindicato(sindicato);
			} else {
				this.currentBean.setSindicato(null);
			}

			NivelFormacaoEntity nivelFormacao = this.subView
					.getMocNivelFormacao().getValue();

			if (ObjectUtils.isNotBlank(nivelFormacao)) {
				this.currentBean.setNivelFormacao(nivelFormacao);
			} else {
				this.currentBean.setNivelFormacao(null);
			}

			CargoEntity cargo = this.subView.getMocCargo().getValue();

			if (ObjectUtils.isNotBlank(cargo)) {
				this.currentBean.setCargo(cargo);
			} else {
				this.currentBean.setCargo(null);
			}

			ContabilContaEntity contabilConta = this.subView
					.getMocContaContabil().getValue();

			if (ObjectUtils.isNotBlank(contabilConta)) {
				this.currentBean.setContaContabil(contabilConta);
			} else {
				this.currentBean.setContaContabil(null);
			}

			ContaCaixa contaCaixa = this.subView.getMocContaCaixa().getValue();

			if (ObjectUtils.isNotBlank(contaCaixa)) {
				this.currentBean.setContaCaixa(contaCaixa);
			} else {
				this.currentBean.setContaCaixa(null);
			}

			PlanoConta planoConta = this.subView.getMocPlanoConta().getValue();

			if (ObjectUtils.isNotBlank(planoConta)) {
				this.currentBean.setPlanoConta(planoConta);
			} else {
				this.currentBean.setPlanoConta(null);
			}

			SetorEntity setor = this.subView.getMocSetor().getValue();

			if (ObjectUtils.isNotBlank(setor)) {
				this.currentBean.setSetor(setor);
			} else {
				this.currentBean.setSetor(null);
			}

			this.currentBean.setDataCadastro(this.subView.getPdfDataCadastro()
					.getValue());
			this.currentBean.setDataAdmissao(this.subView.getPdfDataAdmissao()
					.getValue());
			this.currentBean.setVencimentoFerias(this.subView
					.getPdfDataVencimentoFerias().getValue());
			this.currentBean.setDataTransferencia(this.subView
					.getPdfDataTransferencia().getValue());
			this.currentBean.setDataDemissao(this.subView.getPdfDataDemissao()
					.getValue());
			this.currentBean.setExameMedicoUltimo(this.subView
					.getPdfDataUltimoExame().getValue());
			this.currentBean.setExameMedicoVencimento(this.subView
					.getPdfDataVencimento().getValue());
			this.currentBean.setFgtsDataOpcao(this.subView.getPdfDataOpcao()
					.getValue());
			this.currentBean.setPisDataCadastro(this.subView
					.getPdfDataCadastroPis().getValue());
			this.currentBean.setPisBanco(this.subView.getTfBanco1().getValue());
			this.currentBean.setPisAgencia(this.subView.getTfAgencia1()
					.getValue());
			this.currentBean.setPagamentoBanco(this.subView.getTfBanco()
					.getValue());
			this.currentBean.setPagamentoAgencia(this.subView.getTfAgencia()
					.getValue());
			this.currentBean.setObservacao(this.subView.getTfObservacao()
					.getValue());

			if (this.subView.getTfSalarioFixo() != null) {
				this.currentBean.setSalarioFixo((BigDecimal) this.subView
						.getTfSalarioFixo().getConvertedValue());
			}

			SimNaoEn priorizarComissao = (SimNaoEn) this.subView
					.getCbPriorizarPgto().getValue();

			if (ObjectUtils.isNotBlank(priorizarComissao)) {
				this.currentBean
						.setPriorizarComissao(priorizarComissao == SimNaoEn.S ? true
								: false);
			}

			SimNaoEn comissaoOver = (SimNaoEn) this.subView.getCbComissaoOver()
					.getValue();

			if (ObjectUtils.isNotBlank(comissaoOver)) {
				this.currentBean
						.setComissaoOver(comissaoOver == SimNaoEn.S ? true
								: false);
			}

			this.currentBean.setTipoComissaoServico((String) this.subView
					.getOgTipoComissaoServico().getValue());
			this.currentBean.setTipoComissaoProduto((String) this.subView
					.getOgTipoComissaoProduto().getValue());

			if (this.subView.getTfComissaoProduto() != null) {
				this.currentBean
						.setValorComissaoProduto((BigDecimal) this.subView
								.getTfComissaoProduto().getConvertedValue());
			}

			if (this.subView.getTfComissaoServico() != null) {
				this.currentBean
						.setValorComissaoServico((BigDecimal) this.subView
								.getTfComissaoServico().getConvertedValue());
			}

			if (this.subView.getCbPgtoComissao().getValue() != null) {
				this.currentBean.setPgtoComissaoSera(Integer
						.valueOf(this.subView.getCbPgtoComissao().getValue()
								.toString()));
			}

			if (this.subView.getCbLctoComissao().getValue() != null) {
				this.currentBean.setLctoComissao(Integer.valueOf(this.subView
						.getCbLctoComissao().getValue().toString()));
			}

			this.colaboradorDAO.saveOrUpdate(this.currentBean);

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
		return "Colaborador";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.colaboradorDAO.deleteAllByIds(ids);

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

	/**
	 * COMBOS
	 */

	public void comboDescontoPlanoSaude() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbDescontoPlanoSaude().addItem(en);
		}
	}

	public void comboSaiRais() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbSaiRais().addItem(en);
		}
	}

	public void comboOptante() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbOptante().addItem(en);
		}
	}

	public void comboFormaPagamento() {
		for (FormaPagamentoEn en : FormaPagamentoEn.values()) {
			this.subView.getCbFormaPagamento().addItem(en);
		}
	}

	public void comboPriorizarPgto() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbPriorizarPgto().addItem(en);
		}
	}

	public void comboComissaoOver() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbComissaoOver().addItem(en);
		}
	}

	@Override
	public ColaboradorEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}