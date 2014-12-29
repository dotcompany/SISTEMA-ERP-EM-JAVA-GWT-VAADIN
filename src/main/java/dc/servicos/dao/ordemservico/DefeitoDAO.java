package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.DefeitoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class DefeitoDAO extends AbstractCrudDAO<DefeitoEntity>{

	@Override
	public Class<DefeitoEntity> getEntityClass() {
		return DefeitoEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<DefeitoEntity> listaTodos() {
		return getSession().createQuery("from DefeitoEntity").list();
	}
}


