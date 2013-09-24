package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.TipoCreditoPis;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class TipoCreditoPisDAO extends AbstractCrudDAO<TipoCreditoPis>{

	@Override
	public Class<TipoCreditoPis> getEntityClass() {
		return TipoCreditoPis.class;
	}
	
	@Transactional
	public List<TipoCreditoPis> listaTodos() {
		return getSession().createQuery("from TipoCreditoPis").list();
	}

	@Transactional
	public List<TipoCreditoPis> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoCreditoPis where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}


}
