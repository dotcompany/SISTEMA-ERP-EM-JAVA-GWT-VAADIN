package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TransportadoraDAOImpl extends
		AbstractCrudDAO<TransportadoraEntity> implements
		TransportadoraDAO<TransportadoraEntity> {

	private static Logger logger = Logger
			.getLogger(TransportadoraDAOImpl.class);

	@Override
	public Class<TransportadoraEntity> getEntityClass() {
		return TransportadoraEntity.class;
	}

	public List<TransportadoraEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<TransportadoraEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TransportadoraEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<TransportadoraEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<TransportadoraEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<TransportadoraEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + q + "%").list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}