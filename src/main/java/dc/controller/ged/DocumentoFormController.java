package dc.controller.ged;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;

import dc.entidade.framework.Empresa;
import dc.entidade.ged.Documento;
import dc.entidade.ged.DocumentoArquivo;
import dc.entidade.ged.TipoDocumento;
import dc.entidade.ged.VersaoDocumento;
import dc.entidade.geral.Usuario;
import dc.entidade.pessoal.Colaborador;
import dc.servicos.dao.ged.DocumentoDAO;
import dc.servicos.dao.ged.TipoDocumentoDAO;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.util.Util;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.ged.DocumentoFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class DocumentoFormController extends CRUDFormController<Documento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentoFormView subView;

	@Autowired
	private DocumentoDAO documentoDAO;
	
	@Autowired
	private MainController mainController;

	@Autowired
	private TipoDocumentoDAO tipoDocumentoDAO;

	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private Documento currentBean;

	private static String DOCUMENT_PATH = "";

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtDescricao().getValue())) {
			adicionarErroDeValidacao(subView.getTxtDescricao(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtPalavraChave().getValue())) {
			adicionarErroDeValidacao(subView.getTxtPalavraChave(), "Não pode ficar em branco");
			valido = false;
		}
		TipoDocumento tipoDocumento = (TipoDocumento) subView.getCmbTipoDocumento().getValue();
		if (!Validator.validateObject(tipoDocumento)) {
			adicionarErroDeValidacao(subView.getCmbTipoDocumento(), "Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateObject(subView.getDtFimVigencia())) {
			adicionarErroDeValidacao(subView.getDtFimVigencia(), "Não pode ficar em branco");
			valido = false;
		}
		File tmpFile = (File) subView.getUpArquivo().getValue();
		if (currentBean == null || currentBean.getId() == null) {

			if (!Validator.validateObject(tmpFile) || !tmpFile.exists()) {
				adicionarErroDeValidacao(subView.getUpArquivo(), "Selecione um arquivo");
			}
		}

		if (Validator.validateObject(tipoDocumento) && Validator.validateObject(tmpFile) && tmpFile.exists()) {

			if (tamanhoArquivoMega(tmpFile) > tipoDocumento.getTamanhoMaximo().doubleValue()) {
				adicionarErroDeValidacao(subView.getUpArquivo(), "Tamanho do arquivo excedente");
			}

			if (subView.getCkbAssinado().getValue()) {
				File arquivoCertificado = (File) subView.getUpArquivo().getValue();
				if (!Validator.validateObject(arquivoCertificado) || !arquivoCertificado.exists()) {
					adicionarErroDeValidacao(subView.getUpArquivo(), "Selecione o arquivo de certificado");
				}
			}

		}

		return valido;
	}

	private Double tamanhoArquivoMega(File tmpFile) {
		double bytes = tmpFile.length();
		double kilobytes = (bytes / 1024);
		double megabytes = (kilobytes / 1024);

		return megabytes;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Documento();
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Empresa empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
	}

	@Override
	protected void initSubView() {
		subView = new DocumentoFormView();

		DefaultManyToOneComboModel<TipoDocumento> model= new DefaultManyToOneComboModel<TipoDocumento>(TipoDocumentoListController.class,tipoDocumentoDAO,mainController);
		subView.getCmbTipoDocumento().setModel(model);
		
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = documentoDAO.find(id);
		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getTxtPalavraChave().setValue(currentBean.getPalavraChave());
		subView.getDtFimVigencia().setValue(currentBean.getDataFimVigencia());
		subView.getCkbPodeAlterar().setValue(currentBean.getPodeAlterar());
		subView.getCkbPodeExcluir().setValue(currentBean.getPodeExcluir());
		subView.getCkbAssinado().setValue(currentBean.getAssinado());
		File arquivo = new File(((List<DocumentoArquivo>) currentBean.getDocumentos()).get(0).getCaminho());

		FileResource fr = new FileResource(arquivo);
		Link link = subView.getLinkDonwload();
		link.setVisible(arquivo.exists());
		link.setEnabled(true);
		link.setResource(fr);
		link.setTargetName("_blank");

		subView.atualizaMiniatura(currentBean.getDocumentos());
		
		// Configura combo
		/*ManyToOneComboModel<TipoDocumento> model = new ManyToOneComboModel<TipoDocumento>() {
			
			@Override
			public void onCriarNovo(String filter) {
				Notification.show("Selecionado Criar Novo: " + filter);
			}
			
			@Override
			public List<TipoDocumento> getResultado(String q) {
				return tipoDocumentoDAO.query(q);
			}
			
			@Override
			public Class<TipoDocumento> getEntityClass() {
				return TipoDocumento.class;
			}
			
			@Override
			public String getCaptionProperty() {
				return "nome";
			}

			@Override
			public void onEditar(TipoDocumento value) {
				Notification.show("Selecionado Editar: " + value.getNome());
				
			}
		};
		subView.getCmbTipoDocumento().setModel(model);
		subView.getCmbTipoDocumento().setValue(currentBean.getTipoDocumento());*/
	}

	@Override
	protected void actionSalvar() {

		currentBean.setNome(subView.getTxtNome().getValue());
		currentBean.setDescricao(subView.getTxtDescricao().getValue());
		currentBean.setPalavraChave(subView.getTxtPalavraChave().getValue());
		currentBean.setDataFimVigencia(subView.getDtFimVigencia().getValue());
		currentBean.setTipoDocumento((TipoDocumento) subView.getCmbTipoDocumento().getValue());
		currentBean.setPodeAlterar(subView.getCkbPodeAlterar().getValue());
		currentBean.setPodeExcluir(subView.getCkbPodeExcluir().getValue());
		currentBean.setAssinado(false);

		try {
			documentoDAO.saveOrUpdate(currentBean);

			gravarAnexo(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
			subView.getUpArquivo().setValue(null);
			subView.atualizaMiniatura(currentBean.getDocumentos());
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	private void gravarAnexo(Documento documento) throws IOException {
		File tmpFile = null;

		String caminho = null;
		String hash = null;

		tmpFile = (File) subView.getUpArquivo().getValue();
		hash = Util.md5Arquivo(tmpFile.getAbsolutePath());
		String nomeArquivoSelecionado = subView.getNomeArquivo();
		byte[] temp = Util.lerBytesArquivo(tmpFile);

		caminho = DOCUMENT_PATH + "" + currentBean.getEmpresa().getId() + File.separator + documento.getId()
				+ File.separator + hash + getExtensao(nomeArquivoSelecionado);

		File arquivo = Util.gravarArquivo(caminho, temp);

		if (arquivo != null && arquivo.exists()) {

			if (currentBean.getAssinado()) {
				File certificado = (File) subView.getUpAssinatura().getValue();
				byte[] assinatura = Util.geraAssinaturaArquivo(Util.lerBytesArquivo(arquivo), certificado, subView
						.getPwSenhaCertificado().getValue().toCharArray());
				// usa o array de bytes e salva
				Util.gravarArquivo(hash, assinatura);
			}
		}

		DocumentoArquivo doc = new DocumentoArquivo();
		doc.setCaminho(caminho);
		doc.setHash(hash);
		doc.setExtensaoArquivo(getExtensao(caminho));

		doc.setDocumento(currentBean);
		currentBean.getDocumentos().add(doc);

		VersaoDocumento versao = verificaVersao(doc);
		documentoDAO.saveOrUpdate(versao);
		try {
			tmpFile.delete();
			if (subView.getNomeArquivoVisualizacao() != null) {
				new File(subView.getNomeArquivoVisualizacao()).delete();
			}

		} catch (Exception e) {
		}

	}

	private VersaoDocumento verificaVersao(DocumentoArquivo doc) {
		VersaoDocumento versao = null;
		String acao = null;
		Usuario usuario = SecuritySessionProvider.getUsuario();
		Colaborador colaborador = usuario.getColaborador();

		Documento original = documentoDAO.find(currentBean.getId());

		if (original.getDocumentos().size() > 0) {
			acao = "A";

			DocumentoArquivo arquivoOriginal = original.getDocumentos().iterator().next();

			if (!arquivoOriginal.getHash().equals(doc.getHash())) {

				versao = new VersaoDocumento();
				versao.setAcao(acao);
				versao.setCaminho(doc.getCaminho());
				versao.setDataHora(new Date());
				versao.setDocumento(currentBean);
				versao.setHashArquivo(doc.getHash());
				versao.setVersao(getProximoNumeroVersao(original));
				versao.setColaborador(colaborador);
			}
		} else {
			acao = "I";
			versao = new VersaoDocumento();
			versao.setAcao(acao);
			versao.setCaminho(doc.getCaminho());
			versao.setDataHora(new Date());
			versao.setDocumento(currentBean);
			versao.setHashArquivo(doc.getHash());
			versao.setColaborador(colaborador);
			versao.setVersao(1);
		}

		return versao;
	}

	private Integer getProximoNumeroVersao(Documento documento) {
		return documentoDAO.nextVersionNumber(documento);
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Documento";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		Usuario usuario = SecuritySessionProvider.getUsuario();
		Colaborador colaborador = usuario.getColaborador();
		for (Serializable id : ids) {

			Documento documento = documentoDAO.find(id);
			documento.setDataExclusao(new Date());
			documentoDAO.saveOrUpdate(documento);

			VersaoDocumento versao = new VersaoDocumento();
			versao.setAcao("E");
			versao.setCaminho(documento.getDocumentos().get(0).getCaminho());
			versao.setDataHora(new Date());
			versao.setDocumento(documento);
			versao.setHashArquivo(documento.getDocumentos().get(0).getHash());
			versao.setVersao(getProximoNumeroVersao(documento));
			versao.setColaborador(colaborador);
			documentoDAO.saveOrUpdate(versao);
		}

		mensagemRemovidoOK();
	}

	private String getExtensao(String caminho) {
		if (caminho != null && !caminho.isEmpty()) {
			int indiceExtensao = caminho.lastIndexOf(".");
			if (indiceExtensao > -1) {
				return caminho.substring(indiceExtensao, caminho.length());
			}
		}
		return "";
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "documentoForm";
	}
}