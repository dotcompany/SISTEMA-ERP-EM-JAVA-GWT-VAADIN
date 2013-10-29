package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Defeito;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class DefeitoDAO extends AbstractCrudDAO<Defeito>{

	@Override
	public Class<Defeito> getEntityClass() {
		return Defeito.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Defeito> listaTodos() {
		return getSession().createQuery("from Defeito").list();
	}
}


