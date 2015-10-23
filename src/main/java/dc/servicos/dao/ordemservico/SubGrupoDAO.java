package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class SubGrupoDAO extends AbstractCrudDAO<SubGrupoOsEntity>{

	@Override
	public Class<SubGrupoOsEntity> getEntityClass() {
		return SubGrupoOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<SubGrupoOsEntity> listaTodos() {
		return getSession().createQuery("from SubGrupoOsEntity").list();
	}
}
