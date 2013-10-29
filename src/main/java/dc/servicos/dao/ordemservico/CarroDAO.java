package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Carro;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CarroDAO extends AbstractCrudDAO<Carro>{

	@Override
	public Class<Carro> getEntityClass() {
		return Carro.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"placa"};
	}
	
	@Transactional
	public List<Carro> listaTodos() {
		return getSession().createQuery("from Carro").list();
	}
}
