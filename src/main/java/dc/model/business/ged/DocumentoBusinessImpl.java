package dc.model.business.ged;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import dc.entidade.administrativo.seguranca.UsuarioEntity;
import dc.entidade.geral.ged.Documento;
import dc.entidade.geral.ged.DocumentoArquivo;
import dc.entidade.geral.ged.VersaoDocumento;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.geral.ged.DocumentoDAO;
import dc.servicos.util.Util;
import dc.visao.spring.SecuritySessionProvider;

@org.springframework.stereotype.Component
@Scope("singleton")
public class DocumentoBusinessImpl implements DocumentoBusiness {

	@Autowired
	private DocumentoDAO documentoDAO;

	@Override
	public void gravarAnexo(Documento documento, List<String> listArquivos, File certificado, String senhaCertificado) throws IOException {
		File tmpFile = null;

		String caminho = null;
		String hash = null;

		for (int i = 0; i < listArquivos.size(); i++) {
			String file = listArquivos.get(i);

			tmpFile = new File(file);

			try {
				hash = Util.md5Arquivo(tmpFile.getAbsolutePath());
			} catch (Exception e) {
				hash = "0";
			}

			if (file.indexOf(hash) == -1 && !hash.equals("0")) {

				byte[] temp = Util.lerBytesArquivo(tmpFile);

				String homePath = System.getProperty("user.home");
				String customCompanyBaseFolder = "dc-erp";
				String arqOriginal = file;

				// String copyArquivo = homePath + "/" + customCompanyBaseFolder
				// + "/" + documento.getEmpresa().getId() + "/" +
				// documento.getId() + "/" + file;

				caminho = homePath + "/" + customCompanyBaseFolder + "/" + documento.getEmpresa().getId() + "/" + documento.getId() + "/" + hash + getExtensao(file);

				Util.copyFile(new File(arqOriginal), new File(caminho));

				File arquivo = Util.gravarArquivo(caminho, temp);

				if (arquivo != null && arquivo.exists()) {
					if (documento.getAssinado()) {
						byte[] assinatura = Util.geraAssinaturaArquivo(Util.lerBytesArquivo(arquivo), certificado, senhaCertificado.toCharArray());
						// usa o array de bytes e salva
						Util.gravarArquivo(hash, assinatura);
					}
				}

				DocumentoArquivo doc = new DocumentoArquivo();
				doc.setCaminho(caminho);
				doc.setHash(hash);
				doc.setExtensaoArquivo(getExtensao(caminho));

				doc.setDocumento(documento);
				documento.getDocumentos().add(doc);

				VersaoDocumento versao = verificaVersao(documento, doc);

				if (versao != null){
					documentoDAO.saveOrUpdate(versao);
				}
				try {
					tmpFile.delete();
				} catch (Exception e) {

				}
			}
		}
		
		documentoDAO.saveOrUpdate(documento);
	}

	public VersaoDocumento verificaVersao(Documento documento, DocumentoArquivo doc) {
		VersaoDocumento versao = null;
		String acao = null;
		UsuarioEntity usuario = SecuritySessionProvider.getUsuario();
		ColaboradorEntity colaborador = usuario.getColaborador();

		if (colaborador == null) {
			return null;
		}
		Documento original = documentoDAO.find(documento.getId());

		if (original.getDocumentos().size() > 0) {
			acao = "A";

			DocumentoArquivo arquivoOriginal = original.getDocumentos().iterator().next();

			if (!arquivoOriginal.getHash().equals(doc.getHash())) {

				versao = new VersaoDocumento();
				versao.setAcao(acao);
				versao.setCaminho(doc.getCaminho());
				versao.setDataHora(new Date());
				versao.setDocumento(documento);
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
			versao.setDocumento(documento);
			versao.setHashArquivo(doc.getHash());
			versao.setColaborador(colaborador);
			versao.setVersao(1);
		}

		return versao;
	}

	public Integer getProximoNumeroVersao(Documento documento) {
		return documentoDAO.nextVersionNumber(documento);
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
	
	public void removeArquivo(String caminho) throws Exception {
		File arquivo = new File(caminho);
		try {
		   arquivo.delete();
		} catch (Exception e) {
			throw e;
		} 

	}

}
