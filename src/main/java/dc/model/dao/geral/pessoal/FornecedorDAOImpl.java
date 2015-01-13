package dc.model.dao.geral.pessoal;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class FornecedorDAOImpl extends AbstractCrudDAO<FornecedorEntity>
		implements FornecedorDAO<FornecedorEntity> {

	private static Logger logger = Logger.getLogger(FornecedorDAOImpl.class);

	@Override
	public Class<FornecedorEntity> getEntityClass() {
		return FornecedorEntity.class;
	}

	public List<FornecedorEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<FornecedorEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<FornecedorEntity> procuraNomeContendo(String query) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<FornecedorEntity> auxLista = super.getSession()
					.createQuery(sql).setParameter("q", "%" + query + "%")
					.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<FornecedorEntity> query(String q) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			List<FornecedorEntity> auxLista = super.getSession()
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