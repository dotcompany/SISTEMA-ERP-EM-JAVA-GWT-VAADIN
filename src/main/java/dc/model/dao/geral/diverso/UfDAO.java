package dc.model.dao.geral.diverso;

import dc.model.dao.AbstractDAO;

public interface UfDAO<T> extends AbstractDAO<T> {

	public T getObject(String sigla) throws Exception;

}