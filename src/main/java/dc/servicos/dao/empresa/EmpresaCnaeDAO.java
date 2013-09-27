
package dc.servicos.dao.empresa;

import org.springframework.stereotype.Repository;

import dc.entidade.empresa.EmpresaCnae;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class EmpresaCnaeDAO extends AbstractCrudDAO<EmpresaCnae> {

	@Override
	public Class<EmpresaCnae> getEntityClass() {
		return EmpresaCnae.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro"};
	}
	
}
