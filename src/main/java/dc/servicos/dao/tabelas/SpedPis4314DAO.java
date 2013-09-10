package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SpedPis4314;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SpedPis4314DAO extends AbstractCrudDAO<SpedPis4314>{

	@Override
	protected Class<SpedPis4314> getEntityClass() {
		return SpedPis4314.class;
	}
	
	@Transactional
	public List<SpedPis4314> listaTodos() {
		return getSession().createQuery("from SpedPis4314").list();
	}

	@Transactional
	public List<SpedPis4314> procuraNomeContendo(String query) {
		return getSession().createQuery("from SpedPis4314 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
