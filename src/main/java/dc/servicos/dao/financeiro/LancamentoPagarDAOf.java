package dc.servicos.dao.financeiro;

import java.util.List;

import dc.model.dao.AbstractDAO;

public interface LancamentoPagarDAOf<T> extends AbstractDAO<T> {
	
	public List<T> list() throws Exception;

}
