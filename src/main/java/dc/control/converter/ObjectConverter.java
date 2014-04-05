package dc.control.converter;

import java.math.BigDecimal;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeEvent;

public class ObjectConverter {

	/**
	 * 
	 * @param event
	 * @return
	 */

	public synchronized static String integerToString(TextChangeEvent event) {
		String s = event.getText();

		if (s.equals("")) {
			return "0";
		}

		s = s.replaceAll("[a-zA-Z]+", "");
		s = s.replaceAll("\\W", "");

		return s;
	}

	public synchronized static String integerToString(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "0";
		}

		s1 = s1.replaceAll("[a-zA-Z]+", "");
		s1 = s1.replaceAll("\\W", "");

		return s1;
	}

	public synchronized static Integer stringToInteger(String s) {
		if (s.equals("")) {
			return new Integer(0);
		}

		s = s.replaceAll("[a-zA-Z]+", "");
		s = s.replaceAll("\\W", "");

		return Integer.parseInt(s);
	}

	/**
	 * 
	 * @param event
	 * @return
	 */

	public synchronized static String valueToString(TextChangeEvent event) {
		String s = event.getText();

		if (s.equals("")) {
			return "0,00";
		}

		s = s.replaceAll("[^\\,0-9]+", "");

		return s;
	}

	public synchronized static String valueToString(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "0,00";
		}

		s1 = s1.replaceAll("[^\\,0-9]+", "");

		return s1;
	}

	public synchronized static BigDecimal stringToValue(String s) {
		if (s.equals("")) {
			return new BigDecimal(0);
		}

		s = s.replaceAll("[^\\,0-9]+", "");

		return new BigDecimal(s);
	}

	/**
	 * 
	 * @param event
	 * @return
	 */

	public synchronized static String moneyToString(TextChangeEvent event) {
		String s = event.getText();

		if (s.equals("")) {
			return "R$ 0,00";
		}

		s = "R$ " + s.replaceAll("[^\\,0-9]+", "");

		return s;
	}

	public synchronized static String moneyToString(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "R$ 0,00";
		}

		s1 = "R$ " + s.toString().replaceAll("[^\\,0-9]+", "");

		return s1;
	}

	public synchronized static BigDecimal stringToMoney(String s) {
		if (s.equals("")) {
			return new BigDecimal(0);
		}

		s = s.replaceAll("R$ ", "");
		s = s.replaceAll("[^\\,0-9]+", "");
		s = s.replaceAll(",", ".");

		return new BigDecimal(s);
	}

}