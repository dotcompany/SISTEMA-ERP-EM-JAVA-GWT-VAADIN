package dc.servicos.dao.empresa;

import org.springframework.stereotype.Repository;

import dc.entidade.empresa.QuadroSocietario;
import dc.entidade.empresa.Socio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SocioDAO extends AbstractCrudDAO<Socio> {


	@Override
	public Class<Socio> getEntityClass() {
		return Socio.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"numero"};
	}
	
}

