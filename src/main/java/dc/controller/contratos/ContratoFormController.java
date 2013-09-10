package dc.controller.contratos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Component;

import dc.entidade.contabilidade.ContabilConta;
import dc.entidade.contratos.Contrato;
import dc.entidade.contratos.ContratoHistFaturamento;
import dc.entidade.contratos.ContratoHistoricoReajuste;
import dc.entidade.contratos.ContratoPrevFaturamento;
import dc.entidade.contratos.ContratoSolicitacaoServico;
import dc.entidade.contratos.TipoContrato;
import dc.entidade.framework.Empresa;
import dc.entidade.ged.Documento;
import dc.entidade.ged.DocumentoArquivo;
import dc.entidade.geral.Endereco;
import dc.entidade.pessoal.Cliente;
import dc.servicos.dao.contabilidade.ContabilContaDAO;
import dc.servicos.dao.contratos.ContratoDAO;
import dc.servicos.dao.contratos.ContratoSolicitacaoServicoDAO;
import dc.servicos.dao.contratos.TipoContratoDAO;
import dc.servicos.dao.ged.DocumentoDAO;
import dc.servicos.util.Validator;
import dc.visao.contratos.ContratoFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class ContratoFormController extends CRUDFormController<Contrato> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContratoFormView subView;

	@Autowired
	private ContratoDAO contratoDAO;

	@Autowired
	private TipoContratoDAO tipoContratoDAO;

	@Autowired
	private ContabilContaDAO contabilContaDAO;

	@Autowired
	private ContratoSolicitacaoServicoDAO solicitacaoServicoDAO;

	@Autowired
	private DocumentoDAO documentoDAO;

	private Contrato currentBean;

	@Override
	protected boolean validaSalvar() {
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

		if (!Validator.validateNumber(subView.getTxtValor().getValue())) {
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

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Contrato();
	}

	private void carregarCombos() {
		subView.carregaComboContabilConta(contabilContaDAO
				.getAll(ContabilConta.class));
		subView.carregaComboTipoContrato(tipoContratoDAO
				.getAll(TipoContrato.class));
		subView.carregaComboSolicitacaoServico(solicitacaoServicoDAO
				.getAll(ContratoSolicitacaoServico.class));
		List<Documento> documentos = documentoDAO.getAll(Documento.class);

		Iterator<Documento> iterator = documentos.iterator();
		while (iterator.hasNext()) {
			Documento doc = iterator.next();
			if (doc.getDocumentos().size() > 0
					&& !doc.getDocumentos().get(0).getCaminho()
							.endsWith("docx")) {
				iterator.remove();
			}

		}

		subView.carregaComboDocumento(documentos);
	}

	@Override
	protected void initSubView() {
		subView = new ContratoFormView(this);
	}

	private StreamResource createResource() {
		final Documento documento = (Documento)subView.getCbmDocumento().getValue();
		if(documento != null)
		{
			
			return new StreamResource(new StreamSource() {
				@Override
				public InputStream getStream() {
					
					
					Empresa empresa = documento.getEmpresa();
					
					Endereco enderecoEmpresa = new Endereco();// TODO
					
					Cliente dadosContratante = currentBean
							.getContratoSolicitacaoServico().getCliente();
					
					ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
					try {
						File origem = getFileFromDocumento(documento);
						File novo = getTempFile();
						
						
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
								"#ENDERECO_CONTRATANTE#", "#CIDADE_CONTRATANTE#",
								"#UF_CONTRATANTE#", "#BAIRRO_CONTRATANTE#",
								"#CEP_CONTRATANTE#",
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
								"",	/*enderecoEmpresa.getLogradouro()
									+ " "
									+ enderecoEmpresa.getNumero()
									+ " "
									+ (enderecoEmpresa.getComplemento() == null ? ""
											: enderecoEmpresa.getComplemento()),*/
								"",//enderecoEmpresa.getCidade(),
								"",// enderecoEmpresa.getUf()
								"",//enderecoEmpresa.getBairro(),
								"",//enderecoEmpresa.getCep(),
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
								formatoDataExtenso.format(currentBean.getDataInicioVigencia())
						};
						
						XWPFDocument doc = new XWPFDocument(new FileInputStream(
								origem));
						
						List<XWPFParagraph> paragrafos = new ArrayList<XWPFParagraph>(
								doc.getParagraphs());
						
						for (XWPFParagraph p : paragrafos) {
							for (XWPFRun r : p.getRuns()) {
								for (CTText ctText : r.getCTR().getTList()) {
									for (int i = 0; i < termos.length; i++) {
										String termo = termos[i];
										String substituir = substituicoes[i];
										
										String str = null;
										if (ctText.getStringValue().contains(termo)) {
											str = ctText.getStringValue().replace(
													termo, substituir);
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
			}, documento.getNome()+".docx");
		}
		else
		{
			return null;
		}
	}

	@Override
	protected void carregar(Serializable id) {
		carregarCombos();
		currentBean = contratoDAO.find(id);
		subView.preencheContratoForm(currentBean);
		StreamResource myResource = createResource();
		if(myResource != null)
		{			
			FileDownloader fileDownloader = new FileDownloader(myResource);
			fileDownloader.extend(subView.getBtnGerarContrato());
		}
	}

	@Override
	protected void actionSalvar() {
		subView.preencheContrato(currentBean);

		try {
			contratoDAO.saveOrUpdate(currentBean);
			mensagemSalvoOK();
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

	public ContratoPrevFaturamento novoContratoPrevFaturamento() {
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

	@Override
	public String getViewIdentifier() {
		return "contratoForm";
	}

	private static File getTempFile() {

		return new File("C:\\Users\\Douglas\\novo.docx");
	}

	private static File getFileFromDocumento(Documento documento) {
		File arquivo = new File(
				((List<DocumentoArquivo>) documento.getDocumentos()).get(0)
						.getCaminho());
		return arquivo;
	}

}
