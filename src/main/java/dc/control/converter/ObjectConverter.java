package dc.control.converter;

import com.vaadin.data.Property.ValueChangeEvent;

public class ObjectConverter {

	public synchronized static String onlyInteger(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "0";
		}

		s1 = s.toString().replaceAll("[a-zA-Z]+", "");

		return s1;
	}

	public synchronized static String onlyValue(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "0,00";
		}

		s1 = s.toString().replaceAll("[^\\,0-9]+", "");

		return s1;
	}

	public synchronized static String onlyMoney(ValueChangeEvent event) {
		Object s = event.getProperty().getValue();

		String s1 = s.toString();

		if (s1.equals("")) {
			return "R$ 0,00";
		}

		s1 = "R$ " + s.toString().replaceAll("[^\\,0-9]+", "");

		return s1;
	}

}