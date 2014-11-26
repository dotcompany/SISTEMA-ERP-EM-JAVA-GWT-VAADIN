package dc.servicos.dao.suprimentos;

import dc.entidade.suprimentos.estoque.ContagemDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class ContagemEstoqueDetalheDAO extends
		AbstractCrudDAO<ContagemDetalheEntity> {

	@Override
	public Class<ContagemDetalheEntity> getEntityClass() {
		return ContagemDetalheEntity.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
