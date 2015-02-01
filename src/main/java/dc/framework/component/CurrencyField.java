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
				String value = event.getText();

				value = value.replaceAll("[^0-9]+", "");

				if (StringUtils.isBlank(value)) {
					value = NUMBER_FORMAT.format(new Double(0));
				} else {
					value = NUMBER_FORMAT.format(Double.parseDouble(value
							.trim()) / 100);
				}

				setValue(value);
			}

		});
	}

	public String getNoCurrencyValue() {
		// System.out.println(":: getNoCurrencyValue()");

		String value = this.getValue().replaceAll("[^\\,0-9]+", "")
				.replaceAll(",", ".");

		if (StringUtils.isBlank(value)) {
			value = "0";
		}

		// setValue(value);

		return value;
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