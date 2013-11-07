package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Marca;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class MarcaDAO extends AbstractCrudDAO<Marca>{

	@Override
	public Class<Marca> getEntityClass() {
		return Marca.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Marca> listaTodos() {
		return getSession().createQuery("from Marca").list();
	}
}


