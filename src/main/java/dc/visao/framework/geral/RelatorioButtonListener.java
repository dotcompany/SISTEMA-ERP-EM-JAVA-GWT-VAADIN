package dc.visao.framework.geral;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;

import com.vaadin.server.DownloadStream;
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

import dc.entidade.framework.AbstractModel;
import dc.entidade.relatorio.Relatorio;
import dc.entidade.relatorio.RelatorioParameterView;
import dc.entidade.relatorio.TipoRelatorio;

public class RelatorioButtonListener implements ClickListener {
	private final Relatorio relatorio;
	private final CRUDListController crudListController;
	private final RelatorioParameterView relatorioParameterView;

	public RelatorioButtonListener(Relatorio relatorio, CRUDListController crudListController, RelatorioParameterView parameterView) {
		this.relatorio = relatorio;
		this.crudListController = crudListController;
		this.relatorioParameterView = parameterView;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		try {

			if (relatorioParameterView != null) {

				VerticalLayout relatorioPopUp = new VerticalLayout();
				relatorioPopUp.setMargin(true);
				relatorioPopUp.setSpacing(true);
				relatorioPopUp.addComponent(relatorioParameterView);

				Button imprimir = new Button();
				imprimir.setCaption("Imprimir");
				relatorioPopUp.addComponent(imprimir);

				crudListController.openOnNewWindow(3, CRUDListController.WINDOW_LIST, relatorioPopUp);
				imprimir.addClickListener(new ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							printListener(relatorioParameterView.getParametersMap(), relatorioParameterView.getJRDataSource());
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

			} else {

				JRDataSource dataSource;
				if (TipoRelatorio.getEnum(relatorio.getTipo()).equals(TipoRelatorio.LISTAGEM)) {
					LazyQueryContainer containerDataSource = (LazyQueryContainer) crudListController.getTable().getContainerDataSource();

					List<?> itemIds = containerDataSource.getItemIds(0, Integer.MAX_VALUE);

					List<AbstractModel> items = new ArrayList<>();
					for (Object id : itemIds) {
						AbstractModel bean = (AbstractModel) crudListController.getMainDao().find((Serializable) id);
						items.add(bean);
					}

					dataSource = new JRBeanCollectionDataSource(items);
				} else {
					List<AbstractModel> modelBeanList = new ArrayList<>();
					AbstractModel modelBean = this.crudListController.getFormController().getModelBean();
					modelBeanList.add(modelBean);
					dataSource = new JRBeanCollectionDataSource(modelBeanList);
				}

				printListener(new HashMap<String, Object>(), dataSource);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printListener(Map<String, Object> params, JRDataSource dataSource) throws JRException {

		if (dataSource == null) {
			dataSource = new JREmptyDataSource();
		}

		JasperReport report = JasperCompileManager.compileReport(relatorio.getJasperPath());

		final byte[] bytes = JasperRunManager.runReportToPdf(report, params, dataSource);

		final StreamSource s = new StreamSource() {

			public java.io.InputStream getStream() {
				ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				return bais;
			}
		};

		String filename = relatorio.getNome().replace(" ", "_").concat(".pdf").toLowerCase();

		filename = filename.replaceAll("[éèêë]", "e");
		filename = filename.replaceAll("[áàäâã]", "a");
		filename = filename.replaceAll("[úùüû]", "u");
		filename = filename.replaceAll("[óòôõö]", "o");
		filename = filename.replaceAll("[íìïî]", "i");
		filename = filename.replaceAll("ç", "c");

		StreamResource resource = new StreamResource(s, filename);

		DownloadStream stream = resource.getStream();
		stream.setContentType("application/pdf");
		stream.setFileName(filename);
		stream.setParameter("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		stream.setParameter("Content-Length", String.valueOf(bytes.length));
		resource.setCacheTime(5000);
		resource.setMIMEType("application/pdf");
		Page.getCurrent().open(resource, "_blank", false);
	}

}