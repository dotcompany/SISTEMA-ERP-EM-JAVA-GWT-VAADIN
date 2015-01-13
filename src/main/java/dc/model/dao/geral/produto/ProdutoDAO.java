package dc.model.dao.geral.produto;

import java.util.List;

import dc.model.dao.AbstractDAO;

public interface ProdutoDAO<T> extends AbstractDAO<T> {

	public List<T> list() throws Exception;

}