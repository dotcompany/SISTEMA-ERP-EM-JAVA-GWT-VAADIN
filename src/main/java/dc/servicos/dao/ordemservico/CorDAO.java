package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.CorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CorDAO extends AbstractCrudDAO<CorEntity>{

	@Override
	public Class<CorEntity> getEntityClass() {
		return CorEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<CorEntity> listaTodos() {
		return getSession().createQuery("from CorEntity").list();
	}
}


