package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrcamentoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrcamentoOsDAO extends AbstractCrudDAO<OrcamentoOsEntity>{

	@Override
	public Class<OrcamentoOsEntity> getEntityClass() {
		return OrcamentoOsEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrcamentoOsEntity> listaTodos() {
		return getSession().createQuery("from OrcamentoOsEntity").list();
	}
}
