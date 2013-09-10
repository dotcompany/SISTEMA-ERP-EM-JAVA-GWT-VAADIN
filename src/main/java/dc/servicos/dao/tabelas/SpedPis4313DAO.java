package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SpedPis4313;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SpedPis4313DAO extends AbstractCrudDAO<SpedPis4313>{

	@Override
	protected Class<SpedPis4313> getEntityClass() {
		return SpedPis4313.class;
	}
	
	@Transactional
	public List<SpedPis4313> listaTodos() {
		return getSession().createQuery("from SpedPis4313").list();
	}

	@Transactional
	public List<SpedPis4313> procuraNomeContendo(String query) {
		return getSession().createQuery("from SpedPis4313 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
