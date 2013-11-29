package dc.servicos.dao.comercial;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dc.entidade.comercial.Frete;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class FreteDAO extends AbstractCrudDAO<Frete> {

	@Override
	public Class<Frete> getEntityClass() {
		return Frete.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<Frete> listaTodos() {
		return getSession().createQuery("from Frete").list();
	}
	
}
