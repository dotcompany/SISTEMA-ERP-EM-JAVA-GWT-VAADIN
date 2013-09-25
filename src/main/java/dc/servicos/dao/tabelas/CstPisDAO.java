package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CstPis;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CstPisDAO extends AbstractCrudDAO<CstPis>{

	@Override
	public Class<CstPis> getEntityClass() {
		return CstPis.class;
	}
	
	@Transactional
	public List<CstPis> listaTodos() {
		return getSession().createQuery("from CstPis").list();
	}

	@Transactional
	public List<CstPis> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstPis where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}


}
