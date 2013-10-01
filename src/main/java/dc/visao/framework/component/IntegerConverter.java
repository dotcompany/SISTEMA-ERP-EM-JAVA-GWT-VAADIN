package dc.visao.framework.component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.vaadin.data.util.converter.Converter;

public class IntegerConverter implements Converter<String, Integer> {

	private static final long serialVersionUID = 8469805997615778349L;

	private String prefix = "";
	private String suffix = "";

	public IntegerConverter() {
	}

	public IntegerConverter(String prefix) {
		this.prefix = prefix;
	}

	public IntegerConverter(String prefix, String suffix) {
		this.prefix = prefix;
		this.suffix = suffix;
	}

	private NumberFormat getFormat() {
		DecimalFormat decimalFormat = new DecimalFormat("###,##0.00");
		return decimalFormat;
	}

	@Override
	public Integer convertToModel(String value, Class<? extends Integer> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		value = value.trim();
		value = value.replace(prefix, "");
		value = value.replace(suffix, "");
		Integer converted = null;

		try {
			converted = new BigDecimal(value).intValue();
		} catch (NumberFormatException e) {}

		return converted;
	}

	@Override
	public String convertToPresentation(Integer value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value != null) {
			return prefix + getFormat().format(value.doubleValue()) + suffix;
		}
		return null;
	}

	@Override
	public Class<Integer> getModelType() {
		return Integer.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

}
