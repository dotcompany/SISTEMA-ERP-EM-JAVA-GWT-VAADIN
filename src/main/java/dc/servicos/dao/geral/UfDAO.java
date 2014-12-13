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
		String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
		sql = sql.replace("#", this.getEntityClass().getName());
		sql = sql.replace("-", this.getEntityClass().getSimpleName()
				+ "(ent.id, ent.nome)");

		return getSession().createQuery(sql).list();
	}

	@Transactional
	public List<UfEntity> procuraNomeContendo(String query) {
		String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
		sql = sql.replace("#", this.getEntityClass().getName());

		return getSession().createQuery(sql)
				.setParameter("q", "%" + query + "%").list();
	}

	@Transactional
	public UfEntity find(String sigla) throws Exception {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.sigla = :sigla";
			sql = sql.replace("#", getEntityClass().getName());

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
		String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
		sql = sql.replace("#", getEntityClass().getName());

		q = "%" + q.toLowerCase() + "%";

		return getSession().createQuery(sql).setParameter("q", q).list();
	}

}