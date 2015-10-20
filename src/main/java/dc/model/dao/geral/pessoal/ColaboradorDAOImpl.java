package dc.model.dao.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.model.dao.geral.produto.ProdutoDAOImpl;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ColaboradorDAOImpl extends AbstractCrudDAO<ColaboradorEntity> implements ColaboradorDAO<ColaboradorEntity> {

		private static Logger logger = Logger.getLogger(ProdutoDAOImpl.class);

		@Override
		public Class<ColaboradorEntity> getEntityClass() {
			return ColaboradorEntity.class;
		}

		public List<ColaboradorEntity> listaTodos() {
			try {
				String sql = "FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());
				
				Query query = super.getSession().createQuery(sql);

				List<ColaboradorEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
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


		public List<ColaboradorEntity> procuraNomeContendo(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());
				
				Query query = super.getSession().createQuery(sql);
				query.setParameter("nome", value);

				List<ColaboradorEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<ColaboradorEntity> query(String value) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
				sql = sql.replace("#", getEntityClass().getName());
				
				value = "%" + value.toLowerCase() + "%";

				Query query = super.getSession().createQuery(sql);
				query.setParameter("q", value);

				List<ColaboradorEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "pessoa","tipoColaborador","cargo","setor","sindicato","nivelFormacao","matricula", "codigoTurmaPonto",
					"dataCadastro", "dataAdmissao", "vencimentoFerias",
					"dataTransferencia" };
		}

		/**
		 * 
		 */

		@Override
		public ColaboradorEntity find(Serializable id) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.id = :id";
				sql = sql.replace("#", this.getEntityClass().getName());
				
				Query query = super.getSession().createQuery(sql);
				query.setParameter("id", id);

				ColaboradorEntity ent = (ColaboradorEntity) query.uniqueResult();

				return ent;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<ColaboradorEntity> list() {
			try {
				String sql = "SELECT new - FROM # ent WHERE (1 = 1)";
				sql = sql.replace("#", this.getEntityClass().getName());
				sql = sql.replace("-", this.getEntityClass().getSimpleName()
						+ "(ent.id, ent.nome)");
				
				Query query = super.getSession().createQuery(sql);

				List<ColaboradorEntity> auxLista = query.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}
}