package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CstIcmsB;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CstIcmsBDAO extends AbstractCrudDAO<CstIcmsB>{

	@Override
	public Class<CstIcmsB> getEntityClass() {
		return CstIcmsB.class;
	}
	
	@Transactional
	public List<CstIcmsB> listaTodos() {
		return getSession().createQuery("from CstIcmsB").list();
	}

	@Transactional
	public List<CstIcmsB> procuraNomeContendo(String query) {
		return getSession().createQuery("from CstIcmsB where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}


}
