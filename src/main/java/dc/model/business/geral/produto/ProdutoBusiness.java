package dc.model.business.geral.produto;

import java.util.List;

import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface ProdutoBusiness<T> extends AbstractBusiness<T>,
		AbstractComboBusiness<T> {

	public List<T> list() throws Exception;

}