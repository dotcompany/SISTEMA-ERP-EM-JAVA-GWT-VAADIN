package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Modelo;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ModeloDAO extends AbstractCrudDAO<Modelo>{

	@Override
	public Class<Modelo> getEntityClass() {
		return Modelo.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Modelo> listaTodos() {
		return getSession().createQuery("from Modelo").list();
	}
}
