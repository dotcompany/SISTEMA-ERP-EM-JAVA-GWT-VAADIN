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
		s = s.replaceFirst(",", ".");
		s = s.replaceAll("[\\,]+", "");
		// s = s.replaceFirst(".", ",");

		// 44.33 0.88

		String[] s1 = s.split("[.]");

		if (s1.length == 2) {
			s = (s1[0].equals("") ? "0" : s1[0]) + "," + s1[1];
		}

		return s;
	}

	public synchronized static String valueToString(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "0,00";
		}

		s1 = s1.replaceAll("[^\\,0-9]+", "");
		s1 = s1.replaceFirst(",", ".");
		s1 = s1.replaceAll("[\\,]+", "");
		// s1 = s1.replaceFirst(".", ",");

		// 44.33 0.88

		String[] s2 = s1.split("[.]");

		if (s2.length == 2) {
			s1 = (s2[0].equals("") ? "0" : s2[0]) + "," + s2[1];
		}

		return s1;
	}

	public synchronized static String valueToString(BigDecimal bd) {
		if (bd.equals(new BigDecimal(0)) || bd == null) {
			return "0,00";
		}

		String s = bd.toString().replaceAll("\\.", ",");

		return s;
	}

	public synchronized static BigDecimal stringToValue(String s) {
		if (s.equals("")) {
			return new BigDecimal(0);
		}

		s = s.replaceAll("[^\\,0-9]+", "");
		s = s.replaceFirst(",", "\\.");
		s = s.replaceAll("[\\,]+", "");
		// s = s.replaceAll(",", "\\.");

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