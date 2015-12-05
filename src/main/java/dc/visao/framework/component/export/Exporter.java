package dc.visao.framework.component.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;

import com.vaadin.data.Container;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomTable;

public abstract class Exporter extends Button implements StreamSource {

	private static final long serialVersionUID = 1L;
	
	private FileDownloader fileDownloader;
    protected FileBuilder fileBuilder;
    private Locale locale;
    private String dateFormatString;
    protected String downloadFileName;

	private File file;

	public Exporter() {
		fileDownloader = new FileDownloader(new StreamResource(this, getDownloadFileName()));
		fileDownloader.extend(this);
	}


    public Exporter(CustomTable  table) {
    	this();
        setTableToBeExported(table);
    	file = fileBuilder.getFile();
    }

    public File exportToPDF(CustomTable table) {
        setTableToBeExported(table);
    	file = fileBuilder.getFile();
    	return file;
    }
    
    public Exporter(Container container, Object[] visibleColumns) {
		this();
		setCaption("Exporter");
		setContainerToBeExported(container);
		setVisibleColumns(visibleColumns);
	}

	public Exporter(Container container) {
		this(container, null);
	}

    
	public void setTableToBeExported(CustomTable  table) {
        setContainerToBeExported(table.getContainerDataSource());
        setVisibleColumns(table.getVisibleColumns());
        setHeader(table.getCaption());
        int coluna = 1;
        for (Object column : table.getVisibleColumns()) {
        	
        	if (coluna > 1) { 
            String header = table.getColumnHeader(column);
            if (header != null) {
                setColumnHeader(column, header);
            }
            
        }
        	
        	coluna++;
      }
    }

	public void setContainerToBeExported(Container container) {
		fileBuilder = createFileBuilder(container);
		if (locale != null) {
			fileBuilder.setLocale(locale);
		}
		if (dateFormatString != null) {
			fileBuilder.setDateFormat(dateFormatString);
		}
	}

    public void setVisibleColumns(Object[] visibleColumns) {
        fileBuilder.setVisibleColumns(visibleColumns);
    }

    public void setColumnHeader(Object propertyId, String header) {
        fileBuilder.setColumnHeader(propertyId, header);
    }

    public void setHeader(String header) {
        fileBuilder.setHeader(header);
    }
    
    public void setLocale(Locale locale){
    	this.locale = locale;
    }
    
    public void setDateFormat(String dateFormat){
    	this.dateFormatString = dateFormat;
    }
    protected abstract FileBuilder createFileBuilder(Container container);

    protected abstract String getDownloadFileName();

    public void setDownloadFileName(String fileName){
    	downloadFileName = fileName;
    	((StreamResource)fileDownloader.getFileDownloadResource()).setFilename(getDownloadFileName());
    }

	@Override
	public InputStream getStream() {
		 try {
			 FileInputStream fis = new FileInputStream(fileBuilder.getFile());
				return fis;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}