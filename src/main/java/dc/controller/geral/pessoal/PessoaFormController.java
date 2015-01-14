package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.CrtEn;
import dc.control.enums.FormaDescontoEn;
import dc.control.enums.IndicadorPrecoEn;
import dc.control.enums.LocalizacaoEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoFreteEn;
import dc.control.enums.TipoPessoaEn;
import dc.control.enums.TipoRegimeEn;
import dc.control.enums.TipoSanguineoEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.ObjectUtils;
import dc.control.util.StringUtils;
import dc.control.util.classes.PessoaUtils;
import dc.control.validator.DotErpException;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.tributario.OperacaoFiscalListController;
import dc.entidade.contabilidade.ContabilContaEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.pessoal.AtividadeForCliEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.geral.pessoal.PessoaContatoEntity;
import dc.entidade.geral.pessoal.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.PessoaEntity;
import dc.entidade.geral.pessoal.PessoaFisicaEntity;
import dc.entidade.geral.pessoal.PessoaJuridicaEntity;
import dc.entidade.geral.pessoal.SituacaoForCliEntity;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.pessoal.PessoaBusiness;
import dc.model.business.geral.pessoal.PessoaContatoBusiness;
import dc.model.business.geral.pessoal.PessoaEnderecoBusiness;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.geral.pessoal.AtividadeForCliDAO;
import dc.servicos.dao.geral.pessoal.EstadoCivilDAO;
import dc.servicos.dao.geral.pessoal.SituacaoForCliDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.PessoaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PessoaFormController extends CRUDFormController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PessoaFormView subView;

	/**
	 * ENTITY
	 */

	private PessoaEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private PessoaBusiness<PessoaEntity> business;

	@Autowired
	private PessoaContatoBusiness<PessoaContatoEntity> pessoaContatoBusiness;

	@Autowired
	private PessoaEnderecoBusiness<PessoaEnderecoEntity> pessoaEnderecoBusiness;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	@Autowired
	private SituacaoForCliDAO situacaoForCliDAO;

	@Autowired
	private AtividadeForCliDAO atividadeForCliDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private OperacaoFiscalDAO operacaoFiscalDAO;

	/**
	 * CONSTRUTOR
	 */

	public PessoaFormController() {
		// TODO Auto-generated constructor stub
	}

	public PessoaBusiness<PessoaEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Pessoa";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
	public PessoaEntity getModelBean() {
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PessoaFormView(this);

			DefaultManyToOneComboModel<EstadoCivilEntity> modelEstadoCivil = new DefaultManyToOneComboModel<EstadoCivilEntity>(
					EstadoCivilListController.class, this.estadoCivilDAO,
					super.getMainController());

			this.subView.getMocEstadoCivil().setModel(modelEstadoCivil);

			DefaultManyToOneComboModel<SituacaoForCliEntity> modelSituacaoForCli = new DefaultManyToOneComboModel<SituacaoForCliEntity>(
					SituacaoColaboradorListController.class,
					this.situacaoForCliDAO, super.getMainController());

			this.subView.getMocClienteSituacao().setModel(modelSituacaoForCli);
			this.subView.getMocFornecedorSituacaoForCli().setModel(
					modelSituacaoForCli);

			DefaultManyToOneComboModel<AtividadeForCliEntity> modelAtividadeForCli = new DefaultManyToOneComboModel<AtividadeForCliEntity>(
					AtividadeForCliListController.class,
					this.atividadeForCliDAO, super.getMainController());

			this.subView.getMocClienteAtividade()
					.setModel(modelAtividadeForCli);
			this.subView.getMocFornecedorAtividadeForCli().setModel(
					modelAtividadeForCli);

			DefaultManyToOneComboModel<ContabilContaEntity> modelContabilConta = new DefaultManyToOneComboModel<ContabilContaEntity>(
					ContabilContaListController.class, this.contabilContaDAO,
					super.getMainController());

			this.subView.getMocClienteContaContabil().setModel(
					modelContabilConta);
			this.subView.getMocFornecedorContabilConta().setModel(
					modelContabilConta);
			this.subView.getMocTransportadoraContabilConta().setModel(
					modelContabilConta);

			DefaultManyToOneComboModel<OperacaoFiscalEntity> modelOperacaoFiscal = new DefaultManyToOneComboModel<OperacaoFiscalEntity>(
					OperacaoFiscalListController.class, this.operacaoFiscalDAO,
					super.getMainController());

			this.subView.getMocClienteOperacaoFiscal().setModel(
					modelOperacaoFiscal);

			carregarTipoRegime();
			carregarCnh();
			carregarRaca();
			carregarCategoriaReservista();
			carregarCrt();
			carregarTipoSanguineo();
			carregarTipoPessoa();
			carregarSexo();

			carregarGerarFinanceiro();
			carregarIndicadorPreco();
			carregarTipoFrete();
			carregarFormaDesconto();

			carregarSofreRetencao();
			carregarGerarFaturamento();
			carregarOptanteSimples();
			carregarLocalizacao();

			// Valores iniciais

			this.subView.getCbTipoPessoa().setValue(TipoPessoaEn.F);
			this.subView.getOgSexo().setValue(SexoEn.F);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			PessoaUtils.validateRequiredFields(this.subView);
			// PessoaUtils.validateFieldValue(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) this.subView
					.getCbTipoPessoa().getValue();

			this.entity.setTipoPessoa(tipoPessoaEn);

			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setEmail(this.subView.getTfEmail().getValue());
			this.entity.setSite(this.subView.getTfSite().getValue());

			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			if (this.entity.getTipoPessoa().equals(TipoPessoaEn.F)) {
				savePessoaFisica();
			} else if (this.entity.getTipoPessoa().equals(TipoPessoaEn.J)) {
				savePessoaJuridica();
			}

			this.entity.setTipoCliente(this.subView.getCkCliente().getValue()
					.equals(Boolean.TRUE) ? "1" : "0");

			if (this.entity.getTipoCliente().equals("1")) {
				saveCliente();
			}

			this.entity.setTipoColaborador(this.subView.getCkColaborador()
					.getValue());
			this.entity.setTipoFornecedor(this.subView.getCkFornecedor()
					.getValue());

			if (this.entity.getTipoFornecedor()) {
				saveFornecedor();
			}

			this.entity.setTipoTransportadora(this.subView
					.getCkTransportadora().getValue());

			if (this.entity.getTipoTransportadora()) {
				saveTransportadora();
			}

			// PessoaContato

			List<PessoaContatoEntity> auxLista1 = this.subView
					.getSfPessoaContato().getDados();

			this.entity.setPessoaContatoList(auxLista1);

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista2 = this.subView
					.getSfPessoaEndereco().getDados();

			this.entity.setPessoaEnderecoList(auxLista2);

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	private void savePessoaFisica() throws Exception {
		PessoaFisicaEntity ent = (this.entity.getPessoaFisica() == null ? new PessoaFisicaEntity()
				: this.entity.getPessoaFisica());
		ent.setPessoa(this.entity);

		ent.setCpf(this.subView.getMtfCpf().getValue());
		ent.setDataNascimento(this.subView.getPdfDataNascimento().getValue());
		ent.setNaturalidade(this.subView.getTfNaturalidade().getValue());
		ent.setNacionalidade(this.subView.getTfNacionalidade().getValue());
		ent.setNomeMae(this.subView.getTfNomeMae().getValue());
		ent.setNomePai(this.subView.getTfNomePai().getValue());
		ent.setRg(this.subView.getTfNumeroRg().getValue());
		ent.setOrgaoRg(this.subView.getTfOrgaoEmissor().getValue());
		ent.setDataEmissaoRg(this.subView.getPdfDataEmissaoRg().getValue());
		ent.setCnhNumero(this.subView.getTfCnh().getValue());

		CnhEn cnhEn = (CnhEn) this.subView.getCbCategoriaCnh().getValue();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			ent.setCnh(cnhEn);
		}

		ent.setCnhVencimento(this.subView.getPdfDataCnhEmissao().getValue());

		EstadoCivilEntity estadoCivil = this.subView.getMocEstadoCivil()
				.getValue();

		if (ObjectUtils.isNotBlank(estadoCivil)) {
			ent.setEstadoCivil(estadoCivil);
		}

		RacaEn racaEn = (RacaEn) this.subView.getCbRaca().getValue();

		if (ObjectUtils.isNotBlank(racaEn)) {
			ent.setRaca(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = (TipoSanguineoEn) this.subView
				.getCbTipoSanguineo().getValue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			ent.setTipoSangue(tipoSanguineoEn);
		}

		ent.setReservistaNumero(this.subView.getTfNumeroReservista().getValue());

		CategoriaReservistaEn categoriaReservistaEn = (CategoriaReservistaEn) this.subView
				.getCbCategoriaReservista().getValue();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			ent.setReservistaCategoria(categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.subView.getOgSexo().getValue();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			ent.setSexo(sexoEn);
		}

		ent.setTituloEleitoralNumero(this.subView.getTfTituloEleitor()
				.getValue());
		ent.setTituloEleitoralSecao(NumberUtils.toInt(this.subView
				.getTfTituloSecao().getValue()));
		ent.setTituloEleitoralZona(NumberUtils.toInt(this.subView
				.getTfTituloZona().getValue()));

		this.entity.setPessoaFisica(ent);
	}

	private void savePessoaJuridica() throws Exception {
		PessoaJuridicaEntity ent = (this.entity.getPessoaJuridica() == null ? new PessoaJuridicaEntity()
				: this.entity.getPessoaJuridica());
		ent.setPessoa(this.entity);

		ent.setFantasia(this.subView.getTfFantasia().getValue());
		ent.setCnpj(this.subView.getMtfCnpj().getValue());
		ent.setInscricaoEstadual(this.subView.getTfInscricaoEstadual()
				.getValue());
		ent.setInscricaoMunicipal(this.subView.getTfInscricaoMunicipal()
				.getValue());
		ent.setDataConstituicao(this.subView.getPdfDataConstituicao()
				.getValue());
		ent.setSuframa(this.subView.getTfSuframa().getValue());

		TipoRegimeEn tipoRegimeEn = (TipoRegimeEn) this.subView
				.getCbTipoRegime().getValue();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			ent.setTipoRegime(tipoRegimeEn);
		}

		CrtEn crtEn = (CrtEn) this.subView.getCbCrt().getValue();

		if (ObjectUtils.isNotBlank(crtEn)) {
			ent.setCrt(crtEn);
		}

		this.entity.setPessoaJuridica(ent);
	}

	private void saveCliente() throws Exception {
		ClienteEntity ent = (this.entity.getCliente() == null ? new ClienteEntity()
				: this.entity.getCliente());
		ent.setPessoa(this.entity);

		ent.setObservacao(this.subView.getTaClienteObservacao().getValue());

		String limiteCredito = this.subView.getTfClienteLimiteCredito()
				.getValue();

		if (NumberUtils.isNumber(limiteCredito)) {
			ent.setLimiteCredito(NumberUtils.createBigDecimal(limiteCredito));
		} else {
			ent.setLimiteCredito(new BigDecimal(0));
		}

		String porcentoDesconto = this.subView.getTfClienteTaxaDesconto()
				.getValue();

		if (NumberUtils.isNumber(porcentoDesconto)) {
			ent.setPorcentoDesconto(NumberUtils
					.createBigDecimal(porcentoDesconto));
		} else {
			ent.setPorcentoDesconto(new BigDecimal(0));
		}

		FormaDescontoEn formaDesconto = (FormaDescontoEn) this.subView
				.getCbClienteFormaDesconto().getValue();

		ent.setFormaDesconto(formaDesconto);

		TipoFreteEn tipoFrete = (TipoFreteEn) this.subView
				.getCbClienteTipoFrete().getValue();

		ent.setTipoFrete(tipoFrete);

		IndicadorPrecoEn indicadorPreco = (IndicadorPrecoEn) this.subView
				.getCbClienteIndicadorPreco().getValue();

		ent.setIndicadorPreco(indicadorPreco);

		SimNaoEn gerarFinanceiro = (SimNaoEn) this.subView
				.getCbClienteGerarFinanceiro().getValue();

		ent.setGerarFinanceiro(gerarFinanceiro);

		ent.setContaTomador(this.subView.getTfClienteContaTomador().getValue());
		ent.setDesde(this.subView.getPdfClienteDesde().getValue());
		ent.setSituacaoForCli(this.subView.getMocClienteSituacao().getValue());
		ent.setAtividadeForCli(this.subView.getMocClienteAtividade().getValue());
		ent.setContabilConta(this.subView.getMocClienteContaContabil()
				.getValue());
		ent.setOperacaoFiscal(this.subView.getMocClienteOperacaoFiscal()
				.getValue());

		this.entity.setCliente(ent);
	}

	private void saveColaborador() throws Exception {

	}

	private void saveFornecedor() throws Exception {
		FornecedorEntity ent = (this.entity.getFornecedor() == null ? new FornecedorEntity()
				: this.entity.getFornecedor());
		ent.setPessoa(this.entity);

		ent.setSituacaoForCli(this.subView.getMocFornecedorSituacaoForCli()
				.getValue());
		ent.setAtividadeForCli(this.subView.getMocFornecedorAtividadeForCli()
				.getValue());
		ent.setContabilConta(this.subView.getMocFornecedorContabilConta()
				.getValue());
		ent.setDesde(this.subView.getPdfFornecedorDesde().getValue());
		ent.setContaRemetente(this.subView.getTfFornecedorContaRemetente()
				.getValue());

		SimNaoEn geraFaturamento = (SimNaoEn) this.subView
				.getCbFornecedorGeraFaturamento().getValue();

		ent.setGeraFaturamento(geraFaturamento);

		SimNaoEn optanteSimplesNacional = (SimNaoEn) this.subView
				.getCbFornecedorOptanteSimples().getValue();

		ent.setOptanteSimplesNacional(optanteSimplesNacional);

		LocalizacaoEn localizacao = (LocalizacaoEn) this.subView
				.getCbFornecedorLocalizacao().getValue();

		ent.setLocalizacao(localizacao);

		SimNaoEn sofreRetencao = (SimNaoEn) this.subView
				.getCbFornecedorSofreRetencao().getValue();

		ent.setSofreRetencao(sofreRetencao);

		String prazoMediaEntrega = this.subView
				.getTfFornecedorPrazoMedioEntrega().getValue();

		if (NumberUtils.isNumber(prazoMediaEntrega)) {
			ent.setPrazoMedioEntrega(NumberUtils
					.createBigDecimal(prazoMediaEntrega));
		} else {
			ent.setPrazoMedioEntrega(new BigDecimal(0));
		}

		String numDiasPrimeiroVencimento = this.subView
				.getTfFornecedorNumDiasPrimeiroVenc().getValue();

		if (NumberUtils.isNumber(numDiasPrimeiroVencimento)) {
			ent.setNumDiasPrimeiroVencimento(NumberUtils
					.toInt(numDiasPrimeiroVencimento));
		} else {
			ent.setNumDiasPrimeiroVencimento(0);
		}

		String numDiasIntervalo = this.subView
				.getTfFornecedorNumDiasIntervalo().getValue();

		if (NumberUtils.isNumber(numDiasIntervalo)) {
			ent.setNumDiasIntervalo(NumberUtils.toInt(numDiasIntervalo));
		} else {
			ent.setNumDiasIntervalo(0);
		}

		String quantidadeParcelas = this.subView
				.getTfFornecedorQuantidadeParcelas().getValue();

		if (NumberUtils.isNumber(quantidadeParcelas)) {
			ent.setQuantidadeParcelas(NumberUtils.toInt(quantidadeParcelas));
		} else {
			ent.setQuantidadeParcelas(0);
		}

		ent.setChequeNominalA(this.subView.getTfFornecedorChequeNominalA()
				.getValue());
		ent.setObservacao(this.subView.getTaFornecedorObservacao().getValue());

		this.entity.setFornecedor(ent);
	}

	private void saveTransportadora() throws Exception {
		TransportadoraEntity ent = (this.entity.getTransportadora() == null ? new TransportadoraEntity()
				: this.entity.getTransportadora());
		ent.setPessoa(this.entity);

		ent.setObservacao(this.subView.getTaTransportadoraObservacao()
				.getValue());
		ent.setContaContabil(this.subView.getMocTransportadoraContabilConta()
				.getValue());

		this.entity.setTransportadora(ent);
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			// PessoaContato

			List<PessoaContatoEntity> auxLista1 = this.pessoaContatoBusiness
					.list(this.entity);

			this.entity.setPessoaContatoList(auxLista1);

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista2 = this.pessoaEnderecoBusiness
					.list(this.entity);

			this.entity.setPessoaEnderecoList(auxLista2);

			this.subView.getTfNome().setValue(this.entity.getNome());

			this.subView.getCbTipoPessoa()
					.setValue(this.entity.getTipoPessoa());

			this.subView.getTfEmail().setValue(this.entity.getEmail());
			this.subView.getTfSite().setValue(this.entity.getSite());

			if (this.entity.getTipoPessoa().equals(TipoPessoaEn.F)) {
				this.entity.setPessoaFisica(loadPessoaFisica());
			} else if (this.entity.getTipoPessoa().equals(TipoPessoaEn.J)) {
				this.entity.setPessoaJuridica(loadPessoaJuridica());
			}

			this.subView.getCkCliente().setValue(
					this.entity.getTipoCliente().equals("0") ? Boolean.FALSE
							: Boolean.TRUE);

			if (this.entity.getTipoCliente().equals("1")) {
				loadCliente();
			}

			this.subView.getCkColaborador().setValue(
					this.entity.getTipoColaborador());

			this.subView.getCkFornecedor().setValue(
					this.entity.getTipoFornecedor());

			if (this.entity.getTipoFornecedor()) {
				loadFornecedor();
			}

			this.subView.getCkTransportadora().setValue(
					this.entity.getTipoTransportadora());

			if (this.entity.getTipoTransportadora()) {
				loadTransportadora();
			}

			// PessoaContato

			this.subView.getSfPessoaContato().fillWith(
					this.entity.getPessoaContatoList());

			// PessoaEndereco

			for (PessoaEnderecoEntity ent : this.entity.getPessoaEnderecoList()) {
				if (StringUtils.isNotBlank(ent.getSiglaUf())) {
					UfEntity uf = this.ufBusiness.find(ent.getIdUf());

					ent.setUf(uf);
				}
			}

			this.subView.getSfPessoaEndereco().fillWith(
					this.entity.getPessoaEnderecoList());

			visualizarAba();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PessoaFisicaEntity loadPessoaFisica() {
		PessoaFisicaEntity pf = this.entity.getPessoaFisica();

		this.subView.getMtfCpf().setValue(pf.getCpf());
		this.subView.getPdfDataNascimento().setValue(pf.getDataNascimento());
		this.subView.getTfNaturalidade().setValue(pf.getNaturalidade());
		this.subView.getTfNacionalidade().setValue(pf.getNacionalidade());
		this.subView.getTfNomeMae().setValue(pf.getNomeMae());
		this.subView.getTfNomePai().setValue(pf.getNomePai());
		this.subView.getTfNumeroRg().setValue(pf.getRg());
		this.subView.getTfOrgaoEmissor().setValue(pf.getOrgaoRg());
		this.subView.getPdfDataEmissaoRg().setValue(pf.getDataEmissaoRg());
		this.subView.getTfCnh().setValue(pf.getCnhNumero());

		CnhEn cnhEn = pf.getCnh();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			this.subView.getCbCategoriaCnh().setValue(cnhEn);
		}

		this.subView.getPdfDataCnhEmissao().setValue(pf.getCnhVencimento());
		this.subView.getMocEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (ObjectUtils.isNotBlank(racaEn)) {
			this.subView.getCbRaca().setValue(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = pf.getTipoSangue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			this.subView.getCbTipoSanguineo().setValue(tipoSanguineoEn);
		}

		this.subView.getTfNumeroReservista().setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			this.subView.getCbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.entity.getPessoaFisica().getSexo();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			this.subView.getOgSexo().setValue(sexoEn);
		}

		this.subView.getTfTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTfTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTfTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());

		return pf;
	}

	private PessoaJuridicaEntity loadPessoaJuridica() {
		PessoaJuridicaEntity pj = this.entity.getPessoaJuridica();

		this.subView.getTfFantasia().setValue(pj.getFantasia());
		this.subView.getMtfCnpj().setValue(pj.getCnpj());
		this.subView.getTfInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTfInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getPdfDataConstituicao()
				.setValue(pj.getDataConstituicao());
		this.subView.getTfSuframa().setValue(pj.getSuframa());

		TipoRegimeEn tipoRegimeEn = pj.getTipoRegime();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			this.subView.getCbTipoRegime().setValue(tipoRegimeEn);
		}

		CrtEn crtEn = pj.getCrt();

		if (ObjectUtils.isNotBlank(crtEn)) {
			this.subView.getCbCrt().setValue(crtEn);
		}

		return pj;
	}

	private void loadCliente() throws Exception {
		ClienteEntity ent = this.entity.getCliente();

		this.subView.getTaClienteObservacao().setValue(ent.getObservacao());
		this.subView.getTfClienteLimiteCredito().setValue(
				ent.getLimiteCredito().toString());
		this.subView.getTfClienteTaxaDesconto().setValue(
				ent.getPorcentoDesconto().toString());
		this.subView.getCbClienteFormaDesconto().setValue(
				ent.getFormaDesconto());
		this.subView.getCbClienteTipoFrete().setValue(ent.getTipoFrete());
		this.subView.getCbClienteIndicadorPreco().setValue(
				ent.getIndicadorPreco());
		this.subView.getCbClienteGerarFinanceiro().setValue(
				ent.getGerarFinanceiro());
		this.subView.getTfClienteContaTomador().setValue(ent.getContaTomador());
		this.subView.getPdfClienteDesde().setValue(ent.getDesde());

		SituacaoForCliEntity situacaoForCli = ent.getSituacaoForCli();

		if (ObjectUtils.isNotBlank(situacaoForCli)) {
			this.subView.getMocClienteSituacao().setValue(situacaoForCli);
		}

		AtividadeForCliEntity atividadeForCli = ent.getAtividadeForCli();

		if (ObjectUtils.isNotBlank(atividadeForCli)) {
			this.subView.getMocClienteAtividade().setValue(atividadeForCli);
		}

		ContabilContaEntity contaContabil = ent.getContabilConta();

		if (ObjectUtils.isNotBlank(contaContabil)) {
			this.subView.getMocClienteContaContabil().setValue(contaContabil);
		}

		OperacaoFiscalEntity operacaoFiscal = ent.getOperacaoFiscal();

		if (ObjectUtils.isNotBlank(operacaoFiscal)) {
			this.subView.getMocClienteOperacaoFiscal().setValue(operacaoFiscal);
		}
	}

	private void loadColaborador() throws Exception {

	}

	private void loadFornecedor() throws Exception {
		FornecedorEntity ent = this.entity.getFornecedor();

		SituacaoForCliEntity situacaoForCli = ent.getSituacaoForCli();

		if (ObjectUtils.isNotBlank(situacaoForCli)) {
			this.subView.getMocFornecedorSituacaoForCli().setValue(
					situacaoForCli);
		}

		AtividadeForCliEntity atividadeForCli = ent.getAtividadeForCli();

		if (ObjectUtils.isNotBlank(atividadeForCli)) {
			this.subView.getMocFornecedorAtividadeForCli().setValue(
					atividadeForCli);
		}

		ContabilContaEntity contaContabil = ent.getContabilConta();

		if (ObjectUtils.isNotBlank(contaContabil)) {
			this.subView.getMocFornecedorContabilConta()
					.setValue(contaContabil);
		}

		this.subView.getPdfFornecedorDesde().setValue(ent.getDesde());
		this.subView.getTfFornecedorContaRemetente().setValue(
				ent.getContaRemetente());
		this.subView.getCbFornecedorGeraFaturamento().setValue(
				ent.getGeraFaturamento());
		this.subView.getCbFornecedorOptanteSimples().setValue(
				ent.getOptanteSimplesNacional());
		this.subView.getCbFornecedorLocalizacao()
				.setValue(ent.getLocalizacao());
		this.subView.getCbFornecedorSofreRetencao().setValue(
				ent.getSofreRetencao());
		this.subView.getTfFornecedorPrazoMedioEntrega().setValue(
				ent.getPrazoMedioEntrega().toString());
		this.subView.getTfFornecedorNumDiasPrimeiroVenc().setValue(
				ent.getNumDiasPrimeiroVencimento().toString());
		this.subView.getTfFornecedorNumDiasIntervalo().setValue(
				ent.getNumDiasIntervalo().toString());
		this.subView.getTfFornecedorQuantidadeParcelas().setValue(
				ent.getQuantidadeParcelas().toString());
		this.subView.getTfFornecedorChequeNominalA().setValue(
				ent.getChequeNominalA());
		this.subView.getTaFornecedorObservacao().setValue(ent.getObservacao());
	}

	private void loadTransportadora() throws Exception {
		TransportadoraEntity ent = this.entity.getTransportadora();

		this.subView.getTaTransportadoraObservacao().setValue(
				ent.getObservacao());

		ContabilContaEntity contaContabil = ent.getContaContabil();

		if (ObjectUtils.isNotBlank(contaContabil)) {
			this.subView.getMocTransportadoraContabilConta().setValue(
					contaContabil);
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new PessoaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			visualizarAba();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new PessoaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			visualizarAba();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 */

	public PessoaContatoEntity adicionarPessoaContato() {
		try {
			PessoaContatoEntity ent = new PessoaContatoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaContatoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaContato(List<PessoaContatoEntity> values) {
		try {
			for (PessoaContatoEntity ent : values) {
				this.pessoaContatoBusiness.delete(ent);
				this.entity.getPessoaContatoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	public PessoaEnderecoEntity adicionarPessoaEndereco() {
		try {
			PessoaEnderecoEntity ent = new PessoaEnderecoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaEnderecoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaEndereco(List<PessoaEnderecoEntity> values) {
		try {
			for (PessoaEnderecoEntity ent : values) {
				this.pessoaEnderecoBusiness.delete(ent);
				this.entity.getPessoaEnderecoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * COMBOS
	 */

	public void carregarTipoRegime() {
		for (TipoRegimeEn en : TipoRegimeEn.values()) {
			this.subView.getCbTipoRegime().addItem(en);
		}
	}

	public void carregarCnh() {
		for (CnhEn en : CnhEn.values()) {
			this.subView.getCbCategoriaCnh().addItem(en);
		}
	}

	public void carregarRaca() {
		for (RacaEn en : RacaEn.values()) {
			this.subView.getCbRaca().addItem(en);
		}
	}

	public void carregarCategoriaReservista() {
		for (CategoriaReservistaEn en : CategoriaReservistaEn.values()) {
			this.subView.getCbCategoriaReservista().addItem(en);
		}
	}

	public void carregarCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCbCrt().addItem(en);
		}
	}

	public void carregarTipoSanguineo() {
		for (TipoSanguineoEn en : TipoSanguineoEn.values()) {
			this.subView.getCbTipoSanguineo().addItem(en);
		}
	}

	public void carregarTipoPessoa() {
		for (TipoPessoaEn en : TipoPessoaEn.values()) {
			this.subView.getCbTipoPessoa().addItem(en);
		}
	}

	public void carregarSexo() {
		for (SexoEn en : SexoEn.values()) {
			this.subView.getOgSexo().addItem(en);
		}
	}

	//

	public void carregarGerarFinanceiro() {
		for (SimNaoEn en : SimNaoEn.values()) {
			this.subView.getCbClienteGerarFinanceiro().addItem(en);
		}
	}

	public void carregarIndicadorPreco() {
		for (IndicadorPrecoEn en : IndicadorPrecoEn.values()) {
			this.subView.getCbClienteIndicadorPreco().addItem(en);
		}
	}

	public void carregarTipoFrete() {
		for (TipoFreteEn en : TipoFreteEn.values()) {
			this.subView.getCbClienteTipoFrete().addItem(en);
		}
	}

	public void carregarFormaDesconto() {
		for (FormaDescontoEn en : FormaDescontoEn.values()) {
			this.subView.getCbClienteFormaDesconto().addItem(en);
		}
	}

	//

	public void carregarSofreRetencao() {
		for (SimNaoEn value : SimNaoEn.values()) {
			this.subView.getCbFornecedorSofreRetencao().addItem(value);
		}
	}

	public void carregarGerarFaturamento() {
		for (SimNaoEn value : SimNaoEn.values()) {
			this.subView.getCbFornecedorGeraFaturamento().addItem(value);
		}
	}

	public void carregarOptanteSimples() {
		for (SimNaoEn value : SimNaoEn.values()) {
			this.subView.getCbFornecedorOptanteSimples().addItem(value);
		}
	}

	public void carregarLocalizacao() {
		for (LocalizacaoEn value : LocalizacaoEn.values()) {
			this.subView.getCbFornecedorLocalizacao().addItem(value);
		}
	}

	/**
	 * 
	 */

	public void visualizarAba() {
		this.subView
				.getTsGeral()
				.getTab(4)
				.setVisible(
						this.entity.getTipoCliente().equals("0") ? Boolean.FALSE
								: Boolean.TRUE);

		this.subView.getTsGeral().getTab(5)
				.setVisible(this.entity.getTipoColaborador());
		this.subView.getTsGeral().getTab(6)
				.setVisible(this.entity.getTipoFornecedor());
		this.subView.getTsGeral().getTab(7)
				.setVisible(this.entity.getTipoTransportadora());
	}

	/**
	 * 
	 */

	public BeanItemContainer<UfEntity> getUfBic() {
		try {
			List<UfEntity> auxLista = this.ufBusiness.findAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			return bic;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 */

	public void vceVisibleTabSheet(ValueChangeEvent event, Integer indexTab) {
		Object obj = event.getProperty().getValue();

		if (obj instanceof TipoPessoaEn) {
			TipoPessoaEn en = (TipoPessoaEn) obj;

			this.subView
					.getTsGeral()
					.getTab(0)
					.setVisible(
							en.equals(TipoPessoaEn.F) ? Boolean.TRUE
									: Boolean.FALSE);
			this.subView
					.getTsGeral()
					.getTab(1)
					.setVisible(
							en.equals(TipoPessoaEn.J) ? Boolean.TRUE
									: Boolean.FALSE);

			this.subView.getTsGeral().setSelectedTab(
					en.equals(TipoPessoaEn.F) ? 0 : 1);
		} else if (obj instanceof Boolean) {
			Boolean b = (Boolean) obj;

			this.subView.getTsGeral().getTab(indexTab).setVisible(b);
		} else {
			System.out.println(":: [instanceof] no type for " + obj.toString());
		}
	}

}