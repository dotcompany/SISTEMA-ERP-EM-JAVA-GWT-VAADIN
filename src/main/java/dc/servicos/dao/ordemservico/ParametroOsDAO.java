package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.ParametroOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ParametroOsDAO extends AbstractCrudDAO<ParametroOs>{

	@Override
	public Class<ParametroOs> getEntityClass() {
		return ParametroOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<ParametroOs> listaTodos() {
		return getSession().createQuery("from ParametroOs").list();
	}
}
