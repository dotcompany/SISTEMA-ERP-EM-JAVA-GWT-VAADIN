package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.Csosnb;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CsosnbDAO extends AbstractCrudDAO<Csosnb>{

	@Override
	protected Class<Csosnb> getEntityClass() {
		return Csosnb.class;
	}
	
	@Transactional
	public List<Csosnb> listaTodos() {
		return getSession().createQuery("from Csosnb").list();
	}

	@Transactional
	public List<Csosnb> procuraNomeContendo(String query) {
		return getSession().createQuery("from Csosnb where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}


}
