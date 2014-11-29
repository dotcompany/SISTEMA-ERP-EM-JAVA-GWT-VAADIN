package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.EstadoCivilEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class EstadoCivilDAO extends AbstractCrudDAO<EstadoCivilEntity>{

	@Override
	public Class<EstadoCivilEntity> getEntityClass() {
		return EstadoCivilEntity.class;
	}

	@Transactional
	public List<EstadoCivilEntity> listaTodos() {
		return getSession().createQuery("from EstadoCivil").list();
	}

	@Transactional
	public List<EstadoCivilEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from EstadoCivil where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}

}
