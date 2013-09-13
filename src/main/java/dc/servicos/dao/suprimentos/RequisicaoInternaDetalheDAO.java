package dc.servicos.dao.suprimentos;

import dc.entidade.suprimentos.RequisicaoInternaDetalhe;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class RequisicaoInternaDetalheDAO extends AbstractCrudDAO<RequisicaoInternaDetalhe> {

	@Override
	protected Class<RequisicaoInternaDetalhe> getEntityClass() {
		return RequisicaoInternaDetalhe.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
