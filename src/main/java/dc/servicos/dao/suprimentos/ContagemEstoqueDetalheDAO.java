package dc.servicos.dao.suprimentos;

import dc.entidade.suprimentos.ContagemEstoqueDetalhe;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

public class ContagemEstoqueDetalheDAO extends AbstractCrudDAO<ContagemEstoqueDetalhe> {

	@Override
	protected Class<ContagemEstoqueDetalhe> getEntityClass() {
		return ContagemEstoqueDetalhe.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
