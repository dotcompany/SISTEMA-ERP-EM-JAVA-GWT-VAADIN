package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Grupo;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class GrupoDAO extends AbstractCrudDAO<Grupo>{
 
	@Override
	public Class<Grupo> getEntityClass() {
		return Grupo.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<Grupo> listaTodos() {
		return getSession().createQuery("from Grupo").list();
	}
}


