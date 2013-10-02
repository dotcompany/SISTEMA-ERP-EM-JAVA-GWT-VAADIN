package dc.servicos.dao.tributario;

import org.springframework.stereotype.Repository;
import dc.entidade.tributario.ConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ConfiguracaoTributariaDAO extends AbstractCrudDAO<ConfiguracaoTributaria> {

	@Override
	public Class<ConfiguracaoTributaria> getEntityClass() {
		return ConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}


}
 
