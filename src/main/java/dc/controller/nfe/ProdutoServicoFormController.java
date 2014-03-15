package dc.controller.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
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
import dc.entidade.nfe.NfeDetalheImpostoIpiEntity;
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

	/**
	 * CONSTRUTOR
	 */

	public ProdutoServicoFormController() {
		if (this.nfeCabecalho == null) {
			this.nfeCabecalho = new NfeCabecalhoEntity();
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
			} else {
				this.nfeCabecalho = this.nfeCabecalhoDAO.find(id);

				List auxLista = this.nfeDetalheDAO.getLista(this.nfeCabecalho);

				if (auxLista != null && !auxLista.isEmpty()) {
					for (Object obj : auxLista) {
						NfeDetalheEntity ent = (NfeDetalheEntity) obj;

						NfeDetalheImpostoCofinsEntity ndiCofins = this.nfeDetalheImpostoCofinsDAO
								.getEntidade(ent);
						ndiCofins.setNfeDetalhe(ent);
						NfeDetalheImpostoIcmsEntity ndiIcms = this.nfeDetalheImpostoIcmsDAO
								.getEntidade(ent);
						ndiIcms.setNfeDetalhe(ent);
						NfeDetalheImpostoIiEntity ndiIi = this.nfeDetalheImpostoIiDAO
								.getEntidade(ent);
						ndiIi.setNfeDetalhe(ent);
						// NfeDetalheImpostoIpiEntity ndiIpi =
						// this.nfeDetalheImpostoIpiDAO.getEntidade(ent);
						NfeDetalheImpostoIssqnEntity ndiIssqn = this.nfeDetalheImpostoIssqnDAO
								.getEntidade(ent);
						ndiIssqn.setNfeDetalhe(ent);
						NfeDetalheImpostoPisEntity ndiPis = this.nfeDetalheImpostoPisDAO
								.getEntidade(ent);
						ndiPis.setNfeDetalhe(ent);

						ent.setNfeDetalheImpostoCofins(ndiCofins);
						ent.setNfeDetalheImpostoIcms(ndiIcms);
						ent.setNfeDetalheImpostoIi(ndiIi);
						ent.setNfeDetalheImpostoIssqn(ndiIssqn);
						ent.setNfeDetalheImpostoPis(ndiPis);
					}
				}

				// this.nfeCabecalho.setNfeDetalheList(auxLista);

				this.subView.carregarSfNfeDetalhe(auxLista);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * **************************************
	 */

	/**
	 * @method ADICIONAR NFEDETALHE
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

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return new NfeDetalheEntity();
		}
	}

	/**
	 * @method SELECIONAR NFEDETALHE
	 */

	private NfeDetalheEntity nfeDetalheSelecionado;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizarCofins(String id, ValueChangeEvent event) {
		// System.out.println(event);

		NfeDetalheImpostoCofinsEntity ndiCofins = this.nfeDetalheSelecionado
				.getNfeDetalheImpostoCofins();

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

	private void carregarCofins(NfeDetalheEntity item) {
		NfeDetalheImpostoCofinsEntity entCofins = this.nfeDetalheImpostoCofinsDAO
				.getEntidade(item);

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

	private void carregarIcms(NfeDetalheEntity item) {
		NfeDetalheImpostoIcmsEntity entIcms = this.nfeDetalheImpostoIcmsDAO
				.getEntidade(item);

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

	private void carregarIi(NfeDetalheEntity item) {
		NfeDetalheImpostoIiEntity entIi = this.nfeDetalheImpostoIiDAO
				.getEntidade(item);
	}

	private void carregarIpi(NfeDetalheEntity item) {
		NfeDetalheImpostoIpiEntity entIpi = this.nfeDetalheImpostoIpiDAO
				.getEntidade(item);
	}

	private void carregarIssqn(NfeDetalheEntity item) {
		NfeDetalheImpostoIssqnEntity entIssqn = this.nfeDetalheImpostoIssqnDAO
				.getEntidade(item);
	}

	private void carregarPis(NfeDetalheEntity item) {
		NfeDetalheImpostoPisEntity entPis = this.nfeDetalheImpostoPisDAO
				.getEntidade(item);
	}

}