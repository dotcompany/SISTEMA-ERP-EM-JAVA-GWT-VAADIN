package dc.model.dao.geral.produto;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.produto.MarcaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class MarcaDAOImpl extends AbstractCrudDAO<MarcaEntity> implements
		MarcaDAO<MarcaEntity> {

	private static Logger logger = Logger.getLogger(MarcaDAOImpl.class);

	@Override
	public Class<MarcaEntity> getEntityClass() {
		return MarcaEntity.class;
	}

	public List<MarcaEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<MarcaEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<MarcaEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<MarcaEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<MarcaEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<MarcaEntity> auxLista = query.list();

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