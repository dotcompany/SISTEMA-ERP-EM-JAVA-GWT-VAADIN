package dc.model.dao.geral.pessoal;

import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.dao.AbstractDAO;

public interface ClienteDAO<T> extends AbstractDAO<T> {
	ClienteEntity findById(ClienteEntity t);
}