package dc.controller.financeiro;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.controller.pessoal.ClienteListController;
import dc.entidade.financeiro.Banco;
import dc.entidade.financeiro.ConfiguracaoBoleto;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.DocumentoOrigem;
import dc.entidade.financeiro.LancamentoReceber;
import dc.entidade.financeiro.LctoReceberNtFinanceira;
import dc.entidade.financeiro.NaturezaFinanceira;
import dc.entidade.financeiro.ParcelaReceber;
import dc.entidade.financeiro.StatusParcela;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.PessoaEndereco;
import dc.entidade.pessoal.ClienteEntity;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.financeiro.BancoDAO;
import dc.servicos.dao.financeiro.ConfiguracaoBoletoDAO;
import dc.servicos.dao.financeiro.ContaCaixaDAO;
import dc.servicos.dao.financeiro.DocumentoOrigemDAO;
import dc.servicos.dao.financeiro.LancamentoReceberDAO;
import dc.servicos.dao.financeiro.NaturezaFinanceiraDAO;
import dc.servicos.dao.financeiro.ParcelaReceberDAO;
import dc.servicos.dao.financeiro.StatusParcelaDAO;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.geral.PessoaEnderecoDAO;
import dc.servicos.dao.pessoal.ClienteDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.LancamentoReceberFormView;
import dc.visao.financeiro.enums.TipoVencimento;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class LancamentoReceberFormController extends CRUDFormController<LancamentoReceber> {

	private final class BoletoSource implements StreamSource {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public InputStream getStream() {
			ByteArrayInputStream resource = null;
			try {

				List<ParcelaReceber> listaParcelasReceber = new ArrayList<ParcelaReceber>(subView.getParcelasSubForm().getSelectedItens());

				if (listaParcelasReceber.isEmpty()) {
					listaParcelasReceber.addAll(subView.getParcelasSubForm().getDados());
				}
				byte[] boleto = gerarBoleto(listaParcelasReceber);
				resource = new ByteArrayInputStream(boleto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				mensagemErro(e.getMessage());
			}

			return resource;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoReceberFormView subView;

	@Autowired
	private LancamentoReceberDAO lancamentoReceberDAO;

	@Autowired
	private ParcelaReceberDAO parcelaReceberDAO;

	private LancamentoReceber currentBean;

	@Autowired
	private ContabilContaDAO contabilcontaDAO;

	@Autowired
	private ContaCaixaDAO contaCaixaDAO;

	@Autowired
	private FornecedorDAO fornecedorDAO;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private NaturezaFinanceiraDAO naturezaFinanceiraDAO;

	@Autowired
	private DocumentoOrigemDAO documentoOrigemDAO;

	@Autowired
	private StatusParcelaDAO statusParcelaDAO;

	@Autowired
	private ConfiguracaoBoletoDAO configuracaoBoletoDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private PessoaEnderecoDAO enderecoDAO;

	@Autowired
	private BancoDAO bancoDAO;

	@Override
	protected String getNome() {
		return "Lançamento à Receber";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);

		boolean valido = true;

		List<ParcelaReceber> parcelasReceber = subView.getParcelasSubForm().getDados();
		List<LctoReceberNtFinanceira> naturezasanceiras = subView.getNaturezaFinanceiraSubForm().getDados();

		if (((BigDecimal) subView.getTxValorReceber().getConvertedValue()).compareTo(getTotalParcelaReceber(parcelasReceber)) != 0) {
			adicionarErroDeValidacao(subView.getParcelasSubForm(), "Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		if (((BigDecimal) subView.getTxValorReceber().getConvertedValue()).compareTo(getTotalNaturezaFinanceira(naturezasanceiras)) != 0) {
			adicionarErroDeValidacao(subView.getNaturezaFinanceiraSubForm(),
					"Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
			valido = false;

			mensagemErro("Os valores informados nas naturezas financeiras não batem com o valor a pagar.");
		}

		if (valido) {

			setIntervaloParcelaByTipoVencimento();

			StatusParcela statusParcela = statusParcelaDAO.findBySituacao("01");
			if (statusParcela == null) {
				mensagemErro("O status de parcela em aberto não está cadastrado.\nEntre em contato com a Software House.");
			} else {

				for (ParcelaReceber p : currentBean.getParcelasReceber()) {
					p.setStatusParcela(statusParcela);
				}

				try {
					currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getConta().getEmpresa());
					lancamentoReceberDAO.saveOrUpdate(currentBean);
					notifiyFrameworkSaveOK(this.currentBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setIntervaloParcelaByTipoVencimento() {
		if (TipoVencimento.MENSAL.equals(subView.getCbTipoVencimento().getValue())) {
			currentBean.setIntervaloEntreParcelas(30);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = lancamentoReceberDAO.find(id);
		subView.preencheForm(currentBean);
	}

	/*
	 * Callback para quando novo foi acionado. Colocar programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new LancamentoReceberFormView(this);

		subView.getBtnGerarParcelas().addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					gerarParcelas();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					mensagemErro(e.getMessage());
				}

			}
		});

		StreamResource myResource = createBoletoResource();
		if (myResource != null) {
			FileDownloader fileDownloader = new FileDownloader(myResource);
			fileDownloader.extend(subView.getBtnGerarBoleto());
		}

		subView.getDtLancamento().setValue(new Date());
		subView.getTxIntervaloParcela().setEnabled(false);

		subView.getCbTipoVencimento().addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				TipoVencimento tipoVencimento = (TipoVencimento) subView.getCbTipoVencimento().getValue();
				if (TipoVencimento.MENSAL.equals(tipoVencimento)) {
					subView.getTxIntervaloParcela().setEnabled(false);
					subView.getTxIntervaloParcela().setValue(null);
					currentBean.setIntervaloEntreParcelas(30);
				} else {
					subView.getTxIntervaloParcela().setEnabled(true);
					currentBean.setIntervaloEntreParcelas(null);
				}

			}
		});

		subView.getTxValorComissao().setEnabled(false);
		subView.getTxTaxaComissao().addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {
				calculaValorComissao();

			}
		});

		preencheCombos();
	}

	private StreamResource createBoletoResource() {

		return new StreamResource(new BoletoSource(), "boleto.pdf");
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new LancamentoReceber();
	}

	private void preencheCombos() {

		DefaultManyToOneComboModel<ContaCaixa> model1 = new DefaultManyToOneComboModel<ContaCaixa>(ContaCaixaListController.class,
				this.contaCaixaDAO, super.getMainController());

		this.subView.getCbContaCaixa().setModel(model1);

		DefaultManyToOneComboModel<DocumentoOrigem> model3 = new DefaultManyToOneComboModel<DocumentoOrigem>(DocumentoOrigemListController.class,
				this.documentoOrigemDAO, super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "descricao";
			}
		};
		this.subView.getCbDocumentoOrigem().setModel(model3);

		DefaultManyToOneComboModel<ClienteEntity> model2 = new DefaultManyToOneComboModel<ClienteEntity>(ClienteListController.class, this.clienteDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};
		this.subView.getCbCliente().setModel(model2);

	}

	@Override
	protected void remover(List<Serializable> ids) {
		lancamentoReceberDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {

		boolean valido = true;

		if (!Validator.validateObject(subView.getCbDocumentoOrigem().getValue())) {
			adicionarErroDeValidacao(subView.getCbDocumentoOrigem(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtLancamento().getValue())) {
			adicionarErroDeValidacao(subView.getDtLancamento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtPrimeiroVencimento().getValue())) {
			adicionarErroDeValidacao(subView.getDtPrimeiroVencimento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxQuantidadeParcela().getValue())) {
			adicionarErroDeValidacao(subView.getTxQuantidadeParcela(), "Não pode ficar em branco");
			valido = false;
		} else if (verificaSeFoiParcelado() && !Validator.validateNumber(subView.getTxIntervaloParcela().getValue())) {
			adicionarErroDeValidacao(subView.getTxIntervaloParcela(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	private boolean verificaSeFoiParcelado() {
		// TODO
		/*
		 * return ((Integer)
		 * subView.getTxQuantidadeParcela().getConvertedValue()) > 1 &&
		 * TipoVencimento
		 * .DIARIO.equals(subView.getCbTipoVencimento().getValue());
		 */
		return false;
	}

	private BigDecimal getTotalParcelaReceber(List<ParcelaReceber> parcelas) {
		BigDecimal total = BigDecimal.ZERO;
		for (int i = 0; i < parcelas.size(); i++) {
			total = total.add(parcelas.get(i).getValor());
		}
		return total;
	}

	private BigDecimal getTotalNaturezaFinanceira(List<LctoReceberNtFinanceira> naturezasanceiras) {
		BigDecimal total = BigDecimal.ZERO;
		if (naturezasanceiras != null) {
			for (int i = 0; i < naturezasanceiras.size(); i++) {
				total = total.add(naturezasanceiras.get(i).getValor());
			}
		}
		return total;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			LancamentoReceber lancamentoReceber = (LancamentoReceber) id;
			List<LctoReceberNtFinanceira> lctoReceberNFinanceiras = lancamentoReceber.getLctoReceberNtFinanceira();

			for (LctoReceberNtFinanceira lctoReceberNFinanceira : lctoReceberNFinanceiras) {
				lctoReceberNFinanceira.setLancamentoReceber(null);

			}

			List<ParcelaReceber> parcelasReceber = lancamentoReceber.getParcelasReceber();

			for (ParcelaReceber parcelaReceber : parcelasReceber) {

				parcelaReceber.setLancamentoReceber(null);
			}

			lancamentoReceberDAO.delete(lancamentoReceber);

		}
		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {

		return "lancamentoReceberForm";
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	public void gerarParcelas() throws Exception {

		if (validaCampos()) {
			final ContaCaixa contaCaixa = (ContaCaixa) subView.getCbContaCaixa().getValue();
			if (contaCaixa == null || contaCaixa.getId() == null) {
				throw new Exception("É necessário informar a conta caixa para previsão das parcelas.");
			}
			final List<ParcelaReceber> parcelasReceber = new ArrayList<ParcelaReceber>();
			List<ParcelaReceber> dados = subView.getParcelasSubForm().getDados();
			if (dados != null) {
				parcelasReceber.addAll(subView.getParcelasSubForm().getDados());
			}

			if (parcelasReceber != null && !parcelasReceber.isEmpty()) {
				ConfirmDialog.show(MainUI.getCurrent(), "Confirme a remoção",
						"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?", "Sim", "Não",
						new ConfirmDialog.Listener() {

							/**
							 * 
							 */
							private static final long serialVersionUID = 1L;

							public void onClose(ConfirmDialog dialog) {
								if (dialog.isConfirmed()) {
									excluiParcelas(parcelasReceber);
									geraParcelas(contaCaixa, parcelasReceber);
								}
							}
						});
			} else {
				geraParcelas(contaCaixa, parcelasReceber);
			}

		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}

	}

	private void geraParcelas(ContaCaixa contaCaixa, final List<ParcelaReceber> parcelasReceber) {

		// gera as parcelas
		subView.getParcelasSubForm().removeAllItems();
		subView.preencheBean(currentBean);

		LancamentoReceber lancamentoReceber = currentBean;
		ParcelaReceber parcelaReceber;
		Date dataEmissão = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(lancamentoReceber.getPrimeiroVencimento());
		BigDecimal valorParcela = lancamentoReceber.getValorAReceber().divide(BigDecimal.valueOf(lancamentoReceber.getQuantidadeParcela()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero;
		DecimalFormat formatoNossoNumero4 = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero5 = new DecimalFormat("00000");
		SimpleDateFormat formatoNossoNumero6 = new SimpleDateFormat("D");
		Date dataAtual = new Date();
		for (int i = 0; i < lancamentoReceber.getQuantidadeParcela(); i++) {
			parcelaReceber = new ParcelaReceber();
			parcelaReceber.setContaCaixa(contaCaixa);
			parcelaReceber.setNumeroParcela(i + 1);
			parcelaReceber.setDataEmissao(dataEmissão);
			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH, lancamentoReceber.getIntervaloEntreParcelas());
			}
			parcelaReceber.setDataVencimento(primeiroVencimento.getTime());
			parcelaReceber.setEmitiuBoleto("S");

			nossoNumero = formatoNossoNumero5.format(lancamentoReceber.getCliente().getId());
			nossoNumero += formatoNossoNumero5.format(parcelaReceber.getContaCaixa().getId());
			nossoNumero += formatoNossoNumero4.format(parcelaReceber.getNumeroParcela());
			nossoNumero += formatoNossoNumero6.format(dataAtual);
			parcelaReceber.setBoletoNossoNumero(nossoNumero);

			parcelaReceber.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);
			if (i == (lancamentoReceber.getQuantidadeParcela() - 1)) {
				residuo = lancamentoReceber.getValorAReceber().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				parcelaReceber.setValor(valorParcela);
			}
			parcelasReceber.add(parcelaReceber);
			novoParcelaReceber(parcelaReceber);
		}

		subView.getParcelasSubForm().fillWith(parcelasReceber);
	}

	private void excluiParcelas(List<ParcelaReceber> parcelasReceber) {
		List<ParcelaReceber> persistentObjects = subView.getParcelasSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			parcelaReceberDAO.delete(persistentObjects.get(i));
		}
		parcelasReceber.clear();
	}

	public ParcelaReceber novoParcelaReceber() {
		ParcelaReceber parcela = new ParcelaReceber();
		return novoParcelaReceber(parcela);
	}

	public ParcelaReceber novoParcelaReceber(ParcelaReceber parcela) {

		currentBean.addParcelaReceber(parcela);

		return parcela;
	}

	public void removerParcelaReceber(List<ParcelaReceber> values) {
		for (ParcelaReceber value : values) {
			currentBean.removeParcelaReceber(value);
		}

	}

	public LctoReceberNtFinanceira novoLctoReceberNtFinanceira() {
		LctoReceberNtFinanceira lctoReceberNFinanceira = currentBean.addLctoReceberNtFinanceira();
		return lctoReceberNFinanceira;
	}

	public void removerLctoReceberNtFinanceira(List<LctoReceberNtFinanceira> values) {
		for (LctoReceberNtFinanceira value : values) {
			currentBean.removeLctoReceberNtFinanceira(value);
		}

	}

	public List<NaturezaFinanceira> getNaturezasFinanceiras() {
		return naturezaFinanceiraDAO.listaTodos();
	}

	private void calculaValorComissao() {
		BigDecimal valorAReceber = (BigDecimal) subView.getTxValorReceber().getConvertedValue();

		BigDecimal taxaComissao = (BigDecimal) subView.getTxTaxaComissao().getConvertedValue();

		if (valorAReceber != null && taxaComissao != null) {
			subView.getTxValorComissao().setConvertedValue(
					valorAReceber.multiply(taxaComissao).divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN));
		}
	}

	public byte[] gerarBoleto(List<ParcelaReceber> listaParcelasReceber) throws Exception {
		byte[] boletoByteArray = null;

		List<Boleto> listaBoleto = gerBoleto(listaParcelasReceber);
		boletoByteArray = BoletoViewer.groupInOnePDF(listaBoleto);
		return boletoByteArray;
	}

	private List<Boleto> gerBoleto(List<ParcelaReceber> listaParcelasReceber) throws Exception {
		if (listaParcelasReceber.isEmpty()) {
			throw new Exception("Nenhuma parcela para gerar boleto.");
		}
		ContaCaixa contaCaixa = listaParcelasReceber.get(0).getContaCaixa();
		if (contaCaixa.getAgenciaBanco() == null) {
			throw new Exception("A conta/caixa não está vinculada a um banco. Geração de boletos não permitida.");
		}
		List<ParcelaReceber> listaParcelasBoleto = new ArrayList<ParcelaReceber>();
		for (int i = 0; i < listaParcelasReceber.size(); i++) {
			if (listaParcelasReceber.get(i).getEmitiuBoleto().equals("S")) {
				listaParcelasBoleto.add(listaParcelasReceber.get(i));
			}
		}
		if (listaParcelasBoleto.isEmpty()) {
			throw new Exception("Nenhuma parcela selecionada para gerar boleto.");
		}

		ConfiguracaoBoleto configuracaoBoleto = configuracaoBoleto(listaParcelasReceber.get(0).getContaCaixa());
		LancamentoReceber lancamentoReceber = currentBean;
		ClienteEntity cliente = lancamentoReceber.getCliente();
		Empresa empresa = lancamentoReceber.getEmpresa();
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		Cedente cedente = new Cedente(empresa.getRazaoSocial(), empresa.getCnpj());

		String cpfCnpjSacado;
		if (cliente.getPessoa().getTipo().equals("F")) {
			cpfCnpjSacado = pessoaDAO.getPessoaFisica(cliente.getPessoa().getId()).getCpf();
		} else {
			cpfCnpjSacado = pessoaDAO.getPessoaJuridica(cliente.getPessoa().getId()).getCnpj();
		}
		Sacado sacado = new Sacado(cliente.getPessoa().getNome(), cpfCnpjSacado);

		PessoaEndereco enderecoSacado = new PessoaEndereco();

		dc.entidade.geral.PessoaEndereco endereco = (PessoaEndereco) enderecoDAO.listaPorPessoa(cliente.getPessoa()).get(0);

		enderecoSacado.setUf(endereco.getUf());
		enderecoSacado.setCidade(endereco.getCidade());
		enderecoSacado.setCep(endereco.getCep());
		enderecoSacado.setBairro(endereco.getBairro());
		enderecoSacado.setLogradouro(endereco.getLogradouro());
		enderecoSacado.setNumero(endereco.getNumero());
		//sacado.addEndereco(enderecoSacado);

		Banco banco = bancoDAO.find(contaCaixa.getAgenciaBanco().getIdBanco());
		ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.suportados.get(banco.getCodigo()).create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(contaCaixa.getCodigo()), contaCaixa.getDigito()));
		contaBancaria.setCarteira(new Carteira(Integer.valueOf(configuracaoBoleto.getCarteira())));
		contaBancaria.setAgencia(new Agencia(Integer.valueOf(contaCaixa.getAgenciaBanco().getCodigo()), contaCaixa.getAgenciaBanco().getDigito()));

		Titulo titulo;
		ParcelaReceber parcela;
		Boleto boleto;
		List<Boleto> listaBoleto = new ArrayList<Boleto>();
		for (int i = 0; i < listaParcelasBoleto.size(); i++) {
			parcela = listaParcelasBoleto.get(i);

			titulo = new Titulo(contaBancaria, sacado, cedente);
			titulo.setNumeroDoDocumento(parcela.getBoletoNossoNumero().substring(0, 15));
			titulo.setNossoNumero(parcela.getBoletoNossoNumero());
			titulo.setDigitoDoNossoNumero("");
			titulo.setValor(parcela.getValor());
			titulo.setDataDoDocumento(parcela.getDataEmissao());
			titulo.setDataDoVencimento(parcela.getDataVencimento());
			if (configuracaoBoleto.getEspecie().equals("DM")) {
				titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
			} else if (configuracaoBoleto.getEspecie().equals("DS")) {
				titulo.setTipoDeDocumento(TipoDeTitulo.DS_DUPLICATA_DE_SERVICO);
			} else if (configuracaoBoleto.getEspecie().equals("RC")) {
				titulo.setTipoDeDocumento(TipoDeTitulo.RC_RECIBO);
			} else if (configuracaoBoleto.getEspecie().equals("NP")) {
				titulo.setTipoDeDocumento(TipoDeTitulo.NP_NOTA_PROMISSORIA);
			}
			if (configuracaoBoleto.getAceite().equals("S")) {
				titulo.setAceite(Aceite.A);
			} else {
				titulo.setAceite(Aceite.N);
			}
			titulo.setDesconto(parcela.getValorDesconto());
			// titulo.setDeducao(BigDecimal.ZERO);
			// titulo.setMora(BigDecimal.ZERO);
			// titulo.setAcrecimo(BigDecimal.ZERO);
			// titulo.setValorCobrado(BigDecimal.ZERO);

			boleto = new Boleto(titulo);
			boleto.setLocalPagamento(configuracaoBoleto.getLocalPagamento());
			boleto.setInstrucaoAoSacado(configuracaoBoleto.getMensagem());
			boleto.setInstrucao1(configuracaoBoleto.getInstrucao01());
			boleto.setInstrucao2(configuracaoBoleto.getInstrucao02());
			if (parcela.getDescontoAte() != null && parcela.getTaxaDesconto() != null) {
				boleto.setInstrucao3("Para pagamento até o dia " + formatoData.format(parcela.getDescontoAte()) + " conceder desconto de "
						+ parcela.getTaxaDesconto() + "%.");
			} else {
				boleto.setInstrucao3("");
			}

			listaBoleto.add(boleto);

		}
		return listaBoleto;
	}

	private ConfiguracaoBoleto configuracaoBoleto(ContaCaixa contaCaixa) throws Exception {

		List<ConfiguracaoBoleto> listaConfiguracaoBoleto = configuracaoBoletoDAO.getConfiguracoesBoletoByContaCaixa(contaCaixa);

		if (listaConfiguracaoBoleto.isEmpty()) {
			throw new Exception("Não existem configurações de boleto para a conta/caixa.");
		}
		if (listaConfiguracaoBoleto.size() == 1) {
			return listaConfiguracaoBoleto.get(0);
		} else {
			ConfiguracaoBoleto configuracaoes[] = new ConfiguracaoBoleto[listaConfiguracaoBoleto.size()];
			listaConfiguracaoBoleto.toArray(configuracaoes);
			ConfiguracaoBoleto configuracao = null;
			while (configuracao == null) {
				// TODO Exibir popup para seleção do boleto.
				configuracao = listaConfiguracaoBoleto.get(0);
			}

			return configuracao;
		}
	}

	@Override
	public LancamentoReceber getModelBean() {
		return currentBean;
	}
}
