package dc.model.dao.geral.outro;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.outro.SindicatoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class SindicatoDAOImpl extends AbstractCrudDAO<SindicatoEntity>
		implements SindicatoDAO<SindicatoEntity> {

	private static Logger logger = Logger.getLogger(SindicatoDAOImpl.class);

	@Override
	public Class<SindicatoEntity> getEntityClass() {
		return SindicatoEntity.class;
	}

	public List<SindicatoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<SindicatoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<SindicatoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<SindicatoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<SindicatoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<SindicatoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "Nome", "Logradouro" };
	}

}