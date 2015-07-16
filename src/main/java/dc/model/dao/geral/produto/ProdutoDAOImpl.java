
package dc.model.dao.geral.produto;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.produto.ProdutoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ProdutoDAOImpl extends AbstractCrudDAO<ProdutoEntity> implements
		ProdutoDAO<ProdutoEntity> {

	private static Logger logger = Logger.getLogger(ProdutoDAOImpl.class);

	@Override
	public Class<ProdutoEntity> getEntityClass() {
		return ProdutoEntity.class;
	}

	public List<ProdutoEntity> listaTodos() {
		try {
			//String sql = "FROM # ent WHERE (1 = 1)";
			//sql = sql.replace("#", this.getEntityClass().getName());
			
			String sql = "FROM Produto WHERE (1 = 1)";
			sql = sql.replace("Produto", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);

			List<ProdutoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ProdutoEntity> procuraNomeContendo(String value) {
		try {
			//String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
			//sql = sql.replace("#", this.getEntityClass().getName());
			
			String sql = "FROM Produto WHERE (1 = 1) AND nome LIKE :q";
			sql = sql.replace("Produto", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("nome", value);

			List<ProdutoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ProdutoEntity> query(String value) {
		try {
			//String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			//sql = sql.replace("#", getEntityClass().getName());
			
			String sql = "FROM Produto  WHERE (1 = 1) AND LOWER(nome) LIKE :q";
			sql = sql.replace("Produto", getEntityClass().getName());

			value = "%" + value.toLowerCase() + "%";

			Query query = super.getSession().createQuery(sql);
			query.setParameter("q", value);

			List<ProdutoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "GTIN", "Código interno", "Nome", "Descrição" };
	}

	/**
	 * 
	 */

	@Override
	public ProdutoEntity find(Serializable id) {
		try {
			//String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
			//sql = sql.replace("#", this.getEntityClass().getName());
			
			String sql = "FROM Produto WHERE (1 = 1) AND id = :id";
			sql = sql.replace("Produto", this.getEntityClass().getName());

			Query query = super.getSession().createQuery(sql);
			query.setParameter("id", id);

			ProdutoEntity ent = (ProdutoEntity) query.uniqueResult();

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public List<ProdutoEntity> list() {
		try {
			//String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
			//sql = sql.replace("#", this.getEntityClass().getName());
			//sql = sql.replace("-", this.getEntityClass().getSimpleName()
			//		+ "(ent.id, ent.nome)");
			
			String sql = "SELECT new - FROM Produto WHERE (1 = 1)";
			sql = sql.replace("Produto", this.getEntityClass().getName());
			sql = sql.replace("-", this.getEntityClass().getSimpleName()+ "(id, nome)");

			Query query = super.getSession().createQuery(sql);

			List<ProdutoEntity> auxLista = query.list();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}