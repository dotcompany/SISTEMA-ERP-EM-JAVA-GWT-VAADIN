package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.TipoServicoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoServicoOsDAO extends AbstractCrudDAO<TipoServicoOsEntity>{

	@Override
	public Class<TipoServicoOsEntity> getEntityClass() {
		return TipoServicoOsEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<TipoServicoOsEntity> listaTodos() {
		return getSession().createQuery("from TipoServicoOsEntity").list();
	}
}


