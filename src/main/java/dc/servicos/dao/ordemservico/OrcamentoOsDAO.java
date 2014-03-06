package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.OrcamentoOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class OrcamentoOsDAO extends AbstractCrudDAO<OrcamentoOs>{

	@Override
	public Class<OrcamentoOs> getEntityClass() {
		return OrcamentoOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}
	
	@Transactional
	public List<OrcamentoOs> listaTodos() {
		return getSession().createQuery("from OrcamentoOs").list();
	}
}
