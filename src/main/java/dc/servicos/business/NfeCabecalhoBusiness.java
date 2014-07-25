package dc.servicos.business;

import java.io.Serializable;
import java.util.List;

public interface NfeCabecalhoBusiness<T> extends AbstractBusiness<T> {

	public List<T> search(T entity) throws Exception;

	public T find(Serializable id) throws Exception;

}