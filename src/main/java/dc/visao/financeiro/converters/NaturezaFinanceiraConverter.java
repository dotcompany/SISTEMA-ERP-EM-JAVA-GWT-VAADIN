package dc.visao.financeiro.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.financeiro.NaturezaFinanceira;

public class NaturezaFinanceiraConverter implements Converter<String, NaturezaFinanceira> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public NaturezaFinanceira convertToModel(String value, Class<? extends NaturezaFinanceira> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Verificar
		return null;
	}

	@Override
	public String convertToPresentation(NaturezaFinanceira value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return value.getTipo();
	}

	@Override
	public Class<NaturezaFinanceira> getModelType() {
		// TODO Auto-generated method stub
		return NaturezaFinanceira.class;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}

}
