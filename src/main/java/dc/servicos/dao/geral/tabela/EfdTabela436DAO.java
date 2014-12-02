package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.EfdTabela436;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class EfdTabela436DAO extends AbstractCrudDAO<EfdTabela436>{

	@Override
	public Class<EfdTabela436> getEntityClass() {
		return EfdTabela436.class;
	}
	
	@Transactional
	public List<EfdTabela436> listaTodos() {
		return getSession().createQuery("from EfdTabela436").list();
	}

	@Transactional
	public List<EfdTabela436> procuraNomeContendo(String query) {
		return getSession().createQuery("from EfdTabela436 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao"};
	}


}
