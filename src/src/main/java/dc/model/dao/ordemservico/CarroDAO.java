package dc.model.dao.ordemservico;

import java.util.List;

import dc.model.dao.AbstractDAO;

/**
 * 
 * @author Paulo  Sérgio Ferreira
 * 
 */
public interface CarroDAO<T> extends AbstractDAO<T> {

	public List<T> list() throws Exception;

}