package dc.model.dao.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ColaboradorDAOImpl extends AbstractCrudDAO<ColaboradorEntity>
		implements ColaboradorDAO<ColaboradorEntity> {

	private static Logger logger = Logger.getLogger(ColaboradorDAOImpl.class);

	@Override
	public Class<ColaboradorEntity> getEntityClass() {
		return ColaboradorEntity.class;
	}

	public List<ColaboradorEntity> listaVendedores() {
		try {
			String sql = "SELECT c FROM ColaboradorEntity c INNER JOIN c.tipoColaborador t WHERE (1 = 1) AND t.id = 40 ";

			Query query = super.getSession().createQuery(sql);

			List<ColaboradorEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ColaboradorEntity> listaTecnicos() {
		try {
			String sql = "SELECT c FROM ColaboradorEntity c INNER JOIN c.tipoColaborador t WHERE (1 = 1) AND t.id = 41 ";

			Query query = super.getSession().createQuery(sql);

			List<ColaboradorEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	@Transactional
	public List<ColaboradorEntity> listaTodos() {
		try {
			String sql = "FROM Colaborador ent WHERE (1 = 1)";

			List auxLista = super.getSession().createQuery(sql).list();

			return auxLista;
		} catch (Exception e) {
			return new ArrayList<ColaboradorEntity>();
		}
	}


	public List<ColaboradorEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<ColaboradorEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ColaboradorEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<ColaboradorEntity> auxLista = super.getSession()
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