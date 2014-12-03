package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.UfEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;


@Repository
@SuppressWarnings("unchecked")
public class UfDAO extends AbstractCrudDAO<UfEntity>{
	
	@Override
	public Class<UfEntity> getEntityClass() {
		return UfEntity.class;
	}
	
	
	@Transactional
	public List<UfEntity> listaTodos() {
		return getSession().createQuery("from UF").list();
	}

	@Transactional
	public List<UfEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from UF where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "sigla"};
	}
	
	@Transactional
	public List<UfEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from UF where lower(nome) like :q").setParameter("q", q).list();
	}

}
