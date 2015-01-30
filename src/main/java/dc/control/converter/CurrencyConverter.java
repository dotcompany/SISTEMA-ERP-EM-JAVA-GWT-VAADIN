package dc.control.converter;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.TextField;

import dc.control.util.StringUtils;

public class CurrencyConverter {

	private static NumberFormat NUMBER_FORMAT = NumberFormat
			.getCurrencyInstance();

	public synchronized static String getCurrency(BigDecimal bg) {
		String value = NUMBER_FORMAT.format(bg);

		return value;
	}

	public synchronized static String removeCurrency(String value) {
		value = value.replaceAll("[^\\,0-9]+", "").replaceAll(",", ".");

		System.out.println(value);

		if (StringUtils.isBlank(value)) {
			value = "0";
		}

		return value;
	}

	public synchronized static void vceMask(TextChangeEvent event, TextField t) {
		String value = event.getText();

		value = value.replaceAll("[^0-9]+", "");

		if (StringUtils.isBlank(value)) {
			value = NUMBER_FORMAT.format(new Double(0));
		} else {
			value = NUMBER_FORMAT
					.format(Double.parseDouble(value.trim()) / 100);
		}

		t.setValue(value);
	}

	public static void main(String[] args) {
		try {

			BigDecimal bg = new BigDecimal(0);

			System.out.println(bg);

			String value = "0.980000";
			value = value.replaceAll("[^0-9]+", "");

			if (StringUtils.isBlank(value)) {
				value = NUMBER_FORMAT.format(new Double(0));
			} else {
				value = NUMBER_FORMAT.format(bg);
			}

			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}