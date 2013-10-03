package dc.servicos.dao.tributario;

import org.springframework.stereotype.Repository;
import dc.entidade.tributario.ICMSConfiguracaoTributaria;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ICMSConfiguracaoTributariaDAO extends AbstractCrudDAO<ICMSConfiguracaoTributaria> {

	@Override
	public Class<ICMSConfiguracaoTributaria> getEntityClass() {
		return ICMSConfiguracaoTributaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"empresa"};
	}


}
 

