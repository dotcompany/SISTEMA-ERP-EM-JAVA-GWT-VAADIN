package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.SpedPis4315;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SpedPis4315DAO extends AbstractCrudDAO<SpedPis4315>{

	@Override
	public Class<SpedPis4315> getEntityClass() {
		return SpedPis4315.class;
	}
	
	@Transactional
	public List<SpedPis4315> listaTodos() {
		return getSession().createQuery("from SpedPis4315").list();
	}

	@Transactional
	public List<SpedPis4315> procuraNomeContendo(String query) {
		return getSession().createQuery("from SpedPis4315 where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao","observacao", "inicioVigencia","fimVigencia"};
	}


}
