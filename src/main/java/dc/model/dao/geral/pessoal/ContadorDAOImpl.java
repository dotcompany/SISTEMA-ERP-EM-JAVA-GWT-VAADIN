package dc.model.dao.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.pessoal.ContadorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;
@Repository
public class ContadorDAOImpl extends AbstractCrudDAO<ContadorEntity> implements ContadorDAO<ContadorEntity> {
	
	
		private static Logger logger = Logger.getLogger(ContadorDAOImpl.class);

		@Override
		public Class<ContadorEntity> getEntityClass() {
			return ContadorEntity.class;
		}

		@Transactional
		public List<ContadorEntity> listaTodos() {
			try {
				String sql = "FROM Contador ent WHERE (1 = 1)";

				List auxLista = super.getSession().createQuery(sql).list();

				return auxLista;
			} catch (Exception e) {
				return new ArrayList<ContadorEntity>();
			}
		}


		public List<ContadorEntity> procuraNomeContendo(String query) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				List<ContadorEntity> auxLista = super.getSession()
						.createQuery(sql).setParameter("q", "%" + query + "%")
						.list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public List<ContadorEntity> query(String q) {
			try {
				String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
				sql = sql.replace("#", this.getEntityClass().getName());

				List<ContadorEntity> auxLista = super.getSession()
						.createQuery(sql).setParameter("q", "%" + q + "%").list();

				return auxLista;
			} catch (Exception e) {
				e.printStackTrace();

				throw e;
			}
		}

		public String[] getDefaultSearchFields() {
			return new String[] { "nome", "logradouro" };
		}
}
