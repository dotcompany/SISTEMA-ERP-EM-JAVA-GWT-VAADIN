package dc.model.dao.tributario;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.tributario.GrupoTributarioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class GrupoTributarioDAOImpl extends
		AbstractCrudDAO<GrupoTributarioEntity> implements
		GrupoTributarioDAO<GrupoTributarioEntity> {

	private static Logger logger = Logger
			.getLogger(GrupoTributarioDAOImpl.class);

	@Override
	public Class<GrupoTributarioEntity> getEntityClass() {
		return GrupoTributarioEntity.class;
	}

	public List<GrupoTributarioEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<GrupoTributarioEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<GrupoTributarioEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<GrupoTributarioEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<GrupoTributarioEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<GrupoTributarioEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + q + "%").list();

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