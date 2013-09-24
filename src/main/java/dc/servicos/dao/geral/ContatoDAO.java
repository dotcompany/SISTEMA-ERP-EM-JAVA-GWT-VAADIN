package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.Contato;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class ContatoDAO extends AbstractCrudDAO<Contato>{

	@Override
	public Class<Contato> getEntityClass() {
		return Contato.class;
	}
	
	@Transactional
	public List<Contato> listaTodos() {
		return getSession().createQuery("from Contato").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[]{"nome", "email"};
	}

}
