package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.EquipamentoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EquipamentoDAO extends AbstractCrudDAO<EquipamentoEntity>{

	@Override
	public Class<EquipamentoEntity> getEntityClass() {
		return EquipamentoEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<EquipamentoEntity> listaTodos() {
		return getSession().createQuery("from EquipamentoEntity").list();
	}
}
