package dc.servicos.dao.empresa;

import org.springframework.stereotype.Repository;

import dc.entidade.empresa.QuadroSocietario;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class QuadroSocietarioDAO extends AbstractCrudDAO<QuadroSocietario> {

	

	@Override
	public Class<QuadroSocietario> getEntityClass() {
		return QuadroSocietario.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro"};
	}
	
}
