package dc.controller.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.servicos.dao.nfe.NfeCabecalhoDAO;
import dc.servicos.dao.nfe.NfeDeclaracaoImportacaoDAO;
import dc.servicos.dao.nfe.NfeDetalheDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoCofinsDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIcmsDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIiDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIpiDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIssqnDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoPisDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.nfe.ProdutoServicoFormView;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class ProdutoServicoFormController extends
		CRUDFormController<NfeCabecalhoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoServicoFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private NfeCabecalhoDAO nfeCabecalhoDAO;

	@Autowired
	private NfeDetalheDAO nfeDetalheDAO;

	@Autowired
	private NfeDeclaracaoImportacaoDAO nfeDeclaracaoImportacaoDAO;

	@Autowired
	private NfeDetalheImpostoCofinsDAO nfeDetalheImpostoCofinsDAO;

	@Autowired
	private NfeDetalheImpostoIcmsDAO nfeDetalheImpostoIcmsDAO;

	@Autowired
	private NfeDetalheImpostoIiDAO nfeDetalheImpostoIiDAO;

	@Autowired
	private NfeDetalheImpostoIpiDAO nfeDetalheImpostoIpiDAO;

	@Autowired
	private NfeDetalheImpostoIssqnDAO nfeDetalheImpostoIssqnDAO;

	@Autowired
	private NfeDetalheImpostoPisDAO nfeDetalheImpostoPisDAO;

	/**
	 * ENTITIES
	 */

	private NfeCabecalhoEntity nfeCabecalho;

	private NfeDetalheEntity nfeDetalheSelecionado;

	/**
	 * CONSTRUTOR
	 */

	public ProdutoServicoFormController() {
		if (this.nfeCabecalho == null) {
			this.nfeCabecalho = new NfeCabecalhoEntity();
		}

		if (this.nfeDetalheSelecionado == null) {
			this.nfeDetalheSelecionado = new NfeDetalheEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Produto / serviço";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.nfeCabecalhoDAO.saveOrUpdate(this.nfeCabecalho);

			List auxLista = this.subView.getSfNfeDetalhe().getDados();

			if (auxLista != null && !auxLista.isEmpty()) {
				for (Object obj : auxLista) {
					NfeDetalheEntity ent = (NfeDetalheEntity) obj;
					ent.setNfeCabecalho(this.nfeCabecalho);

					this.nfeDetalheDAO.saveOrUpdate(ent);
				}
			}

			notifiyFrameworkSaveOK(this.nfeCabecalho);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new ProdutoServicoFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public void criarNovo() {
		try {
			super.criarNovo();

			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.nfeCabecalhoDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {
			// this.pDAO.deleteAllByIds(ids);
			this.nfeCabecalhoDAO.listarTodos(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	public String getViewIdentifier() {
		return "";
	}

	/**
	 * COMBOS
	 */

	/**
	 * **************************************
	 */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/**
	 * **************************************
	 */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.nfeCabecalho = new NfeCabecalhoEntity();
				this.nfeDetalheSelecionado = new NfeDetalheEntity();

				limparNdiCofins();
				limparNdiIcms();
				limparNdiIi();
				limparNdiIpi();
				limparNdiIssqn();
				limparNdiPis();
				limparNdeCombustivel();
				limparNdeVeiculo();
			} else {
				this.nfeCabecalho = this.nfeCabecalhoDAO.find(id);
			}

			montarNfeCabecalho();
			montarNfeDetalhe(id);

			this.subView.getGlIcms().setEnabled(false);
			this.subView.getGlPis().setEnabled(false);
			this.subView.getGlCofins().setEnabled(false);
			this.subView.getGlIpi().setEnabled(false);
			this.subView.getGlImpostoImportacao().setEnabled(false);
			this.subView.getGlIssqn().setEnabled(false);
			this.subView.getGlCombustivel().setEnabled(false);
			this.subView.getGlVeiculo().setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * **************************************
	 */

	private void montarNfeCabecalho() {
		try {
			// this.subView.getTfOperacaoFiscalId().setValue(nfeCabecalho);
			// this.subView.getTfOperacaoFiscal().setValue(nfeCabecalho.get);
			// this.subView.getTfVenda().setValue(nfeCabecalho.getVendaCabecalho().toString());
			this.subView.getTfModeloNotaFiscal().setValue(
					this.nfeCabecalho.getCodigoModelo().trim());
			this.subView.getTfNaturezaOperacao().setValue(
					this.nfeCabecalho.getNaturezaOperacao().trim());
			this.subView.getTfChaveAcesso().setValue(
					this.nfeCabecalho.getChaveAcesso().trim());
			this.subView.getTfDigitoChaveAcesso().setValue(
					this.nfeCabecalho.getDigitoChaveAcesso().trim());
			this.subView.getTfCodigoNumerico().setValue(
					this.nfeCabecalho.getCodigoNumerico().trim());
			this.subView.getTfSerie().setValue(
					this.nfeCabecalho.getSerie().trim());
			this.subView.getTfNumero().setValue(
					this.nfeCabecalho.getNumero().trim());
			this.subView.getPdfDataEmissao().setValue(
					this.nfeCabecalho.getDataEmissao());
			this.subView.getPdfDataEntradaSaida().setValue(
					this.nfeCabecalho.getDataEntradaSaida());
			this.subView.getTfHoraEntradaSaida().setValue(
					this.nfeCabecalho.getHoraEntradaSaida());
			this.subView.getTfTipoOperacao().setValue(
					this.nfeCabecalho.getTipoOperacao().trim());
			this.subView.getTfTipoEmissao().setValue(
					this.nfeCabecalho.getTipoEmissao().trim());
			this.subView.getTfFinalidadeEmissao().setValue(
					this.nfeCabecalho.getFinalidadeEmissao().trim());
			this.subView.getTfFormatoImpressaoDanfe().setValue(
					this.nfeCabecalho.getFormatoImpressaoDanfe().trim());
			this.subView.getTfFormaPagamento().setValue(
					this.nfeCabecalho.getIndicadorFormaPagamento().trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void montarNfeDetalhe(Serializable id) {
		try {
			List<NfeDetalheEntity> auxLista = new ArrayList<NfeDetalheEntity>();

			if (id != null && !id.equals(0)) {
				auxLista = this.nfeDetalheDAO.getLista(this.nfeCabecalho);
			}

			this.subView.carregarSfNfeDetalhe(auxLista);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * NFEDETALHE - ADICIONAR
	 */

	public NfeDetalheEntity adicionarNfeDetalhe() {
		try {
			NfeDetalheEntity ent = new NfeDetalheEntity();
			ent.setNfeCabecalho(this.nfeCabecalho);

			/**
			 * COFINS
			 */

			NfeDetalheImpostoCofinsEntity ndiCofins = new NfeDetalheImpostoCofinsEntity();
			ndiCofins.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoCofins(ndiCofins);

			/**
			 * ICMS
			 */

			NfeDetalheImpostoIcmsEntity ndiIcms = new NfeDetalheImpostoIcmsEntity();
			ndiIcms.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoIcms(ndiIcms);

			/**
			 * IMPOSTO IMPORTAÇÃO
			 */

			NfeDetalheImpostoIiEntity ndiIi = new NfeDetalheImpostoIiEntity();
			ndiIi.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoIi(ndiIi);

			/**
			 * IPI
			 */

			// NfeDetalheImpostoIpiEntity ndiIpi = new
			// NfeDetalheImpostoIpiEntity();

			/**
			 * ISSQN
			 */

			NfeDetalheImpostoIssqnEntity ndiIssqn = new NfeDetalheImpostoIssqnEntity();
			ndiIssqn.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoIssqn(ndiIssqn);

			/**
			 * PIS
			 */

			NfeDetalheImpostoPisEntity ndiPis = new NfeDetalheImpostoPisEntity();
			ndiPis.setNfeDetalhe(ent);

			ent.setNfeDetalheImpostoPis(ndiPis);

			/**
			 * COMBUSTÍVEL
			 */

			NfeDetEspecificoCombustivelEntity ndeCombustivel = new NfeDetEspecificoCombustivelEntity();
			ndeCombustivel.setNfeDetalhe(ent);

			ent.setNfeDetEspecificoCombustivel(ndeCombustivel);

			/**
			 * VEÍCULO
			 */

			NfeDetEspecificoVeiculoEntity ndeVeiculo = new NfeDetEspecificoVeiculoEntity();
			ndeVeiculo.setNfeDetalhe(ent);

			ent.setNfeDetEspecificoVeiculo(ndeVeiculo);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return new NfeDetalheEntity();
		}
	}

	/**
	 * NFEDETALHE - SELECIONAR
	 */

	public void selecionarNfeDetalhe(NfeDetalheEntity item) {
		try {
			this.nfeDetalheSelecionado = item;

			/**
			 * COFINS
			 */

			NfeDetalheImpostoCofinsEntity entCofins = item
					.getNfeDetalheImpostoCofins();

			this.subView.getTfCstCofins().setValue(
					entCofins.getCstCofins().trim());
			this.subView.getTfQtdVendidaCofins().setValue(
					entCofins.getQuantidadeVendida().toString().trim());
			this.subView.getTfBaseCalculoBcCofins().setValue(
					entCofins.getBaseCalculoCofins().toString().trim());
			this.subView.getTfAliquotaPercentualCofins().setValue(
					entCofins.getAliquotaCofinsPercentual().toString().trim());
			this.subView.getTfAliquotaReaisCofins().setValue(
					entCofins.getAliquotaCofinsReais().toString().trim());
			this.subView.getTfValorCofins().setValue(
					entCofins.getValorCofins().toString().trim());

			/**
			 * ICMS
			 */

			NfeDetalheImpostoIcmsEntity entIcms = item
					.getNfeDetalheImpostoIcms();

			this.subView.getTfOrigemMercadoriaIcms().setValue(
					entIcms.getOrigemMercadoria().trim());
			this.subView.getTfCstIcms().setValue(entIcms.getCstIcms().trim());
			this.subView.getTfCsosnIcms().setValue(entIcms.getCsosn().trim());
			this.subView.getTfModalidadeBcIcms().setValue(
					entIcms.getModalidadeBcIcms().trim());
			this.subView.getTfTaxaReducaoBcIcms().setValue(
					entIcms.getTaxaReducaoBcIcms().toString().trim());
			this.subView.getTfBaseCalculoBcIcms().setValue(
					entIcms.getBaseCalculoIcms().toString().trim());
			this.subView.getTfAliquotaIcms().setValue(
					entIcms.getAliquotaIcms().toString().trim());
			this.subView.getTfValorIcms().setValue(
					entIcms.getValorIcms().toString().trim());
			this.subView.getTfMotivoDesoneracaoIcms().setValue(
					entIcms.getMotivoDesoneracaoIcms().trim());
			this.subView.getTfModalidadeBcStIcms().setValue(
					entIcms.getModalidadeBcIcmsSt().trim());
			this.subView.getTfPercentualMvaStIcms().setValue(
					entIcms.getPercentualMvaIcmsSt().toString().trim());
			// this.subView.getTfTaxaReducaoBcStIcms().setValue(entIcms.get);
			this.subView.getTfBaseCalculoStIcms().setValue(
					entIcms.getValorBaseCalculoIcmsSt().toString().trim());
			this.subView.getTfAliquotaStIcms().setValue(
					entIcms.getAliquotaIcmsSt().toString().trim());
			this.subView.getTfValorStIcms().setValue(
					entIcms.getValorIcmsSt().toString().trim());
			this.subView.getTfBcStRetidoIcms().setValue(
					entIcms.getValorBcIcmsStRetido().toString().trim());
			this.subView.getTfValorStRetidoIcms().setValue(
					entIcms.getValorIcmsStRetido().toString().trim());
			this.subView.getTfBcStDestinoIcms().setValue(
					entIcms.getValorBcIcmsStDestino().toString().trim());
			this.subView.getTfValorStDestinoIcms().setValue(
					entIcms.getValorIcmsStDestino().toString().trim());
			this.subView.getTfAliquotaCreditoSnIcms().setValue(
					entIcms.getAliquotaCreditoIcmsSn().toString().trim());
			this.subView.getTfValorCreditoSnIcms().setValue(
					entIcms.getValorCreditoIcmsSn().toString().trim());
			this.subView.getTfPercentualBcOperacaoPropriaIcms().setValue(
					entIcms.getPercentualBcOperacaoPropria().toString().trim());
			this.subView.getTfUfStIcms().setValue(entIcms.getUfSt().trim());

			/**
			 * IMPOSTO IMPORTAÇÃO
			 */

			NfeDetalheImpostoIiEntity entIi = item.getNfeDetalheImpostoIi();

			this.subView.getTfBaseCalculoBcImpostoImportacao().setValue(
					entIi.getValorBcIi().toString().trim());
			this.subView.getTfDespesasAduaneirasImpostoImportacao().setValue(
					entIi.getValorDespesasAduaneiras().toString().trim());
			this.subView.getTfValorImpostoImportacao().setValue(
					entIi.getValorImpostoImportacao().toString().trim());
			this.subView.getTfIofImpostoImportacao().setValue(
					entIi.getValorIof().toString().trim());

			/**
			 * IPI
			 */

			// NfeDetalheImpostoIpiEntity ndiIpi = new
			// NfeDetalheImpostoIpiEntity();

			/**
			 * ISSQN
			 */

			NfeDetalheImpostoIssqnEntity entIssqn = item
					.getNfeDetalheImpostoIssqn();

			this.subView.getTfBaseCalculoBcIssqn().setValue(
					entIssqn.getBaseCalculoIssqn().toString().trim());
			this.subView.getTfAliquotaIssqn().setValue(
					entIssqn.getAliquotaIssqn().toString().trim());
			this.subView.getTfValorIssqn().setValue(
					entIssqn.getValorIssqn().toString().trim());
			this.subView.getTfMunicipioIssqn().setValue(
					entIssqn.getMunicipioIssqn().toString().trim());
			this.subView.getTfItemListaServicosIssqn().setValue(
					entIssqn.getItemListaServicos().toString().trim());
			this.subView.getTfTributacaoIssqn().setValue(
					entIssqn.getTributacaoIssqn().trim());

			/**
			 * PIS
			 */

			NfeDetalheImpostoPisEntity entPis = item.getNfeDetalheImpostoPis();

			this.subView.getTfCstPis().setValue(entPis.getCstPis().trim());
			this.subView.getTfQtdVendidaPis().setValue(
					entPis.getQuantidadeVendida().toString().trim());
			this.subView.getTfBaseCalculoBcPis().setValue(
					entPis.getValorBaseCalculoPis().toString().trim());
			this.subView.getTfAliquotaPercentualPis().setValue(
					entPis.getAliquotaPisPercentual().toString().trim());
			this.subView.getTfAliquotaReaisPis().setValue(
					entPis.getAliquotaPisReais().toString().trim());
			this.subView.getTfValorPis().setValue(
					entPis.getValorPis().toString().trim());

			/**
			 * COMBUSTÍVEL
			 */

			NfeDetEspecificoCombustivelEntity entCombustivel = item
					.getNfeDetEspecificoCombustivel();

			this.subView.getTfCodigoAnpCombustivel().setValue(
					entCombustivel.getCodigoAnp().toString().trim());
			this.subView.getTfCodifCombustivel().setValue(
					entCombustivel.getCodif().trim());
			this.subView.getTfQtdeTempAmbienteCombustivel().setValue(
					entCombustivel.getQuantidadeTempAmbiente().toString()
							.trim());
			this.subView.getTfUfConsumoCombustivel().setValue(
					entCombustivel.getUfConsumo().trim());
			this.subView.getTfBcCideCombustivel().setValue(
					entCombustivel.getBaseCalculoCide().toString().trim());
			this.subView.getTfAliquotaCideCombustivel().setValue(
					entCombustivel.getAliquotaCide().toString().trim());
			this.subView.getTfValorCideCombustivel().setValue(
					entCombustivel.getValorCide().toString().trim());

			/**
			 * COMBUSTÍVEL
			 */

			NfeDetEspecificoVeiculoEntity entVeiculo = item
					.getNfeDetEspecificoVeiculo();

			this.subView.getTfTipoOperacaoVeiculo().setValue(
					entVeiculo.getTipoOperacao());
			this.subView.getTfChassiVeiculo().setValue(entVeiculo.getChassi());
			this.subView.getTfCodigoCorVeiculo().setValue(
					entVeiculo.getCodigoCor());
			this.subView.getTfDescricaoCorVeiculo().setValue(
					entVeiculo.getDescricaoCor());
			this.subView.getTfPotenciaMotorVeiculo().setValue(
					entVeiculo.getPotenciaMotor());
			this.subView.getTfCilindradasVeiculo().setValue(
					entVeiculo.getCilindradas());
			this.subView.getTfPesoLiquidoVeiculo().setValue(
					entVeiculo.getPesoLiquido());
			this.subView.getTfPesoBrutoVeiculo().setValue(
					entVeiculo.getPesoBruto());
			this.subView.getTfNumeroSerieVeiculo().setValue(
					entVeiculo.getNumeroSerie());
			this.subView.getTfCombustivelVeiculo().setValue(
					entVeiculo.getTipoCombustivel());
			this.subView.getTfNumeroMotorVeiculo().setValue(
					entVeiculo.getNumeroMotor());
			this.subView.getTfCapacidadeTracaoVeiculo().setValue(
					entVeiculo.getCapacidadeMaximaTracao());
			this.subView.getTfDistanciaEixosVeiculo().setValue(
					entVeiculo.getDistanciaEixos());
			this.subView.getTfAnoModeloVeiculo().setValue(
					entVeiculo.getAnoModelo());
			this.subView.getTfAnoFabricacaoVeiculo().setValue(
					entVeiculo.getAnoFabricacao());
			this.subView.getTfTipoPinturaVeiculo().setValue(
					entVeiculo.getTipoPintura());
			this.subView.getTfTipoVeiculo().setValue(
					entVeiculo.getTipoVeiculo());
			this.subView.getTfEspecieVeiculo().setValue(
					entVeiculo.getEspecieVeiculo());
			this.subView.getTfCondicaoVinVeiculo().setValue(
					entVeiculo.getCondicaoVin());
			this.subView.getTfCondicaoVeiculo().setValue(
					entVeiculo.getCondicaoVeiculo());
			this.subView.getTfCodigoMarcaModeloVeiculo().setValue(
					entVeiculo.getCodigoMarcaModelo());
			this.subView.getTfCodigoCorDenatranVeiculo().setValue(
					entVeiculo.getCor());
			this.subView.getTfLotacaoVeiculo().setValue(
					entVeiculo.getLotacao().toString());
			this.subView.getTfRestricaoVeiculo().setValue(
					entVeiculo.getRestricao());

			this.subView.getGlIcms().setEnabled(true);
			this.subView.getGlPis().setEnabled(true);
			this.subView.getGlCofins().setEnabled(true);
			this.subView.getGlIpi().setEnabled(true);
			this.subView.getGlImpostoImportacao().setEnabled(true);
			this.subView.getGlIssqn().setEnabled(true);
			this.subView.getGlCombustivel().setEnabled(true);
			this.subView.getGlVeiculo().setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ADICIONAR IMPOSTOS
	 * 
	 * @param id
	 * @param event
	 */

	public void adicionarNfeCabecalho(String id, ValueChangeEvent event) {
		if (this.nfeCabecalho == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfOperacaoFiscalId":
			// this.nfeCabecalho.set

			break;
		case "tfOperacaoFiscal":
			// this.nfeCabecalho.set

			break;
		case "tfVenda":
			// this.nfeCabecalho.set

			break;
		case "tfModeloNotaFiscal":
			this.nfeCabecalho.setCodigoModelo(s.toString().trim());

			break;
		case "tfNaturezaOperacao":
			this.nfeCabecalho.setNaturezaOperacao(s.toString().trim());

			break;
		case "tfChaveAcesso":
			this.nfeCabecalho.setChaveAcesso(s.toString().trim());

			break;
		case "tfDigitoChaveAcesso":
			this.nfeCabecalho.setDigitoChaveAcesso(s.toString().trim());

			break;
		case "tfCodigoNumerico":
			this.nfeCabecalho.setCodigoNumerico(s.toString().trim());

			break;
		case "tfSerie":
			this.nfeCabecalho.setSerie(s.toString().trim());

			break;
		case "tfNumero":
			this.nfeCabecalho.setNumero(s.toString().trim());

			break;
		case "pdfDataEmissao":
			this.nfeCabecalho.setDataEmissao((Date) s);

			break;
		case "pdfDataEntradaSaida":
			this.nfeCabecalho.setDataEntradaSaida((Date) s);

			break;
		case "pdfHoraEntradaSaida":
			this.nfeCabecalho.setHoraEntradaSaida(s.toString().trim());

			break;
		case "tfTipoOperacao":
			this.nfeCabecalho.setTipoOperacao(s.toString().trim());

			break;
		case "tfTipoEmissao":
			this.nfeCabecalho.setTipoEmissao(s.toString().trim());

			break;
		case "tfFinalidadeEmissao":
			this.nfeCabecalho.setFinalidadeEmissao(s.toString().trim());

			break;
		case "tfFormatoImpressaoDanfe":
			this.nfeCabecalho.setFormatoImpressaoDanfe(s.toString().trim());

			break;
		case "tfFormaPagamento":
			this.nfeCabecalho.setIndicadorFormaPagamento(s.toString().trim());

			break;
		}
	}

	public void adicionarCofins(String id, ValueChangeEvent event) {
		NfeDetalheImpostoCofinsEntity ndiCofins = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoCofins();

		if (ndiCofins == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfCstCofins":
			ndiCofins.setCstCofins(s.toString().trim());

			break;
		case "tfQtdVendidaCofins":
			ndiCofins.setQuantidadeVendida(new BigDecimal(s.toString().trim()));

			break;
		case "tfBaseCalculoBcCofins":
			ndiCofins.setBaseCalculoCofins(new BigDecimal(s.toString().trim()));

			break;
		case "tfAliquotaPercentualCofins":
			ndiCofins.setAliquotaCofinsPercentual(new BigDecimal(s.toString()
					.trim()));

			break;
		case "tfAliquotaReaisCofins":
			ndiCofins
					.setAliquotaCofinsReais(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorCofins":
			ndiCofins.setValorCofins(new BigDecimal(s.toString().trim()));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado.setNfeDetalheImpostoCofins(ndiCofins);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarIcms(String id, ValueChangeEvent event) {
		NfeDetalheImpostoIcmsEntity ndiIcms = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoIcms();

		if (ndiIcms == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfOrigemMercadoriaIcms":
			ndiIcms.setOrigemMercadoria(s.toString().trim());

			break;
		case "tfCstIcms":
			ndiIcms.setCstIcms(s.toString().trim());

			break;
		case "tfCsosnIcms":
			ndiIcms.setCsosn(s.toString().trim());

			break;
		case "tfModalidadeBcIcms":
			ndiIcms.setModalidadeBcIcms(s.toString().trim());

			break;
		case "tfTaxaReducaoBcIcms":
			ndiIcms.setTaxaReducaoBcIcms(new BigDecimal(s.toString().trim()));

			break;
		case "tfBaseCalculoBcIcms":
			ndiIcms.setBaseCalculoIcms(new BigDecimal(s.toString().trim()));

			break;
		case "tfAliquotaIcms":
			ndiIcms.setAliquotaIcms(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorIcms":
			ndiIcms.setValorIcms(new BigDecimal(s.toString().trim()));

			break;
		case "tfMotivoDesoneracaoIcms":
			ndiIcms.setMotivoDesoneracaoIcms(s.toString().trim());

			break;
		case "tfModalidadeBcStIcms":
			ndiIcms.setModalidadeBcIcmsSt(s.toString().trim());

			break;
		case "tfPercentualMvaStIcms":
			ndiIcms.setPercentualMvaIcmsSt(new BigDecimal(s.toString().trim()));

			break;
		case "tfTaxaReducaoBcStIcms":
			ndiIcms.setPercentualReducaoBcIcmsSt(new BigDecimal(s.toString()
					.trim()));

			break;
		case "tfBaseCalculoStIcms":
			ndiIcms.setValorBaseCalculoIcmsSt(new BigDecimal(s.toString()
					.trim()));

			break;
		case "tfAliquotaStIcms":
			ndiIcms.setAliquotaIcmsSt(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorStIcms":
			ndiIcms.setValorIcmsSt(new BigDecimal(s.toString().trim()));

			break;
		case "tfBcStRetidoIcms":
			ndiIcms.setValorBcIcmsStRetido(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorStRetidoIcms":
			ndiIcms.setValorIcmsStRetido(new BigDecimal(s.toString().trim()));

			break;
		case "tfBcStDestinoIcms":
			ndiIcms.setValorBcIcmsStDestino(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorStDestinoIcms":
			ndiIcms.setValorIcmsStDestino(new BigDecimal(s.toString().trim()));

			break;
		case "tfAliquotaCreditoSnIcms":
			ndiIcms.setAliquotaCreditoIcmsSn(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorCreditoSnIcms":
			ndiIcms.setValorCreditoIcmsSn(new BigDecimal(s.toString().trim()));

			break;
		case "tfPercentualBcOperacaoPropriaIcms":
			ndiIcms.setPercentualBcOperacaoPropria(new BigDecimal(s.toString()
					.trim()));

			break;
		case "tfUfStIcms":
			ndiIcms.setUfSt(s.toString().trim());

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado.setNfeDetalheImpostoIcms(ndiIcms);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarIi(String id, ValueChangeEvent event) {
		NfeDetalheImpostoIiEntity ndiIi = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoIi();

		if (ndiIi == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfBaseCalculoBcImpostoImportacao":
			ndiIi.setValorBcIi(new BigDecimal(s.toString().trim()));

			break;
		case "tfDespesasAduaneirasImpostoImportacao":
			ndiIi.setValorDespesasAduaneiras(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorImpostoImportacao":
			ndiIi.setValorImpostoImportacao(new BigDecimal(s.toString().trim()));

			break;
		case "tfIofImpostoImportacao":
			ndiIi.setValorIof(new BigDecimal(s.toString().trim()));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado.setNfeDetalheImpostoIi(ndiIi);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarIpi(String id, ValueChangeEvent event) {
		// NfeDetalheImpostoIpiEntity ndiIpi = this.nfeDetalheSelecionado
		// .getNfeDetalheImpostoIpi();

		Object s = event.getProperty().getValue();

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		// this.nfeDetalheSelecionado.setNfeDetalheImpostoIpi(ndiIpi);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarIssqn(String id, ValueChangeEvent event) {
		NfeDetalheImpostoIssqnEntity ndiIssqn = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoIssqn();

		if (ndiIssqn == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfBaseCalculoBcIssqn":
			ndiIssqn.setBaseCalculoIssqn(new BigDecimal(s.toString().trim()));

			break;
		case "tfAliquotaIssqn":
			ndiIssqn.setAliquotaIssqn(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorIssqn":
			ndiIssqn.setValorIssqn(new BigDecimal(s.toString().trim()));

			break;
		case "tfMunicipioIssqn":
			ndiIssqn.setMunicipioIssqn(new Integer(s.toString().trim()));

			break;
		case "tfItemListaServicosIssqn":
			ndiIssqn.setItemListaServicos(new Integer(s.toString().trim()));

			break;
		case "tfTributacaoIssqn":
			ndiIssqn.setTributacaoIssqn(s.toString().trim());

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado.setNfeDetalheImpostoIssqn(ndiIssqn);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarPis(String id, ValueChangeEvent event) {
		NfeDetalheImpostoPisEntity ndiPis = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoPis();

		if (ndiPis == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfCstPis":
			ndiPis.setCstPis(s.toString().trim());

			break;
		case "tfQtdVendidaPis":
			ndiPis.setQuantidadeVendida(new BigDecimal(s.toString().trim()));

			break;
		case "tfBaseCalculoBcPis":
			ndiPis.setValorBaseCalculoPis(new BigDecimal(s.toString().trim()));

			break;
		case "tfAliquotaPercentualPis":
			ndiPis.setAliquotaPisPercentual(new BigDecimal(s.toString().trim()));

			break;
		case "tfAliquotaReaisPis":
			ndiPis.setAliquotaPisReais(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorPis":
			ndiPis.setValorPis(new BigDecimal(s.toString().trim()));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado.setNfeDetalheImpostoPis(ndiPis);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarCombustivel(String id, ValueChangeEvent event) {
		NfeDetEspecificoCombustivelEntity ndiCombustivel = this.nfeDetalheSelecionado
				.getNfeDetEspecificoCombustivel();

		if (ndiCombustivel == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfCodigoAnpCombustivel":
			ndiCombustivel.setCodigoAnp(new Integer(s.toString().trim()));

			break;
		case "tfCodifCombustivel":
			ndiCombustivel.setCodif(s.toString().trim());

			break;
		case "tfQtdeTempAmbienteCombustivel":
			ndiCombustivel.setQuantidadeTempAmbiente(new BigDecimal(s
					.toString().trim()));

			break;
		case "tfUfConsumoCombustivel":
			ndiCombustivel.setUfConsumo(s.toString().trim());

			break;
		case "tfBcCideCombustivel":
			ndiCombustivel.setBaseCalculoCide(new BigDecimal(s.toString()
					.trim()));

			break;
		case "tfAliquotaCideCombustivel":
			ndiCombustivel.setAliquotaCide(new BigDecimal(s.toString().trim()));

			break;
		case "tfValorCideCombustivel":
			ndiCombustivel.setValorCide(new BigDecimal(s.toString().trim()));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado
				.setNfeDetEspecificoCombustivel(ndiCombustivel);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	public void adicionarVeiculo(String id, ValueChangeEvent event) {
		NfeDetEspecificoVeiculoEntity ndiVeiculo = this.nfeDetalheSelecionado
				.getNfeDetEspecificoVeiculo();

		if (ndiVeiculo == null) {
			return;
		}

		Object s = event.getProperty().getValue();

		switch (id) {
		case "tfTipoOperacaoVeiculo":
			ndiVeiculo.setTipoOperacao(s.toString().trim());

			break;
		case "tfChassiVeiculo":
			ndiVeiculo.setChassi(s.toString().trim());

			break;
		case "tfCodigoCorVeiculo":
			ndiVeiculo.setCodigoCor(s.toString().trim());

			break;
		case "tfDescricaoCorVeiculo":
			ndiVeiculo.setDescricaoCor(s.toString().trim());

			break;
		case "tfPotenciaMotorVeiculo":
			ndiVeiculo.setPotenciaMotor(s.toString().trim());

			break;
		case "tfCilindradasVeiculo":
			ndiVeiculo.setCilindradas(s.toString().trim());

			break;
		case "tfPesoLiquidoVeiculo":
			ndiVeiculo.setPesoLiquido(s.toString().trim());

			break;
		case "tfPesoBrutoVeiculo":
			ndiVeiculo.setPesoBruto(s.toString().trim());

			break;
		case "tfNumeroSerieVeiculo":
			ndiVeiculo.setNumeroSerie(s.toString().trim());

			break;
		case "tfCombustivelVeiculo":
			ndiVeiculo.setTipoCombustivel(s.toString().trim());

			break;
		case "tfNumeroMotorVeiculo":
			ndiVeiculo.setNumeroMotor(s.toString().trim());

			break;
		case "tfCapacidadeTracaoVeiculo":
			ndiVeiculo.setCapacidadeMaximaTracao(s.toString().trim());

			break;
		case "tfDistanciaEixosVeiculo":
			ndiVeiculo.setDistanciaEixos(s.toString().trim());

			break;
		case "tfAnoModeloVeiculo":
			ndiVeiculo.setAnoModelo(s.toString().trim());

			break;
		case "tfAnoFabricacaoVeiculo":
			ndiVeiculo.setAnoFabricacao(s.toString().trim());

			break;
		case "tfTipoPinturaVeiculo":
			ndiVeiculo.setTipoPintura(s.toString().trim());

			break;
		case "tfTipoVeiculo":
			ndiVeiculo.setTipoVeiculo(s.toString().trim());

			break;
		case "tfEspecieVeiculo":
			ndiVeiculo.setEspecieVeiculo(s.toString().trim());

			break;
		case "tfCondicaoVinVeiculo":
			ndiVeiculo.setCondicaoVin(s.toString().trim());

			break;
		case "tfCondicaoVeiculo":
			ndiVeiculo.setCondicaoVeiculo(s.toString().trim());

			break;
		case "tfCodigoMarcaModeloVeiculo":
			ndiVeiculo.setCodigoMarcaModelo(s.toString().trim());

			break;
		case "tfCodigoCorDenatranVeiculo":
			ndiVeiculo.setCor(s.toString().trim());

			break;
		case "tfLotacaoVeiculo":
			ndiVeiculo.setLotacao(new Integer(s.toString().trim()));

			break;
		case "tfRestricaoVeiculo":
			ndiVeiculo.setRestricao(s.toString().trim());

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeDetalheSelecionado);

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeDetalheSelecionado);

		this.nfeDetalheSelecionado.setNfeDetEspecificoVeiculo(ndiVeiculo);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeDetalheSelecionado);
	}

	/**
	 * LIMPAR
	 * 
	 * @param item
	 */

	private void limparNdiCofins() {
		NfeDetalheImpostoCofinsEntity entCofins = new NfeDetalheImpostoCofinsEntity();

		this.subView.getTfCstCofins().setValue(entCofins.getCstCofins());
		this.subView.getTfQtdVendidaCofins().setValue(
				entCofins.getQuantidadeVendida().toString());
		this.subView.getTfBaseCalculoBcCofins().setValue(
				entCofins.getBaseCalculoCofins().toString());
		this.subView.getTfAliquotaPercentualCofins().setValue(
				entCofins.getAliquotaCofinsPercentual().toString());
		this.subView.getTfAliquotaReaisCofins().setValue(
				entCofins.getAliquotaCofinsReais().toString());
		this.subView.getTfValorCofins().setValue(
				entCofins.getValorCofins().toString());
	}

	private void limparNdiIcms() {
		NfeDetalheImpostoIcmsEntity entIcms = new NfeDetalheImpostoIcmsEntity();

		this.subView.getTfOrigemMercadoriaIcms().setValue(
				entIcms.getOrigemMercadoria());
		this.subView.getTfCstIcms().setValue(entIcms.getCstIcms());
		this.subView.getTfCsosnIcms().setValue(entIcms.getCsosn());
		this.subView.getTfModalidadeBcIcms().setValue(
				entIcms.getModalidadeBcIcms());
		this.subView.getTfTaxaReducaoBcIcms().setValue(
				entIcms.getTaxaReducaoBcIcms().toString());
		this.subView.getTfBaseCalculoBcIcms().setValue(
				entIcms.getBaseCalculoIcms().toString());
		this.subView.getTfAliquotaIcms().setValue(
				entIcms.getAliquotaIcms().toString());
		this.subView.getTfValorIcms().setValue(
				entIcms.getValorIcms().toString());
		this.subView.getTfMotivoDesoneracaoIcms().setValue(
				entIcms.getMotivoDesoneracaoIcms());
		this.subView.getTfModalidadeBcStIcms().setValue(
				entIcms.getModalidadeBcIcmsSt());
		this.subView.getTfPercentualMvaStIcms().setValue(
				entIcms.getPercentualMvaIcmsSt().toString());
		// this.subView.getTfTaxaReducaoBcStIcms().setValue(entIcms.get);
		this.subView.getTfBaseCalculoStIcms().setValue(
				entIcms.getValorBaseCalculoIcmsSt().toString());
		this.subView.getTfAliquotaStIcms().setValue(
				entIcms.getAliquotaIcmsSt().toString());
		this.subView.getTfValorStIcms().setValue(
				entIcms.getValorIcmsSt().toString());
		this.subView.getTfBcStRetidoIcms().setValue(
				entIcms.getValorBcIcmsStRetido().toString());
		this.subView.getTfValorStRetidoIcms().setValue(
				entIcms.getValorIcmsStRetido().toString());
		this.subView.getTfBcStDestinoIcms().setValue(
				entIcms.getValorBcIcmsStDestino().toString());
		this.subView.getTfValorStDestinoIcms().setValue(
				entIcms.getValorIcmsStDestino().toString());
		this.subView.getTfAliquotaCreditoSnIcms().setValue(
				entIcms.getAliquotaCreditoIcmsSn().toString());
		this.subView.getTfValorCreditoSnIcms().setValue(
				entIcms.getValorCreditoIcmsSn().toString());
		this.subView.getTfPercentualBcOperacaoPropriaIcms().setValue(
				entIcms.getPercentualBcOperacaoPropria().toString());
		this.subView.getTfUfStIcms().setValue(entIcms.getUfSt());
	}

	private void limparNdiIi() {
		NfeDetalheImpostoIiEntity entIi = new NfeDetalheImpostoIiEntity();

		this.subView.getTfBaseCalculoBcImpostoImportacao().setValue(
				entIi.getValorBcIi().toString());
		this.subView.getTfDespesasAduaneirasImpostoImportacao().setValue(
				entIi.getValorDespesasAduaneiras().toString());
		this.subView.getTfValorImpostoImportacao().setValue(
				entIi.getValorImpostoImportacao().toString());
		this.subView.getTfIofImpostoImportacao().setValue(
				entIi.getValorIof().toString());
	}

	private void limparNdiIpi() {

	}

	private void limparNdiIssqn() {
		NfeDetalheImpostoIssqnEntity entIssqn = new NfeDetalheImpostoIssqnEntity();

		this.subView.getTfBaseCalculoBcIssqn().setValue(
				entIssqn.getBaseCalculoIssqn().toString());
		this.subView.getTfAliquotaIssqn().setValue(
				entIssqn.getAliquotaIssqn().toString());
		this.subView.getTfValorIssqn().setValue(
				entIssqn.getValorIssqn().toString());
		this.subView.getTfMunicipioIssqn().setValue(
				entIssqn.getMunicipioIssqn().toString());
		this.subView.getTfItemListaServicosIssqn().setValue(
				entIssqn.getItemListaServicos().toString());
		this.subView.getTfTributacaoIssqn().setValue(
				entIssqn.getTributacaoIssqn());
	}

	private void limparNdiPis() {
		NfeDetalheImpostoPisEntity entPis = new NfeDetalheImpostoPisEntity();

		this.subView.getTfCstPis().setValue(entPis.getCstPis());
		this.subView.getTfQtdVendidaPis().setValue(
				entPis.getQuantidadeVendida().toString());
		this.subView.getTfBaseCalculoBcPis().setValue(
				entPis.getValorBaseCalculoPis().toString());
		this.subView.getTfAliquotaPercentualPis().setValue(
				entPis.getAliquotaPisPercentual().toString());
		this.subView.getTfAliquotaReaisPis().setValue(
				entPis.getAliquotaPisReais().toString());
		this.subView.getTfValorPis().setValue(entPis.getValorPis().toString());
	}

	private void limparNdeCombustivel() {
		NfeDetEspecificoCombustivelEntity entCombustivel = new NfeDetEspecificoCombustivelEntity();

		this.subView.getTfCodigoAnpCombustivel().setValue(
				entCombustivel.getCodigoAnp().toString());
		this.subView.getTfCodifCombustivel()
				.setValue(entCombustivel.getCodif());
		this.subView.getTfQtdeTempAmbienteCombustivel().setValue(
				entCombustivel.getQuantidadeTempAmbiente().toString());
		this.subView.getTfUfConsumoCombustivel().setValue(
				entCombustivel.getUfConsumo());
		this.subView.getTfBcCideCombustivel().setValue(
				entCombustivel.getBaseCalculoCide().toString());
		this.subView.getTfAliquotaCideCombustivel().setValue(
				entCombustivel.getAliquotaCide().toString());
		this.subView.getTfValorCideCombustivel().setValue(
				entCombustivel.getValorCide().toString());
	}

	private void limparNdeVeiculo() {
		NfeDetEspecificoVeiculoEntity entVeiculo = new NfeDetEspecificoVeiculoEntity();

		this.subView.getTfTipoOperacaoVeiculo().setValue(
				entVeiculo.getTipoOperacao());
		this.subView.getTfChassiVeiculo().setValue(entVeiculo.getChassi());
		this.subView.getTfCodigoCorVeiculo()
				.setValue(entVeiculo.getCodigoCor());
		this.subView.getTfDescricaoCorVeiculo().setValue(
				entVeiculo.getDescricaoCor());
		this.subView.getTfPotenciaMotorVeiculo().setValue(
				entVeiculo.getPotenciaMotor());
		this.subView.getTfCilindradasVeiculo().setValue(
				entVeiculo.getCilindradas());
		this.subView.getTfPesoLiquidoVeiculo().setValue(
				entVeiculo.getPesoLiquido());
		this.subView.getTfPesoBrutoVeiculo()
				.setValue(entVeiculo.getPesoBruto());
		this.subView.getTfNumeroSerieVeiculo().setValue(
				entVeiculo.getNumeroSerie());
		this.subView.getTfCombustivelVeiculo().setValue(
				entVeiculo.getTipoCombustivel());
		this.subView.getTfNumeroMotorVeiculo().setValue(
				entVeiculo.getNumeroMotor());
		this.subView.getTfCapacidadeTracaoVeiculo().setValue(
				entVeiculo.getCapacidadeMaximaTracao());
		this.subView.getTfDistanciaEixosVeiculo().setValue(
				entVeiculo.getDistanciaEixos());
		this.subView.getTfAnoModeloVeiculo()
				.setValue(entVeiculo.getAnoModelo());
		this.subView.getTfAnoFabricacaoVeiculo().setValue(
				entVeiculo.getAnoFabricacao());
		this.subView.getTfTipoPinturaVeiculo().setValue(
				entVeiculo.getTipoPintura());
		this.subView.getTfTipoVeiculo().setValue(entVeiculo.getTipoVeiculo());
		this.subView.getTfEspecieVeiculo().setValue(
				entVeiculo.getEspecieVeiculo());
		this.subView.getTfCondicaoVinVeiculo().setValue(
				entVeiculo.getCondicaoVin());
		this.subView.getTfCondicaoVeiculo().setValue(
				entVeiculo.getCondicaoVeiculo());
		this.subView.getTfCodigoMarcaModeloVeiculo().setValue(
				entVeiculo.getCodigoMarcaModelo());
		this.subView.getTfCodigoCorDenatranVeiculo().setValue(
				entVeiculo.getCor());
		this.subView.getTfLotacaoVeiculo().setValue(
				entVeiculo.getLotacao().toString());
		this.subView.getTfRestricaoVeiculo()
				.setValue(entVeiculo.getRestricao());
	}

}