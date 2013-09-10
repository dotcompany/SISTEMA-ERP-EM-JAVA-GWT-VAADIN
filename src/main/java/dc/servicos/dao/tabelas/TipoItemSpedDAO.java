package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.TipoItemSped;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoItemSpedDAO extends AbstractCrudDAO<TipoItemSped>{

	@Override
	protected Class<TipoItemSped> getEntityClass() {
		return TipoItemSped.class;
	}
	
	@Transactional
	public List<TipoItemSped> listaTodos() {
		return getSession().createQuery("from TipoItemSped").list();
	}

	@Transactional
	public List<TipoItemSped> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoItemSped where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}


}
