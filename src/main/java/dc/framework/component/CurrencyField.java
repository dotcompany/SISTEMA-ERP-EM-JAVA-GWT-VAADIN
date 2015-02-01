package dc.framework.component;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

import dc.control.util.NumberUtils;
import dc.control.util.StringUtils;

public class CurrencyField extends TextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static NumberFormat NUMBER_FORMAT = NumberFormat
			.getCurrencyInstance();

	public CurrencyField(String caption) {
		// TODO Auto-generated constructor stub
		super(caption);

		this.addTextChangeListener(new TextChangeListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub
				String newValue = event.getText();

				newValue = newValue.replaceAll("[^0-9]+", "");

				if (StringUtils.isBlank(newValue)) {
					newValue = NUMBER_FORMAT.format(new Double(0));
				} else {
					newValue = NUMBER_FORMAT.format(Double.parseDouble(newValue
							.trim()) / 100);
				}

				setValue(newValue);
			}

		});
	}

	public String getNoCurrencyValue() {
		// TODO Auto-generated method stub
		// System.out.println(":: getNoCurrencyValue()");

		String newValue = this.getValue().replaceAll("[^\\,0-9]+", "")
				.replaceAll(",", ".");

		if (StringUtils.isBlank(newValue)) {
			newValue = "0";
		}

		return newValue;
	}

	public BigDecimal getBigDecimalValue() {
		// TODO Auto-generated method stub
		// System.out.println(":: getNoCurrencyValue()");

		String newValue = this.getValue().replaceAll("[^\\,0-9]+", "")
				.replaceAll(",", ".");

		if (StringUtils.isBlank(newValue)) {
			newValue = "0";
		}

		return NumberUtils.createBigDecimal(newValue);
	}

	public void setValue(BigDecimal newValue)
			throws com.vaadin.data.Property.ReadOnlyException {
		// TODO Auto-generated method stub
		// System.out.println(":: setValue(BigDecimal newValue)");

		if (NumberUtils.isBlank(newValue)) {
			newValue = new BigDecimal(0);
		}

		String value = NUMBER_FORMAT.format(newValue);

		super.setValue(value);
	}

}