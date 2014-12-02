package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstCofins;
import dc.entidade.geral.tabela.CstIpi;
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
	
	@Transactional
	public CstIpi procuraPorCodigo(String codigo){
		CstIpi cst = null;
		Criteria c = getSession().createCriteria(CstIpi.class);
		if(codigo!=null && !(codigo.isEmpty())){
			c.add(Restrictions.eq("codigo", codigo));
		}
		cst = (CstIpi)c.uniqueResult();
		return cst;
	}


}
