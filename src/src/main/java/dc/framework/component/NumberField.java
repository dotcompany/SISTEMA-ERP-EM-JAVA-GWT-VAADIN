package dc.framework.component;

import java.math.BigDecimal;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;

import dc.control.util.NumberUtils;
import dc.control.util.StringUtils;

public class NumberField extends TextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberField(String caption) {
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
					newValue = String.valueOf(new Integer(0));
				}

				setValue(newValue);
			}

		});
	}

	public Integer getIntegerValue() {
		// TODO Auto-generated method stub
		// System.out.println(":: getNoCurrencyValue()");

		String newValue = this.getValue().replaceAll("[^0-9]+", "");

		if (StringUtils.isBlank(newValue)) {
			newValue = "0";
		}

		return NumberUtils.toInt(newValue);
	}

	public BigDecimal getBigDecimalValue() {
		// TODO Auto-generated method stub
		// System.out.println(":: getNoCurrencyValue()");

		String newValue = this.getValue().replaceAll("[^0-9]+", "");

		if (StringUtils.isBlank(newValue)) {
			newValue = "0";
		}

		return NumberUtils.createBigDecimal(newValue);
	}

	public void setValue(Integer newValue)
			throws com.vaadin.data.Property.ReadOnlyException {
		// TODO Auto-generated method stub
		// System.out.println(":: setValue(BigDecimal newValue)");

		if (NumberUtils.isBlank(newValue)) {
			newValue = new Integer(0);
		}

		String value = String.valueOf(newValue);
		value = value.replaceAll("[^0-9]+", "");

		super.setValue(value);
	}

	public void setValue(BigDecimal newValue)
			throws com.vaadin.data.Property.ReadOnlyException {
		// TODO Auto-generated method stub
		// System.out.println(":: setValue(BigDecimal newValue)");

		if (NumberUtils.isBlank(newValue)) {
			newValue = new BigDecimal(0);
		}

		String value = String.valueOf(newValue);
		value = value.replaceAll("[^0-9]+", "");

		super.setValue(value);
	}

}