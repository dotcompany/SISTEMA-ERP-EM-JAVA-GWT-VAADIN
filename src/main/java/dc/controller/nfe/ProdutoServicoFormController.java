package dc.controller.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
			this.nfeCabecalhoDAO.save(this.nfeCabecalho);

			if (!this.nfeCabecalho.getNfeDetalheList().isEmpty()) {
				for (Object obj : this.nfeCabecalho.getNfeDetalheList()) {
					NfeDetalheEntity ent = (NfeDetalheEntity) obj;

					System.out.println(ent.getNumeroItem());

					this.nfeDetalheDAO.save(ent);

					this.nfeDetalheImpostoCofinsDAO.save(ent
							.getNfeDetalheImpostoCofins());
					this.nfeDetalheImpostoIcmsDAO.save(ent
							.getNfeDetalheImpostoIcms());
					this.nfeDetalheImpostoIiDAO.save(ent
							.getNfeDetalheImpostoIi());
					// this.nfeDetalheImpostoIpiDAO.save(ent.getn);
					this.nfeDetalheImpostoIssqnDAO.save(ent
							.getNfeDetalheImpostoIssqn());
					this.nfeDetalheImpostoPisDAO.save(ent
							.getNfeDetalheImpostoPis());
				}
			}

			notifiyFrameworkSaveOK(this.nfeCabecalho);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {

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
				this.nfeCabecalho
						.setNfeDetalheList(new ArrayList<NfeDetalheEntity>());
			} else {
				this.nfeCabecalho = this.nfeCabecalhoDAO.find(id);

				List<NfeDetalheEntity> auxLista = (List<NfeDetalheEntity>) this.nfeDetalheDAO
						.getLista(this.nfeCabecalho);

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

				this.nfeCabecalho.setNfeDetalheList(auxLista);

				this.subView.carregarSfNfeDetalhe(this.nfeCabecalho
						.getNfeDetalheList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * **************************************
	 */

	/**
	 * ADICIONAR
	 */

	public NfeDetalheEntity novoNfeDetalhe() {
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

		// NfeDetalheImpostoIpiEntity ndiIpi = new NfeDetalheImpostoIpiEntity();

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

		this.nfeCabecalho.getNfeDetalheList().add(ent);

		return ent;
	}

	/**
	 * CARREGAR
	 */

	public void carregarImpostos(NfeDetalheEntity item) {
		/**
		 * COFINS
		 */

		carregarCofins(item);

		/**
		 * ICMS
		 */

		carregarIcms(item);

		/**
		 * IMPOSTO IMPORTAÇÃO
		 */

		carregarIi(item);

		/**
		 * IPI
		 */

		carregarIpi(item);

		/**
		 * ISSQN
		 */

		carregarIssqn(item);

		/**
		 * PIS
		 */

		carregarPis(item);
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