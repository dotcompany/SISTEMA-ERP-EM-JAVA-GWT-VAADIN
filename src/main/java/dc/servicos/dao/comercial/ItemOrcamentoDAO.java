package dc.servicos.dao.comercial;



import org.springframework.stereotype.Repository;

import dc.entidade.comercial.ItemOrcamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ItemOrcamentoDAO extends AbstractCrudDAO<ItemOrcamento> {

	@Override
	public Class<ItemOrcamento> getEntityClass() {
		return ItemOrcamento.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	
}


