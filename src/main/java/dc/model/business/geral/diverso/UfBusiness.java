package dc.model.business.geral.diverso;

import dc.model.business.AbstractBusiness;

public interface UfBusiness<T> extends AbstractBusiness<T> {

	public T getObject(String sigla) throws Exception;

}