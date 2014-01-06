package dc.servicos.dao.adm.dotcompany;

import org.springframework.stereotype.Repository;

import dc.entidade.adm.dotcompany.ParametroCliente;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ParametroClienteDAO extends AbstractCrudDAO<ParametroCliente> {

	@Override
	public Class<ParametroCliente> getEntityClass() {
		return ParametroCliente.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}
	
	
}
