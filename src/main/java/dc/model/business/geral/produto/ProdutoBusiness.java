package dc.model.business.geral.produto;

import java.util.List;

import dc.model.business.AbstractBusiness;

public interface ProdutoBusiness<T> extends AbstractBusiness<T> {

	public List<T> list() throws Exception;

}