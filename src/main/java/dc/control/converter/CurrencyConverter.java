package dc.control.converter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.TextField;

import dc.control.util.NumberUtils;
import dc.control.util.StringUtils;

public class CurrencyConverter {

	private static NumberFormat NUMBER_FORMAT = NumberFormat
			.getCurrencyInstance();

	public synchronized static String getCurrency(BigDecimal bg) {
		if (NumberUtils.isBlank(bg)) {
			bg = new BigDecimal(0);
		}

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

	/**
	 * 
	 */

	static Currency currency = Currency.getInstance("BRL");

	public static void main(String[] args) {
		try {
			BigDecimal bg = null;

			if (NumberUtils.isBlank(bg)) {
				bg = new BigDecimal(0);
			}

			String value1 = NUMBER_FORMAT.format(bg);

			System.out.println(currency.getSymbol());

			System.out.println(value1);

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