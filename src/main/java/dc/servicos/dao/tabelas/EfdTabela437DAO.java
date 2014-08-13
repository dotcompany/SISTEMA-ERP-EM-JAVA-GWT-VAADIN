package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.EfdTabela437;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela437DAO extends AbstractCrudDAO<EfdTabela437>{

	@Override
	public Class<EfdTabela437> getEntityClass() {
		return EfdTabela437.class;
	}
	
	@Transactional
	public List<EfdTabela437> listaTodos() {
		return getSession().createQuery("from EfdTabela437").list();
	}

	@Transactional
	public List<EfdTabela437> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela437 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}


}
