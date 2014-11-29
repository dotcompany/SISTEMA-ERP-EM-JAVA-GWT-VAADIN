package dc.controller.suprimentos.contrato;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.hibernate.SessionFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.controller.contabilidade.ContabilContaListController;
import dc.controller.geral.UFListController;
import dc.controller.geral.produto.ProdutosListController;
import dc.controller.pessoal.PessoaListController;
import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.PessoaEndereco;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.UF;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.pessoal.ClienteEntity;
import dc.entidade.suprimentos.contrato.Contrato;
import dc.entidade.suprimentos.contrato.ContratoHistFaturamento;
import dc.entidade.suprimentos.contrato.ContratoHistoricoReajuste;
import dc.entidade.suprimentos.contrato.ContratoPrevFaturamento;
import dc.entidade.suprimentos.contrato.ContratoProduto;
import dc.entidade.suprimentos.contrato.ContratoSolicitacaoServico;
import dc.entidade.suprimentos.contrato.Template;
import dc.entidade.suprimentos.contrato.TipoContrato;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.geral.produto.ProdutoDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.suprimentos.contrato.ContratoDAO;
import dc.servicos.dao.suprimentos.contrato.ContratoSolicitacaoServicoDAO;
import dc.servicos.dao.suprimentos.contrato.TemplateDAO;
import dc.servicos.dao.suprimentos.contrato.TipoContratoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimentos.contrato.ContratosFormView;

@Controller
@Scope("prototype")
public class ContratoFormController extends CRUDFormController<Contrato> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContratosFormView subView;

	@Autowired
	private ContratoDAO contratoDAO;

	// @Autowired
	// private MainController mainController;

	@Autowired
	private TipoContratoDAO tipoContratoDAO;

	@Autowired
	private TemplateDAO templateDAO;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	private ContratoSolicitacaoServicoDAO solicitacaoServicoDAO;

	@Autowired
	private TemplateDAO documentoDAO;

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private UFDAO ufDAO;

	private Contrato currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = validaCampos();

		return valido;
	}

	private boolean validaCampos() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNumero().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNumero(), "Número inválido");
			valido = false;
		}

		PessoaEntity pessoa = (PessoaEntity) subView.getCbmPessoa().getValue();

		if (!Validator.validateObject(pessoa)) {
			adicionarErroDeValidacao(subView.getCbmPessoa(),
					"Não pode ficar em branco");
			valido = false;
		}

		TipoContrato tipoContrato = (TipoContrato) subView.getCbmTipoContrato()
				.getValue();
		if (!Validator.validateObject(tipoContrato)) {
			adicionarErroDeValidacao(subView.getCbmTipoContrato(),
					"Não pode ficar em branco");
			valido = false;
		}

		ContabilConta contabilConta = (ContabilConta) subView
				.getCbmContabilConta().getValue();

		if (!Validator.validateObject(contabilConta)) {
			adicionarErroDeValidacao(subView.getCbmContabilConta(),
					"Não pode ficar em branco");
			valido = false;
		}

		Date dataVigencia = (Date) subView.getDtVigencia().getValue();

		if (!Validator.validateObject(dataVigencia)) {
			adicionarErroDeValidacao(subView.getDtVigencia(),
					"Não pode ficar em branco");
			valido = false;
		}

		Date dataCadastro = (Date) subView.getDtCadastro().getValue();

		if (!Validator.validateObject(dataCadastro)) {
			adicionarErroDeValidacao(subView.getDtCadastro(),
					"Não pode ficar em branco");
			valido = false;
		}

		Date dataFimVigencia = (Date) subView.getDtFimVigencia().getValue();

		if (!Validator.validateObject(dataFimVigencia)) {
			adicionarErroDeValidacao(subView.getDtFimVigencia(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtValor().getConvertedValue()
				.toString())) {
			adicionarErroDeValidacao(subView.getTxtValor(), "Número inválido");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtQuantidadeParcelas()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxtQuantidadeParcelas(),
					"Número inválido");
			valido = false;
		}

		if (!Validator.validateNumber(subView.getTxtIntervaloParcelas()
				.getValue())) {
			adicionarErroDeValidacao(subView.getTxtIntervaloParcelas(),
					"Número inválido");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxaDescricao(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxaObservacoes().getValue())) {
			adicionarErroDeValidacao(subView.getTxaObservacoes(),
					"Não pode ficar em branco");
			valido = false;
		}

		/*
		 * if (!Validator.validateString(subView.getTxaTemplate().getValue())) {
		 * adicionarErroDeValidacao(subView.getTxaTemplate(),
		 * "Não pode ficar em branco"); valido = false; }
		 */

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Contrato();
	}

	private void carregarCombos() {
		/*
		 * subView.carregaComboContabilConta(contabilContaDAO.getAll(ContabilConta
		 * .class));
		 * subView.carregaComboTipoContrato(tipoContratoDAO.getAll(TipoContrato
		 * .class));
		 * subView.carregaComboSolicitacaoServico(solicitacaoServicoDAO
		 * .getAll(ContratoSolicitacaoServico.class));
		 */

		/*
		 * DefaultManyToOneComboModel<ContabilConta> contabilContaModel = new
		 * DefaultManyToOneComboModel<ContabilConta>(
		 * ContabilContaListController.class, this.contabilContaDAO,
		 * super.getMainController()) {
		 * 
		 * @Override public String getCaptionProperty() { return "descricao"; }
		 * };
		 * 
		 * DefaultManyToOneComboModel<TipoContrato> tipoContratoModel = new
		 * DefaultManyToOneComboModel
		 * <TipoContrato>(TipoContratoListController.class,
		 * this.tipoContratoDAO, super.getMainController());
		 * 
		 * /**
		 * 
		 * Ajustes para receber os dados de Solicitação de Serviço pegando os
		 * StatusSolicitação para aparecer no ComboBox
		 */

		/*
		 * DefaultManyToOneComboModel<ContratoSolicitacaoServico>
		 * contratoSolicitacaoServicoModel = new
		 * DefaultManyToOneComboModel<ContratoSolicitacaoServico>(
		 * ContratoSolicitacaoServicoListController.class,
		 * this.solicitacaoServicoDAO, super.getMainController());
		 * 
		 * @Override public String getCaptionProperty() { return "descricao"; }
		 * };
		 * 
		 * subView.getCbmContabilConta().setModel(contabilContaModel);
		 * subView.getCbmTipoContrato().setModel(tipoContratoModel);
		 */
		// this.subView.getCmbSolicitacaoServico().setModel(contratoSolicitacaoServicoModel);
		// subView.getCmbSolicitacaoServico().setModel(contratoSolicitacaoServicoModel);

		/*
		 * List<Template> documentos = documentoDAO.getAll(Template.class);
		 * 
		 * Iterator<Template> iterator = documentos.iterator(); while
		 * (iterator.hasNext()) { Template doc = iterator.next(); if
		 * (doc.getDocumentos().size() > 0 &&
		 * !doc.getDocumentos().get(0).getCaminho().endsWith("docx")) {
		 * iterator.remove(); }
		 * 
		 * }
		 */

		// subView.carregaComboDocumento(documentos);
	}

	@Override
	protected void initSubView() {
		subView = new ContratosFormView(this);

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

		subView.getDtCadastro().setValue(new Date());

		DefaultManyToOneComboModel<PessoaEntity> pessoaModel = new DefaultManyToOneComboModel<PessoaEntity>(
				PessoaListController.class, this.pessoaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "nome";
			}

		};

		DefaultManyToOneComboModel<ContabilConta> contabilContaModel = new DefaultManyToOneComboModel<ContabilConta>(
				ContabilContaListController.class, this.contabilContaDAO,
				super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "descricao";
			}

		};

		DefaultManyToOneComboModel<TipoContrato> tipoContratoModel = new DefaultManyToOneComboModel<TipoContrato>(
				TipoContratoListController.class, this.tipoContratoDAO,
				super.getMainController());

		DefaultManyToOneComboModel<ProdutoEntity> modelProduto = new DefaultManyToOneComboModel<ProdutoEntity>(
				ProdutosListController.class, this.produtoDAO,
				super.getMainController());

		subView.getCmbProduto().setModel(modelProduto);

		DefaultManyToOneComboModel<UF> templateUF = new DefaultManyToOneComboModel<UF>(
				UFListController.class, this.ufDAO, super.getMainController());

		DefaultManyToOneComboModel<Template> templateModel = new DefaultManyToOneComboModel<Template>(
				TemplateListController.class, this.templateDAO,
				super.getMainController());

		/**
		 * Ajustes para receber os dados de Solicitação de Serviço pegando os
		 * StatusSolicitação para aparecer no ComboBox
		 */

		DefaultManyToOneComboModel<ContratoSolicitacaoServico> contratoSolicitacaoServicoModel = new DefaultManyToOneComboModel<ContratoSolicitacaoServico>(
				ContratoSolicitacaoServicoListController.class,
				this.solicitacaoServicoDAO, super.getMainController()) {

			@Override
			public String getCaptionProperty() {
				return "contratoTipoServico";
			}

		};

		subView.getCbmPessoa().setModel(pessoaModel);
		subView.getCbmContabilConta().setModel(contabilContaModel);
		subView.getCbmTipoContrato().setModel(tipoContratoModel);
		subView.getCbmDocumento().setModel(templateModel);

		List<UF> ufs = templateUF.getAll();

		for (UF uf : ufs) {
			subView.getCmbEstadoObjeto().addItem(uf);
		}

		subView.getCmbSolicitacaoServico().setModel(
				contratoSolicitacaoServicoModel);

		subView.getTxtValor().addBlurListener(new BlurListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {
				subView.getTxtValor().setConvertedValue(
						subView.getTxtValor().getConvertedValue());

			}

		});

		carregarCombos();
	}

	public StreamResource createResource() {
		final Template documento = (Template) subView.getCbmDocumento()
				.getValue();

		if (documento != null) {
			return new StreamResource(new StreamSource() {
				@Override
				public InputStream getStream() {
					Empresa empresa = documento.getEmpresa();

					PessoaEndereco enderecoEmpresa = new PessoaEndereco();// TODO

					ClienteEntity dadosContratante = currentBean
							.getContratoSolicitacaoServico().getCliente();

					ByteArrayOutputStream bos = new ByteArrayOutputStream();

					try {
						File origem = getFileFromDocumento(documento);

						// define os termos a serem substituidos
						String termos[] = new String[] {
								// contratada
								"#CONTRATADA#", "#CNPJ_CONTRATADA#",
								"#ENDERECO_CONTRATADA#",
								"#CIDADE_CONTRATADA#",
								"#UF_CONTRATADA#",
								"#BAIRRO_CONTRATADA#",
								"#CEP_CONTRATADA#",
								// contratante
								"#CONTRATANTE#", "#CNPJ_CONTRATANTE#",
								"#ENDERECO_CONTRATANTE#",
								"#CIDADE_CONTRATANTE#", "#UF_CONTRATANTE#",
								"#BAIRRO_CONTRATANTE#", "#CEP_CONTRATANTE#",
								// outros
								"#VALOR_MENSAL#", "#DATA_EXTENSO#" };

						MaskFormatter formatoCnpj = new MaskFormatter(
								"##.###.###/####-##");
						formatoCnpj.setValueContainsLiteralCharacters(false);
						MaskFormatter formatoCpf = new MaskFormatter(
								"###.###.###-##");
						formatoCpf.setValueContainsLiteralCharacters(false);
						SimpleDateFormat formatoDataExtenso = new SimpleDateFormat(
								"EEEE, dd 'de' MMMM 'de' yyyy");
						DecimalFormat formatoDecimal = new DecimalFormat(
								"#,###,##0.00");

						// busca os dados para substituicoes dos termos
						String substituicoes[] = new String[] {
								// contratada
								empresa.getRazaoSocial(),
								formatoCnpj.valueToString(empresa.getCnpj()),
								"", /*
									 * enderecoEmpresa . getLogradouro ( ) + " "
									 * + enderecoEmpresa . getNumero ( ) + " " +
									 * ( enderecoEmpresa . getComplemento ( ) ==
									 * null ? "" : enderecoEmpresa .
									 * getComplemento ( ) ) ,
									 */
								"",// enderecoEmpresa.getCidade(),
								"",// enderecoEmpresa.getUf()
								"",// enderecoEmpresa.getBairro(),
								"",// enderecoEmpresa.getCep(),
									// contratante
								dadosContratante.getPessoa().getNome(),
								"", // dadosContratante.getCpfCnpj().length()
								// == 11
								// ?
								// formatoCpf.valueToString(dadosContratante.getCpfCnpj())
								// :
								// formatoCnpj.valueToString(dadosContratante.getCpfCnpj()),
								"", // dadosContratante.getLogradouro() + " " +
								// dadosContratante.getNumero() + " " +
								// (dadosContratante.getComplemento() == null ?
								// "" :
								// dadosContratante.getComplemento()),
								"", // dadosContratante.getCidade(),
								"", // dadosContratante.getUf(),
								"", // dadosContratante.getBairro(),
								"", // dadosContratante.getCep(),
								// outros
								formatoDecimal.format(currentBean.getValor()),
								formatoDataExtenso.format(currentBean
										.getDataInicioVigencia()) };

						XWPFDocument doc = new XWPFDocument(
								new FileInputStream(origem));

						List<XWPFParagraph> paragrafos = new ArrayList<XWPFParagraph>(
								doc.getParagraphs());

						for (XWPFParagraph p : paragrafos) {
							for (XWPFRun r : p.getRuns()) {
								for (CTText ctText : r.getCTR().getTList()) {
									for (int i = 0; i < termos.length; i++) {
										String termo = termos[i];
										String substituir = substituicoes[i];

										String str = null;
										if (ctText.getStringValue().contains(
												termo)) {
											str = ctText.getStringValue()
													.replace(termo, substituir);
											ctText.setStringValue(str);
										}
									}
								}

							}
						}

						doc.write(bos);

						return new ByteArrayInputStream(bos.toByteArray());
					} catch (IOException | ParseException e) {
						e.printStackTrace();
					} finally {
						try {
							bos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					return null;
				}
			}, documento.getNome() + ".docx");
		} else {
			return null;
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = contratoDAO.find(id);
		subView.preencheContratoForm(currentBean);
		StreamResource myResource = createResource();

		if (myResource != null) {
			FileDownloader fileDownloader = new FileDownloader(myResource);
			fileDownloader.extend(subView.getBtnGerarContrato());
		}
	}

	@Override
	protected void actionSalvar() {
		subView.preencheContrato(currentBean);

		boolean valido = true;

		List<ContratoPrevFaturamento> contratoPrevFaturamento = subView
				.getPrevisaoFaturamentoSubForm().getDados();

		if (((BigDecimal) subView.getTxtValor().getConvertedValue())
				.compareTo(getTotal(contratoPrevFaturamento)) != 0) {
			adicionarErroDeValidacao(subView.getPrevisaoFaturamentoSubForm(),
					"Os valores informados nas parcelas não batem com o valor a pagar.");
			valido = false;
			mensagemErro("Os valores informados nas parcelas não batem com o valor a pagar.");
		}

		// currentBean.setContabilConta(subView.getCbmContabilConta().getValue());

		try {
			currentBean.setEmpresa(SecuritySessionProvider.getUsuario()
					.getConta().getEmpresa());
			contratoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		carregarCombos();
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Contrato";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		contratoDAO.deleteAllByIds(ids);
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	public Contrato getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(Contrato currentBean) {
		this.currentBean = currentBean;
	}

	public ContratoHistFaturamento novoContratoHistFaturamento() {
		ContratoHistFaturamento contratoHistFaturamento = new ContratoHistFaturamento();
		this.currentBean.addContratoHistFaturamento(contratoHistFaturamento);

		return contratoHistFaturamento;
	}

	public void removerContratoHistFaturamento(
			List<ContratoHistFaturamento> values) {
		for (ContratoHistFaturamento contratoHistFaturamento : values) {
			this.currentBean
					.removeContratoHistFaturamento(contratoHistFaturamento);
		}

		mensagemRemovidoOK();

	}

	public ContratoProduto novoContratoProduto() {
		ContratoProduto contratoProduto = new ContratoProduto();
		this.currentBean.addContratoHistFaturamento(contratoProduto);

		return contratoProduto;
	}

	public void removerContratoProduto(List<ContratoProduto> values) {
		for (ContratoProduto contrProduto : values) {
			this.currentBean.removeContratoProduto(contrProduto);
		}

		mensagemRemovidoOK();

	}

	public ContratoPrevFaturamento novoContratoPrevFaturamento(
			ContratoPrevFaturamento contratoPreFaturamento) {
		ContratoPrevFaturamento contratoPrevFaturamento = new ContratoPrevFaturamento();
		this.currentBean.addContratoPrevFaturamento(contratoPrevFaturamento);

		return contratoPrevFaturamento;
	}

	public void removerContratoPrevFaturamento(
			List<ContratoPrevFaturamento> values) {
		for (ContratoPrevFaturamento contratoPrevFaturamento : values) {
			this.currentBean
					.removeContratoPrevFaturamento(contratoPrevFaturamento);
		}

		mensagemRemovidoOK();
	}

	public ContratoHistoricoReajuste novoContratoHistoricoReajuste() {
		ContratoHistoricoReajuste contratoHistoricoReajuste = new ContratoHistoricoReajuste();
		this.currentBean
				.addContratoHistoricoReajuste(contratoHistoricoReajuste);

		return contratoHistoricoReajuste;
	}

	public void removerContratoHistoricoReajuste(
			List<ContratoHistoricoReajuste> values) {
		for (ContratoHistoricoReajuste contratoHistoricoReajuste : values) {
			this.currentBean
					.removeContratoHistoricoReajuste(contratoHistoricoReajuste);
		}

		mensagemRemovidoOK();
	}

	public void gerarParcelas() throws Exception {
		if (validaSalvar()) {
			final PessoaEntity pessoa = (PessoaEntity) subView.getCbmPessoa()
					.getValue();

			if (pessoa == null || pessoa.getId() == null) {
				throw new Exception(
						"É necessário informar a pessoa para previsão das parcelas.");
			}

			final List<ContratoPrevFaturamento> contratoprevFaturamento = new ArrayList<ContratoPrevFaturamento>();

			List<ContratoPrevFaturamento> dados = subView
					.getPrevisaoFaturamentoSubForm().getDados();
			Integer i = (Integer) (!subView.getTxtIntervaloParcelas()
					.getValue().equals("") ? new Integer(0) : subView
					.getTxtIntervaloParcelas().getValue());

			if (dados != null) {
				contratoprevFaturamento.addAll(subView
						.getPrevisaoFaturamentoSubForm().getDados());
			}

			if (contratoprevFaturamento != null
					&& !contratoprevFaturamento.isEmpty()) {
				ConfirmDialog
						.show(MainUI.getCurrent(),
								"Confirme a remoção",
								"As parcelas que foram geradas anteriormente serão excluídas!\nDeseja continuar?",
								"Sim", "Não", new ConfirmDialog.Listener() {

									/**
							 * 
							 */
									private static final long serialVersionUID = 1L;

									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											excluiParcelas(contratoprevFaturamento);
											// geraParcelas(contabilConta,
											// parcelasPagar);
											geraParcelas(pessoa,
													contratoprevFaturamento);
										}
									}

								});
			} else {
				// geraParcelas(contabilConta, parcelasPagar);
				geraParcelas(pessoa, contratoprevFaturamento);
			}
		} else {
			mensagemErro("Preencha todos os campos corretamente!");
		}
	}

	/** Wesley Jr gera as Parcelas */

	private void geraParcelas(PessoaEntity pessoa,
			final List<ContratoPrevFaturamento> contratopreFaturamento) {
		subView.getPrevisaoFaturamentoSubForm().removeAllItems();
		subView.preencheContrato(currentBean);

		Contrato contrato = currentBean;
		ContratoPrevFaturamento contratoPrevFaturamento;
		// List<ContratoPrevFaturamento> dados =
		// subView.buildPrevisaoFaturamentoSubForm().getDados();
		Date dataPrevista = new Date();
		Calendar primeiroVencimento = Calendar.getInstance();
		primeiroVencimento.setTime(contrato.getDataInicioVigencia());
		BigDecimal valorParcela = contrato.getValor().divide(
				BigDecimal.valueOf(contrato.getQuantidadeParcelas()),
				RoundingMode.HALF_DOWN);
		BigDecimal somaParcelas = BigDecimal.ZERO;
		BigDecimal residuo = BigDecimal.ZERO;
		String nossoNumero = null;
		DecimalFormat formatoNossoNumero = new DecimalFormat("0000");
		DecimalFormat formatoNossoNumero1 = new DecimalFormat("00000");
		SimpleDateFormat formatoNossoNumero2 = new SimpleDateFormat("D");
		Date dataAtual = new Date();

		for (int i = 0; i < contrato.getQuantidadeParcelas(); i++) {
			contratoPrevFaturamento = new ContratoPrevFaturamento();
			contratoPrevFaturamento.setPessoa(pessoa);
			// contrato.setContabilConta(contabilConta);
			// contrato.setQuantidadeParcelas(i + 1);
			contratoPrevFaturamento.setNumeroParcela(i + 1);
			contratoPrevFaturamento.setDataPrevista(dataPrevista);

			if (i > 0) {
				primeiroVencimento.add(Calendar.DAY_OF_MONTH,
						contrato.getIntervaloEntreParcelas());
			}
			// contratoPrevFaturamento.setDataVencimento(primeiroVencimento.getTime());
			contratoPrevFaturamento.setDataPrevista(primeiroVencimento
					.getTime());

			nossoNumero += formatoNossoNumero1.format(contratoPrevFaturamento
					.getPessoa().getId());
			nossoNumero += formatoNossoNumero.format(contratoPrevFaturamento
					.getNumeroParcela());
			nossoNumero += formatoNossoNumero2.format(dataAtual);

			contratoPrevFaturamento.setValor(valorParcela);

			somaParcelas = somaParcelas.add(valorParcela);

			if (i == (contrato.getQuantidadeParcelas() - 1)) {
				residuo = contrato.getValor().subtract(somaParcelas);
				valorParcela = valorParcela.add(residuo);
				contrato.setValor(valorParcela);
			}

			contratopreFaturamento.add(contratoPrevFaturamento);
			novoContrato(contratoPrevFaturamento);
			// novoContratoPrevFaturamento(contratoPrevFaturamento);
		}

		// subView.getPrevisaoFaturamentoSubForm().fillWith(parcelasPagar);
		subView.getPrevisaoFaturamentoSubForm()
				.fillWith(contratopreFaturamento);
	}

	private void excluiParcelas(
			List<ContratoPrevFaturamento> contratoPrevFaturamento) {
		List<ContratoPrevFaturamento> persistentObjects = subView
				.getPrevisaoFaturamentoSubForm().getDados();

		for (int i = 0; i < persistentObjects.size(); i++) {
			delete(persistentObjects.get(i));
		}

		contratoPrevFaturamento.clear();
	}

	public void delete(ContratoPrevFaturamento contratoPrevFaturamento) {
		sessionFactory.getCurrentSession().delete(contratoPrevFaturamento);
	}

	public ContratoPrevFaturamento novoContratoPrevFaturamento() {
		ContratoPrevFaturamento contratoPreFaturamento = new ContratoPrevFaturamento();

		return novoContratoPrevFaturamento(contratoPreFaturamento);
	}

	public ContratoPrevFaturamento novoContrato(
			ContratoPrevFaturamento contratoPreFaturamento) {
		currentBean.addParcela(contratoPreFaturamento);

		return contratoPreFaturamento;
	}

	public void removerParcelaPagar(List<ContratoPrevFaturamento> values) {
		for (ContratoPrevFaturamento value : values) {
			currentBean.removeParcela(value);
		}
	}

	private BigDecimal getTotal(
			List<ContratoPrevFaturamento> contratoPrevFaturamento) {
		BigDecimal total = BigDecimal.ZERO;

		for (int i = 0; i < contratoPrevFaturamento.size(); i++) {
			total = total.add(contratoPrevFaturamento.get(i).getValor());
		}

		return total;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	private static File getFileFromDocumento(Template documento) {
		File arquivo = new File(documento.getArquivo());

		return arquivo;
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	public List<ProdutoEntity> getProdutos() {
		return produtoDAO.listaTodos();
	}

	@Override
	public Contrato getModelBean() {
		return currentBean;
	}

}