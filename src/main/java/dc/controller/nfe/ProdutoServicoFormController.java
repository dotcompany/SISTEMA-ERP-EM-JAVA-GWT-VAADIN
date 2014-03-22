package dc.controller.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;

import dc.entidade.nfe.NfeCabecalhoEntity;
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
					this.nfeCabecalho.getCodigoModelo());
			this.subView.getTfNaturezaOperacao().setValue(
					this.nfeCabecalho.getNaturezaOperacao());
			this.subView.getTfChaveAcesso().setValue(
					this.nfeCabecalho.getChaveAcesso());
			this.subView.getTfDigitoChaveAcesso().setValue(
					this.nfeCabecalho.getDigitoChaveAcesso());
			this.subView.getTfCodigoNumerico().setValue(
					this.nfeCabecalho.getCodigoNumerico());
			this.subView.getTfSerie().setValue(this.nfeCabecalho.getSerie());
			this.subView.getTfNumero().setValue(this.nfeCabecalho.getNumero());
			// this.subView.getTfDataEmissao().setValue(
			// nfeCabecalho.getDataEmissao().toString());
			// this.subView.getTfDataEntradaSaida().setValue(
			// nfeCabecalho.getDataEntradaSaida().toString());
			// this.subView.getTfHoraEntradaSaida().setValue(
			// nfeCabecalho.getHoraEntradaSaida());
			this.subView.getTfTipoOperacao().setValue(
					this.nfeCabecalho.getTipoOperacao());
			this.subView.getTfTipoEmissao().setValue(
					this.nfeCabecalho.getTipoEmissao());
			this.subView.getTfFinalidadeEmissao().setValue(
					this.nfeCabecalho.getFinalidadeEmissao());
			this.subView.getTfFormatoImpressaoDanfe().setValue(
					this.nfeCabecalho.getFormatoImpressaoDanfe());
			this.subView.getTfFormaPagamento().setValue(
					this.nfeCabecalho.getIndicadorFormaPagamento());
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

			// this.nfeDetalheDAO.saveOrUpdate(ent);

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

			/**
			 * ICMS
			 */

			NfeDetalheImpostoIcmsEntity entIcms = item
					.getNfeDetalheImpostoIcms();

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

			/**
			 * IMPOSTO IMPORTAÇÃO
			 */

			NfeDetalheImpostoIiEntity entIi = item.getNfeDetalheImpostoIi();

			this.subView.getTfBaseCalculoBcImpostoImportacao().setValue(
					entIi.getValorBcIi().toString());
			this.subView.getTfDespesasAduaneirasImpostoImportacao().setValue(
					entIi.getValorDespesasAduaneiras().toString());
			this.subView.getTfValorImpostoImportacao().setValue(
					entIi.getValorImpostoImportacao().toString());
			this.subView.getTfIofImpostoImportacao().setValue(
					entIi.getValorIof().toString());

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

			/**
			 * PIS
			 */

			NfeDetalheImpostoPisEntity entPis = item.getNfeDetalheImpostoPis();

			this.subView.getTfCstPis().setValue(entPis.getCstPis());
			this.subView.getTfQtdVendidaPis().setValue(
					entPis.getQuantidadeVendida().toString());
			this.subView.getTfBaseCalculoBcPis().setValue(
					entPis.getValorBaseCalculoPis().toString());
			this.subView.getTfAliquotaPercentualPis().setValue(
					entPis.getAliquotaPisPercentual().toString());
			this.subView.getTfAliquotaReaisPis().setValue(
					entPis.getAliquotaPisReais().toString());
			this.subView.getTfValorPis().setValue(
					entPis.getValorPis().toString());

			this.subView.getGlIcms().setEnabled(true);
			this.subView.getGlPis().setEnabled(true);
			this.subView.getGlCofins().setEnabled(true);
			this.subView.getGlIpi().setEnabled(true);
			this.subView.getGlImpostoImportacao().setEnabled(true);
			this.subView.getGlIssqn().setEnabled(true);
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

		String s = event.getProperty().getValue().toString().trim();

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
			this.nfeCabecalho.setCodigoModelo(s);

			break;
		case "tfNaturezaOperacao":
			this.nfeCabecalho.setNaturezaOperacao(s);

			break;
		case "tfChaveAcesso":
			this.nfeCabecalho.setChaveAcesso(s);

			break;
		case "tfDigitoChaveAcesso":
			this.nfeCabecalho.setDigitoChaveAcesso(s);

			break;
		case "tfCodigoNumerico":
			this.nfeCabecalho.setCodigoNumerico(s);

			break;
		case "tfSerie":
			this.nfeCabecalho.setSerie(s);

			break;
		case "tfNumero":
			this.nfeCabecalho.setNumero(s);

			break;
		case "tfDataEmissao":
			// this.nfeCabecalho.set

			break;
		case "tfDataEntradaSaida":
			// this.nfeCabecalho.set

			break;
		case "tfHoraEntradaSaida":
			// this.nfeCabecalho.set

			break;
		case "tfTipoOperacao":
			this.nfeCabecalho.setTipoOperacao(s);

			break;
		case "tfTipoEmissao":
			this.nfeCabecalho.setTipoEmissao(s);

			break;
		case "tfFinalidadeEmissao":
			this.nfeCabecalho.setFinalidadeEmissao(s);

			break;
		case "tfFormatoImpressaoDanfe":
			this.nfeCabecalho.setFormatoImpressaoDanfe(s);

			break;
		case "tfFormaPagamento":
			this.nfeCabecalho.setIndicadorFormaPagamento(s);

			break;
		}
	}

	public void adicionarCofins(String id, ValueChangeEvent event) {
		NfeDetalheImpostoCofinsEntity ndiCofins = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoCofins();

		if (ndiCofins == null) {
			return;
		}

		String s = event.getProperty().getValue().toString().trim();

		switch (id) {
		case "tfCstCofins":
			ndiCofins.setCstCofins(s);

			break;
		case "tfQtdVendidaCofins":
			ndiCofins.setQuantidadeVendida(new BigDecimal(s));

			break;
		case "tfBaseCalculoBcCofins":
			ndiCofins.setBaseCalculoCofins(new BigDecimal(s));

			break;
		case "tfAliquotaPercentualCofins":
			ndiCofins.setAliquotaCofinsPercentual(new BigDecimal(s));

			break;
		case "tfAliquotaReaisCofins":
			ndiCofins.setAliquotaCofinsReais(new BigDecimal(s));

			break;
		case "tfValorCofins":
			ndiCofins.setValorCofins(new BigDecimal(s));

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

		String s = event.getProperty().getValue().toString().trim();

		switch (id) {
		case "tfOrigemMercadoriaIcms":
			ndiIcms.setOrigemMercadoria(s);

			break;
		case "tfCstIcms":
			ndiIcms.setCstIcms(s);

			break;
		case "tfCsosnIcms":
			ndiIcms.setCsosn(s);

			break;
		case "tfModalidadeBcIcms":
			ndiIcms.setModalidadeBcIcms(s);

			break;
		case "tfTaxaReducaoBcIcms":
			ndiIcms.setTaxaReducaoBcIcms(new BigDecimal(s));

			break;
		case "tfBaseCalculoBcIcms":
			ndiIcms.setBaseCalculoIcms(new BigDecimal(s));

			break;
		case "tfAliquotaIcms":
			ndiIcms.setAliquotaIcms(new BigDecimal(s));

			break;
		case "tfValorIcms":
			ndiIcms.setValorIcms(new BigDecimal(s));

			break;
		case "tfMotivoDesoneracaoIcms":
			ndiIcms.setMotivoDesoneracaoIcms(s);

			break;
		case "tfModalidadeBcStIcms":
			ndiIcms.setModalidadeBcIcmsSt(s);

			break;
		case "tfPercentualMvaStIcms":
			ndiIcms.setPercentualMvaIcmsSt(new BigDecimal(s));

			break;
		case "tfTaxaReducaoBcStIcms":
			ndiIcms.setPercentualReducaoBcIcmsSt(new BigDecimal(s));

			break;
		case "tfBaseCalculoStIcms":
			ndiIcms.setValorBaseCalculoIcmsSt(new BigDecimal(s));

			break;
		case "tfAliquotaStIcms":
			ndiIcms.setAliquotaIcmsSt(new BigDecimal(s));

			break;
		case "tfValorStIcms":
			ndiIcms.setValorIcmsSt(new BigDecimal(s));

			break;
		case "tfBcStRetidoIcms":
			ndiIcms.setValorBcIcmsStRetido(new BigDecimal(s));

			break;
		case "tfValorStRetidoIcms":
			ndiIcms.setValorIcmsStRetido(new BigDecimal(s));

			break;
		case "tfBcStDestinoIcms":
			ndiIcms.setValorBcIcmsStDestino(new BigDecimal(s));

			break;
		case "tfValorStDestinoIcms":
			ndiIcms.setValorIcmsStDestino(new BigDecimal(s));

			break;
		case "tfAliquotaCreditoSnIcms":
			ndiIcms.setAliquotaCreditoIcmsSn(new BigDecimal(s));

			break;
		case "tfValorCreditoSnIcms":
			ndiIcms.setValorCreditoIcmsSn(new BigDecimal(s));

			break;
		case "tfPercentualBcOperacaoPropriaIcms":
			ndiIcms.setPercentualBcOperacaoPropria(new BigDecimal(s));

			break;
		case "tfUfStIcms":
			ndiIcms.setUfSt(s);

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

		String s = event.getProperty().getValue().toString().trim();

		switch (id) {
		case "tfBaseCalculoBcImpostoImportacao":
			ndiIi.setValorBcIi(new BigDecimal(s));

			break;
		case "tfDespesasAduaneirasImpostoImportacao":
			ndiIi.setValorDespesasAduaneiras(new BigDecimal(s));

			break;
		case "tfValorImpostoImportacao":
			ndiIi.setValorImpostoImportacao(new BigDecimal(s));

			break;
		case "tfIofImpostoImportacao":
			ndiIi.setValorIof(new BigDecimal(s));

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

		String s = event.getProperty().getValue().toString().trim();

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

		String s = event.getProperty().getValue().toString().trim();

		switch (id) {
		case "tfBaseCalculoBcIssqn":
			ndiIssqn.setBaseCalculoIssqn(new BigDecimal(s));

			break;
		case "tfAliquotaIssqn":
			ndiIssqn.setAliquotaIssqn(new BigDecimal(s));

			break;
		case "tfValorIssqn":
			ndiIssqn.setValorIssqn(new BigDecimal(s));

			break;
		case "tfMunicipioIssqn":
			ndiIssqn.setMunicipioIssqn(new Integer(s));

			break;
		case "tfItemListaServicosIssqn":
			ndiIssqn.setItemListaServicos(new Integer(s));

			break;
		case "tfTributacaoIssqn":
			ndiIssqn.setTributacaoIssqn(s);

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

		String s = event.getProperty().getValue().toString().trim();

		switch (id) {
		case "tfCstPis":
			ndiPis.setCstPis(s);

			break;
		case "tfQtdVendidaPis":
			ndiPis.setQuantidadeVendida(new BigDecimal(s));

			break;
		case "tfBaseCalculoBcPis":
			ndiPis.setValorBaseCalculoPis(new BigDecimal(s));

			break;
		case "tfAliquotaPercentualPis":
			ndiPis.setAliquotaPisPercentual(new BigDecimal(s));

			break;
		case "tfAliquotaReaisPis":
			ndiPis.setAliquotaPisReais(new BigDecimal(s));

			break;
		case "tfValorPis":
			ndiPis.setValorPis(new BigDecimal(s));

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

}