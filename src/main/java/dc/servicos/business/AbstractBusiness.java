package dc.servicos.business;

import java.util.List;

/**
 * 
 * @author Gutemberg A Da Silva
 * 
 */

public interface AbstractBusiness<T> {

	public void delete(T entity) throws Exception;

	public void saveOrUpdate(T entity) throws Exception;

	public List<T> search(T entity) throws Exception;

}