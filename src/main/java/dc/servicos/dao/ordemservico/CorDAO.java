package dc.servicos.dao.ordemservico;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ordemservico.Cor;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class CorDAO extends AbstractCrudDAO<Cor>{

	@Override
	public Class<Cor> getEntityClass() {
		return Cor.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	} 
	
	@Transactional
	public List<Cor> listaTodos() {
		return getSession().createQuery("from Cor").list();
	}
}


