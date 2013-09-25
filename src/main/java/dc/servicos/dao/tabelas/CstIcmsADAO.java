package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CstIcmsA;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CstIcmsADAO extends AbstractCrudDAO<CstIcmsA>{

	@Override
	public Class<CstIcmsA> getEntityClass() {
		return CstIcmsA.class;
	}
	
	@Transactional
	public List<CstIcmsA> listaTodos() {
		return getSession().createQuery("from CstIcmsA").list();
	}

	@Transactional
	public List<CstIcmsA> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstIcmsA where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}


}
