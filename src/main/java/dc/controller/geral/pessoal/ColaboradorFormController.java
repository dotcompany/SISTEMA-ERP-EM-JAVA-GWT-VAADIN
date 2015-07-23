package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.enums.FormaPagamentoEn;
import dc.control.enums.SimNaoEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.controller.contabilidade.planoconta.PlanoContaListController;
import dc.controller.financeiro.ContaCaixaListController;
import dc.controller.geral.diverso.SetorListController;
import dc.controller.geral.diverso.UfListController;
import dc.controller.geral.outro.SindicatoListController;
import dc.entidade.geral.pessoal.ColaboradorEntity;
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
import dc.visao.framework.DCFieldGroup;
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
			// Commit tenta transferir os dados do View para a entidade , levando em conta os critérios de validação.
			fieldGroup.commit();
		    return true;
		} catch (FieldGroup.CommitException ce) {
		    return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new ColaboradorEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new ColaboradorFormView(this);
			
            this.fieldGroup = new DCFieldGroup<>(ColaboradorEntity.class);
			
			// Mapeia os campos
			
			//fieldGroup.bind(this.subView.getTfNome(),"nome");
			
			this.subView.getMocPessoa().configuraCombo(
					"nome", PessoaListController.class, this.pessoaDAO, this.getMainController());
			this.subView.getMocNivelFormacao().configuraCombo(
					"nome", NivelFormacaoListController.class, this.nivelFormacaoDAO, this.getMainController());
			this.subView.getMocTipoColaborador().configuraCombo(
					"nome", TipoColaboradorListController.class, this.tipoColaboradorDAO, this.getMainController());
			this.subView.getMocCargo().configuraCombo(
					"descricao", CargoListController.class, this.cargoDAO, this.getMainController());
			this.subView.getMocSituacaoColaborador().configuraCombo(
					"nome", SituacaoColaboradorListController.class, this.situacaoColaboradorDAO, this.getMainController());
			this.subView.getMocSindicato().configuraCombo(
					"nome", SindicatoListController.class, this.sindicatoDAO, this.getMainController());
			this.subView.getMocSetor().configuraCombo(
					"nome", SetorListController.class, this.setorDAO, this.getMainController());
			this.subView.getMocPlanoConta().configuraCombo(
					"nome", PlanoContaListController.class, this.planoContaDAO, this.getMainController());
			this.subView.getMocContaCaixa().configuraCombo(
					"nome", ContaCaixaListController.class, this.contaCaixaDAO, this.getMainController());
			this.subView.getMocUf().configuraCombo(
					"nome", UfListController.class, this.ufDAO, this.getMainController());


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
			
			fieldGroup.setItemDataSource(this.currentBean);

			this.subView.getPdfDataExpedida().setValue(
					this.currentBean.getCtpsDataExpedicao());
			this.subView.getTfSerie().setValue(this.currentBean.getCtpsSerie());
			this.subView.getTfNumeroCarteira().setValue(
					this.currentBean.getCtpsNumero());
			this.subView.getTfDigitoAgencia1().setValue(
					this.currentBean.getPisNumero());
			this.subView.getTfAgencia1().setValue(
					this.currentBean.getPisAgencia());
			this.subView.getTfBanco1().setValue(this.currentBean.getPisBanco());
			this.subView.getTfNumeroPis().setValue(
					this.currentBean.getPisNumero());
			this.subView.getPdfDataCadastroPis().setValue(
					this.currentBean.getPisDataCadastro());
			this.subView.getTfDigitoConta().setValue(
					this.currentBean.getPagamentoContaDigito());
			this.subView.getTfConta().setValue(
					this.currentBean.getPagamentoConta());
			this.subView.getTfDigitoAgencia().setValue(
					this.currentBean.getPagamentoAgenciaDigito());
			this.subView.getTfAgencia().setValue(
					this.currentBean.getPagamentoAgencia());
			this.subView.getTfBanco().setValue(
					this.currentBean.getPagamentoBanco());

			FormaPagamentoEn pagamentoFormaEn = this.currentBean
					.getPagamentoForma();

			if (ObjectUtils.isNotBlank(pagamentoFormaEn)) {
				this.subView.getCbFormaPagamento().setValue(
						this.currentBean.getPagamentoForma());
			}

			Integer codigoDemissaoCaged = this.currentBean
					.getCodigoDemissaoCaged();

			if (NumberUtils.isNotBlank(codigoDemissaoCaged)) {
				this.subView.getTfCodigoDemissaoCaged().setValue(
						this.currentBean.getCodigoDemissaoCaged().toString());
			}

			Integer codigoAdmissaoCaged = this.currentBean
					.getCodigoAdmissaoCaged();

			if (NumberUtils.isNotBlank(codigoAdmissaoCaged)) {
				this.subView.getTfCodigoAdmissao().setValue(
						this.currentBean.getCodigoAdmissaoCaged().toString());
			}

			this.subView.getPdfDataOpcao().setValue(
					this.currentBean.getFgtsDataOpcao());

			SimNaoEn fgtsOptanteEn = this.currentBean.getFgtsOptante();

			if (ObjectUtils.isNotBlank(fgtsOptanteEn)) {
				this.subView.getCbOptante().setValue(
						this.currentBean.getFgtsOptante());
			}

			Integer codigoDemissaoSefip = this.currentBean
					.getCodigoDemissaoSefip();

			if (NumberUtils.isNotBlank(codigoDemissaoSefip)) {
				this.subView.getTfCodigoDemissao().setValue(
						this.currentBean.getCodigoDemissaoSefip().toString());
			}

			Integer ocorrenciaSefip = this.currentBean.getOcorrenciaSefip();

			if (NumberUtils.isNotBlank(ocorrenciaSefip)) {
				this.subView.getTfOcorrencia().setValue(
						this.currentBean.getOcorrenciaSefip().toString());
			}

			this.subView.getTfCategoria().setValue(
					this.currentBean.getCategoriaSefip());

			BigDecimal salarioFixo = this.currentBean.getSalarioFixo();

			// if (salarioFixo != null) {
			this.subView.getTfSalarioFixo().setConvertedValue(salarioFixo);
			// }

			BigDecimal comissaoProduto = this.currentBean
					.getValorComissaoProduto();

			// if (comissaoProduto != null) {
			this.subView.getTfComissaoProduto().setConvertedValue(
					comissaoProduto);
			// }

			BigDecimal comissaoServico = this.currentBean
					.getValorComissaoServico();

			// if (comissaoServico != null) {
			this.subView.getTfComissaoServico().setConvertedValue(
					comissaoServico);
			// }

			this.subView.getPdfDataVencimento().setValue(
					this.currentBean.getExameMedicoVencimento());
			this.subView.getPdfDataUltimoExame().setValue(
					this.currentBean.getExameMedicoUltimo());
			this.subView.getTfCodigoTurma().setValue(
					this.currentBean.getCodigoTurmaPonto());

			SimNaoEn saiNaRaisEn = this.currentBean.getSaiNaRais();

			if (ObjectUtils.isNotBlank(saiNaRaisEn)) {
				this.subView.getCbSaiRais().setValue(
						this.currentBean.getSaiNaRais());
			}

			SimNaoEn descontoPlanoSaudeEn = this.currentBean
					.getDescontoPlanoSaude();

			if (ObjectUtils.isNotBlank(descontoPlanoSaudeEn)) {
				this.subView.getCbDescontoPlanoSaude().setValue(
						this.currentBean.getDescontoPlanoSaude());
			}

			this.subView.getPdfDataDemissao().setValue(
					this.currentBean.getDataDemissao());
			this.subView.getPdfDataTransferencia().setValue(
					this.currentBean.getDataTransferencia());
			this.subView.getPdfDataVencimentoFerias().setValue(
					this.currentBean.getVencimentoFerias());
			this.subView.getPdfDataAdmissao().setValue(
					this.currentBean.getDataAdmissao());
			this.subView.getPdfDataCadastro().setValue(
					this.currentBean.getDataCadastro());
			this.subView.getTfMatricula().setValue(
					this.currentBean.getMatricula());

			boolean priorizarComissao = this.currentBean.getPriorizarComissao();

			if (ObjectUtils.isNotBlank(priorizarComissao)) {
				this.subView.getCbPriorizarPgto().setValue(priorizarComissao);
			}

			boolean comissaoOver = this.currentBean.getComissaoOver();

			if (ObjectUtils.isNotBlank(comissaoOver)) {
				this.subView.getCbComissaoOver().setValue(comissaoOver);
			}

			this.subView.getCbPgtoComissao().setValue(
					this.currentBean.getPgtoComissaoSera());
			this.subView.getCbLctoComissao().setValue(
					this.currentBean.getLctoComissao());
			this.subView.getOgTipoComissaoServico().setValue(
					this.currentBean.getTipoComissaoServico());
			this.subView.getOgTipoComissaoProduto().setValue(
					this.currentBean.getTipoComissaoProduto());
			this.subView.getTfObservacao().setValue(
					this.currentBean.getObservacao());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setCtpsDataExpedicao(this.subView
					.getPdfDataExpedida().getValue());
			this.currentBean.setCtpsSerie(this.subView.getTfSerie().getValue());
			this.currentBean.setCtpsNumero(this.subView.getTfNumeroCarteira()
					.getValue());
			this.currentBean.setPisAgenciaDigito(this.subView
					.getTfDigitoAgencia1().getValue());
			this.currentBean.setPisAgencia(this.subView.getTfAgencia1()
					.getValue());
			this.currentBean.setPisBanco(this.subView.getTfBanco1().getValue());
			this.currentBean.setPisNumero(this.subView.getTfNumeroPis()
					.getValue());
			this.currentBean.setPisDataCadastro(this.subView
					.getPdfDataCadastroPis().getValue());
			this.currentBean.setPagamentoContaDigito(this.subView
					.getTfDigitoConta().getValue());
			this.currentBean.setPagamentoConta(this.subView.getTfConta()
					.getValue());
			this.currentBean.setPagamentoAgenciaDigito(this.subView
					.getTfDigitoAgencia().getValue());
			this.currentBean.setPagamentoAgencia(this.subView.getTfAgencia()
					.getValue());
			this.currentBean.setPagamentoBanco(this.subView.getTfBanco()
					.getValue());

			FormaPagamentoEn formaPagamentoEn = (FormaPagamentoEn) this.subView
					.getCbFormaPagamento().getValue();

			if (ObjectUtils.isNotBlank(formaPagamentoEn)) {
				this.currentBean.setPagamentoForma(formaPagamentoEn);
			}

			String codigoDemissaoCaged = this.subView
					.getTfCodigoDemissaoCaged().getValue();

			if (NumberUtils.isNumber(codigoDemissaoCaged)) {
				this.currentBean.setCodigoDemissaoCaged(NumberUtils
						.toInt(codigoDemissaoCaged));
			}

			String codigoAdmissao = this.subView.getTfCodigoAdmissao()
					.getValue();

			if (NumberUtils.isNumber(codigoAdmissao)) {
				this.currentBean.setCodigoAdmissaoCaged(NumberUtils
						.toInt(codigoAdmissao));
			}

			this.currentBean.setFgtsDataOpcao(this.subView.getPdfDataOpcao()
					.getValue());

			SimNaoEn optanteEn = (SimNaoEn) this.subView.getCbOptante()
					.getValue();

			if (ObjectUtils.isNotBlank(optanteEn)) {
				this.currentBean.setFgtsOptante(optanteEn);
			}

			String codigoDemissaoSefip = this.subView.getTfCodigoDemissao()
					.getValue();

			if (NumberUtils.isNumber(codigoDemissaoSefip)) {
				this.currentBean.setCodigoDemissaoSefip(NumberUtils
						.toInt(codigoDemissaoSefip));
			}

			String ocorrenciaSefip = this.subView.getTfOcorrencia().getValue();

			if (NumberUtils.isNumber(ocorrenciaSefip)) {
				this.currentBean.setOcorrenciaSefip(NumberUtils
						.toInt(ocorrenciaSefip));
			}

			this.currentBean.setCategoriaSefip(this.subView.getTfCategoria()
					.getValue());

			String salarioFixo = this.subView.getTfSalarioFixo().getValue();

			if (NumberUtils.isNumber(salarioFixo)) {
				this.currentBean.setSalarioFixo(NumberUtils
						.createBigDecimal(salarioFixo));
			}

			String comissaoProduto = this.subView.getTfComissaoProduto()
					.getValue();

			if (NumberUtils.isNumber(comissaoProduto)) {
				this.currentBean.setValorComissaoProduto(NumberUtils
						.createBigDecimal(comissaoProduto));
			}

			String comissaoServico = this.subView.getTfComissaoServico()
					.getValue();

			if (NumberUtils.isNumber(comissaoServico)) {
				this.currentBean.setValorComissaoServico(NumberUtils
						.createBigDecimal(comissaoServico));
			}

			this.currentBean.setExameMedicoVencimento(this.subView
					.getPdfDataVencimento().getValue());
			this.currentBean.setExameMedicoUltimo(this.subView
					.getPdfDataUltimoExame().getValue());
			this.currentBean.setCodigoTurmaPonto(this.subView
					.getTfCodigoTurma().getValue());

			SimNaoEn saiRaisEn = (SimNaoEn) this.subView.getCbSaiRais()
					.getValue();

			if (ObjectUtils.isNotBlank(saiRaisEn)) {
				this.currentBean.setSaiNaRais(saiRaisEn);
			}

			SimNaoEn descontoPlanoSaudeEn = (SimNaoEn) this.subView
					.getCbDescontoPlanoSaude().getValue();

			if (ObjectUtils.isNotBlank(descontoPlanoSaudeEn)) {
				this.currentBean.setDescontoPlanoSaude(descontoPlanoSaudeEn);
			}

			this.currentBean.setDataDemissao(this.subView.getPdfDataDemissao()
					.getValue());
			this.currentBean.setDataTransferencia(this.subView
					.getPdfDataTransferencia().getValue());
			this.currentBean.setVencimentoFerias(this.subView
					.getPdfDataVencimentoFerias().getValue());
			this.currentBean.setDataAdmissao(this.subView.getPdfDataAdmissao()
					.getValue());
			this.currentBean.setDataCadastro(this.subView.getPdfDataCadastro()
					.getValue());
			this.currentBean.setMatricula(this.subView.getTfMatricula()
					.getValue());

			SimNaoEn priorizarComissaoEn = (SimNaoEn) this.subView
					.getCbPriorizarPgto().getValue();

			if (ObjectUtils.isNotBlank(priorizarComissaoEn)) {
				this.currentBean
						.setPriorizarComissao(priorizarComissaoEn == SimNaoEn.S ? true
								: false);
			}

			SimNaoEn comissaoOverEn = (SimNaoEn) this.subView
					.getCbComissaoOver().getValue();

			if (ObjectUtils.isNotBlank(comissaoOverEn)) {
				this.currentBean
						.setComissaoOver(comissaoOverEn == SimNaoEn.S ? true
								: false);
			}

			this.currentBean.setTipoComissaoServico((String) this.subView
					.getOgTipoComissaoServico().getValue());
			this.currentBean.setTipoComissaoProduto((String) this.subView
					.getOgTipoComissaoProduto().getValue());

			this.currentBean.setObservacao(this.subView.getTfObservacao()
					.getValue());

			this.colaboradorDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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
		
		try {
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}

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