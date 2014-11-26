package dc.servicos.dao.suprimentos;

import dc.entidade.suprimentos.RequisicaoInternaDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class RequisicaoInternaDetalheDAO extends AbstractCrudDAO<RequisicaoInternaDetalheEntity> {

	@Override
	public Class<RequisicaoInternaDetalheEntity> getEntityClass() {
		return RequisicaoInternaDetalheEntity.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
