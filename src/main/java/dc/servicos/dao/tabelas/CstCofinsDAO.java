package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.CstCofins;
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
	protected Class<CstCofins> getEntityClass() {
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


}
