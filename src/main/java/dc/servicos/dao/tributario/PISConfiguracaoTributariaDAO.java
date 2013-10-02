package dc.servicos.dao.tributario;


import org.springframework.stereotype.Repository;
import dc.entidade.tributario.PISConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class PISConfiguracaoTributariaDAO extends AbstractCrudDAO<PISConfiguracaoTributaria> {

	@Override
	public Class<PISConfiguracaoTributaria> getEntityClass() {
		return PISConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}


}
 


