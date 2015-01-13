package dc.model.dao.geral.produto;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.produto.GrupoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class GrupoDAOImpl extends AbstractCrudDAO<GrupoEntity> implements
		GrupoDAO<GrupoEntity> {

	private static Logger logger = Logger.getLogger(GrupoDAOImpl.class);

	@Override
	public Class<GrupoEntity> getEntityClass() {
		return GrupoEntity.class;
	}

	public List<GrupoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<GrupoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<GrupoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<GrupoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<GrupoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<GrupoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome" };
	}

}