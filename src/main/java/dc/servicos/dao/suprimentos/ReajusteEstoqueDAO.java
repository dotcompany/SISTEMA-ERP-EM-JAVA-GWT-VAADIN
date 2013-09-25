package dc.servicos.dao.suprimentos;

import org.springframework.stereotype.Repository;
import dc.entidade.suprimentos.ReajusteEstoque;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ReajusteEstoqueDAO
extends AbstractCrudDAO<ReajusteEstoque>{

	@Override
	public Class<ReajusteEstoque> getEntityClass() {
		return ReajusteEstoque.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[]{"data"};
	}
}
