package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CstIpi;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CstIpiDAO extends AbstractCrudDAO<CstIpi>{

	@Override
	public Class<CstIpi> getEntityClass() {
		return CstIpi.class;
	}
	
	@Transactional
	public List<CstIpi> listaTodos() {
		return getSession().createQuery("from CstIpi").list();
	}

	@Transactional
	public List<CstIpi> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstIpi where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}


}
