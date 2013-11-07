package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Combustivel;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CombustivelDAO extends AbstractCrudDAO<Combustivel>{

	@Override
	public Class<Combustivel> getEntityClass() {
		return Combustivel.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<Combustivel> listaTodos() {
		return getSession().createQuery("from Combustivel").list();
	}
}


