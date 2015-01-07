package dc.model.dao.tributario;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.tributario.IcmsCustomizadoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class IcmsCustomizadoDAOImpl extends
		AbstractCrudDAO<IcmsCustomizadoEntity> implements
		IcmsCustomizadoDAO<IcmsCustomizadoEntity> {

	private static Logger logger = Logger
			.getLogger(IcmsCustomizadoDAOImpl.class);

	@Override
	public Class<IcmsCustomizadoEntity> getEntityClass() {
		return IcmsCustomizadoEntity.class;
	}

	public List<IcmsCustomizadoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<IcmsCustomizadoEntity> auxLista = super.getSession()
					.createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<IcmsCustomizadoEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<IcmsCustomizadoEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<IcmsCustomizadoEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<IcmsCustomizadoEntity> auxLista = super.getSession()
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