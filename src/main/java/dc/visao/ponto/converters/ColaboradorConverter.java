package dc.visao.ponto.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

import dc.entidade.pessoal.Colaborador;

public class ColaboradorConverter implements Converter<String, Colaborador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Colaborador convertToModel(String value, Class<? extends Colaborador> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Verificar
		return null;
	}

	@Override
	public String convertToPresentation(Colaborador value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		// TODO Auto-generated method stub
		return value.getPessoa().getNome();
	}

	@Override
	public Class<Colaborador> getModelType() {
		// TODO Auto-generated method stub
		return Colaborador.class;
	}

	@Override
	public Class<String> getPresentationType() {
		// TODO Auto-generated method stub
		return String.class;
	}

}
