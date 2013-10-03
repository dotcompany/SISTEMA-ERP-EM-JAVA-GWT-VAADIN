package dc.servicos.dao.tabelas;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CstCofins;
import dc.entidade.tabelas.CstPis;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CstCofinsDAO extends AbstractCrudDAO<CstCofins>{

	@Override
	public Class<CstCofins> getEntityClass() {
		return CstCofins.class;
	}
	
	@Transactional
	public List<CstCofins> listaTodos() {
		return getSession().createQuery("from CstCofins").list();
	}

	@Transactional
	public List<CstCofins> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstCofins where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}
	
	@Transactional
	public CstCofins procuraPorCodigo(String codigo){
		CstCofins cst = null;
		Criteria c = getSession().createCriteria(CstCofins.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cst = (CstCofins)c.uniqueResult();
		return cst;
	}


}
