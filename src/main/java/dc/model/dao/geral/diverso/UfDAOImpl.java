package dc.model.dao.geral.diverso;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.diverso.UfEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class UfDAOImpl extends AbstractCrudDAO<UfEntity> implements
		UfDAO<UfEntity> {

	private static Logger logger = Logger.getLogger(UfDAOImpl.class);

	@Override
	public Class<UfEntity> getEntityClass() {
		return UfEntity.class;
	}

	public List<UfEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<UfEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<UfEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<UfEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<UfEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<UfEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "sigla" };
	}

	/**
	 * 
	 */

	@Transactional
	public UfEntity getObject(String sigla) throws Exception {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.sigla = :sigla";
			sql = sql.replace("#", getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("sigla", sigla);

			UfEntity entity = (UfEntity) query.uniqueResult();

			return entity;
		} catch (Exception e) {
			throw e;
		}
	}

}