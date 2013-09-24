package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CBO;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CBODAO extends AbstractCrudDAO<CBO>{

	@Override
	public Class<CBO> getEntityClass() {
		return CBO.class;
	}
	
	@Transactional
	public List<CBO> listaTodos() {
		return getSession().createQuery("from CBO").list();
	}

	@Transactional
	public List<CBO> procuraNomeContendo(String query) {
		return getSession().createQuery("from CBO where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","nome", "observacao"};
	}


}
