package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SpedPis4316;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SpedPis4316DAO extends AbstractCrudDAO<SpedPis4316>{

	@Override
	protected Class<SpedPis4316> getEntityClass() {
		return SpedPis4316.class;
	}
	
	@Transactional
	public List<SpedPis4316> listaTodos() {
		return getSession().createQuery("from SpedPis4316").list();
	}

	@Transactional
	public List<SpedPis4316> procuraNomeContendo(String query) {
		return getSession().createQuery("from SpedPis4316 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
