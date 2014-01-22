package dc.visao.framework.component.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import dc.servicos.dao.framework.geral.GenericListDAO;
import dc.visao.framework.geral.CRUDListController;

public class ExcelImporter extends Importer {

	/**
	 * 
	 */

	public ExcelImporter() {
		setFieldType(FieldType.FILE);
	}

	public void processaArquivo(File value, CRUDListController listController) {
		try {
			GenericListDAO genericDAO = listController.getGenericDAO();

			FileInputStream fileInputStream = new FileInputStream(value);
			HSSFWorkbook xls = new HSSFWorkbook(fileInputStream);

			HSSFSheet sheet = xls.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			// pula o header. Primeira linha
			if (iterator.hasNext()) {
				Row header = iterator.next();
			}

			while (iterator.hasNext()) {
				Row row = iterator.next();
				// criar bean
				Object bean = listController.getEntityClass().newInstance();

				int coluna = -1;
				for (Object column : listController.getTable().getVisibleColumns()) {
					// Ignora a primeira coluna pois eh check box
					if (coluna >= 0) {
						try {
							Cell cell = row.getCell(coluna);
							PropertyUtils.setSimpleProperty(bean, column.toString(), getCellValue(cell));
						} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					coluna++;
				}

				// salvar

				genericDAO.saveOrUpdate((Serializable) bean);

			}
			fileInputStream.close();
			value.deleteOnExit();

		} catch (IOException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Object getCellValue(Cell cell) {
		Object returnValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			returnValue = cell.getNumericCellValue();
			break;

		case Cell.CELL_TYPE_STRING:
			returnValue = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			returnValue = cell.getBooleanCellValue();
			break;

		default:
			break;
		}
		return returnValue;
	}

}
