package dc.controller.geral.ged;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.TipoDocumento;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.business.ged.DocumentoBusiness;
import dc.servicos.dao.geral.ged.DocumentoDAO;
import dc.servicos.dao.geral.ged.TipoDocumentoDAO;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.servicos.util.Util;
import dc.servicos.util.Validator;
import dc.visao.framework.component.CompanyFileHandler;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;
import dc.visao.geral.ged.DocumentoFormView;
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
	private DocumentoBusiness documentoBusiness;

	
	@Autowired
	private MainController mainController;

	@Autowired
	private TipoDocumentoDAO tipoDocumentoDAO;

	@Autowired
	private ColaboradorDAO colaboradorDAO;

	private Documento currentBean;

	@Autowired
	private CompanyFileHandler companyFileHandler;

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

		if (subView.getCkbAssinado().getValue()) {
			File arquivoCertificado = (File) subView.getUpAssinatura().getValue();
			if (!Validator.validateObject(arquivoCertificado) || !arquivoCertificado.exists()) {
				adicionarErroDeValidacao(subView.getUpArquivo(), "Selecione o arquivo de certificado");
			}
		}

		if (subView.getCkbTemplate().getValue()) {
			List<String> listArquivos = subView.getListArquivos();
			for (int i = 0; i < listArquivos.size(); i++) {
				String file = listArquivos.get(i);

				if(!file.contains(".doc")){
					adicionarErroDeValidacao(subView.getUpArquivo(), "Os templates de contrato devem ser no formato WORD com a extensão .doc");
					valido = false;
					break;
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
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		currentBean.setEmpresa(empresa);
		subView.setIdEmpresa(getIDEmpresa());
		subView.setListArquivos(new ArrayList<String>());
	}

	public String getIDEmpresa() {
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		EmpresaEntity empresa = usuario.getConta().getEmpresa();
		return empresa.getId().toString();

	}

	@Override
	protected void initSubView() {
		subView = new DocumentoFormView();
		companyFileHandler = new CompanyFileHandler();
		DefaultManyToOneComboModel<TipoDocumento> model = new DefaultManyToOneComboModel<TipoDocumento>(TipoDocumentoListController.class, tipoDocumentoDAO, mainController);
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
		subView.getCkbTemplate().setValue(currentBean.getTemplateContrato());
		subView.setListArquivos(new ArrayList<String>());

		List<DocumentoArquivo> listArquivos = currentBean.getDocumentos();

		subView.setIdDocumento(currentBean.getId().toString());

		subView.setIdEmpresa(getIDEmpresa());

		for (int i = 0; i < listArquivos.size(); i++) {
			String arquivo = listArquivos.get(i).getCaminho();
			subView.atualizaMiniatura(new File(arquivo), new File(arquivo).getName(), "A", (i + 1));
		}

		subView.getCmbTipoDocumento().setValue(currentBean.getTipoDocumento());

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
		currentBean.setTemplateContrato(subView.getCkbTemplate().getValue());
		currentBean.setAssinado(false);

		try {

			Integer id = currentBean.getId();
			if (id == null) {
				id = new Integer(0);
			}

			documentoDAO.saveOrUpdate(currentBean);
			File certificado = (File) subView.getUpAssinatura().getValue();
			String senhaCertificado = subView.getPwSenhaCertificado().getValue();
			List<String> listArquivos = subView.getListArquivos();
			
			documentoBusiness.gravarAnexo(currentBean, listArquivos, certificado, senhaCertificado);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
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
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		ColaboradorEntity colaborador = usuario.getColaborador();

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
			versao.setVersao(documentoBusiness.getProximoNumeroVersao(documento));
			versao.setColaborador(colaborador);
			documentoDAO.saveOrUpdate(versao);
		}

		mensagemRemovidoOK();
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "documentoForm";
	}

	@Override
	public Documento getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}