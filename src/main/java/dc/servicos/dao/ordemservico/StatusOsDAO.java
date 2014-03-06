package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.StatusOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class StatusOsDAO extends AbstractCrudDAO<StatusOs>{

	@Override
	public Class<StatusOs> getEntityClass() {
		return StatusOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<StatusOs> listaTodos() {
		return getSession().createQuery("from StatusOs").list();
	}
}


