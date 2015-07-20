package dc.model.dao.tributario;

import java.util.List;

import dc.model.dao.AbstractDAO;

public interface IcmsCustomizadoDAO<T> extends AbstractDAO<T> {

	public List<T> list() throws Exception;

}