package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SpedPis4310;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SpedPis4310DAO extends AbstractCrudDAO<SpedPis4310>{

	@Override
	protected Class<SpedPis4310> getEntityClass() {
		return SpedPis4310.class;
	}
	
	@Transactional
	public List<SpedPis4310> listaTodos() {
		return getSession().createQuery("from SpedPis4310").list();
	}

	@Transactional
	public List<SpedPis4310> procuraNomeContendo(String query) {
		return getSession().createQuery("from SpedPis4310 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
