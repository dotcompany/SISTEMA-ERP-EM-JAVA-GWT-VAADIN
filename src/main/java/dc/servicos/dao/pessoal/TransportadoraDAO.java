package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.Transportadora;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*/


@Repository
@SuppressWarnings("unchecked")
public class TransportadoraDAO extends AbstractCrudDAO<Transportadora>{

	@Override
	protected Class<Transportadora> getEntityClass() {
		return Transportadora.class;
	}

	@Transactional
	public List<Transportadora> listaTodos() {
		return getSession().createQuery("from Transportadora").list();
	}

	@Transactional
	public List<Transportadora> procuraNomeContendo(String query) {
		return getSession().createQuery("from Transportadora where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"observacao"};
	}

}
