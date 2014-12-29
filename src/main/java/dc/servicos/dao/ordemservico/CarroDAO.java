package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.CarroEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CarroDAO extends AbstractCrudDAO<CarroEntity>{

	@Override
	public Class<CarroEntity> getEntityClass() {
		return CarroEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"placa"};
	}
	
	@Transactional
	public List<CarroEntity> listaTodos() {
		return getSession().createQuery("from CarroEntity").list();
	}
}
