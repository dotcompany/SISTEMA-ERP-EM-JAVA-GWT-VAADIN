package dc.visao.spring;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.builtin.NumericEncodingDateBridge;

public class DCDateBridge extends NumericEncodingDateBridge {
	public static Resolution RESOLUTION = Resolution.SECOND;


	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		if (value != null) {
			Long indexedValue = Long.valueOf(DateTools.dateToString((Date) value, RESOLUTION));
			luceneOptions.addNumericFieldToDocument(name, indexedValue, document);
		}
	}

	@Override
	public Object get(String name, Document document) {
		String fromLucene = document.get(name);
		return Long.parseLong(fromLucene);
	}
}
/*
 * implements FieldBridge, ParameterizedBridge {
 * 
 * public static Resolution RESOLUTION = Resolution.MILLISECOND;
 * 
 * public void setParameterValues(Map parameters) { RESOLUTION = (Resolution)
 * parameters.get("Resolution"); }
 * 
 * public String objectToString(Object object) {
 * 
 * return DateTools.dateToString((Date) object, RESOLUTION); }
 * 
 * public Object stringToObject(String stringValue) { try { return
 * DateTools.stringToDate(stringValue); } catch (ParseException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } return null; }
 * 
 * public void set(String name, Object value, Document document, LuceneOptions
 * luceneOptions) {
 * 
 * //luceneOptions.addFieldToDocument(name, objectToString(value), document);
 * 
 * Field field = new Field(name, objectToString(value), new
 * FieldType(FieldType.NumericType)); // field.setBoost(
 * luceneOptions.getBoost() ); document.add(field);
 * 
 * }
 * 
 * }
 */