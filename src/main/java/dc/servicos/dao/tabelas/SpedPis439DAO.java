package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SpedPis439;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SpedPis439DAO extends AbstractCrudDAO<SpedPis439>{

	@Override
	protected Class<SpedPis439> getEntityClass() {
		return SpedPis439.class;
	}
	
	@Transactional
	public List<SpedPis439> listaTodos() {
		return getSession().createQuery("from SpedPis439").list();
	}

	@Transactional
	public List<SpedPis439> procuraNomeContendo(String query) {
		return getSession().createQuery("from SpedPis439 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
