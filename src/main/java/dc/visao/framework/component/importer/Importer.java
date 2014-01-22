package dc.visao.framework.component.importer;

import java.io.File;

import org.vaadin.easyuploads.UploadField;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;

public abstract class Importer extends UploadField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;

	public Importer() {
		this.setFieldType(FieldType.FILE);
		this.setFileDeletesAllowed(false);
		this.addListener(new ValueChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				UploadField upload = (UploadField) event.getProperty();
				file = (File) upload.getValue();
				processarArquivo(file);
				file = null;

			}

		});

		final Upload upload = (Upload) getRootLayout().getComponent(0);
		upload.setButtonCaption("Import");
		upload.setImmediate(true);
		upload.addListener(new FailedListener() {

			@Override
			public void uploadFailed(FailedEvent event) {
				System.out.println(event);

			}
		});

		Button processar = new Button();
		processar.setCaption("Importar");
		processar.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				upload.startUpload();

			}
		});

		getRootLayout().addComponent(processar);

	}

	protected abstract void processarArquivo(File file);

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
