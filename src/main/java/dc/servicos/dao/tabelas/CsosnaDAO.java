package dc.servicos.dao.tabelas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tabelas.Csosna;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CsosnaDAO extends AbstractCrudDAO<Csosna>{

	@Override
	protected Class<Csosna> getEntityClass() {
		return Csosna.class;
	}
	
	@Transactional
	public List<Csosna> listaTodos() {
		return getSession().createQuery("from Csosna").list();
	}

	@Transactional
	public List<Csosna> procuraNomeContendo(String query) {
		return getSession().createQuery("from Csosna where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","descricao", "observacao"};
	}


}
