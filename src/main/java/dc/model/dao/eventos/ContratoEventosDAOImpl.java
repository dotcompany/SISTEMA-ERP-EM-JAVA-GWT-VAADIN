package dc.model.dao.eventos;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.istack.logging.Logger;

import dc.entidade.geral.eventos.ContratoEventosEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContratoEventosDAOImpl extends AbstractCrudDAO<ContratoEventosEntity> implements IContratoEventosDAO<ContratoEventosEntity> {
	
			private static Logger logger = Logger.getLogger(ContratoEventosDAOImpl.class);

			@Override
			public Class<ContratoEventosEntity> getEntityClass() {
				return ContratoEventosEntity.class;
			}

			public List<ContratoEventosEntity> listaTodos() {
				try {
					String sql = "FROM # ent WHERE (1 = 1)";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
			    	List<ContratoEventosEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<ContratoEventosEntity> procuraNomeContendo(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					Query query = super.getSession().createQuery(sql);
					query.setParameter("nome", value);

					List<ContratoEventosEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}

			public List<ContratoEventosEntity> query(String value) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND LOWER(nome) LIKE :q";
					sql = sql.replace("#", getEntityClass().getName());

					value = "%" + value.toLowerCase() + "%";

					Query query = super.getSession().createQuery(sql);
					query.setParameter("q", value);
					List<ContratoEventosEntity> auxLista = query.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				
			}
			
			public String[] getDefaultSearchFields() {
				return new String[] { "curso" };
			}

}
