package dc.model.dao.ordemservico;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.ordemservico.SubGrupoOsEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 * 
 * @author Paulo Sérgio Ferreira
 * 
 */
@Repository
public class SubGrupoOsDAOImpl extends AbstractCrudDAO<SubGrupoOsEntity> implements
		SubGrupoOsDAO<SubGrupoOsEntity> {

	private static Logger logger = Logger.getLogger(SubGrupoOsDAOImpl.class);

	@Override
	public Class<SubGrupoOsEntity> getEntityClass() {
		return SubGrupoOsEntity.class;
	}

	public List<SubGrupoOsEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<SubGrupoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<SubGrupoOsEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<SubGrupoOsEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<SubGrupoOsEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<SubGrupoOsEntity> auxLista = query.list();

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