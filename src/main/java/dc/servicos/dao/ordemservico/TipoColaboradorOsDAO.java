package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.TipoColaboradorOs;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoColaboradorOsDAO extends AbstractCrudDAO<TipoColaboradorOs>{

	@Override
	public Class<TipoColaboradorOs> getEntityClass() {
		return TipoColaboradorOs.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<TipoColaboradorOs> listaTodos() {
		return getSession().createQuery("from TipoColaboradorOs").list();
	}
}


