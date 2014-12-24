package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.CombustivelEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CombustivelDAO extends AbstractCrudDAO<CombustivelEntity>{

	@Override
	public Class<CombustivelEntity> getEntityClass() {
		return CombustivelEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<CombustivelEntity> listaTodos() {
		return getSession().createQuery("from CombustivelEntity").list();
	}
}


