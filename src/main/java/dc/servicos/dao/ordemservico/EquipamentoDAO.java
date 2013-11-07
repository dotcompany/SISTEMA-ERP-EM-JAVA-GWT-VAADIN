package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Equipamento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EquipamentoDAO extends AbstractCrudDAO<Equipamento>{

	@Override
	public Class<Equipamento> getEntityClass() {
		return Equipamento.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<Equipamento> listaTodos() {
		return getSession().createQuery("from Equipamento").list();
	}
}
