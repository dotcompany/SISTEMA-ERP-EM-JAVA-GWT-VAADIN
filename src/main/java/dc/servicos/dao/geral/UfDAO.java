package dc.servicos.dao.geral;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.UfEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class UfDAO extends AbstractCrudDAO<UfEntity> {

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
		return getSession().createQuery("from UF where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	@Transactional
	public UfEntity find(String sigla) throws Exception {
		try {
			String sql = "FROM :entity ent WHERE (1 = 1) AND ent.sigla = :sigla";
			sql = sql.replace(":entity", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("sigla", sigla);

			UfEntity entity = (UfEntity) query.uniqueResult();

			if (entity == null) {
				entity = new UfEntity();
			}

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "sigla" };
	}

	@Transactional
	public List<UfEntity> query(String q) {
		q = "%" + q.toLowerCase() + "%";
		return getSession().createQuery("from UF where lower(nome) like :q")
				.setParameter("q", q).list();
	}

}