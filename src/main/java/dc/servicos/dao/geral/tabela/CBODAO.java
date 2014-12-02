package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.CboEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class CBODAO extends AbstractCrudDAO<CboEntity>{

	@Override
	public Class<CboEntity> getEntityClass() {
		return CboEntity.class;
	}
	
	@Transactional
	public List<CboEntity> listaTodos() {
		return getSession().createQuery("from CBO").list();
	}

	@Transactional
	public List<CboEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from CBO where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","nome", "observacao"};
	}


}
