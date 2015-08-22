package dc.visao.framework.component.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.vaadin.easyuploads.MultiFileUpload;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;

public class DCMultiFileUpload extends MultiFileUpload {
		private static final long serialVersionUID = 1L;
		private UploadFileListner uploadFileListner;
		
		public DCMultiFileUpload() {
		}
		
		public DCMultiFileUpload(UploadFileListner uploadFileListner) {
			this.uploadFileListner = uploadFileListner;
		}		
		
		@SuppressWarnings("deprecation")
		@Override
		protected void handleFile(File file, String fileName, String mimeType, long length) {
			if(uploadFileListner != null){
				uploadFileListner.handleFile(file, fileName, mimeType, length);
			}
			
			new Notification("Arquivo", "Arquivo enviado com sucesso", Notification.TYPE_HUMANIZED_MESSAGE, true).show(Page.getCurrent());
		}

		public UploadFileListner getUploadFileListner() {
			return uploadFileListner;
		}

		public void setUploadFileListner(UploadFileListner uploadFileListner) {
			this.uploadFileListner = uploadFileListner;
		}
		
		/**
		 * Cria um Button.ClickListener que realiza a ação de download do arquivo.
		 * @param arquivo
		 * @return
		 */
		public static Button.ClickListener buildDownloadButtonListener(File arquivo){

			final StreamSource s = new StreamSource() {
				private static final long serialVersionUID = 1L;

				public java.io.InputStream getStream() {
					try {
						FileInputStream fis = new FileInputStream(arquivo);
						return fis;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					return null;
				}
			};
			
			return new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("deprecation")
				public void buttonClick(Button.ClickEvent event) {

					String filename = arquivo.getName();
					StreamResource resource = new StreamResource(s, filename);
					resource.getStream().setContentType("application/image");
					resource.getStream().setFileName(filename);
					resource.getStream().setParameter("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					resource.getStream().setParameter("Content-Length", Long.toString(arquivo.length()));
					resource.setCacheTime(5000);
					resource.setMIMEType("application/image");
					Page.getCurrent().open(resource, "_blank", false);
				}
			};
		}
		
		/**
		 * Gera um objeto do tipo Embedded que é o ícone que representa o arquivo.
		 * @param nomeArquivo
		 * @param arquivoMiniatura
		 * @return
		 */
		public static Embedded geraMiniaturaArquivo(String nomeArquivo, final File arquivoMiniatura) {
			Embedded image = new Embedded();
			if (getExtensao(nomeArquivo).toLowerCase().trim().indexOf("doc") != -1) {
				image.setSource(new ThemeResource("img/word.png"));
			} else if (getExtensao(nomeArquivo).toLowerCase().trim().indexOf("xls") != -1) {
				image.setSource(new ThemeResource("img/excel.png"));
			} else if (getExtensao(nomeArquivo).toLowerCase().trim().indexOf("pdf") != -1) {
				image.setSource(new ThemeResource("img/pdf.png"));

			} else if (getExtensao(nomeArquivo).toLowerCase().trim().indexOf("txt") != -1) {
				image.setSource(new ThemeResource("img/txtfile.png"));
			} else {
				image.setSource(new FileResource(arquivoMiniatura));
			}

			image.setWidth("110px");
			image.setHeight("90px");
			image.setId(nomeArquivo);
			
			return image;
		}
		
		/**
		 * @param caminho
		 * @return a extensão do arquivo
		 */
		public static String getExtensao(String caminho) {
			if (caminho != null && !caminho.isEmpty()) {
				int indiceExtensao = caminho.lastIndexOf(".");
				if (indiceExtensao > -1) {
					return caminho.substring(indiceExtensao, caminho.length());
				}
			}
			return "";
		}
}
