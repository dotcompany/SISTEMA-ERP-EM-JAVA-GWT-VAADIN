package dc.controller.nfe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.converter.ObjectConverter;
import dc.controller.pessoal.ClienteListController;
import dc.controller.produto.ProdutoListController;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.nfe.NfeCabecalhoEntity;
import dc.entidade.nfe.NfeDestinatarioEntity;
import dc.entidade.nfe.NfeDetEspecificoArmamentoEntity;
import dc.entidade.nfe.NfeDetEspecificoCombustivelEntity;
import dc.entidade.nfe.NfeDetEspecificoMedicamentoEntity;
import dc.entidade.nfe.NfeDetEspecificoVeiculoEntity;
import dc.entidade.nfe.NfeDetalheEntity;
import dc.entidade.nfe.NfeDetalheImpIpiEntity;
import dc.entidade.nfe.NfeDetalheImpostoCofinsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIcmsEntity;
import dc.entidade.nfe.NfeDetalheImpostoIiEntity;
import dc.entidade.nfe.NfeDetalheImpostoIssqnEntity;
import dc.entidade.nfe.NfeDetalheImpostoPisEntity;
import dc.entidade.pessoal.Cliente;
import dc.entidade.produto.Produto;
import dc.entidade.tributario.OperacaoFiscal;
import dc.servicos.dao.nfe.NfeCabecalhoDAO;
import dc.servicos.dao.nfe.NfeDeclaracaoImportacaoDAO;
import dc.servicos.dao.nfe.NfeDestinatarioDAO;
import dc.servicos.dao.nfe.NfeDetEspecificoArmamentoDAO;
import dc.servicos.dao.nfe.NfeDetEspecificoMedicamentoDAO;
import dc.servicos.dao.nfe.NfeDetalheDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoCofinsDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIcmsDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIiDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIpiDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoIssqnDAO;
import dc.servicos.dao.nfe.NfeDetalheImpostoPisDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneCombo.ItemValue;
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
	private NfeDestinatarioDAO nfeDestinatarioDAO;

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

	@Autowired
	private NfeDetEspecificoMedicamentoDAO ndeMedicamentoDAO;

	@Autowired
	private NfeDetEspecificoArmamentoDAO ndeArmamentoDAO;

	/**
	 * ENTITIES
	 */

	private NfeCabecalhoEntity nfeCabecalho;

	// private NfeDetalheEntity nfeDetalheSelecionado;

	private NfeDetEspecificoMedicamentoEntity ndeMedicamentoSelecionado;

	private NfeDetEspecificoArmamentoEntity ndeArmamentoSelecionado;

	/**
	 * CONSTRUTOR
	 */

	public ProdutoServicoFormController() {
		if (this.nfeCabecalho == null) {
			this.nfeCabecalho = new NfeCabecalhoEntity();
		}

		// if (this.nfeDetalheSelecionado == null) {
		// this.nfeDetalheSelecionado = new NfeDetalheEntity();
		// }

		if (this.ndeMedicamentoSelecionado == null) {
			this.ndeMedicamentoSelecionado = new NfeDetEspecificoMedicamentoEntity();
		}

		if (this.ndeArmamentoSelecionado == null) {
			this.ndeArmamentoSelecionado = new NfeDetEspecificoArmamentoEntity();
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
			/*
			 * this.nfeCabecalhoDAO.saveOrUpdate(this.nfeCabecalho);
			 * 
			 * List<NfeDetalheEntity> auxLista = this.subView.getSfNfeDetalhe()
			 * .getDados();
			 * 
			 * if (auxLista != null && !auxLista.isEmpty()) { for
			 * (NfeDetalheEntity ent : auxLista) { // NfeDetalheEntity ent =
			 * (NfeDetalheEntity) obj; ent.setNfeCabecalho(this.nfeCabecalho);
			 * 
			 * this.nfeDetalheDAO.saveOrUpdate(ent);
			 * 
			 * for (NfeDetEspecificoMedicamentoEntity ent1 : ent
			 * .getNdeMedicamentoList()) {
			 * this.ndeMedicamentoDAO.saveOrUpdate(ent1); } } }
			 */

			this.nfeCabecalhoDAO.saveOrUpdateNfeCabecalho(this.nfeCabecalho);

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

		// popularCombo();
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

				// this.nfeCabecalho
				// .setNfeDestinatario(new NfeDestinatarioEntity());

				nfeDetalheLimpar();
				ndiCofinsLimpar();
				ndiIcmsLimpar();
				ndiIiLimpar();
				ndiIpiLimpar();
				ndiIssqnLimpar();
				ndiPisLimpar();
				ndeCombustivelLimpar();
				ndeVeiculoLimpar();
				ndeMedicamentoLimpar();
			} else {
				this.nfeCabecalho = this.nfeCabecalhoDAO.find(id);
			}

			this.nfeCabecalho.setNfeDetalhe(new NfeDetalheEntity());

			nfeCabecalhoCarregar();
			nfeDestinatarioCarregar();
			nfeDetalheCarregar();

			popularCombo();

			abaHabilitar(false, false, false, false, false, false, false,
					false, false, false, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * **************************************
	 */

	// CARREGAR

	private void nfeCabecalhoCarregar() throws Exception {
		OperacaoFiscal operacaoFiscal = this.nfeCabecalho
				.getTributOperacaoFiscal();

		if (operacaoFiscal != null) {
			this.subView.getMtoOperacaoFiscal().setValue(operacaoFiscal);
			// this.subView.getTfOperacaoFiscalId().setValue(
			// operacaoFiscal.getDescricaoNaNF());
		}

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
		this.subView.getTfSerie().setValue(this.nfeCabecalho.getSerie().trim());
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

		//
		this.subView.getPlNfeDetalheSubForm().setCaption(
				"NFE CABEÇALHO " + this.nfeCabecalho.getNumero());
	}

	private void nfeDestinatarioCarregar() throws Exception {
		NfeDestinatarioEntity nfeDestinatario = this.nfeDestinatarioDAO
				.getEntidade(this.nfeCabecalho);
		nfeDestinatario.setNfeCabecalho(this.nfeCabecalho);

		this.nfeCabecalho.setNfeDestinatario(nfeDestinatario);

		this.subView.getTfEmailDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getEmail());
		this.subView.getTfSuframaDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getSuframa());
		this.subView.getTfTelefoneDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getTelefone());
		this.subView.getTfInscricaoEstadualDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getInscricaoEstadual());
		this.subView.getTfUfDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getUf());
		// this.subView.getTfCidadeDestinatario().setValue(this.nfeCabecalho.getNfeDestinatario().get);
		// this.subView.getTfCodigoIbgeDestinatario().setValue(this.nfeCabecalho.getNfeDestinatario().getco);
		this.subView.getTfBairroLogradouroDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getBairro());
		this.subView.getTfLogradouroComplementoDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getComplemento());
		this.subView.getTfLogradouroNumeroDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getNumero());
		this.subView.getTfLogradouroDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getLogradouro());
		this.subView.getTfCepDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getCep());
		this.subView.getTfRazaoSocialDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getRazaoSocial());
		this.subView.getTfCpfCnpjDestinatario().setValue(
				this.nfeCabecalho.getNfeDestinatario().getCpfCnpj());
		// this.subView.getTfIdDestinatario().setValue();

		Cliente cliente = this.nfeCabecalho.getCliente();

		if (cliente != null) {
			this.subView.getMtoCliente().setValue(
					this.nfeCabecalho.getCliente());
		}
	}

	private void nfeDetalheCarregar() throws Exception {
		List<NfeDetalheEntity> auxLista = new ArrayList<NfeDetalheEntity>();

		if (this.nfeCabecalho.getId() != null
				&& !this.nfeCabecalho.getId().equals(0)) {
			auxLista = this.nfeDetalheDAO.getLista(this.nfeCabecalho);
		}

		this.nfeCabecalho.setNfeDetalheList(auxLista);

		this.subView
				.carregarSfNfeDetalhe(this.nfeCabecalho.getNfeDetalheList());
	}

	private void ndiCofinsCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * COFINS
		 */

		NfeDetalheImpostoCofinsEntity ndiCofins = new NfeDetalheImpostoCofinsEntity();
		ndiCofins.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoCofins(ndiCofins);
	}

	private void ndiIcmsCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * ICMS
		 */

		NfeDetalheImpostoIcmsEntity ndiIcms = new NfeDetalheImpostoIcmsEntity();
		ndiIcms.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoIcms(ndiIcms);
	}

	private void ndiIiCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * IMPOSTO IMPORTAÇÃO
		 */

		NfeDetalheImpostoIiEntity ndiIi = new NfeDetalheImpostoIiEntity();
		ndiIi.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoIi(ndiIi);
	}

	private void ndiIpiCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * IPI
		 */

		NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();
		ndiIpi.setNfeDetalhe(ent);

		ent.setNfeDetalheImpIpi(ndiIpi);
	}

	private void ndiIssqnCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * ISSQN
		 */

		NfeDetalheImpostoIssqnEntity ndiIssqn = new NfeDetalheImpostoIssqnEntity();
		ndiIssqn.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoIssqn(ndiIssqn);
	}

	private void ndiPisCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * PIS
		 */

		NfeDetalheImpostoPisEntity ndiPis = new NfeDetalheImpostoPisEntity();
		ndiPis.setNfeDetalhe(ent);

		ent.setNfeDetalheImpostoPis(ndiPis);
	}

	private void ndeCombustivelCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * COMBUSTÍVEL
		 */

		NfeDetEspecificoCombustivelEntity ndeCombustivel = new NfeDetEspecificoCombustivelEntity();
		ndeCombustivel.setNfeDetalhe(ent);

		ent.setNfeDetEspecificoCombustivel(ndeCombustivel);
	}

	private void ndeVeiculoCarregar(NfeDetalheEntity ent) throws Exception {
		/**
		 * VEÍCULO
		 */

		NfeDetEspecificoVeiculoEntity ndeVeiculo = new NfeDetEspecificoVeiculoEntity();
		ndeVeiculo.setNfeDetalhe(ent);

		ent.setNfeDetEspecificoVeiculo(ndeVeiculo);
	}

	private void ndeMedicamentoCarregar() throws Exception {
		List<NfeDetEspecificoMedicamentoEntity> auxLista = this.nfeCabecalho
				.getNfeDetalhe().getNdeMedicamentoList();

		if (auxLista == null || auxLista.isEmpty()) {
			auxLista = this.ndeMedicamentoDAO.getLista(this.nfeCabecalho
					.getNfeDetalhe());
		}

		this.nfeCabecalho.getNfeDetalhe().setNdeMedicamentoList(auxLista);

		this.subView.carregarSfNdeMedicamento(this.nfeCabecalho.getNfeDetalhe()
				.getNdeMedicamentoList());
	}

	private void ndeArmamentoCarregar() throws Exception {
		List<NfeDetEspecificoArmamentoEntity> auxLista = this.nfeCabecalho
				.getNfeDetalhe().getNdeArmamentoList();

		if (auxLista == null || auxLista.isEmpty()) {
			auxLista = this.ndeArmamentoDAO.getLista(this.nfeCabecalho
					.getNfeDetalhe());
		}

		this.nfeCabecalho.getNfeDetalhe().setNdeArmamentoList(auxLista);

		this.subView.carregarSfNdeArmamento(this.nfeCabecalho.getNfeDetalhe()
				.getNdeArmamentoList());
	}

	/**
	 * NFEDETALHE - ADICIONAR
	 */

	public NfeDetalheEntity nfeDetalheAdicionar() {
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

			NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();
			ndiIpi.setNfeDetalhe(ent);

			ent.setNfeDetalheImpIpi(ndiIpi);

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

			/**
			 * MEDICAMENTO
			 */

			ent.setNdeMedicamentoList(new ArrayList<NfeDetEspecificoMedicamentoEntity>());

			/**
			 * ARMAMENTO
			 */

			ent.setNdeArmamentoList(new ArrayList<NfeDetEspecificoArmamentoEntity>());

			/**
			 * 
			 */

			this.nfeCabecalho.getNfeDetalheList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * NFEDETESPECIFICOMEDICAMENTO - ADICIONAR
	 */

	public NfeDetEspecificoMedicamentoEntity ndeMedicamentoAdicionar() {
		try {
			NfeDetEspecificoMedicamentoEntity ent = new NfeDetEspecificoMedicamentoEntity();
			ent.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

			// this.nfeDetalheSelecionado.getNdeMedicamentoList().add(ent);
			this.nfeCabecalho.getNfeDetalhe().getNdeMedicamentoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * NFEDETESPECIFICOARMAMENTO - ADICIONAR
	 */

	public NfeDetEspecificoArmamentoEntity ndeArmamentoAdicionar() {
		try {
			NfeDetEspecificoArmamentoEntity ent = new NfeDetEspecificoArmamentoEntity();
			ent.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

			// this.nfeDetalheSelecionado.getNdeMedicamentoList().add(ent);
			this.nfeCabecalho.getNfeDetalhe().getNdeArmamentoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * NFEDETALHE - SELECIONAR
	 */

	public void nfeDetalheSelecionar(NfeDetalheEntity item) {
		try {
			this.nfeCabecalho.setNfeDetalhe(item);

			/**
			 * NFEDETALHE
			 */

			this.subView.getTfNumeroItem().setValue(
					this.nfeCabecalho.getNfeDetalhe().getNumeroItem()
							.toString());
			this.subView.getTfGtin().setValue(
					this.nfeCabecalho.getNfeDetalhe().getGtin());

			Produto produto = this.nfeCabecalho.getNfeDetalhe().getProduto();

			if (produto != null) {
				this.subView.getMtoProduto().setValue(produto);
				this.subView.getTfCodigoProduto().setValue(
						produto.getCodigoInterno());
			}

			this.subView.getTfNcm().setValue(
					this.nfeCabecalho.getNfeDetalhe().getNcm());
			this.subView.getTfExTipi().setValue(
					this.nfeCabecalho.getNfeDetalhe().getExTipi().toString());
			this.subView.getTfCfop().setValue(
					this.nfeCabecalho.getNfeDetalhe().getCfop().toString());
			this.subView.getTfUnidadeComercial().setValue(
					this.nfeCabecalho.getNfeDetalhe().getUnidadeComercial());
			this.subView.getTfQuantidadeComercial().setValue(
					ObjectConverter.valueToString(this.nfeCabecalho
							.getNfeDetalhe().getQuantidadeComercial()));
			this.subView.getTfValorUnitarioComercial().setValue(
					this.nfeCabecalho.getNfeDetalhe()
							.getValorUnitarioComercial().toString());
			this.subView.getTfValorBrutoProduto().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorBrutoProduto()
							.toString());
			this.subView.getTfGtinUnidadeTributavel().setValue(
					this.nfeCabecalho.getNfeDetalhe()
							.getGtinUnidadeTributavel());
			this.subView.getTfUnidadeTributavel().setValue(
					this.nfeCabecalho.getNfeDetalhe().getUnidadeTributavel());
			this.subView.getTfQuantidadeTributavel().setValue(
					this.nfeCabecalho.getNfeDetalhe().getQuantidadeTributavel()
							.toString());
			this.subView.getTfValorUnitarioTributavel().setValue(
					this.nfeCabecalho.getNfeDetalhe()
							.getValorUnitarioTributavel().toString());
			this.subView.getTfValorFrete().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorFrete()
							.toString());
			this.subView.getTfValorSeguro().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorSeguro()
							.toString());
			this.subView.getTfValorDesconto().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorDesconto()
							.toString());
			this.subView.getTfValorOutrasDespesas().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorOutrasDespesas()
							.toString());
			this.subView.getTfEntraTotal().setValue(
					this.nfeCabecalho.getNfeDetalhe().getEntraTotal());
			this.subView.getTfValorSubtotal().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorSubtotal()
							.toString());
			this.subView.getTfValorTotal().setValue(
					this.nfeCabecalho.getNfeDetalhe().getValorTotal()
							.toString());
			this.subView.getTfNumeroPedidoCompra().setValue(
					this.nfeCabecalho.getNfeDetalhe().getNumeroPedidoCompra());
			this.subView.getTfItemPedidoCompra().setValue(
					this.nfeCabecalho.getNfeDetalhe().getItemPedidoCompra()
							.toString());
			this.subView.getTfInformacoesAdicionais().setValue(
					this.nfeCabecalho.getNfeDetalhe()
							.getInformacoesAdicionais());

			/**
			 * COFINS
			 */

			NfeDetalheImpostoCofinsEntity entCofins = this.nfeCabecalho
					.getNfeDetalhe().getNfeDetalheImpostoCofins();

			if (entCofins == null) {
				entCofins = new NfeDetalheImpostoCofinsEntity();
				entCofins.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoCofins(
						entCofins);
			}

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

			NfeDetalheImpostoIcmsEntity entIcms = this.nfeCabecalho
					.getNfeDetalhe().getNfeDetalheImpostoIcms();

			if (entIcms == null) {
				entIcms = new NfeDetalheImpostoIcmsEntity();
				entIcms.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIcms(
						entIcms);
			}

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

			NfeDetalheImpostoIiEntity entIi = this.nfeCabecalho.getNfeDetalhe()
					.getNfeDetalheImpostoIi();

			if (entIi == null) {
				entIi = new NfeDetalheImpostoIiEntity();
				entIi.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIi(entIi);
			}

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

			NfeDetalheImpIpiEntity ndiIpi = this.nfeCabecalho.getNfeDetalhe()
					.getNfeDetalheImpIpi();

			if (ndiIpi == null) {
				ndiIpi = new NfeDetalheImpIpiEntity();
				ndiIpi.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpIpi(ndiIpi);
			}

			this.subView.getTfCstIpi().setValue(ndiIpi.getCstIpi());
			this.subView.getTfBaseCalculoBcIpi().setValue(
					ndiIpi.getValorBaseCalculoIpi().toString());
			this.subView.getTfAliquotaIpi().setValue(
					ndiIpi.getAliquotaIpi().toString());
			this.subView.getTfQtdUndTributavelIpi().setValue(
					ndiIpi.getQuantidadeUnidadeTributavel().toString());
			this.subView.getTfValorUndTributavelIpi().setValue(
					ndiIpi.getValorUnidadeTributavel().toString());
			this.subView.getTfValorIpi().setValue(
					ndiIpi.getValorIpi().toString());
			this.subView.getTfEnquadramentoIpi().setValue(
					ndiIpi.getEnquadramentoIpi());
			this.subView.getTfEnquadramentoLegalIpi().setValue(
					ndiIpi.getEnquadramentoLegalIpi());
			this.subView.getTfCnpjProdutorIpi().setValue(
					ndiIpi.getCnpjProdutorIpi());
			this.subView.getTfQtdSeloIpi().setValue(
					ndiIpi.getQuantidadeSeloIpi().toString());
			this.subView.getTfCodigoSeloIpi().setValue(
					ndiIpi.getCodigoSeloIpi());

			/**
			 * ISSQN
			 */

			NfeDetalheImpostoIssqnEntity entIssqn = this.nfeCabecalho
					.getNfeDetalhe().getNfeDetalheImpostoIssqn();

			if (entIssqn == null) {
				entIssqn = new NfeDetalheImpostoIssqnEntity();
				entIssqn.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIssqn(
						entIssqn);
			}

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

			NfeDetalheImpostoPisEntity entPis = this.nfeCabecalho
					.getNfeDetalhe().getNfeDetalheImpostoPis();

			if (entPis == null) {
				entPis = new NfeDetalheImpostoPisEntity();
				entPis.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoPis(
						entPis);
			}

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

			NfeDetEspecificoCombustivelEntity entCombustivel = this.nfeCabecalho
					.getNfeDetalhe().getNfeDetEspecificoCombustivel();

			if (entCombustivel == null) {
				entCombustivel = new NfeDetEspecificoCombustivelEntity();
				entCombustivel.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe()
						.setNfeDetEspecificoCombustivel(entCombustivel);
			}

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
			 * VEÍCULO
			 */

			NfeDetEspecificoVeiculoEntity entVeiculo = this.nfeCabecalho
					.getNfeDetalhe().getNfeDetEspecificoVeiculo();

			if (entVeiculo == null) {
				entVeiculo = new NfeDetEspecificoVeiculoEntity();
				entVeiculo.setNfeDetalhe(this.nfeCabecalho.getNfeDetalhe());

				this.nfeCabecalho.getNfeDetalhe().setNfeDetEspecificoVeiculo(
						entVeiculo);
			}

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

			/**
			 * MEDICAMENTO
			 */

			ndeMedicamentoCarregar();

			/**
			 * ARMAMENTO
			 */

			ndeArmamentoCarregar();

			abaHabilitar(true, true, true, true, true, true, true, true, true,
					true, true);

			this.subView.getPlNdiCofins()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdiDeclaracaoImportacao()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdiIcms()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdiIi()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdiIpi()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdiIssqn()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdiPis()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdeArmamento()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdeCombustivel()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdeMedicamento()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
			this.subView.getPlNdeVeiculo()
					.setCaption(
							"NFE DETALHE "
									+ this.nfeCabecalho.getNfeDetalhe()
											.getNumeroItem());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * NFEDETESPECIFICOMEDICAMENTO - SELECIONAR
	 */

	public void ndeMedicamentoSelecionar(NfeDetEspecificoMedicamentoEntity item) {
		try {
			this.ndeMedicamentoSelecionado = item;

			this.subView.getTfNumeroLoteMedicamento().setValue(
					this.ndeMedicamentoSelecionado.getNumeroLote());
			this.subView.getTfQuantidadeLoteMedicamento().setValue(
					this.ndeMedicamentoSelecionado.getQuantidadeLote()
							.toString());
			this.subView.getPdfDataFabricacaoMedicamento().setValue(
					this.ndeMedicamentoSelecionado.getDataFabricacao());
			this.subView.getPdfDataValidadeMedicamento().setValue(
					this.ndeMedicamentoSelecionado.getDataValidade());
			this.subView.getTfPrecoMaximoConsumidorMedicamento().setValue(
					this.ndeMedicamentoSelecionado.getPrecoMaximoConsumidor()
							.toString());

			this.subView.getGlNdeMedicamento().setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * NFEDETESPECIFICOARMAMENTO - SELECIONAR
	 */

	public void ndeArmamentoSelecionar(NfeDetEspecificoArmamentoEntity item) {
		try {
			this.ndeArmamentoSelecionado = item;

			this.subView.getTfDescricaoArmamento().setValue(
					ndeArmamentoSelecionado.getTipoArma());
			this.subView.getTfNumeroSerieArmaArmamento().setValue(
					ndeArmamentoSelecionado.getNumeroSerieArma());
			this.subView.getTfNumeroSerieCanoArmamento().setValue(
					ndeArmamentoSelecionado.getNumeroSerieCano());
			this.subView.getTfDescricaoArmamento().setValue(
					ndeArmamentoSelecionado.getDescricao());

			this.subView.getGlNdeArmamento().setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void abaHabilitar(boolean a1, boolean a2, boolean a3, boolean a4,
			boolean a5, boolean a6, boolean a7, boolean a8, boolean a9,
			boolean a10, boolean a11) {
		this.subView.getGlNfeDetalhe().setEnabled(a1);
		this.subView.getGlIcms().setEnabled(a2);
		this.subView.getGlPis().setEnabled(a3);
		this.subView.getGlCofins().setEnabled(a4);
		this.subView.getGlIpi().setEnabled(a5);
		this.subView.getGlImpostoImportacao().setEnabled(a6);
		this.subView.getGlIssqn().setEnabled(a7);
		this.subView.getNdeGlCombustivel().setEnabled(a8);
		this.subView.getNdeGlVeiculo().setEnabled(a9);
		this.subView.getPlNdeMedicamentoSubForm().setEnabled(a10);
		this.subView.getPlNdeArmamentoSubForm().setEnabled(a11);
	}

	/**
	 * SETAR VALORES
	 * 
	 * @param id
	 * @param event
	 */

	public void nfeCabecalhoSetarValor(String id, Object obj) {
		// TODO nfeCabecalhoSetarValor

		if (this.nfeCabecalho == null) {
			return;
		}

		switch (id) {
		// case "mtoOperacaoFiscal":
		// this.nfeCabecalho.set

		// break;
		case "mtoOperacaoFiscal":
			ItemValue m = (ItemValue) obj;
			OperacaoFiscal operacaoFiscal = (OperacaoFiscal) m.getBean();

			this.nfeCabecalho.setTributOperacaoFiscal(operacaoFiscal);
			// this.nfeCabecalho
			// .setTipoOperacao(operacaoFiscal.getDescricaoNaNF());

			break;
		case "tfVenda":
			// this.nfeCabecalho.set

			break;
		case "tfModeloNotaFiscal":
			this.nfeCabecalho.setCodigoModelo((String) obj);

			break;
		case "tfNaturezaOperacao":
			this.nfeCabecalho.setNaturezaOperacao((String) obj);

			break;
		case "tfChaveAcesso":
			this.nfeCabecalho.setChaveAcesso((String) obj);

			break;
		case "tfDigitoChaveAcesso":
			this.nfeCabecalho.setDigitoChaveAcesso((String) obj);

			break;
		case "tfCodigoNumerico":
			this.nfeCabecalho.setCodigoNumerico((String) obj);

			break;
		case "tfSerie":
			this.nfeCabecalho.setSerie((String) obj);

			break;
		case "tfNumero":
			this.nfeCabecalho.setNumero((String) obj);

			break;
		case "pdfDataEmissao":
			this.nfeCabecalho.setDataEmissao((Date) obj);

			break;
		case "pdfDataEntradaSaida":
			this.nfeCabecalho.setDataEntradaSaida((Date) obj);

			break;
		case "pdfHoraEntradaSaida":
			this.nfeCabecalho.setHoraEntradaSaida((String) obj);

			break;
		case "tfTipoOperacao":
			this.nfeCabecalho.setTipoOperacao((String) obj);

			break;
		case "tfTipoEmissao":
			this.nfeCabecalho.setTipoEmissao((String) obj);

			break;
		case "tfFinalidadeEmissao":
			this.nfeCabecalho.setFinalidadeEmissao((String) obj);

			break;
		case "tfFormatoImpressaoDanfe":
			this.nfeCabecalho.setFormatoImpressaoDanfe((String) obj);

			break;
		case "tfFormaPagamento":
			this.nfeCabecalho.setIndicadorFormaPagamento((String) obj);

			break;
		}
	}

	public void nfeDestinatarioSetarValor(String id, Object obj) {
		// TODO nfeDestinatarioSetarValor

		NfeDestinatarioEntity nfeDestinatario = this.nfeCabecalho
				.getNfeDestinatario();

		if (nfeDestinatario == null) {
			return;
		}

		switch (id) {
		case "tfEmailDestinatario":
			nfeDestinatario.setEmail((String) obj);

			break;
		case "tfSuframaDestinatario":
			nfeDestinatario.setSuframa((String) obj);

			break;
		case "tfTelefoneDestinatario":
			nfeDestinatario.setTelefone((String) obj);

			break;
		case "tfInscricaoEstadualDestinatario":
			nfeDestinatario.setInscricaoEstadual((String) obj);

			break;
		case "tfUfDestinatario":
			nfeDestinatario.setUf((String) obj);

			break;
		case "tfCidadeDestinatario":
			// nfeDestinatario.set

			break;
		case "tfCodigoIbgeDestinatario":
			// nfeDestinatario.set

			break;
		case "tfBairroLogradouroDestinatario":
			nfeDestinatario.setBairro((String) obj);

			break;
		case "tfLogradouroComplementoDestinatario":
			nfeDestinatario.setComplemento((String) obj);

			break;
		case "tfLogradouroNumeroDestinatario":
			nfeDestinatario.setNumero((String) obj);

			break;
		case "tfLogradouroDestinatario":
			nfeDestinatario.setLogradouro((String) obj);

			break;
		case "tfCepDestinatario":
			nfeDestinatario.setCep((String) obj);

			break;
		case "tfRazaoSocialDestinatario":
			nfeDestinatario.setRazaoSocial((String) obj);

			break;
		case "tfCpfCnpjDestinatario":
			nfeDestinatario.setCpfCnpj((String) obj);

			break;
		case "mtoCliente":
			ItemValue m = (ItemValue) obj;
			Cliente cliente = (Cliente) m.getBean();

			this.nfeCabecalho.setCliente(cliente);

			break;
		}
	}

	public void nfeDetalheSetarValor(String id, Object obj) {
		// TODO nfeDetalheSetarValor

		boolean b = this.subView.getGlNfeDetalhe().isEnabled();

		if (!b) {
			return;
		}

		switch (id) {
		case "tfNumeroItem":
			this.nfeCabecalho.getNfeDetalhe().setNumeroItem(
					ObjectConverter.stringToInteger((String) obj));

			break;
		// case "tfCodigoProduto":
		// this.nfeDetalheSelecionado.setCodigoProduto(s.toString().trim());

		// break;
		case "tfGtin":
			this.nfeCabecalho.getNfeDetalhe().setGtin((String) obj);

			break;
		case "mtoProduto":
			ItemValue m = (ItemValue) obj;
			Produto produto = (Produto) m.getBean();

			this.nfeCabecalho.getNfeDetalhe().setProduto(produto);
			this.nfeCabecalho.getNfeDetalhe().setCodigoProduto(
					produto.getCodigoInterno());

			break;
		case "tfNcm":
			this.nfeCabecalho.getNfeDetalhe().setNcm((String) obj);

			break;
		case "tfExTipi":
			this.nfeCabecalho.getNfeDetalhe().setExTipi(
					ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfCfop":
			this.nfeCabecalho.getNfeDetalhe().setCfop(
					ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfUnidadeComercial":
			this.nfeCabecalho.getNfeDetalhe().setUnidadeComercial((String) obj);

			break;
		case "tfQuantidadeComercial":
			this.nfeCabecalho.getNfeDetalhe().setQuantidadeComercial(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorUnitarioComercial":
			this.nfeCabecalho.getNfeDetalhe().setValorUnitarioComercial(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorBrutoProduto":
			this.nfeCabecalho.getNfeDetalhe().setValorBrutoProduto(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfGtinUnidadeTributavel":
			this.nfeCabecalho.getNfeDetalhe().setGtinUnidadeTributavel(
					(String) obj);

			break;
		case "tfUnidadeTributavel":
			this.nfeCabecalho.getNfeDetalhe()
					.setUnidadeTributavel((String) obj);

			break;
		case "tfQuantidadeTributavel":
			this.nfeCabecalho.getNfeDetalhe().setQuantidadeTributavel(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorUnitarioTributavel":
			this.nfeCabecalho.getNfeDetalhe().setValorUnitarioTributavel(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorFrete":
			this.nfeCabecalho.getNfeDetalhe().setValorFrete(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorSeguro":
			this.nfeCabecalho.getNfeDetalhe().setValorSeguro(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorDesconto":
			this.nfeCabecalho.getNfeDetalhe().setValorDesconto(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorOutrasDespesas":
			this.nfeCabecalho.getNfeDetalhe().setValorOutrasDespesas(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfEntraTotal":
			this.nfeCabecalho.getNfeDetalhe().setEntraTotal((String) obj);

			break;
		case "tfValorSubtotal":
			this.nfeCabecalho.getNfeDetalhe().setValorSubtotal(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorTotal":
			this.nfeCabecalho.getNfeDetalhe().setValorTotal(
					ObjectConverter.stringToValue((String) obj));

			break;
		case "tfNumeroPedidoCompra":
			this.nfeCabecalho.getNfeDetalhe().setNumeroPedidoCompra(
					(String) obj);

			break;
		case "tfItemPedidoCompra":
			this.nfeCabecalho.getNfeDetalhe().setItemPedidoCompra(
					ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfInformacoesAdicionais":
			this.nfeCabecalho.getNfeDetalhe().setInformacoesAdicionais(
					(String) obj);

			break;
		}
	}

	public void ndiCofinsSetarValor(String id, Object obj) {
		// TODO ndiCofinsSetarValor

		NfeDetalheImpostoCofinsEntity ndiCofins = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetalheImpostoCofins();

		if (ndiCofins == null) {
			return;
		}

		switch (id) {
		case "tfCstCofins":
			ndiCofins.setCstCofins((String) obj);

			break;
		case "tfQtdVendidaCofins":
			ndiCofins.setQuantidadeVendida(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcCofins":
			ndiCofins.setBaseCalculoCofins(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaPercentualCofins":
			ndiCofins.setAliquotaCofinsPercentual(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaReaisCofins":
			ndiCofins.setAliquotaCofinsReais(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorCofins":
			ndiCofins.setValorCofins(ObjectConverter
					.stringToValue((String) obj));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoCofins(ndiCofins);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIcmsSetarValor(String id, Object obj) {
		// TODO ndiIcmsSetarValor

		NfeDetalheImpostoIcmsEntity ndiIcms = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpostoIcms();

		if (ndiIcms == null) {
			return;
		}

		switch (id) {
		case "tfOrigemMercadoriaIcms":
			ndiIcms.setOrigemMercadoria((String) obj);

			break;
		case "tfCstIcms":
			ndiIcms.setCstIcms((String) obj);

			break;
		case "tfCsosnIcms":
			ndiIcms.setCsosn((String) obj);

			break;
		case "tfModalidadeBcIcms":
			ndiIcms.setModalidadeBcIcms((String) obj);

			break;
		case "tfTaxaReducaoBcIcms":
			ndiIcms.setTaxaReducaoBcIcms(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcIcms":
			ndiIcms.setBaseCalculoIcms(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaIcms":
			ndiIcms.setAliquotaIcms(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfValorIcms":
			ndiIcms.setValorIcms(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfMotivoDesoneracaoIcms":
			ndiIcms.setMotivoDesoneracaoIcms((String) obj);

			break;
		case "tfModalidadeBcStIcms":
			ndiIcms.setModalidadeBcIcmsSt((String) obj);

			break;
		case "tfPercentualMvaStIcms":
			ndiIcms.setPercentualMvaIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfTaxaReducaoBcStIcms":
			ndiIcms.setPercentualReducaoBcIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoStIcms":
			ndiIcms.setValorBaseCalculoIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaStIcms":
			ndiIcms.setAliquotaIcmsSt(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorStIcms":
			ndiIcms.setValorIcmsSt(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfBcStRetidoIcms":
			ndiIcms.setValorBcIcmsStRetido(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorStRetidoIcms":
			ndiIcms.setValorIcmsStRetido(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBcStDestinoIcms":
			ndiIcms.setValorBcIcmsStDestino(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorStDestinoIcms":
			ndiIcms.setValorIcmsStDestino(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaCreditoSnIcms":
			ndiIcms.setAliquotaCreditoIcmsSn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorCreditoSnIcms":
			ndiIcms.setValorCreditoIcmsSn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfPercentualBcOperacaoPropriaIcms":
			ndiIcms.setPercentualBcOperacaoPropria(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfUfStIcms":
			ndiIcms.setUfSt((String) obj);

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIcms(ndiIcms);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIiSetarValor(String id, Object obj) {
		// TODO ndiIiSetarValor

		NfeDetalheImpostoIiEntity ndiIi = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpostoIi();

		if (ndiIi == null) {
			return;
		}

		switch (id) {
		case "tfBaseCalculoBcImpostoImportacao":
			ndiIi.setValorBcIi(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfDespesasAduaneirasImpostoImportacao":
			ndiIi.setValorDespesasAduaneiras(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorImpostoImportacao":
			ndiIi.setValorImpostoImportacao(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfIofImpostoImportacao":
			ndiIi.setValorIof(ObjectConverter.stringToValue((String) obj));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIi(ndiIi);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIpiSetarValor(String id, Object obj) {
		// TODO ndiIpiSetarValor

		// NfeDetalheImpostoIpiEntity ndiIpi = new NfeDetalheImpostoIpiEntity();

		NfeDetalheImpIpiEntity ndiIpi = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpIpi();

		if (ndiIpi == null) {
			return;
		}

		ndiIpi.setEmpresa(this.nfeCabecalho.getNfeDetalhe().getEmpresa());

		switch (id) {
		case "tfCstIpi":
			ndiIpi.setCstIpi((String) obj);

			break;
		case "tfBaseCalculoBcIpi":
			ndiIpi.setValorBaseCalculoIpi(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaIpi":
			ndiIpi.setAliquotaIpi(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfQtdUndTributavelIpi":
			ndiIpi.setQuantidadeUnidadeTributavel(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorUndTributavelIpi":
			ndiIpi.setValorUnidadeTributavel(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorIpi":
			ndiIpi.setValorIpi(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfEnquadramentoIpi":
			ndiIpi.setEnquadramentoIpi((String) obj);

			break;
		case "tfEnquadramentoLegalIpi":
			ndiIpi.setEnquadramentoLegalIpi((String) obj);

			break;
		case "tfCnpjProdutorIpi":
			ndiIpi.setCnpjProdutorIpi((String) obj);

			break;
		case "tfQtdSeloIpi":
			ndiIpi.setQuantidadeSeloIpi(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfCodigoSeloIpi":
			ndiIpi.setCodigoSeloIpi((String) obj);

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpIpi(ndiIpi);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiIssqnSetarValor(String id, Object obj) {
		// TODO ndiIssqnSetarValor

		NfeDetalheImpostoIssqnEntity ndiIssqn = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetalheImpostoIssqn();

		if (ndiIssqn == null) {
			return;
		}

		switch (id) {
		case "tfBaseCalculoBcIssqn":
			ndiIssqn.setBaseCalculoIssqn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaIssqn":
			ndiIssqn.setAliquotaIssqn(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorIssqn":
			ndiIssqn.setValorIssqn(ObjectConverter.stringToValue((String) obj));

			break;
		case "tfMunicipioIssqn":
			ndiIssqn.setMunicipioIssqn(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfItemListaServicosIssqn":
			ndiIssqn.setItemListaServicos(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfTributacaoIssqn":
			ndiIssqn.setTributacaoIssqn((String) obj);

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoIssqn(ndiIssqn);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndiPisSetarValor(String id, Object obj) {
		// TODO ndiPisSetarValor

		NfeDetalheImpostoPisEntity ndiPis = this.nfeCabecalho.getNfeDetalhe()
				.getNfeDetalheImpostoPis();

		if (ndiPis == null) {
			return;
		}

		switch (id) {
		case "tfCstPis":
			ndiPis.setCstPis((String) obj);

			break;
		case "tfQtdVendidaPis":
			ndiPis.setQuantidadeVendida(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfBaseCalculoBcPis":
			ndiPis.setValorBaseCalculoPis(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaPercentualPis":
			ndiPis.setAliquotaPisPercentual(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaReaisPis":
			ndiPis.setAliquotaPisReais(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorPis":
			ndiPis.setValorPis(ObjectConverter.stringToValue((String) obj));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetalheImpostoPis(ndiPis);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndeCombustivelSetarValor(String id, Object obj) {
		// TODO ndeCombustivelSetarValor

		NfeDetEspecificoCombustivelEntity ndiCombustivel = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetEspecificoCombustivel();

		if (ndiCombustivel == null) {
			return;
		}

		switch (id) {
		case "tfCodigoAnpCombustivel":
			ndiCombustivel.setCodigoAnp(ObjectConverter
					.stringToInteger((String) obj));

			break;
		case "tfCodifCombustivel":
			ndiCombustivel.setCodif((String) obj);

			break;
		case "tfQtdeTempAmbienteCombustivel":
			ndiCombustivel.setQuantidadeTempAmbiente(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfUfConsumoCombustivel":
			ndiCombustivel.setUfConsumo((String) obj);

			break;
		case "tfBcCideCombustivel":
			ndiCombustivel.setBaseCalculoCide(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfAliquotaCideCombustivel":
			ndiCombustivel.setAliquotaCide(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "tfValorCideCombustivel":
			ndiCombustivel.setValorCide(ObjectConverter
					.stringToValue((String) obj));

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe().setNfeDetEspecificoCombustivel(
				ndiCombustivel);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndeVeiculoSetarValor(String id, Object obj) {
		// TODO ndeVeiculoSetarValor

		NfeDetEspecificoVeiculoEntity ndiVeiculo = this.nfeCabecalho
				.getNfeDetalhe().getNfeDetEspecificoVeiculo();

		if (ndiVeiculo == null) {
			return;
		}

		switch (id) {
		case "tfTipoOperacaoVeiculo":
			ndiVeiculo.setTipoOperacao((String) obj);

			break;
		case "tfChassiVeiculo":
			ndiVeiculo.setChassi((String) obj);

			break;
		case "tfCodigoCorVeiculo":
			ndiVeiculo.setCodigoCor((String) obj);

			break;
		case "tfDescricaoCorVeiculo":
			ndiVeiculo.setDescricaoCor((String) obj);

			break;
		case "tfPotenciaMotorVeiculo":
			ndiVeiculo.setPotenciaMotor((String) obj);

			break;
		case "tfCilindradasVeiculo":
			ndiVeiculo.setCilindradas((String) obj);

			break;
		case "tfPesoLiquidoVeiculo":
			ndiVeiculo.setPesoLiquido((String) obj);

			break;
		case "tfPesoBrutoVeiculo":
			ndiVeiculo.setPesoBruto((String) obj);

			break;
		case "tfNumeroSerieVeiculo":
			ndiVeiculo.setNumeroSerie((String) obj);

			break;
		case "tfCombustivelVeiculo":
			ndiVeiculo.setTipoCombustivel((String) obj);

			break;
		case "tfNumeroMotorVeiculo":
			ndiVeiculo.setNumeroMotor((String) obj);

			break;
		case "tfCapacidadeTracaoVeiculo":
			ndiVeiculo.setCapacidadeMaximaTracao((String) obj);

			break;
		case "tfDistanciaEixosVeiculo":
			ndiVeiculo.setDistanciaEixos((String) obj);

			break;
		case "tfAnoModeloVeiculo":
			ndiVeiculo.setAnoModelo((String) obj);

			break;
		case "tfAnoFabricacaoVeiculo":
			ndiVeiculo.setAnoFabricacao((String) obj);

			break;
		case "tfTipoPinturaVeiculo":
			ndiVeiculo.setTipoPintura((String) obj);

			break;
		case "tfTipoVeiculo":
			ndiVeiculo.setTipoVeiculo((String) obj);

			break;
		case "tfEspecieVeiculo":
			ndiVeiculo.setEspecieVeiculo((String) obj);

			break;
		case "tfCondicaoVinVeiculo":
			ndiVeiculo.setCondicaoVin((String) obj);

			break;
		case "tfCondicaoVeiculo":
			ndiVeiculo.setCondicaoVeiculo((String) obj);

			break;
		case "tfCodigoMarcaModeloVeiculo":
			ndiVeiculo.setCodigoMarcaModelo((String) obj);

			break;
		case "tfCodigoCorDenatranVeiculo":
			ndiVeiculo.setCor((String) obj);

			break;
		case "tfLotacaoVeiculo":
			ndiVeiculo
					.setLotacao(ObjectConverter.stringToInteger((String) obj));

			break;
		case "tfRestricaoVeiculo":
			ndiVeiculo.setRestricao((String) obj);

			break;
		}

		Integer index = this.subView.getSfNfeDetalhe().getDados()
				.indexOf(this.nfeCabecalho.getNfeDetalhe());

		this.subView.getSfNfeDetalhe().getDados()
				.remove(this.nfeCabecalho.getNfeDetalhe());

		this.nfeCabecalho.getNfeDetalhe()
				.setNfeDetEspecificoVeiculo(ndiVeiculo);

		this.subView.getSfNfeDetalhe().getDados()
				.add(index, this.nfeCabecalho.getNfeDetalhe());
	}

	public void ndeMedicamentoSetarValor(String id, Object obj) {
		// TODO ndeMedicamentoSetarValor

		// NfeDetEspecificoCombustivelEntity ndiCombustivel =
		// this.nfeDetalheSelecionado
		// .getNfeDetEspecificoCombustivel();

		if (this.ndeMedicamentoSelecionado == null) {
			return;
		}

		switch (id) {
		case "tfNumeroLoteMedicamento":
			this.ndeMedicamentoSelecionado.setNumeroLote((String) obj);

			break;
		case "tfQuantidadeLoteMedicamento":
			this.ndeMedicamentoSelecionado.setQuantidadeLote(ObjectConverter
					.stringToValue((String) obj));

			break;
		case "pdfDataFabricacaoMedicamento":
			this.ndeMedicamentoSelecionado.setDataFabricacao((Date) obj);

			break;
		case "pdfDataValidadeMedicamento":
			this.ndeMedicamentoSelecionado.setDataValidade((Date) obj);

			break;
		case "tfPrecoMaximoConsumidorMedicamento":
			this.ndeMedicamentoSelecionado
					.setPrecoMaximoConsumidor(ObjectConverter
							.stringToValue((String) obj));

			break;
		}

		Integer index = this.subView.getSfNdeMedicamento().getDados()
				.indexOf(this.ndeMedicamentoSelecionado);

		this.subView.getSfNdeMedicamento().getDados()
				.remove(this.ndeMedicamentoSelecionado);

		this.nfeCabecalho.getNfeDetalhe().getNdeMedicamentoList()
				.remove(this.ndeMedicamentoSelecionado);

		this.ndeMedicamentoSelecionado.setNfeDetalhe(this.nfeCabecalho
				.getNfeDetalhe());

		this.subView.getSfNdeMedicamento().getDados()
				.add(index, this.ndeMedicamentoSelecionado);

		this.nfeCabecalho.getNfeDetalhe().getNdeMedicamentoList()
				.add(this.ndeMedicamentoSelecionado);
	}

	public void ndeArmamentoSetarValor(String id, Object obj) {
		// TODO ndeMedicamentoSetarValor

		if (this.ndeArmamentoSelecionado == null) {
			return;
		}

		switch (id) {
		case "tfTipoArmaArmamento":
			this.ndeArmamentoSelecionado.setTipoArma((String) obj);

			break;
		case "tfNumeroSerieArmaArmamento":
			this.ndeArmamentoSelecionado.setNumeroSerieArma((String) obj);

			break;
		case "tfNumeroSerieCanoArmamento":
			this.ndeArmamentoSelecionado.setNumeroSerieCano((String) obj);

			break;
		case "tfDescricaoArmamento":
			this.ndeArmamentoSelecionado.setDescricao((String) obj);

			break;
		}

		Integer index = this.subView.getSfNdeArmamento().getDados()
				.indexOf(this.ndeArmamentoSelecionado);

		this.subView.getSfNdeArmamento().getDados()
				.remove(this.ndeArmamentoSelecionado);

		this.nfeCabecalho.getNfeDetalhe().getNdeArmamentoList()
				.remove(this.ndeArmamentoSelecionado);

		this.ndeArmamentoSelecionado.setNfeDetalhe(this.nfeCabecalho
				.getNfeDetalhe());

		this.subView.getSfNdeArmamento().getDados()
				.add(index, this.ndeArmamentoSelecionado);

		this.nfeCabecalho.getNfeDetalhe().getNdeArmamentoList()
				.add(this.ndeArmamentoSelecionado);
	}

	/**
	 * LIMPAR
	 * 
	 * @param item
	 */

	private void nfeDetalheLimpar() {
		this.nfeCabecalho.setNfeDetalhe(new NfeDetalheEntity());
		// this.nfeDetalheSelecionado = new NfeDetalheEntity();

		this.subView.getTfNumeroItem().setValue(
				this.nfeCabecalho.getNfeDetalhe().getNumeroItem().toString());
		this.subView.getTfCodigoProduto().setValue(
				this.nfeCabecalho.getNfeDetalhe().getCodigoProduto());
		this.subView.getTfGtin().setValue(
				this.nfeCabecalho.getNfeDetalhe().getGtin());
		// this.subView.getTfNomeProduto().setValue(nfeDetalhe.getNomeProduto());
		// this.subView.getCbLivro().setValue(this.pEntity.getLivro());
		// this.subView.getMtoProduto().setValue(new Produto());

		// this.subView.getMtoProduto().setValue(nfeDetalhe.getProduto());
		this.subView.getTfNcm().setValue(
				this.nfeCabecalho.getNfeDetalhe().getNcm());
		this.subView.getTfExTipi().setValue(
				this.nfeCabecalho.getNfeDetalhe().getExTipi().toString());
		this.subView.getTfCfop().setValue(
				this.nfeCabecalho.getNfeDetalhe().getCfop().toString());
		this.subView.getTfUnidadeComercial().setValue(
				this.nfeCabecalho.getNfeDetalhe().getUnidadeComercial());
		this.subView.getTfQuantidadeComercial().setValue(
				this.nfeCabecalho.getNfeDetalhe().getQuantidadeComercial()
						.toString());
		this.subView.getTfValorUnitarioComercial().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorUnitarioComercial()
						.toString());
		this.subView.getTfValorBrutoProduto().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorBrutoProduto()
						.toString());
		this.subView.getTfGtinUnidadeTributavel().setValue(
				this.nfeCabecalho.getNfeDetalhe().getGtinUnidadeTributavel());
		this.subView.getTfUnidadeTributavel().setValue(
				this.nfeCabecalho.getNfeDetalhe().getUnidadeTributavel());
		this.subView.getTfQuantidadeTributavel().setValue(
				this.nfeCabecalho.getNfeDetalhe().getQuantidadeTributavel()
						.toString());
		this.subView.getTfValorUnitarioTributavel().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorUnitarioTributavel()
						.toString());
		this.subView.getTfValorFrete().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorFrete().toString());
		this.subView.getTfValorSeguro().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorSeguro().toString());
		this.subView.getTfValorDesconto()
				.setValue(
						this.nfeCabecalho.getNfeDetalhe().getValorDesconto()
								.toString());
		this.subView.getTfValorOutrasDespesas().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorOutrasDespesas()
						.toString());
		this.subView.getTfEntraTotal().setValue(
				this.nfeCabecalho.getNfeDetalhe().getEntraTotal());
		this.subView.getTfValorSubtotal()
				.setValue(
						this.nfeCabecalho.getNfeDetalhe().getValorSubtotal()
								.toString());
		this.subView.getTfValorTotal().setValue(
				this.nfeCabecalho.getNfeDetalhe().getValorTotal().toString());
		this.subView.getTfNumeroPedidoCompra().setValue(
				this.nfeCabecalho.getNfeDetalhe().getNumeroPedidoCompra());
		this.subView.getTfItemPedidoCompra().setValue(
				this.nfeCabecalho.getNfeDetalhe().getItemPedidoCompra()
						.toString());
		this.subView.getTfInformacoesAdicionais().setValue(
				this.nfeCabecalho.getNfeDetalhe().getInformacoesAdicionais());
	}

	private void ndiCofinsLimpar() {
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

	private void ndiIcmsLimpar() {
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

	private void ndiIiLimpar() {
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

	private void ndiIpiLimpar() {
		NfeDetalheImpIpiEntity ndiIpi = new NfeDetalheImpIpiEntity();

		this.subView.getTfCstIpi().setValue(ndiIpi.getCstIpi());
		this.subView.getTfBaseCalculoBcIpi().setValue(
				ndiIpi.getValorBaseCalculoIpi().toString());
		this.subView.getTfAliquotaIpi().setValue(
				ndiIpi.getAliquotaIpi().toString());
		this.subView.getTfQtdUndTributavelIpi().setValue(
				ndiIpi.getQuantidadeUnidadeTributavel().toString());
		this.subView.getTfValorUndTributavelIpi().setValue(
				ndiIpi.getValorUnidadeTributavel().toString());
		this.subView.getTfValorIpi().setValue(ndiIpi.getValorIpi().toString());
		this.subView.getTfEnquadramentoIpi().setValue(
				ndiIpi.getEnquadramentoIpi());
		this.subView.getTfEnquadramentoLegalIpi().setValue(
				ndiIpi.getEnquadramentoLegalIpi());
		this.subView.getTfCnpjProdutorIpi().setValue(
				ndiIpi.getCnpjProdutorIpi());
		this.subView.getTfQtdSeloIpi().setValue(
				ndiIpi.getQuantidadeSeloIpi().toString());
		this.subView.getTfCodigoSeloIpi().setValue(ndiIpi.getCodigoSeloIpi());
	}

	private void ndiIssqnLimpar() {
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

	private void ndiPisLimpar() {
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

	private void ndeCombustivelLimpar() {
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

	private void ndeVeiculoLimpar() {
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

	public void ndeMedicamentoLimpar() {
		this.subView.carregarSfNdeMedicamento(this.nfeCabecalho.getNfeDetalhe()
				.getNdeMedicamentoList());

		this.ndeMedicamentoSelecionado = new NfeDetEspecificoMedicamentoEntity();

		this.subView.getTfNumeroLoteMedicamento().setValue(
				this.ndeMedicamentoSelecionado.getNumeroLote());
		this.subView.getTfQuantidadeLoteMedicamento().setValue(
				this.ndeMedicamentoSelecionado.getQuantidadeLote().toString());
		this.subView.getPdfDataFabricacaoMedicamento().setValue(
				this.ndeMedicamentoSelecionado.getDataFabricacao());
		this.subView.getPdfDataValidadeMedicamento().setValue(
				this.ndeMedicamentoSelecionado.getDataValidade());
		this.subView.getTfPrecoMaximoConsumidorMedicamento().setValue(
				this.ndeMedicamentoSelecionado.getPrecoMaximoConsumidor()
						.toString());

		this.subView.getGlNdeMedicamento().setEnabled(false);
	}

	/**
	 * COMBOS
	 */

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private OperacaoFiscalDAO operacaoFiscalDAO;

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<Cliente> model1 = new DefaultManyToOneComboModel<Cliente>(
					ClienteListController.class, this.clienteDAO,
					super.getMainController());

			this.subView.getMtoCliente().setModel(model1);

			DefaultManyToOneComboModel<Produto> model2 = new DefaultManyToOneComboModel<Produto>(
					ProdutoListController.class, this.produtoDAO,
					super.getMainController());

			this.subView.getMtoProduto().setModel(model2);

			DefaultManyToOneComboModel<OperacaoFiscal> model3 = new DefaultManyToOneComboModel<OperacaoFiscal>(
					OperacaoFiscalListController.class, this.operacaoFiscalDAO,
					super.getMainController());

			this.subView.getMtoOperacaoFiscal().setModel(model3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}