package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.BaseCreditoPis;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class BaseCreditoPisDAO extends AbstractCrudDAO<BaseCreditoPis>{

	@Override
	public Class<BaseCreditoPis> getEntityClass() {
		return BaseCreditoPis.class;
	}
	
	@Transactional
	public List<BaseCreditoPis> listaTodos() {
		return getSession().createQuery("from BaseCreditoPis").list();
	}

	@Transactional
	public List<BaseCreditoPis> procuraNomeContendo(String query) {
		return getSession().createQuery("from BaseCreditoPis where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}


}
