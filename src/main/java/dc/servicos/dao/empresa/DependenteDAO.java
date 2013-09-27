package dc.servicos.dao.empresa;

import org.springframework.stereotype.Repository;

import dc.entidade.empresa.Dependente;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class DependenteDAO extends AbstractCrudDAO<Dependente> {


	@Override
	public Class<Dependente> getEntityClass() {
		return Dependente.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"numero"};
	}
	
}


