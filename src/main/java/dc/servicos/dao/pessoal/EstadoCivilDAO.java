package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.EstadoCivil;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class EstadoCivilDAO extends AbstractCrudDAO<EstadoCivil>{

	@Override
	public Class<EstadoCivil> getEntityClass() {
		return EstadoCivil.class;
	}

	@Transactional
	public List<EstadoCivil> listaTodos() {
		return getSession().createQuery("from EstadoCivil").list();
	}

	@Transactional
	public List<EstadoCivil> procuraNomeContendo(String query) {
		return getSession().createQuery("from EstadoCivil where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}

}
