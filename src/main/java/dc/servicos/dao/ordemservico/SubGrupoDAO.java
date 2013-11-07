package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.SubGrupo;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class SubGrupoDAO extends AbstractCrudDAO<SubGrupo>{

	@Override
	public Class<SubGrupo> getEntityClass() {
		return SubGrupo.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<SubGrupo> listaTodos() {
		return getSession().createQuery("from SubGrupo").list();
	}
}
