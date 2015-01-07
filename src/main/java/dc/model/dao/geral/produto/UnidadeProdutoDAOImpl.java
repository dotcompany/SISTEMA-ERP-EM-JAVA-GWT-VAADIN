package dc.model.dao.geral.produto;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.produto.UnidadeProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class UnidadeProdutoDAOImpl extends
		AbstractCrudDAO<UnidadeProdutoEntity> implements
		UnidadeProdutoDAO<UnidadeProdutoEntity> {

	private static Logger logger = Logger
			.getLogger(UnidadeProdutoDAOImpl.class);

	@Override
	public Class<UnidadeProdutoEntity> getEntityClass() {
		return UnidadeProdutoEntity.class;
	}

	public List<UnidadeProdutoEntity> listaTodos() {
		try {
			String sql = "FROM # ent WHERE (1 = 1)";
			sql = sql.replace("#", this.getEntityClass().getName());
			// sql = sql.replace("-", this.getEntityClass().getSimpleName()
			// + "(ent.id, ent.nome, ent.sigla)");

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", query);

			List<UnidadeProdutoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<UnidadeProdutoEntity> procuraNomeContendo(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			sql = sql.replace("#", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<UnidadeProdutoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<UnidadeProdutoEntity> query(String value) {
		try {
			String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("#", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<UnidadeProdutoEntity> auxLista = query.list();

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