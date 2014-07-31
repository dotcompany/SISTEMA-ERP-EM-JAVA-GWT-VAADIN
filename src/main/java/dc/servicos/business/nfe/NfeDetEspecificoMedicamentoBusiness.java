package dc.servicos.business.nfe;

import java.io.Serializable;
import java.util.List;

import dc.servicos.business.AbstractBusiness;

public interface NfeDetEspecificoMedicamentoBusiness<T> extends AbstractBusiness<T> {

	public List<T> search(T entity) throws Exception;

	public T find(Serializable id) throws Exception;

}