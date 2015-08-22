package dc.model.dao.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sun.istack.logging.Logger;

import dc.entidade.comercial.NotaFiscalTipo;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class NotaFiscalTipoDAOImpl extends AbstractCrudDAO<NotaFiscalTipo> implements NotaFiscalTipoDAO<NotaFiscalTipo> {
	
		
			private static Logger logger = Logger.getLogger(NotaFiscalTipoDAOImpl.class);

			@Override
			public Class<NotaFiscalTipo> getEntityClass() {
				return NotaFiscalTipo.class;
			}

			@Transactional
			public List<NotaFiscalTipo> listaTodos() {
				try {
					String sql = "FROM Contador ent WHERE (1 = 1)";

					List auxLista = super.getSession().createQuery(sql).list();

					return auxLista;
				} catch (Exception e) {
					return new ArrayList<NotaFiscalTipo>();
				}
			}


			public List<NotaFiscalTipo> procuraNomeContendo(String query) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					List<NotaFiscalTipo> auxLista = super.getSession()
							.createQuery(sql).setParameter("q", "%" + query + "%")
							.list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public List<NotaFiscalTipo> query(String q) {
				try {
					String sql = "FROM # ent WHERE (1 = 1) AND ent.nome LIKE :q";
					sql = sql.replace("#", this.getEntityClass().getName());

					List<NotaFiscalTipo> auxLista = super.getSession()
							.createQuery(sql).setParameter("q", "%" + q + "%").list();

					return auxLista;
				} catch (Exception e) {
					e.printStackTrace();

					throw e;
				}
			}

			public String[] getDefaultSearchFields() {
				return new String[] { "nome"};
			}

}
