package dc.servicos.dao.empresa;

import org.springframework.stereotype.Repository;

import dc.entidade.empresa.QuadroSocietarioEntity;
import dc.entidade.empresa.SocioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SocioDAO extends AbstractCrudDAO<SocioEntity> {


	@Override
	public Class<SocioEntity> getEntityClass() {
		return SocioEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"numero"};
	}
	
}

